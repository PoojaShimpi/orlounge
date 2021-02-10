package org.orlounge.business;

import org.orlounge.bean.IdeaBean;
import org.orlounge.bean.VotingBean;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.UserToken;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created on 11/22/2016.
 */
@Component
public class VoteBusiness extends AbstractBaseBusiness{



    public List<VotingBean> getAllVotingBeanList(){
        return getServiceFactory().getVoteService().getAllVotingBeanList();
    }


    public VotingBean getVotingBeanById(Integer id){
        return getServiceFactory().getVoteService().getVotingBeanById(id);
    }

    public List<VotingBean> getActiveVoting(){
        return getServiceFactory().getVoteService().getActiveVoting();
    }

    public VotingBean updateCount(VotingBean bean){
        return getServiceFactory().getVoteService().updateCount(bean);
    }


    public VotingBean saveVoting(VotingBean bean){
        return getServiceFactory().getVoteService().saveVoting(bean);
    }

    public IdeaBean saveIdea(IdeaBean bean){
        if(bean.getIdeaId() == null){
            UserToken token = AppThreadLocal.getTokenLocal();
            bean.setCreatedDate(new Date());
            bean.setCreatedId(token.getUserId());
        }
        return getServiceFactory().getVoteService().saveIdea(bean);
    }

    public IdeaBean getIdea(Integer ideaId) {
        return getServiceFactory().getVoteService().getIdea(ideaId);
    }

    public List<IdeaBean> getIdeasSortByVotes(int num){
        return  getServiceFactory().getVoteService().getIdeasSortByVotes(num);
    }

    public boolean voteUpIdea(Integer ideaId){
        UserToken token = AppThreadLocal.getTokenLocal();
        return  getServiceFactory().getVoteService().voteUpIdea(ideaId, token.getUserId() );

    }
    public boolean voteDownIdea(Integer ideaId){
        UserToken token = AppThreadLocal.getTokenLocal();
        return  getServiceFactory().getVoteService().voteDownIdea(ideaId, token.getUserId() );

    }


    public boolean hadVoted (Integer ideaId) {
        UserToken token = AppThreadLocal.getTokenLocal();
        return  getServiceFactory().getVoteService().hadVoted(ideaId, token.getUserId() );
    }
}
