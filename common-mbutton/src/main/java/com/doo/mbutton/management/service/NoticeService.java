package com.doo.mbutton.management.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.management.mapper.NoticeMapper;
import com.doo.mbutton.management.model.FileVo;
import com.doo.mbutton.management.model.NoticeVo;
import com.doo.mbutton.management.model.ReNoticeVo;


@Service
public class NoticeService {
	@Autowired
	NoticeMapper noticeMapper;
	
	public List<NoticeVo> selectNoticeList(NoticeVo noticeVo){
		return noticeMapper.selectNoticeList(noticeVo);
	}
	
	public NoticeVo selectNotice(NoticeVo noticeVo){
		noticeVo = noticeMapper.selectNotice(noticeVo);
		//noticeVo.setReNoticeList(noticeMapper.selectReNoticeList(noticeVo));
		return noticeVo;
	}
	
	public int createNotice(NoticeVo noticeVo,Map<String, Object> map) throws Exception{
		int result = noticeMapper.createNotice(noticeVo);
		ArrayList<Map<String, String>> uploadFileList = null;
		
		
		if(result == 1){
			
			if("Y".equals(map.get("RESULT"))){
				uploadFileList = (ArrayList<Map<String, String>>) map.get("uploadFileList");
				HashMap<String, String> fileInfo = null;
				FileVo fileVo = null;
				if(uploadFileList!=null){
					for(int i=0; i<uploadFileList.size(); i++){
						fileInfo =  (HashMap<String, String>) uploadFileList.get(i);
						fileVo = new FileVo();
						fileVo.setNoticeSeq(noticeVo.getNoticeSeq());
						fileVo.setOrgFileName(fileInfo.get("orgFileName"));
						fileVo.setSysFileName(fileInfo.get("fileName"));
						fileVo.setFilePath(fileInfo.get("filePath"));
						noticeMapper.createFile(fileVo);
						
					}
				}
			}else{
			}
		}
		return result;
	}
	/*
	public NoticeVo updateNotice(NoticeVo noticeVo,Map<String, Object> map){
		try {
		
		String sb = noticeVo.getContents().toString();
		StringTokenizer st = new StringTokenizer(sb,"\r\n");
		BufferedWriter file = new BufferedWriter(new FileWriter("C:\\Users\\leetaehun\\Documents\\이태훈\\상병매핑\\상병기준\\b.txt", true));
		int i=0;
		StringBuffer ss =new StringBuffer();
		String kk = null;
		String subject = "";
		String drugCd = "";
		while(st.hasMoreElements()){
			i++;
			kk = st.nextToken().trim(); 
			if(kk!=null){
				if(kk.length()>0){
					
					
					if(kk.indexOf("요약정보")>=0 || kk.indexOf("심사지침")>=0 || kk.indexOf("관련링크")>=0 || kk.indexOf("결과내 검색")>=0 || kk.indexOf("최근정보수정일")>=0) continue;
					else{
						
						if(i==2){
							StringTokenizer st2 = new StringTokenizer(kk,"\t");
							st2.nextToken();
							String sq[] = st2.nextToken().split(" ");
							subject = sq[0];
						}else{
							StringTokenizer st3 = new StringTokenizer(kk,"\t");
							st3.nextToken();
							drugCd = st3.nextToken();
						}
						if(drugCd == null || drugCd.trim() == "") continue;
						else {
							file.write(subject+"\t"+drugCd+"\r\n");
							ss.append(subject+"\t"+drugCd+"\r\n");
						}
					}
					
					
				}
			}
		}

		file.close();
		noticeVo.setContents(ss.toString());
		}
		 catch (IOException e) {
			logger.error(e.getMessage());
		}
		return noticeVo;
	}
	*/
	
	public int updateNotice(NoticeVo noticeVo,Map<String, Object> map){
		
		int result = noticeMapper.updateNotice(noticeVo);
		
		ArrayList<Map<String, String>> uploadFileList = null;

		
		if(result == 1){
			if("Y".equals(map.get("RESULT"))){
				uploadFileList = (ArrayList<Map<String, String>>) map.get("uploadFileList");
				HashMap<String, String> fileInfo = null;
				FileVo fileVo = null;
				if(uploadFileList!=null){
					if(uploadFileList.size()>0){
						noticeMapper.deleteFile(noticeVo.getNoticeSeq());
					}
					for(int i=0; i<uploadFileList.size(); i++){
						fileInfo =  (HashMap<String, String>) uploadFileList.get(i);
						fileVo = new FileVo();
						fileVo.setNoticeSeq(noticeVo.getNoticeSeq());
						fileVo.setOrgFileName(fileInfo.get("orgFileName"));
						fileVo.setSysFileName(fileInfo.get("fileName"));
						fileVo.setFilePath(fileInfo.get("filePath"));
						noticeMapper.createFile(fileVo);
						
						
					}
				}
			}else{
			}
		}
		return result;
	}
	
	public int createReNotice(ReNoticeVo reNoticeVo){
		int result = noticeMapper.createReNotice(reNoticeVo);
		return result;
	}
	
	public int updateReNotice(ReNoticeVo reNoticeVo){
		int result = noticeMapper.updateReNotice(reNoticeVo);
		return result;
	}
	
	public FileVo selectFile(FileVo fileVo){
		return noticeMapper.selectFile(fileVo);
	}
}
