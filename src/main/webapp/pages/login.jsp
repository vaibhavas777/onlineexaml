${message}


<form >
<h1 align="center" style="color: Maroon;">Welcome to the IBPS Exam Portal</h1>

<body style="background-color:pink">
<label for="username" style="color:darkblue;"><b>Username :  </b></label>
<input type="text"  name="username" placeholder= "Enter the username" required><br><br>
<label for="username"style="color:darkblue;"><b>Password : </b></label>
<input type="password" name="password" type= "password" placeholder= "Enter the password" required><br><br>
<input type= "submit" value= "login" formaction= "login" >

</form>
<a href="showRegisterPage" > Register here </a>
${errormessage}
${endexam}