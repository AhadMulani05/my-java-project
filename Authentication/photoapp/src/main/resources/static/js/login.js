async function login() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const msg = document.getElementById("msg");

    const res = await fetch("/api/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    });

    const data = await res.json();

    if (res.ok) {
        window.location.href = "welcome.html";
    } else {
        msg.textContent = data.message;
    }
}