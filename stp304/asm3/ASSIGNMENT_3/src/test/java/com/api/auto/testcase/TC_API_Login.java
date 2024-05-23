package com.api.auto.testcase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.auto.utils.PropertyReader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_API_Login {

	private String account;
	private String password;
	private Response response;
	private JsonPath jsonBody;
	
	
	@BeforeClass
	public void initiateLogin() throws IOException {
		// Read from config property file
		String baseURL = PropertyReader.getProperty("base_URL");
		String loginPath = PropertyReader.getProperty("loginPath");
		account = PropertyReader.getProperty("account");
		password = PropertyReader.getProperty("password");
		
		// make body
		Map<String, Object> bodyLogin = new HashMap<String, Object>();
		bodyLogin.put("account", account);
		bodyLogin.put("password", password);
		
//		String bodyLoginStr = "{\r\n"
//				+ "    \"account\":\"ACCOUNT\",\r\n"
//				+ "    \"password\":\"PASSWORD\"\r\n"
//				+ "}";
		
		RequestSpecification postReq =		RestAssured.given()
				.baseUri(baseURL)
				.basePath(loginPath)
				.contentType(ContentType.JSON)
//				.body(bodyLoginStr.replace("ACCOUNT", account).replace("PASSWORD", password));
				.body(bodyLogin);
		
		System.out.println(postReq.log().all());

		response = postReq.post();
		System.out.println("\nloginResponse-body: \n" + response.body().asPrettyString());
		
		// Save token
		jsonBody = response.body().jsonPath();
		PropertyReader.saveToken(jsonBody.getString("token"));

	}
	
	@Test(priority = 0)
	public void tc001_Validate200Ok() {
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 1)
	public void tc002_ValidateMessage() {
		Assert.assertTrue(response.getBody().asString().contains("message"));
		Assert.assertEquals(jsonBody.getString("message"), "Đăng nhập thành công");
	}
	
	@Test(priority = 2)
	public void tc003_ValidateToken() {
		Assert.assertTrue(response.getBody().asString().contains("token"));
		String actualToken = jsonBody.getString("token");
		Assert.assertTrue(actualToken == null ? false : true, "[Token is null]");
	}
	
	@Test(priority = 3)
	public void tc004_ValidateUserAccount() {
		Assert.assertTrue(response.getBody().asString().contains("user"));
		Assert.assertTrue(response.getBody().asString().contains("account"));
		Assert.assertEquals(jsonBody.getString("user.account"), account, "[Account check failed]");
	}
	
	@Test(priority = 4)
	public void tc005_ValidateUserPassword() {
		Assert.assertTrue(response.getBody().asString().contains("password"));
		Assert.assertEquals(jsonBody.getString("user.password"), password, "[Password check failed]");
	}
	
	@Test(priority = 5)
	public void tc006_ValidateUserType() {
		Assert.assertTrue(response.getBody().asString().contains("type"));
		Assert.assertEquals(jsonBody.getString("user.type"), "UNGVIEN", "[Type check failed]");
	}
	
}
