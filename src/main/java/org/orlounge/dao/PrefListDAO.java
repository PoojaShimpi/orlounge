package org.orlounge.dao;

import org.orlounge.bean.PrefListBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Satyam Soni on 1/1/2016.
 */
@Component
public interface PrefListDAO extends JpaRepository<PrefListBean, Integer> {

    @Query(" from PrefListBean where groupId = :id and isActive = 1")
    public List<PrefListBean> getAllPrefs(Integer id);


}
