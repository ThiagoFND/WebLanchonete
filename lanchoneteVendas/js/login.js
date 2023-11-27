const loginForm = document.getElementById("login-form");

loginForm.addEventListener("submit", async (event) => {
    event.preventDefault();

    const login = document.getElementById("login").value;
    const password = document.getElementById("password").value;

    try {
        const token = await authenticateUser(login, password);
        if (token) {
            // Salve o token no armazenamento local ou de sessão para uso posterior
            sessionStorage.setItem("token", token);

                // Redirecione o usuário para a página listar.html
            window.location.href = "empresa.html";
            // Exiba um alerta de sucesso
            alert("Login efetuado com sucesso");
        } else {
            // Exiba um alerta de erro
            alert("Login ou senha inválidos");
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
        // Exiba um alerta de erro em caso de erro na requisição
        alert("Ocorreu um erro na requisição. Tente novamente.");
    }
});

// Função para autenticar o usuário e obter o token JWT
async function authenticateUser(login, password) {
    try {
        const response = await fetch("http://localhost:8080/api/v1/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ login, password })
        });

        if (response.ok) {
            const data = await response.json();
            return data.token;
        }
        return null;
    } catch (error) {
        console.error("Erro na requisição:", error);
        return null;
    }
}

// Função para mostrar mensagens de erro na interface do usuário
function showError(errorMessage) {
    // Exiba a mensagem de erro na interface do usuário, por exemplo, em um elemento HTML
    const errorElement = document.getElementById("error-message");
    errorElement.textContent = errorMessage;
}
