<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String resultName = (String)request.getAttribute("MAIL_RESULT");
String context_root = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mail Trigger</title>
</head>
<body>
<form name="MailForm" method="post" action="<%=context_root%>/MailTrigger">
<input type="button" name = "Take_Backup" value="Take Backup" onclick = "callMailTrigger();"></input>
</form>
</body>
<script>
var result = "<%=resultName%>";
if(result!=""&&result=="MAIL_TRIGGERED"){
alert("Mail initiated..!");	
}else if(result!=""&&result=="MAIL_TRIGGER_FAIL"){
alert("Mail initiation failed..!");
}
else if(result!=""&&result=="MAIL_TRIGGER_FAIL"){
	alert("Mail initiation failed..!");
	}
else if(result!=""&&result=="NO_INT_CONN"){
	alert("System not connected to internet.!");
	}
else if(result!=""&&result=="BKUP_FAIL"){
	alert("Failed to create backup..!");
	}

function callMailTrigger(){
alert("Going to initiate mail Trigger..!");
document.forms[0].submit();

}
</script>
</html>