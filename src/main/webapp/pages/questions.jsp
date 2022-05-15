${message}
<h1 align="center" style="color: Maroon;">Welcome to the IBPS Exam portal</h1>
<h1 align="left" style="color: Maroon;">Select the subject......</h1>
<body style="background-color: pink">
<head>

<style>
select {
	padding: 7px
}
</style>

<script>


	function display(x) {
		//AJAX Engine started (Asynchronized JavaScript with XML)
		
		if(x.value != 'actionNoRequire')
		{
			var xmlHttp = new XMLHttpRequest();
			xmlHttp.open("get", "getQuestions?subject=" + x.value, true);
			xmlHttp.send();
		}
		
	}

	
</script>

</head>
<form>
	<select name="selectedSubject"  onchange="display(this)">
		<option value="actionNoRequire"> select subject  </option>
		<option value="English">English</option>
		<option value="Quantitative aptitude">Quantitative Aptitude</option>
	</select>
	<input type="submit" value="startExam" formaction="startExam">
</form>
${msg}