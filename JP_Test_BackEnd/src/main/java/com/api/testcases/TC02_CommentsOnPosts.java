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

public class TC02_CommentsOnPosts {

	static ConfigurationFile config=new ConfigurationFile();
	static RequestSpecification request = RestAssured.given();
	static JsonPath Jsonvalidate;
	static Response response;

	@BeforeClass

	public static void createComments() throws Exception 
	   {	
		request.header("Content-Type", "application/json");
		JSONObject json=new JSONObject();		
		json.put("postId", "101");
		json.put("Id", "501");
		json.put("name", "rashmi");
		json.put("email", "test@gmail.com");
		json.put("body", "body of the text description");	
		request.body(json.toString());
		String usercomments=config.getPropertyValue("commentsURL");
		response=request.post(usercomments);
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
		Assert.assertEquals(responsebody.contains("101"), true);
		Assert.assertEquals(responsebody.contains("501"), true);
		Assert.assertEquals(responsebody.contains("rashmi"), true);
		Assert.assertEquals(responsebody.contains("test@gmail.com"), true);
		Assert.assertEquals(responsebody.contains("body of the text description"), true);
	}
	@Test
	void checkContentType()
	{
		String contenttype=response.header("Content-Type");
		Assert.assertEquals(contenttype, "application/json; charset=utf-8");
		
	}
}

	

