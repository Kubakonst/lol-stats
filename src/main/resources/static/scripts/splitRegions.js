function onRegions(){
console.log("dasdasas");
//setInterval(function(){ alert("Hello"); }, 1000);
var loading = document.createElement("img");
document.getElementById('regions').appendChild(loading);
loading.src = "img/loadgif.gif";
console.log(loading);

var sumNameElement = document.getElementById("summonerNameInput");
var name = document.getElementById("summonerName");
name.innerHTML = sumNameElement.value;

    if (sumNameElement.value.length > 0){
                            riotNameRestCall(sumNameElement.value,
                            (json) => {
                                             makeList(json.regions)},
                                         (e) => document.getElementById("error").innerHTML = e.error);
}

function makeList(regions){

    var listContainer = document.createElement("ul");
        document.getElementById('regions').appendChild(listContainer);
        var listElement = document.createElement("li");
        listContainer.appendChild(listElement);
        var numberOfListItems = regions.length;
        for (var i = 0; i < numberOfListItems; ++i) {
            var listItem = document.createElement("BUTTON");
            listItem.innerHTML = regions[i];
            console.log(listItem.innerHTML);
            listElement.appendChild(listItem);
listItem.onclick = () => { sendReg(listItem.innerHTML)}
var region = listItem.innerHTML;

        }
//listItem.onclick = sendReg(regions[i]);
}

}