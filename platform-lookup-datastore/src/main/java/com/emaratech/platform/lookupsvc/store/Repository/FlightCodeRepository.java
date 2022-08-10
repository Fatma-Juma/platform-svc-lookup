package com.emaratech.platform.lookupsvc.store.Repository;

import com.emaratech.platform.lookupsvc.model.FlightCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightCodeRepository extends JpaRepository<FlightCode, Long> {


}
