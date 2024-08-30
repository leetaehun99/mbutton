<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
var json=eval('(' + '${statement.jsonObject}' + ')');
var statementJs = "statementJsTest";

// (Y:등록모드,N:수정모드)
	$(document).ready(function() {
	
		//EDI초기화명 이동
		$("#index").click(function() {
			$("#statementForm").attr("action", "/statementTest/index.doo");
			$("#statementForm").attr("target", "_self");
			$("#statementForm").submit();
		});

		//상병 등록,수정
		$("#updateDiseaseStatement").click(function() {
			$("#diseaDivdCd",  "#diseaseStatementForm").val( $("#diseaDivdCd",  "#diseaseStatementForm").val().trim() );
			if(!blankCheck("#diseaDivdCd",  "#diseaseStatementForm", "구분은 필수 입력사항입니다.")) { return false; }
			if(!blankCheck("#diseaDivdSym",  "#diseaseStatementForm", "기호는 필수 입력사항입니다.")) { return false; }
			
			$("#diseaseStatementForm").attr("action", "/statementTest/updateDiseaseStatement.json").ajaxSubmit(updateDiseaseStatementAjax);					
		});
		
		//진료 등록,수정
		$("#updateTreatmentStatement").click(function() {
			$("#cd",  "#treatmentStatementForm").val( $("#cd",  "#treatmentStatementForm").val().trim() );
			if(!blankCheck("#cd",  "#treatmentStatementForm", "코드는 필수 입력사항입니다.")) { return false; }
			if(!blankCheck("#dorseDayCntL",  "#treatmentStatementForm", "일투는 필수 입력사항입니다.")) { return false; }
			if(!blankCheck("#dorseDayCntR",  "#treatmentStatementForm", "일투는 필수 입력사항입니다.")) { return false; }
			if(!blankCheck("#dorseTotCnt",  "#treatmentStatementForm", "총투는 필수 입력사항입니다.")) { return false; }
			if(!blankCheck("#dorseOnceAmtL",  "#treatmentStatementForm", "1회투약량은 필수 입력사항입니다.")) { return false; }
			if(!blankCheck("#dorseOnceAmtR",  "#treatmentStatementForm", "1회투약량은 필수 입력사항입니다.")) { return false; }
			
			if($("#dorseDayCntL","#treatmentStatementForm").val().length !=5 ){ alert("5자리를 입력하여 주십시오 ex)00001"); $("#dorseDayCntL","#treatmentStatementForm").focus(); return false; }
			if($("#dorseDayCntR","#treatmentStatementForm").val().length !=2 ){ alert("2자리를 입력하여 주십시오 ex)00"); $("#dorseDayCntR","#treatmentStatementForm").focus(); return false; }
			if($("#dorseTotCnt","#treatmentStatementForm").val().length !=3 ){ alert("3자리를 입력하여 주십시오 ex)001"); $("#dorseTotCnt","#treatmentStatementForm").focus(); return false; }
			if($("#dorseOnceAmtL","#treatmentStatementForm").val().length !=5 ){ alert("5자리를 입력하여 주십시오 ex)00001"); $("#dorseOnceAmtL","#treatmentStatementForm").focus(); return false; }
			if($("#dorseOnceAmtR","#treatmentStatementForm").val().length !=4 ){ alert("4자리를 입력하여 주십시오 ex)0000"); $("#dorseOnceAmtR","#treatmentStatementForm").focus(); return false; }
			
			$("#dorseDayCnt","#treatmentStatementForm").val($("#dorseDayCntL","#treatmentStatementForm").val()+$("#dorseDayCntR","#treatmentStatementForm").val());	
			$("#dorseOnceAmt","#treatmentStatementForm").val($("#dorseOnceAmtL","#treatmentStatementForm").val()+$("#dorseOnceAmtR","#treatmentStatementForm").val());
			
			$("#treatmentStatementForm").attr("action", "/statementTest/updateTreatmentStatement.json").ajaxSubmit(updateTreatmentStatementAjax);					
		});
		
		//상병 신규 모드
		$("#newDiseaseStatement").click(function() {
			$("#modeType","#diseaseStatementForm").val("Y");	
			$("#diseaStsSeq","#diseaseStatementForm").val("");
			$("#diseaDivdCd","#diseaseStatementForm").val("2");
			$("#diseaDivdSym","#diseaseStatementForm").val("");
			$("#diseaDivdSym","#diseaseStatementForm").focus();
		});
		
		//진료 신규 모드 
		$("#newTreatmentStatement").click(function() {
			$("#modeType","#treatmentStatementForm").val("Y");	
			$("#cd","#treatmentStatementForm").val("");
			
			$("#dorseDayCntL","#treatmentStatementForm").val("00001");
			$("#dorseDayCntR","#treatmentStatementForm").val("00");
			$("#dorseDayCnt","#treatmentStatementForm").val("");
			$("#dorseTotCnt","#treatmentStatementForm").val("001");
			$("#dorseOnceAmtL","#treatmentStatementForm").val("00001");
			$("#dorseOnceAmtR","#treatmentStatementForm").val("0000");
			$("#dorseOnceAmt","#treatmentStatementForm").val("");
			$("#trtStsSeq","#treatmentStatementForm").val("");
			$("#s3","#treatmentStatementForm").text("");
			$("#untPrc","#treatmentStatementForm").text("");
			$("#s2").html("<select id='sectNum2' name='sectNum2'><option value=''>선택해주세요.</option></select>");
			//$("#sectNum1","#treatmentStatementForm").val("04");
			$("#sectNum2","#treatmentStatementForm").val("01");
			//$("#cdDivd","#treatmentStatementForm").val("3");
			$("#cd","#treatmentStatementForm").focus();
			
			
		});
		
		
		//사전 팝업
		$("#dictionary1").click(function() {
			window.open('','dictionary','width=1050, height=600', true);
			$("#searchDictionary","#dictionaryForm").val('1');
			$("#dictionaryForm").attr("action", "/dictionary/index.doo");
			$("#dictionaryForm").attr("target", "dictionary");
			$("#dictionaryForm").submit();
		});
		
		//사전 팝업
		$("#dictionary2").click(function() {
			window.open('','dictionary','width=1050, height=600', true);
			$("#searchDictionary","#dictionaryForm").val('2');
			$("#dictionaryForm").attr("action", "/dictionary/index.doo");
			$("#dictionaryForm").attr("target", "dictionary");
			$("#dictionaryForm").submit();
		});
		
		//일반 특정내역조회
		$("#changeGenerSpecificDetail").click(function() {
			window.open('','changeGenerSpecificDetail','width=1200, height=600', true);
			$("#prscrptnDivd","#statementForm").val("1");
			$("#statementForm").attr("action", "/statementTest/selectSpecificDetailList.doo");
			$("#statementForm").attr("target", "changeGenerSpecificDetail");
			$("#statementForm").submit();
		});
		
		//일반 특정내역조회
		$("#changeSpeDetail").click(function() {
			window.open('','changeGenerSpecificDetail','width=1200, height=600', true);
			$("#prscrptnDivd","#statementForm").val("2");
			$("#stsSrlNum","#statementForm").val();
			$("#statementForm").attr("action", "/statementTest/selectSpecificDetailList.doo");
			$("#statementForm").attr("target", "changeGenerSpecificDetail");
			$("#statementForm").submit();
		});
		
		//진료내역수정
		$("#changeTreatmentStatement").click(function() {
			window.open('','changeTreatmentStatement','width=1200, height=600', true);
			$("#statementForm").attr("action", "/statementTest/selectTreatmentStatementList.doo");
			$("#statementForm").attr("target", "changeTreatmentStatement");
			$("#statementForm").submit();
		});
	});	
	
	selectGrpCode = function(grpCd,selectCd){
		sectNum1="";
		if(grpCd != ""){
			if(grpCd=="01") sectNum1 = "00028";
			else if(grpCd=="02") sectNum1 = "00029";				
			else if(grpCd=="03") sectNum1 = "00030";
			else if(grpCd=="04") sectNum1 = "00031";
			else if(grpCd=="05") sectNum1 = "00032";
			else if(grpCd=="06") sectNum1 = "00033";
			else if(grpCd=="07") sectNum1 = "00043";
			else if(grpCd=="08") sectNum1 = "00034";
			else if(grpCd=="09") sectNum1 = "00035";
			else if(grpCd=="10") sectNum1 = "00036";
			else if(grpCd=="L") sectNum1 = "00037";
			else if(grpCd=="S") sectNum1 = "00038";
			else if(grpCd=="T") sectNum1 = "00039";
			else if(grpCd=="U") sectNum1 = "00040";
			else if(grpCd=="V") sectNum1 = "00041";
			else if(grpCd=="W") sectNum1 = "00042";
			else {
				alert("항이 없습니다.");
				return;
			}
			//selectSectNum2(sectNum1,selectCd);
		}else{
			sectNum1="";
		}
	};
	
	/*
	selectSectNum2 = function(sectNum1,selectCd){
		html = "<select id='sectNum2' name='sectNum2'>";
		html += "<option value=''>선택해주세요.</option>";
		
		for(var i=0; i<json.sectNum2.length; i++){
			if(sectNum1 == json.sectNum2[i].grpCd){
				if(selectCd == json.sectNum2[i].cd){
					html +="<option value='"+json.sectNum2[i].cd+"' selected='selected' >"+json.sectNum2[i].cdNm +"</option>";
				}else{
					html +="<option value='"+json.sectNum2[i].cd+"'>"+json.sectNum2[i].cdNm +"</option>";
				}
			}
		}
		html +="</select>";
		$("#s2").html(html);
	};
	*/
	
	hitEnterKey = function(e){
		  if(e.keyCode == 13){
			  $("#searchMedicalSts").click();
		  }
	};
	
	//기본값 세팅
	basicVlaueSetting = function(stsSrlNum){
		//명세
		$("#stsSrlNum","#statementForm").val(stsSrlNum);
		$("#stsSrlNum","#diseaseStatementForm").val(stsSrlNum);
		$("#stsSrlNum","#treatmentStatementForm").val(stsSrlNum);
	}
	
	//진료 조회
	readTreatmentStatement = function(trtStsSeq,sectNum1,sectNum2,cdDivd,untPrc){
		$("#trtStsSeq","#treatmentStatementForm").val(trtStsSeq);
		$("#modeType","#treatmentStatementForm").val("N");//수정모드

		if(cdDivd == "1") $("#s3").text("수가");
		else if(cdDivd == "3") $("#s3").text("보험등재약");
		else if(cdDivd == "8") $("#s3").text("치료재료");
		$("#sectNum1","#treatmentStatementForm").val(sectNum1);
		$("#cdDivd","#treatmentStatementForm").val(cdDivd);
		$("#untPrc","#treatmentStatementForm").val(untPrc);
		
		$("#treatmentStatementForm").attr("action", "/statementTest/selectTreatmentStatement.json").ajaxSubmit(readTreatmentStatementAjax);			
	};
	
	//진료 삭제
	deleteTreatmentStatement = function(trtStsSeq,lnNum,stsSrlNum){
		$("#trtStsSeq","#treatmentStatementForm").val(trtStsSeq);
		$("#lnNum","#treatmentStatementForm").val(lnNum);
		$("#stsSrlNum","#treatmentStatementForm").val(stsSrlNum);
		$("#treatmentStatementForm").attr("action", "/statementTest/deleteTreatmentStatement.json").ajaxSubmit(deleteTreatmentStatementAjax);		
	};
	
	//진료처방 삭제
	deletePrescriptnStatement = function(prscrptnStsSeq,stsSrlNum){
		$("#prscrptnStsSeq","#prescriptnStatementForm").val(prscrptnStsSeq);
		$("#stsSrlNum","#prescriptnStatementForm").val(stsSrlNum);
		$("#prescriptnStatementForm").attr("action", "/statementTest/deletePrescriptnStatement.json").ajaxSubmit(deletePrescriptnStatementAjax);		
	};
	
	// 진료 조회Ajax
	var readTreatmentStatementAjax = {
		beforeSend : function(){
			$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
		},
		success : function(resultData) {
			if(resultData != null){
				$("#trtStsSeq","#treatmentStatementForm").val(resultData.trtStsSeq);
				$("#cd","#treatmentStatementForm").val(resultData.cd);
				$("#dorseDayCntL","#treatmentStatementForm").val(resultData.dorseDayCnt.substring(0,5));
				$("#dorseDayCntR","#treatmentStatementForm").val(resultData.dorseDayCnt.substring(5,7));
				$("#dorseTotCnt","#treatmentStatementForm").val(resultData.dorseTotCnt);
				$("#dorseOnceAmtL","#treatmentStatementForm").val(resultData.dorseOnceAmt.substring(0,5));
				$("#dorseOnceAmtR","#treatmentStatementForm").val(resultData.dorseOnceAmt.substring(5,9));

				selectGrpCode(resultData.sectNum1,resultData.sectNum2);
				
			}else {
				alert("조회에 실패하였습니다.");
			}
			$.unblockUI();
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
			$.unblockUI();
		}
	};
	
	// 진료 수정
	var updateTreatmentStatementAjax = {
		beforeSend : function(){
			//$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
		},
		success : function(resultData) {
			if(resultData != null){
				alert("등록되었습니다.");
				selectStatement(testStsSrlNum,"N");
				//displayTreatmentStatement(resultData);
				//$("#newTreatmentStatement").click();
			}else {
				alert("등록에 실패 하였습니다..");
				$.unblockUI();
			}
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
			//$.unblockUI();
			
		}
	};
	
	// 진료 삭제Ajax
	var deleteTreatmentStatementAjax = {
		beforeSend : function(){
			$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
		},
		success : function(resultData) {
			if(resultData != null){
				alert("삭제되었습니다.");
				selectStatement(testStsSrlNum,"N");
				//displayTreatmentStatement(resultData);
				$("#newTreatmentStatement").click();
			}else {
				alert("삭제에 실패하였습니다.");
				$.unblockUI();
			}
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
			$.unblockUI();
			
		}
	};
	
	// 진료처방 삭제Ajax
	var deletePrescriptnStatementAjax = {
		beforeSend : function(){
			$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
		},
		success : function(resultData) {
			if(resultData.stsSrlNum != null){
				alert("삭제되었습니다.");
				selectStatement(testStsSrlNum,"N");
				$("#newTreatmentStatement").click();
			}else {
				alert("삭제에 실패하였습니다.");
				$.unblockUI();
			}
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
			$.unblockUI();
			
		}
	};
	
	
	//상병 조회
	readDiseaseStatement = function(diseaStsSeq){
		$("#diseaStsSeq").val(diseaStsSeq);
		$("#modeType","#diseaseStatementForm").val("N");//수정모드
		$("#diseaseStatementForm").attr("action", "/statementTest/selectDiseaseStatement.json").ajaxSubmit(readDiseaseStatementAjax);		
	};
	
	//상병 삭제
	deleteDiseaseStatement = function(diseaStsSeq){
		$("#diseaStsSeq").val(diseaStsSeq);
		$("#diseaseStatementForm").attr("action", "/statementTest/deleteDiseaseStatement.json").ajaxSubmit(deleteDiseaseStatementAjax);		
	};
	
	// 상병 조회Ajax
	var readDiseaseStatementAjax = {
		beforeSend : function(){
			$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
		},
		success : function(resultData) {
			if(resultData != null){
				$("#diseaDivdCd").val(resultData.diseaDivdCd);
				$("#diseaDivdSym").val(resultData.diseaDivdSym);
			}else {
				alert("조회에 실패하였습니다.");
			}
			$.unblockUI();
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
			$.unblockUI();
			
		}
	};
	
	// 상병 수정
	var updateDiseaseStatementAjax = {
		beforeSend : function(){
			$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
		},
		success : function(resultData) {
			if(resultData != null){
				alert("등록되었습니다.");
				selectStatement(testStsSrlNum,"N");
				//displayDiseaseStatement(resultData);
				$("#newDiseaseStatement").click();
			}else {
				alert("등록에 실패 하였습니다..");
			}
			$.unblockUI();
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
			$.unblockUI();
			
		}
	};
	
	// 상병 삭제Ajax
	var deleteDiseaseStatementAjax = {
		beforeSend : function(){
			$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
		},
		success : function(resultData) {
			if(resultData != null){
				alert("삭제되었습니다.");
				selectStatement(testStsSrlNum,"N");
				//displayDiseaseStatement(resultData);
				$("#newDiseaseStatement").click();
			}else {
				alert("삭제에 실패하였습니다.");
			}
			$.unblockUI();
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다.");
			$.unblockUI();
			
		}
	};
	
	diseaseStatementValueSetting = function(diseaDivdSym,diseaDivdSymNm){
		$("#diseaDivdSym","#diseaseStatementForm").val(diseaDivdSym);
	};
	
	treatmentStatementValueSetting = function(cd,sectNum1,sectNum2,cdDivd,untPrc){
		$("#cd","#treatmentStatementForm").val(cd);

		if(cdDivd == "1") $("#s3").text("수가");
		else if(cdDivd == "3") $("#s3").text("보험등재약");
		else if(cdDivd == "8") $("#s3").text("치료재료");
		
		selectGrpCode(sectNum1,sectNum2);
		
		$("#sectNum1","#treatmentStatementForm").val(sectNum1);
		$("#sectNum2","#treatmentStatementForm").val(sectNum2);
		$("#cdDivd","#treatmentStatementForm").val(cdDivd);
		
		$("#dorseDayCntL","#treatmentStatementForm").val("00001");
		$("#dorseDayCntR","#treatmentStatementForm").val("00");
		$("#dorseTotCnt","#treatmentStatementForm").val("001");
		$("#dorseOnceAmtL","#treatmentStatementForm").val("00001");
		$("#dorseOnceAmtR","#treatmentStatementForm").val("0000");
		
		$("#untPrc","#treatmentStatementForm").val(untPrc);
		
	};
</script>

