<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>
	
	<link href="/resources/user_css/bootstrap.css" rel="stylesheet">
    <link href="/resources/user_css/font-awesome.css" rel="stylesheet">
    <link href="/resources/user_css/animate.css" rel="stylesheet">
    <link href="/resources/user_css/style.css" rel="stylesheet">
    
    
    
    
<!-- Main content -->
<section class="content">
	
	<input type="hidden" id="data01" value="${data01}" />
	<input type="hidden" id="data02" value="${data02}" />
	
	<div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
                <div class="col-lg-6">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Line Chart Example
                                <small>With custom colors.</small>
                            </h5>
                            <div ibox-tools></div>
                        </div>
                        <div class="ibox-content">
                            <div>
                                <canvas id="lineChart" height="140"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Bar Chart Example</h5>
                            <div ibox-tools></div>
                        </div>
                        <div class="ibox-content">
                            <div>
                                <canvas id="barChart" height="140"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Polar Area</h5>

                            <div ibox-tools></div>
                        </div>
                        <div class="ibox-content">
                            <div class="text-center">
                                <canvas id="polarChart" height="140"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Pie </h5>

                            <div ibox-tools></div>
                        </div>
                        <div class="ibox-content">
                            <div>
                                <canvas id="doughnutChart" height="140"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Radar Chart Example</h5>
                            <div ibox-tools></div>
                        </div>
                        <div class="ibox-content">
                            <div>
                                <canvas id="radarChart"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%-- 
	<script src="/resources/user_js/jquery.metisMenu.js"></script>
    <script src="/resources/user_js/jquery.slimscroll.min.js"></script>

    <!-- Flot -->
    <script src="/resources/user_js/jquery.flot.js"></script>
    <script src="/resources/user_js/jquery.flot.tooltip.min.js"></script>
    <script src="/resources/user_js/jquery.flot.resize.js"></script>
    <script src="/resources/user_js/jquery.flot.pie.js"></script>

    <script src="/resources/user_js/inspinia.js"></script>
    <script src="/resources/user_js/pace.min.js"></script>
--%>
	<script src="/resources/user_js/Chart.min.js"></script>
	<script src="/resources/user_js/chartjs-demo.js"></script>

<%@include file="../include/footer.jsp"%>

<script type="text/javascript">
$(document).ready(function() {
	
	var data01 = ${data01};
	alert(data01.length);
	var arr01 = new Array();
	for(var i=0 ; i < data01.length ; i++) {
		arr01.push(data01[i]);
	}
	var data02 = ${data02} ; 
	var arr02 = new Array();
	for(var i=0 ; i < data02.length ; i++) {
		arr02.push(data02[i]);
	}
	////////////
	var lineData = {
	        labels: ["January", "February", "March", "April", "May", "June", "July"],
	        datasets: [
	            {
	                label: "Example dataset",
	                fillColor: "rgba(220,220,220,0.5)",
	                strokeColor: "rgba(220,220,220,1)",
	                pointColor: "rgba(220,220,220,1)",
	                pointStrokeColor: "#fff",
	                pointHighlightFill: "#fff",
	                pointHighlightStroke: "rgba(220,220,220,1)",
	                data: arr01
	            },
	            {
	                label: "Example dataset",
	                fillColor: "rgba(26,179,148,0.5)",
	                strokeColor: "rgba(26,179,148,0.7)",
	                pointColor: "rgba(26,179,148,1)",
	                pointStrokeColor: "#fff",
	                pointHighlightFill: "#fff",
	                pointHighlightStroke: "rgba(26,179,148,1)",
	                data: arr02
	            }
	        ]
	    };

	    var lineOptions = {
	        scaleShowGridLines: true,
	        scaleGridLineColor: "rgba(0,0,0,.05)",
	        scaleGridLineWidth: 1,
	        bezierCurve: true,
	        bezierCurveTension: 0.4,
	        pointDot: true,
	        pointDotRadius: 4,
	        pointDotStrokeWidth: 1,
	        pointHitDetectionRadius: 20,
	        datasetStroke: true,
	        datasetStrokeWidth: 2,
	        datasetFill: true,
	        responsive: true,
	    };


	    var ctx = document.getElementById("lineChart").getContext("2d");
	    var myNewChart = new Chart(ctx).Line(lineData, lineOptions);

})
</script>

