/**
 * 
 */
$(document).ready(function () { 
	//-------------
    //- PIE CHART -
    //-------------
    // Get context with jQuery - using jQuery's .get() method.
    var pieChartCanvas = $("#pieChart").get(0).getContext("2d");
    var pieChart = new Chart(pieChartCanvas);
//    var PieData = [
//      {
//        value: 700,
//        color: "#f56954",
//        highlight: "#f56954",
//        label: "Chrome"
//      },
//      {
//        value: 500,
//        color: "#00a65a",
//        highlight: "#00a65a",
//        label: "IE"
//      },
//      {
//        value: 400,
//        color: "#f39c12",
//        highlight: "#f39c12",
//        label: "FireFox"
//      },
//      {
//        value: 600,
//        color: "#00c0ef",
//        highlight: "#00c0ef",
//        label: "Safari"
//      },
//      {
//        value: 300,
//        color: "#3c8dbc",
//        highlight: "#3c8dbc",
//        label: "Opera"
//      },
//      {
//        value: 100,
//        color: "#d2d6de",
//        highlight: "#d2d6de",
//        label: "Navigator"
//      }
//    ];
//    
//    console.log(PieData);
    
    var pieOptions = {
    	      //Boolean - Whether we should show a stroke on each segment
    	      segmentShowStroke: true,
    	      //String - The colour of each segment stroke
    	      segmentStrokeColor: "#fff",
    	      //Number - The width of each segment stroke
    	      segmentStrokeWidth: 2,
    	      //Number - The percentage of the chart that we cut out of the middle
    	      percentageInnerCutout: 50, // This is 0 for Pie charts
    	      //Number - Amount of animation steps
    	      animationSteps: 100,
    	      //String - Animation easing effect
    	      animationEasing: "easeOutBounce",
    	      //Boolean - Whether we animate the rotation of the Doughnut
    	      animateRotate: true,
    	      //Boolean - Whether we animate scaling the Doughnut from the centre
    	      animateScale: false,
    	      //Boolean - whether to make the chart responsive to window resizing
    	      responsive: true,
    	      // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
    	      maintainAspectRatio: true,
    	      //String - A legend template
    	      
    	    };
    
    var PieData=[];
    
    $.ajax({
		url:'chipFunctionPercent.action',
		data: [],
		type:'get',
		scriptCharset: 'utf-8',
		"Content-Type":"application/json;charset=utf-8",
		success: function(data){
			PieData=$.parseJSON(data);  
			$.each(PieData, function (index, data) {
				data["value"]=parseInt(data["value"]);
				data["color"]=randomHexColor();
				data["highlight"]=randomHexColor();
			});
			console.log(PieData);
			//Create pie or douhnut chart
		    // You can switch between pie and douhnut using the method below.
		    pieChart.Doughnut(PieData, pieOptions);
		}
    });      
});

function randomHexColor() { //随机生成十六进制颜色
	 return '#' + ('00000' + (Math.random() * 0x1000000 << 0).toString(16)).substr(-6);
}

    