package com.doo.mbutton.common.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doo.mbutton.interfaces.mapper.StatementMapper;
import com.doo.mbutton.interfaces.model.DiseaseStatementVo;
import com.doo.mbutton.interfaces.model.ErrorCheckVo;
import com.doo.mbutton.interfaces.model.KkNotifyVo;
import com.doo.mbutton.interfaces.model.PrescriptnStatementVo;
import com.doo.mbutton.interfaces.model.SpecificDetailVo;
import com.doo.mbutton.interfaces.model.StatementVo;
import com.doo.mbutton.interfaces.model.TreatmentStatementVo;

public class StatementNotifyCheck {
	
	
	@Autowired
	StatementMapper statementMapper;
	// Logger
	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	
	private static final String[] ADULT = {"","101","050"};
	private static final String[] CHILD = {"300","310","350"};
	private static final String[] CAST   = {"T6010","T6020","T6030","T6040","T6051","T6052","T6060","T6061","T6063","T6070","T6080","T6110","T6120"};
	private static final String[] CAST_EX= {"T6020300","T6020310","T6020350","T6030300","T6030310","T6030350"};
	private static final String[] SPLINT = {"T6151","T6152","T6153","T6154"};
	private static final String[] C1  = {"K8002004","K8002005","K8003004","K8003005","K8005004","K8005005"};//석고 CAST
	
	private static final String[] R1  = {"K8200001","K8204002","K8214004","K8201001","K8205002","K8208001","K8208004","K8202001","K8206002","K8209001","K8209004","K8210004","K8212005","K8211001","K8211004"};
	//스프린트
	private static final String[] S1  = {"K8309004","K8310204","K8314204","K8316004"};
	//롤 스프린트
	private static final String[] RS  = {"K8203031","K8400004","K8400008","K8400009","K8400010","K8400011","K8400012","K8400013","K8400014","K8400015","K8400016","K8400021","K8400022","K8400023","K8400024","K8400026","K8400027","K8400028","K8400029","K8400033","K8400034","K8400100","K8400104","K8400108","K8400110","K8400112","K8400113","K8400121","K8400122","K8400124","K8400126","K8400134","K8400204","K8400210","K8400213","K8400221","K8400222","K8400234","K8400313","K8400334","K8400434","K8400534","K8204031","K8204036","K8402001","K8402004","K8402006","K8402007","K8402008","K8402009","K8402010","K8402011","K8402012","K8402013","K8402014","K8402015","K8402016","K8402021","K8402022","K8402023","K8402024","K8402025","K8402026","K8402027","K8402028","K8402029","K8402033","K8402034","K8402037","K8402100","K8402104","K8402110","K8402112","K8402113","K8402121","K8402122","K8402124","K8402126","K8402133","K8402134","K8402204","K8402210","K8402213","K8402221","K8402222","K8402234","K8402313","K8402334","K8402434","K8402534","K8205031","K8205036","K8403001","K8403004","K8403006","K8403007","K8403008","K8403009","K8403010","K8403011","K8403012","K8403013","K8403014","K8403015","K8403016","K8403021","K8403022","K8403023","K8403024","K8403025","K8403026","K8403027","K8403028","K8403029","K8403033","K8403034","K8403037","K8403100","K8403104","K8403110","K8403112","K8403113","K8403121","K8403122","K8403124","K8403126","K8403133","K8403134","K8403204","K8403210","K8403213","K8403221","K8403222","K8403234","K8403313","K8403334","K8403434","K8403534","K8206031","K8206036","K8401244","K8404004","K8404006","K8404007","K8404008","K8404009","K8404010","K8404011","K8404012","K8404013","K8404014","K8404015","K8404016","K8404021","K8404022","K8404023","K8404024","K8404025","K8404026","K8404027","K8404028","K8404029","K8404033","K8404034","K8404037","K8404100","K8404104","K8404110","K8404112","K8404113","K8404121","K8404122","K8404126","K8404133","K8404134","K8404204","K8404210","K8404213","K8404221","K8404222","K8404234","K8404313","K8404334","K8404434","K8404534"};

