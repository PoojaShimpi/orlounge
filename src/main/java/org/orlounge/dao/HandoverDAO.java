package org.orlounge.dao;

import org.orlounge.bean.HandoverBean;
import org.orlounge.exception.ORException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
public interface HandoverDAO extends JpaRepository<HandoverBean, Integer> {

    @Query("from HandoverBean where groupId = :id order by id desc")
    public List<HandoverBean> getAllHandOvers(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("delete from HandoverBean where id= :id")
    public int deleteHandover(@Param("id") Integer id);

//    default HandoverBean deleteHandover(HandoverBean bean) {
//        try {
//            bean.setIsActive(0);
//            return saveAndFlush(bean);
//        } catch (Exception ex) {
//            throw new ORException(0);
//        }
//    }
}
