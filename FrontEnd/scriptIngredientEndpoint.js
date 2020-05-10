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
			tableString += "<table id=\"ingredientTable\" style=width:100% class=\"table table-striped\"> ";
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
				tableString += "<td><input type=\"button\" value=\"edit\" onclick=\"editIngredient(" + dataArray[a].id + ")\"></td>"; //Add edit button
				//tableString += "<td><input type=\"button\" value=\"delete\" onclick=\"deleteIngredientPopUp(" + dataArray[a].id + ")\"></td>"; //Add delete button
				tableString += "<td><input type=\"button\" value=\"delete\" onclick=\"deleteIngredient(" + dataArray[a].id + ")\"></td>"; //Add delete button
				tableString += "</tr>";
			}
			tableString += "</tbody></table>";
			document.getElementById("ingredientTableDiv").innerHTML = tableString+"<br>";
			document.getElementById("hideIngredientButton").style.display = "block";
			document.getElementById("showIngredientButton" ).style.display = "none";
			console.log(dataArray);
			console.log(objectKeys);
			console.log(Object.values(dataArray[0]));
		}
	}
	xhr.open("GET", "http://localhost:8083/ingredientTable", true); 
	xhr.send();
}

function removeIngredientTable(){
	/*
		Remove the table of ingredients from screen
	*/
	document.getElementById("ingredientTable").innerHTML = "";
	document.getElementById("hideIngredientButton").style.display = "none";
	document.getElementById("showIngredientButton" ).style.display = "block";
}

function editIngredient(ingredientId){
	/*
		Show edit component on screen with input fields for each field of Ingredient and 
		
		Changes edit button in "save edit", when clicked call updateIngredient(ingredientId)
	*/
	var tableLine = document.getElementById("ingredientTable").rows[ingredientId];
	console.log(tableLine);
	for(var i = 1; i < (tableLine.cells.length - 2); ++i){
		var cellLabel = document.getElementById("ingredientTable").rows[0].cells[i].innerHTML;
		var cellValue = tableLine.cells[i].innerHTML;
		document.getElementById("ingredientTable").rows[ingredientId].cells[i].innerHTML = "<input type=\"text\" value=\""+ cellValue +"\" id=\"row" + ingredientId + "cell" + cellLabel + "IngredientTable\">";
		console.log(cellValue);
	}	
	document.getElementById("ingredientTable").rows[ingredientId].cells[tableLine.cells.length-2].innerHTML = "<input type=\"button\" value=\"save edit\" onclick=\"updateIngredient(" + ingredientId + ")\">"; //edit button
}

function updateIngredient(ingredientId){
	/*
		PUT request to BackEnd IngredientEndpoint to update the ingredient values
		
		Sends JSON object to BackEnd with all Ingredient field values. If not changed no problem)
	*/
	var ingredientObject = {};
	ingredientObject["id"] = ingredientId;
	var tableLine = document.getElementById("ingredientTable").rows[ingredientId];
	for(var i = 1; i < (tableLine.cells.length - 2); ++i){
		var cellLabel = document.getElementById("ingredientTable").rows[0].cells[i].innerHTML;
		ingredientObject[cellLabel] = document.getElementById("row" + ingredientId + "cell" + cellLabel + "IngredientTable").value;
		document.getElementById("ingredientTable").rows[ingredientId].cells[i].innerHTML = document.getElementById("row" + ingredientId + "cell" + cellLabel + "IngredientTable").value;
	}
	document.getElementById("ingredientTable").rows[ingredientId].cells[tableLine.cells.length-2].innerHTML = "<input type=\"button\" value=\"edit\" onclick=\"editIngredient(" + ingredientId + ")\">"; //edit button
	console.log(ingredientObject);
	var JSONIngredientObject = JSON.stringify(ingredientObject);	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(this.readyState == 4){
			
		}
	}
	xhr.open("PUT", "http://localhost:8083//updateIngredient" ,true); 
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.send(JSONIngredientObject);
}

function showAddIngredient(){
	/*
		When clicked on "Add ingredient" button show the form in which new ingredient object fields can be filled in
		
		This excludes id
	*/
	console.log("Add new ingredient called");
	var display = document.getElementById("addIngredient").style.display;
	console.log(display);
	if(display == "block"){
		document.getElementById("addIngredient").style.display = "";
		document.getElementById("addNewIngredientButton").value = "Add new ingredient";
		console.log("Add ingredient closed");
	}
	else if(display == ""){
		document.getElementById("addIngredient").style.display = "block";
		document.getElementById("addNewIngredientButton").value = "Cancel add new ingredient";
		console.log("Add ingredient open");
	}
}

