package com.emaratech.platform.lookupsvc.webapp.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * Holds the lookup response.
 */
public class LookupListResponse extends BaseLookupResponse {
    private List<?> lookupList;

    /**
     * Constructor overloading to inject the lookup response values.
     *
     * @param lookupType
     * @param lookups
     */
    public LookupListResponse(String lookupType, List<?> lookups) {
        super(lookupType);
        this.lookupList = lookups;
    }
}
