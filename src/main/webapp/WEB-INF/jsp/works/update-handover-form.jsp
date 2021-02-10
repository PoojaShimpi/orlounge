<%@ page import="org.orlounge.bean.HandoverBean" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%@ page import="org.orlounge.common.util.ProcessData" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%
    HandoverBean bean = (HandoverBean) request.getAttribute("bean");
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
    String title = bean.getName() == null ? "" : bean.getName();
    String type = ProcessData.nullToEmpty(bean.getType());
    String desc = bean.getText();
    String fileName = bean.getFilePath() == null ? "" : bean.getFilePath();
    String fileType = bean.getFilePath() == null ? "" : bean.getFilePath().substring(bean.getName().lastIndexOf(".") + 1);

%>

<div class="form-group">
    <label class="col-md-3  control-label">Title  <span class="required">*</span></label>
    <div class="col-md-6">
        <input   required id="title" name="name" class="form-control" value="<%=title%>" />
        <input  type="hidden"  id="id" name="id" value="<%=bean.getId() != null ? bean.getId() : ""%>" class="col-md-4"/>
    </div>
</div>

<%
    Map<String, List<String>> typesMap = new LinkedHashMap<>();
    typesMap.put("Preoperative Handoffs",
            List.of("Outpatients: Holding area to operating room","Inpatients: Floor to operating room" , "Inpatients: Intensive care unit to operating room",
                    "Emergency department to operating room"));

    typesMap.put("Intraoperative Handoffs",
            List.of("Intraoperative Handoffs"));
    typesMap.put("Postoperative Handoffs",
            List.of("Operating room to post-anesthesia care unit","Operating room to intensive care unit"));

%>

<div class="form-group">
    <label class="col-md-3 control-label">Type of handover</label>
    <div class="col-md-6">
        <select class="progress-select-box" id="type" name="type">

            <%
                for(Map.Entry<String,List<String>> e : typesMap.entrySet()) {
            %>
            <optgroup label="<%=e.getKey()%>">
                <% for(String op : e.getValue()) {

                %>
                <option  <%if(type.equals(op)) { %> selected <%  }%>
                        value="<%=op%>"><%=op%></option>
                <%
                    }
                %>
            </optgroup>
            <%

                }
            %>
        </select>
    </div>
</div>


<div class="form-group">
    <label class="col-md-3  control-label">Identification</label>
    <div class="col-md-6">
        <input  id="identification" name="identification" class="form-control" value="<%=ProcessData.nullToEmpty(bean.getIdentification())%>" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-3  control-label">Situation</label>
    <div class="col-md-6">
        <input  id="situation" name="situation" class="form-control" value="<%=ProcessData.nullToEmpty(bean.getSituation())%>" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-3  control-label">Background</label>
    <div class="col-md-6">
        <input  id="background" name="background" class="form-control" value="<%=ProcessData.nullToEmpty(bean.getBackground())%>" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-3  control-label">Assessed By</label>
    <div class="col-md-6">
        <input  id="assessedBy" name="assessedBy" class="form-control" value="<%=ProcessData.nullToEmpty(bean.getAssessedBy())%>" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-3  control-label">Recommendation</label>
    <div class="col-md-6">
        <input  id="recommendation" name="recommendation" class="form-control" value="<%=ProcessData.nullToEmpty(bean.getRecommendation())%>" />
    </div>
</div>



<div class="form-group">
    <label class="col-md-3 control-label">Upload File </label>
    <%


    %>
    <div class="col-md-6">
        <input type="file" required id="file" name="file" value="<%=fileName == null ? "" : fileName%>" class="form-control"/>
        <p>
            Convert all Powerpoint files into pdf. Files must be less than 2 MB. pdf, tiff, gif, bmp, jpeg, jpg, png files  are supported except Microsoft files
        </p>
        <%  if (fileName != null && !fileName.isEmpty()) {
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
        <input  type="hidden" id="text" name="text"/>
        <div class="">
            <div  id="descriptionB" class="summernote" data-plugin-summernote  data-plugin-options='{ "height": 180, "codemirror": { "theme": "ambiance"} }'><%=desc == null ? "" : desc%></div>
        </div>
    </div>

</div>



<div class="panel-footer clearfix">
    <input type="submit" class="btn btn-primary pull-right" value="Save" onclick="dataSubmit()" />
</div>
