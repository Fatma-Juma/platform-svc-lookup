package com.emaratech.platform.lookupsvc.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
/**
 * City Class for holding the city data.
 */
public class City {

    private BigDecimal cityId;
    private BigDecimal countryId;

    @Size(min = 1, message = "City Name shouldn't be less than 1 character.")
    @Size(max = 30, message = "City Name shouldn't be greater than 30 characters.")
    private String cityNameEn;

    @Size(min = 1, message = "City Name shouldn't be less than 1 character.")
    @Size(max = 30, message = "City Name shouldn't be greater than 30 characters.")
    private String cityNameAr;

    private BigDecimal emirateId;

    @Digits(integer = 1, fraction = 0, message = "isArchived shouldn't be greater than 1.")
    private BigDecimal isArchived;



}
