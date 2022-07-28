package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.poiji.annotation.ExcelCellName;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Destination implements Serializable {
    @JsonProperty(value = "id")
    @ExcelCellName("DESTINATION_ID")
    private Long destinationId;

    @Size(min = 1, message = "Destination name shouldn't be less than 1 character.")
    @Size(max = 200, message = "Destination name shouldn't be greater than 200 characters.")
    @JsonProperty(value = "nameAr")
    @ExcelCellName("DESC_AR")
    private String destinationNameAr;

    @Size(min = 1, message = "Destination name shouldn't be less than 1 character.")
    @Size(max = 200, message = "Destination name shouldn't be greater than 200 characters.")
    @NotBlank(message = "Destination Name should not be empty.")
    @JsonProperty(value = "nameEn")
    @ExcelCellName("DESC_AR")
    private String destinationNameEn;

    @Min(value = 100, message = "Country Id shouldn't be less than 3 digits.")
    @ExcelCellName("COUNTRY_ID")
    private Long countryId;

    @Size(min = 1, message = "Destination code shouldn't be less than 1 character.")
    @Size(max = 4, message = "Destination code shouldn't be greater than 4 characters.")
    @ExcelCellName("DESTINATION_CODE")
    private String destinationCode;

    @Max(value = 1, message = "IsArchived shouldn't be greater than 1.")
    @ExcelCellName("IS_ARCHIVED")
    private Long isArchived;

    /**
     *
     *
     * @param destinationId
     * @param destinationNameAr
     * @param destinationNameEn
     * @param destinationCode
     */
    public Destination(Long destinationId, String destinationNameAr, String destinationNameEn, String destinationCode) {
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
