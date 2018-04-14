var baseUrl = "http://localhost:8333"
jsonHeader = new Headers({
    'Content-Type': 'application/json'
  })

function registrationRestCall(email, password, successCallback, errorCallback){
    var url = baseUrl + "/api/auth/register"
    var body = {email: email, password: password}

    post(
        url,
        body,
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
        successCallback,
        errorCallback
        )
}

function post(url, body, successCallback, errorCallback, expectedStatus=200){
    fetch(url,{
        method: "POST",
        headers: jsonHeader,
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