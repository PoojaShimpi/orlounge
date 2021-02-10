package org.orlounge.dao;

import org.orlounge.bean.VotingBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created on 11/22/2016.
 */
@Component
public interface VoteDAO extends JpaRepository<VotingBean, Integer> {



    @Query("from VotingBean where active = 1")
    public List<VotingBean> getActiveVoting();

    @Modifying
    default VotingBean updateCount(VotingBean bean){
        VotingBean votingBean = getOne(bean.getId());
        votingBean.setVoteNumber(votingBean.getVoteNumber()+1);
        return saveAndFlush(votingBean);
    }

}
