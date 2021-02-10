package org.orlounge.dao;

import org.orlounge.bean.TagsBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDAO extends JpaRepository<TagsBean, Integer> {
}
