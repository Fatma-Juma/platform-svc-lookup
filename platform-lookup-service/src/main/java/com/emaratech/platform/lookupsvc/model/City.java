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
 * City Class for holding the city data.
 */
public class City implements Serializable{
        /**
         *
         *
         * @param cityId
         * @param cityNameEn
         * @param cityNameAr
         */
        public City(BigDecimal cityId, String cityNameEn, String cityNameAr) {
            this.cityId = cityId;
            this.cityNameEn = cityNameEn;
            this.cityNameAr = cityNameAr;
        }
    @JsonProperty(value = "id")
    private BigDecimal cityId;

    @Digits(integer = 4, fraction = 0, message = "Country Id shouldn't be greater than 4 digits.")
    private BigDecimal countryId;

    @Size(min = 1, message = "City name shouldn't be less than 1 character.")
    @Size(max = 30, message = "City name shouldn't be greater than 30 characters.")
    @JsonProperty(value = "nameEn")
    private String cityNameEn;

    @Size(min = 1, message = "City name shouldn't be less than 1 character.")
    @Size(max = 30, message = "City name shouldn't be greater than 30 characters.")
    @JsonProperty(value = "nameAr")
    private String cityNameAr;

    @Digits(integer = 1, fraction = 0, message = "Emirate Id shouldn't be greater than 1.")
    private BigDecimal emirateId;

    @Digits(integer = 1, fraction = 0, message = "IsArchived shouldn't be greater than 1.")
    private BigDecimal isArchived;

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
