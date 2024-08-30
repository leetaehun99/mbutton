<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ct" uri="customTag"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#index").click(function(){
			location.href='/statement/index.doo';
		});
	});

	var popObj ;
	medicalStatementList = function(recpCstClmSeq,clmNum,hspId,notify,notifySub){
		popObj = "";
		$("#recpCstClmSeq").val(recpCstClmSeq);
		$("#clmNum").val(clmNum);
		$("#hspId").val(hspId);
		$("#notify").val(notify);
		$("#notifySub").val(notifySub);
		focusItem($("#screenMsgListTBody tr"),notify+'-'+notifySub);
		$("#screenMsgForm").attr("action", "/statement/medicalStatementList.json");
		$("#screenMsgForm").ajaxSubmit(medicalStatementListAjax);
	};
	
	var medicalStatementListAjax = {
			beforeSend : function(){
				$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});				
			},
			success : function(resultData) {
				result = "";
				if(resultData!=null){
					if(resultData.length>0){
						for(var i=0; i<resultData.length; i++){
							result+="<tr id=\""+resultData[i].stsSrlNum+i+"\" class='trFocus' onclick=\'javascript:readStatement(\""+resultData[i].recpCstClmSeq+"\",\""+resultData[i].clmNum+"\",\""+resultData[i].hspId+"\",\""+resultData[i].stsSrlNum+i+"\",\""+resultData[i].stsSrlNum+"\")\'>";
							result+="	<td>"+nullCheck(resultData[i].stsSrlNum)+"</td>";
							result+="	<td>"+nullCheck(resultData[i].rcvrNm)+"</td>";
							result+="	<td>"+nullCheck(resultData[i].birthDy)+"</td>";
							result+="	<td>"+nullCheck(resultData[i].sex)+"</td>";				
							result+="</tr>";
						}
					}else{
						result+="<tr><td colspan='4'>조회된 내역이 없습니다.</td></tr>";
					}
				}else{
					result+="<tr><td colspan='4'>조회된 내역이 없습니다.</td></tr>";	
				}
				$("#rBody").html(result);
				$.unblockUI();
			},
			type : "post",
			dataType : "json",
			error : function() {
				alert("서버와의 통신에 실패 하였습니다.");
				$.unblockUI();
			}
		};
	
		//메뉴 상세 function
		readStatement = function(recpCstClmSeq,clmNum,hspId,idx,stsSrlNum){
			focusItem($("#rBody tr"),idx);
			$("#recpCstClmSeq").val(recpCstClmSeq);
			$("#clmNum").val(clmNum);
			$("#hspId").val(hspId);
			$("#stsSrlNum").val(stsSrlNum);
			if(popObj == ""){
				if(checkBrowser()=="IE")	popObj = window.open('','statement','width=1010,height=730');
				else if(checkBrowser()=="FF")	popObj = window.open('','statement','width=1010,height=730');
				
				popObj=window.open('','statement','width=1110,height=800');
				$("#screenMsgForm").attr("action", "/statement/selectStatement.doo");
				$("#screenMsgForm").attr("target", "statement");
				$("#screenMsgForm").submit();
				$("#screenMsgForm").attr("target", "_self");
			}else{
				try{
					popObj.searchFunction(stsSrlNum);
					popObj.focus();
				}catch(e){
					if(checkBrowser()=="IE")	popObj = window.open('','statement','width=1010,height=730');
					else if(checkBrowser()=="FF")	popObj = window.open('','statement','width=1010,height=730');
					
					popObj=window.open('','statement','width=1110,height=800');
					$("#screenMsgForm").attr("action", "/statement/selectStatement.doo");
					$("#screenMsgForm").attr("target", "statement");
					$("#screenMsgForm").submit();
					$("#screenMsgForm").attr("target", "_self");
				}
			}	
		};
	
		focusItem = function(obj,idx){
			obj.each(function(){
				if ($(this).attr("id") == idx)
					$(this).css("background-color","#e5edfe");
				else {
					$(this).css("background-color","");
				}
			});
		};
</script>
<form id="screenMsgForm" name="screenMsgForm" action="" method="POST">
<input type="hidden" name="recpCstClmSeq" id="recpCstClmSeq" value=""/>
<input type="hidden" name="clmNum" id="clmNum" value=""/>
<input type="hidden" name="hspId" id="hspId" value=""/>
<input type="hidden" name="notify" id="notify" value=""/>
<input type="hidden" name="notifySub" id="notifySub" value=""/>
<input type="hidden" name="stsSrlNum" id="stsSrlNum" value=""/>
<input type="hidden" name="type" id="type" value="SCREEN"/>

<div>
	<div style="float:left;width:698px;height:500px; border:1px solid #CCC;">
		<table class="table7" >
			<caption><img src="/resources/img/checkList.gif"></caption>
			<colgroup>
				<col width="34px;" />
				<col width="69px;" />
				<col width="487px;" />
				<col width="*" />
			</colgroup>
			<thead>
			<tr>
				<th>구분</th>
				<th>고시번호</th>
				<th>메세지</th>
				<th>갯수</th>
			</tr>
			</thead>
		</table>
		<div style="height:435px; overflow-y:scroll; overflow-x:hidden;">
		<table class="table7" >
			<colgroup>
				<col width="34px;" />
				<col width="69px;" />
				<col width="487px;" />
				<col width="*" />
			</colgroup>
			<tbody id="screenMsgListTBody">
			<c:if test="${screenMsgList != null}">
				<c:forEach var="screenMsg" items="${screenMsgList}">
				<tr id="${screenMsg.notify}-${screenMsg.notifySub}" class="trFocus" onclick="javascript:medicalStatementList('${screenMsg.recpCstClmSeq}','${screenMsg.clmNum}','${screenMsg.hspId}','${screenMsg.notify}','${screenMsg.notifySub}');">
					<td>
						<c:choose>
							<c:when test="${screenMsg.msgLev eq 1}">
								필수
							</c:when>
							<c:otherwise>
								선택
							</c:otherwise>
						</c:choose>
					</td>
					<td>${screenMsg.notify} - ${screenMsg.notifySub}</td>
					<td class="l">${screenMsg.msg}</td>
					<td>${screenMsg.cnt}</td>
				</tr>
				</c:forEach>
			</c:if>
			</tbody>
		</table>
		</div>
	</div>
	<div style="float:right;width:298px;height:500px; border:1px solid #CCC;">
		<table class="table7">
			<caption><img src="/resources/img/checkRt.gif"></caption>
			<colgroup>
				<col width="59px;" />
				<col width="89px;" />
				<col width="89px;" />
				<col width="*" />
			</colgroup>
			<thead>
			<tr>
				<th>일련번호</th>
				<th>환자명</th>
				<th>생년월일</th>
				<th>성별</th>
			</tr>
			</thead>
		</table>
		<div style="height:435px; overflow-y:scroll; overflow-x:hidden;">
		<table class="table7" >
			<colgroup>
				<col width="59px;" />
				<col width="89px;" />
				<col width="89px;" />
				<col width="*" />
			</colgroup>
			<tbody id="rBody"></tbody>
		</table>
		</div>
	</div>				
</div>

	<span class="button medium icon"><span class="check"></span><a id="index">목록</a></span>
</form>