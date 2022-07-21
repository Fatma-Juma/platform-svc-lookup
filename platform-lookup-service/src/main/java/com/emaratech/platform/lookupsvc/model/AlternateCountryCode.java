package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

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
 * AlternateCountryCode holds the alternate country code data.
 */
public class AlternateCountryCode implements Serializable {

    /**
     *
     *
     * @param alternateCountryCodeId
     * @param alternateCountryCode
     */
    public AlternateCountryCode(BigDecimal alternateCountryCodeId, String alternateCountryCode) {
        this.alternateCountryCodeId = alternateCountryCodeId;
        this.alternateCountryCode = alternateCountryCode;
    }

    @JsonProperty(value = "id")
    private BigDecimal alternateCountryCodeId;

    private BigDecimal countryId;

    private String alternateCountryCode;

    private BigDecimal isActive;

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
