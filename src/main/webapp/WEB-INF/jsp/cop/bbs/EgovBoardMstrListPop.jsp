
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="org.apache.poi.hssf.usermodel.*" %>
<%@page import="java.io.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
 --%>
 
 
<%
String searchBlNo = (String)request.getParameter("searchBlNo");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js" ></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<meta http-equiv="X-UA-Compatible" content="IE=edage,chrome=1" >


	<title>엑셀 목록</title>
	<link href="<c:url value='/'/>css/common.css" rel="stylesheet" type="text/css" >

	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
		function doExcelDownloadProcess(gubun){
			
			
			var f = document.excelfrm;
			if(gubun == 1){
	        	f.action="<c:url value='/cop/bbs/SelectInvoiceList.do'/>"
			}else if(gubun == 2){
				f.action="<c:url value='/cop/bbs/SelectCargoList.do'/>"
			}else if(gubun == 3){
				f.action="<c:url value='/cop/bbs/SelectBlList.do'/>"
			}else if(gubun == 4){
				f.action="<c:url value='/cop/bbs/SelectPassList.do'/>"
			}else if(gubun == 5){
				f.action="<c:url value='/cop/bbs/SelectDocList.do'/>"
			}else if(gubun == 6){
				f.action="<c:url value='/cop/bbs/SelectSapList.do'/>"
			
			}
			
	        f.submit();
	    }
		
	
	
		
	</script>
	
	<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
	
	A:link    { color: #000000; text-decoration:none; }
	A:visited { color: #000000; text-decoration:none; }
	A:active  { color: #000000; text-decoration:none; }
	A:hover   { color: #fa2e2e; text-decoration:none; }	
</style>
	


</head>




<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>


<!-- 전체 레이어 시작 -->
	<div id="wrap">

    <!-- header 시작 -->
    <div id="header"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncHeader" /></div>
    <div id="topnavi"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncTopnav" /></div>        
    <!-- //header 끝 -->


	    
	    
	    	<!-- 좌측메뉴 시작 -->
	        <div id="leftmenu"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncLeftmenu" /></div>
	        <!-- //좌측메뉴 끝 -->
	    		
	    		<div id="content">
	    	
		    	 	<form name="excelfrm"  method="post">
					    	<table>
		                		
		                		<input type="hidden" id = "searchBlNo" name="searchBlNo"  value="<%=searchBlNo %>"/>
		                    
			                	<tr>
							        <div id="default_tablestyle">
					                	<div class="buttons" style="position:absolute;left:250px;top:100px;">
					                     <a href="#" onclick="doExcelDownloadProcess(1);">수입신고 엑셀다운</a>
					                </div>
								</tr>
								
								<tr>
					                <div id="default_tablestyle">
					                	<div class="buttons" style="position:absolute;left:250px;top:140px;">
					                     <a href="#" onclick="doExcelDownloadProcess(2);">적하보험 엑셀다운</a>
					                </div>
								</tr>
								<tr>
					                <div id="default_tablestyle">
					                	<div class="buttons" style="position:absolute;left:250px;top:180px;">
					                	
					                	<script type ="text/javascript">
						                	var agent = navigator.userAgent.toLowerCase();
						                	if ( (navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1) ) {
						                		document.write('<a href="#" onclick="doExcelDownloadProcess(3);">BL&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 엑셀다운</a>');
							                	
								            }else{
								            	document.write('<a href="#" onclick="doExcelDownloadProcess(3);">BL&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 엑셀다운</a>');
							                	
								            }
					                	</script>
					                
					                
					                </div>
								</tr>
																
								<tr>
					                <div id="default_tablestyle">
					                	<div class="buttons" style="position:absolute;left:250px;top:220px;">
					                     <a href="#" onclick="doExcelDownloadProcess(4);">수입통관 엑셀다운</a>
					                </div>
								</tr>
								
								<tr>
					                <div id="default_tablestyle">
					                	<div class="buttons" style="position:absolute;left:250px;top:260px;">
					                     <a href="#" onclick="doExcelDownloadProcess(5);">송장생성 엑셀다운</a>
					                </div>
								</tr>
								
								<tr>
					                <div id="default_tablestyle">
					                	<div class="buttons" style="position:absolute;left:250px;top:300px;">
					                     <a href="#" onclick="doExcelDownloadProcess(6);">SAP전표 엑셀다운</a>
					                </div>
								</tr>
								
								
								
							</table>
					 	</form>				
		       
        </div>  
        <!-- //container 끝 -->

        <div id="footer"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncFooter" /></div>
	    

    </div>
    <!-- //전체 레이어 끝 -->
    
 </body>
</html>
