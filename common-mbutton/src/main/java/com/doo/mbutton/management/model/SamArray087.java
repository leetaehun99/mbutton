package com.doo.mbutton.management.model;




public class SamArray087 {
	/*
	 * 요양급여비용청구서 : 청구서 Claim-Clm/명세서 Statements-Sts/요양기관 Hospital-Hsp/ 기관 Institution/Inst /건강보험 Heathcare Insurance-HelcInsrn
	 *                      보험자 Insurer-Insr/구분 Divide- Divd/형태 Type-Typ/요양급여 Recuperation-Recp/본인부담금 Coinsurance-Cinsr
	 *                      수가 Medical treatment-Metrt / 약가 Drug treatment- DrgTrt
	 */
	public static String[][] recuperationCostAccount = { 
		{ "clmDcmtVsn","청구서식버전", "3","0","0" },
		{ "stsDcmtVsn","명세서서식버전","3","0","0"},
		{ "clmNum","청구번호","10","0","0" },
		{ "dcmtNum","서식번호","4","0","0" },
		{ "hspId","요양기관(의료급여기관)기호", "8","0","0" }, 
		{ "rcvInst","수신기관","1","0","0" },		
		{ "insrDivd","보험자종별구분(의료급여진료구분)", "1","0","0" },
		{ "clmDivd","청구구분","1","0","0" },
		{ "clmUntDivd","청구단위구분","1","0","0" }, 
		{ "trtDivd","진료구분","1","0","0" },
		{ "trtFldDivd","진료분야구분","1","0","0" },		
		{ "trtTyp","진료형태","1","0","0" },
		{ "trtYrMnth","진료년월","6","0","0" },		
		{ "trtCnt","건수","6","0","1" },				
		{ "recpCst1","요양급여비용총액1", "12","0","1"},	
		{ "cinsrCost","본인부담금","12","0","1" },
		{ "cinsrOverCst","본인부담상한액초과금 총액","12","0","1" },
		{ "clmCst","청구액","12","0","1" },
		{ "sprtCst","지원금","12","0","1" }, 
		{ "hadipCst","장애인의료비","12","0","1" },
		{ "recpCst2","요양급여비용총액2,진료비총액","12","0","1" },		
		{ "helthCst","보훈청구액","12","0","1" },		
		{ "h100Cinsr","건강보험(의료급여)100분의100본인부담금총액","12","0","1" },
		{ "helthCinsrPrt","보훈 본인일부부담금","12","0","1" },
		
		{ "t100Cst1","100분의100미만총액","12","0","1" },
		{ "t100Cst2","100분의100미만본인일부부담금","12","0","1" },
		{ "t100Cst3","100분의100미만청구액","12","0","1" },
		{ "t100Cst4","100분의100미만보훈청구액","12","0","1" },

		{ "etc1","공란1","4","2","1" },
		{ "etc2","공란2","2","2","1" },
		{ "etc3","공란3","1","7","1" },
		{ "etc4","공란4","12","0","1" },
		
		{ "clmDt","청구일자","8","0","0" },
		{ "clmNm","청구인","20","0","0" },
		{ "wrtNm","작성자성명","20","0","0" },
		{ "wrtBrtDt","작성자생년월일","13","0","0" },
		{ "chkAprvNum","검사승인번호","35","0","0" },
		{ "agnClmSym","대행청구단계기호","5","0","0" },
		{ "ref","참조란","1750","0","0" }};
	/*
	 * 명세서(의치과) 일반  청구서 Claim-Clm/명세서 Statements-Sts/요양기관 Hospital-Hsp/ 기관 Institution/Inst /건강보험 Heathcare Insurance-HelcInsrn
	 *                      보험자 Insurer-Insr/구분 Divide- Divd/형태 Type-Typ/요양급여 Recuperation-Recp/본인부담금 Coinsurance-Cinsr
	 *                      수가 Medical treatment-Metrt / 약가 Drug treatment- DrgTrt
	 */
	public static String[][] medicalStatement = { 
		{ "clmNum","청구번호", "10","0","0" },
		{ "stsSrlNum","명세서일련번호", "5","0","0" },
		{ "detailDivd","내역구분", "1","0","0" },
		{ "dcmtNum","서식번호", "4","0","0" },
		{ "hspId","요양기관(의료급여)기관기호", "8","0","0" },
		{ "instNum","보장기관기호", "11","0","0" },
		{ "recpDivd","의료급여종별구분", "1","0","0" },
		{ "pubDivd","공상 등 구분", "1","0","0" },
		{ "fxRtDivd","정액,정률구분", "1","0","0" },
		{ "clmCd","코드", "1","0","0" },
		{ "clmRcptNum","접수번호", "7","0","0" },
		{ "clmStsSrlNum","명세서일련번호", "5","0","0" },
		{ "clmCaseCd","사유코드", "2","0","0" },
		{ "clmFrstEntrDt","최초입원개시일", "8","0","0" },
		{ "housrNm","가입자(세대주)성명", "20","0","0" },
		{ "grntdFcltIdNm","증번호", "20","0","0" },
		{ "rcvrNm","수진자 성명", "20","0","0" },
		{ "rcvrJuminNum","수진자 주민등록번호", "13","0","0" },
		{ "recpDayCnt","요양급여일수", "3","0","1" },
		{ "entrHospCnt","입원(총내원)일수", "3","0","1" },
		{ "empt","공란", "31","0","0" },
		{ "entrHospWay","도착,입원경로", "2","0","0" },
		{ "trtRslt","진료결과", "1","0","0" },
		{ "recpCst1","요양급여비용총액 1", "10","0","1" },
		{ "cinsrPrt","본인일부 부담금", "10","0","1" },
		{ "cinsrOverCst","본인부담상한액 초과금", "10","0","1" },
		{ "clmCst","청구액", "10","0","1" },
		{ "sprtCst","지원금", "10","0","1" },
		{ "hadipCst","장애인의료비", "10","0","1" },
		{ "subPayCst","대불금", "10","0","1" },
		{ "recpCst2","요양급여비용총액2, 진료비총액", "10","0","1" },
		{ "helthCst","보훈청구액", "10","0","1" },
		{ "drgOverCst","약제상한차액총액", "10","0","1" },
		{ "rcvrTotCst","수진자요양여비용총액", "10","0","1" },
		{ "h100Cinsr","건강보험 100/100본인부담금총액", "10","0","1" },
		{ "helthCinsrPrt","보훈 본인일부부담금", "10","0","1" },
		
		{ "t100Cst1","100/100미만 총액", "10","0","1" },
		{ "t100Cst2","100/100미만 본인일부부담금", "10","0","1" },
		{ "t100Cst3","100/100미만 청구액", "10","0","1" },
		{ "t100Cst4","100/100미만 보훈청구액", "10","0","1" }
	};
	
