package com.emaratech.platform.lookupsvc.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
/**
 *
 */
public class LookupListResponse extends BaseLookupResponse {
    private List<?> lookupList;

    /**
     *
     *
     * @param lookupType
     * @param lookups
     */
    public LookupListResponse(String lookupType, List<?> lookups) {
        super(lookupType);
        this.lookupList = lookups;
    }
}
