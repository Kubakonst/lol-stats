

$.ajax({
    contentType: 'application/json',
    data: JSON.stringify( {
        "a": "email",
        "b": "password"
    } ),
    dataType: 'json',
    success: function(data){
        console.log(data.sum);
    },
    error: function(){
        console.log("Device control failed");
    },
    processData: false,
    type: 'POST',
    url: '/test'
});