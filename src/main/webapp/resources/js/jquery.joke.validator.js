$(document).ready(function(){

    $('.submit').click(function(e){
        validateForm(e);
    });

    function validateForm(e){
        var regExp=/^.{3,}$/;
        var text = $('#text').val();

        $('.validationError').hide();

        if(!regExp.test(text)){
            $('#text').after('<p class="validationError">Joke must be at least 3 characters long</p>');
            e.preventDefault();
        }
    }
});