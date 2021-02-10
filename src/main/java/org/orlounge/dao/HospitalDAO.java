package org.orlounge.dao;

import org.orlounge.bean.HospitalBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Satyam Soni on 9/19/2015.
 */
@Component
public interface HospitalDAO extends JpaRepository<HospitalBean, Integer> {

    @Query("from HospitalBean where isActive = 1")
    public List<HospitalBean> getAllHospital();


    @Query("from HospitalBean where isActive = 1 and hospitalId = :id")
    public HospitalBean getActiveHospital(@Param("id") Integer id);




    @Query("from HospitalBean h join GroupBean g on h.hospitalId = g.hospitalId and g.groupId = :groupId")
    HospitalBean getHospitalBeanByGroupId(Integer groupId);
}
