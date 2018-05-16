function onRegions(){

var sumNameElement = document.getElementById("summonerNameInput");

    if (sumNameElement.value.length > 0){
                            riotNameRestCall(sumNameElement.value,
                                            regionSave(json.region)
                                            maleUL()
                                         (e) => document.getElementById("error").innerHTML = e.error);
                            }
}

function makeUL(){

var regions = regionLoad();

    for(var i = 0; j < regions[i].length; i++)
    {
       var btn = document.createElement("BUTTON");
             var reg = document.createTextNode(key[j]);
             btn.appendChild(t);
       			document.body.appendChild(btn);
    }

}