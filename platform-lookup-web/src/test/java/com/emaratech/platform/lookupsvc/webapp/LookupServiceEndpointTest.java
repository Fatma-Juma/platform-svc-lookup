package com.emaratech.platform.lookupsvc.webapp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.emaratech.platform.lookupsvc.model.Country;
import com.emaratech.platform.lookupsvc.webapp.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.emaratech.platform.lookupsvc.api.LookupService;
import com.emaratech.platform.lookupsvc.webapp.endpoint.LookupServiceEndpoint;
import com.emaratech.platform.lookupsvc.webapp.util.ConversionHelper;

/**
 * Unit test for {@code LookupServiceEndpointTest}.
 */
@ExtendWith(MockitoExtension.class)
public class LookupServiceEndpointTest {


    @InjectMocks
    private LookupServiceEndpoint classUnderTest;

    @Mock
    private LookupService lookupService;

    @Mock
    private ConversionHelper conversionHelper;

    /**
     * Tests
     * {@link LookupServiceEndpoint#getLookupList(String)}
     * expect {@code ResponseEntity<List<LookupDTO>>} list of
     * that contains the lookup data body
     *
     * @throws ResponseStatusException if unable to fetch the data
     */

    @Test
    public void testGetLookupListAndExpectListInResponse() throws ResponseStatusException {

        when(lookupService.findAll(LookupConstants.COUNTRY_LOOKUP_NAME)).thenReturn(getLookupDtoStubList());
        ResponseEntity<List<LookupDTO>> response = classUnderTest.getLookupList(LookupConstants.COUNTRY_LOOKUP_NAME);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(200, response.getStatusCodeValue()),
                () -> assertNotNull(response.getBody())
            );

        
    }

    /**
     * Gets the lookup dto list data.
     *
     * @return list of objects
     */
    private List getLookupDtoStubList() {
        List<LookupDTO> lookupDTOList = new ArrayList<>();
        LookupDTO lookupDTO = new LookupDTO(203L, "Pakistan", "باكستان");
        lookupDTOList.add(lookupDTO);
        return lookupDTOList;
    }

    /**
     * Tests
     * {@link LookupServiceEndpoint#getLookupsWithFullDetails(String)}
     * expect {@code ResponseEntity<BaseLookupResponse>} list of
     * that contains the lookup data body
     *
     * @throws ResponseStatusException if unable to fetch the data
     */

    @Test
    public void testGetLookupsWithFullDetailsAndExpectListInResponse() throws ResponseStatusException{

        when(lookupService.findAll(LookupConstants.COUNTRY_LOOKUP_NAME)).thenReturn(getLookupDtoBaseList());
        ResponseEntity<BaseLookupResponse> response = classUnderTest.getLookupsWithFullDetails(LookupConstants.COUNTRY_LOOKUP_NAME);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(200, response.getStatusCodeValue()),
                () -> assertNotNull(response.getBody())
        );


    }

    /**
     * Gets the lookup dto list data.
     *
     * @return list of objects
     */

    private List getLookupDtoBaseList() {
        List<BaseLookupResponse> baseLookupResponseList = new ArrayList<>();
        BaseLookupResponse baseLookupResponse = new BaseLookupResponse("Country");
        baseLookupResponseList.add(baseLookupResponse);
        return baseLookupResponseList;
    }



    /**
     * Tests
     * {@link LookupServiceEndpoint#getLookupByLookupTypeAndLookupId(String, Long)}
     * expect {@code ResponseEntity<List<LookupDTO>>} list of
     * that contains the lookup data body
     *
     * @throws ResponseStatusException if unable to fetch the data
     */

    @Test
    public void testGetLookupByLookupTypeAndLookupIdAndExpectListInResponse() throws ResponseStatusException {
        Long id = 203L;
        when(lookupService.findById(LookupConstants.COUNTRY_LOOKUP_NAME, id)).thenReturn(getLookupByLookupTypeAndLookUpIdDtoList());
        ResponseEntity<List<LookupDTO>> response = classUnderTest.getLookupByLookupTypeAndLookupId(LookupConstants.COUNTRY_LOOKUP_NAME, id);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(200, response.getStatusCodeValue()),
                () -> assertNotNull(response.getBody())
        );


    }

    /**
     * Gets the lookup dto list data.
     *
     * @return list of objects
     */

    private List getLookupByLookupTypeAndLookUpIdDtoList() {
        List<LookupDTO> lookupDTOList = new ArrayList<>();
        LookupDTO lookupDTO = new LookupDTO(203L, "Pakistan", "باكستان");
        lookupDTOList.add(lookupDTO);
        return lookupDTOList;
    }

    /**
     * Tests
     * {@link LookupServiceEndpoint#getLookupByLTypeAndCode(String, String)}
     * expect {@code ResponseEntity<List<LookupDTO>>} list of
     * that contains the lookup data body
     *
     * @throws ResponseStatusException if unable to fetch the data
     */

    @Test
    public void testGetLookupByLTypeAndCodeAndExpectListInResponse() throws ResponseStatusException {
        String code = "PNG";
        when(lookupService.findAll(LookupConstants.COUNTRY_LOOKUP_NAME)).thenReturn(getLookupByLTypeAndCodeDtoList());
        ResponseEntity<List<LookupDTO>> response = classUnderTest.getLookupByLTypeAndCode(LookupConstants.COUNTRY_LOOKUP_NAME, code);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(200, response.getStatusCodeValue()),
                () -> assertNotNull(response.getBody())
        );


    }


    /**
     * Gets the lookup dto list data.
     *
     * @return list of objects
     */
    private List getLookupByLTypeAndCodeDtoList() {
        List<LookupDTO> lookupDTOList = new ArrayList<>();
        LookupDTO lookupDTO = new LookupDTO(203L, "Pakistan", "باكستان","PNG");
        lookupDTOList.add(lookupDTO);
        return lookupDTOList;
    }

    /**
     * Tests
     * {@link LookupServiceEndpoint#getCountryByCode(String)}
     * expect {@code ResponseEntity<List<LookupDTO>>} list of
     * that contains the lookup data body
     *
     * @throws ResponseStatusException if unable to fetch the data
     */
    @Test
    public void testGetCountryByCodeAndExpectListInResponse() throws ResponseStatusException {
        when(lookupService.findAll(LookupConstants.COUNTRY_LOOKUP_NAME)).thenReturn(getCountryByCodeDtoList());
        String code = "PNG";
        ResponseEntity<List<LookupDTO>> response = classUnderTest.getCountryByCode(code);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(200, response.getStatusCodeValue()),
                () -> assertNotNull(response.getBody())
        );


    }

    /**
     * Gets the lookup dto list data.
     *
     * @return list of objects
     */

    private List getCountryByCodeDtoList() {
        List<LookupDTO> lookupDTOList = new ArrayList<>();
        LookupDTO lookupDTO = new LookupDTO(203L, "Pakistan", "باكستان","PNG");
        lookupDTOList.add(lookupDTO);
        return lookupDTOList;
    }

    /**
     * Tests
     * {@link LookupServiceEndpoint#getDestinationByCode(String)}
     * expect {@code ResponseEntity<List<LookupDTO>>} list of
     * that contains the lookup data body
     *
     * @throws ResponseStatusException if unable to fetch the data
     */
    @Test
    public void testGetDestinationByCodeAndExpectListInResponse() throws ResponseStatusException {
        when(lookupService.findAll(LookupConstants.DESTINATION_LOOKUP_NAME)).thenReturn(getDestinationByCodeDtoList());
        String code = "DKR";
        ResponseEntity<List<LookupDTO>> response = classUnderTest.getDestinationByCode(code);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(200, response.getStatusCodeValue()),
                () -> assertNotNull(response.getBody())
        );


    }

    /**
     * Gets the lookup dto list data.
     *
     * @return list of objects
     */

    private List getDestinationByCodeDtoList() {
        List<LookupDTO> lookupDTOList = new ArrayList<>();
        LookupDTO lookupDTO = new LookupDTO(276L, "Bucharest, Romania", "بوخارست، رومانيا","DKR");
        lookupDTOList.add(lookupDTO);
        return lookupDTOList;
    }

    /**
     * Tests
     * {@link LookupServiceEndpoint#getDestinationById(Long)}
     * expect {@code ResponseEntity<List<?>>} list of
     * that contains the lookup data body
     *
     * @throws ResponseStatusException if unable to fetch the data
     */
    @Test
    public void testGetDestinationByIdAndExpectListInResponse() throws ResponseStatusException {
        Long id = 1l;
        when(lookupService.findById(LookupConstants.DESTINATION_LOOKUP_NAME, id)).thenReturn(getDestinationByIdDtoList());
        ResponseEntity<List<?>> response = classUnderTest.getDestinationById(id);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(200, response.getStatusCodeValue()),
                () -> assertNotNull(response.getBody())
        );


    }

    /**
     * Gets the lookup dto list data.
     *
     * @return list of objects
     */

    private List getDestinationByIdDtoList() {
        List<BaseLookupResponse> baseLookupResponseList = new ArrayList<>();
        BaseLookupResponse baseLookupResponse = new BaseLookupResponse("Destination");
        baseLookupResponseList.add(baseLookupResponse);
        return baseLookupResponseList;
    }








}
