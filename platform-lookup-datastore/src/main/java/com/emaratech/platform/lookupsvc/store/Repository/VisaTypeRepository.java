package com.emaratech.platform.lookupsvc.store.Repository;

import com.emaratech.platform.lookupsvc.model.VisaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisaTypeRepository extends JpaRepository<VisaType, Long> {

}
