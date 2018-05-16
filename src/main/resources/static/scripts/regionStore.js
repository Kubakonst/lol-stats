function regionSave(region) {
    window.localStorage.setItem("region", region);
}

function regionLoad() {
    return window.localStorage.getItem("region");
}