	/*
	 * 명세서 상병   
	 */
	public static String[][]  diseaseStatement = { 
		{ "clmNum","청구번호", "10","0","0" },
		{ "stsSrlNum","명세서일련번호", "5","0","0" },
		{ "detailDivd","내역구분", "1","0","0" },
		{ "diseaDivdCd","상병분류구분", "1","0","0" },
		{ "diseaDivdSym","상병분류기호", "6","0","0" },
		{ "trtSubct","진료과목", "2","0","0" },
		{ "trtDetailSubct","내과세부 전문과목", "2","0","0" },
		{ "trtStartDt","당월요양개시일", "8","0","0" },
		{ "licenCateg","면허종류", "1","0","0" },
		{ "licenNum","면허번호", "10","0","0" },
		{ "teethRu","치식우상", "8","0","0" },
		{ "teethLu","치식좌상", "8","0","0" },
		{ "teethRd","치식우하", "8","0","0" },
		{ "teethLd","치식좌하", "8","0","0" }	
	};
	
	/*
	 * 명세서 진료
	 */
	public static String[][] treatmentStatement = { 
		{ "clmNum","청구번호", "10","0","0" },
		{ "stsSrlNum","명세서일련번호", "5","0","0" },
		{ "detailDivd","내역구분", "1","0","0" },
		{ "sectNum1","항", "2","0","0" },
		{ "sectNum2","목", "2","0","0" },
		{ "lnNum","줄", "4","0","1" },
		{ "cdDivd","코드구분", "1","0","0" },
		{ "cd","코드", "9","0","0" },
		{ "untPrc","단가", "8","2","1" },
		{ "dorseDayCnt","일투", "5","2","1" },
		{ "dorseTotCnt","총투", "3","0","1" },
		{ "dorseOnceAmt","1회투약량", "5","4","1" },
		{ "prc","금액", "10","0","1" },
		{ "maxPrc","상한가", "10","0","1" },
		{ "drgOverDiffCst","약제상한차액", "10","0","1" },
		{ "chgDt","변경일", "8","0","0" },
		{ "licenCateg","면허종류", "1","0","0" },
		{ "licenNum","면허번호", "100","0","0" },
		{ "teethRu","치식우상", "8","0","0" },
		{ "teethLu","치식좌상", "8","0","0" },
		{ "teethRd","치식우하", "8","0","0" },
		{ "teethLd","치식좌하", "8","0","0" }	
	};
	
	/*
	 * 명세서 처방
	 */
	public static String[][] prescriptnStatement = { 
		{ "clmNum","청구번호", "10","0","0" },
		{ "stsSrlNum","명세서일련번호", "5","0","0" },
		{ "detailDivd","내역구분", "1","0","0" },
		{ "prscrptnNum","처방전발급번호", "13","0","0" },
		{ "prscrptnDayCnt","처방일수", "3","0","1" },
		{ "againMakCnt","반복조제횟수", "2","0","1" },
		{ "lnNum","줄번호", "4","0","1" },
		{ "cdDivd","코드구분", "1","0","0" },
		{ "cd","코드", "9","0","0" },
		{ "dorseOnce","1회투약량", "5","4","1" },
		{ "dorseOnceCnt","1일투여횟수", "2","0","1" },
		{ "dorseTotCnt","총투약일수", "3","0","1" }		
	};
	
	/*
	 * 명세서 특정내역
	 */
	public static String[][] specificDetail = { 
		{ "clmNum","청구번호", "10","0","0" },
		{ "stsSrlNum","명세서일련번호", "5","0","0" },
		{ "detailDivd","내역구분", "1","0","0" },
		{ "prscrptnDivd","발생단위구분", "1","0","0" },
		{ "prscrptnNum","처방전발급번호", "13","0","1" },		
		{ "lnNum","줄번호", "4","0","1" },
		{ "speDetailDivd","특정내역구분", "5","0","0" },
		{ "speDetail","특정내역", "700","0","0" }
	};
}
