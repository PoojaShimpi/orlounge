<%--
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page import="org.orlounge.bean.UserBean" %>
<%@ page import="org.orlounge.bean.StaffInfoBean" %>
<%@ page import="org.orlounge.common.util.ProcessData" %>
<%@ page import="org.orlounge.common.*" %>

<script type="text/javascript">
    var msg = null;
    var success = true;

    <% if(request.getParameter("message") != null){
%>
    msg = '<%=request.getParameter("message")%>';
    success = <%=request.getParameter("success")%>;
    var json = {success : success, message: msg};
    <%
        }else if(request.getParameter("json") != null){
%>
    var json =<%=request.getParameter("json")%> ;
    <%
        }
            %>

</script>



<%
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
    int userId = Integer.parseInt(request.getAttribute("userId").toString());
    UserBean user = (UserBean) request.getAttribute("userObj");
    UserInfo info = (UserInfo) request.getAttribute("userInfo");
    StaffInfoBean staff = (StaffInfoBean) request.getAttribute("staffInfo");
    boolean isGuest = token.getRole().equals(AppConstants.GUEST_ROLE);
    boolean isView = isGuest || !user.getUserId().equals(token.getUserId()) ;
    boolean showLogout = false;
    if(session.getAttribute("showLogoutMessage")!= null){
        showLogout = Boolean.valueOf((Boolean) session.getAttribute("showLogoutMessage"));
    }

%>
<%--<section role="main" class="content-body">--%>

<div class="row">
<div class="col-md-3 col-lg-3">

    <section class="panel">
        <h3 class="panel-title">Profile Detail</h3>
        <div class="panel-body">
            <div class="thumb-info mb-md">
                <%
                    if(ProcessData.isValid(user.getHospitalBadgeId() != null)){
                        %>
                <figure class="figure"> <img src="getImageDocument?filePath=<%=user.getHospitalBadgeId()%>" class="rounded img-responsive" alt=""></figure>
                <%
                    }else {
                        %>
                <figure class="figure"><img src="resources/images/user.jpg" class="rounded img-responsive" alt=""></figure>
                <%
                    }
                %>

                <div class="thumb-info-detail">
                    <h5 class="bold cap"><%=user.getFirstName()+"  " + user.getLastName()%></h5>
                    <h6 class="lightbold"><%=RoleNameEnum.ROLE_NAME_MAP.get(info.getRole())%></h6>
                    <h6 class="lightbold"><%=info.getGroupName()%></h6>
                    <h6 class="lightbold"><%=user.getEmail()%></h6>
                    <h6 class="lightbold">STATUS : <%=info.getStatus()%></h6>
                   <%-- <span class="thumb-info-inner"></span>
                    <span class="thumb-info-type"></span>--%>
                </div>
            </div>

<%--            <div class="widget-toggle-expand mb-md">
                <div class="widget-header">
                    <h6></h6>
                    <div class="widget-toggle">+</div>
                </div>
                <div class="widget-content-collapsed">

                </div>
                <div class="widget-content-expanded">
                    <ul class="simple-todo-list">
                        <li></li>
                        <li></li>
                        <%--<li>Update Social Media</li>
                        <li>Follow Someone</li>
                    </ul>
                </div>
            </div>--%>


            <div class="progress progress-xs light">
                <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
                    100%
                </div>
            </div>
            <%--<hr class="dotted short">

            <h6 class="lightbold">About</h6>
            <p>This feature Coming Soon.</p>
            <div class="clearfix">
                <a class="text-uppercase text-muted pull-right" href="#">(View All)</a>
            </div>

            <hr class="dotted short">--%>


        </div>
    </section>

