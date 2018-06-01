function regionSave(regions) {
    window.localStorage.setItem("region", regions);
}

function regionLoad() {
    return window.localStorage.getItem("region");
}