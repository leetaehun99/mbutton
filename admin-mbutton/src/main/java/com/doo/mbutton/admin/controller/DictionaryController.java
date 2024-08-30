package com.doo.mbutton.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doo.mbutton.common.helper.JsonMapper;
import com.doo.mbutton.common.helper.PagingManager;
import com.doo.mbutton.management.model.DictionaryVo;
import com.doo.mbutton.management.service.DictionaryService;

@Controller
public class DictionaryController {
	
	// Json Mapper
	@Autowired
	private JsonMapper mapper;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private PagingManager pagingManager;
	@RequestMapping
	public String index(Model model,@ModelAttribute DictionaryVo dictionaryVo){
		model.addAttribute("dictionaryVo", dictionaryVo);
		return "/management/statement/dictionaryPopup.blank";
	}
	
	@RequestMapping
	public String selectDictionary(Model model,@ModelAttribute DictionaryVo dictionaryVo){
		List<DictionaryVo> dictionaryList = null;
		if("1".equals(dictionaryVo.getSearchDictionary())){
		 	dictionaryVo.setReadType("count");
		 	dictionaryVo.setTotalCount(dictionaryService.selectDisea(dictionaryVo).get(0).getTotalCount());
			dictionaryList = dictionaryService.selectDisea(dictionaryVo);
			PagingManager.setPagingInfo(dictionaryVo, "/dictionary/selectDictionary.doo");
		}else if("2".equals(dictionaryVo.getSearchDictionary())){
			dictionaryVo.setReadType("count");
			dictionaryVo.setTotalCount(dictionaryService.selectDictionary(dictionaryVo).get(0).getTotalCount());
			dictionaryList = dictionaryService.selectDictionary(dictionaryVo);
			PagingManager.setPagingInfo(dictionaryVo, "/dictionary/selectDictionary.doo");
		}
		
		model.addAttribute("dictionaryList", dictionaryList);
		model.addAttribute("dictionaryVo", dictionaryVo);
		return "/management/statement/dictionaryPopup.blank";
	}
}
