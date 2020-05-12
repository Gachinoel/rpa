<%--
  Class Name : EgovFileList.jsp
  Description : 파일목록화면
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2009.03.12   이삼섭          최초 생성
     2011.08.31  JJY       경량환경 버전 생성
 
    author   : 공통서비스 개발팀 이삼섭
    since    : 2009.03.12
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>엑셀 다운로드</title>

</head>
<body>

<%

response.setContentType("application/vnd.ms-excel");

response.setHeader("Content-Disposition", "inline; filename=excelDown.xls");

%>

<table border="1">

<thead>

<tr>

<th>번호</th>

<th>제목</th>

<th>작성자</th>

</tr>

</thead>

<tbody>

<tr>

<td>1</td>

<td>테스트</td>

<td>홍길동</td>

</tr>

</tbody>

</table>

</body>

</html>
