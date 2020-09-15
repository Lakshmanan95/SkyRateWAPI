package com.skyrate.clib.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {
	public static String toJson(Object obj) {
		String jsonInString=null;
		try{
		ObjectMapper mapper = new ObjectMapper();
	    jsonInString = mapper.writeValueAsString(obj);
		System.out.println(jsonInString);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonInString;
	}
}
