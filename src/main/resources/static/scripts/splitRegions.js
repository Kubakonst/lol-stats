function onRegions(){

var sumNameElement = document.getElementById("summonerNameInput");

    if (sumNameElement.value.length > 0){
                            riotNameRestCall(sumNameElement.value,
                                            regionSave(json.region),
                                         (e) => document.getElementById("error").innerHTML = e.error);
                            }
}

function makeUl(){

var regions = regionLoad();

    for(var i = 0; i < regions[i].length; i++)
    {
       var btn = document.createElement("BUTTON");
             var reg = document.createTextNode(regions[i]);
             btn.appendChild(t);
       			document.body.appendChild(btn);
       			btn.onclick = sendReg;
    }

}

function sendReg(){

var sumNameElement = document.getElementById("summonerNameInput");

       nameRestCall(sumNameElement.value,
                            reg,
                            () => window.location.href = "/registration-step-1.html",
                            (e) => document.getElementById("error").innerHTML = e.error);
}