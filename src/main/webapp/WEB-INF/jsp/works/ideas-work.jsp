<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.bean.IdeaBean" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section role="main" >
    <div class="comment-body col-sm-8 row">
        <div class="compose-box-footer"></div>
        <section class="panel">

            <%
                UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
            %>

            <h2 class="panel-title clearfix">
                Suggest an Idea for Loungeworks or contribute to discuss about popular topics.

            </h2>
            <div class="panel-body">
                <div class="col-sm-12">
                    <%
                        int num = Integer.valueOf(request.getAttribute("num").toString()).intValue();
                    %>
                    <%
                        if(num > 0){
                    %>
                    <h2 class="pb-sm">Top <%=num%> Trending Ideas</h2>
                    <p>
                        <a class="bold" href="ideasAll.html">See All Ideas</a>
                    </p>
                    <%}
                     else{
                    %>
                    <h2 class="pb-sm">Ideas</h2>
                    <%
                    } %>
                    <p>
                        Want to Publish New Idea?  <a class="bold" href="newidea.html">Click Here</a>
                    </p>
                    <div class="toggle" data-plugin-toggle="">
                        <%
                            List<IdeaBean> ideas  = (List<IdeaBean>) request.getAttribute("ideas");
                            if(ideas.isEmpty()){
                                %>
                                No Ideas Yet Published. Be the First one to share it. Use Above Link to suggest it.
                        <%
                            }
                            for(IdeaBean idea : ideas){
                                IdeaBean.IdeaContent content = idea.getData();
                                    %>
                        <section class="toggle">
                            <label><%=idea.getTitle()%> <span class="badge"><%=idea.getVotes()%> Votes <i class="fa fa-thumbs-up"></i></span></label>
                            <div class="toggle-content" style="display: none;">
                                <p>
                                    <%=content.getDescription()%>
                                </p>
                                <p>
                                    <a class="btn btn-primary" href="view-idea.html?idea=<%=idea.getIdeaId()%>">View Details</a>
                                </p>
                            </div>

                        </section>
                        <%
                            }
                        %>


                    </div>
                </div>
            </div>
        </section>
    </div>

    <jsp:include page="right-side-option-bar.jsp"/>
</section>
<!-- end: page -->




<div id="dialog" class="modal-block mfp-hide">
    <section class="panel">
        <header class="panel-heading">
            <h2 class="panel-title">Are you sure?</h2>
        </header>
        <div class="panel-body">
            <div class="modal-wrapper">
                <div class="modal-text">
                    <p>Are you sure that you want to delete this row?</p>
                </div>
            </div>
        </div>
        <footer class="panel-footer">
            <div class="row">
                <div class="col-md-12 text-right">
                    <button id="dialogConfirm" class="btn btn-primary">Confirm</button>
                    <button id="dialogCancel" class="btn btn-default">Cancel</button>
                </div>
            </div>
        </footer>
    </section>
</div>



<script src="resources/app/admin-user.js"></script>



