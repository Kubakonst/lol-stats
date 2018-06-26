function onMatches(){

{
                            riotMatchesRestCall(
                            (json) => {
                                             makeListOfMatches(json.matches)},
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
            console.log(matches[i]);

            var chamItem =document.createElement("p");
            chamItem.innerText = matches[i].champion;
            console.log(chamItem.innerHTML);
            listElement.appendChild(chamItem);

            var roleItem =document.createElement("p");
            roleItem.innerText = matches[i].lane;
            console.log(roleItem.innerHTML);
            listElement.appendChild(roleItem);
}
}
}