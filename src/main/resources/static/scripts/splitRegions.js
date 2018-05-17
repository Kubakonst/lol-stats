function onRegions(){

var sumNameElement = document.getElementById("summonerNameInput");

    if (sumNameElement.value.length > 0){
                            riotNameRestCall(sumNameElement.value,
                            (json) => {
                                            regionSave(json.regions)
                                            makeList()},
                                         (e) => document.getElementById("error").innerHTML = e.error);
}

function makeList(){

var regions = regionLoad();

    var listContainer = document.createElement("ul");

        document.getElementById('regions').appendChild(listContainer);
        var listElement = document.createElement("li");
        listContainer.appendChild(listElement);
        var numberOfListItems = regions.length;
        for (var i = 0; i < numberOfListItems; ++i) {
            var listItem = document.createElement("BUTTON");
            listItem.innerHTML = regions[i];
            listElement.appendChild(listItem);

        }
//listItem.onclick = sendReg();
}
}