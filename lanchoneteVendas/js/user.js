const formulario = document.querySelector("form");
const Inome = document.querySelector("#nome");
const Iemail = document.querySelector("#email");
const Ilogin = document.querySelector("#login");
const Ipassword = document.querySelector("#password");
const Irole = document.querySelector("#role");
const listaContatos = document.querySelector(".lista-contatos");

// Verifica se todos os campos estão preenchidos
function validarCamposPreenchidos() {
    if (!Inome.value || !Iemail.value || !Ilogin.value || !Ipassword.value || !Irole.value) {
        alert("Por favor, preencha todos os campos antes de enviar o formulário.");
        return false;
    }
    return true;
}

const token = sessionStorage.getItem("token"); // Recupere o token JWT armazenado

if (token) {
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        if (validarCamposPreenchidos()) {
            cadastrar(token);
            alert("Cadastro realizado com sucesso!")
            location.reload();
        }
    });
} else {
    // Lida com a situação em que não há token JWT (o usuário não está autenticado)
    alert("Usuário não autenticado. Faça o login primeiro.");
    window.location.href = "index.html"; // Redirecione o usuário para a página de login
}

// Cadastra os dados
function cadastrar(token) {
    fetch("http://localhost:8080/api/v1/auth/register", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`, // Adicione o token JWT no cabeçalho
        },
        body: JSON.stringify({
            nome: Inome.value,
            email: Iemail.value,
            login: Ilogin.value,
            password: Ipassword.value,
            role: Irole.value,
        }),
    })
        .then(function (res) {
            if (!res.ok) {
                throw new Error('Erro na requisição. Status: ' + res.status + ' ' + res.statusText);
            }
            return res.json();
        })
        .then(function (data) {
            console.log(data);
            // Após cadastrar, limpe os campos
            Inome.value = "";
            Iemail.value = "";
            Ilogin.value = "";
            Ipassword.value = "";
            Irole.value = "";
            listarUser();
        })
        .catch(function (error) {
            console.error(error);
        });
}

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

