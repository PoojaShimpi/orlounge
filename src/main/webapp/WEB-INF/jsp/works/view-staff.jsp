<%@ page import="org.orlounge.common.UserInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
var msg = null;
var success = true;

    <% if(request.getParameter("message") != null){
%>
    msg = '<%=request.getParameter("message")%>';
    success = <%=request.getParameter("success")%>;
    var json = {success : success, message: msg}
<%
    }else if(request.getParameter("json") != null){
%>
var json =<%=request.getParameter("json")%> ;
<%
    }
    %>

</script>



<div class="row">
    <div class="col-xs-12">
        <section class="panel">
            <header class="panel-heading">
                <div class="panel-actions">
                    <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                    <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
                </div>

                <h2 class="panel-title">WYSIWYG Editors</h2>
            </header>
            <div class="panel-body">
                <form class="form-horizontal form-bordered">
                    <div class="form-group">
                        <label class="col-md-3 control-label">Summernote</label>
                        <div class="col-md-9">
                            <div class="summernote" data-plugin-summernote data-plugin-options='{ "height": 180, "codemirror": { "theme": "ambiance" } }'>Start typing...</div>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </div>
</div>



<script src="resources/app/admin-user.js"></script>

<!-- Examples -->
<script src="resources/javascripts/forms/examples.advanced.form.js"></script>



