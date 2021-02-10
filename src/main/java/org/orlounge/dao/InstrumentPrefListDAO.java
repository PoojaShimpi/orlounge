package org.orlounge.dao;

import org.orlounge.bean.InstrumentPrefListBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Satyam Soni on 1/1/2016.
 */
@Component
public interface InstrumentPrefListDAO extends JpaRepository<InstrumentPrefListBean, Integer> {


}
