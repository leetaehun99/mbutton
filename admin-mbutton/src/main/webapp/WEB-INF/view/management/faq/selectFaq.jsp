<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript" src="/resources/seditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		$("#updateFaq").click(function(){ //게시물 수정 
			oEditors2.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
			$("#faqForm").attr("action", "/faq/updateFaq.json");
			$("#faqForm").ajaxSubmit(updateFaqAjax);
		
		});
		
		$("#createReFaq").click(function(){ //답글 등록 
			$("#reFaqForm").attr("action", "/faq/createReFaq.json");
			$("#reFaqForm").ajaxSubmit(createReFaqAjax);
		});
		
		
		$("#selectFaq").click(function(){ //게시판 목록 이동
			setAction();
			$("#faqForm").submit();
		});
	});
	
	function hitEnterKey(e){
		  if(e.keyCode == 13){
			  $("#updateFaq").click();
		  }
	} 
	
	// 게시물 수정Ajax
	var updateFaqAjax = {
		success : function(resultData) {
			if( resultData == 1 ) {
				alert("등록되었습니다.");
				$("#selectFaq").click();
				readFaq();
			} else {
				alert("등록에 실패하였습니다.");
			}
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
		}
	};
	
	// 답글 등록Ajax
	var createReFaqAjax = {
		success : function(resultData) {
			if( resultData == 1 ) {
				alert("등록되었습니다.");
				readFaq();
			} else {
				alert("등록에 실패하였습니다.");
			}
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
		}
	};
	
	readFaq = function(){
		$("#faqForm").attr("action", "/faq/selectFaq.doo");
		$("#faqForm").submit();
	};
	
	//다운로드
	download = function(){
		$("#faqForm").attr("action", "/faq/download.doo");
		$("#faqForm").submit();
	};
	
	setAction = function(){
		if($("#cd","#faqForm").val() == "01"){
			$("#faqForm").attr("action", "/faq/selectFaqList.doo");
			$("#reFaqForm").attr("action", "/faq/selectFaqList.doo");
		}else if($("#cd","#faqForm").val() == "02"){
			$("#faqForm").attr("action", "/faq/selectFaqList.doo");
			$("#reFaqForm").attr("action", "/faq/selectFaqList.doo");
		}else if($("#cd","#faqForm").val() == "03"){
			$("#faqForm").attr("action", "/faq/selectFaqList.doo");
			$("#reFaqForm").attr("action", "/faq/selectFaqList.doo");
		}else{
			$("#faqForm").attr("action", "/faq/selectFaqList.doo");
			$("#reFaqForm").attr("action", "/faq/selectFaqList.doo");
		}
	};
</script>

<!-- value setting -->
<c:set var="reNoticeList"    	value="${notice.reNoticeList}" />
<!-- //value setting -->

<form id="faqForm" name="faqForm" action="" method="post" > 
	<!--검색 조건  -->
	<input type="hidden" name="searchText" value="${noticeVo.searchText}"/>
	<input type="hidden" name="sSortOrder" value="${noticeVo.sSortOrder}"/>
	<input type="hidden" name="sSortType" value="${noticeVo.sSortType}"/>
	<input type="hidden" name="currentPage" value="${noticeVo.currentPage}"/>
	<!--//검색 조건  -->
	<input type="hidden" id="cd" name="cd" value="${noticeVo.cd}"/>
	<input type="hidden" name="delYn" value="N"/>
	<input type="hidden" name="noticeSeq" value="${notice.noticeSeq}"/>
	<input type="hidden" name="fileSeq" value="${notice.fileSeq}"/>
	<table class="table9">
		<caption><!--<img src="/resources/img/faqList.gif">--></caption>
		<colgroup>
			<col width="17%">
			<col width="*">
			<col width="17%">
			<col width="17%">
			<col width="17%">
			<col width="17%">
		</colgroup>
		<tbody>
			<tr>
				<th>글번호</th>
				<td class="l">${notice.noticeSeq}</td>
				<th>작성자</th>
				<td class="l">${notice.userNm}</td>
				<th>작성날짜</th>
				<td class="l">${notice.updateDthms}</td>
			</tr>
			<tr>
				<th >제목</th>
				<c:if test="${notice.registerId==USER.userId}">
				<td class="l" colspan="5">
				<input type="text" id="subject" name="subject" value="${notice.subject}" size="100"  maxlength="45" onKeypress="hitEnterKey(event);return;"/>
				</c:if>
				</td>
				<td class="l" colspan="5">
				<c:if test="${notice.registerId!=USER.userId}">
				${notice.subject}
				</c:if>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<c:if test="${notice.registerId==USER.userId}">
				<td class="l" colspan="5"> 
				<textarea id="content" name="contents" style="height:200px;display:none;">
				${notice.contents}
				</textarea>				
				</c:if>
				</td>
				<td class="l" colspan="5"> 
				<c:if test="${notice.registerId!=USER.userId}">
				${notice.contents}
				</c:if>
				</td>
			</tr>
			<tr>
				<th>파일</th>
				<c:if test="${notice.registerId==USER.userId}">
				<td class="l" colspan="5">
				<input type="file" id="fileName" name="fileName" value="" style="width:300px;height:20px;"/>
				<a href="javascript:download();" >${notice.orgFileName}</a>				
				</c:if>
				</td>
				<td class="l" colspan="5">
				<c:if test="${notice.registerId!=USER.userId}">
				<a href="javascript:download();" >${notice.orgFileName}</a>
				</c:if>
				</td>
			</tr>	
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6">
				<c:if test="${notice.registerId==USER.userId}">
					<span class="button medium icon"><span class="check"></span><a id="updateFaq">등록</a></span>
				</c:if>
				<span class="button medium icon"><span class="check"></span><a id="selectFaq">목록</a></span>
				</td>
			</tr>
</tfoot>
		<script type="text/javascript">
var oEditors2 = [];

nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors2,
    elPlaceHolder: "content",
    sSkinURI: "/resources/seditor/SmartEditor2Skin.html",
    fCreator: "createSEditor23"
});
</script>
	</table>
</form>	