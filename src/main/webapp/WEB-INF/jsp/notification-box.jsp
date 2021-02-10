
<script type="text/javascript">

    if (typeof success !== 'undefined') {
        var stack_topleft = {"dir1": "down", "dir2": "right", "push": "top"};
        var stack_bottomleft = {"dir1": "right", "dir2": "up", "push": "top"};
        var stack_bottomright = {"dir1": "up", "dir2": "left", "firstpos1": 15, "firstpos2": 15};
        var stack_bar_top = {"dir1": "down", "dir2": "right", "push": "top", "spacing1": 0, "spacing2": 0};
        var stack_bar_bottom = {"dir1": "up", "dir2": "right", "spacing1": 0, "spacing2": 0};
        if (success !== true) {
            var notice = new PNotify({
                title: 'Notification',
                type: 'error',
                text: message || 'Something Went Wrong. Please try again',
                stack: stack_bar_top,
                width: "40%",
                top:'2%',
                left:'20%',
                icon:'fa fa-exclamation-circle',
                addclass: 'click-2-close stack-bar-top custom-stack-bar-top',
                hide: false,
                buttons: {
                    closer: true,
                    sticker: false
                }
            });
            notice.get().click(function () {
                notice.remove();
            });
        } else {
            var notice = new PNotify({
                title: 'Notification',
                type: 'success',
                text: message,
                stack: stack_bar_top,
                width: "40%",
                top:'2%',
                left:'20%',
                icon:'fa fa-info-circle',
                addclass: 'click-2-close stack-bar-top custom-stack-bar-top',
                hide: false,
                buttons: {
                    closer: true,
                    sticker: false
                }
            });
            notice.get().click(function () {
                notice.remove();
            });
        }
    }


</script>

