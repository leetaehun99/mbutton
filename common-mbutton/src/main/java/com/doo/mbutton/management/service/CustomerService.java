package com.doo.mbutton.management.service;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doo.mbutton.management.mapper.CustomerMapper;
import com.doo.mbutton.management.model.CustomerVo;

@Service
public class CustomerService{

	@Autowired
	private CustomerMapper customerMapper;

	public int updateCustomer(CustomerVo customerVo) {
		return customerMapper.updateCustomer(customerVo);
	}

	public int createCustomer(CustomerVo customerVo) {
		return customerMapper.createCustomer(customerVo);
	}

	public CustomerVo selectCustomerInfo(CustomerVo customerVo) {
		return customerMapper.selectCustomerInfo(customerVo);
	}
	
	public List<CustomerVo> selectCustomerList(CustomerVo customerVo) {
		return customerMapper.selectCustomerList(customerVo);
	}

	public int deleteCustomer(CustomerVo customerVo) throws Exception{
		String result[] = splitDel(customerVo.getDel());
		customerVo.setDiseaCd(result[0]);
		customerVo.setDrugCd(result[1]);
		return customerMapper.deleteCustomer(customerVo);
	}
	
	public String[] splitDel(String del) throws Exception{
		String[] max = del.split(",");
		
		StringTokenizer st1 = new StringTokenizer(del, ",");
		String[] diseaCd = new String[max.length];
		String[] drugCd = new String[max.length];
		String[] result = new String[2];
		try {
			int i = 0;
			while(st1.hasMoreTokens()){
	            String temp = st1.nextToken();
	           temp.indexOf("_");
	           diseaCd[i] = temp.substring(0, temp.indexOf("_"));
	           drugCd[i] = temp.substring(temp.indexOf("_") + 1, temp.length());
	           i++;   
	        }
			
			result = appendDel(diseaCd, drugCd);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String[] appendDel(String[] diseaCd, String[] drugCd) throws Exception{
		StringBuffer diseaCdBuffer = new StringBuffer();
		StringBuffer drugCdBuffer = new StringBuffer();
		String[] result = new String[2];
		diseaCdBuffer.append("'");
		drugCdBuffer.append("'");
		for(int i=0; i<diseaCd.length; i++) {
			diseaCdBuffer.append(diseaCd[i]).append("','");
			drugCdBuffer.append(drugCd[i]).append("','");
		}
		diseaCdBuffer.delete(diseaCdBuffer.length() -2, diseaCdBuffer.length());
		drugCdBuffer.delete(drugCdBuffer.length() -2, drugCdBuffer.length());
		result[0] = diseaCdBuffer.toString();
		result[1] = drugCdBuffer.toString();
		return result;
	}
	
}
