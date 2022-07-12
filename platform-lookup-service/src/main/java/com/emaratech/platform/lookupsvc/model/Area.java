package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * Area Class for holding the area data.
 */
public class Area implements Serializable {

    @JsonProperty(value = "id")
    private BigDecimal areaId;

    @Size(min = 1, message = "Area Name shouldn't be less than 1 character.")
    @Size(max = 50, message = "Area Name shouldn't be greater than 50 characters.")
    @JsonProperty(value = "nameAr")
    private String areaNameAr;

    @Size(min = 1, message = "Area Name shouldn't be less than 1 character.")
    @Size(max = 50, message = "Area Name shouldn't be greater than 50 characters.")
    @JsonProperty(value = "nameEn")
    private String areaNameEn;

    @Digits(integer = 5, fraction = 0, message = "City Id shouldn't be greater than 5 digits.")
    private BigDecimal cityId;

    @Digits(integer = 1, fraction = 0, message = "isArchived shouldn't be greater than 1.")
    private BigDecimal isArchived;

}
