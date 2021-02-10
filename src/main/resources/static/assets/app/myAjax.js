/**
 * Created by satyam on 9/18/2015.
 */


function myAjax(configs){
    var defaultSuccess = function(resp){
        if(resp['redirectTo']){
            window.href = resp['redirectTo'];
        }
        if(success){
            success(resp);
        }
    };

    var defaultError = function(jqXHR ,textStatus, errorThrown){
        if(generateErrorToast && textStatus !== 'success'){
            new PNotify({
                title: 'Error',
                text: errMsg  || "Seems Something Went wrong.",
                type: 'error'
            });
        }
        if(error){
            error(jqXHR, textStatus, errorThrown);
        }
    };
    var url  = configs.url;
    var data = configs.data || {};
    var success = configs.success ;
    var error = configs.error;
    var method = configs.method || 'GET';
    var async = configs.async || true;
    var generateErrorToast = true;
    if(configs.genErrToast === false) {
      generateErrorToast = false;
    } else {
      generateErrorToast = true;
    }
    var errMsg = configs.errMsg ;
    var contentType = configs.contentType || "application/json";



    if(url){
        $.ajax({
            url : url,
            type : method,
            error: defaultError,
            success: defaultSuccess,
            data : data,
            async : async,
            contentType : contentType

        })
            .done(defaultError)
            .fail(defaultError);
    }

};