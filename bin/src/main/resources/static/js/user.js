var flagUser=false;
var flagPass=true;
var flagMail=false;
var flagNick=false;
var flagAll= flagUser && flagPass && flagMail && flagNick;





			function getCookie(name) {
				  let matches = document.cookie.match(new RegExp(
				    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
				  ));
				  return matches ? decodeURIComponent(matches[1]) : undefined;
				}
			if(getCookie("userLogged") != "")
				document.getElementById('userLogged').textContent = getCookie("userLogged");
	
