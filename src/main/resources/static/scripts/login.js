console.log("Hello");

function onLogin() {

    var emailElement=document.getElementById("emailInput");
    var passwordElement=document.getElementById("passwordInput");
    var repasswordElement=document.getElementById("repasswordInput");
    if (passwordElement.value == repasswordElement.value){
        if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(emailElement.value)){
                if (passwordElement.value.length>3){
                        loginLogCall(emailElement.value,passwordElement.value);
                        }
                    else{
                        document.getElementById("error").innerHTML = "Too short password";
                    }
                }
            else{
                document.getElementById("error").innerHTML = "Wrong email addres";
            }
        }
    else{
        document.getElementById("error").innerHTML = "Not the same passwords";
    }
}