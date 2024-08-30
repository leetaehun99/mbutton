<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
	$(function() {
		$("#selectFaq").click(function(){ //게시판 목록 이동
			$("#faqForm").attr("action", "/faq/selectFaqList.doo");
			$("#faqForm").submit();
		});
	});
	//다운로드
	download = function(){
		$("#faqForm").attr("action", "/faq/download.doo");
		$("#faqForm").submit();
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
				<td class="l" colspan="5">
				${notice.subject}
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td class="l" colspan="5"> ${notice.contents}</td>
			</tr>
			<tr>
				<th>파일</th>
				<td class="l" colspan="5">
				<a href="javascript:download();" >${notice.orgFileName}</a>
				</td>
			</tr>	
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6">
				<span class="button medium icon"><span class="check"></span><a id="selectFaq">목록</a></span>
				</td>
			</tr>
		</tfoot>
	</table>
</form>	