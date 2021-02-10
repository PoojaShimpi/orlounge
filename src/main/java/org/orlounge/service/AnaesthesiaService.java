package org.orlounge.service;

import org.orlounge.bean.AnaethesiaSetupBean;
import org.orlounge.bean.AnaethesiaSetupNewBean;
import org.orlounge.common.AppThreadLocal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Satyam Soni on 1/4/2016.
 */
@Service
@Transactional
public class AnaesthesiaService extends AbstractBaseService {

    public List<AnaethesiaSetupBean> getAllSetups() {
        return getDaoFactory().getAnaethsiaSetupDAO().getAllSetups(AppThreadLocal.getTokenLocal().getCurrentGroupId());
    }

    public AnaethesiaSetupBean getSetupById(final Integer id) {
        return getDaoFactory().getAnaethsiaSetupDAO().findById(id).get();
    }

    public AnaethesiaSetupBean saveSetup(AnaethesiaSetupBean bean){
        return getDaoFactory().getAnaethsiaSetupDAO().saveAndFlush(bean);
    }


    public List<AnaethesiaSetupNewBean> getAllAnaethesiaSetupNewBean(){
        return getDaoFactory().getAnaethsiaNewSetupDAO().getAllSetups(AppThreadLocal.getTokenLocal().getCurrentGroupId());
    }

    public AnaethesiaSetupNewBean getAnaethesiaSetupNewBeanById(Integer id){
        return getDaoFactory().getAnaethsiaNewSetupDAO().findById(id).get();
    }

    public boolean saveAnaethesiaSetupNewBean(AnaethesiaSetupNewBean bean,boolean isUpdate)  {
        return getDaoFactory().getAnaethsiaNewSetupDAO().saveAndFlush(bean) != null;
    }

}
