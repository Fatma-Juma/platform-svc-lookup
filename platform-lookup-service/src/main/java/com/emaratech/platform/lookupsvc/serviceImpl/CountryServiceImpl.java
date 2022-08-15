package com.emaratech.platform.lookupsvc.serviceImpl;

import com.emaratech.platform.lookupsvc.Service.CountryService;
import com.emaratech.platform.lookupsvc.model.Country;
import com.emaratech.platform.lookupsvc.store.DAO.CountryDAO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CountryServiceImpl implements CountryService {

    private final CountryDAO countryDAO;

    public CountryServiceImpl(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    @Override
    public List<Country> loadCountries() {
        List<Country> countryList = countryDAO.loadCountries();
        return countryList;
    }
}
