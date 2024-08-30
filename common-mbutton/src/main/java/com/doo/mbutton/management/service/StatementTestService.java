package com.doo.mbutton.management.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.doo.mbutton.common.helper.CommonUtil;
import com.doo.mbutton.common.helper.StatementNotifyCheck;
import com.doo.mbutton.management.mapper.DiseaMapper;
import com.doo.mbutton.management.mapper.DiseaseStatementMapper;
import com.doo.mbutton.management.mapper.DrugMapper;
import com.doo.mbutton.management.mapper.DrugNotifyMapper;
import com.doo.mbutton.management.mapper.MedicalNotifyMapper;
import com.doo.mbutton.management.mapper.MedicalStatementMapper;
import com.doo.mbutton.management.mapper.NotifyCbMapper;
import com.doo.mbutton.management.mapper.NotifyExtMapper;
import com.doo.mbutton.management.mapper.PrescriptnStatementMapper;
import com.doo.mbutton.management.mapper.RecuperationCostAccountMapper;
import com.doo.mbutton.management.mapper.RelativeValueMapper;
import com.doo.mbutton.management.mapper.SpecificDetailMapper;
import com.doo.mbutton.management.mapper.StatementMapper;
import com.doo.mbutton.management.mapper.StatementTestMapper;
import com.doo.mbutton.management.mapper.TreatmentStatementMapper;
import com.doo.mbutton.management.model.DiseaVo;
import com.doo.mbutton.management.model.DiseaseStatementVo;
import com.doo.mbutton.management.model.DrugVo;
import com.doo.mbutton.management.model.ErrorCheckVo;
import com.doo.mbutton.management.model.MedicalStatementVo;
import com.doo.mbutton.management.model.PrescriptnStatementVo;
import com.doo.mbutton.management.model.RecuperationCostAccountVo;
import com.doo.mbutton.management.model.RelativeValueVo;
import com.doo.mbutton.management.model.SamArray;
import com.doo.mbutton.management.model.SamVo;
import com.doo.mbutton.management.model.SpecificDetailVo;
import com.doo.mbutton.management.model.StatementVo;
import com.doo.mbutton.management.model.TreatmentStatementVo;

@Service
public class StatementTestService {

	// Logger
	Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	StatementMapper statementMapper;
	@Autowired
	StatementTestMapper statementTestMapper;

	@Autowired
	RecuperationCostAccountMapper recuperationCostAccountMapper;

	@Autowired
	MedicalStatementMapper medicalStatementMapper;

	@Autowired
	DiseaseStatementMapper diseaseStatementMapper;

	@Autowired
	TreatmentStatementMapper treatmentStatementMapper;

	@Autowired
	PrescriptnStatementMapper prescriptnStatementMapper;

	@Autowired
	SpecificDetailMapper specificDetailMapper;
	
	@Autowired
	DrugMapper drugMapper;
	
	@Autowired
	DiseaMapper diseaMapper;
	
	@Autowired
	DrugNotifyMapper drugNotifyMapper;
	
	@Autowired
	MedicalNotifyMapper medicalNotifyMapper;
	
	@Autowired
	RelativeValueMapper relativeValueMapper;

	@Autowired
	NotifyExtMapper notifyExtMapper;

	@Autowired
	NotifyCbMapper notifyCbMapper;
	
	@Autowired
	private StatementNotifyCheck statementNotifyCheck;

	/*
	 * SAM 파일패스
	 */
	@Value("#{applicationProp['system.upload.path']}")
	private String uploadPath;

	@Value("#{applicationProp['enc.key']}")
	private String encKey;

	private BufferedReader br;

	/*
	 * 청구서 생성
	 */
	@SuppressWarnings("unchecked")
	public String createStatement(Map<String, Object> map, String registerId, String hspId,String stsTitle) throws Exception {

		String filePath = "";
		if ("Y".equals(map.get("RESULT"))) {
			ArrayList<Map<String, String>> uploadFileList = null;
			uploadFileList = (ArrayList<Map<String, String>>) map.get("uploadFileList");
			HashMap<String, String> fileInfo = (HashMap<String, String>) uploadFileList.get(0);
			filePath = uploadPath + File.separator + fileInfo.get("uploadPath") + File.separator + fileInfo.get("fileName");
		} else {

			return "FALSE";
		}

		List<Map<String, SamVo>> recuperationCostAccountList = new ArrayList<Map<String, SamVo>>();
		List<Map<String, SamVo>> medicalStatementList = new ArrayList<Map<String, SamVo>>();
		List<Map<String, SamVo>> diseaseStatementList = new ArrayList<Map<String, SamVo>>();
		List<Map<String, SamVo>> treatmentStatementList = new ArrayList<Map<String, SamVo>>();
		List<Map<String, SamVo>> prescriptnStatementList = new ArrayList<Map<String, SamVo>>();
		List<Map<String, SamVo>> specificDetailList = new ArrayList<Map<String, SamVo>>();
		String result = readSamFile(filePath, hspId, recuperationCostAccountList, medicalStatementList, diseaseStatementList, treatmentStatementList, prescriptnStatementList, specificDetailList);
		if ("TRUE".equals(result)) {
			
			createStatement("", registerId, recuperationCostAccountList, medicalStatementList, diseaseStatementList, treatmentStatementList, prescriptnStatementList, specificDetailList, encKey,stsTitle);// 수정모드
																																																	// 등록
			return "TRUE";
		} else if ("FALSE1".equals(result)) {
			return "FALSE1";
		} else
			return "FALSE";
	}

	/*
	 * 청구서 파일 리스트
	 */
	public List<String> getFileList(String filePath, String extension) {

		List<String> fileList = new ArrayList<String>();
		File file = new File(filePath);
		if (file.exists()) {
			File[] list = file.listFiles();
			for (int i = 0; i < list.length; i++) {
				if (list[i].getName().indexOf("." + extension) != -1) {
					fileList.add(list[i].getName());
				}
			}
		}
		return fileList;
	}

	/*
	 * 청구서 리스트
	 */
	public List<RecuperationCostAccountVo> selectRecuperationCostAccountList(RecuperationCostAccountVo recuperationCostAccountVo) {
		return recuperationCostAccountMapper.selectRecuperationCostAccountList(recuperationCostAccountVo);
	}

