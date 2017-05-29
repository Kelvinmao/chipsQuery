/**
 * 
 */

function visitorLogout(){
			document.cookie = "user_id=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
			document.cookie = "user_pwd=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
			alert("注销成功");
}