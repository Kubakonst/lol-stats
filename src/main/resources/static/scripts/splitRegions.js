function onRegions(){

var myNode = document.getElementById('regions');
while (myNode.firstChild) {
    myNode.removeChild(myNode.firstChild);
}

document.getElementById("search").disabled = true;

var loading = document.createElement("img");
document.getElementById('regions').appendChild(loading);
loading.src = "img/loadgif.gif";

var sumNameElement = document.getElementById("summonerNameInput");

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
document.getElementById("search").disabled = false;
listItem.onclick = () => { sendReg(listItem.innerHTML)}
var region = listItem.innerHTML;
loading.remove();

}
}

}