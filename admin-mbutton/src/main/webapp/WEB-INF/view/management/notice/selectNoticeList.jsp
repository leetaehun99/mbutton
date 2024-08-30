<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
	$(function() {
		//사용자 등록
		$("#createNotice").click(function() { 
			$("#noticeForm").attr("action", "/notice/createNoticeForm.doo");
			$("#noticeForm").submit();
		});
		
		//검색
		$("#searchList").click(function(){
			setAction();	
			$("#currentPage").val(1);
			$("#noticeForm").submit();
		});
		
		//초기화
		$("#initSearchValue").click(function(){
			initSearchValue();			
		});
	});
	
	readNotice = function(noticeSeq){
		$("#noticeForm").attr("action", "/notice/selectNotice.doo");
		$("#noticeSeq","#noticeForm").val(noticeSeq);
		$("#noticeForm").submit();
		
		$("#noticeListTbody tr").each(function(){
			if(noticeSeq == $(this).find("td:eq(0)").text()) $(this).css("background-color","#e5edfe");
			else  $(this).css("background-color","");
		});
	};
	
	function hitEnterKey(e){
		  if(e.keyCode == 13){
			  $("#searchList").click();
		  }
	}
	
	//검색필드 초기화
	initSearchValue = function(){
		$("#searchText").val("");
		$("#sortSeq").val("");
		$("#sSortOrder").val("UPDATE_DTHMS");
		$('input:radio[name=sSortType]:input[value=DESC]').prop("checked", true);
	};
	
	setAction = function(){
		if($("#cd").val() == "01"){
			$("#noticeForm").attr("action", "/screenGuide/selectScreeningFAQList.doo");
		}else if($("#cd").val() == "02"){
			$("#noticeForm").attr("action", "/faq/selectProgramFAQList.doo");
		}else if($("#cd").val() == "03"){
			$("#noticeForm").attr("action", "/notice/selectNoticeList.doo");
		}else{
			$("#noticeForm").attr("action", "/screenGuide/selectScreeningFAQList.doo");
		}
	};
	
	//다운로드
	download = function(fileSeq){
		$("#fileSeq").val(fileSeq);
		$("#noticeForm").attr("action", "/notice/download.doo");
		$("#noticeForm").submit();
	};
	
</script>


<form id="noticeForm" name="noticeForm" action="/notice/selectNoticeList.doo"method="post">
	<input type="hidden" id="cd" name="cd" value="${noticeVo.cd}"/>
	<input type="hidden" id="noticeSeq"  name="noticeSeq" value="0"/>
	<input type="hidden" id="fileSeq"  name="fileSeq" value=""/>
	<div>
		<table class="table1" >
		<caption><img src="/resources/img/search.gif"></caption>
		<col width="15%" />
		<col width="25%" />
		<col width="15%" />
		<col width="30%" />
		<col width="15%" />
		<tbody>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" id="searchText" name="searchText" value="${noticeVo.searchText }" onKeypress="hitEnterKey(event);return;"/>
				</td>
				<th>정렬</th>
				<td>
					<ct:code name="sSortOrder" type="select" groupCode="00011"  selectCode="${noticeVo.sSortOrder }" />
					<ct:code name="sSortType" type="radio" groupCode="00014"  selectCode="${noticeVo.sSortType }" />
				</td>
				<td class="center" rowspan="2">
					<span class="button medium icon"><span class="check"></span><a id="searchList">조회</a></span>
					<span class="button medium icon"><span class="check"></span><a id="initSearchValue">초기화</a></span> 
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	
	
	<table class="table9">
		<caption><img src="/resources/img/notice2.gif"><span class="divAr"><ct:code name="rowPerPage" type="select" groupCode="00009"  selectCode="${noticeVo.rowPerPage}" eventNm="onchange" eventFn="javascript:commonRowPerChange(document.noticeForm,'/notice/selectNoticeList.doo');"/></span></caption>
		<colgroup>
			<col width="50px;">
			<col width="*">
			<col width="150px;">
			<col width="150px;">
			<col width="100px;">
		</colgroup>
		<thead>
			<tr>
				<th>No</th>
				<th>제목</th>
				<th>수정자</th>
				<th>수정일</th>
				<th>파일</th>	
			</tr>
		</thead>
		<tbody id="noticeListTbody">
			<c:if test="${noticeList != null}">
				<c:forEach var="notice" items="${noticeList}">
					<tr >
						<td>${notice.noticeSeq}</td>
						<td style="text-align:left;padding-left:10px;" class="trFocus" onclick="javascript:readNotice('${notice.noticeSeq}');"><span class="dot300">${notice.subject}</span></td>
						<td>${notice.userNm}</td>
						<td>${notice.updateDthms}</td>
						<td><c:if test="${notice.fileYn != null }"><a href="javascript:download('${notice.fileYn}');" ><img src="/resources/img/file.gif" /></a></c:if></td>						
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(noticeList) == 0}">
				<tr>
					<td colspan="6" align="center">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
		<tfoot>
			<tr><td colspan="6"><span class="button medium icon"><span class="check"></span><a id="createNotice">등록</a></span></td></tr>
		</tfoot>
	</table>
	<!-- 페이지번호 -->
	<div class="paging">${noticeVo.pagingHtml}</div>
</form>	
