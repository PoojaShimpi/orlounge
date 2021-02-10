package org.orlounge.common;

import org.orlounge.common.util.DateContent;

import java.util.Date;

/**
 * Created by Satyam Soni on 9/19/2015.
 */
public interface   UserInfo {
/*
    private Integer userId;
    private Integer userLoginId;
    private String userCode;
    private String email;
    private String role;
    private String groupName;
    private Integer grpId;
    private Integer statusId;
    private String status;
    private String dateStr;
    private Date date;
    private String firstName;
    private String lastName;*/

    public Date getDate();


    public Integer getUserId() ;

    public Integer getUserLoginId() ;

    public String getUserCode();

    public String getEmail();


    public String getRole() ;


    public String getGroupName() ;

    public Integer getGrpId();


    public Integer getStatusId();

    public String getStatus();

    default String getDateStr() {
        String timeZone = AppThreadLocal.getTokenLocal().getTimeZone();
        Date dt = DateContent.convertDBDateToClientDate( getDate(), timeZone);
        return DateContent.formatDateIntoString(dt,null);
    }


    public String getFirstName();


    public String getLastName();

    public Integer getLsa();



}
