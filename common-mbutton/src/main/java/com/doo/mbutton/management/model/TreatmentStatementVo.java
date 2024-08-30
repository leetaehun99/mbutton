package com.doo.mbutton.management.model;

import java.util.Map;

public class TreatmentStatementVo {
	private long recpCstClmSeq;
	private String clmNum;
	private String stsSrlNum;
	private String hspId;
	private String detailDivd;		// 내역구분 'C' : 진료내역
	
	// 01 진찰료 02 입원료 03 투약료 04 주사료 05 마취료 06 이학요법료 07 정신요법료 08 처치 및 수술료 09 검사료 10 : 영상진단 및 방사선 치료료
	private String sectNum1;		// 항 '진찰료'항부터 '비급여'항까지 16개의 항에 부여된 번호를 기재
	private String sectNum1Nm;
	
	private String sectNum2;		// 목
	private String sectNum2Nm;
	
	private String lnNum;			// 줄
	
	private String cdDivd;			// 코드구분
	private String cdDivdNm;
	private String cd;				// 코드
	private String mainDrugCd;			// 주성분코드
	private String untPrc;			// 단가
	private String dorseDayCnt;		// 일투
	private String dorseTotCnt;		// 총투
	private String dorseOnceAmt;	// 1회투약량
	private String prc;
	private String maxPrc;
	private String drgOverDiffCst;
	private String chgDt="";
	private String licenCateg;
	private String licenNum;
	private String teethRu="";
	private String teethLu="";
	private String teethRd="";
	private String teethLd="";
	private String table = "T_TRT_STS";
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;
	private String drugNm;
	private String korNm;
	private String trtNm;
	private String trtLimitCost;
	private String limitCost;
	private String cost;
	private String relativeVal;
	private String divCd;
	private String self50Yn;					// 본인부담 50
	private String self80Yn;					// 본인부담 80
	private String duplicateYn;					// 중복허용
	private String speDetailSeq;
	private String speDetailDivd;
	private String speDetail;
	private String speCdNm;
	private String modeType;
	private String drugNotify;
	private String usePay;
	private String drugLimitDiffExceptYn;
	private String trtStsSeq;
	
	private String rv1;//병원,요양병원,종합병원 점수
	private String rv2;//의원 점수
	private String rv3;//치과의원 치과병원
	private String rv4;//한의원 한방병원
	private String rv5;//조산원
	private String rv6;//약국 한국희귀의약품센터
	private String rv7;//보건소,보건의료원

	private String extCd;
	private String extVal1;
	private String extVal2;
	
	private String applyDt;
	
	
	

	public String getApplyDt() {
		return applyDt;
	}
	public void setApplyDt(String applyDt) {
		this.applyDt = applyDt;
	}
	public String getMainDrugCd() {
		return mainDrugCd;
	}
	public void setMainDrugCd(String mainDrugCd) {
		this.mainDrugCd = mainDrugCd;
	}
	public String getExtCd() {
		return extCd;
	}
	public void setExtCd(String extCd) {
		this.extCd = extCd;
	}
	public String getExtVal1() {
		return extVal1;
	}
	public void setExtVal1(String extVal1) {
		this.extVal1 = extVal1;
	}
	public String getExtVal2() {
		return extVal2;
	}
	public void setExtVal2(String extVal2) {
		this.extVal2 = extVal2;
	}
	public String getRv1() {
		return rv1;
	}
	public void setRv1(String rv1) {
		this.rv1 = rv1;
	}
	public String getRv2() {
		return rv2;
	}
	public void setRv2(String rv2) {
		this.rv2 = rv2;
	}
	public String getRv3() {
		return rv3;
	}
	public void setRv3(String rv3) {
		this.rv3 = rv3;
	}
	public String getRv4() {
		return rv4;
	}
	public void setRv4(String rv4) {
		this.rv4 = rv4;
	}
	public String getRv5() {
		return rv5;
	}
	public void setRv5(String rv5) {
		this.rv5 = rv5;
	}
	public String getRv6() {
		return rv6;
	}
	public void setRv6(String rv6) {
		this.rv6 = rv6;
	}
	public String getRv7() {
		return rv7;
	}
	public void setRv7(String rv7) {
		this.rv7 = rv7;
	}
	public String getTrtStsSeq() {
		return trtStsSeq;
	}
	public void setTrtStsSeq(String trtStsSeq) {
		this.trtStsSeq = trtStsSeq;
	}
	public String getUsePay() {
		return usePay;
	}
	public void setUsePay(String usePay) {
		this.usePay = usePay;
	}
	public String getDrugLimitDiffExceptYn() {
		return drugLimitDiffExceptYn;
	}
	public void setDrugLimitDiffExceptYn(String drugLimitDiffExceptYn) {
		this.drugLimitDiffExceptYn = drugLimitDiffExceptYn;
	}
	public String getDrugNotify() {
		return drugNotify;
	}
	public void setDrugNotify(String drugNotify) {
		this.drugNotify = drugNotify;
	}
	public long getRecpCstClmSeq() {
		return recpCstClmSeq;
	}
	public void setRecpCstClmSeq(long recpCstClmSeq) {
		this.recpCstClmSeq = recpCstClmSeq;
	}
	public String getTrtNm() {
		return trtNm;
	}
	public void setTrtNm(String trtNm) {
		this.trtNm = trtNm;
	}
	public String getTrtLimitCost() {
		return trtLimitCost;
	}
	public void setTrtLimitCost(String trtLimitCost) {
		this.trtLimitCost = trtLimitCost;
	}
	public Map<String, SamVo> getTreatmentStatementMap() {
		return treatmentStatementMap;
	}
	public void setTreatmentStatementMap(Map<String, SamVo> treatmentStatementMap) {
		this.treatmentStatementMap = treatmentStatementMap;
	}
	public String getModeType() {
		return modeType;
	}
	public void setModeType(String modeType) {
		this.modeType = modeType;
	}
	private Map<String, SamVo> treatmentStatementMap;
	
