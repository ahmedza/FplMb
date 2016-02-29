package com.gcaa.fplmb.utils;

import java.util.HashMap;

public enum DeliveryStatus {

	DELIVERED("D"),UN_DELIVERED("U");
	
	private String code;

	static HashMap<String, DeliveryStatus> codeValueMap = new HashMap<String, DeliveryStatus>();
	
	DeliveryStatus(String code)
	{
		this.code = code;
	}
	
	static     
	{         
		for (DeliveryStatus  type : DeliveryStatus.values()) 
		{             
			codeValueMap.put(type.code, type);         
		}     
	}      
	
	public static DeliveryStatus getDeliveryStatus(String code)     
	{         
		return codeValueMap.get(code);     
	}
	
	public String getCode()     
	{         
		return code;     
	}
	
	
}
