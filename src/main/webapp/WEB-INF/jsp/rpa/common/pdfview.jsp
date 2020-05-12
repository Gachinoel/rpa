<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String pdfurl = ""; 
    if (request.getAttribute("pdfurl") == null) pdfurl = "";
    else
        pdfurl = request.getAttribute("pdfurl").toString();
        pdfurl = pdfurl.replace("\\","");

%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge;FF=7;OtherUA=4;chrome=1" />

<title>이미지보기</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
    .pdfobject-container {
        width: 100%;
        max-width: 1450px;
        height: 560px;
        margin: 1em 0;
    }
    .pdfobject { border: solid 1px #666; }
    .hidden { display: none; }
    .success { color: #4F8A10; background-color: #DFF2BF; }
    .fail { color: #D8000C; background-color: #FFBABA; }
</style>


</head>
<body>
	<div id="results" class="hidden"></div>
    <div id="pdfContent"></div>

</body>
<script src="/js/pdfobject.js?3"></script>
<script src="/js/pdfobject.min.js?3"></script>

<script>

    var options = {
    		pdfOpenParams: {
				navpanes: 0,
				toolbar: 0,
				statusbar: 0,
				view: "FitV",
			
				page: 1
			}
			,forcePDFJS: true
        	,PDFJS_URL: "/resources/pdfjs/web/viewer.html"
    };
    PDFObject.embed("/rpa/cmm/display.do?searchAtchFileId=${atchFileId}", "#pdfContent");
    
	//console.log("myPDF : "+myPDF);
	//var el = document.querySelector("#results");
	//el.setAttribute("class", (myPDF) ? "success" : "fail");
	//el.innerHTML = (myPDF) ? "PDFObject was successful!" : "Uh-oh, the embed didn't work.";

</script>
 <!-- 
<object data="your_url_to_pdf" type="application/pdf">
    <embed src="/display.do?searchAtchFileId=${atchFileId}" type="application/pdf" class="pdfobject-container" />
</object>
 -->
</html>