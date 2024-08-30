package com.doo.mbutton.admin.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doo.mbutton.common.helper.JsonMapper;
import com.doo.mbutton.common.helper.PagingManager;
import com.doo.mbutton.common.helper.SessionManager;
import com.doo.mbutton.management.model.MenuVo;
import com.doo.mbutton.management.model.UserVo;
import com.doo.mbutton.management.service.MenuService;

@Controller
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	// Json Mapper
	@Autowired
	private JsonMapper mapper;
	
	@RequestMapping
	public String selectMenuList(Model model,@ModelAttribute MenuVo menuVo){
		List<MenuVo> menuList = menuService.selectMenuList(menuVo);
		
		menuVo.setReadType("count");
		menuVo.setTotalCount(menuService.selectMenuList(menuVo).get(0).getTotalCount());
		PagingManager.setPagingInfo(menuVo, "/menu/selectMenuList.doo");

		model.addAttribute("menuVo", menuVo);
		model.addAttribute("menuList", menuList);
		
		return "/management/menu/selectMenuList.default";
	}
	
	@RequestMapping
	public String selectSubMenuList(Model model,@ModelAttribute MenuVo menuVo){
		List<MenuVo> menuList = menuService.selectSubMenuList(menuVo);
		
		menuVo.setReadType("count");
		menuVo.setTotalCount(menuService.selectSubMenuList(menuVo).get(0).getTotalCount());
		PagingManager.setPagingInfo(menuVo, "/menu/selectMenuList.doo");

		model.addAttribute("menuVo", menuVo);
		model.addAttribute("menuList", menuList);
		
		return "/management/menu/selectSubMenuList.default";
	}
	
	@RequestMapping(value = "/selectMenu.json")
	public void selectMenu(HttpServletResponse response,Model model,@ModelAttribute MenuVo menuVo){
		MenuVo resultVo = menuService.selectMenu(menuVo);
		mapper.send(response, resultVo);
	}
	
	@RequestMapping(value = "/createMenu.json")
	public void createMenu(HttpServletRequest request, HttpServletResponse response,@ModelAttribute MenuVo menuVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		menuVo.setRegisterId(sessionVo.getUserId());
		int resultValue = 0;
		try{
			resultValue = menuService.createMenu(menuVo);
		}catch(Exception e){
			resultValue = 0;
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/updateMenu.json")
	public void updateMenu(HttpServletRequest request, HttpServletResponse response,@ModelAttribute MenuVo menuVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		menuVo.setUpdaterId(sessionVo.getUserId());
		int resultValue = 0;
		try{
			resultValue = menuService.updateMenu(menuVo);
		}catch(Exception e){
			resultValue = 0;
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/selectParentMenu.json")
	public void selectParentMenu(HttpServletRequest request, HttpServletResponse response,@ModelAttribute MenuVo menuVo){
		List<MenuVo> list = null;
		try{
			list = menuService.selectParentMenu();
		}catch(Exception e){
		}
		mapper.send(response, list);
	}
}
