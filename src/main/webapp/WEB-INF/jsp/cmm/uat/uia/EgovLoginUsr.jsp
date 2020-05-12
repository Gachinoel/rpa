<%--
  Class Name : EgovLoginUsr.jsp
  Description : Login 인증 화면
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2009.03.03    박지욱          최초 생성
     2011.08.31  JJY       경량환경 버전 생성
 
    author   : 공통서비스 개발팀 박지욱
    since    : 2009.03.03
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<meta http-equiv="content-language" content="ko">
<title>우진공업 RPA - 로그인</title>
<style type="text/css">
body { margin-left: 0px; margin-top: 0px; margin-right: 0px; margin-bottom: 0px; }
#center { position:absolute; top:50%; left:50%; width:300px; height:200px; overflow:hidden; background-color:#FC0; margin-top:-150px; margin-left:-100px;}
</style>

<link href="<c:url value='/'/>css/common.css" rel="stylesheet" type="text/css" >
<link href="<c:url value='/'/>css/login.css" rel="stylesheet" type="text/css" >

<script type="text/javascript">

if (self.name != 'reload') {
    self.name = 'reload';
    self.location.reload(true);
}
else self.name = ''; 

<!--
function actionLogin() {

    if (document.loginForm.id.value =="") {
        alert("아이디를 입력하세요");
        return false;
    } else if (document.loginForm.password.value =="") {
        alert("비밀번호를 입력하세요");
        return false;
    } else {
        document.loginForm.action="<c:url value='/uat/uia/actionLogin.do'/>";
        //document.loginForm.j_username.value = document.loginForm.userSe.value + document.loginForm.username.value;
        //document.loginForm.action="<c:url value='/j_spring_security_check'/>";
        document.loginForm.submit();
    }
}

function setCookie (name, value, expires) {
    document.cookie = name + "=" + escape (value) + "; path=/; expires=" + expires.toGMTString();
}

function getCookie(Name) {
    var search = Name + "="
    if (document.cookie.length > 0) { // 쿠키가 설정되어 있다면
        offset = document.cookie.indexOf(search)
        if (offset != -1) { // 쿠키가 존재하면
            offset += search.length
            // set index of beginning of value
            end = document.cookie.indexOf(";", offset)
            // 쿠키 값의 마지막 위치 인덱스 번호 설정
            if (end == -1)
                end = document.cookie.length
            return unescape(document.cookie.substring(offset, end))
        }
    }
    return "";
}

function saveid(form) {
    var expdate = new Date();
    // 기본적으로 30일동안 기억하게 함. 일수를 조절하려면 * 30에서 숫자를 조절하면 됨
    if (form.checkId.checked)
        expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30); // 30일
    else
        expdate.setTime(expdate.getTime() - 1); // 쿠키 삭제조건
    setCookie("saveid", form.id.value, expdate);
}

//function getid(form) {
//    form.checkId.checked = ((form.id.value = getCookie("saveid")) != "");
//}

function fnInit() {
    var message = document.loginForm.message.value;
    if (message != "") {
        alert(message);
    }
    //getid(document.loginForm);
}
//-->
</script>


</head>
<body  onload="fnInit();">
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>    
<!-- 전체 레이어 시작 -->
<div id="wrap">
    
    <!-- header 시작 -->
    <div id="header_mainsize"></div>
    <div id="topnavi"></div>
    <!-- //header 끝 -->
    
    
    
    
    
    <!-- container 시작 -->
    <div id="container">
        
        
        
        
        
        
                
                
                <!-- 타이틀 이미지 -->            
                <div id="content_img_div"><img  alt="" src="<c:url value='/'/>images/subtitle/img_subtitle_login.gif" width="1" height="150" /></div>       
                    <div class="user_login">
                            
                            <form:form name="loginForm" method="post" action="#LINK">
	                            <div align="center" class="user_login_ultop">
	                                <ul>
	                                    <li>
	                                        <label for="id"><img alt="login" src="<c:url value='/'/>images/login/img_idtext.gif" /></label>
	                                        <input type="text" class="input_style" title="아이디 입력하세요." id="id" name="id" maxlength="10"/>
	                                    </li>
	                                    <li>
	                                        <label for="password"><img alt="password" src="<c:url value='/'/>images/login/img_pwtext.gif" /></label>
	                                        <input type="password" class="input_style" maxlength="25" title="비밀번호를 입력하세요." id="password" name="password" 
	                                               onkeydown="javascript:if (event.keyCode == 13) { actionLogin(); }"/>
	                                    </li>
	                                    <!-- 
	                                    <li>
	                                        <input type="checkbox" name="checkId" onclick="javascript:saveid(this.form);" id="checkId" /><label for="checkId">ID저장</label>
	                                    </li>
	                                     -->
	                                </ul>
	                                <input type="image" alt="로그인 버튼" class="btn_style" onclick="javascript:actionLogin()" src="<c:url value='/'/>images/login/btn_login.gif"  />
	                            </div>
	                            <input type="hidden" name="message" value="${message}" />
					            <input type="hidden" name="userSe"  value="USR"/>
					            <!-- <input type="hidden" name="j_username" />-->
                            </form:form>
                    </div>
            
    </div>  
    <!-- //container 끝 -->
    <!-- footer 시작 -->
    <div id="footer"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncFooter" /></div>
    <!-- //footer 끝 -->
</div>
<!-- //전체 레이어 끝 -->
</body>
</html>
