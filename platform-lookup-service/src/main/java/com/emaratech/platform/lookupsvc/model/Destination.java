package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * Destination class for holding the destination data.
 */
@Getter
@Setter
public class Destination implements Serializable {

    @JsonProperty(value = "id")
    private BigDecimal destinationId;

    @JsonProperty(value = "nameAr")
    private String destinationNameAr;

    @JsonProperty(value = "nameEn")
    private String getDestinationNameEn;

    private BigDecimal countryId;

    private BigDecimal isArchived;
}
