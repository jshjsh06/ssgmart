<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> <!-- 이거 반드시 넣어줘야 c:forEach 사용 가능!!!!!!!!!!★★★ -->
<!doctype html>
<html class="no-js " lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<meta name="description" content="Responsive Bootstrap 4 and web Application ui kit.">

<title>:: Emart24 :: POS</title>
<!-- Favicon-->
<link rel="icon" href="favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="/resources/infinio_assets/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/infinio_assets/plugins/jvectormap/jquery-jvectormap-2.0.3.css"/>
<link rel="stylesheet" href="/resources/infinio_assets/plugins/morrisjs/morris.css" />

<!-- Custom Css -->
<link rel="stylesheet" href="/resources/infinio_assets/css/main.css">
<link rel="stylesheet" href="/resources/infinio_assets/css/ecommerce.css">
<link rel="stylesheet" href="/resources/infinio_assets/css/color_skins.css">
</head>
<body class="theme-purple">
<!-- Page Loader -->
<div class="page-loader-wrapper">
    <div class="loader">
        <div class="m-t-30"><img class="zmdi-hc-spin" src="/resources/infinio_assets/images/logo.svg" width="40" height="48" alt="InfiniO"></div>
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
                    <a class="navbar-brand" href="home.do"><img src="/resources/infinio_assets/images/SSGMartLogo.png" width="30" alt="InfiniO"><span class="m-l-10">SSGMart24</span></a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="search_bar hidden-xs">
                        <div class="input-group">                
                            <input type="text" class="form-control" placeholder="Find your stuff...">
                        </div>
                    </li>
                    <li class="dropdown notifications">
                        <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button"><i class="icon-bell"></i><span class="label-count">5</span></a>
                        <ul class="dropdown-menu">
                            <li class="header">New Message</li>
                            <li class="body">
                                <ul class="menu list-unstyled">
                                    <li>
                                        <a href="javascript:void(0);">
                                            <div class="media">
                                                <img class="media-object" src="/resources/infinio_assets/images/xs/avatar5.jpg" alt="">
                                                <div class="media-body">
                                                    <span class="name">Alexander <span class="time">13min ago</span></span>
                                                    <span class="message">Meeting with Shawn at Stark Tower by 8 o'clock.</span>                                        
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);">
                                            <div class="media">
                                                <img class="media-object" src="/resources/infinio_assets/images/xs/avatar6.jpg" alt="">
                                                <div class="media-body">
                                                    <span class="name">Grayson <span class="time">22min ago</span></span>
                                                    <span class="message">You have 5 unread emails in your inbox.</span>                                        
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);">
                                            <div class="media">
                                                <img class="media-object" src="/resources/infinio_assets/images/xs/avatar3.jpg" alt="">
                                                <div class="media-body">
                                                    <span class="name">Sophia <span class="time">31min ago</span></span>
                                                    <span class="message">OrderPlaced: You received a new oder from Tina.</span>                                        
                                                </div>
                                            </div>
                                        </a>
                                    </li>                
                                    <li>
                                        <a href="javascript:void(0);">
                                            <div class="media">
                                                <img class="media-object" src="/resources/infinio_assets/images/xs/avatar4.jpg" alt="">
                                                <div class="media-body">
                                                    <span class="name">Isabella <span class="time">35min ago</span></span>
                                                    <span class="message">Lara added a comment in Blazing Saddles.</span>                                        
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);">
                                            <div class="media">
                                                <img class="media-object" src="/resources/infinio_assets/images/xs/avatar8.jpg" alt="">
                                                <div class="media-body">
                                                    <span class="name">Sophia <span class="time">48min ago</span></span>
                                                    <span class="message">OrderPlaced: You received a new oder from Tina.</span>                                        
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li class="footer"> <a href="javascript:void(0);">View All</a> </li>
                        </ul>
                    </li>
                    <li class="dropdown task hidden-sm"><a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button"><i class="icon-flag"></i>
                        <span class="label-count">5</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="header">Project</li>
                            <li class="body">
                                <ul class="menu tasks list-unstyled">
                                    <li>
                                        <a href="javascript:void(0);">
                                            <span class="text-muted">Clockwork Orange <span class="float-right">29%</span></span>
                                            <div class="progress">
                                                <div class="progress-bar l-turquoise" role="progressbar" aria-valuenow="29" aria-valuemin="0" aria-valuemax="100" style="width: 29%;"></div>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);">
                                            <span class="text-muted">Blazing Saddles <span class="float-right">78%</span></span>
                                            <div class="progress">
                                                <div class="progress-bar l-slategray" role="progressbar" aria-valuenow="78" aria-valuemin="0" aria-valuemax="100" style="width: 78%;"></div>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);">
                                            <span class="text-muted">Project Archimedes <span class="float-right">45%</span></span>
                                            <div class="progress">
                                                <div class="progress-bar l-parpl" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 45%;"></div>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);">
                                            <span class="text-muted">Eisenhower X <span class="float-right">68%</span></span>
                                            <div class="progress">
                                                <div class="progress-bar l-coral" role="progressbar" aria-valuenow="68" aria-valuemin="0" aria-valuemax="100" style="width: 68%;"></div>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);">
                                            <span class="text-muted">Oreo Admin Templates <span class="float-right">21%</span></span>
                                            <div class="progress">
                                                <div class="progress-bar l-amber" role="progressbar" aria-valuenow="21" aria-valuemin="0" aria-valuemax="100" style="width: 21%;"></div>
                                            </div>
                                        </a>
                                    </li>                        
                                </ul>
                            </li>
                            <li class="footer"><a href="javascript:void(0);">View All</a></li>
                        </ul>
                    </li>
                    <li class="dropdown app_menu hidden-sm"><a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button"><i class="icon-grid"></i></a>
                        <ul class="dropdown-menu">
                            <li>
                                <ul>
                                    <li><a href="mail-inbox.html"><i class="icon-envelope"></i><span>Mail</span></a></li>
                                    <li><a href="contact.html"><i class="icon-list"></i><span>Contacts</span></a></li>
                                    <li><a href="chat.html"><i class="icon-bubble"></i><span>Chat</span></a></li>
                                    <li><a href="teams-board.html"><i class="icon-users"></i><span>Teams</span></a></li>
                                    <li><a href="projects.html"><i class="icon-notebook"></i><span>Projects</span></a></li>
                                    <li><a href="events.html"><i class="icon-calendar"></i><span>Calendar</span></a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <li><a href="chat.html" ><i class="icon-speech"></i></a></li>
                    
                    <li class="dropdown profile">
                        <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button">
                            <img class="rounded-circle" src="/resources/infinio_assets/images/profile_av.jpg" alt="User">
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <div class="user-info">
                                    <h6 class="user-name m-b-0">Alizee Thomas</h6>
                                    <p class="user-position">Available</p>
                                    <a title="facebook" href="javascript:void(0);"><i class="zmdi zmdi-facebook"></i></a>
                                    <a title="twitter" href="javascript:void(0);"><i class="zmdi zmdi-twitter"></i></a>
                                    <a title="instagram" href="javascript:void(0);"><i class="zmdi zmdi-instagram"></i></a>
                                    <a title="linkedin" href="javascript:void(0);"><i class="zmdi zmdi-linkedin-box"></i></a>
                                    <a title="dribbble" href="javascript:void(0);"><i class="zmdi zmdi-dribbble"></i></a>
                                    <a title="google plus" href="javascript:void(0);"><i class="zmdi zmdi-google-plus-box"></i></a>
                                    <hr>
                                </div>
                            </li>                            
                            <li><a href="profile.html"><i class="icon-user m-r-10"></i> <span>My Profile</span> <span class="badge badge-success float-right">80%</span></a></li>
                            <li><a href="javascript:void(0);"><i class="icon-notebook m-r-10"></i><span>Taskboard</span> <span class="badge badge-info float-right">New</span></a></li>                            
                            <li><a href="locked.html"><i class="icon-lock m-r-10"></i><span>Locked</span></a></li>
                            <li><a href="sign-in.html"><i class="icon-power m-r-10"></i><span>Sign Out</span></a></li>
                        </ul>
                    </li>
                    <li><a href="javascript:void(0);" class="js-right-sidebar"><i class="icon-equalizer"></i></a></li>
                </ul>
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
                        <li> <a href="home.do"><i class="icon-speedometer"></i><span>Dashboard</span></a></li>

                        <li  class="active open">
                            <a href="pos.do" class="menu-toggle"><i class="icon-basket-loaded"></i><span>Ecommerce</span></a>

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
<section class="content ecommerce-page">
    <div class="container">
    	<div class="row clearfix">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <div class="card">
                    <div class="header">
                        <h2><strong>Ecommerce POS SYSTEM</strong> <small>Input QR Code or Barcode</small> </h2>                        
                    </div>
                    <div class="body">                                                
                        <h2 class="card-inside-title">Barcode Reader</h2>
                        <div class="row clearfix">
                            <div class="col-sm-6">
                                <div class="form-group">                                   
                                    <input type="text" class="form-control"  placeholder="Barcode.." name="barcode" id="barcode" onkeypress="if( event.keyCode==13 ){goSearch();}"/>                              
                                </div>
                            </div>
                            <div class="col-sm-1">
                                <div class="form-group">                                   
                                	<button class="btn btn-primary btn-round btn-simple float-right hidden-xs m-l-10" id="searchBtn">Search</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
        <div class="row clearfix">
            <div class="col-lg-12">
                <div class="card product_item_list cart-page">
                    <div class="body">
                        <div class="table-responsive">
                            <table class="table table-hover m-b-0 cart-table">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>Image</th>
                                        <th>Product Name</th>
                                        <th>Quantity</th>
                                        <th data-breakpoints="xs">Amount</th>
                                    </tr>
                                </thead>
                                <tbody id="tbody">
                                    <tr>
                                        <td><img src="/resources/infinio_assets/images/ecommerce/2.png" width="40" alt="Product img"></td>
                                        <td><h5>Brone Lamp Glasses</h5></td>
                                        <td>
                                            <div class="quantity-grp">
                                                <div class="input-group spinner" data-trigger="spinner">                                    
                                                    <span class="input-group-addon">
                                                        <a href="javascript:void(0);" class="spin-up" data-spin="up"><i class="zmdi zmdi-plus"></i></a>
                                                    </span>
                                                    <input type="text" class="form-control text-center" value="1" data-rule="quantity">
                                                    <span class="input-group-addon">
                                                        <a href="javascript:void(0);" class="spin-down" data-spin="down"><i class="zmdi zmdi-minus"></i></a>
                                                    </span>
                                                </div>
                                            </div>
                                        </td>
                                        <td>$12.00</td>
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
                                        <th colspan="1" id="totalCount">Count : 0</th>
                                        <th colspan="2" id="totalPrice">Total : $0</th>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- <div class="card">
                    <div class="body">                            
                        <ul class="pagination pagination-primary m-b-0">
                            <li class="page-item"><a class="page-link" href="javascript:void(0);"><i class="zmdi zmdi-arrow-left"></i></a></li>
                            <li class="page-item active"><a class="page-link" href="javascript:void(0);">1</a></li>
                            <li class="page-item"><a class="page-link" href="javascript:void(0);">2</a></li>
                            <li class="page-item"><a class="page-link" href="javascript:void(0);">3</a></li>
                            <li class="page-item"><a class="page-link" href="javascript:void(0);">4</a></li>
                            <li class="page-item"><a class="page-link" href="javascript:void(0);"><i class="zmdi zmdi-arrow-right"></i></a></li>
                        </ul>
                    </div>
                </div> -->
            </div>
        </div>
    </div>
