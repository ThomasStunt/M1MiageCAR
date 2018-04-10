var cont = true;

$('#sbmButton').click(function() {
	console.log(cont);	
	
	$('#errorDiv').prop('hidden', true);
	$('#errorDiv').empty();
	
	$('#registerForm input').each(function(i, val) {
		
		if ($(this).val() === '') {
			$('#errorDiv').append("<br /><font color='red'>Input "+$(this).attr('name')+" is empty.</font>");
			cont = false;
		}
	});

	$('#errorDiv').prop('hidden', false);
	
	if($('#inputPwd').val() !== $('#confPwd').val()) {
		alert("Wrong matches");
		cont = false;
	}

	console.log(cont);
	
	if(cont === false) {
		return false;
	} else {
		return true;
	}
});	

$(document).ready( function () {
	
})