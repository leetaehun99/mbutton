package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.FileVo;
import com.doo.mbutton.management.model.NoticeVo;
import com.doo.mbutton.management.model.ReNoticeVo;


public interface FaqMapper {
	public List<NoticeVo> selectFaqList(NoticeVo noticeVo);
	public NoticeVo selectFaq(NoticeVo noticeVo);
	
	public List<ReNoticeVo> selectReFaqList(NoticeVo noticeVo);
	public ReNoticeVo selectReFaq(ReNoticeVo reNoticeVo);
	
	public int createFaq(NoticeVo noticeVo);
	public int createReFaq(ReNoticeVo reNoticeVo);
	public int createFile(FileVo fileVo);
	public int deleteFile(Long noticeSeq);
	
	public int updateFaq(NoticeVo noticeVo);
	public int updateReFaq(ReNoticeVo reNoticeVo);
	
	public FileVo selectFile(FileVo fileVo);
}
