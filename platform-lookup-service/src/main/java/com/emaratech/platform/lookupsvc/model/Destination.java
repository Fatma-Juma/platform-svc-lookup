package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
 * Destination class for holding the destination data.
 */
public class Destination implements Serializable {
    @JsonProperty(value = "id")
    private BigDecimal destinationId;

    @Size(min = 1, message = "Destination name shouldn't be less than 1 character.")
    @Size(max = 200, message = "Destination name shouldn't be greater than 200 characters.")
    @JsonProperty(value = "nameAr")
    private String destinationNameAr;

    @Size(min = 1, message = "Destination name shouldn't be less than 1 character.")
    @Size(max = 200, message = "Destination name shouldn't be greater than 200 characters.")
    @NotBlank(message = "Destination Name should not be empty.")
    @JsonProperty(value = "nameEn")
    private String destinationNameEn;

    @Digits(integer = 4, fraction = 0, message = "Country Id shouldn't be greater than 4 digits.")
    private BigDecimal countryId;

    @Size(min = 1, message = "Destination code shouldn't be less than 1 character.")
    @Size(max = 4, message = "Destination code shouldn't be greater than 4 characters.")
    private String destinationCode;

    @Digits(integer = 1, fraction = 0, message = "IsArchived shouldn't be greater than 1.")
    private BigDecimal isArchived;

    /**
     *
     *
     * @param destinationId
     * @param destinationNameAr
     * @param destinationNameEn
     * @param destinationCode
     */
    public Destination(BigDecimal destinationId, String destinationNameAr, String destinationNameEn, String destinationCode) {
        this.destinationId = destinationId;
        this.destinationNameAr = destinationNameAr;
        this.destinationNameEn = destinationNameEn;
        this.destinationCode = destinationCode;
    }

    /**
     * Checks the equality between two {@code destination} objects.
     *
     * @param destination {@code visaType} object
     * @return true if both the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object destination) {
        if (this == destination) {
            return true;
        }

        if (destination == null || getClass() != destination.getClass()) {
            return false;
        }

        Destination that = (Destination) destination;

        return new EqualsBuilder()
                .append(destinationId, that.destinationId)
                .append(destinationNameAr, that.destinationNameAr)
                .append(destinationNameEn, that.destinationNameEn)
                .append(destinationCode, that.destinationCode)
                .isEquals();
    }

    /**
     * Returns the hashcode of the {@code Destination} object.
     *
     * @return hashcode of the {@code Destination} object
     */
    @Override
    public int hashCode() {
        return Objects.hash(destinationId, destinationCode);
    }

    /**
     * Returns the {@code String} representation of {@code destination} object.
     *
     * @return the string representation of the object.
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", Destination.class.getSimpleName() + "(", ")")
                .add("destinationId=" + destinationId + "")
                .add("destinationNameAr=" + destinationNameAr + "")
                .add("destinationNameEn=" + destinationNameEn + "")
                .add("destinationCode=" + destinationCode + "")
                .toString();
    }

    public enum Name {
        UNKNOWN_9999(9999), MUSCAT_OMAN(266);

        private long id;

        Name(long identifier) {
            this.id = identifier;
        }

        public long getId() {
            return id;
        }
    }

}
