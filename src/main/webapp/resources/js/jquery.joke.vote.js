function sendVote(id, vote){

    var jokeId = id;
    var action = vote;

    $.ajax({
        type: "POST",
        url: 'http://localhost:8080/joker.com/rest/joke/' + jokeId + '/' + action,
        dataType: 'json',
        success: function (response) {
            $('.error').empty();
            $('#joke_vote_' + response.id).html('likes: ' + response.likes + ' | dislikes: ' + response.dislikes);
        },
        error: function () {
            $(".error").empty().html('<p>ERROR: You can\'t rate one joke twice</p>');
        }
    });
}