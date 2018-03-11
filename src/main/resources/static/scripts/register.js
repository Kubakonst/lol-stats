console.log("Hello");

function onRegister() {

    var emailElement=document.getElementById("emailInput");
    var passwordElement=document.getElementById("passwordInput");
    var repasswordElement=document.getElementById("repasswordInput");
    if (passwordElement.value == repasswordElement.value){
        registrationRestCall(emailElement.value,passwordElement.value);
        console.log("poszlo")
        }
else{
    alert ("Put same passwords!");
    console.log("jest alert")
    }
}