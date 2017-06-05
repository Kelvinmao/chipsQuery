/**
 * 
 */
function ajaxData(){
    	$.ajax({
    		url:'getInfo.action',
    		data: [],
    		type:'get',
    		scriptCharset: 'utf-8',
    		"Content-Type":"application/json;charset=utf-8",
    		success: function(data){
    			$('#chip-num').text(data);
    		}
    
  });
}
 