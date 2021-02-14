<%@ page import="org.orlounge.common.AppConstants" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.common.util.ProcessData" %>
<!-- start: sidebar -->
<aside id="sidebar-left" class="sidebar-left">

    <div class="sidebar-header" style="height: 30px;">
        <div class="sidebar-title" style="padding: 5px 0 0 9px;font-size: 15px;line-height: 25px;">
        <span>
            LOUNGEWORKS
            <span class="fa fa-question-circle" data-toggle="tooltip" data-placement="bottom"
                  title="This set of Loungeworks can be used by all Operating Rooms. New Loungworks are being developed for larger operating rooms that perform more complex procedures."></i>
        </span>
        </div>
        <div class="sidebar-toggle hidden-xs" data-toggle-class="sidebar-left-collapsed" data-target="html"
             data-fire-event="sidebar-left-toggle" style="height: 30px;" onclick="onToggle()">
            <i class="toggle-switches fa fa-arrow-left" aria-label="Toggle sidebar" style="line-height: 35px;"></i>
        </div>
    </div>
    <script type="application/javascript">
        var isOpen = true;

        function onToggle() {
            if (isOpen) {
                isOpen = false;
                $('.toggle-switches').removeClass('fa-arrow-left');
                $('.toggle-switches').addClass('fa-arrow-right');
            } else {
                isOpen = true;
                $('.toggle-switches').removeClass('fa-arrow-right');
                $('.toggle-switches').addClass('fa-arrow-left');
            }

        }
    </script>

    <div class="">
        <div class="nano-content">
            <nav id="menu" class="nav-main" role="navigation">
                <ul class="nav nav-main nav-children">
                    <%--<li>

                        <a href="profile.html">
                            <i class="fa fa-home" aria-hidden="true"></i>
                            <span>My Profile</span>
                        </a>
                    </li>--%>
                    <%
                        UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
                        if (ProcessData.isValid(token) && token.hasValidRole()) {
                            boolean isLSA = token.isLSA();
                            boolean isAdmin = AppConstants.ADMIN_ROLE.equals(token.getRole());
                            boolean hasGroup = token.getCurrentGroupId() != null;
                            boolean isAdvisoryBoard = ("Advisory Lounge").equals(token.getCurrentGroupName());
                            if (isAdmin) {
                    %>
                    <li class="nav-parent bold">
                        <a title="Mailing">
                            <i class="fas fa-envelope" aria-hidden="true"></i>
                            <span>Mailing</span>
                        </a>
                        <ul class="nav nav-children">
                            <li>
                                <a href="bulkmail.html">
                                    BulkMail
                                </a>
                            </li>
                            <li>
                                <a href="invitationbulkmail.html">
                                    Invitation Mail
                                </a>
                            </li>
                        </ul>
                    </li>
                    <%
                        }
                    %>

                    <%
                        if (isAdvisoryBoard) {
                    %>
                    <li>
                        <a href="loungeworks.html?role=get_well_soon_advisory">
                            Get Well Soon Advisory Lounge
                        </a>
                    </li>
                    <li>
                        <a href="loungeworks.html?role=asc_advisory">
                            ASC Advisory Lounge
                        </a>
                    </li>
                    <li>
                        <a href="loungeworks.html?role=hlounge_advisory">
                            Hospital Lounge Advisory Lounge
                        </a>
                    </li>
                    <%
                    } else if (hasGroup) {
                    %>
                    <li>
                        <a href="staffinfolist.html" title="STAFF DIRECTORY">
                            <i class="fas fa-address-book" aria-hidden="true"></i>
                            <span>STAFF DIRECTORY</span>
                        </a>
                    </li>
                    <li>
                        <a href="call-schedule.html" title="CALL SCHEDULE">
                            <i class="fas fa-calendar-check" aria-hidden="true"></i>
                            <span>CALL SCHEDULE</span>
                        </a>
                    </li>
                    <li>
                        <a href="procedure-manuals.html" title="ADMINISTRATIVE MANUAL">
                            <i class="fas fa-book" aria-hidden="true"></i>
                            <span>ADMINISTRATIVE MANUAL</span>
                        </a>
                    </li>
                    <li>
                        <a href="notices.html" title="NOTICES">
                            <i class="fas fa-sticky-note" aria-hidden="true"></i>
                            <span>NOTICES</span>
                        </a>
                    </li>
                    <li>
                        <a href="inservice.html" title="IN SERVICE NOTICES">
                            <i class="fas fa-tasks"  aria-hidden="true"></i>
                            <span>IN SERVICE NOTICES</span>
                        </a>
                    </li>
                    <li>
                        <a href="setup-list.html" title="ANAESTHESIA SETUP">
                            <i class="fas fa-syringe"  aria-hidden="true"></i>
                            <span>ANAESTHESIA SETUP</span>
                        </a>
                    </li>
                    <%
                        if (isLSA || isAdmin || token.isORM() || token.isSurgeon() || token.isAnaesthesiologist() || token.isDoctorRole()) {
                    %>
                    <li>
                        <a href="pref-list.html" title="PREOPERATIVE CARE">
                            <i class="fas fa-caret-left"  aria-hidden="true"></i>
                                <span>PREFERENCE SETUP</span>
                        </a>
                    </li>
                    <%
                        }
                    %>
                    <%
                        if (isLSA || isAdmin || token.isORM() || token.isSurgeon() || token.isAnaesthesiologist() || token.isDoctorRole()) {
                    %>
                    <li>
                        <a href="postop.html" title="POSTOPERATIVE CARE">
                            <i class="fas fa-caret-right"  aria-hidden="true"></i>
                            <span>POSTOPERATIVE CARE</span>
                        </a>
                    </li>
                    <%
                        }
                    %>
                    <li>
                        <a href="checklists.html" title="CHECKLIST">
                            <i class="fas fa-check-square"  aria-hidden="true"></i>
                            <span>CHECKLIST</span>
                        </a>
                    </li>
                    <li>
                        <a href="handovers.html" title="HANDOVER">
                            <i class="fa fa-exchange"  aria-hidden="true"></i>
                            <span>HANDOVER(ISBAR)</span>
                        </a>
                    </li>
                    <li>
                       <a href="messageboard.html" title="Messages" style="background-color:#005896ba;">
                                                   <i class="fa fa-comments" aria-hidden="true"></i>
                                                   <span style="color:white;font-size:15px;"><b>Messages</b></span>
                                               </a>
                    </li>

                    <%
                        }
                    %>

                    <%
                        if (isAdmin || isLSA) {
                    %>
                    <li class="nav-parent bold">
                        <a title="Administration">
                            <i class="fa fa-tools" aria-hidden="true"></i>
                            <span>Administration</span>
                        </a>
                        <ul class="nav nav-children">
                            <%
                                if (isAdmin || isLSA) {
                            %>
                            <li>
                                <a href="manageUsers.html">
                                    Manage Users
                                </a>
                            </li>

                            <li>
                                <a href="manage-geo-fence.html">
                                    Manage Geo Fence
                                </a>
                            </li>

                            <%
                                }
                            %>

                            <%
                                if (isAdmin || isLSA) {
                            %>
                            <li>
                                <a href="manage-esps.html">
                                    Manage ESPS
                                </a>
                            </li>
                            <li>
                                <a href="groups.html">
                                    Manage ORLS
                                </a>
                            </li>
                            <li>
                                <a href="states.html">
                                    Manage State
                                </a>
                            </li>
                            <li>
                                <a href="hospitals.html">
                                    Manage Hospitals
                                </a>
                            </li>
                            <%
                                }
                            %>


                        </ul>
                    </li>
                    <%
                        }
                    %>
                    <%
                        }
                    %>

                </ul>
            </nav>

            <hr class="separator"/>

            <div class="sidebar-widget widget-tasks">
                <div class="widget-header">
                    <h6>OR Lounge</h6>
                    <div class="widget-toggle">+</div>
                </div>
                <div class="widget-content">
                    <ul class="list-unstyled m-none">
                        <li><a href="aboutus.html" class="bold " style="color:#000">About Us</a></li>
                        <li><a href="contactus.html" class="bold" style="color:#000">Contact OR Lounge Team</a></li>
                        <%--<li><a href="copyrightnotice.html" class="bold" style="color:#000">Copyright Notice &amp; Disclaimer</a></li>--%>
                        <li><a href="terms.html" class="bold " style="color:#000">Terms &amp; Conditions of use</a></li>
                    </ul>
                </div>
            </div>

            <hr class="separator"/>

        </div>

    </div>

</aside>