	/*
	 * 명세서
	 */
	public StatementVo selectStatement(long recpCstClmSeq,String clmNum, String hspId, String stsSrlNum, String type, String msgLev) {
		String year = ""; //진료년도
		StatementVo statementVo = new StatementVo();
		
		statementVo.setRecpCstClmSeq(recpCstClmSeq);
		statementVo.setClmNum(clmNum);
		statementVo.setHspId(hspId);
		statementVo.setStsSrlNum(stsSrlNum);
		
		MedicalStatementVo medicalStatement = null;
		List<DiseaseStatementVo> diseaseStatementList = null;
		List<TreatmentStatementVo> treatmentStatementList = null;
		List<PrescriptnStatementVo> prescriptnStatementList = null;
		List<SpecificDetailVo> specificDetailList = null;

		MedicalStatementVo medicalStatementVo = new MedicalStatementVo();
		DiseaseStatementVo diseaseStatementVo = new DiseaseStatementVo();
		
		medicalStatementVo.setRecpCstClmSeq(recpCstClmSeq);
		medicalStatementVo.setClmNum(clmNum);
		medicalStatementVo.setHspId(hspId);
		medicalStatementVo.setStsSrlNum(stsSrlNum);
		medicalStatementVo.setMsgLev(msgLev);
		
		diseaseStatementVo.setRecpCstClmSeq(recpCstClmSeq);
		diseaseStatementVo.setClmNum(clmNum);
		diseaseStatementVo.setHspId(hspId);
		diseaseStatementVo.setStsSrlNum(stsSrlNum);

		medicalStatement = medicalStatementMapper.selectMedicalStatement(medicalStatementVo);			// 명세서
		diseaseStatementList = diseaseStatementMapper.selectDiseaseStatementList(diseaseStatementVo);	// 상병
		
		statementVo.setTrtStartDt(diseaseStatementList.get(0).getTrtStartDt());//진료 시작일
		
		year = diseaseStatementList.get(0).getTrtStartDt().substring(0, 4);// 년도
		
		RelativeValueVo relativeValueVo = new RelativeValueVo();
		//relativeValueVo.setYear(year);
		relativeValueVo = relativeValueMapper.selectRelativeValueList(year);
		
		statementVo.setDayOfWeek(CommonUtil.getInstance().getDayOfWeek(diseaseStatementList.get(0).getTrtStartDt().substring(0, 6), "yyyyMM"));// 달력
		
		if ("JSON".equals(type)) {
			TreatmentStatementVo treatmentStatementVo = new TreatmentStatementVo();
			PrescriptnStatementVo prescriptnStatementVo = new PrescriptnStatementVo();
			SpecificDetailVo specificDetailVo = new SpecificDetailVo();
			ErrorCheckVo errorCheckVo = new ErrorCheckVo();
			
			treatmentStatementVo.setRecpCstClmSeq(recpCstClmSeq);
			treatmentStatementVo.setClmNum(clmNum);
			treatmentStatementVo.setHspId(hspId);
			treatmentStatementVo.setStsSrlNum(stsSrlNum);
			
			treatmentStatementVo.setRv1(relativeValueVo.getRv1());
			treatmentStatementVo.setRv2(relativeValueVo.getRv2());
			treatmentStatementVo.setRv3(relativeValueVo.getRv3());
			treatmentStatementVo.setRv4(relativeValueVo.getRv4());
			treatmentStatementVo.setRv5(relativeValueVo.getRv5());
			treatmentStatementVo.setRv6(relativeValueVo.getRv6());
			treatmentStatementVo.setRv7(relativeValueVo.getRv7());
			
			prescriptnStatementVo.setRecpCstClmSeq(recpCstClmSeq);
			prescriptnStatementVo.setClmNum(clmNum);
			prescriptnStatementVo.setHspId(hspId);
			prescriptnStatementVo.setStsSrlNum(stsSrlNum);

			specificDetailVo.setRecpCstClmSeq(recpCstClmSeq);
			specificDetailVo.setClmNum(clmNum);
			specificDetailVo.setHspId(hspId);
			specificDetailVo.setStsSrlNum(stsSrlNum);

			errorCheckVo.setRecpCstClmSeq(recpCstClmSeq);
			errorCheckVo.setClmNum(clmNum);
			errorCheckVo.setHspId(hspId);
			errorCheckVo.setStsSrlNum(stsSrlNum);
			treatmentStatementList = treatmentStatementMapper.selectTreatmentStatementList(treatmentStatementVo);		// 진료
			prescriptnStatementList = prescriptnStatementMapper.selectPrescriptnStatementList(prescriptnStatementVo);	// 처방
			specificDetailList = specificDetailMapper.selectSpecificDetailList(specificDetailVo);						// 특정내역
			statementVo.setMedicalStatementVo(medicalStatement);
			statementVo.setDiseaseStatementList(diseaseStatementList);
			statementVo.setTreatmentStatementList(treatmentStatementList);
			statementVo.setPrescriptnStatementList(prescriptnStatementList);
			statementVo.setSpecificDetailList(specificDetailList);
			long start = System.currentTimeMillis(); 
			statementNotifyCheck.notifyCheck(statementVo,errorCheckVo);
			long end = System.currentTimeMillis();
			logger.info("실행 시간 : " + ( end - start )/1000.0 );
		} else {
			List<MedicalStatementVo> medicalStatementSelectList = medicalStatementMapper.selectMedicalStatementSelectList(medicalStatementVo);	// 명세서
			statementVo.setMedicalStatementSelectList(medicalStatementSelectList);
		}
		return statementVo;
	}
	/*
	 * 청구서 상세
	 */
	public RecuperationCostAccountVo selectRecuperationCostAccount(RecuperationCostAccountVo recuperationCostAccountVo) {
		return recuperationCostAccountMapper.selectRecuperationCostAccount(recuperationCostAccountVo);
	}

	/*
	 * 상병리스트
	 */
	public List<DiseaseStatementVo> selectDiseaseStatementList(DiseaseStatementVo diseaseStatementVo) {
		List<DiseaseStatementVo> diseaseStatementList = diseaseStatementMapper.selectDiseaseStatementList(diseaseStatementVo);
		return diseaseStatementList;
	}

	/*
	 * 상병상세
	 */
	public DiseaseStatementVo selectDiseaseStatement(DiseaseStatementVo diseaseStatementVo) {
		DiseaseStatementVo diseaseStatement = diseaseStatementMapper.selectDiseaseStatement(diseaseStatementVo);
		return diseaseStatement;
	}

