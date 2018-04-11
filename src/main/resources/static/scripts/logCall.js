
function loginLogCall(email, password){
$.ajax({
    contentType: 'application/json',
    data: JSON.stringify( {
        "email": email,
        "password": password
    } ),
    dataType: 'json',
    success: function(data){
    },
    error: function(data){
        console.log(data);
//        window.location.href = 'registration-step-1-view.html';
    },
    processData: false,
    type: 'POST',
    url: '/api/auth/login'
});
}