<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<%@include file="../include/header.jsp"%>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class='box'>
				<div class="box-header with-border">
					<h3 class="box-title">Board List</h3>
				</div>
				<div class='box-body'>
					<!-- 추가  -->
					<select name="searchType" id="searchType">
						<option value="title">제목</option>
						<option value="writer">작성자</option>
					</select> 
					<input type="text" name='searchKeyword' id="searchKeyword">
					<button id='searchBtn'>Search</button>
					<!-- 기존 -->
					<button id='newBtn'>New Board</button>
				</div>
				
				
				
			</div>
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">LIST PAGING</h3>
				</div>
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th style="width: 10px">BNO</th>
							<th>TITLE</th>
							<th>WRITER</th>
							<th>REGDATE</th>
							<th style="width: 40px">VIEWCNT</th>
						</tr>
						
						<tbody id="tbody">
						<c:forEach items="${list}" var="boardVO">

							<tr>
								<td>${boardVO.seq}</td>
	<td><a  href='readPage.sinc?seq=${boardVO.seq}'>${boardVO.title}</a></td>
								<td>${boardVO.writer}</td>
								<td>${boardVO.regdate}</td>
								<td><span class="badge bg-red">${boardVO.viewcnt }</span></td>
							</tr>

						</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- /.box-body -->


				<div class="box-footer">

					<div class="text-center">
							<!--  -->

							<jsp:include page="./paging.jsp" flush="true">
								<jsp:param name="firstPageNo" value="${paging.firstPageNo}" />
								<jsp:param name="prevPageNo" value="${paging.prevPageNo}" />
								<jsp:param name="startPageNo" value="${paging.startPageNo}" />
								<jsp:param name="pageNo" value="${paging.pageNo}" />
								<jsp:param name="endPageNo" value="${paging.endPageNo}" />
								<jsp:param name="nextPageNo" value="${paging.nextPageNo}" />
								<jsp:param name="finalPageNo" value="${paging.finalPageNo}" />
							</jsp:include>

							<!--  -->
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
</div>
<!-- /.content -->

<form id="jobForm">
  <input type='hidden' name="page" value=${pageManager.currentPage}>
</form>




<%@include file="../include/footer.jsp"%>


<script>
	function goPage(pageNo){
		alert("pageNo : " + pageNo);
		location.href="listPage.sinc?pageNo="+pageNo ; 
	}
	////////////////////////////////////////////////////////
	var result = '${msg}';

	if (result == 'SUCCESS') {
		alert("처리가 완료되었습니다.");
	}
	
// 	$(".pagination li a").on("click", function(event){
		
// 		event.preventDefault(); 
		
// 		var targetPage = $(this).attr("href");
		
// 		var jobForm = $("#jobForm");
// 		jobForm.find("[name='page']").val(targetPage);
// 		jobForm.attr("action","/board/listPage.do").attr("method", "get");
// 		jobForm.submit();
// 	});
	
	$("#newBtn").on("click", function() {
		location.href="register.sinc";
	});
	
	$("#searchBtn").click(function() {
		$.ajax({
			url  : "search" , 
			type : "post" , 
			dataType : "json" , 
			data : { type : $("#searchType").val() , keyword : $("#searchKeyword").val() } ,
			success : function(data) {
				$("#tbody").empty();
				var txt = "";
				$.each(data , function(idx, obj) {
					txt +="<tr><td>"+obj.bno+"</td>" ; 
					txt +="<td><a href='readPage?bno="+obj.bno+"'>"+obj.title+"</a></td>";
					txt +="<td>"+obj.writer+"</td>";
					txt +="<td>"+obj.regdate+"</td>";
					txt +="<td><span class='badge bg-red'>"+obj.viewcnt+"</span></td></tr>" ; 
				});
				$("#tbody").append(txt); 
			}
			
		});
		
	});
</script>
















