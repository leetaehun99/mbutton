<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<title>${title}</title>
		<!-- css영역 -->
		<!-- -->
		<link type="text/css" href="/resources/css/table.css?v=1.0010"  rel="stylesheet" />
		<link type="text/css" href="/resources/css/button.css?v=1.0010" rel="stylesheet" />
		<link type="text/css" href="/resources/css/common.css?v=1.0010" rel="stylesheet" />
		<link type="text/css" href="/resources/css/main.css?v=1.0010" rel="stylesheet" />
		<link type="text/css" href="/resources/css/jquery-ui.css?v=1.0010" rel="stylesheet" />
		<!-- //css영역 -->
		
		<!-- script 영역 -->
		<script type="text/javascript" src="/resources/js/jquery-1.7.1.js?v=1.0010"></script>
		<script type="text/javascript" src="/resources/js/jquery-ui.js?v=1.0010"></script>
		<script type="text/javascript" src="/resources/js/jquery.form.js?v=1.0010"></script>
		<script type="text/javascript" src="/resources/js/jquery.blockUI.js?v=1.0010"></script>
		<script type="text/javascript" src="/resources/js/common.js?v=1.0010"></script>
		<!-- //script 영역 -->
		<script>
		if("${USER}"==""){
			if(opener!=null) {
				opener.location.href="/index/main.doo";
				self.close();
			}
		}
		</script>
	</head> 
	<body><!--  oncontextmenu='return false'  -->
		
			<!-- header 영역 -->
			<div id="">
				<tiles:insertAttribute name="header" />
			</div>
			
			<div id="container">
			<c:if test="${navigation != null && navigation ne ''}" >
				<div class="navigation">
					<div id="navigation">${navigation}</div>
				</div>
			</c:if>
			<!-- //header 영역 -->
			<!-- contents 영역 -->
			<div id="contents">
				<tiles:insertAttribute name="contents" />
			</div>
			</div>
			<!-- //contents 영역 -->
			<!-- footer 영역 -->
			<div id="">
				<tiles:insertAttribute name="footer" />
			</div>
			<!-- //footer 영역 -->
	</body>
</html>
