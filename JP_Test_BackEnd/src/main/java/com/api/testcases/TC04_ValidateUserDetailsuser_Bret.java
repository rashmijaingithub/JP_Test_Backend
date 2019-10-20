package com.api.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.Utility.ConfigurationFile;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC04_ValidateUserDetailsuser_Bret {
	static ConfigurationFile config=new ConfigurationFile();
	static Response resp;
	static JsonPath Jsonvalidate;

	@BeforeClass
	public static void getAllusers() throws Exception {

		resp = RestAssured.get(config.getPropertyValue("User_Url"));
	}
	
	
	@Test
	void responseValidation() {
		JsonPath jsonpath=resp.jsonPath();
		System.out.println("User id "  + jsonpath.get("id"));
		System.out.println("User name  "  +jsonpath.get("name"));
		System.out.println("Username " +jsonpath.get("username"));
		System.out.println("User email"  +jsonpath.get("email"));
		System.out.println("User Address "  +jsonpath.getList("address"));			
	}
	
}
