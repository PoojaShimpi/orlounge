package org.orlounge.business;

import org.orlounge.bean.ForumBean;
import org.orlounge.bean.ForumCommentBean;
import org.orlounge.common.*;
import org.orlounge.common.util.DateContent;
import org.orlounge.common.util.ProcessData;
import org.orlounge.exception.ORException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Satyam Soni on 9/18/2015.
 */
@Component
public class ForumBusiness extends AbstractBaseBusiness {

    public List<ForumThread> getForumThreads(int start, int limit, String role) throws ORException {
            try{
                UserToken token = AppThreadLocal.getTokenLocal();
                List<ForumBean> forums = getServiceFactory().getForumService().getForums(token.getCurrentGroupId(), start, limit, role);
                List<ForumThread> results =  new ArrayList<ForumThread>();
                ForumThread t;
                CommentThread c;
                for(ForumBean f: forums){
                    t  = new ForumThread();
                    t.setForumId(f.getForumId());
                    t.setCreatedBy(f.getUser().getFirstName() + " "+ f.getUser().getLastName());
                    t.setCreatedUserRole(RoleNameEnum.ROLE_NAME_MAP.get(getServiceFactory().getGroupService().getGroupUserMap(f.getUserId(), token.getCurrentGroupId()).getRole()));
                    t.setCreatedById(f.getUserId());
                    t.setForumTitle(f.getTopic());
                    t.setTopic(f.getTopic());
                    t.setImage(f.getUser().getHospitalBadgeId());
                    t.setCreatedDate(DateContent.convertDBDateToClientDate(f.getCreatedDate(), token.getTimeZone()));
                    t.setCreatedDateStr(DateContent.formatDateIntoString(t.getCreatedDate(), "MMM, dd yyyy, hh:mm a"));
                    List<ForumCommentBean>  comments = getServiceFactory().getForumService().getForumComments(f.getForumId());
                    if(ProcessData.isValid(comments)){
                        t.setThreadList(new ArrayList<CommentThread>());
                        for(ForumCommentBean e : comments){
                            c = new CommentThread();
                            c.setCreatedDate(DateContent.convertDBDateToClientDate(e.getCreatedDate(), token.getTimeZone()));
                            c.setCreatedDateStr(DateContent.formatDateIntoString(c.getCreatedDate(), "MMM, dd yyyy, hh:mm a"));
                            c.setCommentId(e.getForumCommentId());
                            c.setComment(e.getComment());
                            c.setCreatedBy(e.getUser().getFirstName() + " " + e.getUser().getLastName());
                            c.setCreatedUserRole(RoleNameEnum.ROLE_NAME_MAP.get(getServiceFactory().getGroupService().getGroupUserMap(e.getUserId(), token.getCurrentGroupId()).getRole()));
                            c.setCreateById(e.getUserId());
                            c.setImage(e.getUser().getHospitalBadgeId());
                            t.getThreadList().add(c);
                        }
                    }
                    results.add(t);
                }
                return results;
                //List<ForumBean> forums = getServiceFactory().getForumService().getForums()
            } catch (ORException ex){
                throw ex;
            }catch (Exception ex){
                throw new ORException(ex,0);
            }

    }

    public boolean savePost(String text, String type){
        try{
            if(ProcessData.isValid(text)){
                UserToken token = AppThreadLocal.getTokenLocal();
                ForumBean forum = new ForumBean();
                forum.setCreatedDate(DateContent.convertClientDateIntoUTCDate(new Date(), token.getTimeZone()));
                forum.setTopic(text);
                forum.setIsActive(1);
                forum.setGroupId(token.getCurrentGroupId());
                forum.setUserId(token.getUserId());
                forum.setRole("all".equalsIgnoreCase(type) ? type :  token.getRole());

                getServiceFactory().getForumService().saveForum(forum);
                return true;
            }
           return false;
        }catch (Exception ex){
            return false;
        }
    }


    public boolean saveComment(String text, Integer forumId){
        try{
            if(ProcessData.isValid(text) && ProcessData.isValid(forumId)){
                UserToken token = AppThreadLocal.getTokenLocal();

                ForumBean forum = getServiceFactory().getForumService().getForum(forumId);
                if(ProcessData.isValid( forum) && forum.getGroupId().equals(token.getCurrentGroupId())){
                    ForumCommentBean commentBean = new ForumCommentBean();
                    commentBean.setUserId(token.getUserId());
                    commentBean.setIsActive(1);
                    commentBean.setForumId(forumId);
                    commentBean.setComment(text);
                    commentBean.setCreatedDate( DateContent.convertClientDateIntoUTCDate(new Date(), token.getTimeZone()));
                    getServiceFactory().getForumService().saveForumComment(commentBean);
                    return true;

                }

                return false;
            }
            return false;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean deleteComment(Integer commentId){
        try{
            if(ProcessData.isValid(commentId)){
                UserToken token = AppThreadLocal.getTokenLocal();

                ForumCommentBean commentBean = getServiceFactory().getForumService().getForumComment(commentId);
                if(ProcessData.isValid( commentBean)){
                    commentBean.setIsActive(0);
                    getServiceFactory().getForumService().saveForumComment(commentBean);
                    return true;

                }

                return false;
            }
            return false;
        }catch (Exception ex){
            return false;
        }
    }
}
