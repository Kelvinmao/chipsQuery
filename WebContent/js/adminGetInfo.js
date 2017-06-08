/**
 * 
 */
function ajaxInfo(){
    	$.ajax({
    		url:'getInfo.action',
    		data: [],
    		type:'get',
    		scriptCharset: 'utf-8',
    		"Content-Type":"application/json;charset=utf-8",
    		success: function(data){
    			var json=$.parseJSON(data);
    			$('#chip-num').text(json.cAmount);
    			$('#func-num').text(json.fAmount);
    			$('#user-num').text(json.uAmount);
    			$('#admin-num').text(json.aAmount);
    		}
    
  });
}
 