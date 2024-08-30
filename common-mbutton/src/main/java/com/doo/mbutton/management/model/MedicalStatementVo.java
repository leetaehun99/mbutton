package com.doo.mbutton.management.model;

import java.util.Map;

import com.doo.mbutton.common.helper.EncUtil;
import com.doo.mbutton.common.model.GeneralVo;

public class MedicalStatementVo  extends GeneralVo{
	private long recpCstClmSeq;
	private String clmNum;
	private String stsSrlNum;
	private String detailDivd;
	private String dcmtNum;
	private String hspId;
	private String instNum;
	private String recpDivd;
	private String pubDivd;
	private String fxRtDivd;
	private String clmCd;
	private String clmRcptNum;
	private String clmStsSrlNum;
	private String clmCaseCd;
	private String clmFrstEntrDt;
	private String housrNm;
	private String grntdFcltIdNm;
	private String rcvrNm;
	private String rcvrJuminNum;
	private String birthDy;
	private String recpDayCnt;
	private String entrHospCnt;
	private String empt;
	private String entrHospWay;
	private String trtRslt;
	private String recpCst1;
	private String cinsrPrt;
	private String cinsrOverCst;
	private String clmCst;
	private String sprtCst;
	private String hadipCst;
	private String subPayCst;
	private String recpCst2;
	private String helthCst;
	private String drgOverCst;
	private String rcvrTotCst;
	private String h100Cinsr;
	private String helthCinsrPrt;
	private String table = "T_MEDICAL_STS";
	private String registerId;
	private String registDthms;
	private String updaterId;
	private String updateDthms;
	private String msgLev;
	
	private String divCd;
	private String recpCst1Check;
	private String cinsrPrtCheck;
	private String clmCstcheck;
	
	private String checkCd;
	private String checkMsg;
	private String sex;
	
