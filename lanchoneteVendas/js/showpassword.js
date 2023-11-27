// MOSTRAR SENHA
const passwordInput = document.getElementById("password");
const showPasswordCheckbox = document.getElementById("showPasswordCheckbox");

showPasswordCheckbox.addEventListener("change", function () {
    if (showPasswordCheckbox.checked) {
        passwordInput.type = "text"; // Mostrar a senha como texto
    } else {
        passwordInput.type = "password"; // Ocultar a senha
    }
});

