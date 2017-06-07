/**
 * 
 */

function ajaxData(){
	$(document).ready(function () { 
	    $.ajax({
			
	    	url:'.action',
			data: [],
			type:'post',
			scriptCharset: 'utf-8',
			"Content-Type":"application/json;charset=utf-8",
			success: function(data){
				
			}
	    });          
	});  
}
