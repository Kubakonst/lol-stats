function onName(){
    var nameElement = document.getElementById("summonerNameInput");

    if (passwordElement.value.length>3){
                            nameRestCall(nameElement.value,
                                         () => window.location.href = "/main-page.html",
                                         (e) => document.getElementById("error").innerHTML = e.error);
                            }
}

function isMail(){

    var token = tokenLoad();
    var codPpayload = token.split('.')[1];
    var decStrPayload = atob(codPpayload);
    var payload = JSON.parse(decStrPayload);

    if (payload.mail = "undefined") || (payload.exp < Date.now()/1000)
    {
        window.location.href = "/signin.html"
    }
}


function isName(){

    var token = tokenLoad();
    var codPpayload = token.split('.')[1];
    var decStrPayload = atob(codPpayload);
    var payload = JSON.parse(decStrPayload);

    if(payload.SummonerName = "undefined") || (payload.exp < Date.now()/1000){

    window.location.href = "/registration-step-1.html"
    }
}