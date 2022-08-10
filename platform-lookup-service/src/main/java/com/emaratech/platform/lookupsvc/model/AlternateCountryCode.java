package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

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
/**
 * AlternateCountryCode holds the alternate country code data.
 */
public class AlternateCountryCode implements Serializable {

    @JsonProperty(value = "id")
    @ExcelCellName("ALTERNATE_COUNTRY_CODE_ID")
    private Long alternateCountryCodeId;

    @ExcelCellName("COUNTRY_ID")
    private Long countryId;

    @ExcelCellName("ALTERNATE_COUNTRY_CODE")
    private String alternateCountryCode;

    @ExcelCellName("IS_ACTIVE")
    private Long isActive;

    /**
     *
     *
     * @param alternateCountryCodeId
     * @param alternateCountryCode
     */
    public AlternateCountryCode(Long alternateCountryCodeId, String alternateCountryCode) {
        this.alternateCountryCodeId = alternateCountryCodeId;
        this.alternateCountryCode = alternateCountryCode;
    }

    /**
     * Checks the equality between two {@code alternateCountryCode} objects.
     *
     * @param alternateCountryCodeObject {@code alternateCountryCode} object
     * @return true if both the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object alternateCountryCodeObject) {
        if (this == alternateCountryCodeObject) {
            return true;
        }

        if (alternateCountryCodeObject == null || getClass() != alternateCountryCodeObject.getClass()) {
            return false;
        }

        AlternateCountryCode that = (AlternateCountryCode) alternateCountryCodeObject;

        return new EqualsBuilder()
                .append(alternateCountryCodeId, that.alternateCountryCodeId)
                .append(alternateCountryCode, that.alternateCountryCode)
                .isEquals();
    }

    /**
     * Returns the hashcode of the {@code AlternateCountryCode} object.
     *
     * @return hashcode of the {@code AlternateCountryCode} object
     */
    @Override
    public int hashCode() {
        return Objects.hash(alternateCountryCodeId, alternateCountryCode);
    }

    /**
     * Returns the {@code String} representation of {@code visaType} object.
     *
     * @return the string representation of the object.
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", AlternateCountryCode.class.getSimpleName() + "(", ")")
                .add("alternateCountryCodeId=" + alternateCountryCodeId + "")
                .add("alternateCountryCode=" + alternateCountryCode + "")
                .toString();
    }

}
