<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> <!-- 이거 반드시 넣어줘야 c:forEach 사용 가능!!!!!!!!!!★★★ -->
<html lang="en">

<%@include file="../home.jsp"%>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>


<div class="container">
  <h2>Product Table</h2>
  <p>Combine .table-dark and .table-striped to create a dark, striped table:</p>            
  <table class="table table-dark table-striped">
    <thead>
      <tr>
        <th>Storeid</th>
        <th>StoreName</th>
        <th>Prouct</th>
        <th>Units</th>
      </tr>
    </thead>
    <tbody id="tbody">
    	<c:forEach items="${lists}" var="StockStoreVO">
	      <tr>
	        <td>${StockStoreVO.id}</td>
	        <td>${StockStoreVO.address}</td>
	        <td>${StockStoreVO.productName}</td>
	        <td>${StockStoreVO.units}</td>
	      </tr>
      	</c:forEach>
    </tbody>
  </table>
</div>

</body>
</html>