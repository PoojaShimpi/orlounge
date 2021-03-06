<%@page import="org.orlounge.bean.CallScheduleBean"%>
<%@page import="org.orlounge.common.UserToken"%>
<%
    CallScheduleBean bean = (CallScheduleBean) request.getAttribute("bean");
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
    String title = bean.getTopic() == null ? "" : bean.getTopic();
    String fileName = bean.getName();
    String desc = bean.getText();
%>

<div class="form-group">
    <label class="col-md-3  control-label">Title <span class="required">*</span></label>
    <div class="col-md-6">
        <input disabled  id="title" name="topic" class="form-control" value="<%=title%>" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label">Notice</label>
    <div class="col-md-6">
        
        <%
            if (fileName != null && !fileName.isEmpty()) {
                if ("doc".equalsIgnoreCase(bean.getFileType()) || "docx".equalsIgnoreCase(bean.getFileType())) {%>
        <a title="Download" href="notice/downloadFile?id=<%=bean.getId()%>"><i class="fa fa-file-word-o"></i>
            <%=bean.getName()%>
        </a>
        <%
        } else if ("xlsx".equalsIgnoreCase(bean.getFileType()) || "xls".equalsIgnoreCase(bean.getFileType())) {%>
        <a title="Download" href="notice/downloadFile?id=<%=bean.getId()%>"><i class="fa fa-file-excel-o"></i>
            <%=bean.getName()%>
        </a>

        <%
        } else if ("ppt".equalsIgnoreCase(bean.getFileType()) || "pptx".equalsIgnoreCase(bean.getFileType())) {%>
        <a title="Download" href="notice/downloadFile?id=<%=bean.getId()%>"><i class="fa fa-file-powerpoint-o"></i>
            <%=bean.getName()%>
        </a>
        <%
        } else if ("jpg".equalsIgnoreCase(bean.getFileType()) || "image/jpg".equalsIgnoreCase(bean.getFileType())
                || "bmp".equalsIgnoreCase(bean.getFileType()) || "image/bmp".equalsIgnoreCase(bean.getFileType())
                || "jpeg".equalsIgnoreCase(bean.getFileType()) || "image/jpeg".equalsIgnoreCase(bean.getFileType())
                || "png".equalsIgnoreCase(bean.getFileType()) || "image/png".equalsIgnoreCase(bean.getFileType())
                || "svg".equalsIgnoreCase(bean.getFileType()) || "image/svg".equalsIgnoreCase(bean.getFileType())
                || "tiff".equalsIgnoreCase(bean.getFileType()) || "image/tiff".equalsIgnoreCase(bean.getFileType())
                || "gif".equalsIgnoreCase(bean.getFileType()) || "image/gif".equalsIgnoreCase(bean.getFileType())) {%>

        <img width="100" height="100" src="getImageDocument?filePath=<%=bean.getFilePath()%>" data-toggle="modal" data-target="#myModal_image" />

        <div class="modal" id="myModal_image" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" style="margin-top:-10px;" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times"></i></button>
                    </div>

                    <!-- START OF MODAL BODY-->

                    <div class="modal-body">

                        <p>
                            <img  src="getImageDocument?filePath=<%=bean.getFilePath()%>"/>
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
        } else if ("pdf".equalsIgnoreCase(bean.getFileType()) || "application/pdf".equalsIgnoreCase(bean.getFileType())) {%>
        <a title="Download" href="popupex.html" onclick="return popupwin('pdfViewer.jsp?file=notice/viewFile/id/<%=bean.getId()%>')" ><i class="fa fa-file-pdf-o"></i>
            <%=bean.getName()%>
        </a>

        <%
        } else {%>

        <a title="Download" href="notice/downloadFile?id=<%=bean.getId()%>"><i class="fa fa-file"></i>
            <%=bean.getName()%>
        </a>

        <%
                }
            } else { %>
                    No Files
                <%}
        %>
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label"></label>
    <div class="col-md-10">
        <input disabled  type="hidden" id="text" name="text"/>
        <%=desc == null ? "" : desc%>
        </div>
    </div>
</div>