	/*
	 * 상병 수정
	 */
	public List<DiseaseStatementVo> updateDiseaseStatement(DiseaseStatementVo diseaseStatementVo, String updaterId) throws Exception {

		diseaseStatementVo.setUpdaterId(updaterId);
		diseaseStatementVo.setRegisterId(updaterId);
		if (!"1".equals(diseaseStatementVo.getDiseaDivdCd())) {
			diseaseStatementVo.setLicenCateg("");
			diseaseStatementVo.setLicenNum("");
		}
		if ("Y".equals(diseaseStatementVo.getModeType())) {// 등록

			if ("1".equals(diseaseStatementVo.getDiseaDivdCd())) {
				diseaseStatementMapper.updateDiseaDivdCd(diseaseStatementVo);
				diseaseStatementMapper.createDiseaseStatement(diseaseStatementVo);
			} else {
				diseaseStatementMapper.createDiseaseStatement(diseaseStatementVo);
			}
		} else {// 수정
			diseaseStatementMapper.updateDiseaseStatement(diseaseStatementVo);
		}

		List<DiseaseStatementVo> list = selectDiseaseStatementList(diseaseStatementVo);

		return list;
	}

	/*
	 * 상병 삭제
	 */
	public List<DiseaseStatementVo> deleteDiseaseStatement(DiseaseStatementVo diseaseStatementVo, String updaterId) throws Exception {

		diseaseStatementMapper.deleteDiseaseStatement(diseaseStatementVo);
		List<DiseaseStatementVo> list = selectDiseaseStatementList(diseaseStatementVo);
		return list;
	}

	/*
	 * 진료 상세
	 */
	public TreatmentStatementVo selectTreatmentStatement(TreatmentStatementVo treatmentStatementVo) {
		TreatmentStatementVo treatmentStatement = treatmentStatementMapper.selectTreatmentStatement(treatmentStatementVo);
		return treatmentStatement;
	}


	
	/*
	 * 특정내역 리스트
	 */
	public List<SpecificDetailVo> selectSpecificDetailList(SpecificDetailVo specificDetailVo) {
		return specificDetailMapper.selectSpecificDetailList(specificDetailVo);
	}

	/*
	 * 진료 리스트
	 */
	public List<TreatmentStatementVo> selectTreatmentStatementList(TreatmentStatementVo treatmentStatementVo) {
		
		return treatmentStatementMapper.selectTreatmentStatementList(treatmentStatementVo);
	}

	public List<TreatmentStatementVo> selectTreatmentStatementLnNumAscList(TreatmentStatementVo treatmentStatementVo) {
		return treatmentStatementMapper.selectTreatmentStatementLnNumAscList(treatmentStatementVo);
	}

