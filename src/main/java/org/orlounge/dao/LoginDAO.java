package org.orlounge.dao;

import org.orlounge.bean.ConfigBean;
import org.orlounge.bean.LoginBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Satyam Soni on 9/15/2015.
 */
@Component
public interface LoginDAO extends JpaRepository<LoginBean, Integer> {

    @Query("from LoginBean where userCode = :userCode")
    LoginBean getLoginDAO(String userCode);



    @Query("from ConfigBean")
    List<ConfigBean> getConfigs();
}
