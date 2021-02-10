package org.orlounge.business;

import org.orlounge.bean.TagsBean;
import org.orlounge.exception.ORException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created on 7/3/2016.
 */
@Component
public class TagBusiness extends AbstractBaseBusiness{



    public List<TagsBean> getAll(){
        try{
            return getServiceFactory().getTagsService().getAll();
        }catch (Exception ex){
            throw new ORException(0);
        }
    }
    public TagsBean getById(Integer id){
        try{
            return getServiceFactory().getTagsService().getById(id);
        }catch (Exception ex){
            throw new ORException(0);
        }
    }
    public TagsBean saveOrUpdate(TagsBean o){
        try{
            return getServiceFactory().getTagsService().saveOrUpdate(o);

        }catch (Exception ex){
            throw new ORException(0);
        }
    }
    public TagsBean delete(TagsBean o){
        try{
            return getServiceFactory().getTagsService().delete(o);
        }catch (Exception ex){
            throw new ORException(0);
        }
    }


}
