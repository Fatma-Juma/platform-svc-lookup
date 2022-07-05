package com.emaratech.platform.lookupsvc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

/**
 *
 */
public class BaseLookupResponse {
    private String lookupType;

    /**
     *
     *
     * @param lookupType
     */
    public BaseLookupResponse(String lookupType) {
        this.lookupType = lookupType;
    }
}
