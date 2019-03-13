<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<button id = "ssgBtn" onclick="location.href= '../product/product.do'">올때SSG</button>
<button id = "cartBtn" onclick="location.href= '../cart/cart.do'">장바구니</button>


<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">상품목록</h3>
				</div>
				
				<div class="box-body">
				<table class="table table-bordered">
					<tr>
						<th>매장명</th>
						<th>제품명</th>
						<th>가격</th>
						<th>유통기한</th>
						<th>카테고리</th>
						<th>재고수</th>
					</tr>
				
					<tbody id = "tbody">
						<c:forEach items="${productlists}" var="productListVO">
							<tr>
								<td>${productListVO.storeName}</td>
								<td>${productListVO.productName}</td>
								<td>${productListVO.price}</td>
								<td>${productListVO.valid}</td>
								<td>${productListVO.category}</td>
								<td>${productListVO.stock}</td>
							</tr>
						</c:forEach>
					</tbody> 
				</table>
				</div>
				<!-- /.box-body -->
				
				<div class="box-footer">Footer</div>
				<!-- /.box-footer-->
			</div>
			
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<!-- 밑에 코드는? json화해서 객체 정보를 심고, EL로 표현 // 문자열이 필요할 때, 이렇게 심는다.싱글쿼테이션 '%{}' -->
