package org.orlounge.dao;

import org.orlounge.bean.ForumCommentBean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Satyam Soni on 9/18/2015.
 */
@Component
public interface ForumCommentDAO extends PagingAndSortingRepository<ForumCommentBean, Integer> {


    @Query(" from ForumCommentBean where forumId= :forumId and isActive = 1 order by  createdDate asc")
    public List<ForumCommentBean> getForumComments(@Param("forumId") Integer forumId);

}

