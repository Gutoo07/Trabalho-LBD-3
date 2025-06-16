<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Login / Cadastro - Carrossel</title>
<style>
  body {
    font-family: Arial, sans-serif;
    background:#f9f9f9;
    padding: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
  }

  .container {
    width: 400px;
    background: white;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0,0,0,0.1);
    position: relative;
    overflow: hidden;
  }

  h2 {
    text-align: center;
    margin-bottom: 20px;
  }

  form {
    display: none;
    flex-direction: column;
    animation: fadeIn 0.5s ease forwards;
  }

  form.active {
    display: flex;
  }

  input, button {
    width: 100%;
    padding: 10px;
    margin: 8px 0;
    box-sizing: border-box;
    font-size: 14px;
  }

  button {
    background-color: #28a745;
    border: none;
    color: white;
    font-weight: bold;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }

  button:hover {
    background-color: #218838;
  }

  /* Botões para alternar */
  .toggle-buttons {
    display: flex;
    justify-content: center;
    margin-bottom: 15px;
    gap: 10px;
  }

  .toggle-buttons button {
    background-color: #218838;
    border: none;
    color: white;
    width: 120px;
    padding: 8px 0;
    font-weight: bold;
    cursor: pointer;
    border-radius: 3px;
    transition: background-color 0.3s ease;
  }

  .toggle-buttons button.active {
    background-color: #0e520e;
  }



  @keyframes fadeIn {
    from {opacity: 0;}
    to {opacity: 1;}
  }

</style>
</head>
<body>

  <div class="container">
    <div class="toggle-buttons">
      <button id="btnLogin" class="active" type="button">Login</button>
      <button id="btnRegister" type="button">Cadastro</button>
    </div>

	<form id="loginForm" class="active" method="post" action="sendLogin">
	  <h2>Login</h2>
	  <input type="text" name="cpf" placeholder="CPF" required />
	  <input type="text" name="senha" placeholder="Senha" required />
	  <input type="hidden" name="acao" value="Buscar" />
	  <button type="submit">Entrar</button>
	</form>

	<form id="registerForm" method="post" action="crudCliente">
	  <h2>Cadastro</h2>
	  <input type="text" name="cpf" placeholder="CPF" required />
	  <input type="text" name="nome" placeholder="Nome completo" required />
	  <input type="tel" name="telefone" placeholder="Telefone" required />
	  <input type="text" name="end_log" placeholder="Logradouro" required />
	  <input type="text" name="end_num" placeholder="Número" required />
	  <input type="text" name="end_cep" placeholder="CEP" required />
	  <input type="text" name="end_ponto_ref" placeholder="Ponto de Referência" />
	  <input type="password" name="senha" placeholder="Senha" required />
	  <input type="hidden" name="acao" value="Inserir" />
	  <button type="submit">Cadastrar</button>
	</form>

  </div>

<script>
  const btnLogin = document.getElementById('btnLogin');
  const btnRegister = document.getElementById('btnRegister');
  const loginForm = document.getElementById('loginForm');
  const registerForm = document.getElementById('registerForm');

  btnLogin.addEventListener('click', () => {
    btnLogin.classList.add('active');
    btnRegister.classList.remove('active');
    loginForm.classList.add('active');
    registerForm.classList.remove('active');
  });

  btnRegister.addEventListener('click', () => {
    btnRegister.classList.add('active');
    btnLogin.classList.remove('active');
    registerForm.classList.add('active');
    loginForm.classList.remove('active');
  });


</script>

</body>
</html>
