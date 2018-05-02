function onName(){

    var sumNameElement = document.getElementById("summonerNameInput");

    if (sumNameElement.value.length > 0){
                            nameRestCall(sumNameElement.value,
                            (json) => {
                                                tokenSave(json.bearer)
                                                window.location.href = "/main-page.html"
                                                },
                                         (e) => document.getElementById("error").innerHTML = e.error);
                            }
}