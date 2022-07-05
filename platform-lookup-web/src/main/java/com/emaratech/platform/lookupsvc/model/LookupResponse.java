package com.emaratech.platform.lookupsvc.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Holds the lookup response.
 */
@Getter
@Setter
public class LookupResponse extends LookupListResponse {

    private Long lookupId;

    /**
     * Constructor overloading to inject the lookup response values.
     *
     * @param lookupId the lookupId
     * @param lookupType the lookupType
     * @param lookups the lookups
     */
    public LookupResponse(Long lookupId, String lookupType, List<?> lookups) {
        super(lookupType, lookups);
        this.lookupId = lookupId;
    }
}
