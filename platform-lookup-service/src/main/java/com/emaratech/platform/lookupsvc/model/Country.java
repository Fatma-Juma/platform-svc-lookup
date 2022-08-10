package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.poiji.annotation.ExcelCellName;
import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COUNTRY_LK")
/**
 * Country Class for holding the country data.
 */
public class Country implements Serializable {

    @JsonProperty(value = "id")
    @ExcelCellName("COUNTRY_ID")
    @Id
    @Column(name = "COUNTRY_ID")
    private Long countryId;

    @Min(value = 1, message = "Region Id shouldn't be greater than 2 digits.")
    @ExcelCellName("REGION_ID")
    @Column(name = "REGION_ID")
    private Long regionId;

    @Size(min = 1, message = "Country name shouldn't be less than 1 character.")
    @Size(max = 100, message = "Country name shouldn't be greater than 100 characters.")
    @JsonProperty(value = "nameEn")
    @ExcelCellName("COUNTRY_NAME_EN")
    @Column(name = "COUNTRY_NAME_EN")
    private String countryNameEn;

    @Size(min = 1, message = "Country name shouldn't be less than 1 character.")
    @Size(max = 100, message = "Country name shouldn't be greater than 100 characters.")
    @JsonProperty(value = "nameAr")
    @ExcelCellName("COUNTRY_NAME_AR")
    @Column(name = "COUNTRY_NAME_AR")
    private String countryNameAr;

    @Size(min = 3, message = "Country code shouldn't be less than 3 characters.")
    @Size(max = 5, message = "Country code shouldn't be greater than 5 characters.")
    @ExcelCellName("COUNTRY_CODE")
    @Column(name = "COUNTRY_CODE")
    private String countryCode;

    @Size(min = 3, message = "Nationality shouldn't be less than 3 characters.")
    @Size(max = 50, message = "Nationality shouldn't be greater than 50 characters.")
    @ExcelCellName("NATIONALITY")
    @Column(name = "NATIONALITY")
    private String nationality;

    @Max(value = 1, message = "IsTourist shouldn't be greater than 1.")
    @ExcelCellName("IS_TOURIST")
    @Column(name = "IS_TOURIST")
    private Long isTourist;

    @Max(value = 1, message = "IsGCC shouldn't be greater than 1.")
    @ExcelCellName("IS_GCC")
    @Column(name = "IS_GCC")
    private Long isGcc;

    @Max(value = 1, message = "IsMaidAllowed shouldn't be greater than 1.")
    @ExcelCellName("IS_MAID_ALLOWED")
    @Column(name = "IS_MAID_ALLOWED")
    private Long isMaidAllowed;

    @Max(value = 1, message = "IsEntryAllowed shouldn't be greater than 1.")
    @ExcelCellName("IS_ENTRY_ALLOWED")
    @Column(name = "IS_ENTRY_ALLOWED")
    private Long isEntryAllowed;

    @Max(value = 1, message = "IsReciprocityAllowed shouldn't be greater than 1.")
    @ExcelCellName("IS_RECIPROCITY_ALLOWED")
    @Column(name = "IS_RECIPROCITY_ALLOWED")
    private Long isReciprocityAllowed;

    @Max(value = 1, message = "IsArchived shouldn't be greater than 1.")
    @ExcelCellName("IS_ARCHIVED")
    @Column(name = "IS_ARCHIVED")
    private Long isArchived;

    @Size(min = 1, message = "CountryIso shouldn't be less than 1 character.")
    @Size(max = 22, message = "CountryIso shouldn't be greater than 22 characters.")
    @ExcelCellName("COUNTRY_ISO")
    @Column(name = "COUNTRY_ISO")
    private String countryIso;

    @Max(value = 1, message = "IsNsdReviewRequired shouldn't be greater than 1.")
    @ExcelCellName("IS_NSD_REVIEW_REQUIRED")
    @Column(name = "IS_NSD_REVIEW_REQUIRED")
    private Long isNsdReviewRequired;

    @Max(value = 1, message = "IsArabNation shouldn't be greater than 1.")
    @ExcelCellName("IS_ARAB_NATION")
    @Column(name = "IS_ARAB_NATION")
    private Long isArabNation;

    @Max(value = 1, message = "IsAlternateOfCountryId shouldn't be greater than 1.")
    @ExcelCellName("IS_ALTERNATE_OF_COUNTRY_ID")
    @Column(name = "IS_ALTERNATE_OF_COUNTRY_ID")
    private Long isAlternateOfCountryId;

    /**
     *
     *
     * @param countryId
     * @param countryNameEn
     * @param countryNameAr
     * @param countryCode
     */
    public Country(Long countryId, String countryNameEn, String countryNameAr, String countryCode) {
        this.countryId = countryId;
        this.countryNameEn = countryNameEn;
        this.countryNameAr = countryNameAr;
        this.countryCode = countryCode;
    }

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
