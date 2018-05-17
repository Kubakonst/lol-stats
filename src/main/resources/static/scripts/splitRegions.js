function onRegions(){

var sumNameElement = document.getElementById("summonerNameInput");

    if (sumNameElement.value.length > 0){
                            riotNameRestCall(sumNameElement.value,
                            (json) => {
                                             makeList(json.regions)},
                                         (e) => document.getElementById("error").innerHTML = e.error);
}

function makeList(regions){

var regions;

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
listItem.onclick = () => { sendReg(listItem.innerHTML) }

        }
//listItem.onclick = sendReg(regions[i]);
}
}