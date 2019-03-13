<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">READ BOARD</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
				
					<input type="hidden" id="seq" value="${boardVO.seq}">
				
					<div class="form-group">
						<label for="exampleInputEmail1">Title</label> <input type="text"
							name='title' class="form-control" value="${boardVO.title}"
							readonly="readonly">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Content</label>
						<textarea class="form-control" name="content" rows="3"
							readonly="readonly">${boardVO.content}</textarea>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">Writer</label> <input type="text"
							name="writer" class="form-control" value="${boardVO.writer}"
							readonly="readonly">
					</div>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<div>
						<hr>
					</div>
					<ul class="mailbox-attachments clearfix uploadedList">
					</ul>
					<c:if test="${loginUser.id == boardVO.writer }">	
						<button type="submit" class="btn btn-warning" id="modifyBtn">Modify</button>
						<button type="submit" class="btn btn-danger" id="removeBtn">REMOVE</button>
					</c:if>
					<button type="submit" class="btn btn-primary" id="goListBtn">GO	LIST</button>
				
				</div>

				<!-- 리플시작 row -->
				<div class="row">
					<div class="col-md-12">
						<div class="box box-success">
							<div class="box-header">
								<h3 class="box-title">ADD NEW REPLY</h3>
							</div>
							<c:if test="${not empty loginUser}">
								<div class="box-body">
									<label for="exampleInputEmail1">Writer</label> 
									<input
										class="form-control" type="text" placeholder="USER ID"
										id="newReplyWriter" value="${loginUser.id}" readonly="readonly"> 
									<label for="exampleInputEmail1">Reply Text</label> 
									<input
										class="form-control" type="text" placeholder="REPLY TEXT"
										id="newReplyText">
								</div>

								<div class="box-footer">
									<button type="button" class="btn btn-primary" id="replyAddBtn">ADD
										REPLY</button>
								</div>
							</c:if>

							<c:if test="${empty loginUser}">
								<div class="box-body">
									<div>
										<a href="">Login Please</a>
									</div>
								</div>
							</c:if>
						</div>



						<!-- The time line -->
						<ul class="timeline">
							<!-- timeline time label -->
							<li class="time-label" id="repliesDiv"><span
								class="bg-green"> Replies List <small id='replycntSmall'>
										[ 000 ] </small>
							</span></li>
						</ul>
						<ul id="rlist">
							<c:forEach items="${boardVO.list}" var="reply">
							<li class="time-label">${reply.msg }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<c:if test="${loginUser.id == reply.author}">
								<a 	class="btn btn-primary btn-xs" 
		    						data-toggle="modal" data-target="#modifyModal">Modify</a>
							</c:if>
							</li>
							</c:forEach>
						</ul>

					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->

	<script>
		$(document).ready(function() {
			$("#goListBtn").click(function() {
				location.href="/board/listPage.sinc";
			});
			$("#replyAddBtn").click(function() {
				$.ajax({
					url  : "/board/reply.sinc" , 
					type : "post",
					data : { author : $("#newReplyWriter").val(), msg : $("#newReplyText").val() , seq : $("#seq").val()  },
					dataType : "json" ,
					success  : function(ary){
						$("#rlist").empty();
						var txt = "";
						$.each(ary , function(idx, data) {
							txt +="<li class='time-label'>"+data.msg+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp";
							if(data.author == '${loginUser.id}') {							
								txt +="<a class='btn btn-primary btn-xs' data-toggle='modal' data-target='#modifyModal'>Modify</a>";
							}
							txt +="</li>" ;	
						});
						$("#rlist").append(txt);
					}
				});
			});
		});
	</script>




			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@include file="../include/footer.jsp"%>
