<%--
  Class Name : EgovIncHeader.jsp
  Description : 화면상단 Header(include)
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2011.08.31   JJY       경량환경 버전 생성
 
    author   : 실행환경개발팀 JJY
    since    : 2011.08.31 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="egovframework.com.cmm.LoginVO" %>
<div id="skipNav" class="invisible">
    <dl>
        <dt>콘텐츠 바로가기</dt>
        <dd><a href="#main_container">컨텐츠 바로가기</a></dd>
        <dd><a href="#topnavi">메인메뉴 바로가기</a></dd>
    </dl>
</div>




<div id="project_title"><span class="maintitle">우진공업</span><strong> RPA시스템 </strong>
	<!--  
	<a href="<c:url value='/EgovPageLink.do?link=main/sample_menu/Intro'/>" target="_blank"><img width="20" height="20" src="<c:url value='/images/question.jpg'/>" alt="메뉴구성 설명" title="메뉴구성 설명"></a>
	-->
</div>


<div class="header_login">
    <%
       LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO"); 
       if(loginVO == null){ 
    %>
		    <div id="header_loginname">
		        <a href="#" ></a>
		    </div>
		    
		    <div class="header_loginconnection"></div>
			    <ul class="login_bg_area">
			        <li class="righttop_bgleft">&nbsp;</li>
			        <li class="righttop_bgmiddle"><a href="<c:url value='/uat/uia/egovLoginUsr.do'/>">로그인</a></li>
			        <li class="righttop_bgright">&nbsp;</li>
			    </ul>
    <% }else { %>
    
    		<c:set var="loginName" value="<%= loginVO.getName()%>"/>
		    <div id="header_loginname">
		        <c:out value="${loginName}"/> 님
		    </div>
		    <!-- 
		    	<div class="header_loginconnection"> 관리자로 로그인하셨습니다.</div>
		     -->
		    <ul class="login_bg_area">
		        <li class="righttop_bgleft">&nbsp;</li>
		        <li class="righttop_bgmiddle"><a href="<c:url value='/uat/uia/actionLogout.do'/>">로그아웃</a></li>
		        <li class="righttop_bgright">&nbsp;</li>
		    </ul>
    <% } %>    
</div>