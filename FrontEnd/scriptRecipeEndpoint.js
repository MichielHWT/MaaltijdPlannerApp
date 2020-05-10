function getRecipeTable(){
	/*
		GET request to BackEnd IngredientEndpoint to get all Recipe objects from "maaltijdplanner_database/recipe" database
		
		Prints table of Recipe objects in <div> element with id "recipeTable"
	*/
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(this.readyState == 4){
			//alert("GET");
			dataArray = JSON.parse(this.responseText);
			var objectKeys = Object.keys(dataArray[0]);
			var tableString = "";
			tableString += "<table id=\"recipeTable\" style=width:100% class=\"table table-striped\"> ";
			//tableString += "<table class=\"table table-striped\"> ";
			tableString += "<thead><tr>";
			for(var a = 0; a < objectKeys.length; a++){
				tableString += "<th scope=\"col\">" + objectKeys[a] + "</th>";
			}
			tableString += "<th scope=\"col\">edit database</th>";
			tableString += "<th scope=\"col\">delete from database</th>";
			tableString += "</tr></thead>";
			tableString += "<tbody>";
			for(var a = 0; a < dataArray.length; a++){
				tableString += "<tr>";
				var valuesArray = Object.values(dataArray[a]);
				for(var b = 0; b < valuesArray.length; ++b){
					if(b==0)
					{
						tableString += "<th scope=\"row\">" + valuesArray[b] + "</th>";	
					}	
					else
					{
						tableString += "<td>" + valuesArray[b] + "</td>"; 	
					}					
				}
				tableString += "<td><input type=\"button\" value=\"edit\" onclick=\"editRecipe(" + dataArray[a].id + ")\"></td>"; //Add edit button
				tableString += "<td><input type=\"button\" value=\"delete\" onclick=\"deleteRecipePopUp(" + dataArray[a].id + ")\"></td>"; //Add delete button
				tableString += "</tr>";
			}
			tableString += "</tbody></table>";
			document.getElementById("recipeTableDiv").innerHTML = tableString+"<br>";
			document.getElementById("hideRecipeButton").style.display = "block";
			document.getElementById("showRecipeButton" ).style.display = "none";
			console.log(dataArray);
			console.log(objectKeys);
			console.log(Object.values(dataArray[0]));
		}
	}
	xhr.open("GET", "http://localhost:8083/recipeTable", true); 
	xhr.send();
}

function removeRecipeTable(){
	/*
		Remove the table of ingredients from screen
	*/
	document.getElementById("recipeTable").innerHTML = "";
	document.getElementById("hideRecipeButton").style.display = "none";
	document.getElementById("showRecipeButton" ).style.display = "block";
}

function showAddRecipe(){
	/*
		When clicked on "Add recipe" button show the form in which new recipe object fields can be filled in
		
		This excludes id
	*/
	console.log("Add new recipe called");
	var display = document.getElementById("addRecipe").style.display;
	console.log(display);
	if(display == "block"){
		document.getElementById("addRecipe").style.display = "";
		document.getElementById("addNewRecipeButton").value = "Add new recipe";
		console.log("Add recipe closed");
	}
	else if(display == ""){
		document.getElementById("addRecipe").style.display = "block";
		document.getElementById("addNewRecipeButton").value = "Cancel add new recipe";
		console.log("Add recipe open");
	}
}


function addNewRecipe2(){
	/*
		POST request to BackEnd RecipeEndpoint to add Recipe object to "maaltijdplanner_database/recipe" database
		
		Arguments given are the Recipe object name and an array of Ingredient Ids
	*/
	var newRecipe = {};
	var newRecipeName = document.getElementById("postNameRecipe").value;
	newRecipe.name = newRecipeName;
	var newRecipeDescription = document.getElementById("recipeDescription").value;
	newRecipe.description = newRecipeDescription;
	var recipeJSON = JSON.stringify(newRecipe);
	var arrayIngredientIds = document.getElementById("ID1").value + "_" + document.getElementById("ID2").value;
	console.log("Send POST request for new recipe " + newRecipeName);
	var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
		
	}
	xhr.open("POST","http://localhost:8083//recipe//" + arrayIngredientIds,true);
	xhr.setRequestHeader("Content-type", "application/json"); 
	xhr.send(recipeJSON);		
}


function sendRecipeByMail(){
	/*
		POST request to BackEnd MailEndpoint to RecipeEndpoint to send the recipe description of given recipe by e-mail to given mailadress
		
		Arguments given are the recipe name and mailadress of receiver
	*/
	
	var recipeName = document.getElementById("mailRecipeNameInput").value;
	var emailAdress = document.getElementById("mailAdressInput").value;
	console.log(recipeName, emailAdress);
	var emailAdressJSON = JSON.stringify(emailAdress);
	console.log(emailAdressJSON);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		
	}
	xhr.open("POST", "http://localhost:8083//sendMailRecipe//" + recipeName, true);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.send(emailAdress);
}