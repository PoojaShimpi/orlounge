package org.orlounge.dao;

import org.orlounge.bean.VoteIdeaBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

/**
 * Created on 11/22/2016.
 */
@Component
public interface VoteIdeaDAO extends JpaRepository<VoteIdeaBean, Integer> {


    @Query(value = "select count(v) from VoteIdeaBean v where ideaId = :ideaId and userId = :userId")
    int getVotesNum(Integer ideaId, Integer userId);

    @Query("delete from VoteIdeaBean where ideaId = :ideaId and userId = :userId")
    @Modifying
     boolean voteDownIdea(Integer ideaId, Integer userId);



}
