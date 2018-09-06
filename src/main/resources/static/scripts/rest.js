var baseUrl = window.location.origin;
var jsonHeader = new Headers({
    'Content-Type': 'application/json'
  })

function registrationRestCall(email, password, successCallback, errorCallback){
    var url = baseUrl + "/api/auth/register"
    var body = {email: email, password: password}

    post(
        url,
        body,
        jsonHeader,
        successCallback,
        errorCallback
        )
}
function loginRestCall(email, password, successCallback, errorCallback){
    var url = baseUrl + "/api/auth/login"
    var body = {email: email, password: password}

    post(
        url,
        body,
        jsonHeader,
        successCallback,
        errorCallback
        )
}

function nameRestCall(sumName, region, successCallback, errorCallback){
    var url = baseUrl + "/api/summoner/name"
    var body = {sumName: sumName, region: region}
    var header = new Headers({
    'Content-Type': 'application/json',
    'Authorization': tokenLoad()
    })

    post(
        url,
        body,
        header,
        successCallback,
        errorCallback
        )
}

function statsNameRestCall(name, successCallback, errorCallback){
    var url = baseUrl + "/api/stats/summoner/regions?name="+name;
    var header = new Headers({
            'Content-Type': 'application/json',
            "Authorization": tokenLoad()
            })

    get(
        url,
        header,
        jsonHeader,
        successCallback,
        errorCallback
        )
}


function statsRegionRestCall(successCallback, errorCallback){
    var url = baseUrl + "/api/stats/summoner/basicInfo"
    var header = new Headers({
        'Content-Type': 'application/json',
        "Authorization": tokenLoad()
        })

    get(
        url,
        header,
        successCallback,
        errorCallback
        )
}

function statsSummonerLeagueRestCall(successCallback, errorCallback){
    var url = baseUrl + "/api/stats/summoner/league"
    var header = new Headers({
        'Content-Type': 'application/json',
        "Authorization": tokenLoad()
        })

    get(
        url,
        header,
        successCallback,
        errorCallback
        )
}

function statsMatchesRestCall(successCallback, errorCallback){
    var url = baseUrl + "/api/stats/summoner/matches"
    var header = new Headers({
        'Content-Type': 'application/json',
        "Authorization": tokenLoad()
        })

    get(
        url,
        header,
        successCallback,
        errorCallback
        )
}

function post(url, body, headers, successCallback, errorCallback, expectedStatus=200){
    fetch(url,{
        method: "POST",
        headers: headers,
        body: JSON.stringify(body)
    })
    .then( res => {
        if( res.status == expectedStatus ) {
            return res.json().then(json => {
                successCallback(json)
            })
        } else {
            return res.json().then(json => {
                errorCallback(json)
            })
        }
    })
}

function get(url, headers, successCallback, errorCallback, expectedStatus=200){
    fetch(url,{
        method: "GET",
        headers: headers
    })
    .then( res => {
        if( res.status == expectedStatus ) {
            return res.json().then(json => {
                successCallback(json)
            })
        } else {
            return res.json().then(json => {
                errorCallback(json)
            })
        }
    })
}