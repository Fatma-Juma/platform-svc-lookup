package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * AlternateCountryCode holds the alternate country code data.
 */
@Getter
@Setter
public class AlternateCountryCode implements Serializable {

    @JsonProperty(value = "id")
    private BigDecimal alternateCountryCodeId;

    private BigDecimal countryId;

    private String alternateCountryCode;

    private BigDecimal isActive;
}
