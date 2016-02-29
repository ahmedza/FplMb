package com.gcaa.fplmb.utils;

import java.util.HashMap;

public enum ConnectionStatusType {

CONNECTED("C"),DISCONNECTED("D");
	
	private String code;

	static HashMap<String, ConnectionStatusType> codeValueMap = new HashMap<String, ConnectionStatusType>();
	
	ConnectionStatusType(String code)
	{
		this.code = code;
	}
	
	static     
	{         
		for (ConnectionStatusType  type : ConnectionStatusType.values()) 
		{             
			codeValueMap.put(type.code, type);         
		}     
	}      
	
	public static ConnectionStatusType getConnectionStatusType(String code)     
	{         
		return codeValueMap.get(code);     
	}
	
	public String getCode()     
	{         
		return code;     
	}
	
	
}
