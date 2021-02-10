package org.orlounge.dao;

import org.orlounge.bean.BulkMailProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BulkMailProcessDAO extends JpaRepository<BulkMailProcess, Integer> {

    @Query("from BulkMailProcess order by lastSent desc")
    public List<BulkMailProcess> getBulkMailProcess();
}
