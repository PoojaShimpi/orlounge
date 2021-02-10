package org.orlounge.dao;

import org.orlounge.bean.StateBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Satyam Soni on 9/19/2015.
 */
@Component
public interface StateDAO extends JpaRepository<StateBean, Integer> {

}
