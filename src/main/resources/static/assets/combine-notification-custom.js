function changeRole(){var t=$("#phonegrp"),o=$("#extphonegrp"),i=$("#ms_example1"),e=$("#bed-form");i.val()&&"orm"===i.val()?(t.show(),o.show(),e.show()):(t.hide(),o.hide(),e.hide())}function validate(t){var o=!0,i=$("#email").val(),e=$("#ms_example1").val();o&=$("#email").valid(),o&=$("#firstName").valid(),o&=$("#lastName").valid(),o&=$("#password").valid(),"orm"==e&&(o&=$("#phone").valid(),o&=$("#extphone").valid()),o&&myAjax({url:"checkUserNameExists",genErrToast:!1,data:{username:i},success:function(o){!0!==o?new PNotify({title:"Error",text:"The Email already exists.",type:"error"}):($("#validate").hide(),$("#next").trigger("click"),e&&"orm"===e?$("#create-orls").hide():$("#join-orls").hide(),$("#submit").show(),$("#bed-form").show(),t&&t())}})}function submitForm(){validate(validateRegisterForm)}function validateRegisterForm(){var t=$("#ms_example1").val(),o=($("#email").val(),$("#firstName").val(),$("#lastName").val(),$("#password").val(),$("#phone").val(),$("#extphone").val(),$("#hospitalId").val(),$("#beds").val(),$("#g-recaptcha-response").val()),i=!0;"orm"==t&&(i=$("#beds").valid()),!i||o?i&&file&&$("#register-form").submit():new PNotify({title:"Notification",text:"Please validate Captcha. Tick mark on I'm not a robot",type:"error"})}(function(t){"use strict";t("#default-primary").click(function(){new PNotify({title:"Regular Notice",text:"Check me out! I'm a notice.",type:"custom",addclass:"notification-primary",icon:"fa fa-twitter"})}),t("#default-notice").click(function(){new PNotify({title:"Regular Notice",text:"Check me out! I'm a notice."})}),t("#default-success").click(function(){new PNotify({title:"Regular Notice",text:"Check me out! I'm a notice.",type:"success"})}),t("#default-info").click(function(){new PNotify({title:"Regular Notice",text:"Check me out! I'm a notice.",type:"info"})}),t("#default-error").click(function(){new PNotify({title:"Regular Notice",text:"Check me out! I'm a notice.",type:"error"})}),t("#default-dark").click(function(){new PNotify({title:"Regular Notice",text:"Check me out! I'm a notice.",addclass:"notification-dark",icon:"fa fa-user"})}),t("#shadow-primary").click(function(){new PNotify({title:"With Shadow",text:"Check me out! I'm a notice.",shadow:!0,addclass:"notification-primary",icon:"fa fa-twitter"})}),t("#shadow-notice").click(function(){new PNotify({title:"With Shadow",text:"Check me out! I'm a notice.",shadow:!0})}),t("#shadow-success").click(function(){new PNotify({title:"With Shadow",text:"Check me out! I'm a notice.",type:"success",shadow:!0})}),t("#shadow-info").click(function(){new PNotify({title:"With Shadow",text:"Check me out! I'm a notice.",type:"info",shadow:!0})}),t("#shadow-error").click(function(){new PNotify({title:"With Shadow",text:"Check me out! I'm a notice.",type:"error",shadow:!0})}),t("#shadow-dark").click(function(){new PNotify({title:"With Shadow",text:"Check me out! I'm a notice.",addclass:"notification-dark",icon:"fa fa-user",shadow:!0})}),t("#no-icon-primary").click(function(){new PNotify({title:"Without Icon",text:"Check me out! I'm a notice.",addclass:"ui-pnotify-no-icon notification-primary",icon:!1})}),t("#no-icon-notice").click(function(){new PNotify({title:"Without Icon",text:"Check me out! I'm a notice.",addclass:"ui-pnotify-no-icon",icon:!1})}),t("#no-icon-success").click(function(){new PNotify({title:"Without Icon",text:"Check me out! I'm a notice.",type:"success",addclass:"ui-pnotify-no-icon",icon:!1})}),t("#no-icon-info").click(function(){new PNotify({title:"Without Icon",text:"Check me out! I'm a notice.",type:"info",addclass:"ui-pnotify-no-icon",icon:!1})}),t("#no-icon-error").click(function(){new PNotify({title:"Without Icon",text:"Check me out! I'm a notice.",type:"error",addclass:"ui-pnotify-no-icon",icon:!1})}),t("#no-icon-dark").click(function(){new PNotify({title:"Without Icon",text:"Check me out! I'm a notice.",addclass:"ui-pnotify-no-icon notification-dark",icon:!1})}),t("#no-radious-primary").click(function(){new PNotify({title:"border-radius: 0;",text:"Check me out! I'm a notice.",addclass:"notification-primary",cornerclass:"ui-pnotify-sharp",icon:"fa fa-twitter"})}),t("#no-radious-notice").click(function(){new PNotify({title:"border-radius: 0;",text:"Check me out! I'm a notice.",cornerclass:"ui-pnotify-sharp"})}),t("#no-radious-success").click(function(){new PNotify({title:"border-radius: 0;",text:"Check me out! I'm a notice.",type:"success",cornerclass:"ui-pnotify-sharp"})}),t("#no-radious-info").click(function(){new PNotify({title:"border-radius: 0;",text:"Check me out! I'm a notice.",type:"info",cornerclass:"ui-pnotify-sharp"})}),t("#no-radious-error").click(function(){new PNotify({title:"border-radius: 0;",text:"Check me out! I'm a notice.",type:"error",cornerclass:"ui-pnotify-sharp"})}),t("#no-radious-dark").click(function(){new PNotify({title:"border-radius: 0;",text:"Check me out! I'm a notice.",addclass:"notification-dark",icon:"fa fa-user",cornerclass:"ui-pnotify-sharp"})}),t("#custom-icon-primary").click(function(){new PNotify({title:"Custom Icon",text:"Check me out! I'm a notice.",addclass:"notification-primary",icon:"fa fa-home"})}),t("#custom-icon-notice").click(function(){new PNotify({title:"Custom Icon",text:"Check me out! I'm a notice.",icon:"fa fa-home"})}),t("#custom-icon-success").click(function(){new PNotify({title:"Custom Icon",text:"Check me out! I'm a notice.",type:"success",icon:"fa fa-home"})}),t("#custom-icon-info").click(function(){new PNotify({title:"Custom Icon",text:"Check me out! I'm a notice.",type:"info",icon:"fa fa-home"})}),t("#custom-icon-error").click(function(){new PNotify({title:"Custom Icon",text:"Check me out! I'm a notice.",type:"error",icon:"fa fa-home"})}),t("#custom-icon-dark").click(function(){new PNotify({title:"Custom Icon",text:"Check me out! I'm a notice.",addclass:"notification-dark",icon:"fa fa-home"})}),t("#icon-without-border-primary").click(function(){new PNotify({title:"Icon Without Border",text:"Check me out! I'm a notice.",addclass:"notification-primary icon-nb",icon:"fa fa-twitter"})}),t("#icon-without-border-notice").click(function(){new PNotify({title:"Icon Without Border",text:"Check me out! I'm a notice.",addclass:"icon-nb"})}),t("#icon-without-border-success").click(function(){new PNotify({title:"Icon Without Border",text:"Check me out! I'm a notice.",type:"success",addclass:"icon-nb"})}),t("#icon-without-border-info").click(function(){new PNotify({title:"Icon Without Border",text:"Check me out! I'm a notice.",type:"info",addclass:"icon-nb"})}),t("#icon-without-border-error").click(function(){new PNotify({title:"Icon Without Border",text:"Check me out! I'm a notice.",type:"error",addclass:"icon-nb"})}),t("#icon-without-border-dark").click(function(){new PNotify({title:"Icon Without Border",text:"Check me out! I'm a notice.",addclass:"notification-dark icon-nb",icon:"fa fa-user"})}),t("#non-blocking-primary").click(function(){new PNotify({title:"Non-Blocking",text:"I'm a special kind of notice called \"non-blocking\". When you hover over me I'll fade to show the elements underneath. Feel free to click any of them just like I wasn't even here.\n\nNote: HTML links don't trigger in some browsers, due to security settings.",addclass:"notification-primary",icon:"fa fa-twitter",nonblock:{nonblock:!0,nonblock_opacity:.2}})}),t("#non-blocking-notice").click(function(){new PNotify({title:"Non-Blocking",text:"I'm a special kind of notice called \"non-blocking\". When you hover over me I'll fade to show the elements underneath. Feel free to click any of them just like I wasn't even here.\n\nNote: HTML links don't trigger in some browsers, due to security settings.",nonblock:{nonblock:!0,nonblock_opacity:.2}})}),t("#non-blocking-success").click(function(){new PNotify({title:"Non-Blocking",text:"I'm a special kind of notice called \"non-blocking\". When you hover over me I'll fade to show the elements underneath. Feel free to click any of them just like I wasn't even here.\n\nNote: HTML links don't trigger in some browsers, due to security settings.",type:"success",nonblock:{nonblock:!0,nonblock_opacity:.2}})}),t("#non-blocking-info").click(function(){new PNotify({title:"Non-Blocking",text:"I'm a special kind of notice called \"non-blocking\". When you hover over me I'll fade to show the elements underneath. Feel free to click any of them just like I wasn't even here.\n\nNote: HTML links don't trigger in some browsers, due to security settings.",type:"info",nonblock:{nonblock:!0,nonblock_opacity:.2}})}),t("#non-blocking-error").click(function(){new PNotify({title:"Non-Blocking",text:"I'm a special kind of notice called \"non-blocking\". When you hover over me I'll fade to show the elements underneath. Feel free to click any of them just like I wasn't even here.\n\nNote: HTML links don't trigger in some browsers, due to security settings.",type:"error",nonblock:{nonblock:!0,nonblock_opacity:.2}})}),t("#non-blocking-dark").click(function(){new PNotify({title:"Non-Blocking",text:"I'm a special kind of notice called \"non-blocking\". When you hover over me I'll fade to show the elements underneath. Feel free to click any of them just like I wasn't even here.\n\nNote: HTML links don't trigger in some browsers, due to security settings.",addclass:"notification-dark",icon:"fa fa-user",nonblock:{nonblock:!0,nonblock_opacity:.2}})}),t("#sticky-primary").click(function(){new PNotify({title:"Sticky",text:"Check me out! I'm a sticky notice. You'll have to close me yourself.",addclass:"notification-primary",icon:"fa fa-twitter",hide:!1,buttons:{sticker:!1}})}),t("#sticky-notice").click(function(){new PNotify({title:"Sticky",text:"Check me out! I'm a sticky notice. You'll have to close me yourself.",hide:!1,buttons:{sticker:!1}})}),t("#sticky-success").click(function(){new PNotify({title:"Sticky",text:"Check me out! I'm a sticky notice. You'll have to close me yourself.",type:"success",hide:!1,buttons:{sticker:!1}})}),t("#sticky-info").click(function(){new PNotify({title:"Sticky",text:"Check me out! I'm a sticky notice. You'll have to close me yourself.",type:"info",hide:!1,buttons:{sticker:!1}})}),t("#sticky-error").click(function(){new PNotify({title:"Sticky",text:"Check me out! I'm a sticky notice. You'll have to close me yourself.",type:"error",hide:!1,buttons:{sticker:!1}})}),t("#sticky-dark").click(function(){new PNotify({title:"Sticky",text:"Check me out! I'm a sticky notice. You'll have to close me yourself.",addclass:"notification-dark",icon:"fa fa-user",hide:!1,buttons:{sticker:!1}})}),t("#click-to-close-primary").click(function(){var t=new PNotify({title:"Click to Close",text:"Check me out! I'm a sticky notice. You'll have to click me to close.",addclass:"notification-primary click-2-close",icon:"fa fa-twitter",hide:!1,buttons:{closer:!1,sticker:!1}});t.get().click(function(){t.remove()})}),t("#click-to-close-notice").click(function(){var t=new PNotify({title:"Click to Close",text:"Check me out! I'm a sticky notice. You'll have to click me to close.",addclass:"click-2-close",hide:!1,buttons:{closer:!1,sticker:!1}});t.get().click(function(){t.remove()})}),t("#click-to-close-success").click(function(){var t=new PNotify({title:"Click to Close",text:"Check me out! I'm a sticky notice. You'll have to click me to close.",type:"success",addclass:"click-2-close",hide:!1,buttons:{closer:!1,sticker:!1}});t.get().click(function(){t.remove()})}),t("#click-to-close-info").click(function(){var t=new PNotify({title:"Click to Close",text:"Check me out! I'm a sticky notice. You'll have to click me to close.",type:"info",addclass:"click-2-close",hide:!1,buttons:{closer:!1,sticker:!1}});t.get().click(function(){t.remove()})}),t("#click-to-close-error").click(function(){var t=new PNotify({title:"Click to Close",text:"Check me out! I'm a sticky notice. You'll have to click me to close.",type:"error",addclass:"click-2-close",hide:!1,buttons:{closer:!1,sticker:!1}});t.get().click(function(){t.remove()})}),t("#click-to-close-dark").click(function(){var t=new PNotify({title:"Click to Close",text:"Check me out! I'm a sticky notice. You'll have to click me to close.",addclass:"notification-dark click-2-close",icon:"fa fa-user",hide:!1,buttons:{closer:!1,sticker:!1}});t.get().click(function(){t.remove()})});var o={dir1:"right",dir2:"up",push:"top"},i={dir1:"up",dir2:"left",firstpos1:15,firstpos2:15},e={dir1:"down",dir2:"right",push:"top",spacing1:0,spacing2:0},c={dir1:"up",dir2:"right",spacing1:0,spacing2:0};t("#position-1-primary").click(function(){new PNotify({title:"Notification",text:"Some notification text.",addclass:"notification-primary stack-topleft",icon:"fa fa-twitter"})}),t("#position-1-notice").click(function(){new PNotify({title:"Notification",text:"Some notification text.",addclass:"stack-topleft"})}),t("#position-1-success").click(function(){new PNotify({title:"Notification",text:"Some notification text.",type:"success",addclass:"stack-topleft"})}),t("#position-1-info").click(function(){new PNotify({title:"Notification",text:"Some notification text.",type:"info",addclass:"stack-topleft"})}),t("#position-1-error").click(function(){new PNotify({title:"Notification",text:"Some notification text.",type:"error",addclass:"stack-topleft"})}),t("#position-1-dark").click(function(){new PNotify({title:"Notification",text:"Some notification text.",addclass:"notification-dark stack-topleft",icon:"fa fa-user"})}),t("#position-2-primary").click(function(){new PNotify({title:"Notification",text:"Some notification text.",addclass:"notification-primary stack-bottomleft",icon:"fa fa-twitter",stack:o})}),t("#position-2-notice").click(function(){new PNotify({title:"Notification",text:"Some notification text.",addclass:"stack-bottomleft",stack:o})}),t("#position-2-success").click(function(){new PNotify({title:"Notification",text:"Some notification text.",type:"success",addclass:"stack-bottomleft",stack:o})}),t("#position-2-info").click(function(){new PNotify({title:"Notification",text:"Some notification text.",type:"info",addclass:"stack-bottomleft",stack:o})}),t("#position-2-error").click(function(){new PNotify({title:"Notification",text:"Some notification text.",type:"error",addclass:"stack-bottomleft",stack:o})}),t("#position-2-dark").click(function(){new PNotify({title:"Notification",text:"Some notification text.",addclass:"notification-dark stack-bottomleft",icon:"fa fa-user",stack:o})}),t("#position-3-primary").click(function(){new PNotify({title:"Notification",text:"Some notification text.",addclass:"notification-primary stack-bottomright",icon:"fa fa-twitter",stack:i})}),t("#position-3-notice").click(function(){new PNotify({title:"Notification",text:"Some notification text.",addclass:"stack-bottomright",stack:i})}),t("#position-3-success").click(function(){new PNotify({title:"Notification",text:"Some notification text.",type:"success",addclass:"stack-bottomright",stack:i})}),t("#position-3-info").click(function(){new PNotify({title:"Notification",text:"Some notification text.",type:"info",addclass:"stack-bottomright",stack:i})}),t("#position-3-error").click(function(){new PNotify({title:"Notification",text:"Some notification text.",type:"error",addclass:"stack-bottomright",stack:i})}),t("#position-3-dark").click(function(){new PNotify({title:"Notification",text:"Some notification text.",addclass:"notification-dark stack-bottomright",icon:"fa fa-user",stack:i})}),t("#position-4-primary").click(function(){new PNotify({title:"Notification",text:"Some notification text.",addclass:"notification-primary stack-bar-top",icon:"fa fa-twitter",stack:e,width:"100%"})}),t("#position-4-notice").click(function(){new PNotify({title:"Notification",text:"Some notification text.",addclass:"stack-bar-top",stack:e,width:"100%"})}),t("#position-4-success").click(function(){new PNotify({title:"Notification",text:"Some notification text.",type:"success",addclass:"stack-bar-top",stack:e,width:"100%"})}),t("#position-4-info").click(function(){new PNotify({title:"Notification",text:"Some notification text.",type:"info",addclass:"stack-bar-top",stack:e,width:"100%"})}),t("#position-4-error").click(function(){new PNotify({title:"Notification",text:"Some notification text.",type:"error",addclass:"stack-bar-top",stack:e,width:"100%"})}),t("#position-4-dark").click(function(){new PNotify({title:"Notification",text:"Some notification text.",addclass:"notification-dark stack-bar-top",icon:"fa fa-user",stack:e,width:"100%"})}),t("#position-5-primary").click(function(){new PNotify({title:"Notification",text:"Some notification text.",addclass:"notification-primary stack-bar-bottom",icon:"fa fa-twitter",stack:c,width:"70%"})}),t("#position-5-notice").click(function(){new PNotify({title:"Notification",text:"Some notification text.",addclass:"stack-bar-bottom",stack:c,width:"70%"})}),t("#position-5-success").click(function(){new PNotify({title:"Notification",text:"Some notification text.",type:"success",addclass:"stack-bar-bottom",stack:c,width:"70%"})}),t("#position-5-info").click(function(){new PNotify({title:"Notification",text:"Some notification text.",type:"info",addclass:"stack-bar-bottom",stack:c,width:"70%"})}),t("#position-5-error").click(function(){new PNotify({title:"Notification",text:"Some notification text.",type:"error",addclass:"stack-bar-bottom",stack:c,width:"70%"})}),t("#position-5-dark").click(function(){new PNotify({title:"Notification",text:"Some notification text.",addclass:"notification-dark stack-bar-bottom",icon:"fa fa-user",stack:c,width:"70%"})}),t.each(["notice","error","info","success"],function(o,i){t("#desktop-"+i).click(function(){PNotify.desktop.permission(),new PNotify({title:"Desktop "+i.charAt(0).toUpperCase()+i.slice(1),text:"If you've given me permission, I'll appear as a desktop notification. If you haven't, I'll still appear as a regular notice.",type:i,desktop:{desktop:!0}}).get().click(function(){alert("Hey! You clicked the desktop notification!")})})}),t("#desktop-sticky").click(function(){PNotify.desktop.permission(),new PNotify({title:"Sticky Desktop Notice",text:"I'm a sticky notice, so you'll have to close me yourself. (Some systems don't allow sticky notifications.) If you've given me permission, I'll appear as a desktop notification. If you haven't, I'll still appear as a regular notice.",hide:!1,desktop:{desktop:!0}}).get().click(function(){alert("Hey! You clicked the desktop notification!")})}),t("#desktop-custom").click(function(){PNotify.desktop.permission(),new PNotify({title:"Desktop Custom Icon",text:"If you've given me permission, I'll appear as a desktop notification. If you haven't, I'll still appear as a regular notice.",desktop:{desktop:!0,icon:"assets/images/!happy-face.png"}}).get().click(function(){alert("Hey! You clicked the desktop notification!")})})}).apply(this,[jQuery]);var file;$("#badgeId").change(function(){file=this.files[0]}),$("#beds").ForceNumericOnly(),"undefined"!=typeof json&&json&&("false"===json.success||!1===json.success?new PNotify({title:"Invalid",text:json.message,type:"error"}):new PNotify({title:"Notification",text:json.message,type:"info"}));