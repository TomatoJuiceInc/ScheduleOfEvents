/*$('#textarea').keydown(function(e) {
    if (this.value.length > 180)
        if ( !(e.which == '46' || e.which == '8' || e.which == '13') ) // backspace/enter/del
            e.preventDefault();
});*/

$(function() {
    $("textarea[maxlength]").bind('input propertychange', function() {
        var maxLength = $(this).attr('maxlength');
        if ($(this).val().length > maxLength) {
            $(this).val($(this).val().substring(0, maxLength));
        }
    })
});