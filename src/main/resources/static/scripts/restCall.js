
function registrationRestCall(email, password){
$.ajax({
    contentType: 'application/json',
    data: JSON.stringify( {
        "email": email,
        "password": password
    } ),
    dataType: 'json',
    success: function(data){
    window.location.href = 'http://www.example.com';
    },
    error: function(){
        console.log("Device control failed");
    },
    processData: false,
    type: 'POST',
    url: '/register'
});
}