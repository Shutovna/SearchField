const input = document.querySelector(".search-box__search-input");
const popup = document.querySelector(".search-box__search-popup");
const form = document.querySelector("form");

function sendSubStrRequest(substr) {
    const request = new XMLHttpRequest();
    let url = "searchSubString?str=" + substr;
    request.open("GET", url);
    request.setRequestHeader("Content-type", "application/json; charset=utf-8");
    request.send();
    console.log("Request url: " + url)
    popup.innerHTML = "";

    request.addEventListener("load", () => {
        console.log("Response: " + request.response);
        if (request.status === 200) {
            console.log("OK");
            if (request.response.length === 0) {
                popup.style.cssText = "display: none";
            } else {
                popup.style.cssText = "display: block";
                let resObj = JSON.parse(request.response);
                resObj.forEach(str => {
                    let item = document.createElement("div");
                    item.classList.add("search-box__search-item");
                    item.innerText = str;
                    popup.append(item);
                })
            }

        } else {
            console.log("ERROR");
        }
    })
}

function sendStrRequest(str) {
    const request = new XMLHttpRequest();
    let url = "searchString?str=" + str;
    request.open("GET", url);
    request.setRequestHeader("Content-type", "application/json; charset=utf-8");
    request.send();
    console.log("Request url: " + url)

    request.addEventListener("load", () => {
        console.log("Response: " + request.response);
        if (request.status === 200) {
            console.log("OK");
            let results = document.querySelectorAll(".search-result");
            results.forEach(item=> item.remove());

            const p = document.createElement("p")
            p.classList.add("search-result")
            p.innerText = "Вы искали: " + request.response
            form.append(p);

        } else {
            console.log("ERROR");
        }
    })
}

input.addEventListener("input", (e) => {
    sendSubStrRequest(e.target.value);
})



popup.addEventListener("click", (e) => {
    if(e.target.classList.contains("search-box__search-item")) {
        sendStrRequest(e.target.innerText)
    }
})