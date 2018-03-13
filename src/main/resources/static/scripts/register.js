console.log("Hello");

function onRegister() {

    var emailElement=document.getElementById("emailInput");
    var passwordElement=document.getElementById("passwordInput");
    var repasswordElement=document.getElementById("repasswordInput");
    if (passwordElement.value == repasswordElement.value){
        registrationRestCall(emailElement.value,passwordElement.value);
        }
else{
    document.getElementById("error").innerHTML = "Put same passwords";
    }
}