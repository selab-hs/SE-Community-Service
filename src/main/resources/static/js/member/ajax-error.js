function handleCommonError(response) {
    if (response.status === 401) {
        alert("[ " + response.status + " ] " + JSON.parse(response.responseText).message);
        window.localStorage.clear();
        location.href = "http://localhost:8080/login";
    } else {
        alert("[ " + response.status + " ] " + JSON.parse(response.responseText).message);
        location.href = "http://localhost:8080/boards";
    }
}