</section>
<!-- Jquery Core Js --> 
<script src="/resources/infinio_assets/bundles/libscripts.bundle.js"></script> <!-- Lib Scripts Plugin Js --> 
<script src="/resources/infinio_assets/bundles/vendorscripts.bundle.js"></script> <!-- Lib Scripts Plugin Js --> 

<script src="/resources/infinio_assets/plugins/jquery-spinner/js/jquery.spinner.js"></script> <!-- Jquery Spinner Plugin Js --> 

<script src="/resources/infinio_assets/bundles/mainscripts.bundle.js"></script>
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
 				url : "basket.do",
 				type : "post",
 				data : {barcode : $("#barcode").val()}, /* key-value 형식이다! */ 
 				dataType : "json",
 				success : function(ary) { /* success 일 때 호출할 함수 - 뭐로 받을지 모르니깐 ary로 우선 받는다.*/
 					$("#tbody").empty();
 					var txt = "";
 					var units = 0;
 					var price = 0;
 					$.each(ary, function(idx, obj) { // 받은 list타입을 ary로 받고, ary를 어떻게 풀어야하는지 볼 수 있다!!!!!!!
 						//alert(obj.address);
 						txt += "<tr>";
 	 					txt += "<td>"+obj.id+"</td>";
 	 					txt += "<td>"+obj.address+"</td>";
 	 					txt += "<td>"+obj.productName+"</td>";
 	 					txt += "<td>"+obj.units+"</td>";
 	 					txt += "</tr>";
 	 					units += Number(obj.id);
 	 					price += Number(obj.units);
 					});
 					$("#tbody").html(txt);
 					var S_price = "Total : " + String(price);
 					var S_units = "Count : " + String(units);
 					$("#totalPrice").html(S_price);
 					$("#totalCount").html(S_units);
 				}
 			})
 		});
 	});
</script>
