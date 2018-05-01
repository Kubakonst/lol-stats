function onName(){
    var nameElement = document.getElementById("summonerNameInput");

    if (nameElement.value.length>0){
                            nameRestCall(nameElement.value,
                                         () => window.location.href = "/main-page.html",
                                         (e) => document.getElementById("error").innerHTML = e.error);
                            }
}

function isMail(){

    var token = tokenLoad();

    if(token == null){
    window.location.href = "/signin.html"
    }
    else {
    var encodedPayload = token.split('.')[1];
    var decodedPayload = atob(encodedPayload);
    var payload = JSON.parse(decodedPayload);

    if ((payload.email == "undefined") || (payload.exp < Date.now()/1000))
    {
        window.location.href = "/signin.html"
    }
}
}


function isName(){

    var token = tokenLoad();

    if(token == null){
    window.location.href = "/signin.html"
    }
    else{
    var encodedPayload = token.split('.')[1];
    var decodedPayload = atob(encodedPayload);
    var payload = JSON.parse(decodedPayload);

    if((payload.name == "undefined") || (payload.exp < Date.now()/1000))
    {
    window.location.href = "/registration-step-1.html"
    }
    }
}