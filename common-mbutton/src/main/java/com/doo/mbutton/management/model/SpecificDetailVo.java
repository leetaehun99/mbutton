package com.doo.mbutton.management.model;

import java.util.Map;

import com.doo.mbutton.common.model.PagingVo;

public class SpecificDetailVo extends PagingVo{
	private long recpCstClmSeq;
	private String speDetailSeq;
	private String clmNum;
	private String stsSrlNum;
	private String hspId;
	private String detailDivd;
	private String prscrptnDivd;
	private String prscrptnNum;
	private String lnNum;
	private String speDetailDivd;
	private String speDetail;
	private String speCdNm;
	private String table = "T_SPE_DETAIL";
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;
	private String modeType;
	private Map<String, SamVo> specificDetailMap;
	
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
	public String getSpeDetailSeq() {
		return speDetailSeq;
	}
	public void setSpeDetailSeq(String speDetailSeq) {
		this.speDetailSeq = speDetailSeq;
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
	public String getPrscrptnDivd() {
		return prscrptnDivd;
	}
	public void setPrscrptnDivd(String prscrptnDivd) {
		this.prscrptnDivd = prscrptnDivd;
	}
	public String getPrscrptnNum() {
		return prscrptnNum;
	}
	public void setPrscrptnNum(String prscrptnNum) {
		this.prscrptnNum = prscrptnNum;
	}
	public String getLnNum() {
		return lnNum;
	}
	public void setLnNum(String lnNum) {
		this.lnNum = lnNum;
	}
	public String getSpeDetailDivd() {
		return speDetailDivd;
	}
	public void setSpeDetailDivd(String speDetailDivd) {
		this.speDetailDivd = speDetailDivd;
	}
	public String getSpeDetail() {
		return speDetail;
	}
	public void setSpeDetail(String speDetail) {
		this.speDetail = speDetail;
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
	public String getSpeCdNm() {
		return speCdNm;
	}
	public void setSpeCdNm(String speCdNm) {
		this.speCdNm = speCdNm;
	}
	public void setSpecificDetailMap(Map<String, SamVo> specificDetailMap,String hspId,String table) {
		this.clmNum			=	specificDetailMap.get("clmNum").getValue();
		this.stsSrlNum		=	specificDetailMap.get("stsSrlNum").getValue();
		this.hspId			=	hspId;
		this.detailDivd		=	specificDetailMap.get("detailDivd").getValue();
		this.prscrptnDivd	=	specificDetailMap.get("prscrptnDivd").getValue();
		this.prscrptnNum	=	specificDetailMap.get("prscrptnNum").getValue();
		this.lnNum			=	specificDetailMap.get("lnNum").getValue();
		this.speDetailDivd	=	specificDetailMap.get("speDetailDivd").getValue();
		this.speDetail		=	specificDetailMap.get("speDetail").getValue();
		this.table			=	this.table+table;
	}
	
	public String getResultVal() {
		StringBuffer resultVal = new StringBuffer();
		resultVal.append(getByte(this.clmNum,10,0,0));
		resultVal.append(getByte(this.stsSrlNum,5,0,0));
		resultVal.append(getByte(this.detailDivd,1,0,0));
		resultVal.append(getByte(this.prscrptnDivd,1,0,0));
		resultVal.append(getByte(this.prscrptnNum,13,0,1));
		resultVal.append(getByte(this.lnNum,4,0,1));
		resultVal.append(getByte(this.speDetailDivd,5,0,0));
		resultVal.append(getByte(this.speDetail,700,0,0));
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
