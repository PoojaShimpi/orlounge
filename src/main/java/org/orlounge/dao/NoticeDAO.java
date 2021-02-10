package org.orlounge.dao;

import org.orlounge.bean.NoticeBean;
import org.orlounge.exception.ORException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
@Component
public interface NoticeDAO extends JpaRepository<NoticeBean, Integer> {

    @Query("from NoticeBean where isActive = 1 and groupId = :id order by id desc")
    public List<NoticeBean> getNotices(@Param("id") Integer id);


    default NoticeBean deleteNotice(NoticeBean bean) {
        try {
            bean.setIsActive(0);
            return save(bean);
        } catch (Exception ex) {
            throw new ORException(0);
        }
    }

    default NoticeBean deleteNotice(Integer beanId) {
        try {
            return deleteNotice(getOne(beanId));
        } catch (Exception ex) {
            throw new ORException(0);
        }
    }
}
