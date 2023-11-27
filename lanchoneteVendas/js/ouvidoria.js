const formulario = document.querySelector("form");
const Inome = document.querySelector("#nome");
const Itipo = document.querySelector("#tipo");
const Imensagem = document.querySelector("#mensagem");
const Itelefone = document.querySelector("#telefone");
const Iemail = document.querySelector("#email");
const Iatendida = document.querySelector("#atendida");
const Idata = document.querySelector("#data");
const listaEdital = document.querySelector(".lista-ouvidoria");

// Função para preencher a data e hora atuais
function preencherDataHoraAtual() {
    const dataAtual = new Date();
    const dia = String(dataAtual.getDate()).padStart(2, "0");
    const mes = String(dataAtual.getMonth() + 1).padStart(2, "0");
    const ano = dataAtual.getFullYear();
    const hora = String(dataAtual.getHours()).padStart(2, "0");
    const minuto = String(dataAtual.getMinutes()).padStart(2, "0");

    const dataHoraAtual = `${dia}/${mes}/${ano} - ${hora}:${minuto} horas`;
    Idata.value = dataHoraAtual;
}

// Preencher a data e hora atuais quando a página carregar
preencherDataHoraAtual();

function validarCamposPreenchidos() {
    if (!Inome.value || !Itipo.value || !Imensagem.value || !Itelefone.value || !Iemail.value || !Iatendida.value || !Idata.value) {
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

// Cadastra os gastos
function cadastrar(token) {
    const dados = {
        nome: Inome.value,
        tipo: Itipo.value,
        mensagem : Imensagem.value,
        telefone : Itelefone.value,
        email : Iemail.value,
        atendida : Iatendida.value,
        data: Idata.value
    };

    fetch("http://localhost:8080/api/v1/dados/ouvidoria/create", {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`, // Adicione o token JWT no cabeçalho
        },
        method: "POST",
        body: JSON.stringify(dados)
    })
    .then(function (res) {
        if (!res.ok) {
            throw new Error('Erro na requisição. Status: ' + res.status + ' ' + res.statusText);
        }
        return res.json();
    })
    .then(function (data) {
        console.log(data);
// Após cadastrar, limpe os campos e preencha a data e hora atuais
Inome.value = "";
Itipo.value = "";
Imensagem.value = "";
Itelefone.value = "";
Iemail.value = "";
Iatendida.value = "EM ESPERA";
Idata.value = "";
preencherDataHoraAtual(); // Chame a função para preencher a data e hora atuais
listarEdital(); // Atualize a lista de edital após o cadastro
    })
    .catch(function (error) {
        console.error(error);
    });
}