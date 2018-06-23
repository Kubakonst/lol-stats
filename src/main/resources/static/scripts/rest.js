var baseUrl = "http://localhost:8333"
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
        errorCallback,
        201
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

function riotNameRestCall(sumName, successCallback, errorCallback){
    var url = baseUrl + "/api/summoner/riotName"
    var body = {sumName: sumName}

    post(
        url,
        body,
        jsonHeader,
        successCallback,
        errorCallback
        )
}

function riotDataRestCall(successCallback, errorCallback){
    var url = baseUrl + "/api/summoner/riotData"
    var body =" "
    var header = new Headers({
        'Content-Type': 'application/json',
        "Authorization": tokenLoad()
        })

    post(
        url,
        body,
        header,
        successCallback,
        errorCallback
        )
}

function riotMatchesRestCall(successCallback, errorCallback){
    var url = baseUrl + "/api/summoner/matches"
    var body =" "
    var header = new Headers({
        'Content-Type': 'application/json',
        "Authorization": tokenLoad()
        })

    post(
        url,
        body,
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