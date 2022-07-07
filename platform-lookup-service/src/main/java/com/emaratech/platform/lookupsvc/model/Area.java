package com.emaratech.platform.lookupsvc.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
/**
 * Area Class for holding the area data.
 */
public class Area implements Serializable {
    private BigDecimal areaId;

    @Size(min = 1, message = "Area Name shouldn't be less than 1 character.")
    @Size(max = 50, message = "Area Name shouldn't be greater than 50 characters.")
    private String areaAr;

    @Size(min = 1, message = "Area Name shouldn't be less than 1 character.")
    @Size(max = 50, message = "Area Name shouldn't be greater than 50 characters.")
    private String areaEn;

    @Digits(integer = 5, fraction = 0, message = "CityId should be between 1 and 5 characters")
    private BigDecimal cityId;

    @Digits(integer = 1, fraction = 0, message = "isArchived shouldn't be greater than 1.")
    private BigDecimal isArchived;


}
