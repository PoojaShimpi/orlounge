<%@ page import="java.util.Map" %>
<%@ page import="org.orlounge.common.UserToken" %>
<section role="main">
<div class="row col-sm-8">
    <div class="panel">
        <%
            UserToken token = (UserToken) session.getAttribute("USER_TOKEN");

        %>
    <%

        Map<String,String> map = (Map<String, String>) request.getAttribute("contentMap");
    %>
    <div class="panel-title">
        <h3>What is GWS?</h3>
        <%
            if(token.isAdmin()||token.isLSA()) {
            %>
        <span class="pull-right btn btn-default editBtn" >Edit</span>

        <%
            }
        %>
    </div>
        <%
            if(token.isAdmin()||token.isLSA()){
        %>
        <br/>
        <br/>
        <br/>
        <div class="content-gws hidden">
            <div  id="descriptionB" class="summernote" data-plugin-summernote
                  data-plugin-options='{ "height": 180, "codemirror": { "theme": "ambiance"} }'>
                <%=map.getOrDefault("WHAT_IS_GWS","")%>

            </div>
            <span class="btn btn-primary pull-left doneBtn">Done</span>
            <span class="btn btn-danger pull-right cancelBtn">Cancel</span>
        </div>

        <script>
            $('.editBtn').on('click', function(){
                $('.content-gws').removeClass('hidden');

            })
            $('.cancelBtn').on('click', function(){
                $('.content-gws').addClass('hidden');

            })
            $('.doneBtn').on('click', function(){
                var x = $('#descriptionB').parent().find('.note-editable').html();
                $('.content-gws-read').html(x);
                myAjax({
                    url : 'save-gws-app/WHAT_IS_GWS',
                    data :  x.toString(),
                    method : 'POST'
                });
                $('.content-gws').addClass('hidden');

            })

        </script>
        <%
            }

        %>

        <div class="panel-body content-gws-read" >
            <%=map.getOrDefault("WHAT_IS_GWS", "")%>
        </div>

    </div>
</div>
    <jsp:include page="right-side-option-bar.jsp"/>


</section>
