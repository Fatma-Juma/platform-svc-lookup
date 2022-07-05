package com.emaratech.platform.lookupsvc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

/**
 * Base class for lookup response.
 */
public class BaseLookupResponse {
    private String lookupType;

    /**
     * Constructor overloading to inject the lookup response value.
     *
     * @param lookupType
     */
    public BaseLookupResponse(String lookupType) {
        this.lookupType = lookupType;
    }
}
