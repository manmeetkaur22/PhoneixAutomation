package com.api.test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constant.roles;
import com.api.pojo.UserCredential;
import com.api.utils.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import com.api.utils.specUtils;
public class LoginAPITest {
	@Test
	public void loginAPITest() throws IOException
	{
		UserCredential UserCredential = new UserCredential("iamfd","password");
		given()
			.spec(specUtils.requestspec(UserCredential))
		.when()
			.post("login")
		.then()
			.spec(specUtils.responsespec())
		.body("message", equalTo("Success"))
		.and()
	    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}

	
	}