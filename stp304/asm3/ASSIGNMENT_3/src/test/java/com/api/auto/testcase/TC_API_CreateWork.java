package com.api.auto.testcase;

import java.io.File;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.auto.utils.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_API_CreateWork {

	private String token;
	private Response response;
	private JsonPath jsonBody;
	
	private String createWorkJsonPath = "./RequestPayload/CreateWork.json";
	File bodyCreateWork;
	private String myWork = null;
	private String myEducation = null;
	private String myExperience = null;
	

	@BeforeClass
	public void init() throws IOException {
		//
		String baseURL = PropertyReader.getProperty("base_URL");
		String createWorkPath = PropertyReader.getProperty("createWorkPath");
		token = PropertyReader.getToken();

		// make body
		bodyCreateWork = new File(createWorkJsonPath);
//		Map<String, Object> bodyCreateWork = new HashMap<String, Object>();
//		bodyCreateWork.put("nameWork", myWork);
//		bodyCreateWork.put("experience", myExperience);
//		bodyCreateWork.put("education", myEducation);
		
//***********************************************************		
//		RestAssured.given()		//RequestSpecification
//		.baseUri(baseURL)
//		.basePath(createWorkPath)
//		.contentType(ContentType.JSON)
//		.header("token", token)
//		.body(bodyCreateWork)
//		.when()
//		.post()	// response
//		.then()	// ValidatebleResponse
//		.statusCode(200);
//***********************************************************
		
		RequestSpecification postReq =		RestAssured.given()
				.baseUri(baseURL)
				.basePath(createWorkPath)
				.contentType(ContentType.JSON)
				.header("token", token)
				.body(bodyCreateWork);
		
		System.out.println(postReq.log().all());

		response = postReq.post();

		jsonBody = response.jsonPath();

		System.out.println(response.asPrettyString());
		
		//
		
//        myWork = PropertyReader.getJsonData(createWorkJsonPath, "nameWork");
//        myEducation = PropertyReader.getJsonData(createWorkJsonPath, "education");
//        myExperience = PropertyReader.getJsonData(createWorkJsonPath, "experience");
		myWork = "Data Science";
        myEducation = "Bachelor";
        myExperience = "3 years";
		
        System.out.println("myWork" + myWork);
	}
	
	
	@Test(priority = 0)
	public void tc001_Validate201Created() {
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	
	@Test(priority = 1)
	public void tc002_ValidateId() {
		Assert.assertTrue(response.getBody().asString().contains("id"));
	}
	
	@Test(priority = 2)
	public void tc003_ValidateNameWork() {

		Assert.assertTrue(response.getBody().asString().contains("nameWork"));
		Assert.assertEquals(jsonBody.getString("nameWork"), myWork);
	}
	
	@Test(priority = 3)
	public void tc004_ValidateExperience() {

		Assert.assertTrue(response.getBody().asString().contains("experience"));
		Assert.assertEquals(jsonBody.getString("experience"), myExperience);
	}
	
	@Test(priority = 4)
	public void tc005_ValidateEducation() {

		Assert.assertTrue(response.getBody().asString().contains("education"));
		Assert.assertEquals(jsonBody.getString("education"), myEducation);
	}
	
}
