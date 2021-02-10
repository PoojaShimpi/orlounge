<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<div class="form-group">
    <label class="col-md-3  control-label">Title  <span class="required">*</span></label>
    <div class="col-md-6">
        <input   required id="name" name="name" class="form-control" />
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
                        <option value="<%=op%>"><%=op%></option>
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
        <input  id="identification" name="identification" class="form-control" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-3  control-label">Situation</label>
    <div class="col-md-6">
        <input  id="situation" name="situation" class="form-control" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-3  control-label">Background</label>
    <div class="col-md-6">
        <input  id="background" name="background" class="form-control" />
    </div>
</div>
<div class="form-group">
    <label class="col-md-3  control-label">Assessed By</label>
    <div class="col-md-6">
        <input  id="assessedBy" name="assessedBy" class="form-control" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-3  control-label">Recommendation </label>
    <div class="col-md-6">
        <input  id="recommendation" name="recommendation" class="form-control" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label">Upload File </label>

    <div class="col-md-6">
        <input type="file"  required id="file" name="file"  class="form-control"/>
        <p>
            Convert all Powerpoint files into pdf. Files must be less than 2 MB. pdf, tiff, gif, bmp, jpeg, jpg, png files  are supported except Microsoft files
        </p>

    </div>
</div>



<div class="form-group">
    <label class="col-md-2 control-label">Body</label>
    <div class="col-md-10">
        <input type="hidden" id="text" name="text"/>
        <div class="">
            <div  id="descriptionB" class="summernote" data-plugin-summernote  data-plugin-options='{ "height": 180, "codemirror": { "theme": "ambiance"} }'></div>
        </div>
    </div>
</div>

<div class="panel-footer clearfix">
    <input type="submit" class="btn btn-primary pull-right" value="Save" onclick="dataSubmit()"/>
</div>
