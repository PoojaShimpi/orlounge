package org.orlounge.dao;

import org.orlounge.bean.InServiceBean;
import org.orlounge.exception.ORException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
@Component
public interface InServiceDAO extends JpaRepository<InServiceBean, Integer> {

    @Query("from InServiceBean where isActive = 1 and groupId = :groupId order by id desc")
    public List<InServiceBean> getInServices(Integer groupId);


    default InServiceBean deleteInService(InServiceBean bean) {
        try {
            bean.setIsActive(0);
            return save(bean);
        } catch (Exception ex) {
            throw new ORException(0);
        }
    }
}
