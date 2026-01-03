package com.api.test;

import com.api.constant.roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.specUtils;

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
		.spec(specUtils.requestSpecwithAuth(roles.FD))
		.when()
		.get("/dashboard/count")
		.then()
		.spec(specUtils.responsespec())
		.body("message",Matchers.equalTo("Success"))
		.time(Matchers.lessThan(1000L))
		.body("data",Matchers.notNullValue())
		.body("data.size()", Matchers.equalTo(3))
		.body("data.count",Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountResponseSchema.json"));
	}
	
}