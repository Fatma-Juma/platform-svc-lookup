package com.emaratech.platform.lookupsvc.store.Repository;

import com.emaratech.platform.lookupsvc.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    @Override List<City> findAll();
}