	//주사제 체크
	private static final String[] KK = {"KK010","KK020","KK054","MIX","KK090","KK062","KK061","KK058","KK051","KK052","KK053","KK032","KK033","EXT","KK155","KK156","KK151"};


	
	//입원 구분 코드
	private static final String[] H1 = {"AB400","AF400"};
	/*
	 * 진료자 나이
	 */
	private int getAge(String age,String dt){
		int aged=0;
		try{
			//진료일
			int sy = Integer.parseInt(dt.substring(0, 4));
			int sm = Integer.parseInt(dt.substring(4, 6));
			int sd = Integer.parseInt(dt.substring(6, 8));
			//환자나이
			int dy = Integer.parseInt(age.substring(0, 4));
			int dm = Integer.parseInt(age.substring(4, 6));
			int dd = Integer.parseInt(age.substring(6, 8));
			
			aged = (dm<=sm) ?  (dm==sm) ?  (dd<=sd) ?  sy-dy:sy-dy-1:sy-dy-1:sy-dy-1;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return aged;
	}
	
	/*심사지침*/
	public void notifyCheck(StatementVo statementVo, ErrorCheckVo errorCheckVo){
		
		long start = System.currentTimeMillis(); // long start = newDate().getTime();
		String ymd = statementVo.getTrtStartDt();//생년월일
		String birthy = statementVo.getMedicalStatementVo().getBirthDy();
		int age = getAge(birthy,ymd );//나이
		int ptSize = 0; //약품 갯수
		
		//입원 여부 Check
		boolean checkHospital = false;
		
		List<DiseaseStatementVo> diseaseStatementList = statementVo.getDiseaseStatementList(); //상병 리스트
		List<TreatmentStatementVo> treatmentStatementList = statementVo.getTreatmentStatementList();//원내처방 리스트
		List<PrescriptnStatementVo> prescriptnStatementList = statementVo.getPrescriptnStatementList();//원외처방 리스트
		List<SpecificDetailVo> specificDetailList = statementVo.getSpecificDetailList();//특정내역 리스트
		ErrorCheckVo errorVo = null;
		System.out.println("이태훈= diseaseStatementList=="+diseaseStatementList.size());
		System.out.println("이태훈= treatmentStatementList=="+treatmentStatementList.size());
		System.out.println("이태훈= prescriptnStatementList=="+prescriptnStatementList.size());
		System.out.println("이태훈= specificDetailList=="+specificDetailList.size());
		
		List<ErrorCheckVo> errorCheckList = new ArrayList<ErrorCheckVo>();//에러 Check List
		
		List<String> diseaCdList = new ArrayList<String>(); //상병 리스트
		List<String> drugCdList1 = new ArrayList<String>(); //원외약 코드 List
		List<String> drugCdList2 = new ArrayList<String>(); //원내약 코드 List
		List<String> drugAllCdList = new ArrayList<String>(); //모든약 코드 List
		List<String> mainDrugCdList1= new ArrayList<String>(); //원외약 주성분 코드 List
		List<String> mainDrugCdList2= new ArrayList<String>(); //원내약 주성분 코드 List
		List<String> mainDrugAllCdList= new ArrayList<String>(); //주성분 코드 List
		
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		
		List<String> medicalCdList= new ArrayList<String>();//수가 코드 List
		List<String> trtCdList= new ArrayList<String>();//치료대 코드 List
		List<String> castCdList = new ArrayList<String>();// Cast
		List<String> oneDayCdList = new ArrayList<String>();//1일1투
		List<String> kkCdList = new ArrayList<String>();//주사제 List

		//유효 상병Check
		selectDiseaValidationCheck1(errorCheckList,errorCheckVo);
		//불완전 상병 Check
		selectDiseaValidationCheck2(errorCheckList,errorCheckVo);
		
		//상병 값 세팅
		setDiseaseCdList(diseaseStatementList,diseaCdList);
		//원외 처방 값 세팅
		setPrtCdList(prescriptnStatementList,specificDetailList,drugCdList1,drugAllCdList,mainDrugCdList1,mainDrugAllCdList,mapList);
		//원내 처방 값 세팅
		setTrtCdList(treatmentStatementList,specificDetailList,drugCdList2,drugAllCdList,mainDrugCdList2,mainDrugAllCdList,medicalCdList,trtCdList,castCdList,oneDayCdList,kkCdList,checkHospital,mapList);
		
		ptSize = prescriptnStatementList.size()+treatmentStatementList.size();
		
		System.out.println("mainDrugAllCdList" + mainDrugAllCdList.size());
		//Ext 저함략 배수 ,사용 연령,병용 금기
		if(mainDrugAllCdList.size()>0){
			//인증상병 체크
			if(mainDrugAllCdList.size()>0) diseaCheck(errorVo,diseaCdList,mainDrugAllCdList,errorCheckList,mapList);
			//연령별Check 저함량 JX999
			if(mainDrugAllCdList.size()>0) extCheck1(errorVo,mainDrugCdList1,mainDrugCdList2,mainDrugAllCdList,drugCdList2,errorCheckList,age,mapList);
			//병용금기Check
			if(mainDrugAllCdList.size()>0) extCheck2(errorVo,mainDrugAllCdList,errorCheckList,mapList);
			//단일금기Check
			if(mainDrugAllCdList.size()>0) extCheck6(errorVo,mainDrugAllCdList,errorCheckList,mapList);
		}

		//1일1투
		if(oneDayCdList.size()>0)  extCheck5(errorVo,oneDayCdList,checkHospital,errorCheckList);
		//주사제 수기산정
		if(kkCdList.size()>0) extCheck4(errorVo,kkCdList,medicalCdList,errorCheckList,mapList,errorCheckVo);
		else{
			for(int x=0; x<medicalCdList.size(); x++){
				for(int y=0; y<KK.length; y++){
					if(KK[y].equals(medicalCdList.get(x))) {
						errorCheckVo.setNotify("0015");
						errorCheckVo.setNotifySub("1");
						errorCheckVo.setCd(medicalCdList.get(x));
						errorCheckVo.setParam1(medicalCdList.get(x));
						ErrorCheckVo extVo  = statementMapper.selectNotifyExtCheck(errorCheckVo);
						errorCheckList.add(extVo);
					}
				}
			}
		}
		//치료대 
		if(trtCdList.size()>0){
			extCheck3(errorVo,errorCheckVo,castCdList,trtCdList,errorCheckList);
		}
		
		if (ptSize > 12) { 
			errorCheckVo.setNotify("0006");
			errorCheckVo.setNotifySub("1");
			errorCheckVo.setNotifySub("1");
			errorCheckVo.setCd("-");
		//	ErrorCheckVo extVo  = statementMapper.selectNotifyExtCheck(errorCheckVo);
		//	errorCheckList.add(extVo);
		}
		
		if (diseaCdList.size() > 12) {
			errorCheckVo.setNotify("0005");
			errorCheckVo.setNotifySub("1");
			errorCheckVo.setCd("-");
		//	ErrorCheckVo extVo  = statementMapper.selectNotifyExtCheck(errorCheckVo);
		//	errorCheckList.add(extVo);
		}
		
		//약가 기본 Msg
		if(drugAllCdList.size()>0){
		//	drugMsgCheck(errorVo,errorCheckVo.getStsSrlNum(),drugAllCdList,errorCheckList);	
		}
		//수가 기본 Msg
		if(medicalCdList.size()>0){
		//	medicalCheck(errorVo,errorCheckVo.getStsSrlNum(),medicalCdList,errorCheckList);				
		}
		
		
		//중복제거
		List<ErrorCheckVo> uniqueItems = new ArrayList<ErrorCheckVo>(new HashSet<ErrorCheckVo>(errorCheckList));		
		
		statementVo.setErrorCheckList(uniqueItems);
		long end = System.currentTimeMillis();
		logger.info("실행 시간 : " + ( end - start )/1000.0 );
	}
	
	/*
	 * 인증상병 체크
	 */
	private void selectDiseaValidationCheck1(List<ErrorCheckVo> errorCheckList,ErrorCheckVo errorCheckVo){
		errorCheckVo.setNotify("0007");
		errorCheckVo.setNotifySub("1");
		errorCheckList.addAll(errorCheckList.size(), statementMapper.selectDiseaValidationCheck1(errorCheckVo));
	}

	/*
	 * 불완전상병 체크
	 */
	private void selectDiseaValidationCheck2(List<ErrorCheckVo> errorCheckList,ErrorCheckVo errorCheckVo){
		errorCheckVo.setNotify("0008");
		errorCheckVo.setNotifySub("1");
		errorCheckList.addAll(errorCheckList.size(), statementMapper.selectDiseaValidationCheck2(errorCheckVo));
	}
	
	/*
	 * 원외 처방 값 세팅
	 */
	private void setDiseaseCdList(List<DiseaseStatementVo> diseaseStatementList,List<String> diseaseCdList){
		if(diseaseStatementList!=null){
			for(int x=0; x<diseaseStatementList.size(); x++){
				DiseaseStatementVo diseaVo = diseaseStatementList.get(x);
				diseaseCdList.add(diseaVo.getDiseaDivdSym());
			}
		}
	}
	
	/*
	 * 원외 처방 값 세팅
	 */
	private void setPrtCdList(List<PrescriptnStatementVo> prescriptnStatementList,List<SpecificDetailVo> specificDetailList,List<String> drugCdList,List<String> drugAllCdList,List<String> mainDrugCdList1,List<String> mainDrugCdList,List<Map<String,String>> mapList){
		if(prescriptnStatementList!=null){
			for(int x=0; x<prescriptnStatementList.size(); x++){
				PrescriptnStatementVo prtVo = prescriptnStatementList.get(x);
				Map<String, String> map = new HashMap<String, String>();
				drugCdList.add(prtVo.getCd());
				drugAllCdList.add(prtVo.getCd());
				mainDrugCdList1.add(prtVo.getMainDrugCd());
				mainDrugCdList.add(prtVo.getMainDrugCd());
				map.put("MODE", "N");
				map.put("TYPE", "3");
				map.put("DRUG_CD", prtVo.getCd());
				map.put("MAIN_DRUG_CD", prtVo.getMainDrugCd());
				map.put("LN_NUM1", prtVo.getLnNum());
				map.put("CNT",  (Float.parseFloat(prtVo.getDorseTotCnt()) * Float.parseFloat(prtVo.getDorseTotCnt()))+"");
				//특정내역
				for(int y=0; y<specificDetailList.size(); y++){
					if("2".equals(specificDetailList.get(y).getPrscrptnDivd())){
						if(prtVo.getLnNum().equals(specificDetailList.get(y).getLnNum())){
							map.put("LN_NUM2", specificDetailList.get(y).getLnNum());
							map.put("SPE_CD", specificDetailList.get(y).getSpeDetailDivd());
							break;
						}else{
							map.put("LN_NUM2", specificDetailList.get(y).getLnNum());
							map.put("SPE_CD", "-");
						}
					}
				}
				mapList.add(map);
			}
		}
	}

	/*
	 * 원내 처방 값 세팅
	 */
	private void setTrtCdList(List<TreatmentStatementVo> treatmentStatementList,List<SpecificDetailVo> specificDetailList,List<String> drugCdList,List<String> drugAllCdList,List<String> mainDrugCdList2,List<String> mainDrugCdList,List<String> medicalCdList,List<String> trtCdList,List<String> castCdList,List<String> oneDayCdList,List<String> kkCdList,boolean checkHospital,List<Map<String,String>> mapList){
		float kk010Cnt = 0;
		float kk020Cnt = 0;
		if(treatmentStatementList!=null){System.out.println("87213987129872319");
			checkHospital = hospitalizationCheck(treatmentStatementList);
			for(int x=0; x<treatmentStatementList.size(); x++){
				TreatmentStatementVo trtVo = treatmentStatementList.get(x);
				Map<String, String> map = new HashMap<String, String>();
				//특정내역
				map.put("MODE", "Y");
				map.put("TYPE", trtVo.getCdDivd());
				map.put("DRUG_CD", trtVo.getCd());
				map.put("MAIN_DRUG_CD", trtVo.getMainDrugCd());
				map.put("LN_NUM1", trtVo.getLnNum());
				map.put("CNT",  Float.parseFloat(trtVo.getDorseDayCnt())+"");
				//특정내역
				for(int y=0; y<specificDetailList.size(); y++){
					if("2".equals(specificDetailList.get(y).getPrscrptnDivd())){
						if(trtVo.getLnNum().equals(specificDetailList.get(y).getLnNum())){
							map.put("LN_NUM2", specificDetailList.get(y).getLnNum());
							map.put("SPE_CD", specificDetailList.get(y).getSpeDetailDivd()); 
							break;
						}else{
							map.put("LN_NUM2", specificDetailList.get(y).getLnNum());
							map.put("SPE_CD", "-");
						}
					}
				}
				mapList.add(map);
				
				if("1".equals(trtVo.getCdDivd())){ // 수가
					if(trtVo.getCd().indexOf("T60")>-1  || trtVo.getCd().indexOf("T61")>-1){//CAST,SPLINT
						castCdList.add(trtVo.getCd());
					}else{
						medicalCdList.add(trtVo.getCd());//수가 코드 CAST 제외

						if("KK010".equals(trtVo.getCd())) kk010Cnt+=Float.parseFloat(trtVo.getDorseDayCnt());
						if("KK020".equals(trtVo.getCd())) kk020Cnt+=Float.parseFloat(trtVo.getDorseDayCnt());
						
						if(trtVo.getExtCd()!=null && trtVo.getExtVal1()!=null){
							if(checkHospital){ //입원일경우
								if(trtVo.getExtVal2()!=null){
									if("KK010".equals(trtVo.getCd())){
										if(Float.parseFloat(trtVo.getExtVal2()) < kk010Cnt){
											oneDayCdList.add(trtVo.getCd());
										}
									}else if("KK020".equals(trtVo.getCd())){
										if(Float.parseFloat(trtVo.getExtVal2()) < kk020Cnt){
											oneDayCdList.add(trtVo.getCd());
										}
									}else{
										if(Float.parseFloat(trtVo.getExtVal2()) < Float.parseFloat(trtVo.getDorseDayCnt())){
											oneDayCdList.add(trtVo.getCd());
										}
									}
									
								}
							}else{//외래일경우
								if("KK010".equals(trtVo.getCd())){
									if(Float.parseFloat(trtVo.getExtVal1()) < kk010Cnt){
										oneDayCdList.add(trtVo.getCd());
									}
								}else if("KK020".equals(trtVo.getCd())){
									if(Float.parseFloat(trtVo.getExtVal1()) < kk020Cnt){
										oneDayCdList.add(trtVo.getCd());
									}
								}else{
									if(Float.parseFloat(trtVo.getExtVal1()) < Float.parseFloat(trtVo.getDorseDayCnt())){
										oneDayCdList.add(trtVo.getCd());
									}
								}
							}
						}
					} 
				}else if("3".equals(trtVo.getCdDivd())){ // 약가
					drugCdList.add(trtVo.getCd());
					drugAllCdList.add(trtVo.getCd());
					if(trtVo.getMainDrugCd()!=null && trtVo.getMainDrugCd()!=""){
						mainDrugCdList.add(trtVo.getMainDrugCd());
						mainDrugCdList2.add(trtVo.getMainDrugCd());
					}
					
					if("04".equals(trtVo.getSectNum1())) kkCdList.add(trtVo.getCd());
					
				}else if("8".equals(trtVo.getCdDivd())){ // 치료대
					if(	trtVo.getCd().indexOf("K800")>-1  || trtVo.getCd().indexOf("K810")>-1  || trtVo.getCd().indexOf("K820")>-1  || trtVo.getCd().indexOf("K821")>-1 || trtVo.getCd().indexOf("K830")>-1  
						|| trtVo.getCd().indexOf("K831")>-1  || trtVo.getCd().indexOf("K832")>-1  || trtVo.getCd().indexOf("K840")>-1  || trtVo.getCd().indexOf("K850")>-1  || trtVo.getCd().indexOf("K860")>-1  
						|| trtVo.getCd().indexOf("K861")>-1  ){//캐스트
						trtCdList.add(trtVo.getCd());//치료대 추가
					}
				}
			}
		}
	}
	
	/*
	 * 약 메세지 Check
	 */
	private void drugMsgCheck(ErrorCheckVo errorVo,String stsSrlNum ,List<String> drugAllCdList,List<ErrorCheckVo> errorCheckList){
		errorVo = new ErrorCheckVo();
		errorVo.setStsSrlNum(stsSrlNum);
		errorVo.setDrugAllCdList(drugAllCdList);
		errorCheckList.addAll(errorCheckList.size(),statementMapper.selectDrugMsgCheck(errorVo));
	}
	
	/*
	 * 수가 메세지 Check
	 */
	private void medicalCheck(ErrorCheckVo errorVo,String stsSrlNum ,List<String> medicalCdList,List<ErrorCheckVo> errorCheckList){
		errorVo = new ErrorCheckVo();
		errorVo.setStsSrlNum(stsSrlNum);
		errorVo.setMedicalCdList(medicalCdList);
		errorCheckList.addAll(errorCheckList.size(),statementMapper.selectMedicalMsgCheck(errorVo));
	}
	
	/*
	 * 인증상병 Check
	 */
	private void diseaCheck(ErrorCheckVo errorVo,List<String> diseaCdList,List<String> mainDrugAllCdList,List<ErrorCheckVo> errorCheckList,List<Map<String,String>> mapList){
		errorVo = new ErrorCheckVo();
		errorVo.setMainDrugAllCdList(mainDrugAllCdList);
		errorVo.setDiseaCdList(diseaCdList);
		List<ErrorCheckVo> list = new ArrayList<ErrorCheckVo>();
		if (mainDrugAllCdList != null && mainDrugAllCdList.size() > 0)
			list = statementMapper.selectDiseaCheck(errorVo);
		
		list = findSetDrugCd(list,mapList,"0");
		errorCheckList.addAll(errorCheckList.size(),list);
	}
	
	/*
	 * 연령별 저함량 배수Check
	 */
	private void extCheck1(ErrorCheckVo errorVo,List<String> mainDrugCdList1,List<String> mainDrugCdList2,List<String> mainDrugAllCdList,List<String> drugCdList,List<ErrorCheckVo> errorCheckList,int age,List<Map<String,String>> mapList){
		errorVo = new ErrorCheckVo();
		errorVo.setMainDrugCdList1(mainDrugCdList1);
		errorVo.setMainDrugCdList2(mainDrugCdList2);
		errorVo.setMainDrugAllCdList(mainDrugAllCdList);
		List<ErrorCheckVo> extErrorList = new ArrayList<ErrorCheckVo>();
		List<ErrorCheckVo> errorList = null;
		
		if (errorVo.getMainDrugAllCdList() != null && errorVo.getMainDrugAllCdList().size() > 0)
			errorList = statementMapper.selectNotifyExt(errorVo);
		
		if(errorList!=null){
			for(int i=0; i<errorList.size(); i++){
				ErrorCheckVo extVo = (ErrorCheckVo)errorList.get(i);
				if("0001".equals(extVo.getNotify()) && "C".equals(extVo.getExtDiv1())){	//A:주 B:개월 C:세, F:경구제,G:주사제
					//A:미만(미포함),B:이하(포함),C:초과(제외),D:이상(포함),E:미만이상,F:이하초과
					if("A".equals(extVo.getExtDiv2())){		
						//미만 사용금지
						if(age < Integer.parseInt(extVo.getVal())){
							extVo.setErrMsg(extVo.getErrMsg().replace("$[param1]", "("+extVo.getVal()+"세 미만 사용금지)"));
							extErrorList.add(extVo);
						}
					}else if("B".equals(extVo.getExtDiv2())){						//이하 사용금지
						if(age <= Integer.parseInt(extVo.getVal())){
							extVo.setErrMsg(extVo.getErrMsg().replace("$[param1]", "("+extVo.getVal()+"세 이하 사용금지)"));
							extErrorList.add(extVo);
						}
					}else if("C".equals(extVo.getExtDiv2())){						//초과 사용금지
						if(age > Integer.parseInt(extVo.getVal())){
							extVo.setErrMsg(extVo.getErrMsg().replace("$[param1]", "("+extVo.getVal()+"세 초과 사용금지)"));
							extErrorList.add(extVo);
						}
					}else if("D".equals(extVo.getExtDiv2())){						//이상 사용금지
						if(age >= Integer.parseInt(extVo.getVal())){
							extVo.setErrMsg(extVo.getErrMsg().replace("$[param1]", "("+extVo.getVal()+"세 이상 사용금지)"));
							extErrorList.add(extVo);
						}
					}else if("E".equals(extVo.getExtDiv2())){						//미만이상 사용금지
						String vals[] = extVo.getVal().split("-");
						if(age < Integer.parseInt(vals[0]) || age >= Integer.parseInt(vals[1])){
							extVo.setErrMsg(extVo.getErrMsg().replace("$[param1]", "("+vals[0]+" 미만 - "+vals[1]+"세 이상 사용금지)" ));
							extErrorList.add(extVo);
						}
					}else if("F".equals(extVo.getExtDiv2())){						//이하초과 사용금지
						String vals[] = extVo.getVal().split("-");
						if(age <= Integer.parseInt(vals[0]) || age > Integer.parseInt(vals[1])) {
							extVo.setErrMsg(extVo.getErrMsg().replace("$[param1]", "("+vals[0]+" 이하 - "+vals[1]+"세 초과 사용금지)" ));
							extErrorList.add(extVo);
						}
					}
				}else if("0002".equals(extVo.getNotify()) || "0003".equals(extVo.getNotify())){	//저함량 배수 의약품 검사결과
					
					for(int x=0; x<mapList.size(); x++){
						System.out.println(mapList.get(x).get("MODE"));
						System.out.println(mapList.get(x).get("TYPE"));
						System.out.println(mapList.get(x).get("SPE_CD")+" " +extVo.getSpecialCd());
						System.out.println(mapList.get(x).get("MAIN_DRUG_CD")+" "+ extVo.getMainDrugCd());
						if("Y".equals(mapList.get(x).get("MODE")) && "3".equals(mapList.get(x).get("TYPE")) && !extVo.getSpecialCd().equals(mapList.get(x).get("SPE_CD")) && extVo.getMainDrugCd().equals(mapList.get(x).get("MAIN_DRUG_CD"))){
							extErrorList.add(extVo);
						}
					}
					
				}else if("0014".equals(extVo.getNotify())){	//골대사성 의약품 검사결과
					
					for(int x=0; x<mapList.size(); x++){
						if("3".equals(mapList.get(x).get("TYPE")) &&  !extVo.getSpecialCd().equals(mapList.get(x).get("SPE_CD")) && extVo.getMainDrugCd().equals(mapList.get(x).get("MAIN_DRUG_CD"))){
							extErrorList.add(extVo);
						}
					}
					
				}else if("0016".equals(extVo.getNotify())){	//투여량 check
					
					for(int x=0; x<mapList.size(); x++){
						if("3".equals(mapList.get(x).get("TYPE")) && extVo.getMainDrugCd().equals(mapList.get(x).get("MAIN_DRUG_CD"))){
							if(Float.parseFloat(mapList.get(x).get("CNT")) > Float.parseFloat(extVo.getVal())){
								extVo.setErrMsg(extVo.getErrMsg().replace("$[param1]", (Float.parseFloat(extVo.getVal())/7)+""));
								extErrorList.add(extVo);
							}
						}
					}
					
				}
			}
			extErrorList = findSetDrugCd(extErrorList,mapList,"0");
			errorCheckList.addAll(errorCheckList.size(),extErrorList);
		}
	}
	
	/*
	 * 병용금기 Check
	 */
	private void extCheck2(ErrorCheckVo errorVo,List<String> mainDrugCdList,List<ErrorCheckVo> errorCheckList,List<Map<String,String>> mapList){
		errorVo = new ErrorCheckVo();
		errorVo.setMainDrugAllCdList(mainDrugCdList);
		List<ErrorCheckVo> list = statementMapper.selectCbCheck(errorVo);
		list = findSetDrugCd(list,mapList,"2");
		errorCheckList.addAll(errorCheckList.size(),list);
	}
	
	/*
	 * Cast,Split Check
	 */
	private void extCheck3(ErrorCheckVo errorVo,ErrorCheckVo errorCheckVo,List<String> castCdList,List<String> trtCdList,List<ErrorCheckVo> errorCheckList){
		errorVo = new ErrorCheckVo();
		errorVo.setClmNum(errorCheckVo.getClmNum() );
		errorVo.setHspId(errorCheckVo.getHspId());
		errorVo.setRecpCstClmSeq(errorCheckVo.getRecpCstClmSeq());
		errorVo.setStsSrlNum(errorCheckVo.getStsSrlNum());
		List<ErrorCheckVo> extErrorList = new ArrayList<ErrorCheckVo>();
		for(int x=0; x<castCdList.size(); x++){
			String type = medicalTypeCheck(castCdList.get(x));
			logger.info("TYPE = "+type);
			//캐스트 , 스플리트 구분
			if("C".equals(type)){
				errorVo.setNotify("0009");
				if(trtCdList.size()>0){
					if("CB".equals(trtTypeCheck(trtCdList,type))){//CAST 석고붕대
						errorVo.setNotifySub("1");
						errorVo.setExtDiv1("B");
						errorVo.setExtDiv2("0");
					}else{//CAST 성인 소아
						errorVo.setNotifySub("2");
						errorVo.setExtDiv1("C");
						if("Y".equals(medicalTypeCheckSub(castCdList.get(x),type))){
							errorVo.setExtDiv2("9");
						}else{
							errorVo.setExtDiv2("1");
						}
					}
					errorVo.setCd(castCdList.get(x));
					errorVo.setTrtCdList(trtCdList);
					
					List<ErrorCheckVo> temp = validationCheck(errorVo);
					if(temp.size()==0){
						extErrorList.addAll(extErrorList.size(),countCheck(errorVo));
					}else{
						extErrorList.addAll(extErrorList.size(),temp);
					}
					
				}
			}else if("S".equals(type)){
				if(trtCdList.size()>0){
					String sTemp = trtTypeCheck(trtCdList,type);
					logger.info("sTemp = " +sTemp);
					if("RB".equals(sTemp)){//석고 Roll Splint
						errorVo.setNotify("0010");
						errorVo.setNotifySub("1");
						errorVo.setExtDiv1("B");
						errorVo.setExtDiv2("1");
					}else if("RS".equals(sTemp)){//롤일경우
						errorVo.setNotify("0010");
						errorVo.setNotifySub("2");
						errorVo.setExtDiv1("R");
						errorVo.setExtDiv2("1");
					}else if("S154".equals(sTemp)){//석고 Splint T154일경우
						errorVo.setNotify("0011");
						errorVo.setNotifySub("1");
						errorVo.setExtDiv1("B");
						errorVo.setExtDiv2("T154");
					}else if("SB".equals(sTemp)){//석고 Splint
						errorVo.setNotify("0011");
						errorVo.setNotifySub("1");
						errorVo.setExtDiv1("B");
						errorVo.setExtDiv2("0");
					}else{//일반
						
						errorVo.setNotify("0011");
						errorVo.setNotifySub("2");
						String ageGroup = medicalTypeCheckSub(castCdList.get(x),"");
						String checkSub = medicalTypeCheckSub(castCdList.get(x),type);
						
						errorVo.setExtDiv1("S");
						if("A".equals(ageGroup)){
							errorVo.setMode("A");
							if("T6151".equals(checkSub)){//5
								errorVo.setExtDiv2("5");
							}else if("T6152".equals(checkSub)){//6
								errorVo.setExtDiv2("6");
							}else if("T6153".equals(checkSub)){//7
								errorVo.setExtDiv2("7");
							}else if("T6154".equals(checkSub)){//8
								errorVo.setExtDiv2("8");
							}
						}else if("C".equals(ageGroup)){
							errorVo.setMode("C");
							if("T6151".equals(checkSub)){//5
								errorVo.setExtDiv2("5");
							}else if("T6152".equals(checkSub)){//6
								errorVo.setExtDiv2("6");
							}else if("T6153".equals(checkSub)){//7
								errorVo.setExtDiv2("7");
							}else if("T6154".equals(checkSub)){//8
								errorVo.setExtDiv2("8");
							}
						}
						
					}
					
					errorVo.setCd(castCdList.get(x));
					errorVo.setTrtCdList(trtCdList);
					
					List<ErrorCheckVo> temp = validationCheck(errorVo);
					if(temp.size()==0){
						extErrorList.addAll(extErrorList.size(),countCheck(errorVo));
					}else{
						extErrorList.addAll(extErrorList.size(),temp);
					}
				}
			}
		}
		
		

		errorCheckList.addAll(errorCheckList.size(),extErrorList);
	}

	/*
	 * 주사제 체크
	 */
	private void extCheck4(ErrorCheckVo errorVo,List<String> kkCdList,List<String> medicalCdList,List<ErrorCheckVo> errorCheckList,List<Map<String,String>> mapList,ErrorCheckVo errorCheckVo){
		errorVo = new ErrorCheckVo();
		errorVo.setClmNum(errorCheckVo.getClmNum() );
		errorVo.setHspId(errorCheckVo.getHspId());
		errorVo.setRecpCstClmSeq(errorCheckVo.getRecpCstClmSeq());
		errorVo.setStsSrlNum(errorCheckVo.getStsSrlNum());
		errorVo.setDrugCdList(kkCdList);
		List<ErrorCheckVo> extErrorList = new ArrayList<ErrorCheckVo>();
		List<KkNotifyVo> kkNotifyList = statementMapper.selectKKList(errorVo);
		List<KkNotifyVo> kkList = statementMapper.selectKkSum(errorVo);
		
		boolean check = false;
		int m4 = 1;
		int m5 = 1;
		int m6 = 1;
		int m8 = 1;
		int m9 = 1;
		int m10 = 1;
		
		int mCnt=0;
		int dCnt=0;
		
		for(int q=0; q<kkList.size(); q++){
			mCnt+=Integer.parseInt(kkList.get(q).getVal());
			if(Integer.parseInt(kkList.get(q).getVal())>1){
				if("KK090".equals(kkList.get(q).getDrugCd())) m4 = Integer.parseInt(kkList.get(q).getVal());
				if("KK062".equals(kkList.get(q).getDrugCd())) m5 = Integer.parseInt(kkList.get(q).getVal());
				if("KK061".equals(kkList.get(q).getDrugCd())) m6 = Integer.parseInt(kkList.get(q).getVal());
				if("KK051".equals(kkList.get(q).getDrugCd())) m8 = Integer.parseInt(kkList.get(q).getVal());
				if("KK052".equals(kkList.get(q).getDrugCd())) m9 = Integer.parseInt(kkList.get(q).getVal());
				if("KK053".equals(kkList.get(q).getDrugCd())) m10 = Integer.parseInt(kkList.get(q).getVal());
			}
		}
		
		
		errorVo = new ErrorCheckVo();
		List<Integer> kVal =null;
		List<Integer> pVal =null;
		if(kkNotifyList.size()>0){
			for(int i=0; i<kkNotifyList.size(); i++) {
				dCnt+=Integer.parseInt(kkNotifyList.get(i).getVal());
				//644903700	KK020
				
				KkNotifyVo kkNotifyVo = (KkNotifyVo)kkNotifyList.get(i);
				for(int h=0; h<Integer.parseInt(kkNotifyVo.getVal()); h++){
					kVal = new ArrayList<Integer>();
					pVal = new ArrayList<Integer>();
					if("Y".equals(kkNotifyVo.getEtc1()) && !kkNotifyVo.getDrugCd().equals("644903700")){//KK010
						kVal.add(0);
						pVal.add(0);
					}
					if("Y".equals(kkNotifyVo.getEtc2())){//KK020
						kVal.add(1);pVal.add(1);
					}
					if("Y".equals(kkNotifyVo.getEtc3())){//KK054
						kVal.add(2);pVal.add(2);
					}
					if("Y".equals(kkNotifyVo.getEtc4())){//MIX
						kVal.add(3);pVal.add(3);
						kVal.add(8);pVal.add(8);
						kVal.add(9);pVal.add(9);
						kVal.add(10);pVal.add(10);
					}
					//8 9 10 
					if("Y".equals(kkNotifyVo.getEtc5())){//KK090
						pVal.add(4); 
						if(m4 > 0)	kVal.add(4); 
					}
					
					if("Y".equals(kkNotifyVo.getEtc6())){//KK062
						pVal.add(5); 
						if(m5 > 0)	kVal.add(5); 
					}
					
					if("Y".equals(kkNotifyVo.getEtc7())){//KK061
						pVal.add(6); 
						if(m6 > 0)	kVal.add(6); 
					}
					
					if("Y".equals(kkNotifyVo.getEtc8())){//KK058
						kVal.add(7);pVal.add(7);
					}
					
					if("Y".equals(kkNotifyVo.getEtc9())){//KK051
						pVal.add(8); 
						if( m8 > 0)	kVal.add(8); 
					}
					
					if("Y".equals(kkNotifyVo.getEtc10())){//KK052
						pVal.add(9); 
						if(m9 > 0)	kVal.add(9); 
					}
					
					if("Y".equals(kkNotifyVo.getEtc11())){//KK053
						pVal.add(10); 
						if(m10 > 0)	kVal.add(10); 
					}
					
					if("Y".equals(kkNotifyVo.getEtc12())){//KK032
						kVal.add(11);pVal.add(11);
					}
					if("Y".equals(kkNotifyVo.getEtc13())){//KK033
						kVal.add(12);	pVal.add(12);
					}
					//if("Y".equals(kkNotifyVo.getEtc14())) kVal.add(13); //EXT
					if("Y".equals(kkNotifyVo.getEtc15())){//KK155
						kVal.add(14);pVal.add(14);
					}
					if("Y".equals(kkNotifyVo.getEtc16())){//KK156
						kVal.add(15);pVal.add(15);
					}
					if("Y".equals(kkNotifyVo.getEtc17())){//KK155
						kVal.add(16);pVal.add(16);
					}
					for(int x=0; x<kkList.size(); x++){
						if(!"KK".equals(kkList.get(x).getDrugCd().substring(0, 2))) continue;
						if(check) break;
						for(int y=0; y<kVal.size(); y++){
							if(KK[kVal.get(y)].equals(kkList.get(x).getDrugCd())) {
								if(KK[4].equals(kkList.get(x).getDrugCd()) && m4>0) m4-=1;
								if(KK[5].equals(kkList.get(x).getDrugCd()) && m5>0) m5-=1;
								if(KK[6].equals(kkList.get(x).getDrugCd()) && m6>0) m6-=1;
								if(KK[8].equals(kkList.get(x).getDrugCd()) && m8>0) m8-=1;
								if(KK[9].equals(kkList.get(x).getDrugCd()) && m9>0) m9-=1;
								if(KK[10].equals(kkList.get(x).getDrugCd()) && m10>0) m10-=1;
								
								logger.info("kVal = "+KK[kVal.get(y)]);
								check = true; 
								break;
							}
						}
					}

					if(!check){
						String frontText="";
						errorVo.setNotify(kkNotifyVo.getExtNotify());
						errorVo.setNotifySub(kkNotifyVo.getExtNotifySub());
						errorVo.setCd(kkNotifyVo.getDrugCd());
						
						for(int k=0; k<pVal.size(); k++) {
							if(k==0)	frontText = "[";
							if(pVal.size()-1==k)frontText +=KK[pVal.get(k)] +"]";
							else frontText += KK[pVal.get(k)]+",";
						}
						errorVo.setParam1(frontText);
						extErrorList = statementMapper.selectNotifyKkExtCheck(errorVo);
						errorCheckList.addAll(errorCheckList.size(),extErrorList);
						
					}
					check = false; 
				}
			}
		}
		if(kkNotifyList.size()>0){
			if(mCnt>dCnt){
				errorVo.setNotify(kkNotifyList.get(0).getExtNotify());
				errorVo.setNotifySub(kkNotifyList.get(0).getExtNotifySub());
				errorVo.setCd("-");
				errorVo.setParam1("수기료["+mCnt+"]개, 주사제["+dCnt+"]개 수기료가 주사제보다 많을수 없습니다.");
				ErrorCheckVo extVo  = statementMapper.selectNotifyExtCheck(errorVo);
				errorCheckList.add(extVo);
			}
		}
	}
	
	/*
	 * 1일1투 체크
	 */
	private void extCheck5(ErrorCheckVo errorVo,List<String> oneDayCdList,boolean checkHospital,List<ErrorCheckVo> errorCheckList){
		if(oneDayCdList.size()>0){
			List<ErrorCheckVo> extErrorList = new ArrayList<ErrorCheckVo>();
			errorVo = new ErrorCheckVo();
			ErrorCheckVo errorCheckVo = new ErrorCheckVo();
			errorCheckVo.setNotify("0013");
			errorCheckVo.setNotifySub("1");
			errorCheckVo.setDrugCdList(oneDayCdList);
			if(checkHospital) errorCheckVo.setMode("Y");
			else errorCheckVo.setMode("N");
			extErrorList = statementMapper.selectOneDayExtCheck(errorCheckVo);
			for(int i=0; i<extErrorList.size(); i++ ){
				if("0".equals(extErrorList.get(i).getVal())) extErrorList.get(i).setErrMsg("입원 전용 코드입니다.");
			}
			errorCheckList.addAll(errorCheckList.size(),extErrorList);
		}
	}
	
	/*
	 * 단일금기 Check
	 */
	private void extCheck6(ErrorCheckVo errorVo,List<String> mainDrugAllCdList,List<ErrorCheckVo> errorCheckList,List<Map<String,String>> mapList){
		errorVo = new ErrorCheckVo();
		errorVo.setMainDrugAllCdList(mainDrugAllCdList);
		List<ErrorCheckVo> list = statementMapper.selectCcCheck(errorVo);
		
		list = findSetDrugCd(list,mapList,"6");
		errorCheckList.addAll(errorCheckList.size(),list);
	}
	
	/*
	 * Cast Splint 구분
	 */
	private String medicalTypeCheck(String medicalCd){
		
			for(int x=0; x<CAST.length;x++){
				if(medicalCd.indexOf(CAST[x])>-1) return "C";
			}
			
			for(int x=0; x<SPLINT.length;x++){
				if(medicalCd.indexOf(SPLINT[x])>-1) return "S";
			}
		
		
		return "";
	}
	
	/*
	 *치료대 타입 구분
	 */
	private String trtTypeCheck(List<String> trtList,String type){
				 
		
		if("C".equals(type)){
			for(int i=0; i<trtList.size();i++){
				for(int j=0; j<C1.length;j++){
					if(C1[j].equals(trtList.get(i))) return "CB"; //캐스트 붕대
				}
			}
		}else{
			
			//석고 Roll Splint
			for(int i=0; i<trtList.size();i++){
				for(int j=0; j<R1.length;j++){
					if(R1[j].equals(trtList.get(i))) return "RB"; //롤 스프린트 붕대
				}
			}

			//석고 Splint T154
			for(int i=0; i<trtList.size();i++){
				for(int j=0; j<S1.length;j++){
					if(S1[j].equals(trtList.get(i))) return "S154"; //
				}
			}
			
			//석고 Splint
			for(int i=0; i<trtList.size();i++){
				for(int j=0; j<C1.length;j++){
					if(C1[j].equals(trtList.get(i))) return "SB"; //합성 스프린트 붕대
				}
			}
			
			//롤 Splint
			for(int i=0; i<trtList.size();i++){
				for(int j=0; j<RS.length;j++){
					if(RS[j].equals(trtList.get(i))) return "RS"; //합성롤 스프린트
				}
			}
			
			
		}
		
		return "";
	}
	
	/*
	 * 치료대 수가 예외 체크
	 */
	private String medicalTypeCheckSub(String medicalCd,String type){
		if("C".equals(type)){
				for(int j=0; j<CAST_EX.length;j++){
					if(CAST_EX[j].equals(medicalCd)) return "Y";
				}
		}else if("S".equals(type)){
				for(int j=0; j<SPLINT.length;j++){
					if(medicalCd.indexOf(SPLINT[j])>-1) return SPLINT[j];
				}
		}else{
				
				if(medicalCd.length() == 8 ){
					for(int j=0; j<ADULT.length;j++){
						if(ADULT[j].equals(medicalCd.substring(5,8))) return "A";
					}
				}else if(medicalCd.length() == 5){
					for(int j=0; j<SPLINT.length;j++){
						if(SPLINT[j].equals(medicalCd)) return "A";
					}
				}else{
					
				}
				
				if(medicalCd.length() == 8){
					for(int j=0; j<CHILD.length;j++){
						if(CHILD[j].equals(medicalCd.substring(5,8))) return "C";
					}
				}
		}
		return "N";
	}
	/*
	 * 캐스트 갯수 합
	 */
	private List<ErrorCheckVo> countCheck(ErrorCheckVo errorCheckVo){
		
		List<ErrorCheckVo> extErrorCastList = new ArrayList<ErrorCheckVo>();
		String title="";
		double sum =  0.0;
		sum = statementMapper.selectCastSum(errorCheckVo);
		ErrorCheckVo temp = statementMapper.selectCastVal(errorCheckVo);
					
		if("0009".equals(temp.getNotify())){
			if("1".equals(temp.getNotifySub())){
				title+="석고 CAST ";
			}else{
				if("1".equals(temp.getExtDiv2())){
					title+="합성 CAST";
				}else{
					title+="소아 예외CAST";
				}
			}
		}else if("0010".equals(temp.getNotify())){
			if("1".equals(temp.getNotifySub())){
				title+="석고ROLL SPLINT";
			}else{
					title+="ROLL SPLINT";
			}
		}else if("0011".equals(temp.getNotify())){
			if("1".equals(temp.getNotifySub())){
				title+="석고 SPLINT";
			}else{
				if("3".equals(temp.getExtDiv2())){
					title+="합성 SPLINT(성인)";
				}else{
					title+="합성 SPLINT(소아)";
				}
			}
		}
		if(Double.parseDouble(temp.getVal()) != sum ){
			errorCheckVo.setParam1("재료대 용량을 ("+title+"\t"+temp.getCd()+"\t"+"갯수:"+temp.getVal()+"\t합:"+sum+")");
			extErrorCastList.add(statementMapper.selectDrugNotifyCastExtCheck(errorCheckVo));
		}else{
		}
		
		return extErrorCastList;
	}
	/*
	 * 캐스트 갯수 체크 재료대가 맞는지 Check
	 */
	private List<ErrorCheckVo> validationCheck(ErrorCheckVo errorCheckVo){
		String frontText = "";
		String backText = "";
		List<ErrorCheckVo> extErrorCastList = new ArrayList<ErrorCheckVo>();
		int cnt = duplicateEx(errorCheckVo); 
		List<ErrorCheckVo> errorList = statementMapper.selectCastValidationCheck(errorCheckVo);
		
		if(errorCheckVo.getTrtCdList().size()>0) {
			for(int i=0; i<errorCheckVo.getTrtCdList().size(); i++){
				if(i==0)	frontText = "[";
				if(errorCheckVo.getTrtCdList().size()-1==i)frontText += errorCheckVo.getTrtCdList().get(i)+"]";
				else frontText += errorCheckVo.getTrtCdList().get(i)+",";
			}
		}
		
		
		if(errorList.size()>0) {
			for(int i=0; i<errorList.size(); i++){
				if(i==0)	backText = "[";
				if(errorList.size()-1==i)backText += errorList.get(i).getCd()+"]";
				else backText += errorList.get(i).getCd()+",";
			}
		}
		
		if(cnt!=errorList.size()){
			errorCheckVo.setParam1("재료대 불일치(사용 재료대 = "+cnt+frontText+" \t"+"기준 재료대 = "+errorList.size()+backText+")");
			extErrorCastList.add(statementMapper.selectDrugNotifyCastExtCheck(errorCheckVo));
		}
		
		return extErrorCastList;
	}
	
	private int duplicateEx(ErrorCheckVo errorCheckVo){
		List<String> uniqueItems = new ArrayList<String>(new HashSet<String>(errorCheckVo.getTrtCdList()));		
		return uniqueItems.size();
	}
	
	/*
	 * 입원 환자 유무 체크
	 */
	private boolean hospitalizationCheck(List<TreatmentStatementVo> treatmentStatementList){
		boolean check = false;
		for(int i = 0; i<treatmentStatementList.size(); i++){
			if(check) break;
			TreatmentStatementVo trtVo = treatmentStatementList.get(i);
			if("1".equals(trtVo.getCdDivd())){ // 수가
				for(int x = 0; x<H1.length; x++){
					if(trtVo.getCd().indexOf(H1[x])>-1) check = true;
				}
			}
		}
		return check;
	}
	
	private List<ErrorCheckVo> findSetDrugCd(List<ErrorCheckVo> list,List<Map<String,String>> mapList,String type){
		for(int x=0; x<list.size(); x++){
			for(int y=0; y<mapList.size(); y++){
				Map<String,String> map = mapList.get(y);
				if("3".equals(map.get("TYPE"))){
					try{
						if(map.get("MAIN_DRUG_CD").equals(list.get(x).getMainDrugCd())){
							list.get(x).setCd((String)map.get("DRUG_CD"));
							if(!type.equals("0")){
								String tempCds[] = list.get(x).getVal().split(",");
								if(type.equals("2")){ 
									list.get(x).setErrMsg(list.get(x).getErrMsg().replace("$[param1]", map.get("DRUG_CD"))); 
									list.get(x).setErrMsg(list.get(x).getErrMsg().replace("$[param2]", findDrugCdLists(tempCds,mapList))+" 병용금기"+list.get(x).getNotify()+"-"+list.get(x).getNotifySub());
								}else if(type.equals("6")){
									list.get(x).setErrMsg(list.get(x).getErrMsg().replace("$[param1]", findDrugCdLists(tempCds,mapList))+" 단일금기"+list.get(x).getNotify()+"-"+list.get(x).getNotifySub()); 
								}
							}
							break;
						}
					}catch(Exception e){
						logger.error(e.getMessage());
					}
					
				}
			}
		}
		return list;
	}
	
	private String findDrugCdLists(String[] array ,List<Map<String,String>> mapList){
		String result="";
		try{
			if(array.length>0){
					for(int y=0; y<mapList.size(); y++){
						Map<String,String> map = mapList.get(y);
						if("3".equals(map.get("TYPE"))){
							for(int x=0; x<array.length; x++){
							if(map.get("MAIN_DRUG_CD").equals(array[x])){
								if(x==0)	result = "[";
								if(array.length-1==x)result +=map.get("DRUG_CD") +"]";
								else result += map.get("DRUG_CD")+"][";
							}
						}
					}
				}
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return result;
	}
}