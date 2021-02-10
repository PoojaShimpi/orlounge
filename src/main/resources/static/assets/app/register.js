function changeRole() {
    var telNum = $('#phonegrp');
    var extNum = $('#extphonegrp');
    var combo = $('#ms_example1');
    var beds = $('#bed-form');

    if (combo.val() && combo.val() === 'orm') {
        telNum.show();
        extNum.show();
        beds.show();
    } else {
        telNum.hide();
        extNum.hide();
        beds.hide();
    }
};


function validate(callbackSuccess) {
    var valid = true;

    var username = $('#email').val();
    var role = $('#ms_example1').val();

    valid = valid & $('#email').valid();
    valid = valid & $('#firstName').valid();
    valid = valid & $('#lastName').valid();
    valid = valid & $('#password').valid();

    if (role == 'orm') {
        valid = valid & $('#phone').valid();
        valid = valid & $('#extphone').valid();

    }

    if (valid) {
        myAjax({
            url: "checkUserNameExists", genErrToast: false, data: {username: username}, success: function (result) {
                if (result !== true) {
                    new PNotify({
                        title: 'Error',
                        text: "The Email already exists.",
                        type: 'error'
                    });


                } else {
                    $('#validate').hide();
                    $('#next').trigger('click');
                    if (role && role === 'orm') {
                        $('#create-orls').hide();//prop("checked", true);
                    } else {
                        $('#join-orls').hide();//prop("checked", true);
                    }
                    $('#submit').show();

                    //if(role == 'orm'){
                    $('#bed-form').show();
                    //}
                    if (callbackSuccess) {
                        callbackSuccess();
                    }

                }
            }
        });
    }


}

function submitForm() {
    validate(validateRegisterForm);

}

var file;
$('#badgeId').change(function () {
    file = this.files[0];
});

$('#beds').ForceNumericOnly();


function validateRegisterForm() {

    var role = $('#ms_example1').val();
    var username = $('#email').val();
    var firstName = $('#firstName').val();
    var lastName = $('#lastName').val();
    var pass = $('#password').val();
    var phone = $('#phone').val();
    var extphone = $('#extphone').val();
    var hospitalId = $('#hospitalId').val();
    var beds = $('#beds').val();
    var catpcha = $('#g-recaptcha-response').val();


    var valid = true;
    if (role == 'orm') {
        valid = $('#beds').valid();
    }

    if (valid && !catpcha) {
        new PNotify({
            title: 'Notification',
            text: 'Please validate Captcha. Tick mark on I\'m not a robot',
            type: 'error'
        });
        return;

    }

    if (valid && file) {
        $('#register-form').submit();
    }
}
if (typeof json !== 'undefined' && json) {
    if (json.success === "false" || json.success === false) {
        new PNotify({
            title: 'Invalid',
            text: json.message,
            type: 'error'
        });


    } else {
        new PNotify({
            title: 'Notification',
            text: json.message,
            type: 'info'
        });

    }
}

