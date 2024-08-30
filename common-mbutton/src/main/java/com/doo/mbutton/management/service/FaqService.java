package com.doo.mbutton.management.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.management.mapper.FaqMapper;
import com.doo.mbutton.management.model.FileVo;
import com.doo.mbutton.management.model.NoticeVo;
import com.doo.mbutton.management.model.ReNoticeVo;


@Service
public class FaqService {
	@Autowired
	FaqMapper faqMapper;
	
	public List<NoticeVo> selectFaqList(NoticeVo noticeVo){
		return faqMapper.selectFaqList(noticeVo);
	}
	
	public NoticeVo selectFaq(NoticeVo noticeVo){
		noticeVo = faqMapper.selectFaq(noticeVo);
		//noticeVo.setReNoticeList(faqMapper.selectReFaqList(noticeVo));
		return noticeVo;
	}
	
	public int createFaq(NoticeVo noticeVo,Map<String, Object> map) throws Exception{
		int result = 0;
			result = faqMapper.createFaq(noticeVo);
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
						faqMapper.createFile(fileVo);
					}
				}
			}else{
				
			}
		}
		
		return result;
	}
	
	public int updateFaq(NoticeVo noticeVo,Map<String, Object> map){
		
		int result = faqMapper.updateFaq(noticeVo);
		
		ArrayList<Map<String, String>> uploadFileList = null;

		
		if(result == 1){
			if("Y".equals(map.get("RESULT"))){
				uploadFileList = (ArrayList<Map<String, String>>) map.get("uploadFileList");
				HashMap<String, String> fileInfo = null;
				FileVo fileVo = null;
				if(uploadFileList!=null){
					if(uploadFileList.size()>0){
						faqMapper.deleteFile(noticeVo.getNoticeSeq());
					}
					for(int i=0; i<uploadFileList.size(); i++){
						fileInfo =  (HashMap<String, String>) uploadFileList.get(i);
						fileVo = new FileVo();
						fileVo.setNoticeSeq(noticeVo.getNoticeSeq());
						fileVo.setOrgFileName(fileInfo.get("orgFileName"));
						fileVo.setSysFileName(fileInfo.get("fileName"));
						fileVo.setFilePath(fileInfo.get("filePath"));
						faqMapper.createFile(fileVo);
						
						
					}
				}
			}else{
			}
		}
		return result;
	}
	
	public int createReFaq(ReNoticeVo reNoticeVo){
		int result = faqMapper.createReFaq(reNoticeVo);
		return result;
	}
	
	public int updateReFaq(ReNoticeVo reNoticeVo){
		int result = faqMapper.updateReFaq(reNoticeVo);
		return result;
	}
	
	public FileVo selectFile(FileVo fileVo){
		return faqMapper.selectFile(fileVo);
	}
}
