package org.orlounge.dao;

import org.orlounge.bean.IdeaBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IdeaDAO extends PagingAndSortingRepository<IdeaBean, Integer> {



    default List<IdeaBean> getIdeasSortByVotes(int num){
        return findAll(PageRequest.of(0, num, Sort.by(Sort.Direction.DESC, "votes"))).getContent();
    }

    default boolean voteUp(IdeaBean bean){
        bean.setVotes(bean.getVotes()+ 1);
        save(bean);
        return true;
    }




}
