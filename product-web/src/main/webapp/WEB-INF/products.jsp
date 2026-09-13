<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Insurance Products</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">

    <h1>Insurance Products</h1>
    <p class="subtitle">Loaded from the database via EJB</p>

    <form action="${pageContext.request.contextPath}/products" method="get">
        <button type="submit">Reload Products</button>
    </form>

    <table>
        <tr>
            <th>ID</th>
            <th>Product</th>
            <th>Description</th>
            <th>Premium</th>
        </tr>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.productId}</td>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td class="premium">
                    <fmt:formatNumber value="${product.premium}" minFractionDigits="2"/>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
