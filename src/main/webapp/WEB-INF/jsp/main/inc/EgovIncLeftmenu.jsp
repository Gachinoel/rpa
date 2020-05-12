<%--
  Class Name : EgovIncLeftmenu.jsp
  Description :  좌메뉴 화면(include)
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2011.08.31   JJY       경량환경 버전 생성
 
    author   : 실행환경개발팀 JJY
    since    : 2011.08.31 
--%><%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "java.util.HashMap" %>
<%@ page import="egovframework.com.cmm.LoginVO" %>
<%
String menuNo = ((String)session.getAttribute("menuNo")!=null)?(String)session.getAttribute("menuNo"):"11";
LoginVO user = (LoginVO)request.getSession().getAttribute("LoginVO");
String userId = user.getId();

%>

<div id="nav">	
	<div class="nav_style">
	<ul>
	    <% if (menuNo.indexOf("1")== 0) {%>
		<% } %>
		<% if (menuNo.indexOf("2")== 0) {%>
         <% if (userId.equals("admin") || userId.equals("20030019") || userId.equals("20120066") || userId.equals("20170034")) {%>
         <% } %>
		<% } %>
        <% if (menuNo.indexOf("3")== 0) {%>
		<% } %>
        <% if (menuNo.indexOf("4")== 0) {%>
		<% } %>
        <% if (menuNo.indexOf("5")== 0) {%>
		<li class="leftmenu_dept01">
			<div>-RPA</div>
			<ul>
				<!-- 	
				<li class="dept02"><a href="javascript:fn_main_headPageAction('51','cop/smt/sim/EgovIndvdlSchdulManageMonthList.do')">일정관리</a></li>
				<li class="dept02"><a href="javascript:fn_main_headPageAction('52','cop/com/selectTemplateInfs.do')">게시판템플릿관리</a></li>
				<li class="dept02"><a href="javascript:fn_main_headPageAction('53','cop/bbs/SelectBBSMasterInfs.do')">게시판생성관리</a></li>
				<li class="dept02"><a href="javascript:fn_main_headPageAction('54','cop/com/selectBBSUseInfs.do')">게시판사용관리</a></li> 
				<li class="dept02"><a href="javascript:fn_main_headPageAction('55','cop/bbs/selectBoardList.do?bbsId=BBSMSTR_AAAAAAAAAAAA')">공지사항관리</a></li>
                -->
                <li class="dept02"><a href="javascript:fn_main_headPageAction('50','cop/bbs/SelectBBSMasterInfs.do')">수입통관</a></li>
                <% if (userId.equals("admin") || userId.equals("20030019") || userId.equals("20120066") || userId.equals("20170034")) {%>
                <li class="dept02"><a href="javascript:fn_main_headPageAction('51','cop/bbs/SelectBBSSapInfs.do')">SAP전표 확인</a></li>
                <% } %>
                <li class="dept02"><a href="javascript:fn_main_headPageAction('52','cop/bbs/SelectBBSSapDetailInfs.do')">SAP전표 조회</a></li>
                <li class="dept02"><a href="javascript:fn_main_headPageAction('53','cop/bbs/selectExtraSPList.do')">수입부대비용</a></li>
                <li class="dept02"><a href="javascript:fn_main_headPageAction('54','cop/bbs/selectExtraSPFinishList.do')">수입부대비용마감</a></li>
                <li class="dept02"><a href="javascript:fn_main_headPageAction('55','cop/bbs/selectExtraSubList.do')">수입부대비용상세</a></li>
                <li class="dept02"><a href="javascript:fn_main_headPageAction('56','cop/bbs/selectTax.do')">관부가세 마감 내역</a></li>
                <li class="dept02"><a href="javascript:fn_main_headPageAction('57','cop/bbs/SelectBBSTTSendList.do')">TT 송금 내역서</a></li>
                <li class="dept02"><a href="javascript:fn_main_headPageAction('58','cop/bbs/selectYetArrivedList.do')">미착리스트</a></li>
                <li class="dept02"><a href="javascript:fn_main_headPageAction('59','rpa/bill/selectBillList.do')">지급어음리스트</a></li>
                
                
                
                <!--
                
                <li class="dept02"><a href="javascript:fn_main_headPageAction('56','wj/rpa/excel/selectRpaMain.do')">수입통관</a></li>
                -->
                
                
                
			</ul> 
		</li>
		<% } %>
	</ul>
	<!-- </div>  
	<div class="bottom">
	</div>
	-->	
	</div>  
</div>
<!-- //메뉴 끝 -->	
