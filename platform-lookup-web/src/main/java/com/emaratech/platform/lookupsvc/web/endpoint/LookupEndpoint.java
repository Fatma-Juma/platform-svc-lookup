package com.emaratech.platform.lookupsvc.web.endpoint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.MethodHandles;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;

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
import com.emaratech.platform.lookupsvc.model.LookupRequest;
import com.emaratech.platform.lookupsvc.model.LookupSubRequest;
import com.emaratech.platform.lookupsvc.util.ConversionHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.Valid;

/**
 * Provides the REST APIs for fetching the lookup data.
 */
@RestController
@RequestMapping(value = "${API_VERSION}/lookup", produces = MediaType.APPLICATION_JSON_VALUE)
public class LookupEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String EMPTY_RESPONSE = "{}";

    /**
     * Declaring the lookupService instance.
     */
    private final LookupService lookupService;

    private final ObjectMapper objectMapper;

    /**
     * Constructor overloading to inject the lookupService.
     *
     * @param lookupService the lookup service
     * @param objectMapper the objectMapper
     */
    public LookupEndpoint(LookupService lookupService, ObjectMapper objectMapper) {
        this.lookupService = lookupService;
        this.objectMapper = objectMapper;
    }

    /**
     * Gets the countries list as json.
     *
     * @param lookupType the lookupType
     * @return the countries as json.
     * @throws ResponseStatusException if unable to fetch data
     */
    @GetMapping("/{lookupType}")
    public ResponseEntity<List<?>> getLookupList(@PathVariable(name = "lookupType", required = true) String lookupType)
        throws ResponseStatusException {
        LOG.info("In get lookup list method.");
        List<?> lookups = lookupService.findAll(lookupType);
        if (Objects.nonNull(lookups)) {
            return ResponseEntity.ok(lookups);
        }
        LOG.warn("No records are found.");
        return ResponseEntity.ok(Collections.emptyList());
    }

    /**
     * Saves the lookup data.
     *
     * @param lookupRequest the lookupRequest
     * @return the lookup data
     * @throws ResponseStatusException if not able to save
     */
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid LookupRequest lookupRequest)
        throws ResponseStatusException {

        lookupService.save(lookupRequest.getLookupType(), lookupRequest.getLookupData());
        return new ResponseEntity<>("Saved successfully", HttpStatus.OK);

    }

    @GetMapping("/{lookupType}/{id}")
    public ResponseEntity<?> getLookupById(@PathVariable("lookupType") String lookupType,
                                           @PathVariable("id") Long id)
        throws ResponseStatusException {
        Object data = lookupService.findById(lookupType, id);
        if (!Objects.isNull(data)) {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.ok(EMPTY_RESPONSE);
    }

    /**
     * Saves the lookup relation.
     *
     * @param lookupSubRequest the lookupSubRequest
     * @return the lookup data
     */
    @PostMapping("/relation/save")
    public ResponseEntity<String> saveRelation(@RequestBody LookupSubRequest lookupSubRequest) {
        return null;

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
