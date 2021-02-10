<%@page import="org.orlounge.common.util.ProcessData"%>
<%@page import="java.util.HashSet"%>
<%@page import="org.orlounge.common.AppConstants"%>
<%@page import="org.orlounge.bean.InstrumentPrefListBean"%>
<%@page import="java.util.Set"%>
<%@page import="org.orlounge.common.UserToken"%>
<%@page import="org.orlounge.bean.PrefListBean"%>
<%
    PrefListBean bean = (PrefListBean) request.getAttribute("bean");
    UserToken token = (UserToken) session.getAttribute("USER_TOKEN");
    String sp = bean.getSpeciality() == null ? "" : bean.getSpeciality();
    String procedure = bean.getProcedure() == null ? "" : bean.getProcedure();
    String spCons = bean.getSpConsideration();
    String anCons = bean.getAnConsideration();
    String roomSetup = bean.getRoomSetup();
    String prepDrape = bean.getPrepDrape();
    String eq = bean.getEquipment();
    String dressDrain = bean.getDressingDrain();
    String others = bean.getOthers();
    Set<InstrumentPrefListBean> ins = bean.getInstruments() == null ? new HashSet<InstrumentPrefListBean>() : bean.getInstruments();
    String action = (String) request.getAttribute(AppConstants.ACTION);
%>
<h4>Part 1: Operating Room Setup</h4>
<h4>Part 2: Preference List</h4></br>


<h4>Part 1. Operating Room Setup</h4>

<div class="form-group">
    <label class="col-md-3  control-label">Speciality</label>
    <input id="speciality" name="speciality" class="col-md-6 " value="<%=sp%>" readonly />
    <input type="hidden"  id="id" name="id" value="<%=bean.getId() != null ? bean.getId() : ""%>" class="col-md-4"/>
</div>


<div class="form-group">
    <label class="col-md-3  control-label">Procedure <span class="required">*</span></label>
    <input  id="procedure" readonly name="procedure" class="col-md-6 " value="<%=procedure%>" onchange="$('#addToTable').prop('disabled', false);" />
    <!-- <h1> Operating Room Setup</h1> -->
</div>




<div class="form-group">
    <label class="col-md-3  control-label">Special Considerations</label>
    <textarea id="spConsideration" name="spConsideration" class="col-md-6 " readonly ><%=spCons == null ? "" : spCons%></textarea>

</div>


<div class="form-group">
    <label class="col-md-3  control-label">Anaesthesia  Considerations</label>
    <textarea id="anConsideration" name="anConsideration" class="col-md-6 " readonly ><%=anCons == null ? "" : anCons%></textarea>

</div>




<div class="form-group">
    <label class="col-md-3  control-label">Room Setup</label>
    <textarea  id="roomSetup" name="roomSetup" class="col-md-6 " readonly><%=roomSetup == null ? "" : roomSetup%></textarea>
</div>


<div class="form-group">
    <label class="col-md-3  control-label">Preparation and Drape</label>
    <textarea id="prepDrape" name="prepDrape" class="col-md-6 " readonly ><%=prepDrape == null ? "" : prepDrape%></textarea>

</div>




<div class="form-group">
    <label class="col-md-3  control-label">Equipment</label>
    <textarea   id="equipment" name="equipment" class="col-md-6 " readonly ><%=eq == null ? "" : eq%></textarea>
</div>




<div class="form-group">
    <label class="col-md-3  control-label">Dressings and Drains</label>
    <textarea    id="dressingDrain" name="dressingDrain" class="col-md-6 " readonly ><%=dressDrain == null ? "" : dressDrain%></textarea>
</div>


<div class="form-group">
    <label class="col-md-3  control-label">Others</label>
    <textarea   id="others" name="others" class="col-md-6 " readonly ><%=others == null ? "" : others%></textarea>

</div>
<%--  <div class="form-group">
      <a class="btn btn-primary" href="pref-list.html"  >Cancel</a>
  </div>--%>

</form>

<%-- <div class="row">
    <div class="col-sm-0">
        <div class="mb-md" style="margin-left: 20px;">
            <h4>Add Instrument</h4>

            <p class="bold">
                Tips: upload an image only if you think it helps.
            </p>
             <button  type="button" id="uploadInstrument"  onchange="savePreList()" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                  Browse and upload
              </button>
              <button  type="button" id="downloadSampleCSV"  onclick="downloadSample()" class="btn btn-primary" >Download Template CSV</button>
        </div>
    </div>
</div> --%>

