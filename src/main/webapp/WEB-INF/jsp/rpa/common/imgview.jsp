<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String imgurl = ""; 
    if (request.getAttribute("imgurl") == null) imgurl = "";
    else
        imgurl = request.getAttribute("imgurl").toString();

%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>이미지보기</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript">
 //<![CDATA[
function printPage(){
	var initBody;
	window.onbeforeprint = function(){
	    initBody = document.body.innerHTML;
	    document.body.innerHTML =  document.getElementById('print').innerHTML;
	};
	window.onafterprint = function(){
	    document.body.innerHTML = initBody;
	};
	window.print();
	return false;
}
 //]]>
 </script>

  
</head>
<body>
<img src="/rpa/cmm/display.do?searchAtchFileId=${atchFileId}" style="max-width: 1400px; vertical-align: middle"/>
</body>
</html>