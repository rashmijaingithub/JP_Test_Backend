package com.api.testcases;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.Utility.ConfigurationFile;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC03_ListofUsers {

	static ConfigurationFile config=new ConfigurationFile();
	static Response resp;
	static JsonPath Jsonvalidate;

	@BeforeClass
	public static void getAllusers() throws Exception {

		resp = RestAssured.get(config.getPropertyValue("URL"));
	}

	@Test
	void checkresponseCode() {
		int code = resp.getStatusCode();
		System.out.println("Statuscode " + ": " + code);
		Assert.assertEquals(code, 200);
	}

	@Test
	void checkResponseBody() {
		String data = resp.asString();
		System.out.println("body of the response " + ": " + data);
	}
	@Test
	void checkResponseTime() {
		double resp_time = resp.getTime();
		System.out.println("Response  time " + ": " + resp_time);
	}

	@Test
	void usernames()
	{
		String usernames = resp.jsonPath().getString("username");
		System.out.println("User names are "  +usernames);		
	}
	@Test
	void headerValidation()
	{
	String value = resp.getHeader("Content-Type");
	System.out.println("header "+value);
	}

	@Test
	void usersCompanyNames()
	{
		  List <String>users=resp.jsonPath().getList("$");
		  System.out.println("number of users : " +users.size());
		  Assert.assertEquals(users.size(), 10);
		 
 		  List<Map<String, String>> companies = resp.jsonPath().getList("company");
		  for(int i=0;i<companies.size();i++)
			{
			  System.out.println("user company name : " +companies.get(i).get("name"));
			}
	}
	
	@Test
	void usersAddresss()
	{
	  List<Map<String, String>> city = resp.jsonPath().getList("address");
		  for(int i=0;i<city.size();i++)
			{
			  System.out.println("User Address :"  +city.get(i).get("city"));
			}

	}
}



