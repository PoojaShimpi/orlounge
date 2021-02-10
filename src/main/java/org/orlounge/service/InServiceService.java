package org.orlounge.service;

import org.orlounge.bean.InServiceBean;
import org.orlounge.common.AppThreadLocal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
@Transactional
@Service
public class InServiceService extends AbstractBaseService {


    public List<InServiceBean> getInServices(){
            return getDaoFactory().getInServiceDAO().getInServices(AppThreadLocal.getTokenLocal().getCurrentGroupId());
    }

    public InServiceBean getInService(Integer id){
            return getDaoFactory().getInServiceDAO().findById(id).get();
    }

    public InServiceBean saveInService(InServiceBean bean){
        return getDaoFactory().getInServiceDAO().saveAndFlush(bean);
    }

    public InServiceBean deleteInService(InServiceBean bean){
        return getDaoFactory().getInServiceDAO().deleteInService(bean);
    }
}
