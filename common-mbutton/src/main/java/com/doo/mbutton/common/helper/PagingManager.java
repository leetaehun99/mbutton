package com.doo.mbutton.common.helper;

import org.springframework.stereotype.Component;

import com.doo.mbutton.common.model.PagingVo;

/**
 * 페이징 Manager
 * @author mskim
 * @since 2012. 1. 31.
 */
@Component
public class PagingManager {

	int imsiRowPerPage = 0;
	/**
	 * 사용자 정의 정보로 페이징 처리를 한다.
	 * @param pagingVo 페이징 VO
	 */
	public static void setPagingInfo(PagingVo pagingVo, String actionUrl) {

		/** 페이징 기준값 */
		int rowPerPage = pagingVo.getRowPerPage();		// 페이지당 Row수
		int pagePerBlock = pagingVo.getPagePerBlock();	// 블럭당 Page수
		int currentPage = pagingVo.getCurrentPage();	// 현재 페이지
		
		/** 전체 페이지 수 구하기 */
		int totalCount = pagingVo.getTotalCount();
		int totalPageCount = totalCount / rowPerPage;
		if(totalCount % rowPerPage > 0) totalPageCount ++;
		
		/** 전체 페이지 그룹 수 구하기 */
		int totalBlockCount = totalPageCount / pagePerBlock;
		if(totalPageCount % pagePerBlock > 0) totalBlockCount ++;
		
		/** 현재 페이지 그룹 구하기 */
		int currentBlock = ( currentPage / pagePerBlock ) + 1;
		if(currentPage % pagePerBlock == 0) currentBlock --;

		/** 현재 페이지 그룹의 시작과 끝 페이지 구하기 */
		int startPage = ( currentBlock - 1 ) * pagePerBlock + 1;
		int endPage = startPage + pagePerBlock -1 ;
		
		/** 페이징 네비게이션 (프로젝트별 수정) */
		StringBuffer pagingHtml = new StringBuffer(1024);
		
		// 이전 그룹 이동
		if(currentBlock > 1) {
			pagingHtml.append("<a href='javascript:goPage("+(startPage-1)+")'>◀</a> |\n");
		} else {
			pagingHtml.append("◀ |\n");
		}

		// 페이지 번호
		if(endPage > totalPageCount) {
			endPage = totalPageCount;
		}
		for(int i=startPage; i <= endPage; i++) {
			if(i == currentPage) {
				pagingHtml.append(" <b><font color='red'>" + i + "</font></b> |\n");
			} else {
				pagingHtml.append(" <a href='javascript:goPage("+i+")'>" + i + "</a> |\n");
			}
		}
		
		// 다음 그룹 이동
		if(currentBlock < totalBlockCount) {
			pagingHtml.append(" <a href='javascript:goPage("+(endPage+1)+")'>▶</a>\n");
		} else {
			pagingHtml.append(" ▶\n");
		}
		pagingHtml.append("<input type='hidden' id='currentPage' name='currentPage' value='" + currentPage + "'>");
		
		pagingHtml.append("<script type='text/javascript'>");
		pagingHtml.append("function goPage(currentPage) {");
		pagingHtml.append("document.getElementById('currentPage').value = currentPage;");
		pagingHtml.append("for(i=0; i<document.forms.length; i++) {");
		pagingHtml.append("if(document.forms[i].currentPage != undefined) {");
		pagingHtml.append("document.forms[i].action = '").append(actionUrl).append("';");		
		pagingHtml.append("document.forms[i].submit();");
		pagingHtml.append("}");
		pagingHtml.append("}");
		pagingHtml.append("}");
		pagingHtml.append("</script>");
		
		pagingVo.setPagingHtml(pagingHtml.toString());
	}
}
