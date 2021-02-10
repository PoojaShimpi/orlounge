package org.orlounge.dao;

import org.orlounge.bean.ProjectDocBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Satyam Soni on 1/2/2016.
 */
@Repository
public interface ProjectDocDAO extends JpaRepository<ProjectDocBean, Integer> {



}
