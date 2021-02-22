<!doctype html>
<html class="fixed">
<jsp:include page="../header.jsp"/>
<body style="min-height: 830px;">
<section class="body">
<jsp:include page="../header.jsp"/>

    <div class="col-md-9 center" style="padding-left:300px;">
                        <jsp:include page="register.jsp"/>
                        </div>

 </section>
 <jsp:include page="../mainScripts.jsp"/>
 <script type="text/javascript">
     var radio = 'hospital';
     $(window).scroll(function () {
         if ($(this).scrollTop() > 100) {
             //$('.testBtn').fadeIn();
         } else {
             //$('.testBtn').fadeOut();
         }
     });
     // $(document).ready(function () {
     $("#otp").keydown(function (e) {
         // Allow: backspace, delete, tab, escape, enter and .
         console.log('called me otp')
         if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110]) !== -1 ||
                     // Allow: Ctrl+A, Command+A
                 (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
                     // Allow: home, end, left, right, down, up
                 (e.keyCode >= 35 && e.keyCode <= 40)) {
             // let it happen, don't do anything
             return;
         }
         // Ensure that it is a number and stop the keypress
         if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode >
                 105)) {
             e.preventDefault();
         }
     });
     // });
     $('.testBtn').click(function () {
         $('html, body').animate({
             scrollTop: 500
         }, 800);
         return false;
     });

     function checkEmail() {
         var username = $('#email').val();
         myAjax({
             url: "checkUserNameExists",
             genErrToast: false,
             data: {
                 username: username
             },
             success: function (result) {
                 if (result !== true) {
                     new PNotify({
                         title: '.',
                         text: "The Email already exists.",
                         type: 'error'
                     });
                     $('#validate').attr('disabled', true);

                 } else {
                     $('#validate').attr('disabled', false);
                 }

             }
         });
     }

     $("#qrRole").change(function () {
         var qrRole = $('option:selected', this).text();
         var email = $('#email').val();
         console.log(radio);
         if (radio === 'ambulatory_center') {
             var hospitalId = $('#ambituary').val();
         } else if (radio === 'hospital') {
             var hospitalId = $('#hospital').val();
         }
         var data = "email=" + email + "&role=" + qrRole + "&hospital_id=" + hospitalId;
         console.log(qrRole, email, hospitalId);
         /*myAjax({
          type: "POST",url: "checkemailbyrole", genErrToast: false, data: {email: email,role:qrRole,hospital_id:hospitalId}, success: function (result) {
          alert(result);
          }
          });*/
         myAjax({
             method: "POST",
             url: "checkemailbyrole",
             contentType: "application/x-www-form-urlencoded",
             data: data,
             success: function (result) {
                 if (!result.success) {
                     new PNotify({
                         title: '',
                         text: "Your are already registered user with ORM",
                         type: 'error'
                     });
                     $('#validate').attr('disabled', true);
                 } else {
                     $('#validate').attr('disabled', false);
                 }
             }

         });
         myAjax({
             method: "POST",
             url: "checkemailbyrole",
             contentType: "application/x-www-form-urlencoded",
             data: data,
             success: function (result) {
                 if (!result.success) {
                     new PNotify({
                         title: '',
                         text: "Your are already registered user with ORM",
                         type: 'error'
                     });
                     $('#validate').attr('disabled', true);
                 } else {
                     $('#validate').attr('disabled', false);
                 }
             }

         });

     });


     function radioButtonChange(val) {
         radio = val;
         if (val === 'ambulatory_center') {
             console.log('ambulatory_center', val, $('#hospital'));
             $('#hospital').addClass('isActiveHospital')
             $('#ambituary').removeClass('isActiveAmbultary')

         } else if (val === 'hospital') {
             console.log('hospital', val, $('#ambituary'));
             $('#hospital').removeClass('isActiveHospital')
             $('#ambituary').addClass('isActiveAmbultary')

         }
     }

     function onHospitalNoBlur() {

         var email = $('#email').val();
         var hospitalPhNoMask = $('#hospital-phone').val().replace(/[^0-9\.]/g, '');
         if (hospitalPhNoMask) {
             var data = "email=" + email + "&hospitalPhNo=" + encodeURIComponent('+') + "1" + hospitalPhNoMask;
         }
         if(email && hospitalPhNoMask){
         myAjax({
             method: "POST",
             url: "requestotp",
             contentType: "application/x-www-form-urlencoded",
             data: data,
             success: function (result) {
                 if (!result.success) {
                     new PNotify({
                         title: '',
                         text: "Email or Hospital phone no is missing",
                         type: 'error'
                     });
                     $('#validate').attr('disabled', true);
                 } else {
                     new PNotify({
                         title: '',
                         text: "Please enter your OTP we send you",
                         type: 'success'
                     });
                      $('#otp').prop('disabled', false);
                      $('#verify').removeClass('disabled');
                      $('#verify').prop('disabled', false);

                     // $('#validate').attr('disabled', false);
                 }
             }
         });
         }
     }

     function fixInputs(toFix){
         if(toFix){
             $('#verify').hide();
             $('#verified').show();
         }else {
             $('#verify').show();
             $('#verified').hide();
         }
         $('#hospital-phone').prop('readonly', toFix);
         $('#otp').prop('readonly', toFix);

     }

     function verifyOtp(e) {
         if ($('#otp').prop('disabled')) {
             return;
         }
         var email = $('#email').val();
         var otp = $('#otp').val();
         var data = "email=" + email + "&otp=" + otp;
         myAjax({
             method: "POST",
             url: "verfiyotp",
             contentType: "application/x-www-form-urlencoded",
             data: data,
             success: function (result) {
                 if (!result.success) {
                     new PNotify({
                         title: '',
                         text: "Your OTP is invalid",
                         type: 'error'
                     });
                     $('#validate').attr('disabled', true);
                     fixInputs(false);
                 } else {
                     new PNotify({
                         title: '',
                         text: "Your OTP is verified",
                         type: 'success'
                     });
                     $('#validate').attr('disabled', false);
                     fixInputs(true);
                 }
             }
         });
     }
 </script>
 <jsp:include page="notification-box.jsp"/>

 </body>
</html>