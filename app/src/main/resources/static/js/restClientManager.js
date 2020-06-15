$(document).on('click', '.myFiles', function (e) {
	
	
	$.ajax({                                         
		   url: e.target.id + '/checkfiles/filepath/MyFiles',
		   type: "GET",
		   success: function (data, status, xhr) {
			  console.log(data);
			  $("#fileList").empty();
			  jQuery.each(data.response, function(i, val) {
				 /* with the response it fills #fileList */
				 $("#fileList").append("<li><h4>File name: " + val.fileName + ", Validation: " + val.valid + "</h3></li>");
			  });
		   },
		   error: function(result) {
			  alert("Error!");
			  console.log(result);
		   }
		});
	
	
	
});