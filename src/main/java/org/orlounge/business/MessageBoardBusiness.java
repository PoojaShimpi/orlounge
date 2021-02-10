package org.orlounge.business;

import org.orlounge.bean.MsgBoardBean;
import org.orlounge.bean.MsgCommentBean;
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
public class MessageBoardBusiness extends AbstractBaseBusiness {

    public List<MsgBoardThread> getMessageThreads(int start, int limit) throws ORException {
            try{
                UserToken token = AppThreadLocal.getTokenLocal();
                List<MsgBoardBean> forums = getServiceFactory().getMessageBoardService().getMessages(token.getCurrentGroupId(), start, limit);
                List<MsgBoardThread> results =  new ArrayList<MsgBoardThread>();
                MsgBoardThread t;
                MsgCommentThread c;
                for(MsgBoardBean f: forums){
                    t  = new MsgBoardThread();
                    t.setMsgId(f.getMsgId());
                    t.setCreatedBy(f.getUser().getFirstName() + " "+ f.getUser().getLastName());
                    t.setCreatedUserRole(RoleNameEnum.ROLE_NAME_MAP.get(getServiceFactory().getGroupService().getGroupUserMap(f.getUserId(), token.getCurrentGroupId()).getRole()));
                    t.setCreatedById(f.getUserId());
                    t.setMsgTitle(f.getTopic());
                    t.setTopic(f.getTopic());
                    t.setImage(f.getUser().getHospitalBadgeId());
                    t.setCreatedDate(DateContent.convertDBDateToClientDate(f.getCreatedDate(), token.getTimeZone()));
                    t.setCreatedDateStr(DateContent.formatDateIntoString(t.getCreatedDate(), "MMM, dd yyyy, hh:mm a"));
                    List<MsgCommentBean>  comments = getServiceFactory().getMessageBoardService().getMsgComments(f.getMsgId());
                    if(ProcessData.isValid(comments)){
                        t.setThreadList(new ArrayList<MsgCommentThread>());
                        for(MsgCommentBean e : comments){
                            c = new MsgCommentThread();
                            c.setCreatedDate(DateContent.convertDBDateToClientDate(e.getCreatedDate(), token.getTimeZone()));
                            c.setCreatedDateStr(DateContent.formatDateIntoString(c.getCreatedDate(), "MMM, dd yyyy, hh:mm a"));
                            c.setCommentId(e.getMsgCommentId());
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

    public boolean saveMsg(String text){
        try{
            if(ProcessData.isValid(text)){
                UserToken token = AppThreadLocal.getTokenLocal();
                MsgBoardBean forum = new MsgBoardBean();
                forum.setCreatedDate(DateContent.convertClientDateIntoUTCDate(new Date(), token.getTimeZone()));
                forum.setTopic(text);
                forum.setIsActive(1);
                forum.setGroupId(token.getCurrentGroupId());
                forum.setUserId(token.getUserId());

                getServiceFactory().getMessageBoardService().saveMsg(forum);
                return true;
            }
            return false;
        } catch (Exception ex) {
            return false;
        }
    }


    public boolean saveComment(String text, Integer forumId){
        try{
            if(ProcessData.isValid(text) && ProcessData.isValid(forumId)){
                UserToken token = AppThreadLocal.getTokenLocal();

                MsgBoardBean forum = getServiceFactory().getMessageBoardService().getMsg(forumId);
                if(ProcessData.isValid( forum) && forum.getGroupId().equals(token.getCurrentGroupId())){
                    MsgCommentBean commentBean = new MsgCommentBean();
                    commentBean.setUserId(token.getUserId());
                    commentBean.setIsActive(1);
                    commentBean.setMsgId(forumId);
                    commentBean.setComment(text);
                    commentBean.setCreatedDate( DateContent.convertClientDateIntoUTCDate(new Date(), token.getTimeZone()));
                    getServiceFactory().getMessageBoardService().saveMsgComment(commentBean);
                    return true;

                }

                return false;
            }
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean deleteComment(Integer commentId) {
        try {
            if (ProcessData.isValid(commentId)) {
                UserToken token = AppThreadLocal.getTokenLocal();

                MsgCommentBean commentBean = getServiceFactory().getMessageBoardService().getMsgComment(commentId);
                if(ProcessData.isValid( commentBean)){
                    commentBean.setIsActive(0);
                    getServiceFactory().getMessageBoardService().saveMsgComment(commentBean);
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
