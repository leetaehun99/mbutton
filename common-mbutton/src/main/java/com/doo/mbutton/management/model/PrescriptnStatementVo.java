package com.doo.mbutton.management.model;

import java.util.Map;

public class PrescriptnStatementVo {
	private long recpCstClmSeq;
	private String prscrptnStsSeq;
	private String clmNum;
	private String stsSrlNum;
	private String hspId;
	private String detailDivd;
	private String prscrptnNum;
	private String prscrptnDayCnt;
	private String againMakCnt;
	private String lnNum;
	private String cdDivd;
	private String cd;
	private String mainDrugCd;
	private String drugNm;
	private String dorseOnce;
	private String dorseOnceCnt;
	private String dorseTotCnt;
	private String table = "T_PRSCRPTN_STS";
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;

	private String parDiv;
	private String limitCost;
	private String injectPass;
	private String drugNotify;
	private String exitCd;
	
	private String applyDt;
	
	
	public String getApplyDt() {
		return applyDt;
	}
	public void setApplyDt(String applyDt) {
		this.applyDt = applyDt;
	}

	private Map<String, SamVo> prescriptnStatementMap;
	
	
	
	public String getMainDrugCd() {
		return mainDrugCd;
	}
	public void setMainDrugCd(String mainDrugCd) {
		this.mainDrugCd = mainDrugCd;
	}
	public String getParDiv() {
		return parDiv;
	}
	public String getPrscrptnStsSeq() {
		return prscrptnStsSeq;
	}
	public void setPrscrptnStsSeq(String prscrptnStsSeq) {
		this.prscrptnStsSeq = prscrptnStsSeq;
	}
	public void setParDiv(String parDiv) {
		this.parDiv = parDiv;
	}
	public String getLimitCost() {
		return limitCost;
	}
	public void setLimitCost(String limitCost) {
		this.limitCost = limitCost;
	}
	public String getInjectPass() {
		return injectPass;
	}
	public void setInjectPass(String injectPass) {
		this.injectPass = injectPass;
	}
	public String getDrugNotify() {
		return drugNotify;
	}
	public void setDrugNotify(String drugNotify) {
		this.drugNotify = drugNotify;
	}
	public String getExitCd() {
		return exitCd;
	}
	public void setExitCd(String exitCd) {
		this.exitCd = exitCd;
	}
	public String getDrugNm() {
		return drugNm;
	}
	public void setDrugNm(String drugNm) {
		this.drugNm = drugNm;
	}
	public long getRecpCstClmSeq() {
		return recpCstClmSeq;
	}
	public void setRecpCstClmSeq(long recpCstClmSeq) {
		this.recpCstClmSeq = recpCstClmSeq;
	}
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
	public String getPrscrptnNum() {
		return prscrptnNum;
	}
	public void setPrscrptnNum(String prscrptnNum) {
		this.prscrptnNum = prscrptnNum;
	}
	public String getPrscrptnDayCnt() {
		return prscrptnDayCnt;
	}
	public void setPrscrptnDayCnt(String prscrptnDayCnt) {
		this.prscrptnDayCnt = prscrptnDayCnt;
	}
	public String getAgainMakCnt() {
		return againMakCnt;
	}
	public void setAgainMakCnt(String againMakCnt) {
		this.againMakCnt = againMakCnt;
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
	public String getDorseOnce() {
		return dorseOnce;
	}
	public void setDorseOnce(String dorseOnce) {
		this.dorseOnce = dorseOnce;
	}
	public String getDorseOnceCnt() {
		return dorseOnceCnt;
	}
	public void setDorseOnceCnt(String dorseOnceCnt) {
		this.dorseOnceCnt = dorseOnceCnt;
	}
	public String getDorseTotCnt() {
		return dorseTotCnt;
	}
	public void setDorseTotCnt(String dorseTotCnt) {
		this.dorseTotCnt = dorseTotCnt;
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
	public void setPrescriptnStatementMap(Map<String, SamVo> prescriptnStatementMap,String hspId,String table) {
		this.clmNum			=	prescriptnStatementMap.get("clmNum").getValue();
		this.stsSrlNum		=	prescriptnStatementMap.get("stsSrlNum").getValue();
		this.hspId			=	hspId;
		this.detailDivd		=	prescriptnStatementMap.get("detailDivd").getValue();
		this.prscrptnNum	=	prescriptnStatementMap.get("prscrptnNum").getValue();
		this.prscrptnDayCnt	=	prescriptnStatementMap.get("prscrptnDayCnt").getValue();
		this.againMakCnt	=	prescriptnStatementMap.get("againMakCnt").getValue();
		this.lnNum			=	prescriptnStatementMap.get("lnNum").getValue();
		this.cdDivd			=	prescriptnStatementMap.get("cdDivd").getValue();
		this.cd				=	prescriptnStatementMap.get("cd").getValue();
		this.dorseOnce		=	prescriptnStatementMap.get("dorseOnce").getValue();
		this.dorseOnceCnt	=	prescriptnStatementMap.get("dorseOnceCnt").getValue();
		this.dorseTotCnt	=	prescriptnStatementMap.get("dorseTotCnt").getValue();
		this.table			=	this.table+table;
	}
	
	public String getResultVal() {
		StringBuffer resultVal = new StringBuffer();
		resultVal.append(getByte(this.clmNum,10,0,0));
		resultVal.append(getByte(this.stsSrlNum,5,0,0));
		resultVal.append(getByte(this.detailDivd,1,0,0));
		resultVal.append(getByte(this.prscrptnNum,13,0,0));
		resultVal.append(getByte(this.prscrptnDayCnt,3,0,1));
		resultVal.append(getByte(this.againMakCnt,2,0,1));
		resultVal.append(getByte(this.lnNum,4,0,1));
		resultVal.append(getByte(this.cdDivd,1,0,0));
		resultVal.append(getByte(this.cd,9,0,0));
		resultVal.append(getByte(this.dorseOnce,5,4,1));
		resultVal.append(getByte(this.dorseOnceCnt,2,0,1));
		resultVal.append(getByte(this.dorseTotCnt,3,0,1));
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
