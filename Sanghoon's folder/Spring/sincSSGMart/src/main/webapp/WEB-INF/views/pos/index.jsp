<!doctype html>
<html class="no-js " lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">
<title>:: SSGMart :: Home</title>
<link rel="icon" href="favicon.ico" type="image/x-icon"> <!-- Favicon-->
<link rel="stylesheet" href="/resources/infinio_assets/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/infinio_assets/plugins/jvectormap/jquery-jvectormap-2.0.3.min.css"/>
<link rel="stylesheet" href="/resources/infinio_assets/plugins/morrisjs/morris.min.css" />
<!-- Custom Css -->
<link rel="stylesheet" href="/resources/infinio_assets/css/main.css">
<link rel="stylesheet" href="/resources/infinio_assets/css/color_skins.css">
</head>
<body class="theme-purple">
<!-- Page Loader -->
<div class="page-loader-wrapper">
    <div class="loader">
        <div class="m-t-30"><img class="zmdi-hc-spin" src="/resources/infinio_assets/images/logo.svg" width="48" height="48" alt="InfiniO"></div>
        <p>Please wait...</p>        
    </div>
</div>
<!-- Overlay For Sidebars -->
<div class="overlay"></div>

<!-- Top Bar -->
<nav class="top_navbar">
    <div class="container">
        <div class="row clearfix">
            <div class="col-12">
                <div class="navbar-logo">
                    <a href="javascript:void(0);" class="bars"></a>
                    <a class="navbar-brand" href="index.html"><img src="/resources/infinio_assets/images/SSGMartLogo.png" width="30" alt="InfiniO"><span class="m-l-10">SSGMart</span></a>
                </div>                
            </div>
        </div>        
    </div>
</nav>

<aside id="leftsidebar" class="sidebar h_menu">
    <div class="container">
        <div class="row clearfix">
            <div class="col-12">
                <div class="menu">
                    <ul class="list">
                        <li class="header">MAIN</li>
                        <li class="active open"> <a href="index.html"><i class="icon-speedometer"></i><span>Dashboard</span></a></li>
                        <li>
                            <a href="javascript:void(0);" class="menu-toggle"><i class="icon-basket-loaded"></i><span>Ecommerce</span></a>
                        </li>              
                    </ul>
                </div>
            </div>
        </div>
    </div>
</aside>

<!-- Right Sidebar -->
<aside id="rightsidebar" class="right-sidebar">
    <div class="slim_scroll">
        <div class="card">
            <h6>Skins</h6>
            <ul class="choose-skin list-unstyled">
                <li data-theme="purple" class="active">
                    <div class="purple"></div>
                </li>                   
                <li data-theme="blue">
                    <div class="blue"></div>
                </li>
                <li data-theme="cyan">
                    <div class="cyan"></div>
                </li>
                <li data-theme="green">
                    <div class="green"></div>
                </li>
                <li data-theme="orange">
                    <div class="orange"></div>
                </li>
                <li data-theme="blush">
                    <div class="blush"></div>
                </li>
            </ul>
        </div>
        <div class="card theme-light-dark">
            <h6>Theme Option</h6>
            <button class="btn btn-default btn-block btn-round btn-simple t-light">Light</button>
            <button class="btn btn-default btn-block btn-round t-dark">Dark</button>
        </div> 
        <div class="card">
            <h6>General Settings</h6>
            <ul class="setting-list list-unstyled">
                <li>
                    <div class="checkbox">
                        <input id="checkbox1" type="checkbox">
                        <label for="checkbox1">Report Panel Usage</label>
                    </div>
                </li>
                <li>
                    <div class="checkbox">
                        <input id="checkbox2" type="checkbox" checked="">
                        <label for="checkbox2">Email Redirect</label>
                    </div>
                </li>
                <li>
                    <div class="checkbox">
                        <input id="checkbox3" type="checkbox" checked="">
                        <label for="checkbox3">Notifications</label>
                    </div>                        
                </li>
                <li>
                    <div class="checkbox">
                        <input id="checkbox4" type="checkbox" checked="">
                        <label for="checkbox4">Auto Updates</label>
                    </div>
                </li>
                <li>
                    <div class="checkbox">
                        <input id="checkbox5" type="checkbox" checked="">
                        <label for="checkbox5">Offline</label>
                    </div>
                </li>
                <li>
                    <div class="checkbox">
                        <input id="checkbox6" type="checkbox" checked="">
                        <label for="checkbox6">Location Permission</label>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</aside>

