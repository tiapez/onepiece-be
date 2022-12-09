var flagUser=false;
var flagPass=true;
var flagMail=false;
var flagNick=false;
var flagAll= flagUser && flagPass && flagMail && flagNick;

function userValid() {
	var username = document.getElementById("username").value;
	var xhr = new XMLHttpRequest();
		xhr.open("POST", "userValidation", true);
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhr.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				document.getElementById("labelErrorUsername").setAttribute("hidden","hidden");
				flagUser=true;			
			}
			else{
				document.getElementById("labelErrorUsername").removeAttribute("hidden");
				flagUser=false;
			}
			buttonValid();
		}
		xhr.send("username="+username);	
			
}

function passwordValid() {
	var password = document.getElementById("password").value;
	var xhr = new XMLHttpRequest();
		xhr.open("POST", "passValidation", true);
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhr.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				document.getElementById("labelErrorPassword").setAttribute("hidden","hidden");
				document.getElementById("register").removeAttribute("disabled");							
			}
			else{
				document.getElementById("register").setAttribute("disabled","disabled");
				document.getElementById("labelErrorPassword").removeAttribute("hidden");
			}
			buttonValid();
		}
		xhr.send("password="+password);	
			
}

function mailValid() {
	var mail = document.getElementById("mail").value;
	var xhr = new XMLHttpRequest();
		xhr.open("POST", "mailValidation", true);
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhr.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				document.getElementById("labelErrorMail").setAttribute("hidden","hidden");
				flagMail=true;							
			}
			else{
				flagMail=false;
				document.getElementById("labelErrorMail").removeAttribute("hidden");
			}
			buttonValid();	
		}
		xhr.send("mail="+mail);	
		
}

function nickValid() {
	var nick = document.getElementById("nickname").value;
	var xhr = new XMLHttpRequest();
		xhr.open("POST", "nickValidation", true);
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhr.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				document.getElementById("labelErrorNickname").setAttribute("hidden","hidden");
				flagNick=true;							
			}
			else{
				flagNick=false;
				document.getElementById("labelErrorNickname").removeAttribute("hidden");
			}
			buttonValid();
		}
		xhr.send("nick="+nick);	
			
}

function buttonValid(){
	flagAll= flagUser && flagPass && flagMail && flagNick;
	if(flagAll)
		document.getElementById("register").removeAttribute("disabled");
	else
		document.getElementById("register").setAttribute("disabled","disabled");
}

function registerButton(){
	nickValid();
	mailValid();
	userValid();

	buttonValid();
	if(flagAll){
		document.getElementById("formRegister").submit();	
	}		
}

function loginButton(){
	var password = document.getElementById("password").value;
	var username = document.getElementById("username").value;
	var xhr = new XMLHttpRequest();
		xhr.open("POST", "loginValidation", true);
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhr.onreadystatechange = function() {
			if (this.status == 400) {
				document.getElementById("labelErrorLogin").removeAttribute("hidden");							
			}
			if (this.status == 200) {
				window.location.href = "/";						
			}
		}
		xhr.send("password="+password+"&username="+username);	
			
}


