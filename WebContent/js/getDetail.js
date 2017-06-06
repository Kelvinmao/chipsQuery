/**
 * 
 */

function GetRowNo(obj) { 
	  var ev=ev||window.event; 
	  var target=ev.target||ev.srcElement; 
	  if(target.tagName.toLowerCase()=="td"){ 
	    obj=target.innerHTML;
	  } 
	  return obj;
}


function getDetail(){
	    var chip_id=0;
	    var otb=document.getElementById("table");
	    chip_id=GetRowNo(chip_id);
    	$.ajax({
    		url:'showDetailByAjax.action?chipID='+chip_id,
    		data: [],
    		type:'post',
    		scriptCharset: 'utf-8',
    		"Content-Type":"application/json;charset=utf-8",
    		success: function(data){
    			var detail=$.parseJSON(data);
    			$("#detail-table tr:has(td)").remove();
			    $("#detail-table").append($('<tr/>')  
			               	.append($('<td>').html(detail.id))
			                .append($('<td>').html(detail.name))  
			                .append($('<td>').html(detail.func)) 
			                .append($('<td>').html(detail.pinnum)) 
			                .append($('<td>').html(detail.pindef)) 
			                .append($('<td>').html(detail.intro)) 
			    );     
    		}
  });
}
 