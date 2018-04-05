var cont = true;

function btnSubmit() {
	cont = true;
	
	$('#errorDiv').prop('hidden', true);
	$('#errorDiv').empty();
	
	$('#registerForm input').each(function(i, val) {
		if ($(this).val() === '' || $(this).val() === null) {
			cont = false;
			$('#errorDiv').append("<br /><font color='red'>Input "+$(this).attr('name')+" is empty.</font>");
			return;
		}
	});

	$('#errorDiv').prop('hidden', false);
	
	if($('#inputPwd').val() !== $('#confPwd').val()) {
		alert("Wrong matches");
		cont = false;
	}
	
	console.log(cont);
	
	if(cont === true) {
		$('#registerForm').submit();
	}
};

$(document).ready( function () {
	$('#inputPseudo').bind('input', function() {
		console.log($(this).val());
	});
})