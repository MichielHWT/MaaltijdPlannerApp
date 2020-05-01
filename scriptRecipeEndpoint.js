function getAllRecipes(){
	/*
		GET request to BackEnd RecipeEndpoint to get all Recipe objects from "maaltijdplanner_database/recipe" database
		
		Prints all recipe Id and name 
	*/
	
}

function getIngredientIdFromTable(){
	
	var ingredientId = document.getElementById("ingredientTable").value;
	console.log(ingredientId);
	
	return ingredientId;
}

function addNewRecipe(){
	/*
		POST request to BackEnd RecipeEndpoint to add Recipe object to "maaltijdplanner_database/recipe" database
		
		Arguments given are the Recipe object name and an array of Ingredient Ids
	*/
	var ingredientId = getIngredientIdFromTable();
	console.log(ingredientId);
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