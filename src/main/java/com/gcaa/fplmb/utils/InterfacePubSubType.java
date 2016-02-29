package com.gcaa.fplmb.utils;

import java.util.HashMap;

public enum InterfacePubSubType {

PUBLISHER("PUB"),SUBSCRIBER("SUB"), SERVER("SRV"), CLIENT("CLT");
	
	private String code;

	static HashMap<String, InterfacePubSubType> codeValueMap = new HashMap<String, InterfacePubSubType>();
	
	InterfacePubSubType(String code)
	{
		this.code = code;
	}
	
	static     
	{         
		for (InterfacePubSubType  type : InterfacePubSubType.values()) 
		{             
			codeValueMap.put(type.code, type);         
		}     
	}      
	
	public static InterfacePubSubType getInterfacePubSubType(String code)     
	{         
		return codeValueMap.get(code);     
	}
	
	public String getCode()     
	{         
		return code;     
	}
	
	
}
