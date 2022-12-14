package com.emaratech.platform.lookupsvc.webapp.endpoint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.MethodHandles;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.emaratech.platform.lookupsvc.api.LookupService;
import com.emaratech.platform.lookupsvc.model.*;
import com.emaratech.platform.lookupsvc.webapp.model.*;
import com.emaratech.platform.lookupsvc.webapp.util.ConversionHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Hidden;

/**
 * Provides the REST APIs for fetching the lookup data.
 */
@RestController
@RequestMapping(value = "${API_VERSION}/lookups", produces = MediaType.APPLICATION_JSON_VALUE)
public class LookupServiceEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

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
     * Gets the default values for destination.
     */
    @Value("#{'${default.destionation.lk}'.split(',')}")
    private List<Long> defaultDestinationIds;

    /**
     * Constructor overloading to inject the lookupService, objectMapper and
     * conversionHelper.
     *
     * @param lookupService the lookup service
     * @param objectMapper the objectMapper
     * @param conversionHelper the conversionHelper
     */
    public LookupServiceEndpoint(LookupService lookupService, ObjectMapper objectMapper,
            ConversionHelper conversionHelper) {
        this.lookupService = lookupService;
        this.objectMapper = objectMapper;
        this.conversionHelper = conversionHelper;
    }

    @GetMapping("/complete/{lookupType}")
    public ResponseEntity<BaseLookupResponse> getLookupsWithFullDetails(@PathVariable("lookupType") String lookupType) {

        LOG.info("In get lookup list method.");
        List<?> lookups = lookupService.findAll(lookupType);
        BaseLookupResponse lookupResponse = new LookupListResponse(lookupType, lookups != null ? lookups : Collections.emptyList());
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
    public ResponseEntity<List<LookupDTO>> getLookupList(@PathVariable(name = "lookupType") String lookupType)
        throws ResponseStatusException {
        LOG.info("In get lookup list method.");
        List<?> lookups = lookupService.findAll(lookupType);
        return ResponseEntity.ok(lookups != null ? conversionHelper
                .buildPartialLookupResponse(lookups, lookupType) : Collections.emptyList());
    }

    /**
     * Gets the lookup data by id.
     *
     * @param lookupId the lookupId
     * @param lookupType the lookupType
     * @return single list of object
     * @throws ResponseStatusException if unable to fetch the data
     */
    @GetMapping("/{lookupType}/{lookupId}")
    public ResponseEntity<List<LookupDTO>> getLookupByLookupTypeAndLookupId(@PathVariable("lookupType") String lookupType,
                                                                            @PathVariable("lookupId") Long lookupId)
        throws ResponseStatusException {
        List<?> singleObjectList = lookupService.findById(lookupType, lookupId);
        return ResponseEntity.ok(singleObjectList != null
                ? conversionHelper.buildPartialLookupResponse(singleObjectList, lookupType)
                : Collections.emptyList());
    }

    /**
     * Gets the gcc countries.
     *
     * @return list of gcc countries
     * @throws ResponseStatusException if unable to fetch the data
     */
    @GetMapping("/countries/gcc")
    public ResponseEntity<List<LookupSubResponse>> getGccCountries() throws ResponseStatusException {

        List<Country> lookups = (List<Country>) lookupService
                .findAll(LookupConstants.COUNTRY_LOOKUP_NAME).stream()
                .filter(country -> {
                    Country newCountryObj = (Country) country;
                    return newCountryObj.getIsGcc().intValue() == 1 && newCountryObj.getIsArchived().intValue() == 0;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(!CollectionUtils.isEmpty(lookups) ? conversionHelper
                .buildPartialLookupResponse(lookups) : Collections.emptyList());
    }

    /**
     * Gets the arab nation list.
     *
     * @return list of arab nation
     * @throws ResponseStatusException if unable to fetch the data
     */
    @GetMapping("/countries/arab")
    public ResponseEntity<List<LookupSubResponse>> getArabCountries() throws ResponseStatusException {

        List<Country> lookups = (List<Country>) lookupService
                .findAll(LookupConstants.COUNTRY_LOOKUP_NAME).stream()
                .filter(country -> {
                    Country newCountryObj = (Country) country;
                    return newCountryObj.getIsArabNation().intValue() == 1;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(!CollectionUtils.isEmpty(lookups) ? conversionHelper
                .buildPartialLookupResponse(lookups) : Collections.emptyList());
    }

    /**
     * Gets the arab nation list.
     *
     * @return list of arab nation
     * @throws ResponseStatusException if unable to fetch the data
     */
    @GetMapping("/countries/alternate")
    public ResponseEntity<List<LookupSubResponse>> getAlternateCountries() throws ResponseStatusException {

        List<LookupSubResponse> countryDTOList = lookupService
                .findAll(LookupConstants.ALTERNATE_COUNTRY_CODE_LOOKUP_NAME).stream().map(obj -> {
                    AlternateCountryCode alternateCountryCode = (AlternateCountryCode) obj;
                    return new LookupDTO(alternateCountryCode.getCountryId().longValue(), alternateCountryCode.getAlternateCountryCode());
                }).collect(Collectors.toList());

        return ResponseEntity.ok(!CollectionUtils.isEmpty(countryDTOList) ? countryDTOList : Collections.emptyList());

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
    public ResponseEntity<List<LookupDTO>> getLookupByLTypeAndCode(@PathVariable("lookupType") String lookupType,
                                                                   @PathVariable("code") String code)
        throws ResponseStatusException {
        List<LookupDTO> lookupDTOS = null;
        List<?> lookups = lookupService.findAll(lookupType);
        if (!CollectionUtils.isEmpty(lookups)) {
            lookupDTOS = (List<LookupDTO>) conversionHelper
                    .buildResponseByCode(lookups, lookupType, code);
            if (!CollectionUtils.isEmpty(lookupDTOS)) {
                lookupDTOS.get(0).setCode(code);
            }
        }
        return ResponseEntity.ok(!CollectionUtils.isEmpty(lookupDTOS) ? lookupDTOS : Collections.emptyList());
    }

    /**
     * Gets the country by code.
     *
     * @param code the code
     * @return list of objects
     * @throws ResponseStatusException if unable to fetch the lookup data
     */
    @GetMapping("/country/code/{code}")
    public ResponseEntity<List<LookupDTO>> getCountryByCode(@PathVariable("code") String code)
        throws ResponseStatusException {
        return getLookupByLTypeAndCode(LookupConstants.COUNTRY_LOOKUP_NAME, code);
    }

    /**
     * Gets the destination by code.
     *
     * @param code the code
     * @return List of objects
     * @throws ResponseStatusException if unable to fetch the lookup data
     */
    @GetMapping("/destination/code/{code}")
    public ResponseEntity<List<LookupDTO>> getDestinationByCode(@PathVariable("code") String code)
        throws ResponseStatusException {
        return getLookupByLTypeAndCode(LookupConstants.DESTINATION_LOOKUP_NAME, code);
    }

    /**
     * Gets destination by id.
     *
     * @param id the id
     * @return list of objects
     * @throws ResponseStatusException if unable to fetch the lookup data
     */
    @GetMapping("/destination/id/{id}")
    public ResponseEntity<List<?>> getDestinationById(@PathVariable("id") Long id)
        throws ResponseStatusException {

        List<?> destinations = lookupService.findById(LookupConstants.DESTINATION_LOOKUP_NAME, id);
        return ResponseEntity.ok(!CollectionUtils.isEmpty(destinations) ? destinations : Collections.emptyList());
    }

    /**
     * Gets the destination by name.
     *
     * @param searchWord the searchWord
     * @param keyword the keyword
     * @return list of objects
     * @throws ResponseStatusException if unable to fetch the data
     */
    @GetMapping("/destination/{searchWord}")
    public ResponseEntity<List<?>> getDestinationByName(@PathVariable("searchWord") String searchWord,
                                                        @RequestParam(value = "keyword", required = false) String keyword)
        throws ResponseStatusException {

        if (searchWord.length() < 3) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        List<Destination> destinations = (List<Destination>) lookupService.findAll(LookupConstants.DESTINATION_LOOKUP_NAME);
        if (searchWord.equalsIgnoreCase("default")) {
            List<Long> defaultDestinationIdsLocal = defaultDestinationIds;
            defaultDestinationIdsLocal.add(Destination.Name.UNKNOWN_9999.getId());
            destinations = destinations.parallelStream()
                    .filter(destination -> defaultDestinationIds.stream().anyMatch(aLong -> destination.getDestinationId().longValue() == aLong))
                    .collect(Collectors.toList());
        } else {
            if (searchWord.equalsIgnoreCase("search_word")
                    && StringUtils.hasLength(keyword)) {
                searchWord = keyword;
            }
            boolean isArabicLocale = true;
            boolean searchByCode = false;
            if (searchWord.matches("^[a-zA-Z]*$")) {
                isArabicLocale = false;
                if (searchWord.length() == 3) {
                    searchByCode = true;
                }
            }
            String finalSearchWord = searchWord;
            boolean finalIsArabicLocale = isArabicLocale;
            boolean finalSearchByCode = searchByCode;
            destinations = destinations.parallelStream().filter(destination -> {
                if (finalIsArabicLocale) {
                    return destination.getDestinationNameAr().startsWith(finalSearchWord);
                }
                if (finalSearchByCode && destination
                        .getDestinationCode().toUpperCase().startsWith(finalSearchWord)) {
                    return true;
                }
                return destination.getDestinationNameEn().startsWith(finalSearchWord);
            }).collect(Collectors.toList());
        }
        List<LookupDTO> lookupDTOList = destinations.stream().map(destination -> new LookupDTO(destination.getDestinationId().longValue(),
                                                                                               destination.getDestinationNameEn(),
                                                                                               destination.getDestinationNameAr(),
                                                                                               destination.getDestinationCode()))
                .collect(Collectors.toList());
        lookupDTOList.sort((lookupDTO1, lookupDTO2) -> {
            if (lookupDTO1.getId() < lookupDTO2.getId()) {
                return -1;
            }
            return 1;
        });
        return ResponseEntity.ok(lookupDTOList);

    }

    /**
     * Gets the city lookup data by emirate id.
     *
     * @param emirateId the emirateId
     * @return list of objects
     * @throws ResponseStatusException if unable to fetch the data
     */
    @GetMapping("/city/{emirateId}")
    public ResponseEntity<List<LookupDTO>> getCityByEmiratesId(@PathVariable("emirateId") Long emirateId) throws ResponseStatusException {
        List<City> lookups = (List<City>) lookupService.findAll(LookupConstants.CITY_LOOKUP_NAME)
                .stream().filter(obj -> {
                    City city = (City) obj;
                    return city.getEmirateId().longValue() == emirateId;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(!CollectionUtils.isEmpty(lookups) ? conversionHelper
                .buildPartialLookupResponse(lookups, LookupConstants.CITY_LOOKUP_NAME) : Collections.emptyList());
    }

    /**
     * Gets the area lookup data by city id.
     *
     * @param cityId the cityId
     * @return list of objects
     * @throws ResponseStatusException if unable to fetch the data
     */
    @GetMapping("/area/{cityId}")
    public ResponseEntity<List<LookupDTO>> getAreaByCity(@PathVariable("cityId") Long cityId) throws ResponseStatusException {

        List<Area> lookups = (List<Area>) lookupService.findAll(LookupConstants.AREA_LOOKUP_NAME)
                .stream().filter(obj -> {
                    Area area = (Area) obj;
                    return area.getCityId() != null && area.getCityId().longValue() == cityId;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(!CollectionUtils.isEmpty(lookups) ? conversionHelper
                .buildPartialLookupResponse(lookups, LookupConstants.AREA_LOOKUP_NAME) : Collections.emptyList());
    }

    /**
     * Gets the faith lookup data.
     *
     * @return list of objects
     * @throws ResponseStatusException if unable to fetch the data
     */
    @GetMapping("/faith")
    public ResponseEntity<List<LookupDTO>> getLookupFaith() throws ResponseStatusException {
        List<Faith> faithList = (List<Faith>) lookupService.findAll(LookupConstants.FAITH_LOOKUP_NAME);

        return ResponseEntity.ok(faithList.stream().map(faith -> {
            LookupDTO lookupDTO = new LookupDTO(faith.getFaithId().longValue(), faith.getFaithNameEn(), faith.getFaithNameAr(),
                                                faith.getReligionId().longValue());
            return lookupDTO;
        }).collect(Collectors.toList()));
    }

    /**
     * Gets the border Visas list.
     *
     * @return list of objects
     * @throws ResponseStatusException if unable to fetch the data
     */
    @GetMapping("/borderVisas")
    public ResponseEntity<List<LookupSubResponse>> getBorderVisaList() throws ResponseStatusException {

        List<VisaType> visaTypeList = (List<VisaType>) lookupService
                .findAll(LookupConstants.VISA_TYPE_LOOKUP_NAME).stream().filter(obj -> {
                    VisaType visaType = (VisaType) obj;
                    return visaType.getIsBorderVisa().intValue() == 1;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(visaTypeList
                .stream()
                .map(visaType -> new LookupSubResponse(visaType.getVisaTypeId().longValue(),
                                                       visaType.getVisaTypeNameEn(),
                                                       visaType.getVisaTypeNameAr()))
                .collect(Collectors.toList()));
    }

    /**
     * Gets the Indian Visa Types.
     *
     * @return list of Objects
     */

    @GetMapping("/indianVisaCardTypes")
    public ResponseEntity<List<LookupSubResponse>> getIndianVisaType() {

        List<LookupSubResponse> indianVisaTypeList = new ArrayList<>();

        for (IndianVisaCardType.Name cardType : IndianVisaCardType.Name.values()) {
            LookupSubResponse indianVisaType = new LookupSubResponse();
            indianVisaType.setId(cardType.getId());
            indianVisaType.setNameAr(cardType.getDescAr());
            indianVisaType.setNameEn(cardType.getDescEn());
            indianVisaTypeList.add(indianVisaType);
        }
        return ResponseEntity.ok(indianVisaTypeList);

    }

    /**
     * Gets the flight details by code.
     *
     * @param code the code
     * @return list of objects
     */
    @GetMapping("/flightCode/code/{code}")
    public ResponseEntity<List<LookupDTO>> getFlightDetailsByCode(@PathVariable("code") String code) {
        return getLookupByLTypeAndCode(LookupConstants.FLIGHT_CODE_LOOKUP_NAME, code);
    }

    @GetMapping("/importDetails")
    public ResponseEntity<List<?>> getDataImportDetails() {
        return ResponseEntity.ok(lookupService.getImportDetails());
    }

    /**
     * Saving rdms lookup data into redis.
     *
     * @param lookupType the lookupType
     * @param multipartFile the multipartFile
     * @return response String
     */
    @Hidden
    @PostMapping("/save/{lookupType}")
    public ResponseEntity<?> saveData(@PathVariable("lookupType") String lookupType,
                                      @RequestParam("file") MultipartFile multipartFile)
        throws ResponseStatusException {
        List<?> csvData = null;
        try {
            InputStream inputStream = multipartFile.getInputStream();
            File tempFile = new File(System.getProperty("user.dir") + "temp.xlsx");
            OutputStream outputStream = new FileOutputStream(tempFile);
            inputStream.transferTo(outputStream);
            inputStream.close();
            outputStream.close();
            csvData = ConversionHelper.getMapSortedList(tempFile, lookupType);
            tempFile.delete();
            List<?> finalCsvData = csvData;

            LOG.info("Processing in separate thread");
            lookupService.save(lookupType, objectMapper
                    .writeValueAsString(finalCsvData));

        } catch (Exception ex) {
            LOG.error("Exception occurred during saving the lookup data.", ex);
            return ResponseEntity.internalServerError().body("Data is not saved.");
        }
        return ResponseEntity.ok("Data is saved successfully.");
    }

    /**
     * Saves the lookup data into Redis.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void init() {

        LOG.info("Starting the data load process into Redis.");
        try {
            File[] files = (new File(getClass().getClassLoader().getResource("lookupData").toURI())).listFiles();
            for (File file : files) {
                String lookupType = file.getName();
                String entityName = lookupType.substring(0, lookupType.lastIndexOf("."));
                try {
                    List<?> list = ConversionHelper.getMapSortedList(file, entityName);
                    LOG.info("Starting the data load process into Redis for the entity {} ", entityName);
                    lookupService.saveRdmsData(entityName, objectMapper
                            .writeValueAsString(list));
                    LOG.info("Completed the data load process into Redis for the entity {} ", entityName);
                } catch (JsonProcessingException exception) {
                    LOG.warn("Unable to process the data into redis and exception is {} for the entity {} ", exception, entityName);
                } catch (Exception exception) {
                    LOG.warn("Unable to mapped the excel data into list and exception is {} for the entity {} ", exception, entityName);
                }
            }
            LOG.info("Completed the data load process into Redis.");
        } catch (URISyntaxException exception) {
            LOG.error("Error while reading the data import files from lookup folder", exception.getReason());
        }
    }

}
