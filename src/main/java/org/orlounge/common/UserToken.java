package org.orlounge.common;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.orlounge.common.AppConstants.*;

/**
 * Created by Satyam Soni on 9/18/2015.
 */

@Getter
@Setter
public class UserToken {

    private String userCode;
    private String email;

    private String firstName;
    private String lastName;

    private String userImage;

    private String timeZone;
    private Integer userId;
    private Integer loginId;
    private Integer currentGroupId;
    private String currentGroupName;
    private String currentGroupSize;
    private String ipAddress;
    private boolean isPassExpired = false;
    private String role;
    private boolean hasStaffInfo = false;
    private Integer eventId;
    private boolean isLSA;
    private boolean hasOrmAsLSA = false;
    private boolean hasAccessRestriction = false;
    private String alertLine = null;
    private List<VoteInfo> voteInfos = new ArrayList<>();
    private AccessRestrictionInfo restrictionInfo = new AccessRestrictionInfo();
    private List<GroupAccessMap> groupAccessList = new ArrayList<>();

    public String getCurrentGroupSize() {
        return currentGroupSize;
    }

    public void setCurrentGroupSize(String currentGroupSize) {
        this.currentGroupSize = currentGroupSize;
    }

    public List<VoteInfo> getVoteInfos() {
        return voteInfos;
    }

    public void setVoteInfos(List<VoteInfo> voteInfos) {
        this.voteInfos = voteInfos;
    }

    public String getAlertLine() {
        if (alertLine == null) {
            String txt = "<span style='padding:1px;background-color:green;margin-left:2px;margin-right:2px;border-radius:2px;color:white;font-weight:bold;'>Invite a friend to join this OR Lounge</span>";
            alertLine = isHasOrmAsLSA() ? "" : "<span  style='padding:1px;margin-left:2px;margin-right:2px;border-radius:2px;color:white;font-weight:bold;width:183px;font-size:11px;line-height:13px;'> This site has no permanent Local Site Administrator <span><a href='javascript:none(0)' > Why?</span></span>";

        }
        return alertLine;

    }

    public boolean isHasOrmAsLSA() {
        return hasOrmAsLSA;
    }

    public void setHasOrmAsLSA(boolean hasOrmAsLSA) {
        this.hasOrmAsLSA = hasOrmAsLSA;
        this.alertLine = null;
    }

    public boolean isGuest() {
        return GUEST_ROLE.equals(getRole());
    }

    public boolean isAdmin() {
        return ADMIN_ROLE.equals(getRole());
    }

    public boolean isAnaesthesiologist() {
        return ANESTHESIOLOGIST_ROLE.equals(getRole());
    }

    public boolean isOtherRole() {
        return !ANESTHESIOLOGIST_ROLE.equals(getRole())
                && !isAdmin()
                && !SURGEON_ROLE.equals(getRole())
                && !isLSA();
    }

    public boolean isDoctorRole() {
        return RESIDENT_ROLE.equals(getRole()) ||
               PULMONOLOGIST_ROLE.equals(getRole())||
                GASTROENTERLOGIST_ROLE.equals(getRole()) ||
                RADIOLOGIST_ROLE.equals(getRole()) ||
                OBSTERTRICIAN_ROLE.equals(getRole()) ||
                GYNECOLOGIST_ROLE.equals(getRole()) ||
                RESIDENT_ROLE.equals(getRole());
    }

    public boolean hasValidRole() {
        return RoleNameEnum.ROLE_NAME_MAP.keySet().contains(getRole());
    }

