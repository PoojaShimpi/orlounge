package org.orlounge.dao;

import org.orlounge.bean.MsgCommentBean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Satyam Soni on 9/18/2015.
 */
@Component
public interface MsgBoardCommentDAO extends PagingAndSortingRepository<MsgCommentBean, Integer> {



    @Query(" from MsgCommentBean where msgId= :msgId and isActive = 1 order by  createdDate asc")
    public List<MsgCommentBean> getMsgComments(Integer msgId);

}

