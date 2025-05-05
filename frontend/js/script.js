const username = "random";
const password = "user";
const basicAuth = 'Basic ' + btoa(username + ":" + password);

fetch("http://localhost:8080/api/videos/json", {
    headers: {
        'Authorization': basicAuth
    }
})
    .then((response) => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then((videoArray) =>  {
        const list = document.querySelector("#video-list");
        for (const video of videoArray) {
            const li = document.createElement("li");
            li.classList.add("list-group-item");
            li.innerText = `${video.compilationName}`;
            list.appendChild(li);
        }
    })
    .catch((error) => console.error(error));