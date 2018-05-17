function sendReg(){

 var sumNameElement = document.getElementById("summonerNameInput");
 var regionElement = document.getElementById('regions');

                            nameRestCall(sumNameElement.value, regionElement.value,

                            (json) => {
                                                tokenSave(json.bearer)
                                                window.location.href = "/main-page.html"
                                                },
                                         (e) => document.getElementById("error").innerHTML = e.error);
}