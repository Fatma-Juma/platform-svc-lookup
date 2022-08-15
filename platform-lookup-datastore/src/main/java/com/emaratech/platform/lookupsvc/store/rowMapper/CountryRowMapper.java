package com.emaratech.platform.lookupsvc.store.rowMapper;

import com.emaratech.platform.lookupsvc.model.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryRowMapper implements RowMapper<Country> {
    @Override
    public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
        Country country = new Country();
        country.setCountryId(rs.getLong("countryId"));
        country.setRegionId(rs.getLong("regionId"));
        country.setCountryNameEn(rs.getString("countryNameEn"));
        country.setCountryNameAr(rs.getString("countryNameAr"));
        country.setCountryCode(rs.getString("countryCode"));
        country.setNationality(rs.getString("nationality"));
        country.setIsTourist(rs.getLong("isTourist"));
        country.setIsGcc(rs.getLong("isGcc"));
        country.setIsMaidAllowed(rs.getLong("isMaidAllowed"));
        country.setIsEntryAllowed(rs.getLong("isEntryAllowed"));
        country.setIsReciprocityAllowed(rs.getLong("isReciprocityAllowed"));
        country.setIsArchived(rs.getLong("isArchived"));
        country.setCountryIso(rs.getString("countryIso"));
        country.setIsNsdReviewRequired(rs.getLong("isNsdReviewRequired"));
        country.setIsArabNation(rs.getLong("isArabNation"));
        country.setIsAlternateOfCountryId(rs.getLong("isAlternateOfCountryId"));


        return country;
    }
}
