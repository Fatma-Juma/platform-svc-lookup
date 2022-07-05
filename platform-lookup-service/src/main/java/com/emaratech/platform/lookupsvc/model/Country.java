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

    @Digits(integer = 1, fraction = 0, message = "isTourist shouldn't be greater than 1.")
    private BigDecimal isTourist;

    @Digits(integer = 1, fraction = 0, message = "isGCC shouldn't be greater than 1.")
    private BigDecimal isGcc;

    @Digits(integer = 1, fraction = 0, message = "isMaidAllowed shouldn't be greater than 1.")
    private BigDecimal isMaidAllowed;

    @Digits(integer = 1, fraction = 0, message = "isEntryAllowed shouldn't be greater than 1.")
    private BigDecimal isEntryAllowed;

    @Digits(integer = 1, fraction = 0, message = "isReciprocityAllowed shouldn't be greater than 1.")
    private BigDecimal isReciprocityAllowed;

    @Digits(integer = 1, fraction = 0, message = "isArchived shouldn't be greater than 1.")
    private BigDecimal isArchived;

    @Size(min = 1, message = "countryIso shouldn't be less than 1 character.")
    @Size(max = 22, message = "countryIso shouldn't be greater than 22 characters.")
    private String countryIso;

    @Digits(integer = 1, fraction = 0, message = "isNsdReviewRequired shouldn't be greater than 1.")
    private BigDecimal isNsdReviewRequired;

    @Digits(integer = 1, fraction = 0, message = "isArabNation shouldn't be greater than 1.")
    private BigDecimal isArabNation;

    @Digits(integer = 1, fraction = 0, message = "isAlternateOfCountryId shouldn't be greater than 1.")
    private BigDecimal isAlternateOfCountryId;

}
