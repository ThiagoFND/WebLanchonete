const formulario = document.querySelector("form");
const Iproduto = document.querySelector("#produto");
const Ifalta = document.querySelector("#falta");
const Idata = document.querySelector("#data");
const listaProdutos = document.querySelector(".lista-produtos");

// Função para listar dados vendas autenticada
async function listarVendasAutenticado(token) {
    const url = "http://localhost:8080/api/v1/dados/produtos/list";

    try {
        const response = await fetch(url, {
            headers: {
                "Authorization": `Bearer ${token}` // Adicione o token JWT no cabeçalho de autenticação
            }
        });

        if (!response.ok) {
            throw new Error('Erro na requisição. Status: ' + response.status + ' ' + response.statusText);
        }

        const data = await response.json();
        exibirDados(data);
    } catch (error) {
        console.error(error);
    }
}

// Exemplo de uso:
const token = sessionStorage.getItem("token"); // Recupere o token JWT armazenado

if (token) {
    listarVendasAutenticado(token); // Chame a função de listagem com o token
} else {
    // Lida com a situação em que não há token JWT (o usuário não está autenticado)
    alert("Usuário não autenticado. Faça o login primeiro.");
    window.location.href = "index.html"; // Redirecione o usuário para a página de login
}

function exibirDados(dados) {
    listaProdutos.innerHTML = "";

    if (dados.length === 0) {
        // Não há dados para exibir, então limpe os totais
    } else {
        const table = document.createElement("table");
        table.classList.add("contato-table");
        table.classList.add("move-right"); // Adicione essa classe para mover a tabela para a direita


        const tableHeader = table.createTHead();
        const headerRow = tableHeader.insertRow();
        const headers = ["Produto", "Falta", "Data"];

        headers.forEach(function (headerText) {
            const th = document.createElement("th");
            th.textContent = headerText;
            headerRow.appendChild(th);
        });

        dados.forEach(function (contato) {
            if (contato.produto && contato.falta && contato.data) {
                const row = table.insertRow(); // Crie uma nova linha para cada conjunto de dados
        
                const contatoData = [contato.produto, contato.falta, contato.data];
        
                contatoData.forEach(function (value, index) {
                    const cell = row.insertCell();
                    cell.textContent = value;
                });
            }
        });
        

        listaProdutos.appendChild(table);
    }
}
