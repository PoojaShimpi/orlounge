package org.orlounge.service;

import org.orlounge.bean.HandoverBean;
import org.orlounge.common.AppThreadLocal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
@Transactional
@Service
public class HandoverService extends AbstractBaseService{


    public List<HandoverBean> getHandovers(){
            return getDaoFactory().getHandoverDAO().getAllHandOvers(AppThreadLocal.getTokenLocal().getCurrentGroupId());
    }

    public HandoverBean getHandover(Integer id){
            return getDaoFactory().getHandoverDAO().findById(id).get();
    }

    public HandoverBean saveHandover(HandoverBean bean){
        return getDaoFactory().getHandoverDAO().saveAndFlush(bean);
    }

    public int deleteHandover(Integer id){
        return getDaoFactory().getHandoverDAO().deleteHandover(id);
    }
}
