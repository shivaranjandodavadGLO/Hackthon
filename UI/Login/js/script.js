document.addEventListener("DOMContentLoaded", function() {
    // Handle Login Form Submission
    document.getElementById("loginForm")?.addEventListener("submit", function(event) {
        event.preventDefault();
        alert("Login Successful");
    });

    // Handle Registration Form Submission
    document.getElementById("registerForm")?.addEventListener("submit", function(event) {
        event.preventDefault();

        const userData = {
            firstName: document.getElementById("firstName").value,
            lastName: document.getElementById("lastName").value,
            email: document.getElementById("email").value,
            password: document.getElementById("password").value,
            role: document.getElementById("role").value
        };

        fetch("http://localhost:8080/api/auth/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(userData)
        })
        .then(response => response.json())
        .then(data => alert("User Registered Successfully!"))
        .catch(error => console.error("Error:", error));
    });
});
