async function signup() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const msg = document.getElementById("msg");

    const res = await fetch("/api/signup", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    });

    const data = await res.json();

    if (res.ok) {
        window.location.href = "login.html";
    } else {
        msg.textContent = data.message;
    }
}