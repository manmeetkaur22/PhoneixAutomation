package com.api.test;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import org.testng.annotations.Test;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.constant.roles.*;
import com.api.utils.ConfigManager;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {

	@Test
	public void userDeatislAPITest() throws IOException
	{
		Header authHeader=new Header("Authorization",getToken(FD));
		given()
			.baseUri(ConfigManager.getProperty("BASE_URI"))
			.and()
			.header(authHeader)
			.and()
			.accept(ContentType.JSON)
		.when()
			.get("userdetails")
		.then()
			.log().all()
			.statusCode(200)
			.time(lessThan(1000L))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}

}
