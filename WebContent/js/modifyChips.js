/**
 * 
 */
$(function() {
    $('#submit-button').on('click', function() {
        var modelID = $('#model-id input').val();
        var chipName = $('#chip-name input').val();
        var chipID = $('#ChipID').val();
        var chipFun= $('#chip-functions input').val();
        var pinNum=$('#chip-pins input').val();
        var pinDef=$('#pins-def input').val();
        var chipIntro=$('#chip-intro input').val();
        
        	$('#modify-attention').hide();
            $.ajax({
                url: 'submitModify.action',
                type: 'post',
                data:
                {
                    'ChipID': chipID,
                    'ModelID': modelID,
                    'Functions': chipFun,
                    'PinNumber': pinNum,
                    'PinDefination':pinDef,
                    'ChipIntro':chipIntro,
                    'ChipName': chipName
                },
                //dataType: "JSON"
            }).done(function(data, status, xhr) {
              if(data=="fail") {
                  $('#modify-attention').text("修改失败");
                  $('#modify-attention').show();
              }
              else {
            	  $('#modify-attention').text(data);
                  $('#modify-attention').show();
//                  alert("修改成功");
              }
                
            }).fail(function() {
                $('#modify-attention').text("提交失败，请检查网络");
                $('#modify-attention').show();
            });    
    });
});