    public boolean isORM() {
        return ORM_ROLE.equals(getRole());
    }
    public boolean isSurgeon() {
        return SURGEON_ROLE.equals(getRole());
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public List<GroupAccessMap> getGroupAccessList() {
        return groupAccessList;
    }

    public void setGroupAccessList(List<GroupAccessMap> groupAccessList) {
        this.groupAccessList = groupAccessList;
    }

    public boolean isLSA() {
        return isLSA;
    }

    public void setLSA(boolean isLSA) {
        this.isLSA = isLSA;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public boolean isPassExpired() {
        return isPassExpired;
    }

    public void setPassExpired(boolean isPassExpired) {
        this.isPassExpired = isPassExpired;
    }

    public boolean isHasStaffInfo() {
        return hasStaffInfo;
    }

    public void setHasStaffInfo(boolean hasStaffInfo) {
        this.hasStaffInfo = hasStaffInfo;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public Integer getCurrentGroupId() {
        return currentGroupId;
    }

    public void setCurrentGroupId(Integer currentGroupId) {
        this.currentGroupId = currentGroupId;
    }

    public String getCurrentGroupName() {
        return currentGroupName;
    }

    public void setCurrentGroupName(String currentGroupName) {
        this.currentGroupName = currentGroupName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean getIsPassExpired() {
        return isPassExpired;
    }

    public void setIsPassExpired(boolean isPassExpired) {
        this.isPassExpired = isPassExpired;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public boolean isHavingAccessRestriction() {
        return hasAccessRestriction;
    }

    public void setHasAccessRestriction(boolean hasAccessRestriction) {
        this.hasAccessRestriction = hasAccessRestriction;
    }


    public AccessRestrictionInfo getRestrictionInfo() {
        return restrictionInfo;
    }

    public UserToken addRestriction(DeviceType type, Restriction restriction) {
        restrictionInfo.set(type, restriction);
        return this;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "userCode='" + userCode + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userImage='" + userImage + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", userId=" + userId +
                ", loginId=" + loginId +
                ", currentGroupId=" + currentGroupId +
                ", currentGroupName='" + currentGroupName + '\'' +
                ", currentGroupSize='" + currentGroupSize + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", isPassExpired=" + isPassExpired +
                ", role='" + role + '\'' +
                ", hasStaffInfo=" + hasStaffInfo +
                ", eventId=" + eventId +
                ", isLSA=" + isLSA +
                ", hasOrmAsLSA=" + hasOrmAsLSA +
                ", alertLine='" + alertLine + '\'' +
                ", voteInfos=" + voteInfos +
                ", groupAccessList=" + groupAccessList +
                '}';
    }

    public static enum DeviceType {
        ALL("ALL"), TABLET("TABLET"), MOBILE("MOBILE"), OTHER("OTHER");
        private String type;

        DeviceType(String type) {
            this.type = type;
        }
    }

    public static class GroupAccessMap {

        private GroupInfo groupInfo;
        private String role;
        private boolean isLSA;
        private boolean isLastAccessGroup;

        public GroupAccessMap(GroupInfo groupInfo, String role, boolean isLSA, boolean isLastAccessGroup) {
            this.groupInfo = groupInfo;
            this.role = role;
            this.isLSA = isLSA;
            this.isLastAccessGroup = isLastAccessGroup;
        }

        public GroupInfo getGroupInfo() {
            return groupInfo;
        }

        public void setGroupInfo(GroupInfo groupInfo) {
            this.groupInfo = groupInfo;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public boolean isLSA() {
            return isLSA;
        }

        public void setLSA(boolean isLSA) {
            this.isLSA = isLSA;
        }

        public boolean isLastAccessGroup() {
            return isLastAccessGroup;
        }

        public void setLastAccessGroup(boolean isLastAccessGroup) {
            this.isLastAccessGroup = isLastAccessGroup;
        }
    }

    public static class GroupInfo {


        private Integer groupId;
        private String groupName;
        private boolean isTrial = true;
        private boolean isCommon;


        public GroupInfo(Integer groupId, String groupName, boolean isTrial, boolean isCommon) {
            this.groupId = groupId;
            this.groupName = groupName;
            this.isTrial = isTrial;
            this.isCommon = isCommon;
        }

        public Integer getGroupId() {
            return groupId;
        }

        public void setGroupId(Integer groupId) {
            this.groupId = groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public boolean isTrial() {
            return isTrial;
        }

        public void setTrial(boolean isTrial) {
            this.isTrial = isTrial;
        }

        public boolean isCommon() {
            return isCommon;
        }

        public void setCommon(boolean isCommon) {
            this.isCommon = isCommon;
        }
    }

    public static class VoteInfo {
        private Integer id;
        private String text;
        private Integer num = 0;


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }
    }

    public static class AccessRestrictionInfo {
        private ConcurrentHashMap<DeviceType, List<Restriction>> data = new ConcurrentHashMap<>();


        public ConcurrentHashMap<DeviceType, List<Restriction>> getData() {
            return data;
        }

        public List<Restriction> getRestrictionsForDevice(DeviceType deviceType) {
            return data.get(deviceType);
        }

        public void set(DeviceType deviceType, Restriction restriction) {
            if (data.get(deviceType) == null) {
                data.put(deviceType, new ArrayList<>());
            }
            data.get(deviceType).add(restriction);
        }

    }

    public static class Coord {
        private Double lat;
        private Double lon;

        public Coord(Double lat, Double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public Double getLat() {
            return lat;
        }

        public Double getLon() {
            return lon;
        }
    }

    public static class Restriction {
        private String base;
        private float radius;
        private String key;
        private Set<Coord> coordinates;

        public Restriction(String base, float radius, Set<Coord> coordinates, String key) {
            this.base = base;
            this.radius = radius;
            this.coordinates = coordinates;
            this.key = key;
        }

        public Restriction() {
        }

        public String getBase() {
            return base;
        }

        public void setBase(String base) {
            this.base = base;
        }

        public float getRadius() {
            return radius;
        }

        public void setRadius(float radius) {
            this.radius = radius;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Set<Coord> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(Set<Coord> coordinates) {
            this.coordinates = coordinates;
        }
    }

}
