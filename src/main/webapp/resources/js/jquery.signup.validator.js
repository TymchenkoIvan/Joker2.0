$(document).ready(function(){

    $('.submit').click(function(e){
        validateForm(e);
    });

    function validateForm(e){
        var regExpMail=/^\w+[\w-.]*@\w+((-\w+)|(\w*))\.[a-z]{2,3}$/;
        var regExp=/^[a-zA-Z0-9-]{3,15}$/;

        var login = $('#login').val();
        var mail = $('#mail').val();
        var telephone = $('#telephone').val();
        var pass = $('#password').val();
        var confirm = $('#confirm').val();

        var inputVal = new Array(login, mail, telephone, pass, confirm);

        $('.validationError').hide();

        if(!regExp.test(inputVal[0])){
            $('#login').after('<p class="validationError">Wrong login pattern, try again</p>');
            e.preventDefault();
        }

        if(!regExpMail.test(inputVal[1])) {
            $('#mail').after('<p class="validationError">Wrong mail pattern, try again</p>');
            e.preventDefault();
        }

        if((inputVal[2].length > 0) && (!regExp.test(inputVal[2]))){
            $('#telephone').after('<p class="validationError">Wrong telephone pattern, try again</p>');
            e.preventDefault();
        }

        if(!regExp.test(inputVal[3])) {
            $('#password').after('<p class="validationError">Wrong password pattern, try again</p>');
            e.preventDefault();
        }

        if(inputVal[3]!=inputVal[4]){
            $('#confirm').after('<p class="validationError">Wrong confirmation, try again</p>');
            e.preventDefault();
        }
    }
});