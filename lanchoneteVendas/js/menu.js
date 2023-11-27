document.getElementById("menuOpcoes").addEventListener("change", function () {
    var selectedOption = this.value;
    window.location.href = selectedOption; // Redireciona para a página selecionada
});
document.getElementById("menuOpcoesLogin").addEventListener("change", function () {
    var selectedOption = this.value;
    window.location.href = selectedOption; // Redireciona para a página selecionada
});
document.getElementById("menuOpcoesGastos").addEventListener("change", function () {
    var selectedOption = this.value;
    window.location.href = selectedOption; // Redireciona para a página selecionada
});
document.getElementById("menuOpcoesProdutos").addEventListener("change", function () {
    var selectedOption = this.value;
    window.location.href = selectedOption; // Redireciona para a página selecionada
});
document.getElementById("menuOpcoesUsuarios").addEventListener("change", function () {
    var selectedOption = this.value;
    window.location.href = selectedOption; // Redireciona para a página selecionada
});

function toggleMenu() {
    var menuOptions = document.querySelector('.menu-options');
    menuOptions.classList.toggle('active');
}