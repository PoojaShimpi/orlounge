package org.orlounge.service;

import org.orlounge.bean.EspsBillInfo;
import org.orlounge.bean.GeoLocation;
import org.orlounge.bean.GroupBean;
import org.orlounge.bean.UserGroupMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Satyam Soni on 9/19/2015.
 */
@Service
@Transactional
public class GroupService extends AbstractBaseService {

    public List<GroupBean> getAllGroups() {
        return getDaoFactory().getGroupDAO().findAll();
    }

    public GroupBean getGroupByHospitalId(Integer hospitalId) {
        return getDaoFactory().getGroupDAO().findByHospitalId(hospitalId);
    }

  /*  public Map<String,String> getStaticContent(){
        return getDaoFactory().getGroupDAO().getStaticContent();
    }
    public void saveStaticContent(String contentId , String content){
        getDaoFactory().getGroupDAO().saveStaticContent(contentId, content);
    }*/

    public GroupBean saveGroup(GroupBean groupBean) {
        return getDaoFactory().getGroupDAO().save(groupBean);
    }

    public UserGroupMap saveUserGroupMap(UserGroupMap map) {
        return getDaoFactory().getUserGroupMapDAO().save(map);
    }

    public List<UserGroupMap> getGroupsByUser(Integer userid) {
        return getDaoFactory().getUserGroupMapDAO().getGroupsByUser(userid);

    }

    public List<UserGroupMap> getGroupsByUserIncludingInActive(Integer userid) {
        return getDaoFactory().getUserGroupMapDAO().getGroupsByUserIncludingInActive(userid);

    }

    public boolean hasGroupLsaAsRole(String role, Integer groupId) {
        return !getDaoFactory().getUserGroupMapDAO().getGroupLsaAsRole(role, groupId).isEmpty();

    }

    public Integer getLastAccessGroupIdForUser(Integer userid) {
        return getDaoFactory().getLastAccessGroupDAO().getLastAccessGroupIdByUser(userid).size();

    }

    public List<GroupBean>  getGroupByUserForAction(Integer userid) {
        return getDaoFactory().getGroupDAO().getGroupByUserForAction(userid);
    }

    public GroupBean getGroupByGrpId(Integer id) {
        return getDaoFactory().getGroupDAO().findById(id).get();

    }

    public UserGroupMap getGroupUserMap(Integer userId, Integer grpId) {
        return getDaoFactory().getUserGroupMapDAO().findByUserIdAndGroupId(userId, grpId);

    }

    public List<GroupBean> fetchCommonGroup() {
        return getDaoFactory().getGroupDAO().fetchCommonGroup();
    }

    public Integer updateLastAccessGroupIdByUser(Integer userId, Integer groupId) {
        return getDaoFactory().getLastAccessGroupDAO().updateLastAccessGroupIdByUser(userId, groupId);
    }
    public List<GeoLocation> getGeoActiveLocationsForGroup(Integer group){
        return getDaoFactory().getGeoLocationDAO().getGeoActiveLocationsForGroup(group);
    }

    public List<GeoLocation> getGeoAllLocations(){
        return getDaoFactory().getGeoLocationDAO().getGeoAllLocations();

    }

    public GeoLocation getGeoLocation(Integer id){
        return getDaoFactory().getGeoLocationDAO().getGeoLocation(id);

    }

    public EspsBillInfo getEspsBillInfoByGroupId(Integer groupId){
        return getDaoFactory().getEspsBillInfoDAO().getEspsBillInfo(groupId);

    }

    public List<EspsBillInfo> getAllEspsBillInfo(){
        return getDaoFactory().getEspsBillInfoDAO().findAll();
    }

    public GeoLocation saveGeoLocation(GeoLocation bean){
        return getDaoFactory().getGeoLocationDAO().save(bean);

    }

    public EspsBillInfo saveEspsInfo(EspsBillInfo bean){
        return getDaoFactory().getEspsBillInfoDAO().save(bean);

    }

    public GeoLocation deleteGeoLocation(GeoLocation bean){
        return getDaoFactory().getGeoLocationDAO().deleteGeoLocation(bean);
    }

    public UserGroupMap checkEmailIdByRoleAndHospitalId(String emailId, String role, Integer hospitalId) {
        return getDaoFactory().getUserGroupMapDAO().checkEmailIdByRoleAndHospitalId(emailId, hospitalId);
    }

    public GroupBean getGroupByGrpName(String name) {
        return getDaoFactory().getGroupDAO().findByGroupName(name);
    }
}
