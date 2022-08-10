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
@Table(name = "CITY_LK")
/**
 * City Class for holding the city data.
 */
public class City implements Serializable {
    @JsonProperty(value = "id")
    @ExcelCellName("CITY_ID")
    @Id
    @Column(name = "CITY_ID")
    private Long cityId;

    @Min(value = 100, message = "Country Id shouldn't be greater than 4 digits.")
    @ExcelCellName("COUNTRY_ID")
    @Column(name = "COUNTRY_ID")
    private Long countryId;

    @Size(min = 1, message = "City name shouldn't be less than 1 character.")
    @Size(max = 30, message = "City name shouldn't be greater than 30 characters.")
    @JsonProperty(value = "nameEn")
    @ExcelCellName("CITY_NAME_EN")
    @Column(name = "CITY_NAME_EN")
    private String cityNameEn;

    @Size(min = 1, message = "City name shouldn't be less than 1 character.")
    @Size(max = 30, message = "City name shouldn't be greater than 30 characters.")
    @JsonProperty(value = "nameAr")
    @ExcelCellName("CITY_NAME_AR")
    @Column(name = "CITY_NAME_AR")
    private String cityNameAr;

    @Min(value = 1, message = "Emirate Id shouldn't be less than 1.")
    @ExcelCellName("EMIRATE_ID")
    @Column(name = "EMIRATE_ID")
    private Long emirateId;

    @Max(value = 1, message = "IsArchived shouldn't be greater than 1.")
    @ExcelCellName("IS_ARCHIVED")
    @Column(name = "IS_ARCHIVED")
    private Long isArchived;

    /**
     *
     *
     * @param cityId
     * @param cityNameEn
     * @param cityNameAr
     */
    public City(Long cityId, String cityNameEn, String cityNameAr) {
        this.cityId = cityId;
        this.cityNameEn = cityNameEn;
        this.cityNameAr = cityNameAr;
    }

    /**
     * Checks the equality between two {@code city} objects.
     *
     * @param city {@code city} object
     * @return true if both the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object city) {
        if (this == city) {
            return true;
        }

        if (city == null || getClass() != city.getClass()) {
            return false;
        }

        City that = (City) city;

        return new EqualsBuilder()
                .append(cityId, that.cityId)
                .append(cityNameEn, that.cityNameEn)
                .append(cityNameAr, that.cityNameAr)
                .isEquals();
    }

    /**
     * Returns the hashcode of the {@code City} object.
     *
     * @return hashcode of the {@code City} object
     */
    @Override
    public int hashCode() {
        return Objects.hash(cityId, cityNameEn);
    }

    /**
     * Returns the {@code String} representation of {@code city} object.
     *
     * @return the string representation of the object.
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", City.class.getSimpleName() + "(", ")")
                .add("cityId=" + cityId + "")
                .add("cityNameEn=" + cityNameEn + "")
                .add("cityNameAr=" + cityNameAr + "")
                .toString();
    }

}
