package org.orlounge.dao;

import org.orlounge.bean.CallScheduleBean;
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
public interface CallScheduleDAO extends JpaRepository<CallScheduleBean, Integer> {

    @Query("from CallScheduleBean where groupId = :id order by id desc ")
    public List<CallScheduleBean> getCallSchedules(@Param("id") Integer id);
    
    
    @Transactional
    @Modifying
    @Query("delete from CallScheduleBean where id= :id")
    public int deleteCallSchedule(@Param("id") Integer id);
    
    
//    default CallScheduleBean deleteCallSchedule(CallScheduleBean bean) {
//        try {
////            bean.setIsActive(0);
//            return save(bean);
//        } catch (Exception ex) {
//            throw new ORException(0);
//        }
//    }
}
