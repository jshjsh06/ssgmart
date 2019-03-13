<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form action="getProducts.do" method="POST">


<!-- <button id = "ssgBtn" onclick="location.href= './product/product.do'">올때SSG</button>
<button id = "cartBtn" onclick="location.href= '../cart/cart.do'">장바구니</button>
 -->

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