	/*
	 * 항목구분 구하기
	 */
	public Map<String, String> selectTrtType(String mode, String type, String code) {
		Map<String, String> map = new HashMap<String, String>();
		String sectNum1 = "";
		String sectNum2 = "";
		String cdDivd = "";

		code = code.trim();

		if ("A".equals(mode)) {
			cdDivd = "1";
			if ("AA1".equals(code.substring(0, 3))) {// 초진
				sectNum1 = "01";
				sectNum2 = "01";
			} else if ("AA2".equals(code.substring(0, 3)) || "W01".equals(code.substring(0, 3)) || "W02".equals(code.substring(0, 3)) || "AH1".equals(code.substring(0, 3))) {// 재진
				sectNum1 = "01";
				sectNum2 = "02";
			} else if ("AL".equals(code.substring(0, 2)) || "AC".equals(code.substring(0, 2)) || "AE".equals(code.substring(0, 2)) || "AN".equals(code.substring(0, 2))
					|| "AX".equals(code.substring(0, 2)) || "AH2".equals(code.substring(0, 3)) || "AH3".equals(code.substring(0, 3)) || "AH4".equals(code.substring(0, 3))) {// 응급및
																																												// 회송료
				sectNum1 = "01";
				sectNum2 = "03";
			} else if ("AB".equals(code.substring(0, 2))) {// 입원료
				sectNum1 = "02";
				if ("4".equals(code.substring(code.length() - 1, code.length()))) {// 내과
					// 정신과
					sectNum2 = "02";
				} else { // 일반
					sectNum2 = "01";
				}
			} else if ("AJ".equals(code.substring(0, 2))) {// 중환자
				sectNum1 = "02";
				sectNum2 = "03";
			} else if ("AK".equals(code.substring(0, 2))) {// 격리병실
				sectNum1 = "02";
				sectNum2 = "04";
			} else if ("Y0000".equals(code.substring(0, 5)) || "Z0000".equals(code.substring(0, 5)) || "Z7000".equals(code.substring(0, 5)) || "Z8000".equals(code.substring(0, 5))
					|| "Z9000".equals(code.substring(0, 5)) || "T0000".equals(code.substring(0, 5))) {// 기본식대
				sectNum1 = "02";
				sectNum2 = "10";
			} else if ("Y0000".equals(code.substring(0, 5)) || "Z0000".equals(code.substring(0, 5)) || "Z7000".equals(code.substring(0, 5)) || "Z8000".equals(code.substring(0, 5))
					|| "Z9000".equals(code.substring(0, 5)) || "T0000".equals(code.substring(0, 5))) {// 가산식대
																										// ?
				sectNum1 = "02";
				sectNum2 = "11";
			} else if ("AD".equals(code.substring(0, 2)) || "AF".equals(code.substring(0, 2)) || "AG".equals(code.substring(0, 2)) || "AQ".equals(code.substring(0, 2))
					|| "AM".equals(code.substring(0, 2))) {// 기타
															// ?
				sectNum1 = "02";
				sectNum2 = "99";
			} else if ("J10".equals(code.substring(0, 3)) || "J11".equals(code.substring(0, 3)) || "J50".equals(code.substring(0, 3)) || "J51".equals(code.substring(0, 3))
					|| "J2000".equals(code.substring(0, 5))) {// 투약료및
																// 내복
																// 처방
				sectNum1 = "03";
				sectNum2 = "01";
			} else if ("J00".equals(code.substring(0, 3)) || "J15".equals(code.substring(0, 3)) || "J16".equals(code.substring(0, 3)) || "J55".equals(code.substring(0, 3))
					|| "J56".equals(code.substring(0, 5))) {// 투약료및
															// 외용
															// 처방
				sectNum1 = "03";
				sectNum2 = "02";
			} else if ("KK".equals(code.substring(0, 2))) {// 주사료
				sectNum1 = "04";
				if ("KK03".equals(code.substring(0, 4)) || "KK07".equals(code.substring(0, 4)) || "KK08".equals(code.substring(0, 4)) || "KK09".equals(code.substring(0, 4))
						|| "KK10".equals(code.substring(0, 4)) || "KK11".equals(code.substring(0, 4)) || "KK12".equals(code.substring(0, 4)) || "KK13".equals(code.substring(0, 4))
						|| "KK14".equals(code.substring(0, 4)) || "KK155".equals(code.substring(0, 5)) || "KK160".equals(code.substring(0, 5)) || "KX".equals(code.substring(0, 2))) {// 주사료
					sectNum2 = "99";
				} else {
					sectNum2 = "01";
				}
			} else if ("L".equals(code.substring(0, 1))) {// 마취료
				sectNum1 = "05";
				sectNum2 = "01";
			} else if ("MM".equals(code.substring(0, 2)) || "MX".equals(code.substring(0, 2))) {// 이학요법료
				sectNum1 = "06";
				sectNum2 = "01";
			} else if ("MN".equals(code.substring(0, 2))) {// 정신요법료
				sectNum1 = "07";
				sectNum2 = "01";
			} else if ("M".equals(code.substring(0, 1)) || "MX7".equals(code.substring(0, 3)) || "MY7".equals(code.substring(0, 3)) || "N".equals(code.substring(0, 1))
					|| "O".equals(code.substring(0, 1)) || "P".equals(code.substring(0, 1)) || "Q".equals(code.substring(0, 1)) || "R".equals(code.substring(0, 1)) || "S".equals(code.substring(0, 1))
					|| "U".equals(code.substring(0, 1))) {// 처치및 수술료
				sectNum1 = "08";
				sectNum2 = "01";
			} else if ("T6".equals(code.substring(0, 2))) {// 처치및 수술료
				sectNum1 = "08";
				sectNum2 = "03";
			} else if ("B".equals(code.substring(0, 1)) || "C".equals(code.substring(0, 1)) || "E".equals(code.substring(0, 1)) || "F".equals(code.substring(0, 1))) {// 검사료
				sectNum1 = "09";
				sectNum2 = "01";
			} else if ("HA4".equals(code.substring(0, 3)) || "HA8".equals(code.substring(0, 3))) {// 영상진단
																									// HB,HG
				sectNum1 = "S";
				sectNum2 = "01";
			} else if ("HE".equals(code.substring(0, 2)) || "HF".equals(code.substring(0, 2))) {// 영상진단
																								// HB
				sectNum1 = "S";
				sectNum2 = "02";
			} else if ("HZ".equals(code.substring(0, 2))) {// 영상진단
				sectNum1 = "S";
				sectNum2 = "03";
			} else if ("G".equals(code.substring(0, 1)) || "HA".equals(code.substring(0, 2)) || "HC".equals(code.substring(0, 2)) || "HX".equals(code.substring(0, 2))
					|| "HY".equals(code.substring(0, 2)) || "GB".equals(code.substring(0, 2)) || "HB".equals(code.substring(0, 2)) || "HG".equals(code.substring(0, 2))) {// 영상진단
				sectNum1 = "10";
				sectNum2 = "01";
			} else if ("HD".equals(code.substring(0, 2)) || "HX4".equals(code.substring(0, 3)) || "HY4".equals(code.substring(0, 3))) {// 영상진단
				sectNum1 = "10";
				sectNum2 = "02";
			}
		} else if ("B".equals(mode)) {
			cdDivd = "3";
			if ("A".equals(type)) {
				sectNum1 = "03";
				sectNum2 = "01";
			} else if ("B".equals(type)) {
				sectNum1 = "03";
				sectNum2 = "01";
			} else if ("C".equals(type)) {
				sectNum1 = "03";
				sectNum2 = "02";
			} else if ("D".equals(type)) {
				sectNum1 = "04";
				sectNum2 = "01";
			}
		} else if ("C".equals(mode)) {
			// 항목구분필요
			if ("A".equals(code.substring(0, 1)) || "K2".equals(code.substring(0, 2))) {
				sectNum1 = "10";
				sectNum2 = "02";
			} else if ("K8".equals(code.substring(0, 2))) {
				sectNum1 = "08";
				sectNum2 = "03";
			} else if ("L9".equals(code.substring(0, 2))) {
				sectNum1 = "05";
				sectNum2 = "01";
			} else {
				sectNum1 = "08";
				sectNum2 = "01";
			}
			cdDivd = "8";
		}


		map.put("cd", code);
		map.put("mode", mode);
		map.put("type", type);

		if (cdDivd == "") {
			map.put("result", "구분이 없습니다.");
		} else if (sectNum1 == "") {
			map.put("result", "항이 없습니다.");
		} else if (sectNum2 == "") {
			map.put("result", "목이 없습니다.");
		} else {
			map.put("result", "Y");
			map.put("cdDivd", cdDivd);
			map.put("sectNum1", sectNum1);
			map.put("sectNum2", sectNum2);
		}

		return map;
	}

