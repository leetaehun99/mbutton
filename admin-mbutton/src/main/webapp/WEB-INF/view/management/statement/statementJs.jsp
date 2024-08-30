<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
//var json=eval('(' + '${statement.jsonObject}' + ')');
	$(document).ready(function() {
		//EDI초기화명 이동
		$("#index").click(function() {
			$("#statementForm").attr("action", "/statement/index.doo");
			$("#statementForm").attr("target", "_self");
			$("#statementForm").submit();
		});
		
		$(".tab_content").hide(); 
		$("ul.tabs li:first").addClass("active").show();
		$(".tab_content:first").show(); 
		$("ul.tabs li").click(function() {
			$("ul.tabs li").removeClass("active"); 
			$(this).addClass("active");
			$(".tab_content").hide(); 

			var activeTab = $(this).find("a").attr("href"); 
			$(activeTab).fadeIn(); 
			return false;
		});
		
		$(".tab_content2").hide(); 
		$("ul.tabs2 li:first").addClass("active").show();
		$(".tab_content2:first").show(); 
		$("ul.tabs2 li").click(function() {
			$("ul.tabs2 li").removeClass("active"); 
			$(this).addClass("active");
			$(".tab_content2").hide(); 

			var activeTab = $(this).find("a").attr("href"); 
			$(activeTab).fadeIn(); 
			return false;
		});
	    $("#popWrapDetail").draggable({ containment: "#blockUI2" , handle: ".popImg" });
	   
	    
		$("#searchMedicalSts").click(function(){
		    	
		    	var text = $("#searchText").val();
		    	try{
		    		text = Number($("#searchText").val());
		    	}catch(e){
		    		text = $("#searchText").val();
		    	}
		    	$("#statementTbody tr").each(function(idx){
			    	if($(this).find("td:eq(0)").text() == text ){
						val = $(this).offset();
						div = $("#statementTbody").offset();
						selectStatement($(this).find("td:eq(0)").text(),"Y");
			    		$('#medicalStatementDiv').animate({scrollTop:val.top-div.top},300);
			    	}
			    });
			    
			    $("#statementTbody tr").each(function(idx){
			    	if($(this).find("td:eq(1)").text() == $("#searchText").val() ){
						val = $(this).offset();
						div = $("#statementTbody").offset();
						selectStatement($(this).find("td:eq(0)").text(),"Y");
			    		$('#medicalStatementDiv').animate({scrollTop:val.top-div.top},300);
			    		return false;
			    	}
			    });
		    });
		$("#searchText").val("${statement.stsSrlNum}");
		$("#searchMedicalSts").click();
	});	
	
	selectMsgLev = function () {
		if ($("#msgLevTemp").val() == '') {
			alert("메시지 레벨을 선택해주세요");
			$("#msgLevTemp").focus();
			return;
		}
		alert("추후작업");
	};
	
	// 명세서 조회 Ajax
	var selectMsgLevAjax = {
		beforeSend : function(){
			$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
		},
		success : function(resultData) {
			if( resultData != null ) {
				displayErrorCheck(resultData);//사전점검
				//$("#medicalTab1").click();
				
			} else {
				alert("조회에 실패하였습니다.");
			}
			$.unblockUI();
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다");
			$.unblockUI();
		}
	};
	
	//기본값 세팅
	basicVlaueSetting = function(stsSrlNum){
		var recpCstClmSeq =$("#recpCstClmSeq","#statementForm").val();
		var hspId = $("#hspId","#statementForm").val();
		var clmNum = $("#clmNum","#statementForm").val();
		$("#stsSrlNum","#statementForm").val(stsSrlNum);
	}
	
	//명세서 조회
	selectStatement = function(stsSrlNum){
		$("#statementTbody tr").each(function(){
			if(stsSrlNum == $(this).find("td:eq(0)").text()) $(this).css("background-color","#e5edfe");
			else {
				if ($(this).attr("id") == 'red')
					$(this).css("background-color","#ffcccc");
				else {
					$(this).css("background-color","");
				}
			}
		});
		basicVlaueSetting(stsSrlNum);
		$("#msgLev","#statementForm").val($("#msgLevTemp").val());
		$("#statementForm").attr("action", "/statement/selectStatement.json");
		$("#statementForm").attr("target", "_self");
		$("#statementForm").ajaxSubmit(statementAjax);	
	}
	
	// 명세서 조회 Ajax
	var statementAjax = {
		beforeSend : function(){
			$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
		},
		success : function(resultData) {
			if( resultData != null ) {
				displayMedicalStatement(resultData.medicalStatementVo);//명세서
				displayDiseaseStatement(resultData.diseaseStatementList);//상병
				displayPrescriptnStatement(resultData.prescriptnStatementList);//처방
				displayTreatmentStatement(resultData.treatmentStatementList);//진료
				displaySpecificDetailStatement(resultData.specificDetailList);//특정내역
				displayErrorCheck(resultData.errorCheckList);//사전점검
				if(resultData.trtStartDt!=null ){
					var dt = Number((resultData.trtStartDt).substring(6,8));
					$(".calCss").each(function(){
						if($(this).attr("id")=="cal2_"+dt){
							$(this).css("background-color","#e5edfe");
						}else{
							$(this).css("background-color","");
						}
					}); 
				}
				$("#medicalTab1").click();
				
			} else {
				alert("조회에 실패하였습니다."+resultData.errorMsg);
			}
			$.unblockUI();
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다");
			$.unblockUI();
		}
	};
	
	//명세서 표현
	displayMedicalStatement = function(resultData){
		try{
			$("#medicalStatementTab1 tr:eq(0) td:eq(0)").text(nullCheck(resultData.stsSrlNum));
			$("#medicalStatementTab1 tr:eq(1) td:eq(0)").text(nullCheck(resultData.rcvrNm));
			$("#medicalStatementTab1 tr:eq(1) td:eq(1)").text(nullCheck(getAge(resultData.birthDy)+"("+resultData.sex+")" ));
			$("#medicalStatementTab1 tr:eq(2) td:eq(0)").text(nullCheck(resultData.housrNm));
			$("#medicalStatementTab1 tr:eq(2) td:eq(1)").text("");
			$("#medicalStatementTab1 tr:eq(3) td:eq(0)").text(disPlayComma(resultData.rcvrTotCst));
			$("#medicalStatementTab1 tr:eq(4) td:eq(0)").text(disPlayComma(resultData.cinsrPrt));
			$("#medicalStatementTab1 tr:eq(5) td:eq(0)").text(disPlayComma(resultData.clmCst));
			$("#medicalStatementTab1 tr:eq(6) td:eq(0)").text(disPlayComma(resultData.hadipCst));
			
			$("#medicalStatementTab2 tr:eq(0) td:eq(0)").text('15%');
			$("#medicalStatementTab2 tr:eq(1) td:eq(0)").text(disPlayComma(resultData.recpCst2));
			$("#medicalStatementTab2 tr:eq(2) td:eq(0)").text(disPlayComma(resultData.cinsrPrt));
			$("#medicalStatementTab2 tr:eq(3) td:eq(0)").text(disPlayComma(resultData.drgOverCst));
			$("#medicalStatementTab2 tr:eq(4) td:eq(0)").text(disPlayComma(resultData.clmCst));
			$("#medicalStatementTab2 tr:eq(5) td:eq(0)").text(disPlayComma(resultData.hadipCst));
			$("#medicalStatementTab2 tr:eq(6) td:eq(0)").text(disPlayComma(resultData.subPayCst));
		}catch(e){
			alert(e);
		}
	};
	//상병 표현
	displayDiseaseStatement = function(resultData){
		var result="";
		if(resultData!=null){
			if(resultData.length>0){
				for(var i=0; i<resultData.length; i++){
					result+="<tr id='"+resultData[i].diseaDivdSym+"' class='trId' cd='disease'>";
					result+="	<td>"+(i+1)+"</td>";
					result+="	<td>"+nullCheck(resultData[i].diseaDivdCdNm)+"</td>";
					result+="	<td style='text-align:left; padding-left:10px;'>"+nullCheck(resultData[i].diseaDivdSym)+"("+nullCheck(resultData[i].diseaKorNm)+")"+"</td>";
					result+="	<td>"+nullCheck(resultData[i].trtSubctNm)+"</td>";					
					result+="</tr>";
				}

				$("#trtSubct").val(resultData[0].trtSubct);
				$("#trtDetailSubct").val(resultData[0].trtDetailSubct);
			}else{
				result+="<tr><td colspan='5'>조회된 내역이 없습니다.</td></tr>";
			}
		}else{
			result+="<tr><td colspan='5'>조회된 내역이 없습니다.</td></tr>";	
		}
		
		$("#diseaseStatementBody").html(result);
	};

	//처방 표현
	displayPrescriptnStatement = function(resultData){
		var result="";
		if(resultData!=null){
			if(resultData.length>0){
				for(var i=0; i<resultData.length; i++){
					if(i==0){
						result+="<tr style='background-color:#FAF4C0;'><td colspan='8' style='text-align:left;'>교부번호 : "+nullCheck(resultData[0].prscrptnNum)+"</td><td>투여</td><td>횟수</td><td>일수</td><td></td></tr>";					
					}
					result+="<tr id='"+nullCheck(resultData[i].cd)+"' class='trId' cd='trt'>";
					result+="	<td>"+nullCheck(resultData[i].lnNum)+"</td>";
					result+="	<td></td>";
					result+="	<td></td>";
					result+="	<td></td>";
					result+="	<td>"+nullCheck(resultData[i].cdDivd)+"</td>";//1수가3약가8치료대
					result+="	<td style='cursor:pointer;' ondblclick=\'javascript:cdDetail(\""+resultData[i].cdDivd+"\",\""+resultData[i].cd+"\")\'>"+nullCheck(resultData[i].cd)+"</td>";
					result+="	<td class='l'>"+"["+resultData[i].drugNotify+"]"+nullCheck(resultData[i].drugNm)+"</td>";
					result+="	<td >"+nullCheck(resultData[i].speDetail)+"</td>";
					result+="	<td>"+disPlayFloat(resultData[i].dorseOnce)+"</td>";
					result+="	<td>"+disPlayFloat(resultData[i].dorseOnceCnt)+"</td>";
					result+="	<td>"+disPlayFloat(resultData[i].dorseTotCnt)+"</td>";
					//result+="	<td class='r'>"+disPlayComma(resultData[i].limitCost)+"</td>";
					result+="</tr>";
					
				}
			}
		}
		$("#treatmentStatementBody").html(result);
	};
	
	//진료 표현
	displayTreatmentStatement = function(resultData){
		var result="";
		var nm = "";
		if(resultData!=null){
			if(resultData.length>0){
				for(var i=0; i<resultData.length; i++){
					nm = "";
					cost = "";
					if(resultData[i].cdDivd=="1"){//수가
						nm = resultData[i].korNm;
						cost = resultData[i].cost;
					}else if(resultData[i].cdDivd=="3"){//약가	
						nm = "["+resultData[i].drugNotify+"]"+resultData[i].drugNm;
						if(resultData[i].speDetail == "99") cost = resultData[i].usePay;
						else cost = resultData[i].limitCost;
					}else if(resultData[i].cdDivd=="8"){//치료대	
						nm = resultData[i].trtNm;
						cost = resultData[i].trtLimitCost;
					}else{
						nm="";cost="";
					}
					if(i==0){
						result+="<tr style='background-color:#FAF4C0;'><td colspan='8'  style='text-align:left;'>진료내역</td><td>금액</td><td>일투</td><td>총투</td><td></td></tr>";
					}
					result+="<tr id='"+resultData[i].cd+"' class='trId' cd='trt'>";
					result+="	<td>"+nullCheck(resultData[i].lnNum)+"</td>";
					result+="	<td>"+nullCheck(resultData[i].sectNum1)+"</td>";
					result+="	<td>"+nullCheck(resultData[i].sectNum2)+"</td>";	
					result+="	<td>"+nullCheck(resultData[i].divCd)+"</td>";		
					result+="	<td>"+nullCheck(resultData[i].cdDivd)+"</td>";	
					result+="	<td style='cursor:pointer;' ondblclick=\'javascript:cdDetail(\""+resultData[i].cdDivd+"\",\""+resultData[i].cd+"\")\'>"+nullCheck(resultData[i].cd)+"</td>";
					result+="	<td class='l'>"+nullCheck(nm)+"</td>";
					result+="	<td><font color='red'>"+nullCheck(resultData[i].speDetail)+"</font></td>";
					result+="	<td class='r'>"+disPlayComma(resultData[i].prc)+"</td>";
					result+="	<td>"+disPlayFloat(resultData[i].dorseDayCnt)+"</td>";
					result+="	<td>"+disPlayFloat(resultData[i].dorseTotCnt)+"</td>";
					//result+="	<td class='r'>"+disPlayComma(cost)+"</td>";					
					result+="</tr>";
				}
			}
		}
		$("#treatmentStatementBody").append(result);
		if($("#treatmentStatementBody").html()=="") $("#treatmentStatementBody").html("<tr><td colspan='11'>조회된 내역이 없습니다.</td></tr>");	
	}
	
	displaySpecificDetailStatement  = function(resultData){
		var result="";
		if(resultData!=null){
			if(resultData.length>0){
				for(var i=0; i<resultData.length; i++){					
					if(resultData[i].prscrptnDivd == 1){
						result+="<p style='font-family: dotum; font-size:12px; color:#2c2c2c;'>[명세서]"+resultData[i].speDetailDivd+" : "+resultData[i].speDetail+"</p>";
					}else{
						result+="<p style='font-family: dotum; font-size:12px; color:#2c2c2c;'>["+resultData[i].lnNum+"]"+resultData[i].speDetailDivd+"("+resultData[i].speCdNm+") : "+resultData[i].speDetail+"</p>";
					}
				}
			}
		}
		$("#specificDetailDiv").html(result);
	}
	
	//사전점검 표현
	displayErrorCheck = function(resultData){
		var result="";
		if(resultData!=null){
			if(resultData.length>0){
				for(var i=0; i<resultData.length; i++){
						result+="<tr onclick=\"itemFocus('"+resultData[i].cd+"')\" class='trId'>";
						result+="<td>"+(i+1)+"</td>";
						result+="	<td>"+nullCheck(resultData[i].msgLev)+"</td>";
						result+="	<td style='cursor:pointer;' ondblclick=\'javascript:errorCheckDetail(\""+resultData[i].cd+"\",\""+resultData[i].msgType+"\",\""+nullCheck(resultData[i].msgLev)+"\",\""+nullCheck(resultData[i].errMsg)+"\",\""+resultData[i].notify+"\",\""+resultData[i].notifySub+"\",\""+"\")\'>"+nullCheck(resultData[i].cd)+"</td>";
						result+="	<td style='text-align:left; padding-left:10px;'>"+nullCheck(resultData[i].errMsg)+"</td>";
						result+="</tr>";
				}
			}else{
				result+="<tr><td colspan='3'>점검된 내역이 없습니다.</td></tr>";
			}
		}else{
			result+="<tr><td colspan='3'>점검된 내역이 없습니다.</td></tr>";
		}
		$("#errorCheckBody").html(result);
	}
	
	itemFocus = function(cd){
		$(".trId").each(function(){
			if($(this).attr("id")==cd){
				$(this).css("background-color","#e5edfe");
				var val = $(this).offset();
				if($(this).attr("cd")=="trt"){
					 div = $('#treatmentStatementBody').offset();
					$('#trtStsDiv').animate({scrollTop:val.top-div.top},300);
				}else{
					div = $('#diseaseStatementBody').offset();
					$('#diseaseStsDiv').animate({scrollTop:val.top-div.top},300);
				}
				 
			}else{
				$(this).css("background-color","");
			}
			
		});
	}
	
	// 사전점검 조회 Ajax
	var errorCheckDetailAjax = {
		beforeSend : function(){
			$.blockUI({message:$("#loadingDiv"),css:{border:'none',backgroundColor:'',color:'',padding:'20px'},overlayCSS : {opacity:0.0}});
		},
		success : function(resultData) {
			var result="";
			if( resultData != null ) {
				
				if(resultData.notifyExtVo!=null){
					$("#tab4Text").html(resultData.notifyExtVo.notifyContents);
				}else if(resultData.notifyCbVo!=null){
					$("#tab4Text").html(resultData.notifyCbVo.notifyContents);
				}else if(resultData.notifyCcVo!=null){
					$("#tab4Text").html(resultData.notifyCcVo.notifyContents);
				}else if(resultData.drugNotifyVo!=null){
					$("#tab4Text").html(resultData.drugNotifyVo.drugNotifyContents);
				}else if(resultData.medicalVo!=null){
					$("#tab4Text").html(resultData.medicalVo.notifyContents);
				}
				
				if(resultData.drugVo!=null){
					$("#tab5Text").html(resultData.drugVo.drugEfficacy);
					$("#tab6Text").html(resultData.drugVo.drugDosage);
					$("#tab7Text").html(resultData.drugVo.drugTaboo);
				}
				
				if(resultData.diseaList!=null){
					if(resultData.diseaList.length>0){
						for(var i=0; i<resultData.diseaList.length; i++){
							result+="<tr>";
							result+="	<td>"+(i+1)+"</td>";
							result+="	<td>"+nullCheck(resultData.diseaList[i].diseaCd)+"</td>";
							result+="	<td class='l'>"+nullCheck(resultData.diseaList[i].diseaKorNm)+"</td>";
							result+="</tr>";
						}
						$("#checkDrugTbody").html(result);
					}
				}
				$.blockUI({message:$("#popWrapDetail"),css:{border: 'none',left:'25%',top:'100px',backgroundColor:''}, overlayCSS : {opacity:0.0,border: 'none'}});
				

			} else {
				alert("조회에 실패하였습니다.");
			}
		},
		type : "post",
		dataType : "json",
		error : function() {
			alert("서버와의 통신에 실패 하였습니다");
			$.unblockUI();
		}
	};
	//0:불필요 1:수가 3:약가 Z:약가(인증상병) Y:수가(인증상병) E:약품예외 C:병용
	errorCheckDetail = function(cd,msgType,msgLev,errMsg,notify,notifySub){
		if(msgType=='0') return false;
		cdNm = "";
		if(msgType!='0') cdNm = $("#"+cd).find("td:eq(5)").text() +"  " +$("#"+cd).find("td:eq(6)").text();
		else cdNm = $("#"+cd).find("td:eq(2)").text();
		
		$("#errCd","#errorCheckDetailForm").val(cd);
		$("#msgType","#errorCheckDetailForm").val(msgType);

		$("#notify","#errorCheckDetailForm").val(notify);
		$("#notifySub","#errorCheckDetailForm").val(notifySub);
		
		$("#errorCheckDetailForm").attr("action", "/statement/selectCheckContents.json");
		
		tabDisplayMode(msgType);
		$("#popTxt1").html(cdNm);
		$("#popTxt2").html(errMsg);
		$("#errorCheckDetailForm").ajaxSubmit(errorCheckDetailAjax);	
	}
	
	cdDetail = function(cdDivd,cd){
		if(cdDivd=='8') return false;
		cdNm = "";
		if(Number(cdDivd)<8) cdNm = $("#"+cd).find("td:eq(5)").text() +"  " +$("#"+cd).find("td:eq(6)").text();
		else cdNm = $("#"+cd).find("td:eq(2)").text();
		
		$("#msgType","#errorCheckDetailForm").val(cdDivd); 
		$("#errCd","#errorCheckDetailForm").val(cd);

		$("#notify","#errorCheckDetailForm").val("");
		$("#notifySub","#errorCheckDetailForm").val("");
		
		$("#errorCheckDetailForm").attr("action", "/statement/selectCheckContents.json");
		
		tabDisplayMode(cdDivd);
		$("#popTxt1").html(cdNm);
		$("#popTxt2").html("-");
		$("#errorCheckDetailForm").ajaxSubmit(errorCheckDetailAjax);	
	}
	
	//0:불필요 1:수가 3:약가 Z:약가(인증상병) E:약품예외 F:수가예외 C:병용 D:단일
	tabDisplayMode = function(type){
		tabDisplayInit();
		if(type=='1'){
			$("#popTab1").parent().show();
			$("#popTab6").parent().show();
			$("#popTab1").click();
		}else if(type=='3'){
			$("#popTab1").parent().show();
			$("#popTab2").parent().show();
			$("#popTab3").parent().show();
			$("#popTab4").parent().show();
			$("#popTab1").click();
		}else if(type=='Z'){
			$("#popTab1").parent().show();
			$("#popTab2").parent().show();
			$("#popTab3").parent().show();
			$("#popTab4").parent().show();
			$("#popTab5").parent().show();
			$("#popTab5").click();
		}else if(type=='E'){
			$("#popTab1").parent().show();
			$("#popTab2").parent().show();
			$("#popTab3").parent().show();
			$("#popTab4").parent().show();
			$("#popTab1").click();
		}else if(type=='F'){
			$("#popTab1").parent().show();
			$("#popTab6").parent().show();
			$("#popTab1").click();
		}else if(type=='C' || type=='D'){
			$("#popTab1").parent().show();
			$("#popTab2").parent().show();
			$("#popTab3").parent().show();
			$("#popTab4").parent().show();
			$("#popTab1").click();
		}else if(type=="Y") {
			$("#popTab1").parent().show();
			$("#popTab5").parent().show();
		}
	}
	
	tabDisplayInit = function(){

		$("#popTab1").parent().hide();
		$("#popTab2").parent().hide();
		$("#popTab3").parent().hide();
		$("#popTab4").parent().hide();
		$("#popTab5").parent().hide();
		$("#popTab6").parent().hide();
		
		$("#checkDrugTbody").html('');
		$("#tab4Text").html('');
		$("#tab5Text").html('');
		$("#tab6Text").html('');
		$("#tab7Text").html('');
		$("#tab8Text").html('');
		$("#popTxt1").html('');
		$("#popTxt2").html('');
	}
	
	hitEnterKey = function(e){
		  if(e.keyCode == 13){
			  $("#searchMedicalSts").click();
		  }
	};
	
	searchFunction = function(searchText){
		$("#searchText").val(searchText);
		$("#searchMedicalSts").click();
	};
</script>