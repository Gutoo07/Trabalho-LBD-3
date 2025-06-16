<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8" />
<title>Carrinho</title>
<style>
  body { font-family: Arial, sans-serif; padding: 20px; background:#fafafa; }
  h1 { text-align: center; }
  table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }
  th, td { padding: 12px; border-bottom: 1px solid #ddd; text-align: left; }
  .remover-btn { background-color: #dc3545; color: white; border: none; padding: 6px 12px; cursor: pointer; border-radius: 4px; }
  .remover-btn:hover { background-color: #c82333; }
  .total { text-align: right; font-weight: bold; font-size: 18px; }
  .finalizar { background-color: #28a745; color: white; padding: 10px 20px; border: none; cursor: pointer; border-radius: 5px; float: right; }
  .finalizar:hover { background-color: #218838; }
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

  <h1>Carrinho</h1>

  <table>
    <thead>
      <tr>
        <th>Prato</th>
        <th>Porção</th>
        <th>Valor Unitário</th>
        <th>Ação</th>
      </tr>
    </thead>
    <tbody id="itensCarrinho">
      <c:forEach var="item" items="${itens}">
        <tr>
          <td>${item.nome}</td>
          <td>${item.tamanho_porcao}</td>
          <td>R$ <fmt:formatNumber value="${item.valor}" type="currency" currencySymbol="" minFractionDigits="2" /></td>
          <td>
            <form method="post" action="remover-item">
              <input type="hidden" name="pratoId" value="${item.id}" />
              <button class="remover-btn" type="submit">Remover</button>
            </form>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

  <div class="total">
    Total: R$
    <fmt:formatNumber value="${total}" type="currency" currencySymbol="" minFractionDigits="2" />
  </div>

  <form method="post" action="finalizar-compra">
    <button class="finalizar" type="submit">Finalizar Compra</button>
  </form>

</body>
</html>
