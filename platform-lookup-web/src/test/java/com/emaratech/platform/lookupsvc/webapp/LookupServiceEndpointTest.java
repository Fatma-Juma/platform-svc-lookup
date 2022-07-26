package com.emaratech.platform.lookupsvc.webapp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.emaratech.platform.lookupsvc.api.LookupService;
import com.emaratech.platform.lookupsvc.webapp.endpoint.LookupServiceEndpoint;
import com.emaratech.platform.lookupsvc.webapp.model.LookupConstants;
import com.emaratech.platform.lookupsvc.webapp.model.LookupDTO;
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
    public void testGetLookupList() throws ResponseStatusException {

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

}
