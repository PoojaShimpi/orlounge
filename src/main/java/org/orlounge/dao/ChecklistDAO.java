package org.orlounge.dao;

import org.orlounge.bean.ChecklistBean;
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
public interface ChecklistDAO extends JpaRepository<ChecklistBean, Integer> {

    @Query("from ChecklistBean where groupId = :id order by id desc")
    public List<ChecklistBean> getChecklists(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("delete from ChecklistBean where id= :id")
    public int deleteChecklist(@Param("id") Integer id);
    
//    default ChecklistBean deleteChecklist(ChecklistBean bean) {
//        try {
//            bean.setIsActive(0);
//            return saveAndFlush(bean);
//        } catch (Exception ex) {
//            throw new ORException(0);
//        }
//    }
}
