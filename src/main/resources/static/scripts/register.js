console.log("Hello");

function onRegister() {

    var emailElement=document.getElementById("emailInput");
    var passwordElement=document.getElementById("passwordInput");
    var repasswordElement=document.getElementById("repasswordInput");
    if (passwordElement.value == repasswordElement.value){
        if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(emailElement.value)){
                if (passwordElement.value.length>3){
                        registrationRestCall(emailElement.value,
                                             passwordElement.value,
                                             () => window.location.href = "/registration-step-1.html",
                                             (e) => document.getElementById("error").innerHTML = e.error);
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