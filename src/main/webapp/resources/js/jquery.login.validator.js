$(document).ready(function(){

    $('.submit').click(function(e){
        validateForm(e);
    });

    function validateForm(e){
        var regExp=/^[a-zA-Z0-9-]{3,15}$/;
        var login = $('#login').val();
        var pass = $('#password').val();
        var inputVal = new Array(login, pass);

        $('.validationError').hide();

        if(!regExp.test(inputVal[0])){
            $('#login').after('<p class="validationError">Wrong login pattern, try again</p>');
            e.preventDefault();
        }

        if(!regExp.test(inputVal[1])) {
            $('#password').after('<p class="validationError">Wrong password pattern, try again</p>');
            e.preventDefault();
        }
    }
});