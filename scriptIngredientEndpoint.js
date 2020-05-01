function getIngredientTable(){
	/*
		GET request to BackEnd IngredientEndpoint to get all Ingredient objects from "maaltijdplanner_database/ingredient" database
		
		Prints table of Ingredeint objects in <div> element with id "ingredientTable"
	*/
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(this.readyState == 4){
			//alert("GET");
			dataArray = JSON.parse(this.responseText);
			var objectKeys = Object.keys(dataArray[0]);
			var tableString = "";
			tableString += "<table style=width:100%> ";
			tableString += "<tr>";
			for(var a = 0; a < objectKeys.length; a++){
				tableString += "<th>" + objectKeys[a] + "</th>";
			}
			tableString += "<th>delete from database</th>";
			tableString += "</tr>";
			for(var a = 0; a < dataArray.length; a++){
				var valuesArray = Object.values(dataArray[a]);
				for(var b = 0; b < valuesArray.length; ++b){					
					tableString += "<td>" + valuesArray[b] + "</td>"; 					
				}
				tableString += "<td><input type=\"button\" value=\"delete\" onclick=\"deleteIngredient(" + dataArray[a].id + ")\"></td>"; //Add delete button
				tableString += "</tr>";
			}
			tableString += "</table>";
			document.getElementById("ingredientTable").innerHTML = tableString+"<br>";
			console.log(dataArray);
			console.log(objectKeys);
			console.log(Object.values(dataArray[0]));
		}
	}
	xhr.open("GET", "http://localhost:8083/ingredientTable", true); 
	xhr.send();
}

function addIngredient(){
	/*
		POST request to BackEnd IngredientEndpoint to add Ingredient object to "maaltijdplanner_database/ingredient" database
	*/	
	var newIngredient = {};
	newIngredient.naam = document.getElementById("postNaamIngredient").value;
	var hoJSON = JSON.stringify(newIngredient);
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(this.readyState == 4){
			getIngredientTable();
		}
	}
	xhr.open("POST","http://localhost:8083/addIngredient",true);
	xhr.setRequestHeader("Content-type", "application/json"); 
	xhr.send(hoJSON);
	
	console.log(newIngredient);
	console.log(hoJSON);
}

function deleteIngredient(id){
	/*
		DELETE request to BackEnd IngredientEndpoint to delete Ingredient object from "maaltijdplanner_database/ingredient" database
	*/
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(this.readyState == 4){
			getIngredientTable();
		}
	}
	xhr.open("DELETE", "http://localhost:8083//deleteIngredient",true); 
	xhr.setRequestHeader("Content-type", "application/json"); 
	var idJSON = JSON.stringify(id);
	xhr.send(idJSON);
	console.log(id)
}

function updateIngredientProtein_gValue(){
	/*
		PUT request to BackEnd IngredientEndpoint to update the ingredient protein_g value 
	*/
	
	var newProtein_gValue = document.getElementById("updateProtein_gIngredient").value;
	var ingredientId = document.getElementById("IdUpdatedIngredient").value;
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(this.readyState == 4){
			getIngredientTable();
		}
	}
	xhr.open("PUT", "http://localhost:8083//updateIngredientProtein_g/" + ingredientId,true); 
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.send(newProtein_gValue);
	getIngredientTable();
}

function getIngredientById(){
	/*
		GET request to BackEnd IngredientEndpoint to get Ingredient object from "maaltijdplanner_database/ingredient" database with a specific Id
		
		Ingredient id is taken from the text input with id "ingredientId"
	*/
	var ingredientId = document.getElementById("ingredientIdInput").value;
	console.log(ingredientId);
	var idJSON = JSON.stringify(ingredientId);
	console.log(idJSON);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(this.readyState == 4){
			ingredientObject = JSON.parse(this.responseText);
			document.getElementById("ingredientIdOutput").innerHTML = ingredientObject.naam;
			console.log(ingredientObject);
		}
	}
	xhr.open("GET", "http://localhost:8083//getIngredientById//" + ingredientId, true); 
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.send(idJSON);	
}

function getIngredientByName(){
	/*
		GET request to BackEnd IngredientEndpoint to get Ingredient object from "maaltijdplanner_database/ingredient" database with a specific name
		
		Ingredient name is taken from the text input with the id ""
	*/
	
	
	
}
			
			
			
			
			
			
			
			
			
			
			
			
			
			