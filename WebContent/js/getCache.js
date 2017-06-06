/**
 function refreshTable()  
 * 
 */
function ajaxData(){
	$(document).ready(function () { 
	    $.ajax({
			url:'getFreqUsedChips.action',
			data: [],
			type:'get',
			scriptCharset: 'utf-8',
			"Content-Type":"application/json;charset=utf-8",
			success: function(data){
				var jsonarray=$.parseJSON(data);
				jsonarray=sortByKey(jsonarray,"freq");
				$("tr:has(td)").remove();  
			    $.each(jsonarray, function (index, data) {  
			        $("#table").append($('<tr data-toggle="modal" data-target="#detail-modal"/>')  
			                .append($('<td id="chips-id" onclick="getDetail();"/>').html(data.id))  
			                .append($('<td/>').html(data.name))  
			                .append($('<td/>').html(data.func)) 
			                .append($('<td/>').html(data.freq)) 
			        );     
			    });    
			}
	    });          
	});  
}


function sortByKey(array, key) {
    return array.sort(function(a, b) {
        var x = a[key]; var y = b[key];
        return ((x > y) ? -1 : ((x < y) ? 1 : 0));
});
}