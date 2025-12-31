package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import java.io.IOException;
import com.api.constant.roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.specUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPITest {
	@Test()
	public void MasterAPIResponse() throws IOException{
		given()
		.spec(specUtils.requestSpecwithAuth(roles.FD))
		.when()
		.post("master")
		.then()
		.spec(specUtils.responsespec())
		//.body("data",Matchers.hasKey("mst_osem"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterResponseSchema.json"));
		
	}
}
