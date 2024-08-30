package com.doo.mbutton.management.model;

import java.util.Map;


public class DiseaseStatementVo{
	private long recpCstClmSeq;
	private String modeType;
	private String clmNum = "";
	private String stsSrlNum = "";
	private String hspId = "";
	private String detailDivd = "B";
	
	private String diseaDivdCd = "";
	private String diseaDivdCdNm = "";
	private String diseaDivdSym = "";
	private String[] arrayDiseaDivdCd;
	private String[] arrayDiseaDivdSym;
	private String trtSubct = "";
	private String trtSubctNm = "";
	private String trtDetailSubct = "";
	private String trtDetailSubctNm = "";
	private String trtStartDt = "";
	private String licenCateg = "";
	private String licenNum = "";
	private String teethRu = "";
	private String teethLu = "";
	private String teethRd = "";
	private String teethLd = "";
	private String diseaKorNm ="";
	private String diseaEngNm ="";
	private String table = "T_DISEA_STS";
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;
	private Map<String, SamVo> diseaseStatementMap;
	private String diseaStsSeq;
	
	public Map<String, SamVo> getDiseaseStatementMap() {
		return diseaseStatementMap;
	}
	public void setDiseaseStatementMap(Map<String, SamVo> diseaseStatementMap) {
		this.diseaseStatementMap = diseaseStatementMap;
	}
	public String getDiseaStsSeq() {
		return diseaStsSeq;
	}
	public void setDiseaStsSeq(String diseaStsSeq) {
		this.diseaStsSeq = diseaStsSeq;
	}
	public long getRecpCstClmSeq() {
		return recpCstClmSeq;
	}
	public void setRecpCstClmSeq(long recpCstClmSeq) {
		this.recpCstClmSeq = recpCstClmSeq;
	}
	public String getModeType() {
		return modeType;
	}
	public void setModeType(String modeType) {
		this.modeType = modeType;
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
	public String getDiseaDivdCd() {
		return diseaDivdCd;
	}
	public void setDiseaDivdCd(String diseaDivdCd) {
		this.diseaDivdCd = diseaDivdCd;
	}
	public String getDiseaDivdSym() {
		return diseaDivdSym;
	}
	public void setDiseaDivdSym(String diseaDivdSym) {
		this.diseaDivdSym = diseaDivdSym;
	}
	public String[] getArrayDiseaDivdCd() {
		return arrayDiseaDivdCd;
	}
	public void setArrayDiseaDivdCd(String[] arrayDiseaDivdCd) {
		this.arrayDiseaDivdCd = arrayDiseaDivdCd;
	}
	public String[] getArrayDiseaDivdSym() {
		return arrayDiseaDivdSym;
	}
	public void setArrayDiseaDivdSym(String[] arrayDiseaDivdSym) {
		this.arrayDiseaDivdSym = arrayDiseaDivdSym;
	}
	public String getTrtSubct() {
		return trtSubct;
	}
	public void setTrtSubct(String trtSubct) {
		this.trtSubct = trtSubct;
	}
	public String getTrtDetailSubct() {
		return trtDetailSubct;
	}
	public void setTrtDetailSubct(String trtDetailSubct) {
		this.trtDetailSubct = trtDetailSubct;
	}
	public String getTrtStartDt() {
		return trtStartDt;
	}
	public void setTrtStartDt(String trtStartDt) {
		this.trtStartDt = trtStartDt;
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
	public String getDiseaKorNm() {
		return diseaKorNm;
	}
	public void setDiseaKorNm(String diseaKorNm) {
		this.diseaKorNm = diseaKorNm;
	}
	public String getDiseaEngNm() {
		return diseaEngNm;
	}
	public void setDiseaEngNm(String diseaEngNm) {
		this.diseaEngNm = diseaEngNm;
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
	public String getDiseaDivdCdNm() {
		return diseaDivdCdNm;
	}
	public void setDiseaDivdCdNm(String diseaDivdCdNm) {
		this.diseaDivdCdNm = diseaDivdCdNm;
	}
	public String getTrtSubctNm() {
		return trtSubctNm;
	}
	public void setTrtSubctNm(String trtSubctNm) {
		this.trtSubctNm = trtSubctNm;
	}
	public String getTrtDetailSubctNm() {
		return trtDetailSubctNm;
	}
	public void setTrtDetailSubctNm(String trtDetailSubctNm) {
		this.trtDetailSubctNm = trtDetailSubctNm;
	}
	public void setDiseaseStatementMap(Map<String, SamVo> diseaseStatementMap,String hspId,String table) {
		this.clmNum			=	diseaseStatementMap.get("clmNum").getValue();
		this.stsSrlNum		=	diseaseStatementMap.get("stsSrlNum").getValue();
		this.hspId			=	hspId;
		this.detailDivd		=	diseaseStatementMap.get("detailDivd").getValue();
		this.diseaDivdCd	=	diseaseStatementMap.get("diseaDivdCd").getValue();
		this.diseaDivdSym	=	diseaseStatementMap.get("diseaDivdSym").getValue();
		this.trtSubct		=	diseaseStatementMap.get("trtSubct").getValue();
		this.trtDetailSubct	=	diseaseStatementMap.get("trtDetailSubct").getValue();
		this.trtStartDt		=	diseaseStatementMap.get("trtStartDt").getValue();
		this.licenCateg		=	diseaseStatementMap.get("licenCateg").getValue();
		this.licenNum		=	diseaseStatementMap.get("licenNum").getValue();
		this.teethRu		=	diseaseStatementMap.get("teethRu").getValue();
		this.teethLu		=	diseaseStatementMap.get("teethLu").getValue();
		this.teethRd		=	diseaseStatementMap.get("teethRd").getValue();
		this.teethLd		=	diseaseStatementMap.get("teethLd").getValue();
		this.table			=	this.table+table;
	}
	
	public String getResultVal() {
		StringBuffer resultVal = new StringBuffer();
		resultVal.append(getByte(this.clmNum,10,0,0));
		resultVal.append(getByte(this.stsSrlNum,5,0,0));
		resultVal.append(getByte(this.detailDivd,1,0,0));
		resultVal.append(getByte(this.diseaDivdCd,1,0,0));
		resultVal.append(getByte(this.diseaDivdSym,6,0,0));
		resultVal.append(getByte(this.trtSubct,2,0,0));
		resultVal.append(getByte(this.trtDetailSubct,2,0,0));
		resultVal.append(getByte(this.trtStartDt,8,0,0));
		resultVal.append(getByte(this.licenCateg,1,0,0));
		resultVal.append(getByte(this.licenNum,10,0,0));
		return resultVal.toString();
	}
	
	private String getByte(String val,int size,int floatSize,int type){
		String returnVal = val;
		String preFixVal="";
		byte[] byteVal = null;
		int byteSize = 0;
		try {
			byteVal = val.getBytes("KSC5601");
			byteSize = byteVal.length;
			if(type==0){
				while(byteSize!=(size+floatSize)){
					returnVal+=" ";
					byteSize++;
				}
			}else{
				
				while(byteSize!=(size+floatSize)){
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
