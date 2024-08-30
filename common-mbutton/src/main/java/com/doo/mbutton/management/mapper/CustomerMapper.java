package com.doo.mbutton.management.mapper;

import java.util.List;

import com.doo.mbutton.management.model.CustomerVo;

public interface CustomerMapper {
	public int updateCustomer(CustomerVo customerVo);
	public int createCustomer(CustomerVo customerVo);
	public int deleteCustomer(CustomerVo customerVo) throws Exception;
	public CustomerVo selectCustomerInfo(CustomerVo customerVo);
	public List<CustomerVo> selectCustomerList(CustomerVo customerVo); 
}
