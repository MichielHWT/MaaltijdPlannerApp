var openFile = function(event) {
    var input = event.target;

    var reader = new FileReader();
    reader.onload = function(){
      var dataURL = reader.result;
      var output = document.getElementById("uploadedRecipeImage");
      output.src = dataURL;
    };
    reader.readAsDataURL(input.files[0]);
	document.getElementById("buttonRemoveImage").style.display = "block";
 };
 
 function removeUploadedImage(){
	 var output = document.getElementById("uploadedRecipeImage");
     output.src = "";
	 document.getElementById("buttonRemoveImage").style.display = "none";
	 document.getElementById("postImageRecipe").value = "";
 }
	
function addUploadedImage(){
	/*
		POST Request to add the uploaded Image that comes with the new Recipe to the database as Entity "Image"
		
		send the image file as "FormData"
		
		https://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
	*/
	//var form = document.forms.namedItem("fileinfo");
	formData = new FormData(document.forms.namedItem("fileUploadForm"));
	var xhr = new XMLHttpRequest();
	//var formData = new FormData();
	//onsole.log(document.getElementById("postImageRecipe").files);
	//var fileName = document.getElementById("postImageRecipe").value;
	//var newImagefile = document.getElementById("postImageRecipe").files[0];
	//formData.append(fileName, newImagefile);
	console.log(formData);
	for (var [key, value] of formData.entries()) { 
		console.log(key, value);
	}
	xhr.open("POST", "http://localhost:8083//addUploadedImage", true);
	xhr.send(formData);		
}

