function handleCommonError(response) {
    if (response.status === 401) {
        alert("[ " + response.status + " ] " + JSON.parse(response.responseText).message);
        window.localStorage.clear();
        location.href = "https://www.se-community.net/login";
    } else {
        alert("[ " + response.status + " ] " + JSON.parse(response.responseText).message);
        location.href = "https://www.se-community.net/boards";
    }
}