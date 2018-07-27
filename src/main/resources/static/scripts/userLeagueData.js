function onLeague(){

    {
                            riotSummonerLeagueCall(
                            (json) => {
                                             setTier(json[0].tier)
                                             console.log(json[0])
                                             setRank(json[0].rank)},
                                         (e) => document.getElementById("error").innerHTML = e.error);
}

function setTier(tier){

var sumTier = document.getElementById("summonerTier");
sumTier.innerText = tier;
console.log(sumTier);
}

function setRank(rank){

var sumLRank = document.getElementById("summonerRank");
sumLRank.innerText = rank;
console.log(sumLRank);
}
}