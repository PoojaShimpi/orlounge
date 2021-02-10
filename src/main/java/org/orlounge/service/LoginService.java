package org.orlounge.service;

import org.orlounge.bean.*;
import org.orlounge.common.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Satyam Soni on 9/15/2015.
 */
@Service
@Transactional
public class LoginService extends AbstractBaseService {


    public List<ConfigBean> getConfig() {
        return getDaoFactory().getLoginDAO().getConfigs();
    }

    public LoginBean getLoginDAO(String userCode) {
        return getDaoFactory().getLoginDAO().getLoginDAO(userCode);
    }

    public UserBean saveUser(UserBean user) {
        return getDaoFactory().getUserDAO().saveAndFlush(user);
    }

    public LoginBean saveLogin(LoginBean user) {
        return getDaoFactory().getLoginDAO().save(user);
    }

    public UserBean getUserByUserCode(String userCode) {
        return getDaoFactory().getUserDAO().getUserByUserCode(userCode);
    }

    public List<UserInfo> getAllUsersAsPerRole() {
        return getDaoFactory().getUserDAO().getUserAsPerRole();
    }

    public UserInfo getUsersAsPerRole(Integer userId, Integer groupId) {
        return getDaoFactory().getUserDAO().getUserForGroup(userId, groupId);
    }

    public UserBean getUserByUserId(Integer userId) {
        return getDaoFactory().getUserDAO().findById(userId).get();
    }
    public List<UserInfo> getUsersForGroup(Integer groupId) {
        return getDaoFactory().getUserDAO().getUserForGroup(groupId);
    }

    public List<UserInfo> getLsaUsersForGroup(Integer groupId){
        return getDaoFactory().getUserDAO().getLsaUsersForGroup(groupId);
    }
    public List<UserInfo> getLsaUsersForGroup(Integer groupId, Integer active){
        return getDaoFactory().getUserDAO().getLsaUsersForGroup(groupId, active);
    }
    public List<UserInfo> getSuperUser(){
        return getDaoFactory().getUserDAO().getSuperUser();
    }

    public void changeLsaFlag(Integer userId, Integer groupId, Integer lsaFlag) {
        getDaoFactory().getUserGroupMapDAO().changeLsaFlag(userId, groupId, lsaFlag);

    }

    public ApproverResponse addApproverResponse(ApproverResponse toSave) {
        return getDaoFactory().getApproverResponseDAO().save(toSave);
    }

    public List<BulkMailProcess> getBulkMailProcesses(){
        return getDaoFactory().getBulkMailProcessDAO().getBulkMailProcess();
    }

    public BulkMailProcess saveBulkProcess(BulkMailProcess process){
        return getDaoFactory().getBulkMailProcessDAO().saveAndFlush(process);
    }
    public BulkMailProcess getBulkProcess(Integer id){
        return getDaoFactory().getBulkMailProcessDAO().findById(id).get();
    }

    public TmpOtp saveOrUpdate(TmpOtp requestTmpOtp) {
        return getDaoFactory().getTmpOtpDAO().save(requestTmpOtp);
    }

    public TmpOtp getTmpOtpByEmail(String email){
        List<TmpOtp> otpList = getDaoFactory().getTmpOtpDAO().getTmpOtpByEmail(email);
        if(otpList.isEmpty())
            return null;
        return otpList.get(0);
    }

    public void deleteTmpById(TmpOtp requestTmpOtp){
        getDaoFactory().getTmpOtpDAO().delete(requestTmpOtp);
    }

    public UserInfo getUserAsPerRoleForStaff(Integer userId, Integer groupId){
        return getDaoFactory().getUserDAO().getUserAsPerRoleForStaff(userId,groupId);
    }
}