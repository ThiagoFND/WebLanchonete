const formulario = document.querySelector("form");
const Idinheiro = document.querySelector("#dinheiro");
const Ipix = document.querySelector("#pix");
const Icartao = document.querySelector("#cartao");
const Idata = document.querySelector("#data");
const listaEdital = document.querySelector(".lista-vendas");

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
    if (!Idinheiro.value || !Ipix.value || !Icartao.value || !Idata.value) {
        alert("Por favor, preencha todos os campos antes de enviar o formulário.");
        return false;
    }
    return true;
}

const token = sessionStorage.getItem("token");

if (token) {
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        if (validarCamposPreenchidos()) {
            cadastrar(token);
            alert("Cadastro realizado com sucesso!");
            location.reload();
        }
    });
} else {
    alert("Usuário não autenticado. Faça o login primeiro.");
    window.location.href = "index.html"; // Redirecione o usuário para a página de login
}

function cadastrar(token) {
    const dados = {
        dinheiro: Idinheiro.value,
        pix: Ipix.value,
        cartao: Icartao.value,
        data: Idata.value
    };

    fetch("http://localhost:8080/api/v1/dados/vendas/create", {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
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
        Idinheiro.value = "";
        Ipix.value = "";
        Icartao.value = "";
        preencherDataHoraAtual(); // Atualizar a data e hora para a atual após o cadastro
        listarEdital(token);
    })
    .catch(function (error) {
        console.error(error);
    });
}
