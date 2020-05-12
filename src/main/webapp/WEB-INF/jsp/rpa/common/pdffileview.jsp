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

<title>PDF보기</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<style>
    .pdfobject-container {
        width: 100%;
        min-width: 800px;
        min-height: 560px;
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
    <div id="pdf"></div>

</body>
<script src="/js/pdfobject.js?3"></script>
<script src="/js/pdfobject.min.js?3"></script>

<script>
$(function() {
	$('#pdf').css('height', $(window).height() - 50 );

    var options = {
        pdfOpenParams: {
            navpanes: 0,
            toolbar: 0,
            statusbar: 0,
            view: "FitV",
            page: 1
        },
        forcePDFJS: true,
        PDFJS_URL: "/resources/pdfjs/web/viewer.html"
    };

    var myPDF = PDFObject.embed("${pdfurl}", "#pdf",options);
//    var el = document.querySelector("#results");
//    el.setAttribute("class", (myPDF) ? "success" : "fail");
//    $("#results").innerHTML = (myPDF) ? "PDFObject was successful!" : "Uh-oh, the embed didn't work.";


    $(window).bind('resize', function(e)
	{
		$('.pdfobject-container').css('height', $(window).height()  );
		
	});
});	
</script>
  
</html>