<!-- Main Content -->
<section class="content">
    <div class="container">
        <div class="row clearfix">
            <div class="col-lg-12">
                <div class="card">
                    <div class="body block-header">
                        <div class="row">
                            <div class="col-lg-6 col-md-8 col-sm-12">
                                <h2>Welcome to SSGMart POS SYSTEM</h2>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
             
        <div class="row clearfix">
            <div class="col-xl-8 col-lg-12 col-md-12">
                <div class="card">
                    <div class="header">
                        <h2><strong>POS</strong> Items</h2>
                        <h1> </h1>
                        <div class="row">
	                        <div class="col-lg-9">
	                        	<input style="border-color:#18ce0f" type="text" class="form-control"  placeholder="Barcode Scanner" name="barcode" id="barcode" onkeypress="if( event.keyCode==13 ){goSearch();}"/>                              
	                        </div>
	                        <div class="col-lg-3">	                        
		                       	<button class="btn btn-primary btn-round btn-simple float-right hidden-xs m-l-10" style="padding: 6px 14px;" id="searchBtn">Search</button>	                        
	                        </div>
                        </div>
                    </div>
                    <div class="body">
                        <div class="table-responsive">
                            <table class="table table-hover m-b-0 cart-table">
                                <thead class="thead-dark">
                                    <tr>
                                        <th><h5>Image</h5></th>
                                        <th><h5>Product Name</h5></th>
                                        <th><h5>Quantity</h5></th>
                                        <th data-breakpoints="xs"><h5>Amount</h5></th>
                                    </tr>
                                </thead>
                                <tbody id="tbody">
                                    <tr>
                                        <td><img src="/resources/infinio_assets/images/ecommerce/2.png" width="40" alt="Product img"></td>
                                        <td><h5>Brone Lamp Glasses</h5></td>
                                        <td><h5>1</h5></td>
                                        <td><h5>$12.00</h5></td>
                                    </tr>
                                    <tr>
                                        <td><img src="/resources/infinio_assets/images/ecommerce/2.png" width="40" alt="Product img"></td>
                                        <td><h5>Brone Lamp Glasses</h5></td>
                                        <td><h5>1</h5></td>
                                        <td><h5>$12.00</h5></td>
                                    </tr>
				              	    <c:forEach items="${lists}" var="StockStoreVO">	
				 	                    <tr>
									      <td>${StockStoreVO.id}</td>
									      <td>${StockStoreVO.address}</td>
									      <td>${StockStoreVO.productName}</td>
									      <td>${StockStoreVO.units}</td>
					                    </tr>
				                    </c:forEach>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th colspan="1"></th>
                                        <th colspan="1"></th>                                    
                                        <th colspan="1" id="totalCount"><h5>Count : 0</h5></th>
                                        <th colspan="2" id="totalPrice"><h5>Total : $0</h5></th>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6 col-xl-4">
                <div class="card">
                    <div class="header">
                        <h2><strong>Sale</strong> Progress</h2>                       
                    </div>
                    <div class="body">
                        <span>Total Amount</span>
                        <h3 id="saleAmount">$22,652<sup>.35</sup></h3>                        
                        <div class="m-b-20">
                            <h6>117</h6>                            
                            <div class="progress">
                                <div class="progress-bar progress-bar-success" role="progressbar" style="width:75%; " aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <div><small>Example data</small><span class="float-right"><i class="zmdi zmdi-caret-up"></i> +24%</span></div>
                        </div>
                        <div class="m-b-20">
                            <h6>78</h6>                            
                            <div class="progress">
                                <div class="progress-bar progress-bar-warning" role="progressbar" style="width:55%; " aria-valuenow="55" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <div><small>Example data</small><span class="float-right"><i class="zmdi zmdi-caret-down"></i> -12%</span></div>
                        </div>
                        <div class="m-b-20">
                            <h6>56</h6>
                            <div class="progress">
                                <div class="progress-bar progress-bar-danger" role="progressbar" style="width:30%; " aria-valuenow="30" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <div><small>Example data</small><span class="float-right"><i class="zmdi zmdi-caret-up"></i> +24%</span></div>
                        </div>
                        <div class="m-b-20">
                            <h6>82</h6>
                            <div class="progress">
                                <div class="progress-bar progress-bar-primary" role="progressbar" style="width:62%; " aria-valuenow="62" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                            <div><small>Example data</small><span class="float-right"><i class="zmdi zmdi-caret-down"></i> -12%</span></div>
                        </div>
                    </div>
                </div>
            </div>
    </div>
