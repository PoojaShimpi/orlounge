package org.orlounge.business;

import org.orlounge.bean.HospitalBean;
import org.orlounge.exception.ORException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Satyam Soni on 11/7/2015.
 */
@Component
public class HospitalBusiness extends AbstractBaseBusiness{

    public List<HospitalBean> getAllHospitals(){
        return getServiceFactory().getHospitalService().getAllHospitals();
    }

    public void saveHospital(HospitalBean bean){
        try{
            getServiceFactory().getHospitalService().saveOrUpdate(bean);

        }catch (ORException ex){
          throw ex;
        } catch (Exception ex){
            throw new ORException(ex, 0);
        }
    }
}
