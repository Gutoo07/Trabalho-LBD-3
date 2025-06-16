<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8" />
<title>Painel Administrativo</title>
<style>
  body { font-family: Arial, sans-serif; margin: 0; padding: 0; background: #f1f1f1; }
  header { background: #343a40; color: white; padding: 10px 20px; }
  h1 { margin: 0; }
  nav { background: #495057; padding: 10px 20px; display: flex; gap: 15px; }
  nav a { color: white; text-decoration: none; font-weight: bold; }
  nav a:hover { text-decoration: underline; }
  main { padding: 20px; }
  table { width: 100%; border-collapse: collapse; margin-bottom: 20px; background: white; }
  th, td { padding: 10px; border: 1px solid #ddd; text-align: left; }
  .btn { padding: 8px 12px; background: #007bff; color: white; border: none; cursor: pointer; border-radius: 4px; }
  .btn:hover { background: #0056b3; }
  .btn-danger { background: #dc3545; }
  .btn-danger:hover { background: #a71d2a; }
  form { margin-bottom: 20px; background: white; padding: 15px; border-radius: 5px; }
  input, select { padding: 8px; margin: 5px 10px 10px 0; width: 200px; }
</style>
</head>
<body>
	<header>
	  <h1>Painel Administrativo</h1>
	</header>
	<nav>
	  <a href="#pratos">Pratos</a>
	  <a href="#tipos">Tipos</a>
	  <a href="#ingredientes">Ingredientes</a>
	  <a href="#clientes">Clientes</a>
	  <a href="#pedidos">Pedidos</a>
	  <a href="#relatorios">Relatórios</a>
	</nav>
	<main>
		
		<section id="pratos">
			    <h2>Pratos</h2>

			    <form action="crudPrato" method="post">
			      <input type="hidden" name="prato_id" value="${prato != null ? prato.id : ''}" />

			      <label>Nome do Prato:</label>
			      <input type="text" name="nome" value="${prato != null ? prato.nome : ''}" required />

			      <label>Tamanho da Porção:</label>
			      <input type="number" name="tamanho_porcao" value="${prato != null ? prato.tamanho_porcao : ''}" required />

				  <label>Valor (R$):</label>
				  <input type="number" 
				         name="valor" 
				         value="${prato != null ? prato.valor : ''}" 
				         step="0.01" 
				         min="0.01" 
				         required 
				         placeholder="0.00" />
						 
			      <label>Tipo:</label>
			      <select name="tipo_id" required>
			        <option value="">Selecione o Tipo</option>
			        <c:forEach var="tipo" items="${tipos}">
			          <option value="${tipo.id}" 
			            <c:if test="${prato != null && prato.tipo != null && tipo.id == prato.tipo.id}">selected</c:if>>
			            ${tipo.nome}
			          </option>
			        </c:forEach>
			      </select>

				  <label>Ingredientes:</label>
				  <div style="max-height: 150px; overflow-y: auto; border: 1px solid #ccc; padding: 5px; background: white;">
				    <c:forEach var="ing" items="${ingredientes}">
				      <label style="display:block; margin-bottom:5px;">
				        <input type="checkbox" name="ingredientesIds" value="${ing.id}"
				          <c:if test="${prato != null && prato.ingredientes != null && prato.ingredientes.contains(ing)}">checked</c:if> />
				        ${ing.nome} (${ing.apresentacao})
				      </label>
				    </c:forEach>
				  </div>

			

			      <br><br>
			      <input type="submit" name="acao" value="Inserir" class="btn" />
			    </form>

			    <c:if test="${not empty erro}">
			      <p style="color: red;">${erro}</p>
			    </c:if>

			    <h3>Lista de Pratos</h3>
			    <table border="1">
			      <thead>
			        <tr><th>ID</th><th>Nome</th><th>Tipo</th><th>Ações</th></tr>
			      </thead>
			      <tbody>
			        <c:forEach var="p" items="${pratos}">
			          <tr>
			            <td>${p.id}</td>
			            <td>${p.nome}</td>
			            <td>${p.tipo.nome}</td>
			            <td>
							<a href="pratos?acao=excluir&prato_id=${p.id}" onclick="return confirm('Confirma exclusão?');">Excluir</a>
			            </td>
			          </tr>
			        </c:forEach>
			      </tbody>
			    </table>
			  </section>
	

			  <section id="tipos">
			    <h2>Tipos</h2>
			    <!-- Form para inserir / atualizar -->
			    <form id="formTipo" method="post" action="/crudTipo">
			      <input type="hidden" name="tipo_id" id="tipo_id" value="" />
			      <input type="text" name="nome" placeholder="Nome do Tipo" required />
			      <input type="hidden" name="acao" id="acao" value="Inserir" />
			      <button type="submit" class="btn">Salvar</button>
			    </form>

			    <!-- Tabela com tipos -->
			    <table>
			      <thead>
			        <tr>
			          <th>ID</th>
			          <th>Nome</th>
			          <th>Ações</th>
			        </tr>
			      </thead>
			      <tbody>
			        <c:forEach var="tipo" items="${tipos}">
			          <tr>
			            <td>${tipo.id}</td>
			            <td>${tipo.nome}</td>
			            <td>
		
	
			              <form method="post" action="/crudTipo" style="display:inline;">
			                <input type="hidden" name="tipo_id" value="${tipo.id}" />
			                <input type="hidden" name="acao" value="Excluir" />
			                <button type="submit" class="btn btn-danger">Excluir</button>
			              </form>
			            </td>
			          </tr>
			        </c:forEach>
			      </tbody>
			    </table>

			    <!-- Espaço para exibir erros, se houver -->
			    <c:if test="${not empty erro}">
			      <p style="color: red;">${erro}</p>
			    </c:if>

			  </section>

			  <section id="ingredientes">
			    <h2>Ingredientes</h2>
			    <form id="formIngrediente" method="post" action="/crudIngrediente">
			      <input type="hidden" name="id" id="id" value="" />
			      <input type="text" name="nome" placeholder="Nome do Ingrediente" required />
			      <input type="text" name="apresentacao" placeholder="Formato de Apresentação" required />
			      <input type="hidden" name="acao" id="acao" value="Inserir" />
			      <button type="submit" class="btn">Salvar</button>
			    </form>

			    <table>
			      <thead>
			        <tr>
			          <th>ID</th>
			          <th>Nome</th>
			          <th>Formato</th>
			          <th>Ações</th>
			        </tr>
			      </thead>
			      <tbody>
			        <c:forEach var="ing" items="${ingredientes}">
			          <tr>
			            <td>${ing.id}</td>
			            <td>${ing.nome}</td>
			            <td>${ing.apresentacao}</td>
			            <td>
			              <form method="post" action="/crudIngrediente" style="display:inline;">
			                <input type="hidden" name="id" value="${ing.id}" />
			                <input type="hidden" name="acao" value="Excluir" />
			                <button type="submit" class="btn btn-danger">Excluir</button>
			              </form>
			            </td>
			          </tr>
			        </c:forEach>
			      </tbody>
			    </table>

			    <c:if test="${not empty erro}">
			      <p style="color: red;">${erro}</p>
			    </c:if>
			  </section>

			



	 

	</main>
</body>
</html>
