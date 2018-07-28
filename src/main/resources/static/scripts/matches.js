function onMatches(){
{
                            riotMatchesRestCall(
                            (json) => {  makeListOfMatches(json.matches)},
                                         (e) => document.getElementById("error").innerHTML = e.error);
}

function makeListOfMatches(matches){
console.log(matches);

    var listContainer = document.createElement("ul");
        document.getElementById('matches').appendChild(listContainer);
        var listElement = document.createElement("ul");
        listContainer.appendChild(listElement);
        var numberOfListItems = matches.length;
        console.log(numberOfListItems);
        for (var i = 0; i < numberOfListItems; ++i) {

            var chamIconId = document.createElement("img");
            chamIconId.src = "img/riotimg/champion/" + matches[i].championName + ".png";
            listElement.appendChild(chamIconId);
            console.log(chamIconId);

            var chamItem =document.createElement("p");
            chamItem.innerText = matches[i].championName;
            listElement.appendChild(chamItem);

            var timeItem =document.createElement("p");
            timeItem.innerText = convert(matches[i].gameDuration);
            listElement.appendChild(timeItem);

            var roleItem =document.createElement("p");
            roleItem.innerText = matches[i].lane;
            listElement.appendChild(roleItem);

            var win =document.createElement("p");
            if(matches[i].win = true){
            win.innerText = "true";
            win.style.color = "green";
            }
            else
            {win.innerText = "false"
            win.style.color = "red";
            }
            listElement.appendChild(win);

            var kda =document.createElement("p");
            kda.innerText = matches[i].kda;
            listElement.appendChild(kda);
}
}
function convert(value) {
    return Math.floor(value / 60) + ":" + (value % 60 ? value % 60 : '00')
}

}