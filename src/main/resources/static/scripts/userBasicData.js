function onData(){

    {
                            riotRegionRestCall(
                            (json) => {
                                             setLevel(json.summonerLevel)
                                             setIcon(json.profileIconId)},
                                         (e) => document.getElementById("error").innerHTML = e.error);
}

function setLevel(summonerLevel){

var sumLevel = document.getElementById("summonerLevel");
sumLevel.innerText = summonerLevel;
console.log(sumLevel);
}

function setIcon(profileIconId){

var profIconId = document.getElementById("profileIconId");
profIconId.src = "img/riotimg/profileicon/" + profileIconId + ".png";
console.log(profIconId);
}
}