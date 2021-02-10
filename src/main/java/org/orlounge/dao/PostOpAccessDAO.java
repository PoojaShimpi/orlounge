package org.orlounge.dao;

import org.orlounge.bean.PostOpAccessBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Satyam Soni on 9/22/2015.
 */
@Component
public interface PostOpAccessDAO extends JpaRepository<PostOpAccessBean, Integer> {


}
