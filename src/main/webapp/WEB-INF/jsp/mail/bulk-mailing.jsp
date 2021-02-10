<!doctype html>
<html class="fixed">
<jsp:include page="../../header.jsp"/>
<body style="min-height: 830px;">
<section class="body">
<jsp:include page="../../head-section.jsp"/>
<div class="inner-wrapper" style="width:100%!important;">

<section role="main" class="content-body" style="margin-left:60px!important;margin-top:20px!important;">


</div>









<!-- end: page -->
</section>




</div>


</aside>
</section>
<jsp:include page="../../mainScripts.jsp"/>



<script type="text/javascript" >
    $(window).scroll(function(){
        if ($(this).scrollTop() > 100) {
            //$('.testBtn').fadeIn();
        } else {
            //$('.testBtn').fadeOut();
        }
    });

    $('.testBtn').click(function(){
        $('html, body').animate({scrollTop : 500},800);
        return false;
    });


</script>
</body>
</html>