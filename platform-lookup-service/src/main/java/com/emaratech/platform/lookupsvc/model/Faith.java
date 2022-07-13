package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * Faith class for holding the faith data.
 */
@Getter
@Setter
public class Faith implements Serializable {

    @JsonProperty(value = "id")
    private BigDecimal faithId;

    @JsonProperty(value = "nameAr")
    private String faithNameAr;

    @JsonProperty(value = "nameEn")
    private String faithNameEn;

    private BigDecimal religionId;

    private BigDecimal isArchived;
}
