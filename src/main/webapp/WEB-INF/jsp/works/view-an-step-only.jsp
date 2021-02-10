
<%@ page import="org.orlounge.bean.AnaethesiaSetupNewBean" %>
<%@ page import="org.orlounge.common.UserToken" %>
<%

    AnaethesiaSetupNewBean bean = (AnaethesiaSetupNewBean) request.getAttribute("bean");
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
%>

<div class="form-group">
    <label class="col-md-3  control-label">Operation <span class="required">*</span></label>
    <div class="col-md-6">
        <input disabled='disabled'  required id="operation"
                name="operation" class="form-control "
                value="<%=bean.getOperation() != null ? bean.getOperation() : ""%>"/>
      
    </div>
</div>

<div class="form-group">
    <label class="col-md-3  control-label">Type of Anesthesia <span
            class="required">*</span></label>
    <div class="col-md-6">
        <input disabled='disabled'  required id="type_of_anesthesia"
                name="type_of_anesthesia"
                class="form-control "
                value="<%=bean.getTypeOfAnesthesia() != null ? bean.getTypeOfAnesthesia() : ""%>"/>

    </div>
</div>

<div class="form-group">
    <label class="col-md-3  control-label">Pre-induction <span
            class="required">*</span></label>
    <div class="col-md-6">
        <textarea  disabled='disabled' required id="pre_induction"
                   name="pre_induction"
                   class="form-control"> <%=bean.getPreInduction() != null ? bean.getPreInduction() : ""%> </textarea>

    </div>
</div>

<div class="form-group">
    <label class="col-md-3  control-label">Intra-operative <span
            class="required">*</span></label>
    <div class="col-md-6">
        <textarea  disabled='disabled' required id="intra_operative"
                   name="intra_operative"
                   class="form-control"> <%=bean.getIntraOperative() != null ? bean.getIntraOperative() : ""%> </textarea>

    </div>
</div>

<div class="form-group">
    <label class="col-md-3  control-label">Emergence<span
            class="required">*</span></label>
    <div class="col-md-6">
        <textarea disabled='disabled'  required id="emergence"
                   name="emergence"
                   class="form-control" > <%=bean.getEmergence() != null ? bean.getEmergence() : ""%> </textarea>

    </div>
</div>
