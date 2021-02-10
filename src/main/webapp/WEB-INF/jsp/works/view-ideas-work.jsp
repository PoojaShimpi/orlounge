<%@ page import="org.orlounge.bean.IdeaBean" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="static org.orlounge.common.AppConstants.DD_MMM_YYYY_HH_MM_AM_PM" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section role="main" >
    <div class="comment-body col-sm-8 row">
        <div class="compose-box-footer"></div>
        <section class="panel">

            <%
                UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
                IdeaBean bean = (IdeaBean) request.getAttribute("idea");
                Boolean voted = (Boolean) request.getAttribute("hadVoted");
                IdeaBean.IdeaContent content = bean.getData();
            %>

            <h2 class="panel-title clearfix">
                <%=bean.getTitle()%>
                <span class="pull-right">
                    <span class="badge"><%=bean.getVotes()%> Votes </span>
                    <%
                        if(Boolean.TRUE.equals(voted)){
                      %>
                    <a class="btn btn-primary" href="votedown.html?idea=<%=bean.getIdeaId()%>"> <i class="fa fa-thumbs-down"></i> Vote Down </a>
                    <%
                        }else {
                    %>
                    <a class="btn btn-primary" href="voteup.html?idea=<%=bean.getIdeaId()%>"> <i class="fa fa-thumbs-up"></i> Vote Up </a>
                    <%
                        }
                    %>

                </span>

            </h2>
            <div class="panel-body">
                <div class="col-sm-12">
                    <strong>Description</strong>
                    <br/>
                    <p>
                        <%=content.getDescription()%>
                    </p>

                    <br/>
                    <strong>Explanation</strong><br/>
                    <p>
                        <%=content.getContent()%>
                    </p>

                    <div class="toggle" data-plugin-toggle="">

                        <section class="toggle">
                            <label>Comments  & Discussions</label>
                            <div class="toggle-content" style="display: none;">
                                <%
                                    List<IdeaBean.IdeasComment> comments = content.getComments();
                                    if(comments.isEmpty()){
                                        %>
                                    No comments Yet.
                                <%
                                    }
                                    for(IdeaBean.IdeasComment comment : comments){
                                        %>

                                <section class="simple-compose-box mb-xlg panel-body msg-box">
                                    <div class="inner-msg-box">
                                        <div class="profile-pic "><img
                                                src="resources/images/user.jpg"
                                                alt="ORL Member" >
                                        </div>
                                        <div class="message-body">
                                            <div>
                                                <span class="name" title="<%=comment.getCommentBy()%>">
                                                <a class="name-title" href="#"><%=comment.getCommentBy()%></a>
                                                </span>
                                                <span> on
                                                    <span class="timestamp"> <i class="em700">

                                                    </i><%=new SimpleDateFormat(DD_MMM_YYYY_HH_MM_AM_PM).format(comment.getCommentTime())%>
                                                    </span>
                                                </span>
                                                <span class="options"> <i class="fa fa-arrow-down"></i> </span></div>
                                                <%=comment.getComment()%>
                                        </div>


                                    </div>
                                </section>

                                <%
                                    }
                                %>

                                <section class="simple-compose-box mb-lg post-msg">
                                    <form method="POST" id="postIdeaComment" action="comment-idea.html">
                                        <input type="hidden" name="idea" value="<%=bean.getIdeaId()%>" />
                                        <textarea rows="2" class="form-control" name="comment" data-plugin-textarea-autosize="" placeholder="Post Your Comments here..."></textarea>
                                    </form>
                                    <div class="compose-box-footer">

                                        <ul class="compose-btn">
                                            <li>

                                                <a class="btn btn-primary btn-xs" onclick="saveComment()">Post</a>

                                            </li>
                                        </ul>
                                    </div>
                                </section>

                            </div>

                        </section>


                    </div>
                </div>
            </div>
        </section>
    </div>

    <jsp:include page="right-side-option-bar.jsp"/>
</section>
<!-- end: page -->
<script>
    function saveComment(){
        $('#postIdeaComment').submit();
    }
</script>



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



