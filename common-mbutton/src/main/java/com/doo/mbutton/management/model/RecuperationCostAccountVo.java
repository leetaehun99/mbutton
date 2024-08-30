package com.doo.mbutton.management.model;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.doo.mbutton.common.helper.EncUtil;
import com.doo.mbutton.common.model.PagingVo;

public class RecuperationCostAccountVo extends PagingVo{
	
	private long recpCstClmSeq = 0;
	private String stsTitle;
	private String clmDcmtVsn;
	private String stsDcmtVsn;
	private String clmNum;
	private String dcmtNum;
	private String hspId;
	private String rcvInst;
	private String insrDivd;
	private String clmDivd;
	private String clmUntDivd;
	private String trtDivd;
	private String trtFldDivd;
	private String trtTyp;
	private String trtYrMnth;
	private String trtCnt;
	private String recpCst1;
	private String cinsrCost;
	private String cinsrOverCst;
	private String clmCst;
	private String sprtCst;
	private String hadipCst;
	private String recpCst2;
	private String helthCst;
	private String h100Cinsr;
	private String helthCinsrPrt;
	
	private String t100Cst1;
	private String t100Cst2;
	private String t100Cst3;
	private String t100Cst4;
	

	private String etc1;
	private String etc2;
	private String etc3;
	private String etc4;
	
/*	private String dffTrtDayCnt;
	private String dctrCnt;
	private String dffIndx;
	private String dffMetrtCst;*/
	
	private String clmDt;
	private String clmNm;
	private String wrtNm;
	private String wrtBrtDt;
	private String birthDy;
	private String chkAprvNum;
	private String agnClmSym;
	private String ref;
	private String table = "T_RECP_CST_CLM";
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;
	private String searchCd;
	private String msgYn;
	private String fileYn;
	private String fileSeq;
	
	public String getMsgYn() {
		return msgYn;
	}
	public void setMsgYn(String msgYn) {
		this.msgYn = msgYn;
	}
	public String getSearchCd() {
		return searchCd;
	}
	public void setSearchCd(String searchCd) {
		this.searchCd = searchCd;
	}

	private Map<String, SamVo> recuperationCostAccountMap;
	public long getRecpCstClmSeq() {
		return recpCstClmSeq;
	}
	public void setRecpCstClmSeq(long recpCstClmSeq) {
		this.recpCstClmSeq = recpCstClmSeq;
	}
	public String getClmDcmtVsn() {
		return clmDcmtVsn;
	}
	public void setClmDcmtVsn(String clmDcmtVsn) {
		this.clmDcmtVsn = clmDcmtVsn;
	}
	public String getStsDcmtVsn() {
		return stsDcmtVsn;
	}
	public void setStsDcmtVsn(String stsDcmtVsn) {
		this.stsDcmtVsn = stsDcmtVsn;
	}
	public String getClmNum() {
		return clmNum;
	}
	public void setClmNum(String clmNum) {
		this.clmNum = clmNum;
	}
	public String getDcmtNum() {
		return dcmtNum;
	}
	public void setDcmtNum(String dcmtNum) {
		this.dcmtNum = dcmtNum;
	}
	public String getHspId() {
		return hspId;
	}
	public void setHspId(String hspId) {
		this.hspId = hspId;
	}
	public String getRcvInst() {
		return rcvInst;
	}
	public void setRcvInst(String rcvInst) {
		this.rcvInst = rcvInst;
	}
	public String getInsrDivd() {
		return insrDivd;
	}
	public void setInsrDivd(String insrDivd) {
		this.insrDivd = insrDivd;
	}
	public String getClmDivd() {
		return clmDivd;
	}
	public void setClmDivd(String clmDivd) {
		this.clmDivd = clmDivd;
	}
	public String getClmUntDivd() {
		return clmUntDivd;
	}
	public void setClmUntDivd(String clmUntDivd) {
		this.clmUntDivd = clmUntDivd;
	}
	public String getTrtDivd() {
		return trtDivd;
	}
	public void setTrtDivd(String trtDivd) {
		this.trtDivd = trtDivd;
	}
	public String getTrtFldDivd() {
		return trtFldDivd;
	}
	public void setTrtFldDivd(String trtFldDivd) {
		this.trtFldDivd = trtFldDivd;
	}
	public String getTrtTyp() {
		return trtTyp;
	}
	public void setTrtTyp(String trtTyp) {
		this.trtTyp = trtTyp;
	}
	public String getTrtYrMnth() {
		return trtYrMnth;
	}
	public void setTrtYrMnth(String trtYrMnth) {
		this.trtYrMnth = trtYrMnth;
	}
	public String getTrtCnt() {
		return trtCnt;
	}
	public void setTrtCnt(String trtCnt) {
		this.trtCnt = trtCnt;
	}
	public String getRecpCst1() {
		return recpCst1;
	}
	public void setRecpCst1(String recpCst1) {
		this.recpCst1 = recpCst1;
	}
	public String getCinsrCost() {
		return cinsrCost;
	}
	public void setCinsrCost(String cinsrCost) {
		this.cinsrCost = cinsrCost;
	}
	public String getCinsrOverCst() {
		return cinsrOverCst;
	}
	public void setCinsrOverCst(String cinsrOverCst) {
		this.cinsrOverCst = cinsrOverCst;
	}
	public String getClmCst() {
		return clmCst;
	}
	public void setClmCst(String clmCst) {
		this.clmCst = clmCst;
	}
	public String getSprtCst() {
		return sprtCst;
	}
	public void setSprtCst(String sprtCst) {
		this.sprtCst = sprtCst;
	}
	public String getHadipCst() {
		return hadipCst;
	}
	public void setHadipCst(String hadipCst) {
		this.hadipCst = hadipCst;
	}
	public String getRecpCst2() {
		return recpCst2;
	}
	public void setRecpCst2(String recpCst2) {
		this.recpCst2 = recpCst2;
	}
	public String getHelthCst() {
		return helthCst;
	}
	public void setHelthCst(String helthCst) {
		this.helthCst = helthCst;
	}
	public String getH100Cinsr() {
		return h100Cinsr;
	}
	public void setH100Cinsr(String h100Cinsr) {
		this.h100Cinsr = h100Cinsr;
	}
	public String getHelthCinsrPrt() {
		return helthCinsrPrt;
	}
	public void setHelthCinsrPrt(String helthCinsrPrt) {
		this.helthCinsrPrt = helthCinsrPrt;
	}
	
