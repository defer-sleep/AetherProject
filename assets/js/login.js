console.log(axios)
async function auth_submit() {
    let email = document.getElementById("email");
    let password = document.getElementById("password");

    sendPost({
        adress: "api/auth/login",
        message: {
            email: document.getElementById("email").value,
            password: document.getElementById("password").value,
        }
    });
    // event.preventDefault();
    // const email = document.getElementById('email').value;
    // const password = document.getElementById('password').value;
    // try {
    //     const response = await fetch('/auth/try', {
    //         method: 'POST',
    //         headers: { 'Content-Type': 'application/json' },
    //         body: JSON.stringify({ email, password }),
    //         redirect: 'manual'
    //     });
    //
    //     if (response.ok) {
    //         const data = await response.json();
    //         if (data.redirect) {
    //             window.location.href = data.redirect;
    //         }
    //     } else {
    //         const errorText = await response.text();
    //         alert(`Login failed: ${errorText}`);
    //     }
    // } catch (error) {
    //     console.error('Error:', error);
    // }
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