package com.emaratech.platform.lookupsvc.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 */
@Getter
@Setter
public class LookupResponse extends LookupListResponse {

    private Long lookupId;

    /**
     *
     * @param lookupId
     * @param lookupType
     * @param lookups
     */
    public LookupResponse(Long lookupId, String lookupType, List<?> lookups) {
        super(lookupType, lookups);
        this.lookupId = lookupId;
    }
}
