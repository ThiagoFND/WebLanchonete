const formulario = document.querySelector("form");
const Inome = document.querySelector("#nome");
const Iemail = document.querySelector("#email");
const Ilogin = document.querySelector("#login");
const Irole = document.querySelector("#role");
const listaUsers = document.querySelector(".lista-users");

// Função para listar usuários autenticada
async function listarUsuariosAutenticado(token) {
    const url = "http://localhost:8080/api/v1/admin/user/list";

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
        exibirUsuarios(data);
    } catch (error) {
        console.error(error);
    }
}

// Suponha que você já tenha obtido o token após o login

// Exemplo de uso:
const token = sessionStorage.getItem("token"); // Recupere o token JWT armazenado

if (token) {
    listarUsuariosAutenticado(token); // Chame a função de listagem com o token
} else {
    // Lida com a situação em que não há token JWT (o usuário não está autenticado)
    alert("Usuário não autenticado. Faça o login primeiro.");
    window.location.href = "index.html"; // Redirecione o usuário para a página de login
}



function exibirUsuarios(usuarios) {
    listaUsers.innerHTML = "";

    if (usuarios.length === 0) {
        // Não há usuários para exibir
    } else {
        const table = document.createElement("table");
        table.classList.add("contato-table");
        table.classList.add("move-right");

        const tableHeader = table.createTHead();
        const headerRow = tableHeader.insertRow();
        const headers = ["Selecionar", "Nome", "Email", "Login", "Password", "Função"];

        headers.forEach(function (headerText) {
            const th = document.createElement("th");
            th.textContent = headerText;
            headerRow.appendChild(th);
        });

        let corDeFundo = true;

        usuarios.forEach(function (usuario) {
            if (usuario.nome && usuario.email && usuario.login && usuario.role) {
                const row = table.insertRow();
        
                const removeButtonCell = row.insertCell();
                const removeButton = document.createElement("button");
                removeButton.textContent = "Remover";
                removeButton.style.backgroundColor = "red"; // Define a cor de fundo vermelha
                removeButton.style.color = "black"; // Define a cor do texto como branco para melhor legibilidade
                removeButton.addEventListener("click", function () {
                    const usuarioId = usuario.id; // Obtenha o ID do usuário
                    deletarUsuario(usuarioId, token); // Chame a função para deletar o usuário com o token e o ID
                });
                removeButtonCell.appendChild(removeButton);
        
                const usuarioData = [usuario.nome, usuario.email, usuario.login, usuario.password, usuario.role];
        
                usuarioData.forEach(function (value, index) {
                    const cell = row.insertCell();
                    cell.textContent = value;
                });
            }
        });
        

        listaUsers.appendChild(table);
    }
}



async function deletarUsuario(usuarioId, token) {
    const url = `http://localhost:8080/api/v1/admin/user/delete/${usuarioId}`;

    try {
        const response = await fetch(url, {
            method: "DELETE",
            headers: {
                "Authorization": `Bearer ${token}` // Adicione o token JWT no cabeçalho de autenticação
            }
        });

        if (!response.ok) {
            throw new Error('Erro na requisição. Status: ' + response.status + ' ' + response.statusText);
        }

        // Usuário excluído com sucesso, recarregue a página
        location.reload();

    } catch (error) {
        console.error(error);
    }
}
