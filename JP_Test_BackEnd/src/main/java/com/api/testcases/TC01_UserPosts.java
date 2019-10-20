package com.api.testcases;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.Utility.ConfigurationFile;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC01_UserPosts {

	static ConfigurationFile config=new ConfigurationFile();
	static RequestSpecification request = RestAssured.given();
	static JsonPath Jsonvalidate;
	static Response response;

	@BeforeClass

	public static void createPosts() throws Exception 
	   {		
		request.header("Content-Type", "application/json");
		JSONObject json=new JSONObject();		
		json.put("userId", "1");
		json.put("Id", "104");
		json.put("title", "title of the page");
		json.put("body", "body of the text description");	
		request.body(json.toString());
		String carturl=config.getPropertyValue("postURL");
		response=request.post(carturl);
		Thread.sleep(5000);
	   }
	@Test
	void checkResponseCode()
	{
		int code=response.getStatusCode();
		Assert.assertEquals(code, 201);
		System.out.println("Status code of create Posts "  +code);		
	}	
	@Test
	void responseBody()
	{
		String responsebody=response.getBody().asString();
		System.out.println("Response body " +responsebody);
		Assert.assertEquals(responsebody.contains("1"), true);
		Assert.assertEquals(responsebody.contains("104"), true);
		Assert.assertEquals(responsebody.contains("title of the page"), true);
		Assert.assertEquals(responsebody.contains("body of the text description"), true);
	}
	@Test
	void checkContentType()
	{
		String contenttype=response.header("Content-Type");
		Assert.assertEquals(contenttype, "application/json; charset=utf-8");
		
	}
}
