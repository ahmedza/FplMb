package com.gcaa.fplmb.utils;

import java.util.HashMap;

public enum InterfaceType {

JMS("JMS"),TCP("TCP"), SOAP("SOP"), REST("RST");

	private String code;

	static HashMap<String, InterfaceType> codeValueMap = new HashMap<String, InterfaceType>();

	InterfaceType(String code)
	{
		this.code = code;
	}

	static     
	{         
		for (InterfaceType  type : InterfaceType.values()) 
		{             
			codeValueMap.put(type.code, type);         
		}     
	}      

	public static InterfaceType getInterfaceType(String code)     
	{         
		return codeValueMap.get(code);     
	}

	public String getCode()     
	{         
		return code;     
	}	
}