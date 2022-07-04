package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * Country Class for holding the country data.
 */
public class Country implements Serializable {

    private BigDecimal countryId;

    @Digits(integer = 2, fraction = 0, message = "Region shouldn't be greater than 2 digits.")
    private BigDecimal regionId;

    @Size(min = 1, message = "Country Name shouldn't less than 1 character.")
    @Size(max = 100, message = "Country Name shouldn't be greater than 100 characters.")
    private String countryNameEn;

    @Size(min = 1, message = "Country Name shouldn't less than 1 character.")
    @Size(max = 100, message = "Country Name shouldn't be greater than 100 characters.")
    private String countryNameAr;

    @Size(min = 3, message = "Country Code shouldn't be less than 3 characters.")
    @Size(max = 5, message = "Country Code shouldn't be greater than 5 characters.")
    private String countryCode;

    @Size(min = 3, message = "nationality shouldn't be less than 3 characters.")
    @Size(max = 50, message = "nationality shouldn't be greater than 50 characters.")
    private String nationality;

    @Max(1)
    private BigDecimal isTourist;

    private BigDecimal isGcc;

    private BigDecimal isMaidAllowed;

    private BigDecimal isEntryAllowed;

    private BigDecimal isReciprocityAllowed;

    private BigDecimal isArchived;

    @Size(min = 1, message = "countryIso shouldn't be less than 1 character.")
    @Size(max = 22, message = "countryIso shouldn't be greater than 22 characters.")
    private String countryIso;

    private BigDecimal isNsdReviewRequired;

    private BigDecimal isArabNation;

    private BigDecimal isAlternateOfCountryId;

}