<h4>Part 2: Preference List</h4>
<div class="form-group">
    <form enctype="multipart/form-data" id="ins-form" action="saveinstrument.html?action=<%=action%>" method="POST">
        <input type="hidden" name="id" id="ins-id"/>
        <input type="hidden" name="prefId" id="ins-prefId" />
        <input type="hidden" name="action" id="action" value="<%=action%>"/>

        <div class="panel panel-default">
            <div id="collapseTwo" class="panel-collapse collapse in">
                <div class="panel-body"> 
                    <div class="row ifields">
                        <table class="table table-bordered table-striped mb-none" id="datatable-editable">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Photo</th>
                                    <th>Bin</th>
                                    <th>Catalog #</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (InstrumentPrefListBean i : ins) {
                                %>
                                <tr class="gradeX" pref-id="<%=i.getPrefId()%>" ins-id="<%=i.getId()%>" >
                                    <td><%=i.getName()%></td>
                                    <td>
                                        <%=i.getQuantity()%>
                                    </td>
                                    <td>
                                        <%
                                            if (ProcessData.isValid(i.getPhotoImagePath())) {
                                        %>


                                        <img width="100" height="100" src="getImageDocument?filePath=<%=i.getPhotoImagePath()%>" data-toggle="modal" data-target="#myModal_<%=i.getId()%>" />

                                        <div class="modal" id="myModal_<%=i.getId()%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" style="margin-top:-2px;" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times"></i></button>
                                                    </div>

                                                    <!-- START OF MODAL BODY-->

                                                    <div class="modal-body">

                                                        <p>
                                                            <img  src="getImageDocument?filePath=<%=i.getPhotoImagePath()%>"/>
                                                        </p>


                                                    </div>

                                                    <!-- END OF APPLICATION FORM MODAL BODY -->

                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                    </div>
                                                </div><!-- /.modal-content -->
                                            </div><!-- /.modal-dialog -->


                                        </div>
                                    </td>

                                    <%
                                        }
                                    %>
                                    <td> <%=i.getBin()%></td>
                                    <td><%=i.getCatalog()%></td>
                                    <td class="actions">
                                        <%
                                            if (!token.isGuest()) {
                                        %>
                                        <a href="#"  class="hidden on-editing save-row"><i class="fa fa-save"></i> Save</a>
                                        <a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i> Cancel</a>
                                        <a href="#" class="on-default remove-row"><i class="fa fa-trash-o"></i> Remove</a>
                                        <%
                                            }
                                        %>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>

                    </div>


                    <div class="row" style="float: right;">
                        <div class="col-sm-0">
                            <div class="mb-md">
                              <%-- 	  <button id="addToTable" name="addToTable" class="btn btn-primary"><i>Add Instrument</i></button>--%>
                                <%--  <button  type="button" id="uploadInstrument"  onchange="savePreList()" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                                      Browse and upload
                                  </button>
                                  <button  type="button" id="downloadSampleCSV"  onclick="downloadSample()" class="btn btn-primary" >Download Template CSV</button>--%>
                            </div>
                        </div>
                    </div>
                 </div> 
            </div>
        </div>

    </form>
</div>
<div id="dialog" class="modal-block mfp-hide">
    <section class="panel">
        <header class="panel-heading">
            <h2 class="panel-title">Are you sure?</h2>
        </header>
        <div class="panel-body">
            <div class="modal-wrapper">
                <div class="modal-text">
                    <p>Are you sure that you want to delete this row?</p>
                </div>
            </div>
        </div>
        <footer class="panel-footer">
            <div class="row">
                <div class="col-md-12 text-right">
                    <button id="dialogConfirm" class="btn btn-primary">Confirm</button>
                    <button id="dialogCancel" class="btn btn-default">Cancel</button>
                </div>
            </div>
        </footer>
    </section>
</div>






<div class="modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">-</button>
            </div>

            <!-- START OF MODAL BODY-->

            <div class="modal-body">

                <p>
                    <a href="#" data-toggle="modal" data-target="#upload-avatar" class="button"><i class="fa fa-plus"></i> Upload new CSV File</a>
                </p>


            </div>

            <!-- END OF APPLICATION FORM MODAL BODY -->

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->


    <!--Modal for uploading photo-->
    <div class="modal" id="upload-avatar" tabindex="-1" role="dialog" aria-labelledby="upload-avatar-title" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">-</button>
                    <h4 class="modal-title" id="upload-avatar-title">Upload new File</h4>
                </div>
                <div class="modal-body">
                    <form enctype="multipart/form-data" id="uploadFile" action="saveBatchInstrument" method="POST">
                        <div class="form-group">
                            <div class="col-xs-7">
                                <input type="file" id="file1" name="file" class="form-control" required />
                                <input type="hidden" id="uploadPrefId" name="prefId" />
                                <p id="error1" style="display:none; color:#FF0000;">Invalid Image Format!File Format Must Be CSV.</p>
                                <p id="error2" style="display:none; color:#FF0000;">Maximum File Size Limit is 1MB.</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="button" class="hl-btn hl-btn-default" id="btnUploadCancel">Cancel</button>
                            <button type="button" id="uploadDoc" class="hl-btn hl-btn-green" onclick="savePreList();">Upload</button>
                        </div>
                    </form>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>
    <script>
        $('#btnUploadCancel').click(function () {
            $('#upload-avatar').modal('toggle');
        });
        $('#uploadDoc').prop("disabled", true);
        var a = 0;
        $('#file1').bind('change', function () {
            if ($('#uploadDoc').attr('disabled', false)) {
                $('#uploadDoc').attr('disabled', true);
            }
            var ext = $('#file1').val().split('.').pop().toLowerCase();
            if ($.inArray(ext, ['csv']) == -1) {
                $('#error1').slideDown("slow");
                $('#error2').slideUp("slow");
                a = 0;
            } else {
                var picsize = (this.files[0].size);
                if (picsize > 1000000) {
                    $('#error2').slideDown("slow");
                    a = 0;
                } else {
                    a = 1;
                    $('#error2').slideUp("slow");
                }
                $('#error1').slideUp("slow");
                if (a == 1) {
                    $('#uploadDoc').attr('disabled', false);
                }
            }
        });
    </script>
</div>
<div id="push"></div>
<!-- 
<div class="form-group">
    <button id="btn-save" name="btn-save" class="btn btn-primary " onclick="dataSubmit()">Save Preference List</button>
</div> -->
