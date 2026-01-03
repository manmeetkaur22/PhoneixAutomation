package com.api.test;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import org.testng.annotations.Test;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.constant.roles.*;

import com.api.constant.roles;
import com.api.utils.ConfigManager;
import com.api.utils.specUtils;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {

	@Test
	public void userDeatislAPITest() throws IOException
	{
		given()
		.spec(specUtils.requestSpecwithAuth(roles.FD))
		.when()
			.get("userdetails")
		.then()
		.spec(specUtils.responsespec())	
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}

}
