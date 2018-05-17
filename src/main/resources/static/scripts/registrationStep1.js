function sendReg(region){
console.log(region);
 var sumNameElement = document.getElementById("summonerNameInput");
// var regionElement = document.getElementById('regions');

                            nameRestCall(sumNameElement.value, region,

                            (json) => {
                                                tokenSave(json.bearer)
                                                window.location.href = "/main-page.html"
                                                },
                                         (e) => document.getElementById("error").innerHTML = e.error);
}