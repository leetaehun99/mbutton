package com.doo.mbutton.management.mapper;


import java.util.List;

import com.doo.mbutton.management.model.FileVo;
import com.doo.mbutton.management.model.RecuperationCostAccountVo;

public interface RecuperationCostAccountMapper {
	public long createRecuperationCostAccount(RecuperationCostAccountVo recuperationCostAccountVo) ;
	
	public List<RecuperationCostAccountVo> selectRecuperationCostAccountList(RecuperationCostAccountVo recuperationCostAccountVo);	
	
	public RecuperationCostAccountVo selectRecuperationCostAccount(RecuperationCostAccountVo recuperationCostAccountVo);
	public void deleteStatement(String recpCstClmSeq);
	public int createFile(FileVo fileVo);
	public FileVo selectFile(FileVo fileVo);
	public int deleteFile(String recpCstClmSeq);
}