	public String getT100Cst1() {
		return t100Cst1;
	}
	public void setT100Cst1(String t100Cst1) {
		this.t100Cst1 = t100Cst1;
	}
	public String getT100Cst2() {
		return t100Cst2;
	}
	public void setT100Cst2(String t100Cst2) {
		this.t100Cst2 = t100Cst2;
	}
	public String getT100Cst3() {
		return t100Cst3;
	}
	public void setT100Cst3(String t100Cst3) {
		this.t100Cst3 = t100Cst3;
	}
	public String getT100Cst4() {
		return t100Cst4;
	}
	public void setT100Cst4(String t100Cst4) {
		this.t100Cst4 = t100Cst4;
	}
	public String getEtc1() {
		return etc1;
	}
	public void setEtc1(String etc1) {
		this.etc1 = etc1;
	}
	public String getEtc2() {
		return etc2;
	}
	public void setEtc2(String etc2) {
		this.etc2 = etc2;
	}
	public String getEtc3() {
		return etc3;
	}
	public void setEtc3(String etc3) {
		this.etc3 = etc3;
	}
	public String getEtc4() {
		return etc4;
	}
	public void setEtc4(String etc4) {
		this.etc4 = etc4;
	}
	public String getClmDt() {
		return clmDt;
	}
	public void setClmDt(String clmDt) {
		this.clmDt = clmDt;
	}
	public String getClmNm() {
		return clmNm;
	}
	public void setClmNm(String clmNm) {
		this.clmNm = clmNm;
	}
	public String getWrtNm() {
		return wrtNm;
	}
	public void setWrtNm(String wrtNm) {
		this.wrtNm = wrtNm;
	}
	public String getBirthDy() {
		return birthDy;
	}
	public void setBirthDy(String birthDy) {
		this.birthDy = birthDy;
	}
	public String getWrtBrtDt() {
		return this.wrtBrtDt;
	}
	public String getWrtBrtDtEnc() {
		return EncUtil.getSSDec(this.wrtBrtDt,getEncKey().getBytes());
	}
	public void setWrtBrtDt(String wrtBrtDt) {
		this.wrtBrtDt = wrtBrtDt;
	}
	public String getChkAprvNum() {
		return chkAprvNum;
	}
	public void setChkAprvNum(String chkAprvNum) {
		this.chkAprvNum = chkAprvNum;
	}
	public String getAgnClmSym() {
		return agnClmSym;
	}
	public void setAgnClmSym(String agnClmSym) {
		this.agnClmSym = agnClmSym;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
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
	
	public String getStsTitle() {
		return stsTitle;
	}
	public void setStsTitle(String stsTitle) {
		this.stsTitle = stsTitle;
	}
	public String getFileYn() {
		return fileYn;
	}
	public void setFileYn(String fileYn) {
		this.fileYn = fileYn;
	}
	public String getFileSeq() {
		return fileSeq;
	}
	public void setFileSeq(String fileSeq) {
		this.fileSeq = fileSeq;
	}
	public void setRecuperationCostAccountMap(Map<String, SamVo> recuperationCostAccountMap,String table) {
		this.clmDcmtVsn		=	recuperationCostAccountMap.get("clmDcmtVsn").getValue();
		this.stsDcmtVsn		=	recuperationCostAccountMap.get("stsDcmtVsn").getValue();
		this.clmNum			=	recuperationCostAccountMap.get("clmNum").getValue();
		this.dcmtNum		=	recuperationCostAccountMap.get("dcmtNum").getValue();
		this.hspId			=	recuperationCostAccountMap.get("hspId").getValue();
		this.rcvInst		=	recuperationCostAccountMap.get("rcvInst").getValue();
		this.insrDivd		=	recuperationCostAccountMap.get("insrDivd").getValue();
		this.clmDivd		=	recuperationCostAccountMap.get("clmDivd").getValue();
		this.clmUntDivd		=	recuperationCostAccountMap.get("clmUntDivd").getValue();
		this.trtDivd		=	recuperationCostAccountMap.get("trtDivd").getValue();
		this.trtFldDivd		=	recuperationCostAccountMap.get("trtFldDivd").getValue();
		this.trtTyp			=	recuperationCostAccountMap.get("trtTyp").getValue();
		this.trtYrMnth		=	recuperationCostAccountMap.get("trtYrMnth").getValue();
		this.trtCnt			=	recuperationCostAccountMap.get("trtCnt").getValue();
		this.recpCst1		=	recuperationCostAccountMap.get("recpCst1").getValue();
		this.cinsrCost		=	recuperationCostAccountMap.get("cinsrCost").getValue();
		this.cinsrOverCst	=	recuperationCostAccountMap.get("cinsrOverCst").getValue();
		this.clmCst			=	recuperationCostAccountMap.get("clmCst").getValue();
		this.sprtCst		=	recuperationCostAccountMap.get("sprtCst").getValue();
		this.hadipCst		=	recuperationCostAccountMap.get("hadipCst").getValue();
		this.recpCst2		=	recuperationCostAccountMap.get("recpCst2").getValue();
		this.helthCst		=	recuperationCostAccountMap.get("helthCst").getValue();
		this.h100Cinsr		=	recuperationCostAccountMap.get("h100Cinsr").getValue();
		this.helthCinsrPrt	=	recuperationCostAccountMap.get("helthCinsrPrt").getValue();
		
		/*
		this.t100Cst1	=	recuperationCostAccountMap.get("t100Cst1").getValue();
		this.t100Cst2	=	recuperationCostAccountMap.get("t100Cst2").getValue();
		this.t100Cst3	=	recuperationCostAccountMap.get("t100Cst3").getValue();
		this.t100Cst4	=	recuperationCostAccountMap.get("t100Cst4").getValue();
		this.etc1	=	recuperationCostAccountMap.get("etc1").getValue();
		this.etc2	=	recuperationCostAccountMap.get("etc2").getValue();
		this.etc3	=	recuperationCostAccountMap.get("etc3").getValue();
		this.etc4	=	recuperationCostAccountMap.get("etc4").getValue();		
		
		this.dffTrtDayCnt	=	recuperationCostAccountMap.get("dffTrtDayCnt").getValue();
		this.dctrCnt		=	recuperationCostAccountMap.get("dctrCnt").getValue();
		this.dffIndx		=	recuperationCostAccountMap.get("dffIndx").getValue();
		this.dffMetrtCst	=	recuperationCostAccountMap.get("dffMetrtCst").getValue();*/
		
		this.clmDt			=	recuperationCostAccountMap.get("clmDt").getValue();
		this.clmNm			=	recuperationCostAccountMap.get("clmNm").getValue();
		this.wrtNm			=	recuperationCostAccountMap.get("wrtNm").getValue();
		this.wrtBrtDt		=	EncUtil.getSSEnc(recuperationCostAccountMap.get("wrtBrtDt").getValue(), getEncKey().getBytes());
		
		String yearFlag = recuperationCostAccountMap.get("wrtBrtDt").getValue().substring(6,7);
	    if(yearFlag == "1" || yearFlag == "2" || yearFlag == "5" || yearFlag == "6")	this.birthDy		=	"19"+recuperationCostAccountMap.get("wrtBrtDt").getValue().substring(0,6);
	    else if(yearFlag == "3" || yearFlag == "4" || yearFlag == "7" || yearFlag == "8")	this.birthDy		=	"20"+recuperationCostAccountMap.get("wrtBrtDt").getValue().substring(0,6);
	    else	this.birthDy		=	"18"+recuperationCostAccountMap.get("wrtBrtDt").getValue().substring(0,2); 
		this.chkAprvNum		=	recuperationCostAccountMap.get("chkAprvNum").getValue();
		this.agnClmSym		=	recuperationCostAccountMap.get("agnClmSym").getValue();
		this.ref			=	recuperationCostAccountMap.get("ref").getValue();
		this.msgYn			=	"N";
		this.table			=	this.table+table;
	}
	
	public String getResultVal() {
		StringBuffer resultVal = new StringBuffer();
		resultVal.append(getByte(this.clmDcmtVsn,3,0,0));
		resultVal.append(getByte(this.stsDcmtVsn,3,0,0));
		resultVal.append(getByte(this.clmNum,10,0,0));
		resultVal.append(getByte(this.dcmtNum,4,0,0));
		resultVal.append(getByte(this.hspId,8,0,0));
		
		resultVal.append(getByte(this.rcvInst,1,0,0));
		resultVal.append(getByte(this.insrDivd,1,0,0));
		resultVal.append(getByte(this.clmDivd,1,0,0));
		resultVal.append(getByte(this.clmUntDivd,1,0,0));
		resultVal.append(getByte(this.trtDivd,1,0,0));
		
		resultVal.append(getByte(this.trtFldDivd,1,0,0));
		resultVal.append(getByte(this.trtTyp,1,0,0));
		resultVal.append(getByte(this.trtYrMnth,6,0,0));
		resultVal.append(getByte(this.trtCnt,6,0,1));
		resultVal.append(getByte(this.recpCst1,12,0,1));
		
		resultVal.append(getByte(this.cinsrCost,12,0,1));
		resultVal.append(getByte(this.cinsrOverCst,12,0,1));
		resultVal.append(getByte(this.clmCst,12,0,1));
		resultVal.append(getByte(this.sprtCst,12,0,1));
		resultVal.append(getByte(this.hadipCst,12,0,1));
		
		resultVal.append(getByte(this.recpCst2,12,0,1));
		resultVal.append(getByte(this.helthCst,12,0,1));
		resultVal.append(getByte(this.h100Cinsr,12,0,1));
		resultVal.append(getByte(this.helthCinsrPrt,12,0,1));
		
/*		resultVal.append(getByte(this.dffTrtDayCnt,4,2,1));
		resultVal.append(getByte(this.dctrCnt,2,2,1));
		resultVal.append(getByte(this.dffIndx,1,7,1));
		resultVal.append(getByte(this.dffMetrtCst,12,0,1));*/
		
		resultVal.append(getByte(this.t100Cst1,12,0,1));
		resultVal.append(getByte(this.t100Cst2,12,0,1));
		resultVal.append(getByte(this.t100Cst3,12,0,1));
		resultVal.append(getByte(this.t100Cst4,12,0,1));
		resultVal.append(getByte(this.etc1,4,2,1));
		resultVal.append(getByte(this.etc2,2,2,1));
		resultVal.append(getByte(this.etc3,1,7,1));
		resultVal.append(getByte(this.etc4,12,0,1));
		
		resultVal.append(getByte(this.clmDt,8,0,0));
		resultVal.append(getByte(this.clmNm,20,0,0));
		resultVal.append(getByte(this.wrtNm,20,0,0));
		
		//resultVal.append(getByte(EncUtil.getSSDec(this.wrtBrtDt, getEncKey().getBytes()) ,13,0,0));
		resultVal.append(getByte(getWrtBrtDtEnc(),13,0,0));
		resultVal.append(getByte(this.chkAprvNum,35,0,0));
		resultVal.append(getByte(this.agnClmSym,5,0,0));
		resultVal.append(getByte(this.ref,1750,0,0));
		return resultVal.toString();
	}
	
	private String getByte(String val,int intSize,int floatSize,int type){
		
		String returnVal = val;
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