	private void createStatement(String table, String registerId, List<Map<String, SamVo>> recuperationCostAccountList, List<Map<String, SamVo>> medicalStatementList,
			List<Map<String, SamVo>> diseaseStatementList, List<Map<String, SamVo>> treatmentStatementList, List<Map<String, SamVo>> prescriptnStatementList,
			List<Map<String, SamVo>> specificDetailList, String encKey,String stsTitle) throws Exception {
		long recpCstClmSeq =0;
		RecuperationCostAccountVo recuperationCostAccountVo = null;
		MedicalStatementVo medicalStatementVo = null;
		DiseaseStatementVo diseaseStatementVo = null;
		TreatmentStatementVo treatmentStatementVo = null;
		PrescriptnStatementVo prescriptnStatementVo = null;
		SpecificDetailVo specificDetailVo = null;
		String hspId = "";

		for (int i = 0; i < recuperationCostAccountList.size(); i++) {
			recuperationCostAccountVo = new RecuperationCostAccountVo();
			recuperationCostAccountVo.setRegisterId(registerId);
			recuperationCostAccountVo.setEncKey(encKey);
			Map<String, SamVo> recuperationCostAccountMap = recuperationCostAccountList.get(i);
			recuperationCostAccountVo.setRecuperationCostAccountMap(recuperationCostAccountMap, table);
			recuperationCostAccountVo.setStsTitle(stsTitle);
			recpCstClmSeq =  recuperationCostAccountMapper.createRecuperationCostAccount(recuperationCostAccountVo);
			if(recpCstClmSeq == 1) recpCstClmSeq = recuperationCostAccountVo.getRecpCstClmSeq();
			
		}

		for (int i = 0; i < medicalStatementList.size(); i++) {
			medicalStatementVo = new MedicalStatementVo();
			medicalStatementVo.setRecpCstClmSeq(recpCstClmSeq);
			medicalStatementVo.setRegisterId(registerId);
			medicalStatementVo.setEncKey(encKey);
			Map<String, SamVo> medicalStatementMap = medicalStatementList.get(i);
			medicalStatementVo.setMedicalStatementMap(medicalStatementMap, table);
			hspId = medicalStatementVo.getHspId();
			medicalStatementMapper.createMedicalStatement(medicalStatementVo);
		}

		for (int i = 0; i < diseaseStatementList.size(); i++) {
			diseaseStatementVo = new DiseaseStatementVo();
			diseaseStatementVo.setRecpCstClmSeq(recpCstClmSeq);
			diseaseStatementVo.setRegisterId(registerId);
			Map<String, SamVo> diseaseStatementMap = diseaseStatementList.get(i);
			diseaseStatementVo.setDiseaseStatementMap(diseaseStatementMap, hspId, table);
			diseaseStatementMapper.createDiseaseStatement(diseaseStatementVo);
		}

		for (int i = 0; i < treatmentStatementList.size(); i++) {
			treatmentStatementVo = new TreatmentStatementVo();
			treatmentStatementVo.setRecpCstClmSeq(recpCstClmSeq);
			treatmentStatementVo.setRegisterId(registerId);
			Map<String, SamVo> treatmentStatementMap = treatmentStatementList.get(i);
			treatmentStatementVo.setTreatmentStatementMap(treatmentStatementMap, hspId, table);
			treatmentStatementMapper.createTreatmentStatement(treatmentStatementVo);
		}

		for (int i = 0; i < prescriptnStatementList.size(); i++) {
			prescriptnStatementVo = new PrescriptnStatementVo();
			prescriptnStatementVo.setRecpCstClmSeq(recpCstClmSeq);
			prescriptnStatementVo.setRegisterId(registerId);
			Map<String, SamVo> prescriptnStatementMap = prescriptnStatementList.get(i);
			prescriptnStatementVo.setPrescriptnStatementMap(prescriptnStatementMap, hspId, table);
			prescriptnStatementMapper.createPrescriptnStatement(prescriptnStatementVo);
		}

		for (int i = 0; i < specificDetailList.size(); i++) {
			specificDetailVo = new SpecificDetailVo();
			specificDetailVo.setRecpCstClmSeq(recpCstClmSeq);
			specificDetailVo.setRegisterId(registerId);
			Map<String, SamVo> specificDetailMap = specificDetailList.get(i);
			specificDetailVo.setSpecificDetailMap(specificDetailMap, hspId, table);
			specificDetailMapper.createSpecificDetail(specificDetailVo);
		}

		logger.debug("등록자 : " + registerId + "	TABLE : " + table + "	recuperationCostAccountList SIZE : " + recuperationCostAccountList.size());
		logger.debug("등록자 : " + registerId + "	TABLE : " + table + "	medicalStatementList SIZE 		 : " + medicalStatementList.size());
		logger.debug("등록자 : " + registerId + "	TABLE : " + table + "	diseaseStatementList SIZE 		 : " + diseaseStatementList.size());
		logger.debug("등록자 : " + registerId + "	TABLE : " + table + "	treatmentStatementList SIZE 	 : " + treatmentStatementList.size());
		logger.debug("등록자 : " + registerId + "	TABLE : " + table + "	prescriptnStatementList SIZE 	 : " + prescriptnStatementList.size());
		logger.debug("등록자 : " + registerId + "	TABLE : " + table + "	specificDetailList SIZE 		 : " + specificDetailList.size());
	}