	private String notify;
	private String notifySub;
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNotify() {
		return notify;
	}
	public void setNotify(String notify) {
		this.notify = notify;
	}
	public String getNotifySub() {
		return notifySub;
	}
	public void setNotifySub(String notifySub) {
		this.notifySub = notifySub;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCheckMsg() {
		return checkMsg;
	}
	public void setCheckMsg(String checkMsg) {
		this.checkMsg = checkMsg;
	}
	private Map<String, SamVo> medicalStatementMap;
	
	public String getMsgLev() {
		return msgLev;
	}
	public void setMsgLev(String msgLev) {
		this.msgLev = msgLev;
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
	public String getDetailDivd() {
		return detailDivd;
	}
	public void setDetailDivd(String detailDivd) {
		this.detailDivd = detailDivd;
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
	public String getInstNum() {
		return instNum;
	}
	public void setInstNum(String instNum) {
		this.instNum = instNum;
	}
	public String getRecpDivd() {
		return recpDivd;
	}
	public void setRecpDivd(String recpDivd) {
		this.recpDivd = recpDivd;
	}
	public String getPubDivd() {
		return pubDivd;
	}
	public void setPubDivd(String pubDivd) {
		this.pubDivd = pubDivd;
	}
	public String getFxRtDivd() {
		return fxRtDivd;
	}
	public void setFxRtDivd(String fxRtDivd) {
		this.fxRtDivd = fxRtDivd;
	}
	public String getClmCd() {
		return clmCd;
	}
	public void setClmCd(String clmCd) {
		this.clmCd = clmCd;
	}
	public String getClmRcptNum() {
		return clmRcptNum;
	}
	public void setClmRcptNum(String clmRcptNum) {
		this.clmRcptNum = clmRcptNum;
	}
	public String getClmStsSrlNum() {
		return clmStsSrlNum;
	}
	public void setClmStsSrlNum(String clmStsSrlNum) {
		this.clmStsSrlNum = clmStsSrlNum;
	}
	public String getClmCaseCd() {
		return clmCaseCd;
	}
	public void setClmCaseCd(String clmCaseCd) {
		this.clmCaseCd = clmCaseCd;
	}
	public String getClmFrstEntrDt() {
		return clmFrstEntrDt;
	}
	public void setClmFrstEntrDt(String clmFrstEntrDt) {
		this.clmFrstEntrDt = clmFrstEntrDt;
	}
	public String getHousrNm() {
		/*String result = "";
		if(housrNm != null && housrNm != "" ){
			if(housrNm.length()>0){
				result = housrNm.substring(0, 1);
				for(int i=1; i<rcvrNm.length(); i++){
					result+="O";
				}
			}
		}
		return result;*/
		return housrNm;
	}
	public void setHousrNm(String housrNm) {
		this.housrNm = housrNm;
	}
	public String getGrntdFcltIdNm() {
		return grntdFcltIdNm;
	}
	public void setGrntdFcltIdNm(String grntdFcltIdNm) {
		this.grntdFcltIdNm = grntdFcltIdNm;
	}
	public String getRcvrNm() {
		/*String result = "";
		if(rcvrNm != null && rcvrNm != "" ){
			if(rcvrNm.length()>0){
				result = rcvrNm.substring(0, 1);
				for(int i=1; i<rcvrNm.length(); i++){
					result+="O";
				}
			}
		}
		return result;*/
		return rcvrNm;
	}
	public void setRcvrNm(String rcvrNm) {
		this.rcvrNm = rcvrNm;
	}
	public String getRcvrJuminNum() {
		return this.rcvrJuminNum;
	}
	public String getRcvrJuminNumEnc() {
		return EncUtil.getSSDec(this.rcvrJuminNum,getEncKey().getBytes());
	}
	public String getBirthDy() {
		return birthDy;
	}
	public void setBirthDy(String birthDy) {
		this.birthDy = birthDy;
	}
	public void setRcvrJuminNum(String rcvrJuminNum) {
		this.rcvrJuminNum = rcvrJuminNum;
	}
	public String getRecpDayCnt() {
		return recpDayCnt;
	}
	public void setRecpDayCnt(String recpDayCnt) {
		this.recpDayCnt = recpDayCnt;
	}
	public String getEntrHospCnt() {
		return entrHospCnt;
	}
	public void setEntrHospCnt(String entrHospCnt) {
		this.entrHospCnt = entrHospCnt;
	}
	public String getEmpt() {
		return empt;
	}
	public void setEmpt(String empt) {
		this.empt = empt;
	}
	public String getEntrHospWay() {
		return entrHospWay;
	}
	public void setEntrHospWay(String entrHospWay) {
		this.entrHospWay = entrHospWay;
	}
	public String getTrtRslt() {
		return trtRslt;
	}
	public void setTrtRslt(String trtRslt) {
		this.trtRslt = trtRslt;
	}
	public String getRecpCst1() {
		return recpCst1;
	}
	public void setRecpCst1(String recpCst1) {
		this.recpCst1 = recpCst1;
	}
	public String getCinsrPrt() {
		return cinsrPrt;
	}
	public void setCinsrPrt(String cinsrPrt) {
		this.cinsrPrt = cinsrPrt;
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
	public String getSubPayCst() {
		return subPayCst;
	}
	public void setSubPayCst(String subPayCst) {
		this.subPayCst = subPayCst;
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
	public String getDrgOverCst() {
		return drgOverCst;
	}
	public void setDrgOverCst(String drgOverCst) {
		this.drgOverCst = drgOverCst;
	}
	public String getRcvrTotCst() {
		return rcvrTotCst;
	}
	public void setRcvrTotCst(String rcvrTotCst) {
		this.rcvrTotCst = rcvrTotCst;
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
	public String getDivCd() {
		return divCd;
	}
	public void setDivCd(String divCd) {
		this.divCd = divCd;
	}
	public String getRecpCst1Check() {
		return recpCst1Check;
	}
	public void setRecpCst1Check(String recpCst1Check) {
		this.recpCst1Check = recpCst1Check;
	}
	public String getCinsrPrtCheck() {
		return cinsrPrtCheck;
	}
	public void setCinsrPrtCheck(String cinsrPrtCheck) {
		this.cinsrPrtCheck = cinsrPrtCheck;
	}
	public String getClmCstcheck() {
		return clmCstcheck;
	}
	public void setClmCstcheck(String clmCstcheck) {
		this.clmCstcheck = clmCstcheck;
	}
	public String getCheckCd() {
		return checkCd;
	}
	public void setCheckCd(String checkCd) {
		this.checkCd = checkCd;
	}
	public void setMedicalStatementMap(Map<String, SamVo> medicalStatementMap,String table) {
		this.clmNum			=	medicalStatementMap.get("clmNum").getValue();
		this.stsSrlNum		=	medicalStatementMap.get("stsSrlNum").getValue();
		this.detailDivd		=	medicalStatementMap.get("detailDivd").getValue();
		this.dcmtNum		=	medicalStatementMap.get("dcmtNum").getValue();
		this.hspId			=	medicalStatementMap.get("hspId").getValue();
		this.instNum		=	medicalStatementMap.get("instNum").getValue();
		this.recpDivd		=	medicalStatementMap.get("recpDivd").getValue();
		this.pubDivd		=	medicalStatementMap.get("pubDivd").getValue();
		this.fxRtDivd		=	medicalStatementMap.get("fxRtDivd").getValue();
		this.clmCd			=	medicalStatementMap.get("clmCd").getValue();
		this.clmRcptNum		=	medicalStatementMap.get("clmRcptNum").getValue();
		this.clmStsSrlNum	=	medicalStatementMap.get("clmStsSrlNum").getValue();
		this.clmCaseCd		=	medicalStatementMap.get("clmCaseCd").getValue();
		this.clmFrstEntrDt	=	medicalStatementMap.get("clmFrstEntrDt").getValue();

		this.housrNm	= medicalStatementMap.get("housrNm").getValue();
		
		this.grntdFcltIdNm	=	"";
		
		this.rcvrNm	= medicalStatementMap.get("rcvrNm").getValue();
		
		this.rcvrJuminNum	=	"";
	   
		String yearFlag = medicalStatementMap.get("rcvrJuminNum").getValue().substring(6,7);
		if("1".equals(yearFlag) || "3".equals(yearFlag)  || "5".equals(yearFlag) || "7".equals(yearFlag) || "9".equals(yearFlag))	this.sex="Y";//남자
		else this.sex="N";//여자
		
	    if("1".equals(yearFlag) || "2".equals(yearFlag)  || "5".equals(yearFlag) || "6".equals(yearFlag) )	this.birthDy		=	"19"+medicalStatementMap.get("rcvrJuminNum").getValue().substring(0,6);
	    else if("3".equals(yearFlag) || "4".equals(yearFlag)  || "7".equals(yearFlag) || "8".equals(yearFlag))	this.birthDy		=	"20"+medicalStatementMap.get("rcvrJuminNum").getValue().substring(0,6);
	    else	this.birthDy		=	"18"+medicalStatementMap.get("rcvrJuminNum").getValue().substring(0,6); 
	    
		this.recpDayCnt		=	medicalStatementMap.get("recpDayCnt").getValue();
		this.entrHospCnt	=	medicalStatementMap.get("entrHospCnt").getValue();
		this.empt			=	medicalStatementMap.get("empt").getValue();
		this.entrHospWay	=	medicalStatementMap.get("entrHospWay").getValue();
		this.trtRslt		=	medicalStatementMap.get("trtRslt").getValue();
		this.recpCst1		=	medicalStatementMap.get("recpCst1").getValue();
		this.cinsrPrt		=	medicalStatementMap.get("cinsrPrt").getValue();
		this.cinsrOverCst	=	medicalStatementMap.get("cinsrOverCst").getValue();
		this.clmCst			=	medicalStatementMap.get("clmCst").getValue();
		this.sprtCst		=	medicalStatementMap.get("sprtCst").getValue();
		this.hadipCst		=	medicalStatementMap.get("hadipCst").getValue();
		this.subPayCst		=	medicalStatementMap.get("subPayCst").getValue();
		this.recpCst2		=	medicalStatementMap.get("recpCst2").getValue();
		this.helthCst		=	medicalStatementMap.get("helthCst").getValue();
		this.drgOverCst		=	medicalStatementMap.get("drgOverCst").getValue();
		this.rcvrTotCst		=	medicalStatementMap.get("rcvrTotCst").getValue();
		this.h100Cinsr		=	medicalStatementMap.get("h100Cinsr").getValue();
		this.helthCinsrPrt	=	medicalStatementMap.get("helthCinsrPrt").getValue();
		
		this.table			=	this.table+table;
	}
	
	public String getResultVal() {
		StringBuffer resultVal = new StringBuffer();
		resultVal.append(getByte(this.clmNum,10,0,0));
		resultVal.append(getByte(this.stsSrlNum,5,0,0));
		resultVal.append(getByte(this.detailDivd,1,0,0));
		resultVal.append(getByte(this.dcmtNum,4,0,0));
		resultVal.append(getByte(this.hspId,8,0,0));
		
		resultVal.append(getByte(this.instNum,11,0,0));
		resultVal.append(getByte(this.recpDivd,1,0,0));
		resultVal.append(getByte(this.pubDivd,1,0,0));
		resultVal.append(getByte(this.fxRtDivd,1,0,0));
		resultVal.append(getByte(this.clmCd,1,0,0));
		
		resultVal.append(getByte(this.clmRcptNum,7,0,0));
		resultVal.append(getByte(this.clmStsSrlNum,5,0,0));
		resultVal.append(getByte(this.clmCaseCd,2,0,0));
		resultVal.append(getByte(this.clmFrstEntrDt,8,0,0));
		resultVal.append(getByte(this.housrNm,20,0,0));
		
		resultVal.append(getByte(this.grntdFcltIdNm,20,0,0));
		resultVal.append(getByte(this.rcvrNm,20,0,0));
		//resultVal.append(getByte(EncUtil.getSSDec(this.rcvrJuminNum, getEncKey().getBytes()),13,0,0));
		resultVal.append(getByte(getRcvrJuminNumEnc(),13,0,0));
		
		resultVal.append(getByte(this.recpDayCnt,3,0,1));
		resultVal.append(getByte(this.entrHospCnt,3,0,1));
		
		resultVal.append(getByte(this.empt,31,0,0));
		resultVal.append(getByte(this.entrHospWay,2,0,0));
		resultVal.append(getByte(this.trtRslt,1,0,1));
		resultVal.append(getByte(this.recpCst1,10,0,1));
		resultVal.append(getByte(this.cinsrPrt,10,0,1));
		
		resultVal.append(getByte(this.cinsrOverCst,10,0,1));
		resultVal.append(getByte(this.clmCst,10,0,1));
		resultVal.append(getByte(this.sprtCst,10,0,1));
		resultVal.append(getByte(this.hadipCst,10,0,1));
		resultVal.append(getByte(this.subPayCst,10,0,1));
		
		resultVal.append(getByte(this.recpCst2,10,0,1));
		resultVal.append(getByte(this.helthCst,10,0,1));
		resultVal.append(getByte(this.drgOverCst,10,0,1));
		resultVal.append(getByte(this.rcvrTotCst,10,0,1));
		resultVal.append(getByte(this.h100Cinsr,10,0,1));
		resultVal.append(getByte(this.helthCinsrPrt,10,0,1));
		
		return resultVal.toString();
	}
	
	private String getByte(String val,int intSize,int floatSize,int type){
		
		String returnVal = val;
		int size = intSize+floatSize;
		if(returnVal!=null && returnVal != ""){
			try {
				String preFixVal="";
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
		}
		
		return returnVal;
	}
	public Map<String, SamVo> getMedicalStatementMap() {
		return medicalStatementMap;
	}
	public void setMedicalStatementMap(Map<String, SamVo> medicalStatementMap) {
		this.medicalStatementMap = medicalStatementMap;
	}
}
