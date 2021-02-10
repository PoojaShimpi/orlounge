package org.orlounge.service;

import org.joda.time.DateTime;
import org.orlounge.bean.IdeaBean;
import org.orlounge.bean.VoteIdeaBean;
import org.orlounge.bean.VotingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created on 11/22/2016.
 */
@Service
@Transactional
public class VoteService  extends AbstractBaseService{

    public List<VotingBean> getAllVotingBeanList(){
        return getDaoFactory().getVoteDAO().findAll();
    }


    public VotingBean getVotingBeanById(Integer id){
        return getDaoFactory().getVoteDAO().findById(id).get();
    }

    public List<VotingBean> getActiveVoting(){
        return getDaoFactory().getVoteDAO().getActiveVoting();
    }

    public VotingBean updateCount(VotingBean bean){
        return getDaoFactory().getVoteDAO().updateCount(bean);
    }


    public VotingBean saveVoting(VotingBean bean){
        return getDaoFactory().getVoteDAO().saveAndFlush(bean);
    }


    public IdeaBean saveIdea(IdeaBean bean){
        return getDaoFactory().getIdeaDAO().save(bean);
    }
    public IdeaBean getIdea(Integer ideaId) {
        return getDaoFactory().getIdeaDAO().findById(ideaId).get();
    }
    public List<IdeaBean> getIdeasSortByVotes(int num){
        return  getDaoFactory().getIdeaDAO().getIdeasSortByVotes(num);
    }

    public boolean voteDownIdea(Integer ideaId, Integer userId){
        return  getDaoFactory().getVoteIdeaDAO().voteDownIdea(ideaId, userId);
    }

    public boolean hadVoted (Integer ideaId, Integer userId) {
        return getDaoFactory().getVoteIdeaDAO().getVotesNum(ideaId, userId)>0;
    }


    public boolean voteUpIdea(Integer ideaId, Integer userId){

        return !hadVoted(ideaId, userId) &&
                getDaoFactory().getIdeaDAO().voteUp(getIdea(ideaId)) &&
                getDaoFactory().getVoteIdeaDAO().save(new VoteIdeaBean(ideaId, userId, DateTime.now().toDate()))  != null;
    }


    }
