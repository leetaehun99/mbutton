<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript" src="/resources/seditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		$("#createFaq").click(function(){ //게시판 등록 
			oEditors2.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
				$("#faqForm").attr("action", "/faq/createFaq.doo");
				$("#faqForm").submit();
		});

		$("#selectFaq").click(function(){ //게시판 목록 이동
			$("#faqForm").attr("action", "/faq/selectFaqList.doo");
			$("#faqForm").submit();
		});
		
	});
</script>


<form id="faqForm" name="faqForm" action="/faq/selectFaqList.doo" method="post" enctype="multipart/form-data">
	<input type="hidden" name="cd" value="${noticeVo.cd}"/>
	<input type="hidden" name="delYn" value="N"/>
	<table class="table9">
		<caption><img src="/resources/img/reg.gif"></caption>
		<colgroup>
			<col width="20%">
			<col width="*">
		</colgroup>
		<tbody>
			<tr>
				<th>제목</th>
				<td class="l"><input type="text" id="subject" name="subject" value="" size="100"  maxlength="45"/></td>
			</tr>
			<tr>
			<th>내용</th>
				<td class="l">
				 <textarea id="content" name="contents" style="height:200px;display:none;">${notice.contents}</textarea>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td class="l">
				<input type="file" id="fileName" name="fileName" style="width:300px;height:20px;"/>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">
				<span class="button medium icon"><span class="check"></span><a id="createFaq">등록</a></span>
				<span class="button medium icon"><span class="check"></span><a id="selectFaq">목록</a></span>
				</td>
			</tr>
		</tfoot>
	</table>
<script type="text/javascript">
var oEditors2 = [];

nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors2,
    elPlaceHolder: "content",
    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
    fCreator: "createSEditor23"
});
</script>	
</form>	
