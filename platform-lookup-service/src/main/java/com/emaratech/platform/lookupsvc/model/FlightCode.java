package com.emaratech.platform.lookupsvc.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

import com.poiji.annotation.ExcelCellName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FLIGHT_CODE_LK")
/**
 * FlightCode class for holding the faith data.
 */
public class FlightCode implements Serializable {

    @Size(min = 1, message = "Flight Code shouldn't be less than 1 character.")
    @Size(max = 3, message = "Flight Code shouldn't be greater than 3 characters.")
    @ExcelCellName("FLIGHT_CODE")
    @Column(name = "FLIGHT_CODE")
    private String flightCode;

    @Size(min = 1, message = "Desc shouldn't be less than 1 character.")
    @Size(max = 100, message = "Desc shouldn't be greater than 100 characters.")
    @ExcelCellName("DESC_AR")
    @Column(name = "DESC_AR")
    private String descAr;

    @Size(min = 1, message = "Desc shouldn't be less than 1 character.")
    @Size(max = 100, message = "Desc shouldn't be greater than 100 characters.")
    @ExcelCellName("DESC_EN")
    @Column(name = "DESC_EN")
    private String descEn;

    @ExcelCellName("FLIGHT_CODE_ID")
    @Id
    @Column(name = "FLIGHT_CODE_ID")
    private Long flightCodeId;

    @Max(value = 1, message = "Is Archived shouldn't be greater than 1 digit.")
    @ExcelCellName("IS_ARCHIVED")
    @Column(name = "IS_ARCHIVED")
    private Integer isArchived;

    @ExcelCellName("FLIGHT_NUMBER")
    private String flightNumber;

    /**
     *
     *
     * @param flightCode
     * @param flightCodeId
     */
    public FlightCode(String flightCode, Long flightCodeId) {
        this.flightCode = flightCode;
        this.flightCodeId = flightCodeId;
    }

    /**
     * Checks the equality between two {@code flightCode} objects.
     *
     * @param flightCodeObject {@code flightCode} object
     * @return true if both the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object flightCodeObject) {
        if (this == flightCodeObject) {
            return true;
        }

        if (flightCodeObject == null || getClass() != flightCodeObject.getClass()) {
            return false;
        }

        FlightCode that = (FlightCode) flightCodeObject;

        return new EqualsBuilder()
                .append(flightCode, that.flightCode)
                .append(flightCodeId, that.flightCodeId)
                .isEquals();
    }

    /**
     * Returns the hashcode of the {@code FlightCode} object.
     *
     * @return hashcode of the {@code FlightCode} object
     */
    @Override
    public int hashCode() {
        return Objects.hash(flightCodeId, flightCode);
    }

    /**
     * Returns the {@code String} representation of {@code flightCode} object.
     *
     * @return the string representation of the object.
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", FlightCode.class.getSimpleName() + "(", ")")
                .add("flightCodeId=" + flightCodeId + "")
                .add("flightCode=" + flightCode + "")
                .toString();
    }

}
