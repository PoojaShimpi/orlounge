<%@ page import="org.orlounge.common.AppConstants" %>
<%@ page import="org.orlounge.common.GroupedHospital" %>
<%@ page import="org.orlounge.common.HospitalType" %>
<%@ page import="org.orlounge.common.RoleNameEnum" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<script type="text/javascript">
    var json = null;
    <%
     if (session.getAttribute("json")!= null){
     %>
    json = <%=session.getAttribute("json")%>;
    <%
        session.removeAttribute("json");
     }
    %>
</script>
<!-- <script src="jquery-mask-as-number.js"></script> -->

<div class="">
    <sec:authorize access="isAnonymous()">
        <section class="panel form-wizard register-form" id="w5">

            <h2 class="panel-title">Join Your Lounge Site.</h2>

            <div class="panel-body">
                <div class="wizard-tabs hidden">
                    <ul class="wizard-steps">
                        <li class="active">
                            <a href="#w5-account" data-toggle="tab"><span class="badge">1</span>Account Info</a>
                        </li>
                        <li>
                            <a href="#w5-profile" data-toggle="tab"><span class="badge">2</span>Profile Info</a>
                        </li>
                    </ul>
                </div>
                <div class="progress-steps" style="display:none">
                    <ul class="progress-list">
                        <li class="active progress-line">
                            <span class="progress-number">1</span>
                            <span class="progress-info">Personal Details</span>
                        </li>
                        <li>
                            <span class="progress-number">2</span>
                            <span class="progress-info">Select Appropriate</span>
                        </li>
                    </ul>
                </div>
               <form enctype="multipart/form-data" action="registerUser" method="POST" class="form-horizontal"
                      id="register-form"
                      novalidate="novalidate">
                    <div class="tab-content">
                        <!-- first step starts-->
                        <div class="progress-one">
                            <p class="progress-desc text-center">ORLOUNGE is for people who work in the operating room.
                                Only those who ae eligible should join. We follow a strict verification procedure.</p>

                            <div class="row progress-form">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="">First Name :</label>
                                        <input type="text" name="firstName" class="form-control"
                                               placeholder="First Name"
                                               required>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="">Last Name :</label>
                                        <input type="text" name="lastName" class="form-control" placeholder="Last Name"
                                               required>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="">Email :</label>
                                        <input type="email" name="email" id="email" class="form-control"
                                               onblur="checkEmail()"
                                               placeholder="Email" required>

                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="">Password:</label>
                                        <input type="password" name="password" class="form-control"
                                               placeholder="Password"
                                               required>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <p class="font-size-11">On entering the telephone number, you will receive a 6 digit
                                        passcode on your mobile device to verify that you
                                        own the mobile device.</p>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                            <%--<label class="">Hospital phone no :</label>--%>
                                        <input id="hospital-phone" type="text" name="hospitalPhNo" class="form-control"
                                               data-plugin-masked-input data-input-mask="(999) 999-9999"
                                               placeholder="Mobile telephone"
                                               onblur="onHospitalNoBlur()" required>
                                    </div>
                                </div>
                                <div class="col-md-6">

                                    <div class="form-group">
                                            <%--<label class="">Hospital phone no :</label>--%>
                                        <div class="row">
                                            <div class="col-md-8">
                                                <input id="otp" type="text" name="otp" disabled class="form-control"
                                                       maxlength="6" placeholder="Enter the 6 digit code here"
                                                       data-plugin-masked-input
                                                       data-input-mask="9999999" required onblur="verifyOtp(event)">
                                            </div>
                                            <div class="col-md-4">
                                                <a class="verify-link btn btn-success disabled" id="verify" onclick="verifyOtp(event)" style="cursor: pointer;">Verify</a>
                                                <span class="hidden fa fa-check-circle " id="verified"></span>
                                            </div>
                                        </div>

                                        <div class="col-md-12">
                                            Did not receive OTP? <a class="alert-link"  onclick="onHospitalNoBlur()" disabled style="cursor: pointer;">Resend</a>
                                        </div>

                                    </div>


                                </div>

                            </div>
                        </div>
                        <!-- first step ends here -->
                        <!-- second step starts-->
                        <div class="progress-two">
                            <div class="row progress-form-2">
                                <div class="col-md-12">
                                    <div class="progress-select">
                                        <div class="progress-select-block">
                                            <div class="progress-select-desc">Please select your hospital</div>
                                            <div class="progress-select-wrap">
                                                <div class="progress-select-block-wrap">
                                                    <label class="control control--radio">
                                                        <input type="radio" name="radio" value="hospital"
                                                               onchange="radioButtonChange('hospital')"
                                                               checked="checked"/>

                                                        <div class="control__indicator"></div>
                                                        <div class="control-info">Hospital</div>
                                                    </label>
                                                    <label class="control control--radio">
                                                        <input type="radio" value="ambulatory_center"
                                                               onchange="radioButtonChange('ambulatory_center')"
                                                               name="radio"/>

                                                        <div class="control__indicator"></div>
                                                        <div class="control-info">Ambulatory Center</div>
                                                    </label>
                                                </div>
                                                <select class="progress-select-box hospital" id="hospital"
                                                        name="hospitalId">
                                                    <%
                                                        List<GroupedHospital> hospitals = (List<GroupedHospital>) request.getAttribute("hospitals");
                                                        for (GroupedHospital each : hospitals) {
                                                    %>
                                                    <optgroup
                                                            label="<%=each.getStateName()%> - <%=each.getStateCode()%>">

                                                                <%
                        List<GroupedHospital.HospitalInfo> list = each.getList();
                        for(GroupedHospital.HospitalInfo hos : list){
                                    if(hos.getHospitalType().equalsIgnoreCase(HospitalType.HOSPITAL.getDispName())){
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                %>

                                                        <option value="<%=hos.getHospitalId()%>">
                                                            <%=hos.getHospitalName()%>
                                                        </option>
                                                                <%
                                }
                        }
                    }
                %>
                                                </select>

                                                <select id="ambituary" name="hospitalId"
                                                        class="progress-select-box hospital isActiveAmbultary                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  isActiveAmbultary">
                                                    <%
                                                        List<GroupedHospital> ascs = (List<GroupedHospital>) request.getAttribute("hospitals");
                                                        for (GroupedHospital each : ascs) {
                                                    %>
                                                    <optgroup
                                                            label="<%=each.getStateName()%> - <%=each.getStateCode()%>">

                                                                <%
                            List<GroupedHospital.HospitalInfo> list = each.getList();
                            for(GroupedHospital.HospitalInfo hos : list){
                                        if(hos.getHospitalType().equalsIgnoreCase(HospitalType.AMBULATORY_CENTER.getDispName())){
                                    %>
                                                        <option value="<%=hos.getHospitalId()%>">
                                                            <%=hos.getHospitalName()%>
                                                        </option>
                                                                <%
                                    }
                            }
                        }
                    %>

                                                </select>
                                                <p class="progress-mail">Cannot find your hospital? Send us mail to <a
                                                        href="#">notification@orlounge.com</a>
                                                </p>
                                                <div class="row">
                                                    <div class="col-md-12  mb-0" >
                                                      <div class="form-group mb-0">
                                                                                   <label for="Size of Operating Room *" class="role-label">Size of Operating Room *</label>
                                                                                                        <select name="sizeOfOR" id="sizeOfOR" class="progress-select-box" required>
                                                                                                         <option value="Small">Small (1-5 beds)</option>
                                                                                                         <option value="Medium">Medium (6-10 beds)</option>
                                                                                                         <option value="Large">Large (More than 10 beds)</option>

                                                                                                         </select></div>
                                                            </div>
                                                </div>


                                                <div class="row">
                                                    <div class="col-md-12 progress-choose-file">
                                                        <label>Hospital Badge Photo:</label>
                                                        <input type="file" name="hospitalBadge" id="badgeId"
                                                               class="form-control progress-file"
                                                               required>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                        <%--<p class="progress-mail">Cannot find your hospital? Send us mail to <a href="#">notification@orlounge.com</a>--%>
                                        <%--</p>--%>

                                    <div class="col-md-12 mb-0">
                                        <div class="form-group mb-0">
                                            <label class="role-label">Your role in hospital *</label>
                                            <select class="progress-select-box" id="qrRole" name="role">
                                                <%
                                                    Set<Map.Entry<String, String>> roles = RoleNameEnum.ROLE_NAME_MAP.entrySet();
                                                    for(Map.Entry<String,String> role: roles){
                                                        boolean selected = (role.getKey().equals(AppConstants.ORM_ROLE));
                                                      if(!selected){
                                                 %>
                                                <option  value="<%=role.getKey()%>"><%=role.getValue()%></option>
                                                <%
                                                        } else {
                                                %>
                                                <option selected  value="<%=role.getKey()%>"><%=role.getValue()%></option>
                                                <%
                                                        }
                                                    }
                                                %>


                                            </select>
                                        </div>
                                    </div>
                            <div>
                                 What you want to do? *<br>
                                        <input type="radio" id="create" name="hospitalCreateOrJoin" value="Create">
                                          <label for="create">Create</label><br>
                                          <input type="radio" id="join" name="hospitalCreateOrJoin" value="Join">
                                          <label for="join">Join</label><br>

                              </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="progress-robot">
                                                <div class="g-recaptcha"
                                                     data-sitekey="6Le9MqIUAAAAAA4JUeqGbaXRYIrpp5aWR6iyPT89"></div>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="progress-terms">
                                                <input type="checkbox" class="progress-term-check" required>
                                                <label> &nbsp; I agree to the terms of service</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer border-none">
                            <ul class="pager">
                                <li class="validate">
                                    <input type="submit" class="btn submit-btn" id="validate" value="Submit">
                                    <!--<a class="readmore modal-with-move-anim" >Submit</a>-->
                                </li>
                            </ul>
                        </div>
                </form>


            </div>
 <div id="modalreadmore1" class="zoom-anim-dialog modal-block modal-block-primary mfp-hide readmore-panel">
            <form action="/registerUser">
            What you want to do?
            <input type="radio" id="create" name="create" value="Create">
              <label for="create">Create</label><br>
              <input type="radio" id="join" name="join" value="Join">
              <label for="join">Join</label><br>
  <input type="submit" value="Submit">
</form>

            </div>

        </section>
    </sec:authorize>
</div>