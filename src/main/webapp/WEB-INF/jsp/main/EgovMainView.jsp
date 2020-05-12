<%--
  Class Name : EgovMainView.jsp 
  Description : 메인화면
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2011.08.31   JJY       경량환경 버전 생성
 
    author   : 실행환경개발팀 JJY
    since    : 2011.08.31 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Language" content="ko" >
<title>우진공업</title>
<link href="<c:url value='/'/>css/common.css" rel="stylesheet" type="text/css" >
</head>

<body>

<!-- 전체 레이어 시작 -->
<div id="wrap">
	<!-- header 시작 -->
		<div id="header_mainsize">
		    <c:import url="/EgovPageLink.do?link=main/inc/EgovIncHeader" />
		</div>
		
		<div id="topnavi">
		    <c:import url="/EgovPageLink.do?link=main/inc/EgovIncTopnav" />
		</div>
	<!-- //header 끝 -->
	
	<!-- container 시작 -->
	<div id="main_container">
		<!-- 좌측메뉴 시작-->
		<div id="leftmenu">
			<c:import url="/EgovPageLink.do?link=main/inc/EgovIncLeftmenu" />
		</div>
	</div>
	<!-- container 끝 -->
	
	
</div>
<!-- //전체 레이어 끝 -->

</body>
</html>

