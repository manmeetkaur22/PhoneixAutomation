package com.api.tests.datadriven;

import static com.api.utils.specUtils.requestspec;
import static com.api.utils.specUtils.responsespec;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.request.model.UserCredential;
import com.dataprovider.api.bean.UserBean;

public class LoginApiDataDrivenTest {
	private UserCredential UserCredential;

	
	@Test(description = "Verifying login api is working for FD user", 
			groups = { "api", "regression", "smoke" },
			dataProviderClass=com.dataprovider.DataProviderUtils.class,
			dataProvider="LoginApiDataProvider")
	public void loginAPITest(UserBean userbean) throws IOException  {
		given().spec(requestspec(userbean)).when().post("login").then().spec(responsespec())
				.body("message", equalTo("Success")).and()
				.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}


}
