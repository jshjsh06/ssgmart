<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<form action="getProducts.do" method="POST">

 <!-- <form id="add-form" action="" method="post"> -->
{"id":"1", "name":"1"}

  <div class="form-group has-feedback">
    <input type="text" name="id" class="form-control" placeholder="ID"/>
    <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
  </div>
  <div class="form-group has-feedback">
    <input type="text" name="name" class="form-control" placeholder="Name"/>
    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
  </div>
  
  <div class="row">
    <div class="col-xs-8">    
      <div class="checkbox icheck">
        <label>
          <input type="checkbox" name="useCookie"> Remember Me
        </label>
      </div>                        
    </div><!-- /.col -->
    <div class="col-xs-4">
      <button type="submit" class="btn btn-primary btn-block btn-flat">SEND</button>
    </div><!-- /.col -->
  </div>
</form>


<p id="hi">hihihihihi</p>

<!-- 
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-Latest.js"></script>
<script type="text/javascript">	
	$(document).ready(function() {
		$("#add-form").submit( function(event){

			event.preventDefault();

			let queryString = $(this).serialize();

			$.ajax({
				url: "http://localhost:8088/product/getProducts.do",
				type: "POST",
				dataType: "json",
				data: {"id":"2", "name":"2"},
				success: function(result){
					alert("success2");
					console.log(result); 
					}
		         })	
			});
		});
</script> -->
