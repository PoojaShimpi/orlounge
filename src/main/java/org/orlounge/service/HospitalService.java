package org.orlounge.service;

import org.orlounge.bean.HospitalBean;
import org.orlounge.bean.StateBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Satyam Soni on 9/19/2015.
 */
@Service
@Transactional
public class HospitalService extends AbstractBaseService{

    public List<HospitalBean> getAllHospitals(){
        return getDaoFactory().getHospitalDAO().getAllHospital();
    }

    public List<StateBean> getAllStates(){
        return getDaoFactory().getStateDAO().findAll();
    }

    public HospitalBean getActiveHospital(Integer id){
        return getDaoFactory().getHospitalDAO().getActiveHospital(id);
    }

    public HospitalBean saveOrUpdate(HospitalBean bean){
        return getDaoFactory().getHospitalDAO().save(bean);
    }

    public StateBean saveState(StateBean stateBean) {

        return getDaoFactory().getStateDAO().saveAndFlush(stateBean);
    }

    public HospitalBean getHospitalBeanByGroupId(Integer groupId){
        return getDaoFactory().getHospitalDAO().getHospitalBeanByGroupId(groupId);
    }
}
