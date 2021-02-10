package org.orlounge.dao;

import org.orlounge.bean.EspsBillInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EspsBillInfoDAO extends JpaRepository<EspsBillInfo , Integer> {

    @Query("from EspsBillInfo where groupId = :groupId")
    public EspsBillInfo getEspsBillInfo(Integer groupId);
}
