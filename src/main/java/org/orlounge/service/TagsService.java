package org.orlounge.service;

import org.orlounge.bean.TagsBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TagsService extends AbstractBaseService {

    public List<TagsBean> getAll(){
        return getDaoFactory().getTagDAO().findAll();
    }

    public TagsBean delete(TagsBean o) {
        getDaoFactory().getTagDAO().delete(o);
        return o;
    }

    public TagsBean saveOrUpdate(TagsBean o) {
        return getDaoFactory().getTagDAO().save(o);
    }

    public TagsBean getById(Integer id) {
        return getDaoFactory().getTagDAO().findById(id).get();
    }
}
