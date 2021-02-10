package org.orlounge.business;

import org.orlounge.bean.PostOpAccessBean;
import org.orlounge.bean.PostOpBean;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.UserToken;
import org.orlounge.common.util.ProcessData;
import org.orlounge.common.util.StringUtil;
import org.orlounge.exception.ORException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
@Component
public class PostOpBusiness extends AbstractBaseBusiness{

    public List<PostOpBean> getAccessiblePostOps(){
        try{
            List<PostOpBean> schedules = getServiceFactory().getPostOpService().getAccessiblePostOps();
            return schedules;
        } catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            throw new ORException(ex,0);
        }
    }
    public PostOpBean  getPostOp(Integer id){
        try{
            PostOpBean schedule = getServiceFactory().getPostOpService().getPostOp(id);
            return schedule;
        } catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            throw new ORException(ex,0);
        }
    }

    public boolean  saveOrUpdate(PostOpBean bean){
        try{
            boolean isNew= false;
            UserToken token = AppThreadLocal.getTokenLocal();
            Set<PostOpAccessBean> accessBeanSet = new HashSet<>();
            if(ProcessData.isValid(bean.getId())){
                PostOpBean schedule = getServiceFactory().getPostOpService().getPostOp(bean.getId());
                schedule = setValue(schedule, bean);
                bean = schedule;
                bean.setCreatedDate(schedule.getCreatedDate());
                bean.setUserId(schedule.getUserId());
                bean.setGroupId(schedule.getGroupId());
                bean.setUpdatedDate(new Date());
            } else {
                bean.setCreatedDate(new Date());
                bean.setUserId(token.getUserId());
                bean.setGroupId(token.getCurrentGroupId());
                bean.setIsActive(1);
                isNew = true;
            }
            getServiceFactory().getPostOpService().savePostOp(bean);
            if(isNew){
                if(bean.getUserPreveliges() != null){
                String[] access = bean.getUserPreveliges().split(",");

                PostOpAccessBean accessBean = new PostOpAccessBean();
                accessBean.setActive(1);
                accessBean.setPostOpId(bean.getId());
                accessBean.setUserId(AppThreadLocal.getTokenLocal().getUserId());
                savePostOpAccess(accessBean);
                for(String e : access){
                    if(StringUtil.isEmptyString(e)){
                        continue;
                    }
                    Integer userId = Integer.parseInt(e);
                    accessBean = new PostOpAccessBean();
                    accessBean.setActive(1);
                    accessBean.setPostOpId(bean.getId());
                    accessBean.setUserId(userId);
                    savePostOpAccess(accessBean);

                }
            }
           }

            return true;
        } catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            throw new ORException(ex,0);
        }
    }

    private PostOpAccessBean savePostOpAccess(PostOpAccessBean accessBean) {
        try{
           return getServiceFactory().getPostOpService().savePostOpAccess(accessBean);

        } catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            throw new ORException(ex,0);
        }
    }


    public boolean removePostOpAccess(String id) throws Exception {
        boolean flag = false;
        try {
            Integer inputId  = Integer.parseInt(id);
            PostOpBean schedule = getServiceFactory().getPostOpService().getPostOp(inputId);
            schedule.setIsActive(0);
            flag = saveOrUpdate(schedule);
//            int ans=getServiceFactory().getPostOpService().deletePostOp(inputId);
//    		if(ans > 0){
//    			flag = true;
//    		} else {
//    			flag  = false;
//    		}
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return flag;
    }

    private PostOpBean setValue(PostOpBean schedule,PostOpBean bean){
        if(ProcessData.isValid(bean.getSurgeon())){
            schedule.setSurgeon(bean.getSurgeon());
        }
        if(ProcessData.isValid(bean.getOperation())){
            schedule.setOperation(bean.getOperation());
        }
        if(ProcessData.isValid(bean.getActivity())){
            schedule.setActivity(bean.getActivity());
        }
        if(ProcessData.isValid(bean.getEmergencyContact())){
            schedule.setEmergencyContact(bean.getEmergencyContact());
        }
        if(ProcessData.isValid(bean.getRestriction())){
            schedule.setRestriction(bean.getRestriction());
        }
        if(ProcessData.isValid(bean.getWoundCare())){
            schedule.setWoundCare(bean.getWoundCare());
        }
        if(ProcessData.isValid(bean.getMedications())){
            schedule.setMedications(bean.getMedications());
        }
        if(ProcessData.isValid(bean.getSupplies())){
            schedule.setSupplies(bean.getSupplies());
        }
        if(ProcessData.isValid(bean.getOfficeVisit())){
            schedule.setOfficeVisit(bean.getOfficeVisit());
        }
        if(ProcessData.isValid(bean.getInstructions())){
            schedule.setInstructions(bean.getInstructions());
        }
        if(ProcessData.isValid(bean.getOther())){
            schedule.setOther(bean.getOther());
        }
        if(ProcessData.isValid(bean.getIsActive())){
            schedule.setIsActive(bean.getIsActive());
        }
        return  schedule;
    }

}
