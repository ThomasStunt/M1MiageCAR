function btnSubmit() {
	var cont = true;
	
	$('#errorDiv').prop('hidden', true);
	$('#errorDiv').empty();
	
	$('#registerForm input').each(function(i, val) {
		if ($(this).val() === '' || $(this).val() === null) {
			cont = false;
			$('#errorDiv').append("<br /><font color='red'>Input "+$(this).attr('name')+" is empty.</font>");
			
			$("#registerForm").submit(function(e){
		        e.preventDefault();
		    });
		}
	});

	$('#errorDiv').prop('hidden', false);
	
	if($('#inputPwd').val() !== $('#confPwd').val()) {
		alert("Wrong matches");
	}
};

$(document).ready( function () {
	$('#inputPseudo').bind('input', function() {
		console.log($(this).val());
	});
})