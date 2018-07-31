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

            var item0Icon = document.createElement("img");
            if(matches[i].item0 != 0){
            item0Icon.src = "img/riotimg/item/" + matches[i].item0 + ".png";
            listElement.appendChild(item0Icon);}

            var item1Icon = document.createElement("img");
            if(matches[i].item1 != 0){
            item1Icon.src = "img/riotimg/item/" + matches[i].item1 + ".png";
            listElement.appendChild(item1Icon);}

            var item2Icon = document.createElement("img");
            if(matches[i].item2 != 0){
            item2Icon.src = "img/riotimg/item/" + matches[i].item2 + ".png";
            listElement.appendChild(item2Icon);}

            var item3Icon = document.createElement("img");
            if(matches[i].item3 != 0){
            item3Icon.src = "img/riotimg/item/" + matches[i].item3 + ".png";
            listElement.appendChild(item3Icon);}

            var item4Icon = document.createElement("img");
            if(matches[i].item4 != 0){
            item4Icon.src = "img/riotimg/item/" + matches[i].item4 + ".png";
            listElement.appendChild(item4Icon);}

            var item5Icon = document.createElement("img");
            if(matches[i].item5 != 0){
            item5Icon.src = "img/riotimg/item/" + matches[i].item5 + ".png";
            listElement.appendChild(item5Icon);}

            var win =document.createElement("p");
            if(matches[i].win == true){
            win.innerText = "win";
            win.style.color = "green";
            }
            else
            {win.innerText = "lose"
            win.style.color = "red";
            }
            listElement.appendChild(win);

            var kda =document.createElement("p");
            kda.innerText = "KDA: " + Round(matches[i].kda, 2);
            listElement.appendChild(kda);


}
}
function convert(value) {
    return Math.floor(value / 60) + ":" + (value % 60 ? value % 60 : '00')
}

function Round(n, k)
{
    var factor = Math.pow(10, k);
    return Math.round(n*factor)/factor;
}

}