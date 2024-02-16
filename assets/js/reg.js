async function reg_submit() {
    sendPost({
        adress: "api/auth/addUser",
        message: {
            email: document.getElementById("email").value,
            password: document.getElementById("password").value,
            userName: document.getElementById("name").value,
            key: "admin",
        }
    });
}

function sendPost(message) {
    console.log("send", message)
    axios.post('/' + message.adress, message.message || {}, message.config || {})
        .then((response) => {
            if (message.f) message.f(response.data);
            console.log(response.data);
        })
        .catch((error) => {
            console.log(error)
        });
}
  