function onData(){

    {
                            statsRegionRestCall(
                            (json) => {
                                             setLevel(json.summonerLevel)
                                             setName(json.name)
                                             setIcon(json.profileIconId)},
                                         (e) => document.getElementById("error").innerHTML = e.error);
}

function setLevel(summonerLevel){

var sumLevel = document.getElementById("summonerLevel");
sumLevel.innerText = summonerLevel;
console.log(sumLevel);
}

function setName(summonerName){

var sumName = document.getElementById("summonerName");
sumName.innerText = summonerName;
console.log(sumName);
}

function setIcon(profileIconId){

var profIconId = document.getElementById("profileIconId");
profIconId.src = "img/riotimg/profileicon/" + profileIconId + ".png";
console.log(profIconId);
}
}