package org.orlounge.dao;

import org.orlounge.bean.LastAccessGroupBean;
import org.orlounge.common.util.ProcessData;
import org.orlounge.exception.ORException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by Satyam Soni on 9/19/2015.
 */
public interface LastAccessGroupDAO extends JpaRepository<LastAccessGroupBean, Integer> {

    @Query("from LastAccessGroupBean where userId = :id order by id desc")
    List<LastAccessGroupBean> getLastAccessGroupIdByUser(@Param("id") Integer id);


    default Integer updateLastAccessGroupIdByUser(Integer userId, Integer groupId) {

        try {
            Optional<LastAccessGroupBean> b = getLastAccessGroupIdByUser(userId).stream().filter(e->e.getGroupId().equals(groupId)).findFirst();

            if (b.isPresent() && ProcessData.isValid(b.get())) {
                b.get().setGroupId(groupId);
            } else {
                b = Optional.of(new LastAccessGroupBean());
                b.get().setGroupId(groupId);
                b.get().setUserId(userId);
            }

            saveAndFlush(b.get());

            return b.get().getGroupId();

        } catch (Exception ex) {
            throw new ORException(0);
        }
    }


}
