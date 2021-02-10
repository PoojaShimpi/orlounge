<%@ page import="org.orlounge.bean.ChecklistBean" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.common.util.ProcessData" %>
<%
    ChecklistBean bean = (ChecklistBean) request.getAttribute("bean");
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
    String title = bean.getTopic() == null ? "" : bean.getTopic();
    String desc = ProcessData.nullToEmpty(bean.getText());
    String fileName = bean.getName() == null ? "" : bean.getName();
    String fileType = bean.getName() == null ? "" : bean.getName().substring(bean.getName().lastIndexOf(".") + 1);

%>

<div class="form-group">
    <label class="col-md-3  control-label">Title  <span class="required">*</span></label>
    <div class="col-md-6">
        <input  disabled="disabled"   required id="title" name="topic" class="form-control" value="<%=title%>" />
       
    </div>
</div>



<div class="form-group">
    <label class="col-md-3 control-label">Upload File </label>
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
           %> href="checklist/downloadFile?id=<%=bean.getId()%>"><i class="fa fa-file-word-o"></i><%
           } else if ("xlsx".equalsIgnoreCase(fileType) || "xls".equalsIgnoreCase(fileType)) {
            %>href="checklist/downloadFile?id=<%=bean.getId()%>"><i class="fa fa-file-excel-o"></i><%
            } else if ("ppt".equalsIgnoreCase(fileType) || "pptx".equalsIgnoreCase(fileType)) {
            %>href="checklist/downloadFile?id=<%=bean.getId()%>"><i class="fa fa-file-powerpoint-o"></i><%
            } else if ("image/jpg".equalsIgnoreCase(fileType)
                    || "image/bmp".equalsIgnoreCase(fileType)
                    || "image/jpeg".equalsIgnoreCase(fileType)
                    || "image/png".equalsIgnoreCase(fileType)
                    || "image/svg".equalsIgnoreCase(fileType)
                    || "image/tiff".equalsIgnoreCase(fileType)
                    || "image/gif".equalsIgnoreCase(fileType)) {
            %>href="checklist/downloadFile?id=<%=bean.getId()%>"><i class="fa fa-file-image-o"></i><%
            } else if ("pdf".equalsIgnoreCase(fileType) || "application/pdf".equalsIgnoreCase(fileType)) {
            %>href="popupex.html" onclick="return popupwin('pdfViewer.jsp?file=checklist/viewFile/id/<%=bean.getId()%>')" ><i class="fa fa-file-pdf-o"></i> <%
            } else {
            %> href="checklist/downloadFile?id=<%=bean.getId()%>"><i class="fa fa-files-o"></i><%
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
    <label class="col-md-2 control-label">Body</label>



    <div class="col-md-10">
            <%=desc%>
    </div>

</div>



