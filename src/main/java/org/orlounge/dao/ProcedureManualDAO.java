package org.orlounge.dao;

import org.orlounge.bean.ProcedureManualBean;
import org.orlounge.exception.ORException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
@Repository
public interface ProcedureManualDAO extends JpaRepository<ProcedureManualBean, Integer> {

    @Query("from ProcedureManualBean where isActive = 1 and groupId = :id order by id desc")
    public List<ProcedureManualBean> getProcedureManuals(Integer id);


    default ProcedureManualBean deleteProcedureManual(ProcedureManualBean bean) {
        try {
            bean.setIsActive(0);
            return saveAndFlush(bean);
        } catch (Exception ex) {
            throw new ORException(0);
        }
    }
}
