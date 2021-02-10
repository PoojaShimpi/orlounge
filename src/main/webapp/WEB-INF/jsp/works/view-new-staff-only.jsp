<%@ page import="org.orlounge.common.RoleNameEnum" %>
<%@ page import="org.orlounge.bean.StaffInfoBean" %>
<%@ page import="org.orlounge.common.UserInfo" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.common.util.ProcessData" %>
<%
    StaffInfoBean bean = (StaffInfoBean) request.getAttribute("staffInfo");
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
    boolean isView = true; //token.isAdmin() || token.isLSA();
    UserInfo info = (UserInfo) request.getAttribute("userInfo");
%>
<div class="row">
    <div class="col-md-3 col-lg-3">

        <section class="panel">
            <h3 class="panel-title">Profile Detail</h3>
            <div class="panel-body">
                <div class="thumb-info mb-md">
                    <%
                        if (ProcessData.isValid(bean.getUser()) && ProcessData.isValid(bean.getUser().getHospitalBadgeId())) {
                    %>
                    <figure class="figure"><img src="getImageDocument?filePath=<%=bean.getUser().getHospitalBadgeId()%>"
                                                class="rounded img-responsive" alt=""></figure>
                    <%
                    } else {
                    %>
                    <figure class="figure"><img src="resources/images/user.jpg" class="rounded img-responsive" alt="">
                    </figure>
                    <%
                        }
                    %>

                    <div class="thumb-info-detail">
                        <h5 class="bold cap"><%=bean.getUser().getFirstName() + "  " + bean.getUser().getLastName()%>
                        </h5>
                        <%
                            if (info != null && info.getRole() != null) {%>
                        <h6 class="lightbold"><%=RoleNameEnum.ROLE_NAME_MAP.get(info.getRole())%>
                        </h6>


                        <%
                            }
                            if (info != null && info.getGroupName() != null) { %>
                        <h6 class="lightbold"><%=info.getGroupName()%>
                        </h6>

                        <% }
                        %>


                        <h6 class="lightbold"><%=bean.getUser().getEmail()%>
                        </h6>
                        <%
                            if (info != null && info.getStatus() != null) {%>
                        <h6 class="lightbold">STATUS : <%=info.getStatus()%>
                        </h6>
                        <%
                            }
                        %>
                    </div>
                </div>


                <div class="progress progress-xs light">
                    <div class="progress-bar" role="progressbar" aria-valuenow="100" aria-valuemin="0"
                         aria-valuemax="100" style="width: 100%;">
                        100%
                    </div>
                </div>
            </div>
        </section>

    </div>
    <div class="col-md-9 col-lg-9">


        <div class="tabs">
            <ul class="nav nav-tabs tabs-primary">
                <li class="active" id="staff-directory-tab">
                    <a href="#edit" role="tab" data-toggle="tab">Staff Directory Info</a>
                </li>
                <li id="user-info-tab">
                    <a href="#userInfo" data-toggle="tab">User Info</a>
                </li>


            </ul>

            <div class="tab-content">
                <div id="edit" class="tab-pane active">
                    <h4 class="mb-xlg">Staff Directory Information</h4>
                    <fieldset>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileFirstName">Title</label>
                            <div class="col-md-8">
                                <input data-plugin-maxlength path="title" maxlength="10"
                                       disabled='disabled' type="text" class="form-control"
                                       id="title" name="title"
                                       value="<%=bean.getTitle() != null? bean.getTitle() : ""  %>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileLastName">Mobile Ph No.</label>
                            <div class="col-md-8">
                                <input path="mobile" data-plugin-maxlength maxlength="25"
                                       type="text" disabled='disabled'
                                       class="form-control" id="mobile" name="mobile"
                                       value="<%=bean.getMobile() != null? bean.getMobile() : ""  %> ">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">Office Ph No.</label>
                            <div class="col-md-8">
                                <input path="officePhNo" data-plugin-maxlength maxlength="25"
                                       type="text" disabled='disabled'
                                       class="form-control" id="officePhNo"
                                       name="officePhNo"
                                       value="<%=bean.getOfficePhNo() != null? bean.getOfficePhNo() : ""  %> ">
                            </div>
                        </div>

                    </fieldset>
                    <hr class="dotted tall">
                    <h4 class="mb-xlg">More Information</h4>
                    <fieldset>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">Address</label>
                            <div class="col-md-8">
                                    <textarea path="address" data-plugin-maxlength maxlength="150"
                                              class="form-control" rows="3"
                                              name="address"
                                              id="address"
                                              disabled='disabled'> <%=bean.getAddress() != null ? bean.getAddress() : ""  %> </textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileState">State</label>
                            <div class="col-md-8">
                                <input path="state" data-plugin-maxlength maxlength="30" name="state" type="text"
                                       class="form-control" id="state" disabled='disabled'
                                       value="<%=bean.getState() != null ? bean.getState() : ""  %>"
                                />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">City</label>
                            <div class="col-md-8">
                                <input name="city" data-plugin-maxlength maxlength="30" path="city" type="text"
                                       class="form-control" id="city" disabled='disabled'
                                       value="<%=bean.getCity() != null ? bean.getCity() : ""  %>"
                                />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileBio">Surgical Speciality Of
                                Surgeon</label>
                            <div class="col-md-8">
                                    <textarea name="surgicalSpeciality" data-plugin-maxlength maxlength="150"
                                              path="surgicalSpeciality" class="form-control"
                                              rows="3" disabled='disabled'
                                              id="surgicalSpeciality"><%=bean.getSurgicalSpeciality() != null ? bean.getSurgicalSpeciality() : ""  %></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">Special Accreditation if
                                Any</label>
                            <div class="col-md-8">
                                <input path="specialAccrediation" data-plugin-maxlength maxlength="50"
                                       name="specialAccrediation" type="text"
                                       class="form-control" disabled='disabled'
                                       id="specialAccrediation"
                                       value="<%=bean.getSpecialAccrediation() != null ? bean.getSpecialAccrediation() : ""  %>"
                                />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">Website</label>
                            <div class="col-md-8">
                                <input path="website" data-plugin-maxlength maxlength="50" name="website"
                                       type="text" class="form-control"
                                       id="website" disabled='disabled'
                                       value="<%=bean.getWebsite() != null ? bean.getWebsite() : ""  %>"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">Office Manager</label>
                            <div class="col-md-8">
                                <input path="officeManager" name="officeManager" type="text" data-plugin-maxlength
                                       maxlength="50" class="form-control"
                                       id="offMgr" disabled='disabled'
                                       value="<%=bean.getOfficeManager() != null ? bean.getOfficeManager() : ""  %>"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">Office Hours</label>
                            <div class="col-md-8">
                                <input path="officeHrs" name="officeHrs" type="text" class="form-control"
                                       id="offHours" data-plugin-maxlength
                                       maxlength="30" disabled='disabled'
                                       value="<%=bean.getOfficeHrs() != null ? bean.getOfficeHrs() : ""  %>"
                                />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">NPI</label>
                            <div class="col-md-8">
                                <input path="npi" data-plugin-maxlength maxlength="30" name="npi" type="text"
                                       class="form-control" id="npi" disabled='disabled'
                                       value="<%=bean.getNpi() != null ? bean.getNpi() : ""  %>"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">Covering Physician If
                                Any</label>
                            <div class="col-md-8">
                                <input path="coveringPhy" name="coveringPhy" data-plugin-maxlength maxlength="50"
                                       type="text" class="form-control"
                                       id="covPhy" disabled='disabled'
                                       value="<%=bean.getCoveringPhy() != null ? bean.getCoveringPhy() : ""  %>"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileAddress">Additional Info.</label>
                            <div class="col-md-8">
                                    <textarea data-plugin-maxlength maxlength="150" path="additionalInfo"
                                              name="additionalInfo" class="form-control"
                                              rows="3" disabled='disabled'
                                              id="addInfo"><%=bean.getAdditionalInfo() != null ? bean.getAdditionalInfo() : ""  %></textarea>
                            </div>
                        </div>


                    </fieldset>
                </div>

                <div id="userInfo" class="tab-pane">


                    <h4 class="mb-xlg">User Information</h4>
                    <fieldset>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileFirstName">FirstName</label>
                            <div class="col-md-8">
                                <input path="user.firstName" data-plugin-maxlength maxlength="25"
                                       type="text" disabled='disabled'
                                       class="form-control" name="user.firstName" id="firstname"
                                       value="<%=bean.getUser()!=null && bean.getUser().getFirstName() != null ? bean.getUser().getFirstName() : ""  %>"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="profileLastName">Last Name</label>
                            <div class="col-md-8">
                                <input path="user.lastName" data-plugin-maxlength maxlength="25" type="text"
                                       class="form-control" disabled='disabled'
                                       name="user.lastName" id="lastname"
                                       value="<%= bean.getUser() !=null && bean.getUser().getLastName() != null ? bean.getUser().getLastName() : ""  %>"/>
                            </div>
                        </div>
                        <div class=" form-group">
                            <label class="col-md-3 control-label" for="profileAddress">Email Id</label>
                            <div class="col-md-8">

                                <input path="user.email" data-plugin-maxlength maxlength="50" type="text"
                                       class="form-control" disabled='disabled' id="email"
                                       name="user.email" value="<%=bean.getUser().getEmail()%>"/>


                            </div>
                        </div>


                    </fieldset>
                </div>
            </div>

        </div>
    </div>

</div>