</section>
<!-- Jquery Core Js --> 
<script src="/resources/infinio_assets/bundles/libscripts.bundle.js"></script> <!-- Lib Scripts Plugin Js ( jquery.v3.2.1, Bootstrap4 js) --> 
<script src="/resources/infinio_assets/bundles/vendorscripts.bundle.js"></script> <!-- slimscroll, waves Scripts Plugin Js -->

<script src="/resources/infinio_assets/bundles/morrisscripts.bundle.js"></script><!-- Morris Plugin Js -->
<script src="/resources/infinio_assets/bundles/jvectormap.bundle.js"></script> <!-- JVectorMap Plugin Js -->
<script src="/resources/infinio_assets/bundles/knob.bundle.js"></script> <!-- Jquery Knob-->

<script src="/resources/infinio_assets/bundles/mainscripts.bundle.js"></script>
<script src="/resources/infinio_assets/js/pages/widgets/infobox/infobox-1.js"></script>
<script src="/resources/infinio_assets/js/pages/index.js"></script>
</body>
</html>

<script>
	function goSearch(){
	 		$("#searchBtn").bind("click", function() {
			});
	 		$("#searchBtn").trigger("click");
		}
 	$(document).ready(function() {

 		$("#searchBtn").click(function() {
 			$.ajax({
 				url : "pos.do",
 				type : "post",
 				data : {user_Id : $("#barcode").val()}, /* key-value 형식이다! */ 
 				dataType : "json",
 				success : function(ary) { /* success 일 때 호출할 함수 - 뭐로 받을지 모르니깐 ary로 우선 받는다.*/
 					$("#tbody").empty();
 					var txt = "";
 					var units = 0;
 					var price = 0;
 					$.each(ary, function(idx, obj) { // 받은 list타입을 ary로 받고, ary를 어떻게 풀어야하는지 볼 수 있다!!!!!!!
 						//alert(obj.address);
 						txt += "<tr>";
 	 					//txt += "<td><h5>"+obj.id+"</h5></td>";
 	 					txt += "<td><img src='/resources/infinio_assets/images/ecommerce/2.png' width='40' alt='Product img'></td>";
 	 					txt += "<td><h5>"+obj.name+"("+obj.priceSell+")"+"</h5></td>";
 	 					txt += "<td><h5>"+obj.cnt+"</h5></td>";
 	 					txt += "<td><h5>"+Number(obj.cnt) * Number(obj.priceSell)+"</h5></td>";
 	 					txt += "</tr>";
 	 					units += Number(obj.cnt);
 	 					price += Number(obj.cnt) * Number(obj.priceSell);
 					});
 					$("#tbody").html(txt);
 					var S_price = "<h5>Total : " + String(price) + "</h5>";
 					var S_units = "<h5>Count : " + String(units) + "</h5>";
 					var saleAmount = "<h6>$" + String(price) + "</h6>"
 					$("#totalPrice").html(S_price);
 					$("#totalCount").html(S_units);
 					$("#saleAmount").html(saleAmount);
 				}
 			})
 		});
 	});
</script>