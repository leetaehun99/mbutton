package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.FileVo;
import com.doo.mbutton.management.model.NoticeVo;
import com.doo.mbutton.management.model.ReNoticeVo;


public interface NoticeMapper {
	public List<NoticeVo> selectNoticeList(NoticeVo noticeVo);
	public NoticeVo selectNotice(NoticeVo noticeVo);
	
	public List<ReNoticeVo> selectReNoticeList(NoticeVo noticeVo);
	public ReNoticeVo selectReNotice(ReNoticeVo reNoticeVo);
	
	public int createNotice(NoticeVo noticeVo);
	public int createReNotice(ReNoticeVo reNoticeVo);
	public int createFile(FileVo fileVo);
	public int deleteFile(Long noticeSeq);
	
	public int updateNotice(NoticeVo noticeVo);
	public int updateReNotice(ReNoticeVo reNoticeVo);
	
	public FileVo selectFile(FileVo fileVo);
}
