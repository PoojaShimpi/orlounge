<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="org.orlounge.common.AppConstants" %>
<%@ page import="org.orlounge.common.RoleNameEnum" %>
<div class="row">
    <div class="col-md-9 col-lg-9">


        <div class="tabs">
            <ul class="nav nav-tabs tabs-primary">
                <li class="active" id="staff-directory-tab">
                    <a href="#edit" role="tab" data-toggle="tab">Staff Directory Info</a>
                </li>
                <li class="disabled" id="user-info-tab">
                    <a href="#userInfo">User Info</a>
                </li>


            </ul>
            <form action="saveOrUpdateStaffDirectory" name="staffInfo" id="staffInfoForm" class="form-horizontal"
                  method="POST">


                <div class="tab-content">
                    <div id="edit" class="tab-pane active">
                        <h4 class="mb-xlg">Staff Directory Information</h4>
                        <fieldset>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="profileFirstName">Title</label>
                                <div class="col-md-8">
                                    <input data-plugin-maxlength path="title" maxlength="10"
                                           type="text" class="form-control"
                                           id="title" name="title"
                                    >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="profileLastName">Mobile Ph No.</label>
                                <div class="col-md-8">
                                    <input path="mobile" data-plugin-maxlength maxlength="25"
                                           type="text"
                                           class="form-control" id="mobile" name="mobile"
                                    />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="profileAddress">Office Ph No.</label>
                                <div class="col-md-8">
                                    <input path="officePhNo" data-plugin-maxlength maxlength="25"
                                           type="text"
                                           class="form-control" id="officePhNo"
                                           name="officePhNo"
                                    />
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
                                              id="address"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="profileState">State</label>
                                <div class="col-md-8">
                                    <input path="state" data-plugin-maxlength maxlength="30" name="state" type="text"
                                           class="form-control" id="state"
                                    />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="profileAddress">City</label>
                                <div class="col-md-8">
                                    <input name="city" data-plugin-maxlength maxlength="30" path="city" type="text"
                                           class="form-control" id="city"
                                    />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="profileBio">Surgical Speciality Of
                                    Surgeon</label>
                                <div class="col-md-8">
                                    <textarea name="surgicalSpeciality" data-plugin-maxlength maxlength="150"
                                              path="surgicalSpeciality" class="form-control"
                                              rows="3"
                                              id="surgicalSpeciality"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="profileAddress">Special Accreditation if
                                    Any</label>
                                <div class="col-md-8">
                                    <input path="specialAccrediation" data-plugin-maxlength maxlength="50"
                                           name="specialAccrediation" type="text"
                                           class="form-control"
                                           id="specialAccrediation"

                                    />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="profileAddress">Website</label>
                                <div class="col-md-8">
                                    <input path="website" data-plugin-maxlength maxlength="50" name="website"
                                           type="text" class="form-control"
                                           id="website"
                                    />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="profileAddress">Office Manager</label>
                                <div class="col-md-8">
                                    <input path="officeManager" name="officeManager" type="text" data-plugin-maxlength
                                           maxlength="50" class="form-control"
                                           id="offMgr"
                                    />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="profileAddress">Office Hours</label>
                                <div class="col-md-8">
                                    <input path="officeHrs" name="officeHrs" type="text" class="form-control"
                                           id="offHours" data-plugin-maxlength
                                           maxlength="30"

                                    />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="profileAddress">NPI</label>
                                <div class="col-md-8">
                                    <input path="npi" data-plugin-maxlength maxlength="30" name="npi" type="text"
                                           class="form-control" id="npi"
                                    />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="profileAddress">Covering Physician If
                                    Any</label>
                                <div class="col-md-8">
                                    <input path="coveringPhy" name="coveringPhy" data-plugin-maxlength maxlength="50"
                                           type="text" class="form-control"
                                           id="covPhy"
                                    />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="profileAddress">Additional Info.</label>
                                <div class="col-md-8">
                                    <textarea data-plugin-maxlength maxlength="150" path="additionalInfo"
                                              name="additionalInfo" class="form-control"
                                              rows="3"
                                              id="addInfo"></textarea>
                                </div>
                            </div>

                            <div class="tab-pane-footer">
                                <div class="row">
                                    <div class="col-md-6 col-md-offset-3">
                                        <button type="button" class="btn btn-primary" onclick="return validateStep1();">
                                            Next
                                        </button>
                                    </div>
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
                                           type="text"
                                           class="form-control" name="user.firstName" id="firstname"
                                    />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="profileLastName">Last Name</label>
                                <div class="col-md-8">
                                    <input path="user.lastName" data-plugin-maxlength maxlength="25" type="text"
                                           class="form-control"
                                           name="user.lastName" id="lastname"
                                    />
                                </div>
                            </div>
                            <div class=" form-group">
                                <label class="col-md-3 control-label" for="profileAddress">Email Id</label>
                                <div class="col-md-8">


                                    <input path="user.email" data-plugin-maxlength maxlength="50" type="text"
                                           class="form-control" id="email"
                                           name="user.email"/>

                                </div>
                            </div>
                                   <div class=" form-group">
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
                            <div class="tab-pane-footer">
                                <div class="row">
                                    <div class="col-md-6 col-md-offset-3">
                                        <button type="button" class="btn btn-primary" onclick="return validateStep2();">
                                            Save
                                        </button>
                                    </div>
                                </div>
                            </div>

                        </fieldset>
                    </div>
                </div>

            </form>
        </div>
    </div>

</div>

<script type="application/javascript">
    function validateStep1() {
        if ($('#title').val().toString().trim().length == 0) {
            new PNotify({
                title: 'Validation',
                text: "title can not be empty.",
                type: 'error'
            });
            $("#title").focus();
            return false;
        }
        if ($('#mobile').val().toString().trim().length == 0) {
            new PNotify({
                title: 'Validation',
                text: "mobile can not be empty.",
                type: 'error'
            });
            $("#mobile").focus();
            return false;
        }

        if ($('#officePhNo').val().toString().trim().length == 0) {
            new PNotify({
                title: 'Validation',
                text: "An Office Phone No can not be empty.",
                type: 'error'
            });
            $("#officePhNo").focus();
            return false;
        }

        if ($('#surgicalSpeciality').val().toString().trim().length == 0) {
            new PNotify({
                title: 'Validation',
                text: "Surgical Speciality Of Surgeon can not be empty.",
                type: 'error'
            });
            $("#surgicalSpeciality").focus();
            return false;
        }

        if ($('#user-info-tab').hasClass('disabled')) {
            $('#user-info-tab').removeClass('disabled')
        }
        $('a[href=#userInfo]').tab('show');
        $("html, body").animate({scrollTop: 0}, "slow");
    }

    function validateStep2() {
        var formName = $('#staffInfoForm');
        if ($('#firstname').val().toString().trim().length == 0) {
            new PNotify({
                title: 'Validation',
                text: "Firstname can not be empty.",
                type: 'error'
            });
            $("#firstname").focus();
            return false;
        }

        if ($('#lastname').val().toString().trim().length == 0) {
            new PNotify({
                title: 'Validation',
                text: "Lastname can not be empty.",
                type: 'error'
            });
            $("#lastname").focus();
            return false;
        }

        if ($('#email').val().toString().trim().length == 0) {
            new PNotify({
                title: 'Validation',
                text: "Email can not be empty.",
                type: 'error'
            });
            $("#email").focus();
            return false;
        }
        formName.submit();
    }


</script>