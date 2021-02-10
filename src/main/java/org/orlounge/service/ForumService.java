package org.orlounge.service;

import org.orlounge.bean.ForumBean;
import org.orlounge.bean.ForumCommentBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Satyam Soni on 9/18/2015.
 */
@Service
@Transactional
public class ForumService extends AbstractBaseService {

    public List<ForumBean> getForums(final Integer groupId, int start, int limit, String role){
        return getDaoFactory().getForumDAO().getForums(groupId, start, limit,  role);
    }

    public List<ForumCommentBean> getForumComments(final Integer forumId){
        return getDaoFactory().getForumDAO().getForumComments(forumId);
    }

    public ForumBean saveForum(ForumBean bean){
        return  getDaoFactory().getForumDAO().save(bean);
    }

    public ForumBean getForum(Integer id){
        return  getDaoFactory().getForumDAO().findById(id).get();
    }

    public ForumCommentBean getForumComment(Integer id){
        return  getDaoFactory().getForumCommentDAO().findById(id).get();
    }

    public ForumCommentBean saveForumComment(ForumCommentBean bean){
        return  getDaoFactory().getForumCommentDAO().save(bean);
    }

}
