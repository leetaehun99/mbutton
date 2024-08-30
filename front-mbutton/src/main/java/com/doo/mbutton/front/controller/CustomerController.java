package com.doo.mbutton.front.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doo.mbutton.common.helper.JsonMapper;
import com.doo.mbutton.common.helper.PagingManager;
import com.doo.mbutton.common.helper.SessionManager;
import com.doo.mbutton.management.model.CustomerVo;
import com.doo.mbutton.management.model.DiseaVo;
import com.doo.mbutton.management.model.DrugVo;
import com.doo.mbutton.management.model.UserVo;
import com.doo.mbutton.management.service.CustomerService;
import com.doo.mbutton.management.service.DiseaService;
import com.doo.mbutton.management.service.DrugService;

@Controller
public class CustomerController {
	
	private Log logger = LogFactory.getLog("MBUTTON.INFO");
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private DiseaService diseaService;
	
	@Autowired
	private DrugService drugService;
	// Json Mapper
	@Autowired
	private JsonMapper mapper;
	
	@RequestMapping(value = "/createCustomer.json")
	public void createCustomer(HttpServletRequest request, @RequestParam(value="isCreate") String isCreate, HttpServletResponse response, Model model, @ModelAttribute CustomerVo customerVo ){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		customerVo.setRegisterId(sessionVo.getUserId());
		int resultValue = 0;
		try{
			//CustomerVo customerInfo = customerService.selectCustomerInfo(customerVo);
			if("Y".equals(isCreate)) {
				resultValue = customerService.createCustomer(customerVo);
			}else if("N".equals(isCreate)){
				resultValue = customerService.updateCustomer(customerVo);
			}else {
				System.out.println("isCreate 값이 null입니다.");
			}
		}catch(Exception e){
			resultValue = 0;
			e.printStackTrace();
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/updateCustomer.json")
	public void updateCustomer(HttpServletRequest request, @RequestParam(value="isCreate") char isCreate, HttpServletResponse response, @ModelAttribute CustomerVo customerVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		customerVo.setUpdaterId(sessionVo.getUserId());
		int resultValue = 0;
		try{
			resultValue = customerService.updateCustomer(customerVo);
		}catch(Exception e){
			e.printStackTrace();
			resultValue = 0;
		}
		mapper.send(response, resultValue);
	}
	
	@RequestMapping(value = "/deleteCustomer.json")
	public void deleteCustomer(HttpServletRequest request, HttpServletResponse response,@ModelAttribute CustomerVo customerVo){
		UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
		customerVo.setRegisterId(sessionVo.getUserId());
		int resultValue = 0;
		try{
			resultValue = customerService.deleteCustomer(customerVo);
		}catch(Exception e){
			e.printStackTrace();
			resultValue = 0;
		}
		System.out.println("삭제 확인 : " + resultValue);
		mapper.send(response, resultValue);
	}
	
	/*
	 * 상병조회
	 */
	@RequestMapping
	public String selectManagerDiseaList(HttpServletRequest request, Model model,  @ModelAttribute CustomerVo customerVo) {
		try{

			UserVo sessionVo = (UserVo)SessionManager.get(request, "USER");
			customerVo.setRegisterId(sessionVo.getUserId());
			
			List<CustomerVo> customerList = customerService.selectCustomerList(customerVo);

			customerVo.setReadType("count");
			customerVo.setTotalCount(customerService.selectCustomerList(customerVo).get(0).getTotalCount());
			PagingManager.setPagingInfo(customerVo, "/customer/selectManagerDisea.doo");

			model.addAttribute("customerVo", customerVo);
			model.addAttribute("customerList", customerList);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		return "/management/disea/selectManagerDisea.default";
	}
	
	
	/*
	 * 질병분류코드 리스트(팝업)
	 */
	@RequestMapping(value = "/selectDiseaCdList.json")
	public void selectDiseaCdList(HttpServletResponse response, @ModelAttribute DiseaVo diseaVo) {
		List<DiseaVo> diseaCdList = null;
		try{
			diseaCdList = diseaService.selectDiseaCdList(diseaVo);
		}catch(Exception e){
			logger.error(e.getMessage());
			diseaCdList = null;
		}
		mapper.send(response, diseaCdList);
	}
	
	/*
	 * 약품코드 리스트(팝업)
	 */
	@RequestMapping(value = "/selectDrugCdList.json")
	public void selectDrugCdList(HttpServletResponse response, @ModelAttribute DrugVo drugVo) {
		List<DrugVo> drugCdList = null;
		try{
			drugCdList = drugService.selectDrugCdList(drugVo);
		}catch(Exception e){
			logger.error(e.getMessage());
			drugCdList = null;
		}
		mapper.send(response, drugCdList);
	}
	
}
