function getAllAccounts(){
	/*
		GET request to BackEnd AccountEndpoint to get all Account objects from "maaltijdplanner_database/account" database
		
		Prints all accounts fields in a table 
	*/
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(this.readyState == 4){
			dataArray = JSON.parse(this.responseText);
		}
	}	
}

function getAccountByName(accountName){
	return new Promise(function(resolve,reject){
		//console.log("getAccounByName");
		var accountObject = {};
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(this.readyState == 4){
				//console.log(this.responseText);
				if(this.responseText == ""){
					accountObject = null;
				}
				else{
					accountObject = JSON.parse(this.responseText);
				}
				//console.log("Account object: ");
				//console.log(accountObject);
				resolve(accountObject);
			}
		}
		xhr.open("GET", "http://localhost:8083//getAccountByName/" + accountName, true); 
		xhr.send();
	});
}

async function loginAccount(){
	/*
		GET Request to the BackEnd  to check if Account with given name already exists
	*/
	var loginAccountName = document.getElementById("inputAccountName").value;
	var loginAccountObject = await getAccountByName(loginAccountName);
	//console.log(loginAccountObject);
	if(loginAccountObject == null){
		var message = await addAccount(loginAccountName);
		loginAccountObject = await getAccountByName(loginAccountName);
		
		//console.log(loginAccountObject);
		if(loginAccountObject.name == "admin"){
			window.location.href = "file:///D:/WorkingTalent/JavaProjectMaaltijdApp/maaltijdplanner/FrontEnd/maaltijdplannerDeveloperPage.html";
		}
		else{
			window.location.href = "file:///D:/WorkingTalent/JavaProjectMaaltijdApp/maaltijdplanner/FrontEnd/maaltijdplannerUserPage.html";
			document.getElementById("accountNameUser").value = loginAccountObject.name;
		}
	}	
	else{		
		if(loginAccountObject.name == "admin"){
			window.location.href = "file:///D:/WorkingTalent/JavaProjectMaaltijdApp/maaltijdplanner/FrontEnd/maaltijdplannerDeveloperPage.html";
		}
		else{
			window.location.href = "file:///D:/WorkingTalent/JavaProjectMaaltijdApp/maaltijdplanner/FrontEnd/maaltijdplannerUserPage.html";
			document.getElementById("accountNameUser").value = loginAccountObject.name;
			//console.log(loginAccountObject);
			//console.log(loginAccountObject.name);
		}
	}
}

function addAccount(newAccountName){
	/*
		POST Account object to the Backend as JSON 
				
	*/
	return new Promise(function(resolve,reject){
		var newAccountObject = {};
		newAccountObject.name = newAccountName;
		var acJSON = JSON.stringify(newAccountObject);
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(this.readyState == 4){
				resolve("Succesfully added new account");
			}
		}
		xhr.open("POST","http://localhost:8083/addNewAccount",true);
		xhr.setRequestHeader("Content-type", "application/json"); 
		xhr.send(acJSON);	
	});
}