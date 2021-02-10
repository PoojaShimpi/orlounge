<%@ page import="org.orlounge.bean.HandoverBean" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%
    HandoverBean bean = (HandoverBean) request.getAttribute("bean");
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
    String title = bean.getName() == null ? "" : bean.getName();
    String desc = bean.getText();
    String fileName = bean.getFilePath() == null ? "" : bean.getFilePath();
    String fileType = bean.getFilePath() == null ? "" : bean.getFilePath().substring(bean.getName().lastIndexOf(".") + 1);

%>

<div class="form-group">
    <label class="col-md-3  control-label">Title  <span class="required">*</span></label>
    <div class="col-md-6">
        <input  disabled="disabled"   required id="title" name="topic" class="form-control" value="<%=title%>" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-3  control-label">Type  <span class="required">*</span></label>
    <div class="col-md-6">
        <input  disabled="disabled"  id="type" name="type" class="form-control" value="<%=bean.getType()%>" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-3  control-label">Identification </label>
    <div class="col-md-6">
        <input  disabled="disabled"   id="identification" name="identification" class="form-control" value="<%=bean.getIdentification()%>" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-3  control-label">Situation</label>
    <div class="col-md-6">
        <input  disabled="disabled"   id="situation" name="situation" class="form-control" value="<%=bean.getSituation()%>" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-3  control-label">Background</label>
    <div class="col-md-6">
        <input  disabled="disabled"   id="background" name="background" class="form-control" value="<%=bean.getBackground()%>" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-3  control-label">Assessed By</label>
    <div class="col-md-6">
        <input  disabled="disabled"   id="assessedBy" name="assessedBy" class="form-control" value="<%=bean.getAssessedBy()%>" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-3  control-label">Recommendation</label>
    <div class="col-md-6">
        <input  disabled="disabled"   id="recommendation" name="recommendation" class="form-control" value="<%=bean.getRecommendation()%>" />
    </div>
</div>



<div class="form-group">
    <label class="col-md-3 control-label">File </label>
    <%


    %>
    <div class="col-md-6">
       
        <%

            if (fileName != null && !fileName.isEmpty()) {
        %>
        <a title="Download"
           <%
               if ("doc".equalsIgnoreCase(fileType) || "application/msword".equalsIgnoreCase(fileType)
                       || "docx".equalsIgnoreCase(fileType)) {
           %> href="handover/downloadFile?id=<%=bean.getId()%>"><i class="fa fa-file-word-o"></i><%
           } else if ("xlsx".equalsIgnoreCase(fileType) || "xls".equalsIgnoreCase(fileType)) {
            %> href="handover/downloadFile?id=<%=bean.getId()%>"><i class="fa fa-file-excel-o"></i><%
            } else if ("ppt".equalsIgnoreCase(fileType) || "pptx".equalsIgnoreCase(fileType)) {
            %> href="handover/downloadFile?id=<%=bean.getId()%>"><i class="fa fa-file-powerpoint-o"></i><%
            } else if ("image/jpg".equalsIgnoreCase(fileType)
                    || "image/bmp".equalsIgnoreCase(fileType)
                    || "image/jpeg".equalsIgnoreCase(fileType)
                    || "image/png".equalsIgnoreCase(fileType)
                    || "image/svg".equalsIgnoreCase(fileType)
                    || "image/tiff".equalsIgnoreCase(fileType)
                    || "image/gif".equalsIgnoreCase(fileType)) {
            %>href="handover/downloadFile?id=<%=bean.getId()%>"><i class="fa fa-file-image-o"></i><%
            } else if ("pdf".equalsIgnoreCase(fileType) || "application/pdf".equalsIgnoreCase(fileType)) {
            %>href="popupex.html" onclick="return popupwin('pdfViewer.jsp?file=handover/viewFile/id/<%=bean.getId()%>')" ><i class="fa fa-file-pdf-o"></i> <%
            } else {
            %> href="handover/downloadFile?id=<%=bean.getId()%>"><i class="fa fa-files-o"></i><%
                }
            %>
            <%=fileName%>
        </a>

        <%
        } else {
        %>
        No Files
        <%
            }
        %>
    </div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label">Body</label>



    <div class="col-md-9">
        <input disabled="disabled"  type="hidden" id="text" name="text"/>
            <%=desc == null ? "" : desc%>
    </div>

</div>



