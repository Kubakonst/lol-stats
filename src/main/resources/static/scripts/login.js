function onLogin() {

    var emailElement = document.getElementById("emailInput");
    var passwordElement = document.getElementById("passwordInput");
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(emailElement.value)) {
        if (passwordElement.value.length > 3) {
            loginRestCall(emailElement.value,
                passwordElement.value,
                (json) => {
                    tokenSave(json.bearer)
                    window.location.href = "/main-page.html"
                },
                (e) => document.getElementById("error").innerHTML = e.error);
        } else {
            document.getElementById("error").innerHTML = "Too short password";
        }
    } else {
        document.getElementById("error").innerHTML = "Wrong email addres";
    }
}
