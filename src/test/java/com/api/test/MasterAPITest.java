package com.api.test;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import org.testng.annotations.Test;
import com.api.constant.roles;
import static com.api.utils.specUtils.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
//Add description

public class MasterAPITest {
	@Test(description = "Verifying master api is giving correct response", groups = { "regression", "smoke", "api" })
	public void MasterAPIResponse() throws IOException {
		given().spec(requestSpecwithAuth(roles.FD)).when().post("master").then().spec(responsespec())
				// .body("data",Matchers.hasKey("mst_osem"))
				.body(matchesJsonSchemaInClasspath("response-schema/MasterResponseSchema.json"));

	}
}
