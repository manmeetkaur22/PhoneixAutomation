package com.api.test;

import com.api.constant.roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class CountAPITest {

	@Test()
	public void verifycountAPitResponse() throws IOException
	{
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.header("Authorization",AuthTokenProvider.getToken(roles.FD))
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(200)
		.body("message",Matchers.equalTo("Success"))
		.time(Matchers.lessThan(1000L))
		.body("data",Matchers.notNullValue())
		.body("data.size()", Matchers.equalTo(3))
		.body("data.count",Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountResponseSchema.json"));
	}
	
}