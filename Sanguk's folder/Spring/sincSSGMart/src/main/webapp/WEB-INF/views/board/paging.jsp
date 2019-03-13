<%@ page language="java" contentType="text/html; charSet=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="pagination">
	<li><a href="javascript:goPage(${param.firstPageNo})" >처음</a></li>
	<li><a href="javascript:goPage(${param.prevPageNo})" >이전</a></li>
	
		<c:forEach var="i" begin="${param.startPageNo}" end="${param.endPageNo}" step="1">
			<c:choose>
				<c:when test="${i eq param.pageNo}">
					<li><a href="javascript:goPage(${i})">${i}</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:goPage(${i})">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	
	<li><a href="javascript:goPage(${param.nextPageNo})">다음</a></li>
	<li><a href="javascript:goPage(${param.finalPageNo})">마지막</a></li>
</ul>