</div>
<div class="col-md-9 col-lg-9">
    <%
        if(showLogout){
            %>

    <div class="warn-header " style="padding-top: 5px;padding-bottom: 5px;margin-bottom: 15px;">
        <p >
            For Security Reasons, You may need to Login Again for reflecting changes. Please <a href="logout" style="color: #fff;text-decoration: underline;">Logout</a>
        </p>

    </div>
    <%
        }
    %>

        <div class="tabs">
        <ul class="nav nav-tabs tabs-primary">
            <li class="active">
                <a href="#edit"  data-toggle="tab">Staff Directory Info</a>
            </li>
                <li class="">
                    <a href="#userInfo"  data-toggle="tab">User Info</a>
                </li>
                <%
                    if(user.getUserId().equals(token.getUserId())){
                        %>
                <li class="">
                    <a href="#changePass"  data-toggle="tab">Change Password</a>
                </li>
                <%
                    }
                %>

        </ul>
        <div class="tab-content">

            <div id="edit" class="tab-pane active">

                <form action="saveStaffInfo" name="staffInfo" id="staffInfoForm" class="form-horizontal" method="POST" >
                    <h4 class="mb-xlg">Staff Directory Information</h4>
                    <input path="id" <%=isView ? "disabled='disabled'" : ""%> value="<%=staff.getId()%>" type="hidden" name="id" />
                    <input path="userId" <%=isView ? "disabled='disabled'" : ""%> value="<%=user.getUserId()%>" type="hidden" name="userId" />
                    <fieldset>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileFirstName">Title</label>
                            <div class="col-md-8">
                                <input data-plugin-maxlength path="title" maxlength="10" value="<%=StringUtil.getNotNullString(staff.getTitle()) %>" <%=isView ? "disabled='disabled'" : ""%> type="text" class="form-control" id="title" name="title">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileLastName">Mobile Ph No.</label>
                            <div class="col-md-8">
                                <input path="mobile" data-plugin-maxlength maxlength="25" type="text" value="<%=StringUtil.getNotNullString(staff.getMobile())%>" <%=isView ? "disabled='disabled'" : ""%>  class="form-control" id="mobile" name="mobile">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">Office Ph No.</label>
                            <div class="col-md-8">
                                <input path="officePhNo" data-plugin-maxlength maxlength="25" type="text" value="<%=StringUtil.getNotNullString(staff.getOfficePhNo())%>"  class="form-control" id="officePhNo" <%=isView ? "disabled='disabled'" : ""%> name="officePhNo">
                            </div>
                        </div>

                    </fieldset>
                    <hr class="dotted tall">
                    <h4 class="mb-xlg">More Information</h4>
                    <fieldset>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileBio">Address</label>
                            <div class="col-md-8">
                                <textarea path="address" data-plugin-maxlength maxlength="150" class="form-control" <%=isView ? "disabled='disabled'" : ""%> rows="3" name="address" id="address"><%=StringUtil.getNotNullString(staff.getAddress())%> </textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">State</label>
                            <div class="col-md-8">
                                <input path="state" data-plugin-maxlength maxlength="30" name="state" type="text" class="form-control" id="state" value="<%=StringUtil.getNotNullString(staff.getState())%>" <%=isView ? "disabled='disabled'" : ""%> >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">City</label>
                            <div class="col-md-8">
                                <input name="city" data-plugin-maxlength maxlength="30" path="city" type="text" class="form-control" id="city" value="<%=StringUtil.getNotNullString(staff.getCity())%>" <%=isView ? "disabled='disabled'" : ""%>/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileBio">Surgical Speciality Of Surgeon</label>
                            <div class="col-md-8">
                                <textarea name="surgicalSpeciality" data-plugin-maxlength maxlength="150" path="surgicalSpeciality" class="form-control" rows="3" id="surgicalSpeciality" <%=isView ? "disabled='disabled'" : ""%>><%=StringUtil.getNotNullString(staff.getSurgicalSpeciality())%></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">Special Accreditation if Any</label>
                            <div class="col-md-8">
                                <input path="specialAccrediation" data-plugin-maxlength maxlength="50" name="specialAccrediation" type="text" class="form-control" id="specialAccrediation" value="<%=StringUtil.getNotNullString(staff.getSpecialAccrediation())%>" <%=isView ? "disabled='disabled'" : ""%>>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">Website</label>
                            <div class="col-md-8">
                                <input path="website" data-plugin-maxlength maxlength="50" name="website" type="text" class="form-control" id="website" value="<%=StringUtil.getNotNullString(staff.getWebsite())%>" <%=isView ? "disabled='disabled'" : ""%>>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">Office Manager</label>
                            <div class="col-md-8">
                                <input path="officeManager" name="officeManager" type="text" data-plugin-maxlength maxlength="50" class="form-control" id="offMgr" value="<%=StringUtil.getNotNullString(staff.getOfficeManager())%>" <%=isView ? "disabled='disabled'" : ""%>>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">Office Hours</label>
                            <div class="col-md-8">
                                <input path="officeHrs" name="officeHrs" type="text" class="form-control" id="offHours" data-plugin-maxlength maxlength="30" value="<%=StringUtil.getNotNullString(staff.getOfficeHrs())%>" <%=isView ? "disabled='disabled'" : ""%>>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">NPI</label>
                            <div class="col-md-8">
                                <input path="npi" data-plugin-maxlength maxlength="30" name="npi" type="text" class="form-control" id="npi" value="<%=StringUtil.getNotNullString(staff.getNpi())%>" <%=isView ? "disabled='disabled'" : ""%>>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">Covering Physician If Any</label>
                            <div class="col-md-8">
                                <input path="coveringPhy" name="coveringPhy" data-plugin-maxlength maxlength="50" type="text" class="form-control" id="covPhy" value="<%=StringUtil.getNotNullString(staff.getCoveringPhy())%>" <%=isView ? "disabled='disabled'" : ""%>>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">Additional Info.</label>
                            <div class="col-md-8">
                                <textarea data-plugin-maxlength maxlength="150" path="additionalInfo" name="additionalInfo" class="form-control" rows="3" <%=isView ? "disabled='disabled'" : ""%> id="addInfo"><%=StringUtil.getNotNullString(staff.getAdditionalInfo())%></textarea>
                            </div>
                        </div>

                    </fieldset>
                    <div class="tab-pane-footer">
                        <div class="row">
                            <div class="col-md-6 col-md-offset-3">
                                <%if(!isView){
                                   %>
                                <button type="submit" class="btn btn-primary">Save</button>
                                <button type="reset" class="btn btn-default">Reset</button>
                                <%

                                }%>

                            </div>
                        </div>
                    </div>

                </form>

            </div>

            <div id="userInfo" class="tab-pane">

                <form action="#" class="form-horizontal" method="POST" >
                    <h4 class="mb-xlg">User Information</h4>
                    <fieldset>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileFirstName">FirstName</label>
                            <div class="col-md-8">
                                <input path="title" data-plugin-maxlength maxlength="25" value="<%=user.getFirstName()%>"  disabled='disabled' type="text" class="form-control"  name="title">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileLastName">Last Name</label>
                            <div class="col-md-8">
                                <input path="mobile" data-plugin-maxlength maxlength="25" type="text" value="<%=user.getLastName()%>" disabled='disabled' class="form-control" name="mobile">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">Email Id</label>
                            <div class="col-md-8">
                                <input path="officePhNo" data-plugin-maxlength maxlength="50" type="text" value="<%=user.getEmail()%>"  class="form-control" disabled='disabled' name="officePhNo">
                            </div>
                        </div>

                    </fieldset>

                </form>

            </div>

           <%
            if(user.getUserId().equals(token.getUserId()) && !isGuest){
                %>

           <div id="changePass" class="tab-pane">

               <form action="changePassword.html" name="changePass" id="changePassForm" class="form-horizontal" method="POST" >
                   <h4 class="mb-xlg">Change Password</h4>
                   <input name="userId" value="<%=token.getUserId()%>" type="hidden" name="id" class="form-control" />
                   <div class="form-group">
                       <label class="col-md-3 control-label" for="profileAddress">UserCode</label>
                       <div class="col-md-8">
                           <label class="col-md-3 control-label" for="profileAddress"><%=token.getUserCode()%></label>
                           <input type="hidden" name="userCode" value="<%=token.getUserCode()%>" class="form-control"   name="id" />
                       </div>
                   </div>
                   <div class="form-group">
                       <label class="col-md-3 control-label" for="profileAddress">Old Password</label>
                       <div class="col-md-8">
                           <input type="password" required name="oldPass"  class="form-control" data-plugin-maxlength maxlength="16" />
                       </div>
                   </div>
                   <div class="form-group">
                       <label class="col-md-3 control-label" for="profileAddress">New Password</label>
                       <div class="col-md-8">
                           <input type="password" required name="newPass" class="form-control" data-plugin-maxlength maxlength="16"  />
                       </div>
                   </div>
                   <div class="form-group">
                       <label class="col-md-3 control-label" for="profileAddress">Renter New Password</label>
                       <div class="col-md-8">
                           <input type="password" required name="confirmPass"  class="form-control" data-plugin-maxlength maxlength="16" />
                       </div>
                   </div>

                   <div class="tab-pane-footer">
                       <div class="row">
                           <div class="col-md-6 col-md-offset-3">
                           <button type="submit" class="btn btn-primary">Change</button>
                           <button type="reset" class="btn btn-default">Reset</button>
                       </div>
                   </div>
</div>
               </form>
           </div>

           <%
            }
           %>

        </div>
    </div>
</div>

</div>
<!-- end: page -->
<%--
</section>--%>
