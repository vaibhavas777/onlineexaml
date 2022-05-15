<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<style>
table,th,td
{
border : 1px solid red;
border-collapse :collapse; 
}
th, td
{
padding : 10px;
text-align : centre;
}

table
{
margin-left : 180px
}
</style>


Your score is ${score}


<table>
<tr>

<th>qno</th>
<th>question</th>
<th>submittedAnswer</th>
<th>correctAnswer</th>

<tr>
<c:forEach var="answer" items= "${submittedAnswer.values()}">
<tr>
<td>${answer.qno}</td>
<td>${answer.question}</td>
<td>${answer.submittedAnswer}</td>
<td>${answer.correctAnswer}</td>
</tr>
</c:forEach>
</table>

<h1> Want to attempt exam again ?</h1>
<a href="loginpage" style="text-decoration : none"> Go to login page </a> <!-- href attribute needs url -->