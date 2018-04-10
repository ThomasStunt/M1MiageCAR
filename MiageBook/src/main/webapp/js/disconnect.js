function disconnect() {
	$.ajax({
		url: "disconnect",
		method: "GET"
	})
}