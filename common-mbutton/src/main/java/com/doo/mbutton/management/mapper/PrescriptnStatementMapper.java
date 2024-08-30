package com.doo.mbutton.management.mapper;


import java.util.List;

import com.doo.mbutton.management.model.PrescriptnStatementVo;

public interface PrescriptnStatementMapper {
	public void createPrescriptnStatement(PrescriptnStatementVo prescriptnStatementVo) ;
	public List<PrescriptnStatementVo> selectPrescriptnStatementList(PrescriptnStatementVo prescriptnStatementVo);
	public List<PrescriptnStatementVo> selectPrescriptnStatementListTotal(PrescriptnStatementVo prescriptnStatementVo);
	public int deletePrescriptnStatement(PrescriptnStatementVo prescriptnStatementVo);
	public void deleteStatement(String recpCstClmSeq);
}
