			
			
			
			function getCookie(name) {
				  let matches = document.cookie.match(new RegExp(
				    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
				  ));
				  return matches ? decodeURIComponent(matches[1]) : undefined;
				}
			
			if(getCookie("cardListOption") == ""){
				document.cookie = "cardListOption= 0";
			}	
			else{
				let cardListOption = getCookie("cardListOption");
				if(cardListOption == 0){
					document.getElementById('card-list-img').classList.remove('card-list-none');
					document.getElementById('card-list-text').classList.add('card-list-none');
				}		
				else{
					document.getElementById('card-list-text').classList.remove('card-list-none');
					document.getElementById('card-list-img').classList.add('card-list-none');
				}
					
			}
			
			function changeDisplay(){
	
				let cardListOption = getCookie("cardListOption");
				if(cardListOption == 0){
					document.cookie = "cardListOption= 1";
					document.getElementById('card-list-text').classList.remove('card-list-none');
					document.getElementById('card-list-img').classList.add('card-list-none');
				}		
				else{
					document.cookie = "cardListOption= 0";
					document.getElementById('card-list-img').classList.remove('card-list-none');
					document.getElementById('card-list-text').classList.add('card-list-none');
				}
}