	public String getClmNum() {
		return clmNum;
	}
	public void setClmNum(String clmNum) {
		this.clmNum = clmNum;
	}
	public String getStsSrlNum() {
		return stsSrlNum;
	}
	public void setStsSrlNum(String stsSrlNum) {
		this.stsSrlNum = stsSrlNum;
	}
	public String getHspId() {
		return hspId;
	}
	public void setHspId(String hspId) {
		this.hspId = hspId;
	}
	public String getDetailDivd() {
		return detailDivd;
	}
	public void setDetailDivd(String detailDivd) {
		this.detailDivd = detailDivd;
	}
	public String getSectNum1() {
		return sectNum1;
	}
	public void setSectNum1(String sectNum1) {
		this.sectNum1 = sectNum1;
	}
	public String getSectNum2() {
		return sectNum2;
	}
	public void setSectNum2(String sectNum2) {
		this.sectNum2 = sectNum2;
	}
	public String getLnNum() {
		return lnNum;
	}
	public void setLnNum(String lnNum) {
		this.lnNum = lnNum;
	}
	public String getCdDivd() {
		return cdDivd;
	}
	public void setCdDivd(String cdDivd) {
		this.cdDivd = cdDivd;
	}
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getUntPrc() {
		return untPrc;
	}
	public void setUntPrc(String untPrc) {
		this.untPrc = untPrc;
	}
	public String getDorseDayCnt() {
		return dorseDayCnt;
	}
	public void setDorseDayCnt(String dorseDayCnt) {
		this.dorseDayCnt = dorseDayCnt;
	}
	public String getDorseTotCnt() {
		return dorseTotCnt;
	}
	public void setDorseTotCnt(String dorseTotCnt) {
		this.dorseTotCnt = dorseTotCnt;
	}
	public String getDorseOnceAmt() {
		return dorseOnceAmt;
	}
	public void setDorseOnceAmt(String dorseOnceAmt) {
		this.dorseOnceAmt = dorseOnceAmt;
	}
	public String getPrc() {
		return prc;
	}
	public void setPrc(String prc) {
		this.prc = prc;
	}
	public String getMaxPrc() {
		return maxPrc;
	}
	public void setMaxPrc(String maxPrc) {
		this.maxPrc = maxPrc;
	}
	public String getDrgOverDiffCst() {
		return drgOverDiffCst;
	}
	public void setDrgOverDiffCst(String drgOverDiffCst) {
		this.drgOverDiffCst = drgOverDiffCst;
	}
	public String getChgDt() {
		return chgDt;
	}
	public void setChgDt(String chgDt) {
		this.chgDt = chgDt;
	}
	public String getLicenCateg() {
		return licenCateg;
	}
	public void setLicenCateg(String licenCateg) {
		this.licenCateg = licenCateg;
	}
	public String getLicenNum() {
		return licenNum;
	}
	public void setLicenNum(String licenNum) {
		this.licenNum = licenNum;
	}
	public String getTeethRu() {
		return teethRu;
	}
	public void setTeethRu(String teethRu) {
		this.teethRu = teethRu;
	}
	public String getTeethLu() {
		return teethLu;
	}
	public void setTeethLu(String teethLu) {
		this.teethLu = teethLu;
	}
	public String getTeethRd() {
		return teethRd;
	}
	public void setTeethRd(String teethRd) {
		this.teethRd = teethRd;
	}
	public String getTeethLd() {
		return teethLd;
	}
	public void setTeethLd(String teethLd) {
		this.teethLd = teethLd;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	public String getRegistDthms() {
		return registDthms;
	}
	public void setRegistDthms(String registDthms) {
		this.registDthms = registDthms;
	}
	public String getUpdaterId() {
		return updaterId;
	}
	public void setUpdaterId(String updaterId) {
		this.updaterId = updaterId;
	}
	public String getUpdateDthms() {
		return updateDthms;
	}
	public void setUpdateDthms(String updateDthms) {
		this.updateDthms = updateDthms;
	}
	
	public String getDrugNm() {
		return drugNm;
	}
	public void setDrugNm(String drugNm) {
		this.drugNm = drugNm;
	}
	public String getKorNm() {
		return korNm;
	}
	public void setKorNm(String korNm) {
		this.korNm = korNm;
	}
	public String getLimitCost() {
		return limitCost;
	}
	public void setLimitCost(String limitCost) {
		this.limitCost = limitCost;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getSpeDetailSeq() {
		return speDetailSeq;
	}
	public void setSpeDetailSeq(String speDetailSeq) {
		this.speDetailSeq = speDetailSeq;
	}
	public String getSpeDetail() {
		return speDetail;
	}
	public void setSpeDetail(String speDetail) {
		this.speDetail = speDetail;
	}
	public String getSpeDetailDivd() {
		return speDetailDivd;
	}
	public void setSpeDetailDivd(String speDetailDivd) {
		this.speDetailDivd = speDetailDivd;
	}
	public String getRelativeVal() {
		return relativeVal;
	}
	public void setRelativeVal(String relativeVal) {
		this.relativeVal = relativeVal;
	}
	public String getDivCd() {
		return divCd;
	}
	public void setDivCd(String divCd) {
		this.divCd = divCd;
	}
	
	public String getSelf50Yn() {
		return self50Yn;
	}
	public void setSelf50Yn(String self50Yn) {
		this.self50Yn = self50Yn;
	}
	public String getSelf80Yn() {
		return self80Yn;
	}
	public void setSelf80Yn(String self80Yn) {
		this.self80Yn = self80Yn;
	}
	public String getDuplicateYn() {
		return duplicateYn;
	}
	public void setDuplicateYn(String duplicateYn) {
		this.duplicateYn = duplicateYn;
	}
	public String getSpeCdNm() {
		return speCdNm;
	}
	public void setSpeCdNm(String speCdNm) {
		this.speCdNm = speCdNm;
	}
	public String getSectNum1Nm() {
		return sectNum1Nm;
	}
	public void setSectNum1Nm(String sectNum1Nm) {
		this.sectNum1Nm = sectNum1Nm;
	}
	public String getSectNum2Nm() {
		return sectNum2Nm;
	}
	public void setSectNum2Nm(String sectNum2Nm) {
		this.sectNum2Nm = sectNum2Nm;
	}
	public String getCdDivdNm() {
		return cdDivdNm;
	}
	public void setCdDivdNm(String cdDivdNm) {
		this.cdDivdNm = cdDivdNm;
	}
	public void setTreatmentStatementMap(Map<String, SamVo> treatmentStatementMap,String hspId,String table) {
		this.clmNum			=	treatmentStatementMap.get("clmNum").getValue();
		this.stsSrlNum		=	treatmentStatementMap.get("stsSrlNum").getValue();
		this.hspId			=	hspId;
		this.detailDivd		=	treatmentStatementMap.get("detailDivd").getValue();
		this.sectNum1		=	treatmentStatementMap.get("sectNum1").getValue();
		this.sectNum2		=	treatmentStatementMap.get("sectNum2").getValue();
		this.lnNum			=	treatmentStatementMap.get("lnNum").getValue();
		this.cdDivd			=	treatmentStatementMap.get("cdDivd").getValue();
		this.cd				=	treatmentStatementMap.get("cd").getValue();
		this.untPrc			=	treatmentStatementMap.get("untPrc").getValue();
		this.dorseDayCnt	=	treatmentStatementMap.get("dorseDayCnt").getValue();
		this.dorseTotCnt	=	treatmentStatementMap.get("dorseTotCnt").getValue();
		this.dorseOnceAmt	=	treatmentStatementMap.get("dorseOnceAmt").getValue();
		this.prc			=	treatmentStatementMap.get("prc").getValue();
		this.maxPrc			=	treatmentStatementMap.get("maxPrc").getValue();
		this.drgOverDiffCst	=	treatmentStatementMap.get("drgOverDiffCst").getValue();
		this.chgDt			=	treatmentStatementMap.get("chgDt").getValue();
		this.licenCateg		=	treatmentStatementMap.get("licenCateg").getValue();
		this.licenNum		=	treatmentStatementMap.get("licenNum").getValue();
		this.teethRu		=	treatmentStatementMap.get("teethRu").getValue();
		this.teethLu		=	treatmentStatementMap.get("teethLu").getValue();
		this.teethRd		=	treatmentStatementMap.get("teethRd").getValue();
		this.teethLd		=	treatmentStatementMap.get("teethLd").getValue();
		this.table			=	this.table+table;
	}
	
	public String getResultVal() {
		StringBuffer resultVal = new StringBuffer();
		resultVal.append(getByte(this.clmNum,10,0,0));
		resultVal.append(getByte(this.stsSrlNum,5,0,0));
		resultVal.append(getByte(this.detailDivd,1,0,0));
		resultVal.append(getByte(this.sectNum1,2,0,0));
		resultVal.append(getByte(this.sectNum2,2,0,0));
		resultVal.append(getByte(this.lnNum,4,0,1));
		resultVal.append(getByte(this.cdDivd,1,0,0));
		resultVal.append(getByte(this.cd,9,0,0));
		resultVal.append(getByte(this.untPrc,8,2,1));
		resultVal.append(getByte(this.dorseDayCnt,5,2,1));
		resultVal.append(getByte(this.dorseTotCnt,3,0,1));
		resultVal.append(getByte(this.dorseOnceAmt,5,4,1));
		resultVal.append(getByte(this.prc,10,0,1));
		resultVal.append(getByte(this.maxPrc,10,0,1));
		resultVal.append(getByte(this.drgOverDiffCst,10,0,1));
		resultVal.append(getByte(this.chgDt,8,0,0));
		resultVal.append(getByte(this.licenCateg,1,0,0));
		resultVal.append(getByte(this.licenNum,100,0,0));
		return resultVal.toString();
	}
	
	private String getByte(String val,int intSize,int floatSize,int type){
		
		String returnVal = val;
		if(returnVal == null) return "";
		int size = intSize+floatSize;
		try {
			String preFixVal="";
			byte[] byteVal = null;
			int valSize = val.length();
			int byteSize = val.getBytes("KSC5601").length;
			int cutIdx = 0; 
			int cumLen = 0; 
			if(type==0){
				if (byteSize > size ){
					for(int i = 0; i < valSize; i++){
						cumLen = val.substring(0, i+1).getBytes("KSC5601").length;
						if(cumLen > size) {
							cutIdx = i - 1;
							i = valSize + 1; // for loop 탈출
						}else if(cumLen == size){
							cutIdx = i;
							i = valSize + 1; // for loop 탈출
						}
					}      
					returnVal = val.substring(0, cutIdx + 1); // String을 자를때는 endIndex - 1 만큼 자르기 때문에 +1을 해서 보정해준다.
				}
	
				// 빈 공백을 추가하기 위해 다시 길이를 가져온다.
				valSize = returnVal.getBytes("KSC5601").length;
	
				for ( int i = valSize ; i < size ; i++ ) {
					returnVal = returnVal+ " ";
				}
			}else{
				while(byteSize!=(size)){
					preFixVal+="0";
					byteSize++;
				}
				returnVal=preFixVal+returnVal;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnVal;
	}
	
}
