package org.orlounge.service;

import org.orlounge.bean.ProcedureManualBean;
import org.orlounge.common.AppThreadLocal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
@Transactional
@Service
public class ProcedureManualService extends AbstractBaseService{


    public List<ProcedureManualBean> getProcedureManuals(){
            return getDaoFactory().getProcedureManualDAO().getProcedureManuals(AppThreadLocal.getTokenLocal().getCurrentGroupId());
    }

    public ProcedureManualBean getProcedureManual(Integer id){
            return getDaoFactory().getProcedureManualDAO().findById(id).get();
    }

    public ProcedureManualBean saveProcedureMaunal(ProcedureManualBean bean){
        return getDaoFactory().getProcedureManualDAO().save(bean);
    }

    public ProcedureManualBean deleteProcedureManual(ProcedureManualBean bean){
        return getDaoFactory().getProcedureManualDAO().deleteProcedureManual(bean);
    }
}
