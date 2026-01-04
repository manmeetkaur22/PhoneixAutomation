package com.api.test;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import com.api.constant.roles;
import static com.api.utils.specUtils.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
//Add description

public class CountAPITest {

	@Test(description = "Verifying count api is giving correct response", groups = { "regression", "smoke", "api" })
	public void verifycountAPitResponse() throws IOException {
		given().spec(requestSpecwithAuth(roles.FD)).when().get("/dashboard/count").then().spec(responsespec())
				.body("message", Matchers.equalTo("Success")).time(Matchers.lessThan(1000L))
				.body("data", Matchers.notNullValue()).body("data.size()", Matchers.equalTo(3))
				.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
				.body(matchesJsonSchemaInClasspath("response-schema/CountResponseSchema.json"));
	}

}