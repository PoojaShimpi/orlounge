<%@page import="org.orlounge.bean.InServiceBean"%>
<%@page import="org.orlounge.common.AppConstants"%>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.common.util.DateContent" %>
<%@ page import="org.orlounge.common.util.ProcessData" %>
<%@ page import="java.util.Date" %>

<%
    InServiceBean inServiceBean = (InServiceBean) request.getAttribute("bean");
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
    String title = inServiceBean.getTopic() == null ? "" : inServiceBean.getTopic();
    String fileName = inServiceBean.getName() == null ? "" : inServiceBean.getName();
    String date = inServiceBean.getDate() == null ? DateContent.formatDateIntoString(DateContent.convertDBDateToClientDate(new Date(), token.getTimeZone()), AppConstants.YYYY_MM_DD_DASH) : inServiceBean.getDate();
    String presenter = inServiceBean.getPresenter() == null ? "" : inServiceBean.getPresenter();
    String desc = ProcessData.nullToEmpty(inServiceBean.getText() );
%>


<div class="form-group">
    <label class="col-md-3  control-label">Title <span class="required">*</span></label>
    <div class="col-md-6">
        <input disabled  required id="title" name="topic" class="form-control" value="<%=title%>" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label">Date</label>
    <div class="col-md-6">
        <input disabled type="date"  required id="date" name="date" value="<%=date%>" class="form-control"/>
    </div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label">Presenter(If Any)</label>
    <div class="col-md-6">
        <input disabled  required id="presenter" name="presenter" value="<%=presenter%>" class="form-control"/>
    </div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label">In service </label>

    <div class="col-md-6">
        <input type="file"  required id="file" name="file"  class="form-control"/>
        <p>
            Convert all Powerpoint files into pdf. Files must be less than 2 MB. pdf, tiff, gif, bmp, jpeg, jpg, png files  are supported except Microsoft files
        </p>
        <%
            if (fileName == null || fileName.isEmpty()) {
        %>
        No File

        <%
            }
        %>


        <%
            if ("doc".equalsIgnoreCase(inServiceBean.getFileType()) || "application/msword".equalsIgnoreCase(inServiceBean.getFileType())
                    || "docx".equalsIgnoreCase(inServiceBean.getFileType())) {
        %>
        <a title="Download" href="call/downloadFile?id=<%=inServiceBean.getId()%>"><i class="fa fa-file-word-o"></i>
            <%=fileName%>
        </a>
        <%
        } else if ("xlsx".equalsIgnoreCase(inServiceBean.getFileType()) || "xls".equalsIgnoreCase(inServiceBean.getFileType())) {
        %>
        <a title="Download" href="inservice/downloadFile?id=<%=inServiceBean.getId()%>"><i class="fa fa-file-excel-o"></i>
            <%=fileName%>
        </a>
        <%
        } else if ("ppt".equalsIgnoreCase(inServiceBean.getFileType()) || "pptx".equalsIgnoreCase(inServiceBean.getFileType())) {
        %>
        <a title="Download" href="inservice/downloadFile?id=<%=inServiceBean.getId()%>"><i class="fa fa-file-powerpoint-o"></i>
            <%=fileName%>
        </a>
        <%
        } else if ("image/jpg".equalsIgnoreCase(inServiceBean.getFileType())
                || "image/bmp".equalsIgnoreCase(inServiceBean.getFileType())
                || "image/jpeg".equalsIgnoreCase(inServiceBean.getFileType())
                || "image/png".equalsIgnoreCase(inServiceBean.getFileType())
                || "image/svg".equalsIgnoreCase(inServiceBean.getFileType())
                || "image/tiff".equalsIgnoreCase(inServiceBean.getFileType())
                || "image/gif".equalsIgnoreCase(inServiceBean.getFileType())) {
        %>
        <%--   <a title="Download" href="inservice/downloadFile?id=<%=inServiceBean.getId()%>"><i class="fa fa-file-image-o"></i>
               <%=fileName%>
           </a>--%>
        <img width="100" height="100" src="getImageDocument?filePath=<%=inServiceBean.getFilePath()%>" data-toggle="modal" data-target="#myModal_image" />

        <div class="modal" id="myModal_image" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" style="margin-top:-10px;" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times"></i></button>
                    </div>

                    <!-- START OF MODAL BODY-->

                    <div class="modal-body">

                        <p>
                            <img  src="getImageDocument?filePath=<%=inServiceBean.getFilePath()%>"/>
                        </p>


                    </div>

                    <!-- END OF APPLICATION FORM MODAL BODY -->

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->


        </div>

        <%
        } else if ("pdf".equalsIgnoreCase(inServiceBean.getFileType()) || "application/pdf".equalsIgnoreCase(inServiceBean.getFileType())) {
        %>
        <a title="Download" href="popupex.html" onclick="return popupwin('pdfViewer.jsp?file=inservice/viewFile/id/<%=inServiceBean.getId()%>')" ><i class="fa fa-file-pdf-o"></i>
            <%=fileName%>
        </a>

        <%
        } else {
        %>
        <a title="Download" href="inservice/downloadFile?id=<%=inServiceBean.getId()%>"><i class="fa fa-files-o"></i>
            <%=fileName%>
        </a>
        <%
            }
        %>
    </div>
</div>


<div class="form-group">
    <label class="col-md-3 control-label">Description</label>
    <div class="col-md-9">
       <%=desc%>
    </div>
</div>    

