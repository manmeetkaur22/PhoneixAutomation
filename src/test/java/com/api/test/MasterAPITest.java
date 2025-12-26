package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import java.io.IOException;
import com.api.constant.roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPITest {
	@Test()
	public void MasterAPIResponse() throws IOException{
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.header("Authorization",AuthTokenProvider.getToken(roles.FD))
		.and()
		.contentType("")
		.when()
		.post("master")
		.then()
		.log().all()
		.statusCode(200)
		.time(Matchers.lessThan(1000L))
		//.body("data",Matchers.hasKey("mst_osem"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterResponseSchema.json"));
		
	}
}
