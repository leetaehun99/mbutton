<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
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
		<script type="text/javascript" src="/resources/js/common.js?v=1.0012"></script>
		<!-- //script 영역 -->
		<style type="text/css">html { overflow:hidden; }</style>
	</head> 
	<body oncontextmenu='return false' >
		<div id="container">
			<div id="contents">
				<tiles:insertAttribute name="contents" />
			</div>
			<!-- //contents 영역 -->
		</div>
	</body>
</html>
