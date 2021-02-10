<link ref="resources/fonts/glyphicons-halflings-regular.woff"/>
<link ref="resources/fonts/glyphicons-halflings-regular.ttf"/>
<link ref="resources/fonts/glyphicons-halflings-regular.woff2"/>
<!-- Vendor -->
<script src="resources/vendor/jquery/jquery.js"></script>
<script src="resources/combine-ui.js"></script>
<script src="resources/combine-chart.js"></script>
<script src="resources/combine-bootstrap-date-popup.js"></script>
<script src="resources/combine-dropzone-tag.js"></script>
<script src="resources/combine-markdown.js"></script>
<script src="resources/combine-codemirror.js"></script>
<script src="resources/combine-bootstrap1.js"></script>
<script src="resources/combine-theme.js"></script>
<script src="resources/combine-form.js"></script>
<script src="resources/combine-datatable.js"></script>



<script type="text/javascript">
    function isNumber(evt) {
        evt = (evt) ? evt : window.event;
        var charCode = (evt.which) ? evt.which : evt.keyCode;
        return !(charCode > 31 && (charCode < 48 || charCode > 57));
    }

    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    })


</script>

<script type="text/javascript">

    jQuery.fn.ForceNumericOnly = function()
    {
        return this.each(function()
        {
            $(this).keydown(function(e)
            {
                var key = e.charCode || e.keyCode || 0;
                // allow backspace, tab, delete, enter, arrows, numbers and keypad numbers ONLY
                // home, end, period, and numpad decimal
                return (
                        key == 8 ||
                        key == 9 ||
                        key == 13 ||
                        key == 46 ||
                        key == 110 ||
                        key == 190 ||
                        (key >= 35 && key <= 40) ||
                        (key >= 48 && key <= 57) ||
                        (key >= 96 && key <= 105));
            });

            $(this).keypress(function(e)
            {
                var k = e.which;
                var ok = k >= 65 && k <= 90 || // A-Z
                    k >= 97 && k <= 122 || // a-z
                    k >= 48 && k <= 57; // 0-9

                if (!ok){
                    e.preventDefault();
                }



            });
        });
    };

</script>


<script src="resources/combine-notification-custom.js"></script>
<script src="resources/combine.js"></script>
<script type="text/javascript">
    $('select').select2();
</script>



