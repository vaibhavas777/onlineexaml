<h1 align="center" style="color: Brown;">IBPS EXAM PORTAL</h1>
<h1 align="center" style="color: blue ;">welcome to the exam portal</h1>
<h1 align="left" style="font-size:20px ;" style="color: red;">Exam Started...</h1>

<body style="background-color: pink">



<head>
<style>
qno {
	padding: 5px
}
</style>
</head>

<script>


var xmlhttp;

function getRemainingTime()
{
	xmlhttp= new XMLHttpRequest();

	xmlhttp.onload=showtime;
	
	xmlhttp.open("get","showRemainingTime",true);
	
	xmlhttp.send();
	
}

function showtime()
{
	
	alert(xmlhttp.responseText);
	
	if(xmlhttp.readyState==4 && xmlhttp.status==200){

		document.questionForm.show.value=xmlhttp.responseText;
		
		if(xmlhttp.responseText==0)
		{
			alert("Time Up !!")
			//xmlhttp.open("get","endexam",true);
			window.location.href="score";
		}
		
		
	}
}


setInterval(getRemainingTime,60000);// 1000 ms - 1 sec  60000ms - 60sec

function getResponse()
{
	
	var qno = document.questionForm.qno.value;
	var question = document.questionForm.question.value;
	var submittedAnswer = document.questionForm.option.value;
	alert(qno+" "+ question+  " " +  submittedAnswer )
	
	var xmlHttp = new XMLHttpRequest();
	var data = "?qno="+ qno+"&question="+question+"&submittedAnswer="+submittedAnswer;
	xmlHttp.open("get", "storeResponse"+ data, true);
	xmlHttp.send();
}
</script>

<body>
${message}
<form name="questionForm">

<label > Remaining Minutes </label>
<input  style="border:none" type="text" name="show" value="Total Duration 3 minutes"><br><br>

<input  type="button"  name="qno" value= ${question.qno}> Question: <br><br>

<b><textarea  rows=4 cols=50 name="question" style="border : none, text: bold" readonly > ${question.question} </textarea> </b><br><br>

<input type="radio"  name="option" value= "${question.option1}" onclick = "getResponse()"> <span> ${question.option1} </span><br><br>

<input type="radio"  name="option" value= "${question.option2}" onclick = "getResponse()" ><span> ${question.option2} </span><br><br>

<input type="radio"  name="option" value= "${question.option3}" onclick = "getResponse()"><span> ${question.option3} </span><br><br>

<input type="radio"  name="option" value= "${question.option4}"  onclick = "getResponse()"><span> ${question.option4} </span><br><br>


<input  type="submit" value= "next"  formaction="next" align="center"><br><br> 

<input  type="submit" value= "previous"  formaction="previous" align="right"><br><br> 

<input  type="submit" value= "end exam"  formaction="endexam" align="right"><br><br>
${message2}
 
</form>
</body>