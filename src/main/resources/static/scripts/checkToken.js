function isMail(){

    var token = tokenLoad();

    if(token == null){
    window.location.href = "/signin.html"
    }
    else {
    var encodedPayload = token.split('.')[1];
    var decodedPayload = atob(encodedPayload);
    var payload = JSON.parse(decodedPayload);

    if (payload.email == undefined)
    {
        window.location.href = "/signin.html"
    }
}
}

function checkToken(){


    var token = tokenLoad();

    if(token == null){
    window.location.href = "/signin.html"
    }
    else{
    var encodedPayload = token.split('.')[1];
    var decodedPayload = atob(encodedPayload);
    var payload = JSON.parse(decodedPayload);

    if(payload.name == undefined)
    {
    window.location.href = "/registration-step-1.html"
    }

    if(payload.exp < Date.now()/1000)
    {
    window.location.href = "/signin.html"
    }

    }


}