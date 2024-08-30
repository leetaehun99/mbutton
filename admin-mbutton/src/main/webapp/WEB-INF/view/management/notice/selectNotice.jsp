<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript" src="/resources/seditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		$("#updateNotice").click(function(){ //게시물 수정 
			oEditors2.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
			$("#noticeForm").attr("action", "/notice/updateNotice.json");
			$("#noticeForm").ajaxSubmit(updateNoticeAjax);
		
		});
		
		$("#createReNotice").click(function(){ //답글 등록 
			$("#reNoticeForm").attr("action", "/notice/createReNotice.json");
			$("#reNoticeForm").ajaxSubmit(createReNoticeAjax);
		});
		
		$("#selectNotice").click(function(){ //게시판 목록 이동
			setAction();
			$("#noticeForm").submit();
		});
	});
	
	function hitEnterKey(e){
		  if(e.keyCode == 13){
			  $("#updateNotice").click();
		  }
	} 
	
	// 게시물 수정Ajax
	var updateNoticeAjax = {
		success : function(resultData) {
			if( resultData == 1 ) {
				alert("등록되었습니다.");
				$("#selectNotice").click();
			//	//readNotice();
			//alert(resultData);
			$("#contents","#noticeForm").val(resultData);
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
	var createReNoticeAjax = {
		success : function(resultData) {
			if( resultData == 1 ) {
				alert("등록되었습니다.");
				readNotice();
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
	
	readNotice = function(){
		$("#noticeForm").attr("action", "/notice/selectNotice.doo");
		$("#noticeForm").submit();
	};
	
	//다운로드
	download = function(){
		$("#noticeForm").attr("action", "/notice/download.doo");
		$("#noticeForm").submit();
	};
	
	setAction = function(){
		if($("#cd","#noticeForm").val() == "01"){
			$("#noticeForm").attr("action", "/notice/selectScreeningFAQList.doo");
			$("#reNoticeForm").attr("action", "/notice/selectScreeningFAQList.doo");
		}else if($("#cd","#noticeForm").val() == "02"){
			$("#noticeForm").attr("action", "/notice/selectProgramFAQList.doo");
			$("#reNoticeForm").attr("action", "/notice/selectProgramFAQList.doo");
		}else if($("#cd","#noticeForm").val() == "03"){
			$("#noticeForm").attr("action", "/notice/selectNoticeList.doo");
			$("#reNoticeForm").attr("action", "/notice/selectNoticeList.doo");
		}else{
			$("#noticeForm").attr("action", "/notice/selectScreeningFAQList.doo");
			$("#reNoticeForm").attr("action", "/notice/selectScreeningFAQList.doo");
		}
	};
</script>

<!-- value setting -->
<c:set var="reNoticeList"    	value="${notice.reNoticeList}" />
<!-- //value setting -->

<form id="noticeForm" name="noticeForm" action="" method="post" >
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
		<caption><img src="/resources/img/noticeList.gif"></caption>
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
				<td class="l" colspan="5">
				<c:if test="${USER.userId == notice.registerId}">
				<input type="text" id="subject" name="subject" value="${notice.subject}" size="100"  maxlength="45" onKeypress="hitEnterKey(event);return;"/>
				</c:if>
				<c:if test="${USER.userId != notice.registerId}">
				${notice.subject}
				</c:if>
				</td>
			</tr>			
			<tr>
			    <th>내용</th>
				<td class="l" colspan="5">
				<c:if test="${USER.userId == notice.registerId}">
				  <textarea id="content" name="contents" style="height:200px;display:none;">${notice.contents}</textarea>
				</c:if>
				<c:if test="${USER.userId != notice.registerId}">
				<div>${notice.contents}</div>
				</c:if>
				</td>
			</tr>
			<tr>
				<th>파일</th>
				<td class="l" colspan="5">
					<c:if test="${USER.userId == notice.registerId}">
					<input type="file" id="fileName" name="fileName" value="" style="width:300px;height:20px;"/>
					</c:if>
					<a href="javascript:download();" >${notice.orgFileName}</a>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
			<td colspan="6">
				<c:if test="${USER.userId == notice.registerId}">
					<span class="button medium icon"><span class="check"></span><a id="updateNotice">등록</a></span>
				</c:if>	
				<span class="button medium icon"><span class="check"></span><a id="selectNotice">목록</a></span>
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