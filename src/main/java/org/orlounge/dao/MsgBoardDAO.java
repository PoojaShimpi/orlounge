package org.orlounge.dao;

import org.orlounge.bean.MsgBoardBean;
import org.orlounge.bean.MsgCommentBean;
import org.orlounge.exception.ORException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Satyam Soni on 9/18/2015.
 */
@Component
public interface MsgBoardDAO extends PagingAndSortingRepository<MsgBoardBean, Integer> {


    default List<MsgBoardBean> getMessages(Integer groupId, int start, int limit) {
        try {
            return findAllByGroupid(groupId, PageRequest.of(start, limit));

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }

    @Query("from MsgBoardBean m where m.groupId = :grpId and isActive = 1 order by createdDate desc")
    List<MsgBoardBean> findAllByGroupid(@Param("grpId") Integer grpId, Pageable pageable);

    @Query(" from MsgCommentBean where msgId= :msgId and isActive = 1 order by  createdDate asc")
    public List<MsgCommentBean> getMsgComments(Integer msgId);

}

