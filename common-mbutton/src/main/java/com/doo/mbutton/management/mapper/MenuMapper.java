package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.MenuVo;



public interface MenuMapper {
	public List<MenuVo> selectMenuList(MenuVo menuVo);
	public List<MenuVo> selectSubMenuList(MenuVo menuVo);
	public List<MenuVo> selectAllMenuList(MenuVo menuVo);
	public MenuVo selectMenu(MenuVo menuVo);
	public int createMenu(MenuVo menuVo);
	public int updateMenu(MenuVo menuVo);
	public String getMenuId();
	public List<MenuVo> selectParentMenu();
}