	private String readSamFile(String filePath, String hspId, List<Map<String, SamVo>> recuperationCostAccountList, List<Map<String, SamVo>> medicalStatementList,
			List<Map<String, SamVo>> diseaseStatementList, List<Map<String, SamVo>> treatmentStatementList, List<Map<String, SamVo>> prescriptnStatementList,
			List<Map<String, SamVo>> specificDetailList) {
		String result = "FALSE";
		try {
			String inputLine;
			boolean firstCheck = true;
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "euc-kr"));
			Map<String, SamVo> samMap = null;
			while ((inputLine = br.readLine()) != null) {
				byte[] inputByte = inputLine.getBytes("euc-kr");
				if (firstCheck) {
					if (hspId.equals(new String(inputByte, 20, 8, "euc-kr"))) {
						getRecuperationCost(inputByte, SamArray.recuperationCostAccount, samMap, recuperationCostAccountList);// 요양급여비용청구서
					} else {
						result = "FALSE1";
						return result;
					}
					firstCheck = false;
				} else {
					if ("A".equals(new String(inputByte, 15, 1, "euc-kr"))) {
						getRecuperationCost(inputByte, SamArray.medicalStatement, samMap, medicalStatementList);// 명세서(의치과)
																												// 일반
					} else if ("B".equals(new String(inputByte, 15, 1, "euc-kr"))) {
						getRecuperationCost(inputByte, SamArray.diseaseStatement, samMap, diseaseStatementList);// 명세서
																												// 상병
					} else if ("C".equals(new String(inputByte, 15, 1, "euc-kr"))) {
						getRecuperationCost(inputByte, SamArray.treatmentStatement, samMap, treatmentStatementList);// 명세서
																													// 진료
					} else if ("D".equals(new String(inputByte, 15, 1, "euc-kr"))) {
						getRecuperationCost(inputByte, SamArray.prescriptnStatement, samMap, prescriptnStatementList);// 명세서
																														// 처방
					} else if ("E".equals(new String(inputByte, 15, 1, "euc-kr"))) {
						getRecuperationCost(inputByte, SamArray.specificDetail, samMap, specificDetailList);// 명세서
																											// 특정내역
					}
				}
			}
			result = "TRUE";
		} catch (Exception e) {
			result = "FALSE2";
			logger.error("StatementService  > readSamFile : " + e.getMessage());
		}
		return result;
	}

	private void getRecuperationCost(byte[] inputByte, String[][] recuperationCost, Map<String, SamVo> map, List<Map<String, SamVo>> list) {

		try {
			int byteLength = inputByte.length; // 총사이즈
			int startPoint = 0; // 시작위치
			int endPoint = 0;
			int intLength = 0;
			int floatLength = 0;
			String key = "";
			String name = "";
			String value = "";
			String align = "";
			SamVo samVo;
			map = new HashMap<String, SamVo>();
			for (int i = 0; i < recuperationCost.length; i++) {
				samVo = new SamVo();
				intLength = Integer.parseInt(recuperationCost[i][2]);
				floatLength = Integer.parseInt(recuperationCost[i][3]);
				endPoint = intLength + floatLength;
				if (startPoint + endPoint > byteLength) { // 사이즈 예외처리
					endPoint = byteLength - (startPoint + endPoint);
					if (endPoint <= 0)
						endPoint = byteLength - startPoint;
				}
				key = recuperationCost[i][0];
				name = recuperationCost[i][1];

				value = new String(inputByte, startPoint, endPoint, "euc-kr");
				// if(floatLength!=0){
				// value
				// =value.substring(0,intLength)+"."+value.substring(intLength,endPoint);
				// }

				align = recuperationCost[i][4];
				samVo.setKey(key);
				samVo.setName(name);
				samVo.setValue(value.trim());
				samVo.setAlign(align);
				samVo.setFloatLength(floatLength);
				samVo.setIntLength(intLength);
				map.put(key, samVo);
				startPoint += endPoint;
			}
			list.add(map);

		} catch (Exception e) {
			logger.error("StatementService  > getRecuperationCost : " + e.getMessage());
		}
	}

	private String putZero(String val, int intSize) {
		int valSize = val.length();
		String leftPut = "";

		while ((intSize - valSize) != 0) {
			leftPut += "0";
			valSize++;
		}
		return leftPut + val;
	}

	// 명세서 수정
	public void updateMedical(MedicalStatementVo medicalStatementVo) {
		try{
			TreatmentStatementVo treatmentStatement = new TreatmentStatementVo();
			treatmentStatement.setClmNum(medicalStatementVo.getClmNum());
			treatmentStatement.setStsSrlNum(medicalStatementVo.getStsSrlNum());
			treatmentStatement.setHspId(medicalStatementVo.getHspId());
			treatmentStatement.setRecpCstClmSeq(medicalStatementVo.getRecpCstClmSeq());
		
			MedicalStatementVo medicalStatement = medicalStatementMapper.selectMedicalStatement(medicalStatementVo);
			

		RelativeValueVo relativeValueVo = relativeValueMapper.selectRelativeValueList("2014");
		treatmentStatement.setRv1(relativeValueVo.getRv1());
		treatmentStatement.setRv2(relativeValueVo.getRv2());
		treatmentStatement.setRv3(relativeValueVo.getRv3());
		treatmentStatement.setRv4(relativeValueVo.getRv4());
		treatmentStatement.setRv5(relativeValueVo.getRv5());
		treatmentStatement.setRv6(relativeValueVo.getRv6());
		treatmentStatement.setRv7(relativeValueVo.getRv7());
		
		
			List<TreatmentStatementVo> treatmentStatementList = treatmentStatementMapper.selectTreatmentStatementList(treatmentStatement);// 진료
			int h100Cinsr = Integer.parseInt(medicalStatement.getH100Cinsr());
			int totalPrice = 0;
			int sum1 = 0;// 구분1합
			int sum2 = 0;// 구분2합
			int round = 0;// 가산20%
			int selfPrice = 0;// 자기부담금
			int claimPrice = 0;// 청구금
			for (int i = 0; i < treatmentStatementList.size(); i++) {
				TreatmentStatementVo treatmentStatementVo = treatmentStatementList.get(i);
				//if ("Y".equals(treatmentStatementVo.getActDiv())) { // 가산적용
				//	sum2 += Integer.parseInt(treatmentStatementVo.getPrc());
				//} else { // 비가산
					sum1 += Integer.parseInt(treatmentStatementVo.getPrc());
				//}
			}
		
			round = (int) (sum2 * 1.2);
			totalPrice = (int) (Math.floor((sum1 + round) * 0.1) * 10);// 절사
			selfPrice = (int) Math.floor(Math.floor(totalPrice * 0.4) * 0.01) * 100;
			claimPrice = totalPrice - selfPrice;
		
			medicalStatementVo.setRecpCst1(putZero(totalPrice + "", 10)); // 요양급여1
			medicalStatementVo.setRcvrTotCst(putZero(totalPrice + "", 10)); // 수진자
			// 총액
			medicalStatementVo.setCinsrPrt(putZero(selfPrice + "", 10)); // 본인부담
			medicalStatementVo.setClmCst(putZero(claimPrice + "", 10)); // 청구액
			medicalStatementVo.setRecpCst2(putZero(totalPrice + h100Cinsr + "", 10)); // 요양급여2
			//medicalStatementMapper.updateMedicalStatement(medicalStatementVo);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void createSamFile(String clmNum, String hspId, String path, String fileName) {
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + File.separator + fileName), "MS949"));
			String stsSrlNum = "";
			RecuperationCostAccountVo recuperationCostAccount = null;
			List<MedicalStatementVo> medicalStatementSelectList = null;
			List<DiseaseStatementVo> diseaseStatementList = null;
			List<TreatmentStatementVo> treatmentStatementList = null;
			List<PrescriptnStatementVo> prescriptnStatementList = null;
			List<SpecificDetailVo> specificDetailList = null;

			RecuperationCostAccountVo recuperationCostAccountVo = new RecuperationCostAccountVo();
			MedicalStatementVo medicalStatementVo = new MedicalStatementVo();
			DiseaseStatementVo diseaseStatementVo = new DiseaseStatementVo();
			TreatmentStatementVo treatmentStatementVo = new TreatmentStatementVo();
			PrescriptnStatementVo prescriptnStatementVo = new PrescriptnStatementVo();
			SpecificDetailVo specificDetailVo = new SpecificDetailVo();

			recuperationCostAccountVo.setClmNum(clmNum);
			recuperationCostAccountVo.setHspId(hspId);

			medicalStatementVo.setClmNum(clmNum);
			medicalStatementVo.setHspId(hspId);

			diseaseStatementVo.setClmNum(clmNum);
			diseaseStatementVo.setHspId(hspId);

			treatmentStatementVo.setClmNum(clmNum);
			treatmentStatementVo.setHspId(hspId);

			prescriptnStatementVo.setClmNum(clmNum);
			prescriptnStatementVo.setHspId(hspId);

			specificDetailVo.setClmNum(clmNum);
			specificDetailVo.setHspId(hspId);

			recuperationCostAccount = recuperationCostAccountMapper.selectRecuperationCostAccount(recuperationCostAccountVo);
			medicalStatementSelectList = medicalStatementMapper.selectMedicalStatementList(medicalStatementVo);
			diseaseStatementList = diseaseStatementMapper.selectDiseaseStatementListTotal(diseaseStatementVo);
			treatmentStatementList = treatmentStatementMapper.selectTreatmentStatementListTotal(treatmentStatementVo);
			prescriptnStatementList = prescriptnStatementMapper.selectPrescriptnStatementListTotal(prescriptnStatementVo);
			specificDetailList = specificDetailMapper.selectSpecificDetailListTotal(specificDetailVo);
			recuperationCostAccount.setEncKey(encKey);
			out.write(recuperationCostAccount.getResultVal());
			out.newLine();

			for (int i = 0; i < medicalStatementSelectList.size(); i++) {
				MedicalStatementVo medicalStatement = medicalStatementSelectList.get(i);
				stsSrlNum = medicalStatement.getStsSrlNum();
				medicalStatement.setEncKey(encKey);
				out.write(medicalStatement.getResultVal());
				out.newLine();
				for (int a = 0; a < diseaseStatementList.size(); a++) {
					DiseaseStatementVo diseaseStatement = diseaseStatementList.get(a);
					if (stsSrlNum.equals(diseaseStatement.getStsSrlNum())) {
						out.write(diseaseStatement.getResultVal());
						out.newLine();
					}

				}

				for (int b = 0; b < treatmentStatementList.size(); b++) {
					TreatmentStatementVo treatmentStatement = treatmentStatementList.get(b);
					if (stsSrlNum.equals(treatmentStatement.getStsSrlNum())) {
						out.write(treatmentStatement.getResultVal());
						out.newLine();
					}

				}

				for (int c = 0; c < prescriptnStatementList.size(); c++) {
					PrescriptnStatementVo prescriptnStatement = prescriptnStatementList.get(c);
					if (stsSrlNum.equals(prescriptnStatement.getStsSrlNum())) {
						out.write(prescriptnStatement.getResultVal());
						out.newLine();
					}

				}

				for (int d = 0; d < specificDetailList.size(); d++) {
					SpecificDetailVo specificDetail = specificDetailList.get(d);
					if (stsSrlNum.equals(specificDetail.getStsSrlNum())) {
						out.write(specificDetail.getResultVal());
						out.newLine();
					}

				}
			}
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public int createSpecificDetail(SpecificDetailVo specificDetailVo) {
		int result = 0;
		if ("Y".equals(specificDetailVo.getModeType())) {
			specificDetailVo.setPrscrptnNum("");
			specificDetailVo.setDetailDivd("E");
			if ("1".equals(specificDetailVo.getPrscrptnDivd())) {
				specificDetailVo.setLnNum("");
			}
			result = specificDetailMapper.createSpecificDetail(specificDetailVo);
		} else {
			result = specificDetailMapper.updateSpecificDetail(specificDetailVo);
		}

		return result;
	}

	public int deleteSpecificDetail(SpecificDetailVo specificDetailVo) {
		return specificDetailMapper.deleteSpecificDetail(specificDetailVo);
	}

	public SpecificDetailVo selectSpecificDetail(SpecificDetailVo specificDetailVo) {
		return specificDetailMapper.selectSpecificDetail(specificDetailVo);
	}
	
	public StatementVo selectCheckContents(String errCodeType,String errCode){
		StatementVo statementVo = new StatementVo();
		DrugVo drugVo = new DrugVo();
		if("D".equals(errCodeType)){
			drugVo.setDrugCd(errCode);
			List<DiseaVo> diseaList = diseaMapper.selectDiseaMappingListByDrug(drugVo);
			statementVo.setDiseaList(diseaList);
		}
		drugVo = drugMapper.selectDrugInfo(drugVo);
		statementVo.setDrugVo(drugVo);
		return statementVo;
	}
	
	/*
	 * 진료 수정
	 */
	public TreatmentStatementVo updateTreatmentStatement(TreatmentStatementVo treatmentStatementVo, String updaterId) throws Exception {

		MedicalStatementVo medicalStatementVo = new MedicalStatementVo();
		medicalStatementVo.setStsSrlNum(treatmentStatementVo.getStsSrlNum());
		medicalStatementVo.setHspId(treatmentStatementVo.getHspId());
		medicalStatementVo.setClmNum(treatmentStatementVo.getClmNum());

		medicalStatementVo.setRecpCstClmSeq(treatmentStatementVo.getRecpCstClmSeq());
		medicalStatementVo.setUpdaterId(updaterId);

		treatmentStatementVo.setUpdaterId(updaterId);
		treatmentStatementVo.setRegisterId(updaterId);
		int uPrc = (int) Float.parseFloat(treatmentStatementVo.getUntPrc());
		String untPrc = uPrc + "";

		float dorseDayCnt = Float.parseFloat(treatmentStatementVo.getDorseDayCnt().substring(0, 5) + "." + treatmentStatementVo.getDorseDayCnt().substring(5, 7));
		int dorseTotCnt = Integer.parseInt(treatmentStatementVo.getDorseTotCnt());
		float dorseOnceAmt = Float.parseFloat(treatmentStatementVo.getDorseOnceAmt().substring(0, 5) + "." + treatmentStatementVo.getDorseOnceAmt().substring(5, 9));
		int prc = (int) Float.parseFloat(treatmentStatementVo.getUntPrc());
		String sPrc = "";

		untPrc += "00";
		untPrc = putZero(untPrc, 10);
		prc = (int) (prc * dorseDayCnt * dorseOnceAmt * dorseTotCnt);
		sPrc = putZero(prc + "", 10);

		treatmentStatementVo.setPrc(sPrc);
		treatmentStatementVo.setUntPrc(untPrc);
		// 진료가 아닌경우는 면허번호 제거
		if (!"01".equals(treatmentStatementVo.getSectNum1())) {
			treatmentStatementVo.setChgDt("");
			treatmentStatementVo.setLicenNum("");
			treatmentStatementVo.setLicenCateg("");
		}

		/* 코드구분 검색 */

		if ("Y".equals(treatmentStatementVo.getModeType())) {// 등록
			// ln_num 적용 process
			treatmentStatementVo.setLnNum("9999"); // 맨 마지막라인에 등록
			treatmentStatementMapper.createTreatmentStatement(treatmentStatementVo);

			List<TreatmentStatementVo> tsList = treatmentStatementMapper.selectTreatmentStatementLnNumDescList(treatmentStatementVo);
			int lnNum = 0;

			int result = 0;
			if (tsList != null) {
				// 특정내역 모델 초기화 작업
				SpecificDetailVo specificDetailVo = new SpecificDetailVo();
				specificDetailVo.setClmNum(treatmentStatementVo.getClmNum());
				specificDetailVo.setHspId(treatmentStatementVo.getHspId());
				specificDetailVo.setStsSrlNum(treatmentStatementVo.getStsSrlNum());
				Map<String, String> tMap = null;
				Map<String, String> sMap = null;
				for (int i = 0; i < tsList.size(); i++) {
					TreatmentStatementVo tempVo = (TreatmentStatementVo) tsList.get(i);
					lnNum = Integer.parseInt(tempVo.getLnNum());
					lnNum += 1;
					if (!"9999".equals(tempVo.getLnNum())) {
						specificDetailVo.setLnNum(tempVo.getLnNum());
						List<String> sdList = specificDetailMapper.selectSpecificDetailLnNumList(specificDetailVo);
						tMap = new HashMap<String, String>();
						tMap.put("lnNum", putZero(lnNum + "", 4));
						tMap.put("trtStsSeq", tempVo.getTrtStsSeq());

						if (sdList != null) { // 특정내역 수정 후 진료 수정
							for (int x = 0; x < sdList.size(); x++) {
								String speDetailSeq = sdList.get(x);
								sMap = new HashMap<String, String>();
								sMap.put("lnNum", putZero(lnNum + "", 4));
								sMap.put("speDetailSeq", speDetailSeq);
								result = specificDetailMapper.updateSpecificDetailLnNum(sMap);
							}
							result = treatmentStatementMapper.updateTreatmentStatementLnNum(tMap);
							
						}
					} else if ("9999".equals(tempVo.getLnNum())) {
						if (i == 0) {
							lnNum = tsList.size();
						} else {
							TreatmentStatementVo tempVo2 = (TreatmentStatementVo) tsList.get(i - 1);
							lnNum = Integer.parseInt(tempVo2.getLnNum());
						}
						tMap = new HashMap<String, String>();
						tMap.put("lnNum", putZero(lnNum + "", 4));
						tMap.put("trtStsSeq", tempVo.getTrtStsSeq());
						result = treatmentStatementMapper.updateTreatmentStatementLnNum(tMap);
						
						break;
					}
				}
			}

		} else {// 수정
			treatmentStatementMapper.updateTreatmentStatement(treatmentStatementVo);
		}
		// 명세서 수정

		//updateMedical(medicalStatementVo);

		// List<TreatmentStatementVo> list =
		// treatmentStatementMapper.selectTreatmentStatementList(treatmentStatementVo);//진료

		return treatmentStatementVo;
	}

	/*
	 * 진료 삭제
	 */
	public TreatmentStatementVo deleteTreatmentStatement(TreatmentStatementVo treatmentStatementVo, String updaterId) throws Exception {

		MedicalStatementVo medicalStatementVo = new MedicalStatementVo();
		medicalStatementVo.setStsSrlNum(treatmentStatementVo.getStsSrlNum());
		medicalStatementVo.setHspId(treatmentStatementVo.getHspId());
		medicalStatementVo.setClmNum(treatmentStatementVo.getClmNum());
		medicalStatementVo.setRecpCstClmSeq(treatmentStatementVo.getRecpCstClmSeq());
		medicalStatementVo.setUpdaterId(updaterId);

		SpecificDetailVo specificDetailVo = new SpecificDetailVo();
		specificDetailVo.setRecpCstClmSeq(treatmentStatementVo.getRecpCstClmSeq());
		specificDetailVo.setClmNum(treatmentStatementVo.getClmNum());
		specificDetailVo.setHspId(treatmentStatementVo.getHspId());
		specificDetailVo.setStsSrlNum(treatmentStatementVo.getStsSrlNum());
		specificDetailVo.setLnNum(treatmentStatementVo.getLnNum());
		specificDetailMapper.deleteSpecificDetailLnNum(specificDetailVo);
		treatmentStatementMapper.deleteTreatmentStatement(treatmentStatementVo);

		int lnNum = Integer.parseInt(treatmentStatementVo.getLnNum());
		int tempLnNum = 0;
		Map<String, String> tMap = null;
		Map<String, String> sMap = null;
		List<TreatmentStatementVo> tsList = treatmentStatementMapper.selectTreatmentStatementLnNumAscList(treatmentStatementVo);
		if (tsList != null) {
			for (int i = 0; i < tsList.size(); i++) {
				TreatmentStatementVo tempVo = (TreatmentStatementVo) tsList.get(i);
				tempLnNum = Integer.parseInt(tempVo.getLnNum());

				if (lnNum < tempLnNum) {
					tempLnNum -= 1;
					specificDetailVo.setLnNum(tempVo.getLnNum());
					List<String> sdList = specificDetailMapper.selectSpecificDetailLnNumList(specificDetailVo);
					if (sdList != null) { // 특정내역 수정 후 진료 수정
						for (int x = 0; x < sdList.size(); x++) {
							String speDetailSeq = sdList.get(x);
							sMap = new HashMap<String, String>();
							sMap.put("lnNum", putZero(tempLnNum + "", 4));
							sMap.put("speDetailSeq", speDetailSeq);
							specificDetailMapper.updateSpecificDetailLnNum(sMap);
						}
						tMap = new HashMap<String, String>();
						tMap.put("lnNum", putZero(tempLnNum + "", 4));
						tMap.put("trtStsSeq", tempVo.getTrtStsSeq());
						treatmentStatementMapper.updateTreatmentStatementLnNum(tMap);
					}

				}

			}
		}

		updateMedical(medicalStatementVo);
		return treatmentStatementVo;
	}
	
	public int deletePrescriptnStatement(PrescriptnStatementVo prescriptnStatementVo){
		return prescriptnStatementMapper.deletePrescriptnStatement( prescriptnStatementVo);
	}
}
