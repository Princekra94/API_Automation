package com.files;

import io.restassured.path.json.JsonPath;

public class CommonPath {
	
	
	public static JsonPath rawTojson(String responce) {
		
		JsonPath js1 = new JsonPath(responce);
		
		return js1;
		
		
	};

}
