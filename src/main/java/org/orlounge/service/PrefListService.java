package org.orlounge.service;

import org.orlounge.bean.InstrumentPrefListBean;
import org.orlounge.bean.PrefListBean;
import org.orlounge.common.AppThreadLocal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Satyam Soni on 1/1/2016.
 */
@Service
@Transactional
public class PrefListService extends AbstractBaseService {

    public List<PrefListBean> getAllPrefs() {
        return getDaoFactory().getPrefListDAO().getAllPrefs(AppThreadLocal.getTokenLocal().getCurrentGroupId());
    }


    public PrefListBean getPrefById(final Integer prefId) {
        return getDaoFactory().getPrefListDAO().findById(prefId).get();
    }


    public PrefListBean savePrefList(PrefListBean bean) {
        return getDaoFactory().getPrefListDAO().save(bean);
    }
    public InstrumentPrefListBean saveIns(InstrumentPrefListBean bean){
        return getDaoFactory().getInstrumentPrefListDAO().save(bean);
    }


    public InstrumentPrefListBean deleteIns(InstrumentPrefListBean bean){
         getDaoFactory().getInstrumentPrefListDAO().delete(bean);
         return bean;
    }
}