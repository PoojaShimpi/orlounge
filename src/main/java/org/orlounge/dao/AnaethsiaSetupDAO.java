package org.orlounge.dao;

import org.orlounge.bean.AnaethesiaSetupBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Satyam Soni on 1/1/2016.
 */
@Component
public interface AnaethsiaSetupDAO extends JpaRepository<AnaethesiaSetupBean, Integer> {

    @Query(" from AnaethesiaSetupBean where groupId = :id and active = 1")
    public List<AnaethesiaSetupBean> getAllSetups(@Param("id") Integer groupId);
    

}
