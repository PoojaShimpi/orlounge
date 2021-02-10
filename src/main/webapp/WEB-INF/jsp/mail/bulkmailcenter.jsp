<section role="main" width="" class="content-body">
    <header class="page-header" style="top:80px;">
        <h2>Bulk Mail</h2>

        <div class="right-wrapper pull-right">
            <ol class="breadcrumbs">
                <li>
                    <a href="home.html">
                        <i class="fa fa-home"></i>
                    </a>
                </li>
                <li><span>Bulk Mail </li>
                <li><span> See Calendar</span></li>
            </ol>

            <a class="sidebar-right-toggle" data-open="sidebar-right"><i class="fa fa-chevron-left"></i></a>
        </div>
    </header>

    <!-- start: page -->



    <section class="content-with-menu content-with-menu-has-toolbar mailbox"  style="top:134px;">
        <div class="content-with-menu-container" >


            <div class="inner-body" style="margin-left: 0px;
    margin-top: -80px;">
                <div class="inner-toolbar clearfix" style="left: 72px;
    margin-top: 19px;">
                    <ul>
                        <li>
                            <a href="#" onclick="sendmail()"><i class="fa fa-send-o mr-sm"></i> Send</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-times mr-sm"></i> Discard</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-paperclip mr-sm"></i> Attach</a>
                        </li>
                    </ul>
                </div>
                <script type="application/javascript">
                    function sendmail(){
                        $('#send_mail_form').submit();
                    }

                </script>
                <div class="mailbox-compose">
                    <form id="send_mail_form" action="mail/sendBulkMail" class="form-horizontal form-bordered form-bordered">

                        <div class="form-group form-group-invisible">
                            <label for="to" class="control-label-invisible">To:</label>
                            <div class="col-sm-offset-2 col-sm-9 col-md-offset-1 col-md-10">
                                <input id="to" name="bcc" type="text" class="form-control form-control-invisible" data-role="tagsinput" data-tag-class="label label-primary" value="">
                            </div>
                        </div>

                        <%--<div class="form-group form-group-invisible">
                            <label for="cc" class="control-label-invisible">CC:</label>
                            <div class="col-sm-offset-2 col-sm-9 col-md-offset-1 col-md-10">
                                <input id="cc"  name="cc" type="text" class="form-control form-control-invisible" data-role="tagsinput" data-tag-class="label label-primary" value="">
                            </div>
                        </div>--%>

                        <div class="form-group form-group-invisible">
                            <label for="subject" class="control-label-invisible">Subject:</label>
                            <div class="col-sm-offset-2 col-sm-9 col-md-offset-1 col-md-10">
                                <input id="subject"  name="subject" type="text" class="form-control form-control-invisible" value="">
                            </div>
                        </div>

                        <%--<div class="form-group">
                            <div class="compose">
                                <div id="compose-field" class="compose-control">
                                  </div>
                            </div>
                        </div>--%>
                        <div class="form-group form-group-invisible">
                            <label for="subject" class="control-label-invisible">Body:</label>

                        </div>

                        <div class="form-group form-group-invisible">
                            <div class="col-sm-offset-2 col-sm-9 col-md-offset-1 col-md-10">
                                <textarea   name="body" rows="20" id="body" class="form-control form-control-invisible"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </section>


</section>
