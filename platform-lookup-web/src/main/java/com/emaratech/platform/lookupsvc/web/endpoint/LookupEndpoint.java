package com.emaratech.platform.lookupsvc.web.endpoint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.emaratech.platform.lookupsvc.api.LookupService;
import com.emaratech.platform.lookupsvc.model.*;
import com.emaratech.platform.lookupsvc.util.ConversionHelper;
import com.emaratech.platform.lookupsvc.util.ModelMapperConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Provides the REST APIs for fetching the lookup data.
 */
@RestController
@RequestMapping(value = "${API_VERSION}/lookups", produces = MediaType.APPLICATION_JSON_VALUE)
public class LookupEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String EMPTY_RESPONSE = "{}";

    /**
     * Declaring the lookupService instance.
     */
    private final LookupService lookupService;

    /**
     * Declaring the objectMapper instance.
     */
    private final ObjectMapper objectMapper;

    /**
     * Declaring the modelMapperConverter instance.
     */
    private final ModelMapperConverter modelMapperConverter;

    /**
     * Constructor overloading to inject the lookupService and objectMapper.
     *
     * @param lookupService the lookup service
     * @param objectMapper the objectMapper
     * @param modelMapperConverter the modelMapperConverter
     */
    public LookupEndpoint(LookupService lookupService, ObjectMapper objectMapper,
            ModelMapperConverter modelMapperConverter) {
        this.lookupService = lookupService;
        this.objectMapper = objectMapper;
        this.modelMapperConverter = modelMapperConverter;
    }

    /**
     * Gets the countries list as json.
     *
     * @param lookupType the lookupType
     * @return the countries as json.
     * @throws ResponseStatusException if unable to fetch the data
     */
    @GetMapping("/{lookupType}")
    public ResponseEntity<?> getLookupList(@PathVariable(name = "lookupType", required = true) String lookupType)
        throws ResponseStatusException {
        LOG.info("In get lookup list method.");
        List<?> lookups = lookupService.findAll(lookupType);
        BaseLookupResponse lookupResponse = new LookupListResponse(lookupType, lookups != null ? lookups : Collections.EMPTY_LIST);
        return ResponseEntity.ok(lookupResponse);
    }

    /**
     * Saves the lookup data.
     *
     * @param lookupRequest the lookupRequest
     * @return the lookup data
     * @throws ResponseStatusException if unable to save
     */
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid LookupRequest lookupRequest)
        throws ResponseStatusException {

        lookupService.save(lookupRequest.getLookupType(), lookupRequest.getLookupData());
        return new ResponseEntity<>("Saved successfully", HttpStatus.OK);

    }

    /**
     * Gets the lookup data by Id.
     *
     * @param lookupId the lookupId
     * @param lookupType the lookupType
     * @return single list of object
     * @throws ResponseStatusException if unable to fetch the data
     */
    @GetMapping("/{lookupType}/{lookupId}")
    public ResponseEntity<?> getLookupById(@PathVariable("lookupType") String lookupType,
                                           @PathVariable("lookupId") Long lookupId)
        throws ResponseStatusException {
        List<?> singleObjectList = lookupService.findById(lookupType, lookupId);
        BaseLookupResponse lookupResponse = new LookupResponse(lookupId, lookupType, singleObjectList != null ? singleObjectList : Collections.EMPTY_LIST);
        return ResponseEntity.ok(lookupResponse);
    }

    /**
     *
     *
     * @return
     */
    @GetMapping("/countries/gcc")
    public ResponseEntity<?> getGccCountries() {

        List<Country> lookups = (List<Country>) lookupService
                .findAll("Country").stream()
                .filter(country -> {
                    Country newCountryObj = (Country) country;
                    if (newCountryObj.getIsGcc().intValue() == 1) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());

        List<LookupSubResponse> lookupSubRes = new ArrayList<>();
        lookups.forEach(country -> {
            LookupSubResponse lookupSubResponse = new LookupSubResponse(country.getCountryId().intValue(),
                                                                        country.getCountryNameEn(), country.getCountryNameAr());
            lookupSubRes.add(lookupSubResponse);
        });

        return ResponseEntity.ok(lookupSubRes);
    }

    /**
     * Saving rdms lookup data into redis.
     *
     * @param lookupType the lookupType
     * @param multipartFile the multipartFile
     * @return response String
     */
    @PostMapping("/save-rdms/{lookupType}")
    public DeferredResult<ResponseEntity<?>> saveOldData(@PathVariable("lookupType") String lookupType,
                                                         @RequestParam("file") MultipartFile multipartFile) {
        List<?> csvData = null;
        DeferredResult<ResponseEntity<?>> output = new DeferredResult<>(60000L);
        output.onTimeout(() -> output.setErrorResult(
                                                     ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout occurred.")));
        try {
            InputStream inputStream = multipartFile.getInputStream();
            File tempFile = new File(System.getProperty("java.io.tmpdir") + "temp.xlsx");
            OutputStream outputStream = new FileOutputStream(tempFile);
            inputStream.transferTo(outputStream);
            inputStream.close();
            outputStream.close();
            csvData = ConversionHelper.getList(tempFile.getPath(), lookupType);
            tempFile.deleteOnExit();
            output.setResult(ResponseEntity.ok("Saving data job initiated.."));
        } catch (Exception ex) {
            LOG.error("Exception occurred during saving the lookup data.", ex);
            output.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL_SERVER_ERROR occurred."));
        }

        List<?> finalCsvData = csvData;
        ForkJoinPool.commonPool().submit(() -> {
            try {
                LOG.info("Processing in separate thread");
                lookupService.saveRdmsData(lookupType, objectMapper
                        .writeValueAsString(finalCsvData));
            } catch (JsonProcessingException ex) {
                LOG.error("Request processing interrupted ", ex);
                output.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL_SERVER_ERROR occurred."));
            }

        });

        return output;
    }
}
