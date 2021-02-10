package org.orlounge.service;

import org.orlounge.bean.ChecklistBean;
import org.orlounge.common.AppThreadLocal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
@Transactional
@Service
public class ChecklistService extends AbstractBaseService{


    public List<ChecklistBean> getChecklists(){
            return getDaoFactory().getChecklistDAO().getChecklists(AppThreadLocal.getTokenLocal().getCurrentGroupId());
    }

    public ChecklistBean getChecklist(Integer id){
            return getDaoFactory().getChecklistDAO().findById(id).get();
    }

    public ChecklistBean saveChecklist(ChecklistBean bean){
        return getDaoFactory().getChecklistDAO().saveAndFlush(bean);
    }

    public int deleteChecklist(Integer id){
        return getDaoFactory().getChecklistDAO().deleteChecklist(id);
    }
}
