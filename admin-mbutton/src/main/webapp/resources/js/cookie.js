/*쿠키저장*/
function setCookie (name, value, expires) {
	document.cookie = name + "=" + escape (value) + "; path=/;expires=" + expires.toGMTString();
}
/*쿠기가져오기*/
function getCookie(cookieName) {   
	var result = "";
	var allCookies = document.cookie.split("; ");
	var cookieArray = null;
	for(i=0;i<allCookies.length;i++) {
		cookieArray = allCookies[i].split("=");
		if(cookieName == cookieArray[0]) {
			result = cookieArray[1];
			break;
		}
	}
	return result;
}
/*id저장*/
function saveId(form) {
	var expdate = new Date();
	
	if ($("#checkId","#"+form).is(":checked")) {
		expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 365); // 1년
		setCookie("cookieId", $("#userId","#"+form).val(), expdate);
	} else {
		expdate.setTime(expdate.getTime() - 1); 
		setCookie("cookieId", $("#userId","#"+form).val(), expdate);
	}
}
/*id setting*/
function setUserId() {
	var userId = getCookie("cookieId");
	if(userId != "") {
		 $("#userId","#"+form).val(userId);
		 $("#checkId","#"+form).prop("checked",true);
		 $("#userPwd","#"+form).focus();
		
	} else {
		 $("#userId","#"+form).focus();
	}
}

//window.onload = function() {
//	form="loginFrm";
//	setTimeout("setUserId("+form+")",50);
	
//};