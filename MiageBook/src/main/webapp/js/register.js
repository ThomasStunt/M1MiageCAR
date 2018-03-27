var pwd;

$('#submit').onclick = function() {
	pwd = $('#inputPwd').val();
	console.log(pwd);
	if(($('#inputPwd').val().localCompare($('#confPwd').val())) != 0) {
		alert("Wrong matches");
	}
};