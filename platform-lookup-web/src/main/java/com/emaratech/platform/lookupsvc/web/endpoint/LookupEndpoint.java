package com.emaratech.platform.lookupsvc.web.endpoint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.MethodHandles;
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
     * Declaring the conversionHelper instance.
     */
    private final ConversionHelper conversionHelper;

    /**
     * Constructor overloading to inject the lookupService, objectMapper and
     * conversionHelper.
     *
     * @param lookupService the lookup service
     * @param objectMapper the objectMapper
     * @param conversionHelper the conversionHelper
     */
    public LookupEndpoint(LookupService lookupService, ObjectMapper objectMapper,
            ConversionHelper conversionHelper) {
        this.lookupService = lookupService;
        this.objectMapper = objectMapper;
        this.conversionHelper = conversionHelper;
    }

    @GetMapping("/complete/{lookupType}")
    public ResponseEntity<?> getLookupsWithFullDetails(@PathVariable("lookupType") String lookupType) {

        LOG.info("In get lookup list method.");
        List<?> lookups = lookupService.findAll(lookupType);
        BaseLookupResponse lookupResponse = new LookupListResponse(lookupType, lookups != null ? lookups : Collections.EMPTY_LIST);
        return ResponseEntity.ok(lookupResponse);

    }

    /**
     * Gets the countries list as json.
     *
     * @param lookupType the lookupType
     * @return the countries as json.
     * @throws ResponseStatusException if unable to fetch the data
     */
    @GetMapping("/{lookupType}")
    public ResponseEntity<?> getLookupList(@PathVariable(name = "lookupType") String lookupType)
        throws ResponseStatusException {
        LOG.info("In get lookup list method.");
        List<?> lookups = lookupService.findAll(lookupType);
        return ResponseEntity.ok(lookups != null ? conversionHelper
                .buildPartialLookupResponse(lookups, lookupType) : Collections.EMPTY_LIST);
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
    public ResponseEntity<?> getLookupByLookupTypeAndLookupId(@PathVariable("lookupType") String lookupType,
                                                              @PathVariable("lookupId") Long lookupId)
        throws ResponseStatusException {
        List<?> singleObjectList = lookupService.findById(lookupType, lookupId);
        return ResponseEntity.ok(singleObjectList != null
                ? conversionHelper.buildPartialLookupResponse(singleObjectList, lookupType)
                : Collections.EMPTY_LIST);
    }

    /**
     * Gets the gcc countries.
     *
     * @return list of gcc countries
     * @throws ResponseStatusException if unable to fetch the data
     */
    @GetMapping("/countries/gcc")
    public ResponseEntity<?> getGccCountries() throws ResponseStatusException {

        List<Country> lookups = (List<Country>) lookupService
                .findAll("Country").stream()
                .filter(country -> {
                    Country newCountryObj = (Country) country;
                    if (newCountryObj.getIsGcc().intValue() == 1) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(conversionHelper
                .buildPartialLookupResponse(lookups, "Country"));
    }

    /**
     * Gets the arab nation list.
     *
     * @return list of arab nation
     * @throws ResponseStatusException if unable to fetch the data
     */
    @GetMapping("/countries/arab")
    public ResponseEntity<?> getArabCountries() throws ResponseStatusException {

        List<Country> lookups = (List<Country>) lookupService
                .findAll("Country").stream()
                .filter(country -> {
                    Country newCountryObj = (Country) country;
                    if (newCountryObj.getIsArabNation().intValue() == 1) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(conversionHelper
                .buildPartialLookupResponse(lookups, "Country"));
    }

    /**
     * Gets the lookup data by lookupType and code.
     *
     * @param lookupType the lookupType
     * @param code the code
     * @return list of objects
     * @throws ResponseStatusException if unable to fetch the data
     */
    @GetMapping("/lc/{lookupType}/{code}")
    public ResponseEntity<?> getLookupByLTypeAndCode(@PathVariable(value = "lookupType") String lookupType,
                                                     @PathVariable(value = "code") String code)
        throws ResponseStatusException {

        List<?> lookups = lookupService.findAll(lookupType);
        return ResponseEntity.ok(lookups != null ? conversionHelper
                .buildResponseByCode(lookups, lookupType, code) : Collections.EMPTY_LIST);
    }

    /**
     * Gets the city lookup data by emirate id.
     *
     * @param emirateId the emirateId
     * @return list of objects
     * @throws ResponseStatusException if unable to fetch the data
     */
    @GetMapping("/city/{emirateId}")
    public ResponseEntity<?> getCityByEmiratesId(@PathVariable("emirateId") Long emirateId) throws ResponseStatusException {
        List<City> lookups = (List<City>) lookupService.findAll("City")
                .stream().filter(obj -> {
                    City city = (City) obj;
                    if (city.getEmirateId().longValue() == emirateId) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(lookups != null ? conversionHelper
                .buildPartialLookupResponse(lookups, "City") : Collections.EMPTY_LIST);
    }

    /**
     * Gets the area lookup data by city id.
     *
     * @param cityId the cityId
     * @return list of objects
     * @throws ResponseStatusException if unable to fetch the data
     */
    @GetMapping("/area/{cityId}")
    public ResponseEntity<?> getAreaByCity(@PathVariable("cityId") Long cityId) throws ResponseStatusException {

        List<Area> lookups = (List<Area>) lookupService.findAll("Area")
                .stream().filter(obj -> {
                    Area area = (Area) obj;
                    if (area.getCityId() != null && area.getCityId().longValue() == cityId) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(lookups != null ? conversionHelper
                .buildPartialLookupResponse(lookups, "Area") : Collections.EMPTY_LIST);
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
            csvData = ConversionHelper.getMapSortedList(tempFile.getPath(), lookupType);
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
