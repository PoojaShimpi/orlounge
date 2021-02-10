package org.orlounge.dao;

import org.orlounge.bean.EventLogBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Satyam Soni on 9/19/2015.
 */
public interface EventLogDAO extends JpaRepository<EventLogBean, Integer> {


    @Query("select e.id as id, e.isActive as isActive, e.eventName as eventName," +
            " e.eventMsg as eventMsg, e.ip as ip, e.userId as userId, e.dts as dts from EventLogBean e" +
            " inner join UserGroupMap ug on ug.userId = e.userId " +
            " inner join GroupBean g on  g.id = ug.groupId and g.id = :id ")
    public List<EventLogBean> getEventLogsByGroup(@Param("id") Integer groupId);

    List<EventLogBean> findAllByUserId(Integer userId);

    List<EventLogBean> findAllByEventName(String eventName);


}
