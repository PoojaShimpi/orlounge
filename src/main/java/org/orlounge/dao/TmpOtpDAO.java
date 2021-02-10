package org.orlounge.dao;

import org.orlounge.bean.TmpOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TmpOtpDAO extends JpaRepository<TmpOtp, Long> {

    @Query("from TmpOtp where email =:email order by createTs desc ")
    public List<TmpOtp> getTmpOtpByEmail(String email) ;
}
