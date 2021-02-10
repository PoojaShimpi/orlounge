<%@page import="org.orlounge.common.util.ProcessData"%>
<%@ page import="org.orlounge.bean.*" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.common.util.StringUtil" %>
<%
    NoticeBean bean = (NoticeBean) request.getAttribute("bean");
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
    String title = bean.getTopic() == null ? "" : bean.getTopic();
    String fileName = bean.getName();
    String desc = bean.getText();
    out.println(bean);
%>

<div class="form-group">
    <label class="col-md-3  control-label">Title <span class="required">*</span></label>
    <div class="col-md-6">
        <input  required id="title" name="topic" class="form-control" value="<%=title%>" />
        <input type="hidden"  id="id" name="id" value="<%=bean.getId() != null ? bean.getId() : ""%>" class="col-md-4"/>
    </div>
</div>


<div class="form-group">
    <label class="col-md-3 control-label">Notice</label>
    <div class="col-md-6">
        <input type="file"  required id="file" name="file" value="<%=fileName == null ? "" : fileName%>" class="form-control"/>
        <p>
            Files must be less than 2 MB. pdf, tiff, gif, bmp, jpeg, jpg, png files  are supported except Microsoft files

        </p>
        <%

        if (!ProcessData.isValid(bean.getName())) {
    %>
    <%-- <div class=" col-md-6"> <span class="form-control-txt"> No File.</span></div>--%>
    <%
        }
        if (!StringUtil.isEmptyString(fileName)) {
    %>
    <div class=" col-md-6">

        <%
            if ("doc".equalsIgnoreCase(bean.getFileType()) || "docx".equalsIgnoreCase(bean.getFileType())) {
        %>
        <a title="Download" href="notice/downloadFile?id=<%=bean.getId()%>"><i class="fa fa-file-word-o"></i>
            <%=bean.getName()%>
        </a>
        <%
        } else if ("xlsx".equalsIgnoreCase(bean.getFileType()) || "xls".equalsIgnoreCase(bean.getFileType())) {
        %>
        <a title="Download" href="notice/downloadFile?id=<%=bean.getId()%>"><i class="fa fa-file-excel-o"></i>
            <%=bean.getName()%>
        </a>
        <%
        } else if ("ppt".equalsIgnoreCase(bean.getFileType()) || "pptx".equalsIgnoreCase(bean.getFileType())) {
        %>
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
                || "gif".equalsIgnoreCase(bean.getFileType()) || "image/gif".equalsIgnoreCase(bean.getFileType())) {
        %>

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
        } else if ("pdf".equalsIgnoreCase(bean.getFileType()) || "application/pdf".equalsIgnoreCase(bean.getFileType())) {
        %>
        <a title="Download" href="popupex.html" onclick="return popupwin('pdfViewer.jsp?file=notice/viewFile/id/<%=bean.getId()%>')" ><i class="fa fa-file-pdf-o"></i>
            <%=bean.getName()%>
        </a>
        <%
        } else {
        %>
        <a title="Download" href="notice/downloadFile?id=<%=bean.getId()%>"><i class="fa fa-file"></i>
            <%=bean.getName()%>
        </a>
        <%
            }
        %>

    </div>
    <%
    } else {
    %>
    <%
        }

    %>
    </div>
    
</div>
<div class="form-group">
    <label class="col-md-2 control-label">Body</label>
    <div class="col-md-10">
        <input  type="hidden" id="text" name="text"/>
        <div class="">
            <div  id="descriptionB" class="summernote" data-plugin-summernote  data-plugin-options='{ "height": 180, "codemirror": { "theme": "ambiance"} }'><%=desc == null ? "" : desc%></div>
        </div>
    </div>
</div>
<div class="panel-footer clearfix">
    <a class="btn btn-primary pull-right" href="#save" onclick="dataSubmit()" >Save</a>
</div>
