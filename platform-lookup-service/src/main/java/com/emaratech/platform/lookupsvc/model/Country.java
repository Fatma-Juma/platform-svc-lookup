package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Country Class for holding the country data.
 */
public class Country implements Serializable {

    /**
     *
     *
     * @param countryId
     * @param countryNameEn
     * @param countryNameAr
     * @param countryCode
     */
    public Country(BigDecimal countryId, String countryNameEn, String countryNameAr, String countryCode) {
        this.countryId = countryId;
        this.countryNameEn = countryNameEn;
        this.countryNameAr = countryNameAr;
        this.countryCode = countryCode;
    }

    @JsonProperty(value = "id")
    private BigDecimal countryId;

    @Digits(integer = 2, fraction = 0, message = "Region shouldn't be greater than 2 digits.")
    private BigDecimal regionId;

    @Size(min = 1, message = "Country name shouldn't be less than 1 character.")
    @Size(max = 100, message = "Country name shouldn't be greater than 100 characters.")
    @JsonProperty(value = "nameEn")
    private String countryNameEn;

    @Size(min = 1, message = "Country name shouldn't be less than 1 character.")
    @Size(max = 100, message = "Country name shouldn't be greater than 100 characters.")
    @JsonProperty(value = "nameAr")
    private String countryNameAr;

    @Size(min = 3, message = "Country code shouldn't be less than 3 characters.")
    @Size(max = 5, message = "Country code shouldn't be greater than 5 characters.")
    private String countryCode;

    @Size(min = 3, message = "Nationality shouldn't be less than 3 characters.")
    @Size(max = 50, message = "Nationality shouldn't be greater than 50 characters.")
    private String nationality;

    @Digits(integer = 1, fraction = 0, message = "IsTourist shouldn't be greater than 1.")
    private BigDecimal isTourist;

    @Digits(integer = 1, fraction = 0, message = "IsGCC shouldn't be greater than 1.")
    private BigDecimal isGcc;

    @Digits(integer = 1, fraction = 0, message = "IsMaidAllowed shouldn't be greater than 1.")
    private BigDecimal isMaidAllowed;

    @Digits(integer = 1, fraction = 0, message = "IsEntryAllowed shouldn't be greater than 1.")
    private BigDecimal isEntryAllowed;

    @Digits(integer = 1, fraction = 0, message = "IsReciprocityAllowed shouldn't be greater than 1.")
    private BigDecimal isReciprocityAllowed;

    @Digits(integer = 1, fraction = 0, message = "IsArchived shouldn't be greater than 1.")
    private BigDecimal isArchived;

    @Size(min = 1, message = "CountryIso shouldn't be less than 1 character.")
    @Size(max = 22, message = "CountryIso shouldn't be greater than 22 characters.")
    private String countryIso;

    @Digits(integer = 1, fraction = 0, message = "IsNsdReviewRequired shouldn't be greater than 1.")
    private BigDecimal isNsdReviewRequired;

    @Digits(integer = 1, fraction = 0, message = "IsArabNation shouldn't be greater than 1.")
    private BigDecimal isArabNation;

    @Digits(integer = 1, fraction = 0, message = "IsAlternateOfCountryId shouldn't be greater than 1.")
    private BigDecimal isAlternateOfCountryId;


    /**
     * Checks the equality between two {@code country} objects.
     *
     * @param country {@code country} object
     * @return true if both the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object country) {
        if (this == country) {
            return true;
        }

        if (country == null || getClass() != country.getClass()) {
            return false;
        }

        Country that = (Country) country;

        return new EqualsBuilder()
                .append(countryId, that.countryId)
                .append(countryNameEn, that.countryNameEn)
                .append(countryNameAr, that.countryNameAr)
                .append(countryCode, that.countryCode)
                .isEquals();
    }

    /**
     * Returns the hashcode of the {@code Country} object.
     *
     * @return hashcode of the {@code Country} object
     */
    @Override
    public int hashCode() {
        return Objects.hash(countryId, countryCode);
    }

    /**
     * Returns the {@code String} representation of {@code country} object.
     *
     * @return the string representation of the object.
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", Country.class.getSimpleName() + "(", ")")
                .add("countryId=" + countryId + "")
                .add("countryNameEn=" + countryNameEn + "")
                .add("countryNameAr=" + countryNameAr + "")
                .add("countryCode=" + countryCode + "")
                .toString();
    }
}
