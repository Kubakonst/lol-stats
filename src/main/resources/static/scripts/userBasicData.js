function onData(){

    {
                            riotRegionRestCall(
                            (json) => {
                                             setLevel(json.summonerLevel)
                                             setIcon(json.profileIconId)},
                                         (e) => document.getElementById("error").innerHTML = e.error);
}

function setLevel(summonerLevel){

var summonerLevel;
var sumLevel = document.getElementById("summonerLevel");
sumLevel.innerText = summonerLevel;
console.log(sumLevel);
}

function setIcon(profileIconId){

var profileIconId;
var profIconId = document.getElementById("profileIconId");
profIconId.innerText = profileIconId;
console.log(profIconId);
}
}