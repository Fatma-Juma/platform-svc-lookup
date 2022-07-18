package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * AlternateCountryCode holds the alternate country code data.
 */
@Getter
@Setter
public class AlternateCountryCode implements Serializable {

    @JsonProperty(value = "id")
    private BigDecimal alternateCountryCodeId;

    private BigDecimal countryId;

    private String alternateCountryCode;

    private BigDecimal isActive;

    /**
     * Checks the equality between two {@code alternateCountryCode} objects.
     *
     * @param alternateCountryCode {@code alternateCountryCode} object
     * @return true if both the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object alternateCountryCode) {
        if (this == alternateCountryCode) {
            return true;
        }

        if (alternateCountryCode == null || getClass() != alternateCountryCode.getClass()) {
            return false;
        }

        AlternateCountryCode that = (AlternateCountryCode) alternateCountryCode;

        return new EqualsBuilder()
                .append(alternateCountryCodeId, that.alternateCountryCodeId)
                .isEquals();
    }

    /**
     * Returns the hashcode of the {@code AlternateCountryCode} object.
     *
     * @return hashcode of the {@code AlternateCountryCode} object
     */
    @Override
    public int hashCode() {
        return Objects.hash(alternateCountryCodeId);
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
                .toString();
    }

}
