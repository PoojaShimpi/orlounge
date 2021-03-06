package org.orlounge.business;

import org.orlounge.bean.*;
import org.orlounge.common.*;
import org.orlounge.common.mail.ORMail;
import org.orlounge.common.util.*;
import org.orlounge.exception.ORException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.*;

/**
 * Created by Satyam Soni on 9/13/2015.
 */
@Component
@Transactional
public class UserServiceImpl extends AbstractBaseBusiness implements UserDetailsService {

    @Autowired
    private ORMail orMail;

    public ORMail getOrMail() {
        return orMail;
    }

    public void setOrMail(ORMail orMail) {
        this.orMail = orMail;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            LoginBean loginBean = getServiceFactory().getLoginService().getLoginDAO(username.trim().toLowerCase());
            if (ProcessData.isValid(loginBean)) {
                final UserBean user = getServiceFactory().getLoginService().getUserByUserCode(username.trim().toLowerCase());
                List<GrantedAuthority> auths = (List<GrantedAuthority>) loginBean.getAuthorities();
                auths.add(new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return "USER";
                    }
                });
            }
            return loginBean;
        } catch (Exception ex) {
            throw new UsernameNotFoundException("Invalid Login");
        }
    }

    public Integer getLastAccessGroupIdByUser(Integer userId) {
        try {
            return getServiceFactory().getGroupService().getLastAccessGroupIdForUser(userId);
        } catch (Exception ex) {
            throw new ORException(0);
        }
    }

    public Integer updateLastAccessGroupIdByUser(Integer userId, Integer groupId) {
        try {
            return getServiceFactory().getGroupService().updateLastAccessGroupIdByUser(userId, groupId);
        } catch (Exception ex) {
            throw new ORException(0);
        }
    }

    public List<UserInfo> getUsersForGroup(Integer groupId) {
        try {
            return getServiceFactory().getLoginService().getUsersForGroup(groupId);
        } catch (Exception ex) {
            throw new ORException(0);
        }
    }


    public boolean changePass(Integer userId, String userCode, String oldPass, String newPass) throws UsernameNotFoundException {
        try {
            UserToken token = AppThreadLocal.getTokenLocal();
            if (!token.getUserCode().equals(userCode) || !token.getUserId().equals(userId) || oldPass.equals(newPass)) {
                return false;
            }

            LoginBean loginBean = getServiceFactory().getLoginService().getLoginDAO(userCode.trim().toLowerCase());
            if (ProcessData.isValid(loginBean) && loginBean.getPassword().equals(oldPass)) {
                loginBean.setLastPassChange(DateContent.convertDBDateToClientDate(new Date(), token.getTimeZone()));
                loginBean.setPassword(newPass);
                getServiceFactory().getLoginService().saveLogin(loginBean);
                return true;
            }
            return false;
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }

    public boolean doUserApproval(Integer userId, Integer groupId) {
        try {
            UserInfo info = getUserInfo(userId, groupId);
            if (ProcessData.isValid(info)) {
                String username = info.getUserCode();
                UserBean user = activeDeactiveUser(username, 1);
                UserToken token = AppThreadLocal.getTokenLocal();
                if (ProcessData.isValid(user) && ProcessData.isValid(token)) {
                    UserGroupMap userGroupMap = activeDeactiveUserInGroup(userId, info.getGrpId(), 1);
                    String subject = "Your request has been Approved";
                    String mailText;
                    if (userGroupMap.getIsLSA().equals(1) && userGroupMap.getRole().equals(AppConstants.ORM_ROLE)) {

                        mailText = new RegisterationEmailer()
                                .approvalLSAIsORMEmail(user.getFirstName() + " " + user.getLastName(), info.getGroupName());
                    } else if (userGroupMap.getIsLSA().equals(1)) {

                        mailText = new RegisterationEmailer()
                                .approvalLSAEmail(user.getFirstName() + " " + user.getLastName(), info.getGroupName());
                    } else if (userGroupMap.getRole().equals(AppConstants.ORM_ROLE)) {

                        mailText = new RegisterationEmailer()
                                .approvalMemberEmailIsORMButNotLSA(user.getFirstName() + " " + user.getLastName(), info.getGroupName(), token.getFirstName() + " " + token.getLastName());

                    } else {

                        mailText = new RegisterationEmailer()
                                .approvalMemberEmail(user.getFirstName() + " " + user.getLastName(), info.getGroupName(), token.getFirstName() + " " + token.getLastName());

                    }
                    String to = user.getEmail();
                    getBusinessFactory().getMailBusiness().sendMail(to, null, null, subject, mailText, null);


                    return true;
                }
                return false;
            } else {
                throw new ORException(5);
            }
        } catch (Exception ex) {
            return false;
        }


    }

    /*
        public boolean sendInviteMail(String mail, boolean sameOR, boolean isOrm){
            String body;
            if(sameOR){
                body = INVITE_MAIL.replaceAll("\\{userName\\}",mail);
            }else if(isOrm){
                body = INVITE_ORM_MAIL.replaceAll("\\{userName\\}",mail);
            }else {
                body = INVITE__HOSPITAL_MAIL.replaceAll("\\{userName\\}",mail);
            }
            return sendInviteMail(mail, body);
        }*/
    public boolean sendInviteMail(String mail, String emailMessage) {

        UserToken token = AppThreadLocal.getTokenLocal();
        String body = new RegisterationEmailer().invitationEmail(token.getFirstName() + " " + token.getLastName(), emailMessage, mail);

        final String host = getConfigMap().get(AppConstants.SMTP_HOST);
        final String port = getConfigMap().get(AppConstants.SMTP_PORT);
        final String accessKey = getConfigMap().get(AppConstants.SES_ACCESS_KEY_PROP);
        final String secretKey = getConfigMap().get(AppConstants.SES_SECRET_KEY_PROP);
        final String fromMail = getConfigMap().get(AppConstants.FROM_MAIL);
        final String fromPass = getConfigMap().get(AppConstants.FROM_PASS);

        final ORMail.MailConfig mailConfig = new ORMail.MailConfig(fromPass, fromMail, port, host, accessKey, secretKey);
        getOrMail().sendMail(mailConfig, "Check this out : www.orlounge.com", body, Arrays.asList(mail), new ArrayList<String>(0), new ArrayList<String>(0), null, false);
        return true;
    }


    public boolean verificationMailNotification(String hospitalName, ApproverResponse bean) {


        List<UserInfo> approvers = getServiceFactory().getLoginService().getSuperUser();
        List<String> mails = new ArrayList<>();
        for (UserInfo e : approvers) {
            mails.add(e.getEmail());
        }

        String body = new RegisterationEmailer().verificationStatusEmail("ORTeam", hospitalName, "OR Team", bean);

        final String host = getConfigMap().get(AppConstants.SMTP_HOST);
        final String port = getConfigMap().get(AppConstants.SMTP_PORT);
        final String accessKey = getConfigMap().get(AppConstants.SES_ACCESS_KEY_PROP);
        final String secretKey = getConfigMap().get(AppConstants.SES_SECRET_KEY_PROP);
        final String fromMail = getConfigMap().get(AppConstants.FROM_MAIL);
        final String fromPass = getConfigMap().get(AppConstants.FROM_PASS);

        final ORMail.MailConfig mailConfig = new ORMail.MailConfig(fromPass, fromMail, port, host, accessKey, secretKey);
        getOrMail().sendMail(mailConfig, "Verification Response for " + hospitalName, body, mails, new ArrayList<String>(0), new ArrayList<String>(0), null, false);
        return true;
    }


    public boolean doUserDelete(Integer userId, Integer groupId) {
        try {
            UserInfo info = getUserInfo(userId, groupId);
            if (ProcessData.isValid(info)) {
                String username = info.getUserCode();
                UserBean user = activeDeactiveUser(username, -1);
                List<GroupBean> grp = getServiceFactory().getGroupService().getGroupByUserForAction(userId);
                if (ProcessData.isValid(user) && ProcessData.isValidCollection(grp)) {
                    for (GroupBean g : grp) {
                        activeDeactiveUserInGroup(userId, g.getGroupId(), -1);
                    }

                    return true;
                }
                return false;
            } else {
                throw new ORException(5);
            }
        } catch (Exception ex) {
            return false;
        }


    }

    public boolean doUserDeActive(Integer userId, Integer groupId) {
        try {
            UserInfo info = getUserInfo(userId, groupId);
            if (ProcessData.isValid(info)) {
                String username = info.getUserCode();
                List<UserGroupMap> list = getServiceFactory().getGroupService().getGroupsByUserIncludingInActive(userId);
                boolean needToDisableLogin = true;
                for (UserGroupMap each : list) {
                    if (!each.getGroupId().equals(groupId) && each.getIsActive().equals(1)) {
                        needToDisableLogin = false;
                        break;
                    }
                }

                UserBean user = activeDeactiveUser(username, needToDisableLogin ? 0 : 1);
                UserToken token = AppThreadLocal.getTokenLocal();
                /*GroupBean grp =  getServiceFactory().getGroupService().getGroupByUserForAction( userId );*/
                if (ProcessData.isValid(user) && ProcessData.isValid(token)) {
                    /*if(user.getLsa()) {
                        activeDeActiveGroup(grp, 0);
                    }*/
                    activeDeactiveUserInGroup(userId, info.getGrpId(), 0);
                    return true;
                }
                return false;
            } else {
                throw new ORException(5);
            }
        } catch (Exception ex) {
            return false;
        }


    }

    public LoginBean getLoginBean(String username) throws UsernameNotFoundException {
        try {
            LoginBean loginBean = getServiceFactory().getLoginService().getLoginDAO(username.trim().toLowerCase());
            return loginBean;
        } catch (Exception ex) {
            throw new ORException(5);
        }
    }

    public UserBean activeDeactiveUser(String username, int activeFlag) throws UsernameNotFoundException {
        try {
            LoginBean loginBean = getLoginBean(username);
            if (ProcessData.isValid(loginBean)) {
                loginBean.setIsActive(activeFlag);
                getServiceFactory().getLoginService().saveLogin(loginBean);

                return getUserBeanByUserCode(username);
            }
            return null;
        } catch (Exception ex) {
            throw new ORException(5);
        }
    }

    public GroupBean activeDeActiveGroup(Integer grpId, int activeFlag) throws UsernameNotFoundException {
        try {
            GroupBean grp = getServiceFactory().getGroupService().getGroupByGrpId(grpId);
            if (ProcessData.isValid(grp)) {
                grp.setIsActive(activeFlag);
                getServiceFactory().getGroupService().saveGroup(grp);
            }
            return grp;
        } catch (Exception ex) {
            throw new ORException(5);
        }
    }

    public GroupBean activeDeActiveGroup(GroupBean bean, int activeFlag) throws UsernameNotFoundException {
        try {
            if (ProcessData.isValid(bean)) {
                bean.setIsActive(activeFlag);
                getServiceFactory().getGroupService().saveGroup(bean);
            }
            return bean;
        } catch (Exception ex) {
            throw new ORException(5);
        }
    }


    public UserGroupMap activeDeactiveUserInGroup(Integer userId, Integer grp, int activeFlag) throws UsernameNotFoundException {
        try {
            UserGroupMap map = getServiceFactory().getGroupService().getGroupUserMap(userId, grp);
            if (ProcessData.isValid(map)) {
                map.setIsActive(activeFlag);
                getServiceFactory().getGroupService().saveUserGroupMap(map);
                UserBean bean = getServiceFactory().getLoginService().getUserByUserId(userId);
                bean.setUserFlag(1);
                getServiceFactory().getLoginService().saveUser(bean);

            }
            return map;
        } catch (Exception ex) {
            throw new ORException(5);
        }
    }

    public UserBean getUserBeanByUserCode(String userCode) {
        try {
            return getServiceFactory().getLoginService().getUserByUserCode(userCode);
        } catch (Exception ex) {
            throw new UsernameNotFoundException("Invalid Login");
        }
    }

    public UserBean getUserBeanByUserId(Integer userId) {
        try {
            return getServiceFactory().getLoginService().getUserByUserId(userId);
        } catch (Exception ex) {
            throw new UsernameNotFoundException("Invalid Login");
        }
    }

    public List<UserGroupMap> getGroupMapsBeanByUser(Integer userId) {
        try {
            return getServiceFactory().getGroupService().getGroupsByUser(userId);
        } catch (Exception ex) {
            throw new UsernameNotFoundException("Invalid Login");
        }
    }

    public boolean hasGroupLSAAsRole(String role, Integer groupId) {
        try {
            return getServiceFactory().getGroupService().hasGroupLsaAsRole(role, groupId);
        } catch (Exception ex) {
            throw new UsernameNotFoundException("Invalid Login");
        }
    }


    private boolean validateUserBean(UserBean userBean) {
        try {
            return ProcessData.isValid(userBean)
                    && ProcessData.isValid(userBean.getEmail())
                    && ProcessData.isValid(userBean.getUserCode())
                    && ProcessData.isValid(userBean.getFirstName())
                    && ProcessData.isValid(userBean.getLastName())
                    && ProcessData.isValid(userBean.getRole())
                    && Validation.validateEmail(userBean.getEmail());


        } catch (Exception ex) {
            return false;
        }
    }

    private boolean validateUserLogin(LoginBean loginBean) {
        try {
            return ProcessData.isValid(loginBean) && ProcessData.isValid(loginBean.getEmail())
                    && ProcessData.isValid(loginBean.getUserCode())
                    && ProcessData.isValid(loginBean.getPassword())
                    && Validation.validateEmail(loginBean.getEmail())
                    && loadUserByUsername(loginBean.getUsername()) == null;


        } catch (Exception ex) {
            return false;
        }
    }

    private boolean validateGroup(GroupBean bean) {
        try {
            boolean valid = ProcessData.isValid(bean)
                    && ProcessData.isValid(bean.getHospitalId());


            return valid;


        } catch (Exception ex) {
            return false;
        }
    }

    public HospitalBean getHospital(Integer id) {
        try {
            return getServiceFactory().getHospitalService().getActiveHospital(id);
        } catch (Exception ex) {
            throw new ORException(0);
        }
    }

    public List<UserInfo> getLsaUserForGroup(Integer groupId) {
        try {
            return getServiceFactory().getLoginService().getLsaUsersForGroup(groupId);
        } catch (Exception ex) {
            throw new ORException(0);
        }
    }

    public List<UserInfo> getLsaUserForGroup(Integer groupId, Integer active) {
        try {
            return getServiceFactory().getLoginService().getLsaUsersForGroup(groupId, active);
        } catch (Exception ex) {
            throw new ORException(0);
        }
    }


    public List<StateBean> getStates() {
        try {
            return getServiceFactory().getHospitalService().getAllStates();
        } catch (Exception ex) {
            throw new ORException(0);
        }
    }

    public StateBean saveState(StateBean stateBean) {
        try {
            return getServiceFactory().getHospitalService().saveState(stateBean);
        } catch (Exception ex) {
            throw new ORException(0);
        }
    }

    public List<GroupedHospital> getAllGroupedHospital() {
        try {
            List<HospitalBean> hos = getServiceFactory().getHospitalService().getAllHospitals();
            List<GroupedHospital> result = new ArrayList<GroupedHospital>();
            Map<Integer, GroupedHospital> map = new HashMap<Integer, GroupedHospital>();
            GroupedHospital g = null;
            StateBean state = null;
            for (HospitalBean h : hos) {
                state = h.getState();
                if (map.get(state.getStateId()) != null) {
                    g = map.get(state.getStateId());

                } else {
                    g = new GroupedHospital();
                    g.setStateName(state.getStateName());
                    g.setStateId(state.getStateId());
                    g.setStateCode(state.getStateCode());
                    map.put(state.getStateId(), g);
                    result.add(g);
                }
                g.addHospital(h.getHospitalName(), h.getHospitalId(), h.getType());
            }
            Set<Map.Entry<Integer, GroupedHospital>> set = map.entrySet();

            return result;
        } catch (Exception ex) {
            throw new ORException(0);
        }
    }


    public GroupBean getGrpAlreadyExistsByHospitalId(GroupBean grp) {
        return getServiceFactory().getGroupService().getGroupByHospitalId(grp.getHospitalId());
    }

    private void downloadAndSetProfileImage(MultipartFile file, UserBean user) throws IOException {
        if (file != null && !file.isEmpty()) {
            final String bucket = getConfigMap().get(AppConstants.CALL_SCHEDULE_BUCKET_NAME);
            final String accessKey = getConfigMap().get(AppConstants.S3_ACCESS_KEY_PROP);
            final String secretKey = getConfigMap().get(AppConstants.S3_SECRET_KEY_PROP);
            final String region = getConfigMap().get(AppConstants.S3_REGION_PROP);
            File dir = new File(System.getProperty("java.io.tmpdir") + "\\uploads");
            if (!dir.exists()) {
                dir.mkdir();
                new File(System.getProperty("java.io.tmpdir") + "\\uploads\\" + user.getUserCode()).mkdir();
            }
            File f = new File(System.getProperty("java.io.tmpdir") + "\\uploads" + "\\" + file.getOriginalFilename());
            f.createNewFile();
            try (OutputStream os = Files.newOutputStream(f.toPath())) {
                os.write(file.getBytes());
            }
       //     Files.copy(f.toPath(), new FileOutputStream(f));
            ORS3 ors3 = ORS3.getInstance(accessKey, secretKey, region);
            String fId = StringUtil.getUUID();
            String ext = f.getName().substring(f.getName().lastIndexOf("."));
            String path = "badges" + "/" + fId + ext;
            user.setHospitalBadgeId(path);
            ors3.uploadFile(f, bucket, path);
            f.deleteOnExit();
        }


    }

    private void createStaffForUser(UserBean user){
        StaffInfoBean staffInfoBean = new StaffInfoBean();
        staffInfoBean.setUserId(user.getUserId());
        staffInfoBean.setMobile(user.getHospitalTelPh());
        staffInfoBean.setFullName(user.getFirstName()+" "+user.getLastName());
        getServiceFactory().getStaffInfoService().saveStaffInfo(staffInfoBean);
    }

    public Map saveUserNewLSA(final LoginBean login, UserBean user, String radioEvent, String hospitalName, Integer hospitalId, String sizeOfOR, MultipartFile file, String hospitalCreateOrJoin) throws UsernameNotFoundException {
        Map result = new HashMap();
        String message = "";
        boolean success;
        try {
            GroupBean grp = new GroupBean();
            grp.setUser(user);
            grp.setSizeOfOperatinRoom(sizeOfOR);
            grp.setHospitalId(hospitalId);

            boolean valid = validateUserLogin(login) && validateUserBean(user) && validateGroup(grp);
            if (!valid) {
                success = false;
                message = "Invalid Details";
                result.put("success", success);
                result.put("message", message);
                return result;
            } else {
                HospitalBean h = getHospital(grp.getHospitalId());
                if (h != null) {
                    final String timezone = h.getTimezone();
                    Date utc = DateContent.convertClientDateIntoUTCDate(new Date(), timezone);
                    GroupBean alreadyExistsGrp = getGrpAlreadyExistsByHospitalId(grp);
                    alreadyExistsGrp.setSizeOfOperatinRoom(sizeOfOR);


                    boolean isLSA = false;
                    boolean isAlreadyHospitalExists = false;
                    if (alreadyExistsGrp == null) {
                        isAlreadyHospitalExists = false;
                    } else {

                            grp = alreadyExistsGrp;
                            isAlreadyHospitalExists = true;

                    }


                    String subject = "Welcome To ORLounge";
                    String mailText = null;
                    String approverMail = null;
                    List<UserInfo> approvers = null;
                    if (hospitalCreateOrJoin.equals("Join") && !isAlreadyHospitalExists) {
                        message =  "The " + h.getHospitalName() + " Operating Room Lounge does not exists. First you have to create Lounge Site. Please select create option.";
                        result.put("success", false);
                        result.put("message", message);
                        return result;
                    }


                    if ((isAlreadyHospitalExists && hospitalCreateOrJoin.equals("Create"))) {
                       message = "The Lounge site already exists.Please select join option to join the site.";
                        result.put("success", false);
                        result.put("message", message);
                        return result;
                    }
                    downloadAndSetProfileImage(file, user);

                    login.setLastPassChange(utc);
                    login.setIsActive(0);
                    getServiceFactory().getLoginService().saveLogin(login);
                    user.setCreatedDate(utc);
                    user.setTimezone(timezone);
                    getServiceFactory().getLoginService().saveUser(user);

                    if (!isAlreadyHospitalExists) {
                        grp.setIsActive(0);
                        grp.setGroupName(h.getHospitalName());
                        grp.setUser(user);
                        grp.setCreatedDate(utc);
                        grp.setCreatorId(user.getUserId());
                        grp.setTrialFlag(1);
                        grp.setIsActive(1);
                        getServiceFactory().getGroupService().saveGroup(grp);
                        isLSA = true;
                    }


                    UserGroupMap map = new UserGroupMap();
                    map.setUserId(user.getUserId());
                    map.setUser(user);
                    map.setIsActive(0);
                    map.setGroup(grp);
                    map.setGroupId(grp.getGroupId());
                    map.setIsLSA(isLSA ? 1 : 0);
                    map.setRole(user.getRole());


                    getServiceFactory().getGroupService().saveUserGroupMap(map);

                    createStaffForUser(user);

                    // TODO : MAILING CODE
                    if (radioEvent.equalsIgnoreCase("hospital")) {
                        if (user.getRole().equalsIgnoreCase(AppConstants.ORM_ROLE)) {
                            mailText = new RegisterationEmailer().registerLSAisORMEmail(user.getFirstName() + " " + user.getLastName(), h.getHospitalName());
                            approvers = getServiceFactory().getLoginService().getSuperUser();
                            approverMail = new RegisterationEmailer().approverNotificationForORTeamEmail(user.getFirstName() + " " + user.getLastName(), h.getHospitalName());
                            message = "Congratulations! The " + h.getHospitalName() + " Operating Room Lounge has been created. <br/> As an OR Manager, you will be the local site administrator.<br/> All requests to join this lounge will be forwarded to you for approval.";
                        } else {
                            mailText = new RegisterationEmailer().registerLSAEmail(user.getFirstName() + " " + user.getLastName(), h.getHospitalName());
                            approvers = getServiceFactory().getLoginService().getSuperUser();
                            approverMail = new RegisterationEmailer().approverNotificationForORTeamEmail(user.getFirstName() + " " + user.getLastName(), h.getHospitalName());
                            message = "Congratulations! The " + h.getHospitalName() + " Operating Room Lounge has been created. You will be the temporary local site administrator until the OR Manager has joined the site. Read why.All requests to join this lounge will be forwarded to you for approval. Read whylink";
                        }
                        if (isAlreadyHospitalExists) {
                            mailText = new RegisterationEmailer().registerLSAEmail(user.getFirstName() + " " + user.getLastName(), h.getHospitalName());
                            approvers = getServiceFactory().getLoginService().getSuperUser();
                            approverMail = new RegisterationEmailer().approverNotificationForORTeamEmail(user.getFirstName() + " " + user.getLastName(), h.getHospitalName());
                            message = "Thank you for joining the " + h.getHospitalName() + " Operating Room Lounge. Your account has been created. This account must be approved by the local site administrator, who has been notified. When your account has been approved, you will be notified.";

                        }
                    } else if (radioEvent.equalsIgnoreCase("ambulatory_center")) {
                        if (user.getRole().equalsIgnoreCase(AppConstants.ORM_ROLE)) {
                            mailText = new RegisterationEmailer().registerORMMemberEmail(user.getFirstName() + " " + user.getLastName(), h.getHospitalName());
                            approvers = getServiceFactory().getLoginService().getLsaUsersForGroup(grp.getGroupId());
                            approverMail = new RegisterationEmailer().approverNotificationEmail(user.getFirstName() + " " + user.getLastName(), h.getHospitalName());
                            message = "Thank you for registering to join an ORLS for " + h.getHospitalName() + ". Your registration profile has been sent to the local site administrator (LSA), who will notify you when you are approved.<br/>Read this as an OR ManagerlinkAfter you have joined the site, the currentlocal site administrator is temporary and will transfer that role to you. You can discuss this with the current local site administrator.System then forwards the request to the LSA with the following message. Action script required here.";

                        } else {
                            mailText = new RegisterationEmailer().registerORMMemberEmail(user.getFirstName() + " " + user.getLastName(), h.getHospitalName());
                            approvers = getServiceFactory().getLoginService().getLsaUsersForGroup(grp.getGroupId());
                            approverMail = new RegisterationEmailer().approverNotificationEmail(user.getFirstName() + " " + user.getLastName(), h.getHospitalName());
                            message = "Thank you for registering to join an ORLS for  " + h.getHospitalName() + ". Your registration profile has been sent to the local site administrator (LSA), who will notify you when you are approved.<br/>System then forwards the request to theLSA with the following message. Action script is required here.";

                        }
                        if (isAlreadyHospitalExists) {
                            mailText = new RegisterationEmailer().registerLSAEmail(user.getFirstName() + " " + user.getLastName(), h.getHospitalName());
                            approvers = getServiceFactory().getLoginService().getSuperUser();
                            approverMail = new RegisterationEmailer().approverNotificationForORTeamEmail(user.getFirstName() + " " + user.getLastName(), h.getHospitalName());
                            message = "Thank you for joining the " + h.getHospitalName() + " Operating Room Lounge. Your account has been created. This account must be approved by the local site administrator, who has been notified. When your account has been approved, you will be notified.";

                        }
                    }

                    String to = user.getEmail();
                    getBusinessFactory().getMailBusiness().sendMail(to, null, null, subject, mailText, null);

                    List<String> approversBCC = new ArrayList<>();
                    for (UserInfo each : approvers) {
                        approversBCC.add(each.getEmail());
                    }

                    getBusinessFactory().getMailBusiness().sendMail(null, StringUtil.join(",", approversBCC), null, "Joining Request", approverMail, null);


                        result.put("success", true);
                        result.put("message", message);

                    return result;

                } else {
                    success = false;
                    message = "No Such Hospital Found.";
                    result.put("success", success);
                    result.put("message", message);
                    return result;
                }
            }
        } catch (Exception ex) {
            throw new ORException(3);
        }
    }


    public UserInfo getUserInfo(Integer userId, Integer groupId) {
        try {
            return getServiceFactory().getLoginService().getUsersAsPerRole(userId, groupId);
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }

    public List<UserInfo> getUserInfoList() {
        try {
            return getServiceFactory().getLoginService().getAllUsersAsPerRole();
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }

    public StaffInfoBean getStaffInfo(Integer userId) {
        try {
            return getServiceFactory().getStaffInfoService().getStaffInfo(userId);
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }

    public StaffInfoBean saveStaffInfo(StaffInfoBean staffInfoBean) {
        try {
            return getServiceFactory().getStaffInfoService().saveStaffInfo(staffInfoBean);
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }

    public void changeLsaFlag(Integer userId, Integer groupId, Integer lsaFlag) {
        try {
            getServiceFactory().getLoginService().changeLsaFlag(userId, groupId, lsaFlag);
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

    }

    public ApproverResponse addApproverResponse(ApproverResponse toSave) {

        try {
            return getServiceFactory().getLoginService().addApproverResponse(toSave);
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }


    public boolean recoverPassword(final String email) {
        LoginBean loginBean = getBusinessFactory().getUserService().getLoginBean(email);

        if (ProcessData.isValid(loginBean) && loginBean.isEnabled()) {
            String emailAddress = loginBean.getEmail();
            final String password = loginBean.getPassword();
            final String mailText = new RegisterationEmailer()
                    .passwordRecoveryMail(password);
            try {
                getBusinessFactory().getMailBusiness().sendMail(emailAddress, null, null, "Password Recovery", mailText, null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return true;
        } else {
            return false;

        }
    }

    public BulkMailProcess saveBulkProcess(BulkMailProcess process) {

        return getServiceFactory().getLoginService().saveBulkProcess(process);
    }

    public BulkMailProcess getBulkProcess(Integer id) {

        return getServiceFactory().getLoginService().getBulkProcess(id);
    }

    public List<BulkMailProcess> getBulkMailProcesses() {
        return getServiceFactory().getLoginService().getBulkMailProcesses();
    }

    public boolean saveOrUpdateStaffDirectory(StaffInfoBean staffInfo) {
        UserToken token = AppThreadLocal.getTokenLocal();
        if (staffInfo.getId() != null && staffInfo.getUserId() != null) {
            Integer staffInfoId = staffInfo.getId();
            Integer userId = staffInfo.getUserId();
            StaffInfoBean dbStaffInfoBean = getStaffInfoBean(staffInfoId, userId);
            updateUserBean(staffInfo, dbStaffInfoBean);
            getServiceFactory().getLoginService().saveUser(dbStaffInfoBean.getUser());
            dbStaffInfoBean = updateStaffInfoBean(staffInfo, dbStaffInfoBean);
            getServiceFactory().getStaffInfoService().saveStaffInfo(dbStaffInfoBean);
            return true;
        } else {
            if (staffInfo.getUser() != null) {
                UserBean userBean = staffInfo.getUser();
                HospitalBean hospitalBean = getServiceFactory().getHospitalService().getHospitalBeanByGroupId(token.getCurrentGroupId());
                String timezone = hospitalBean.getTimezone();
                userBean.setCreatedDate(new Date());
                userBean.setTimezone(timezone);
                userBean.setUserCode(userBean.getEmail());
                getServiceFactory().getLoginService().saveUser(userBean);

                createUserGroupMapForStaff(token, userBean);

                staffInfo.setUserId(userBean.getUserId());
                getServiceFactory().getStaffInfoService().saveStaffInfo(staffInfo);
                return true;
            } else {
                return false;
            }
        }
    }

    private void updateUserBean(StaffInfoBean staffInfo, StaffInfoBean dbStaffInfoBean) {
        UserBean dbUserBean = dbStaffInfoBean.getUser();
        dbUserBean.setFirstName(staffInfo.getUser().getFirstName());
        dbUserBean.setLastName(staffInfo.getUser().getLastName());
        dbStaffInfoBean.setUser(dbUserBean);
    }

    private void createUserGroupMapForStaff(UserToken token, UserBean userBean) {
        UserGroupMap map = new UserGroupMap();
        map.setUserId(userBean.getUserId());
        map.setUser(userBean);
        map.setIsActive(1);
        map.setGroupId(token.getCurrentGroupId());
        map.setIsLSA(0);
        map.setRole(AppConstants.STAFF_ROLE);
        getServiceFactory().getGroupService().saveUserGroupMap(map);
    }

    public StaffInfoBean getStaffInfoBean(Integer id, Integer userId) {
        try {
            if (id != null && userId != null) {
                StaffInfoBean dbStaffInfoBean = getServiceFactory().getStaffInfoService().getStaffByIdAndUserId(id, userId);
                UserBean dbUserBean = getServiceFactory().getLoginService().getUserByUserId(userId);
                dbStaffInfoBean.setUser(dbUserBean);
                return dbStaffInfoBean;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public StaffInfoBean updateStaffInfoBean(StaffInfoBean staffInfo, StaffInfoBean dbStaffInfoBean) {
        if (staffInfo.getTitle() != null) {
            dbStaffInfoBean.setTitle(staffInfo.getTitle());
        }
        if (staffInfo.getMobile() != null) {
            dbStaffInfoBean.setMobile(staffInfo.getMobile());
        }
        if (staffInfo.getOfficePhNo() != null) {
            dbStaffInfoBean.setOfficePhNo(staffInfo.getOfficePhNo());
        }
        if (staffInfo.getAddress() != null) {
            dbStaffInfoBean.setAddress(staffInfo.getAddress());
        }
        if (staffInfo.getCity() != null) {
            dbStaffInfoBean.setCity(staffInfo.getCity());
        }
        if (staffInfo.getState() != null) {
            dbStaffInfoBean.setState(staffInfo.getState());
        }
        if (staffInfo.getSurgicalSpeciality() != null) {
            dbStaffInfoBean.setSurgicalSpeciality(staffInfo.getSurgicalSpeciality());
        }
        if (staffInfo.getSpecialAccrediation() != null) {
            dbStaffInfoBean.setSpecialAccrediation(staffInfo.getSpecialAccrediation());
        }
        if (staffInfo.getWebsite() != null) {
            dbStaffInfoBean.setWebsite(staffInfo.getWebsite());
        }
        if (staffInfo.getOfficeManager() != null) {
            dbStaffInfoBean.setOfficeManager(staffInfo.getOfficeManager());
        }
        if (staffInfo.getOfficeHrs() != null) {
            dbStaffInfoBean.setOfficeHrs(staffInfo.getOfficeHrs());
        }
        if (staffInfo.getNpi() != null) {
            dbStaffInfoBean.setNpi(staffInfo.getNpi());
        }
        if (staffInfo.getCoveringPhy() != null) {
            dbStaffInfoBean.setCoveringPhy(staffInfo.getCoveringPhy());
        }
        if (staffInfo.getAdditionalInfo() != null) {
            dbStaffInfoBean.setAdditionalInfo(staffInfo.getAdditionalInfo());
        }
        return dbStaffInfoBean;
    }

    public UserInfo getUserAsPerRoleForStaff(Integer userId, Integer groupId) {
        try {
            return getServiceFactory().getLoginService().getUserAsPerRoleForStaff(userId, groupId);
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }

}
