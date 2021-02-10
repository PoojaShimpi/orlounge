package org.orlounge.service;

import org.orlounge.bean.MsgBoardBean;
import org.orlounge.bean.MsgCommentBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Satyam Soni on 9/18/2015.
 */
@Service
@Transactional
public class MessageBoardService extends AbstractBaseService {

    public List<MsgBoardBean> getMessages(final Integer groupId, int start, int limit) {
        return getDaoFactory().getMsgBoardDAO().getMessages(groupId, start, limit);
    }

    public List<MsgCommentBean> getMsgComments(final Integer forumId) {
        return getDaoFactory().getMsgBoardDAO().getMsgComments(forumId);
    }

    public MsgBoardBean saveMsg(MsgBoardBean bean) {
        return getDaoFactory().getMsgBoardDAO().save(bean);
    }

    public MsgBoardBean getMsg(Integer id) {
        return getDaoFactory().getMsgBoardDAO().findById(id).get();
    }

    public MsgCommentBean getMsgComment(Integer id) {
        return getDaoFactory().getMsgBoardCommentDAO().findById(id).get();
    }

    public MsgCommentBean saveMsgComment(MsgCommentBean bean) {
        return getDaoFactory().getMsgBoardCommentDAO().save(bean);
    }

}
