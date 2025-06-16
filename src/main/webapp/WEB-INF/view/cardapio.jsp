<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8" />
<title>Cardápio</title>
<style>
  body { font-family: Arial, sans-serif; background: #fff; padding: 20px; }
  h1 { text-align: center; }
  .prato { border: 1px solid #ddd; border-radius: 8px; padding: 15px; margin: 10px auto; max-width: 600px; }
  .nome { font-weight: bold; font-size: 18px; }
  .tipo { font-style: italic; color: #666; margin-bottom: 8px; }
  .porcoes button { margin-right: 8px; padding: 8px 12px; cursor: pointer; border: 1px solid #28a745; background: white; color: #28a745; border-radius: 5px; }
  .porcoes button:hover { background: #28a745; color: white; }
</style>
</head>
<body>
	
	
	<div style="display: flex; justify-content: center; gap: 10px; margin-bottom: 20px;">
	  <a href="cardapio" style="text-decoration: none;">
	    <button style="padding: 10px 20px; background-color: #28a745; color: white; border: none; border-radius: 4px; cursor: pointer;">
	      Cardápio
	    </button>
	  </a>
	  <a href="carrinho" style="text-decoration: none;">
	    <button style="padding: 10px 20px; background-color: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer;">
	      Carrinho
	    </button>
	  </a>
	  <a href="/" style="text-decoration: none;">
	    <button style="padding: 10px 20px; background-color: #dc3545; color: white; border: none; border-radius: 4px; cursor: pointer;">
	      Sair
	    </button>
	  </a>
	</div>


  <h1>Cardápio</h1>
  <div id="cardapio">
    <!-- Exemplo de prato -->
	<c:forEach var="item" items="${pratosComIngredientes}">
	  <div class="prato">
	    <div class="nome">${item.prato.nome}</div>
	    <div class="tipo">Tipo: ${item.prato.tipo.nome}</div>
	    <div class="ingredientes">
	      Ingredientes:
	      <ul>
	        <c:forEach var="ing" items="${item.ingredientes}">
	          <li>${ing.pratoIngredienteId.ingrediente.nome} - ${ing.qtd} ${ing.pratoIngredienteId.ingrediente.apresentacao}</li>
	        </c:forEach>
	      </ul>
	    </div>
	    <div class="porcoes">
	      <form action="add-to-cart" method="post">
	        <input type="hidden" name="pratoId" value="${item.prato.id}" />
	        <button type="submit">Adicionar - R$ ${item.prato.valor}</button>
	      </form>
	    </div>
	  </div>
	</c:forEach>


 </div>
</body>
</html>
