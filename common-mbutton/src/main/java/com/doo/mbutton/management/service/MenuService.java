package com.doo.mbutton.management.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.management.mapper.MenuMapper;
import com.doo.mbutton.management.model.MenuVo;

@Service
public class MenuService {
	@Autowired
	MenuMapper menuMapper;
	
	public List<MenuVo> selectMenuList(MenuVo menuVo){
		return menuMapper.selectMenuList(menuVo);
	}
	
	public List<MenuVo> selectSubMenuList(MenuVo menuVo){
		return menuMapper.selectSubMenuList(menuVo);
	}
	
	public List<MenuVo> selectAllMenuList(MenuVo menuVo){
		return menuMapper.selectAllMenuList(menuVo);
	}
	
	public MenuVo selectMenu(MenuVo menuVo){
		return menuMapper.selectMenu(menuVo);
	}
	
	public int createMenu(MenuVo menuVo){
		menuVo.setMenuId(menuMapper.getMenuId());
		return menuMapper.createMenu(menuVo);
	}
	
	public int updateMenu(MenuVo menuVo){
		return menuMapper.updateMenu(menuVo);
	}
	
	public List<MenuVo> selectParentMenu(){
		return menuMapper.selectParentMenu();
	}	
}
