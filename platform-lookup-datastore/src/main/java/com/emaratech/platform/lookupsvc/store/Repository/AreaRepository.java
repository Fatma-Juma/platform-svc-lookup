package com.emaratech.platform.lookupsvc.store.Repository;

import com.emaratech.platform.lookupsvc.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {

}
