package com.emaratech.platform.lookupsvc.store.Repository;

import com.emaratech.platform.lookupsvc.model.Emirate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmirateRepository extends JpaRepository<Emirate, Long> {

}