function addIngredient(){
	/*
		POST request to BackEnd IngredientEndpoint to add Ingredient object to "maaltijdplanner_database/ingredient" database
	*/	
	var newIngredient = {};
	newIngredient.naam = document.getElementById("postNaamIngredient").value;
	newIngredient.name = document.getElementById("postNameIngredient").value;
	newIngredient.naam2 = document.getElementById("postNaam2Ingredient").value;
	newIngredient.energy_kJ = document.getElementById("postEnergy_kJIngredient").value;
	newIngredient.energy_kcal = document.getElementById("postEnergy_kcalIngredient").value;
	newIngredient.protein_g = document.getElementById("postProteinIngredient").value;	
	
	newIngredient.measureUnit = "g";
	newIngredient.amount = 100;
	
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

function deleteIngredientPopUp(id){
	/*
		Create popup to confirm deletion (use bootstrap modal)
	*/
	document.getElementById("deleteIngredientModal").modal;
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

function getIngredientById(ingredientId){
	/*
		GET request to BackEnd IngredientEndpoint to get Ingredient object from "maaltijdplanner_database/ingredient" database with a specific Id
		
		Ingredient id is taken from the text input with id "ingredientId"
	*/
	//var ingredientId = document.getElementById("ingredientIdInput").value;
	console.log(ingredientId);
	var idJSON = JSON.stringify(ingredientId);
	console.log(idJSON);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(this.readyState == 4){
			ingredientObject = JSON.parse(this.responseText);
			//document.getElementById("ingredientIdOutput").innerHTML = ingredientObject.naam;
			console.log(ingredientObject);
			return ingredientObject;
		}
	}
	xhr.open("GET", "http://localhost:8083//getIngredientById//" + ingredientId, true); 
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.send(idJSON);
}

async function getInputSearchBarIngredient(){
	var inputSearchIngredient = document.getElementById("inputSearchIngredientByName").value;
	var listOfIngredients = await getIngredientByName(inputSearchIngredient);
	createDropDownMenu(listOfIngredients);
}

function getIngredientByName(ingredientName){
	return new Promise(function(resolve,reject){
		/*
			GET request to BackEnd IngredientEndpoint to get Ingredient object from "maaltijdplanner_database/ingredient" database with a specific name
		*/
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(this.readyState == 4){
				listOfIngredients = JSON.parse(this.responseText);
				//console.log(listOfIngredients);
				//createDropDownMenu(listOfIngredients);
				resolve(listOfIngredients);
			}
		}
		
		xhr.open("GET", "http://localhost:8083//getIngredientByName//" + ingredientName, true); 
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.send();	
	});
}

function createDropDownMenu(listOfIngredients){
	/*
		Create the drop down menu with <select> https://www.w3schools.com/tags/tag_select.asp
		
		Use <mark></mark> to highlight the searched characters
	*/
	document.getElementById("searchIngredientDropDownMenu").innerHTML = "";
	var inputSearchIngredient = document.getElementById("inputSearchIngredientByName").value;
	//console.log(listOfIngredients);
	//var newElement = document.createElement("");
	//for(ingredient in listOfIngredients)
	for(var i = 0; i < listOfIngredients.length; ++i)
	{
		//console.log(listOfIngredients[i].name);
		var newElement = document.createElement("option");
		newElement.value = listOfIngredients[i].name;
		var newElementText = document.createTextNode(listOfIngredients[i].name);
		newElement.appendChild(newElementText);
		document.getElementById("searchIngredientDropDownMenu").appendChild(newElement);
	}
	//document.getElementById("searchIngredientDropDownMenu").appendChild(newElement);
}

async function addIngredientToRecipe(){
	/*
		Add Ingredient chosen in the search bar to the ingredient list for making a recipe
		
	*/
	ingredientName = document.getElementById("searchIngredientDropDownMenu").value;
	console.log(ingredientName);
	var ingredientObjectList = await getIngredientByName(ingredientName);
	var ingredientObject = ingredientObjectList[0];
	console.log(ingredientObject);
	document.getElementById("ingredientLine").innerHTML = ingredientObject.name;
}
					