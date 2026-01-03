package com.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.IOException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.api.request.model.UserCredential;
import static com.api.utils.specUtils.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginAPITest {
	private UserCredential UserCredential;

	@BeforeMethod(description = "Create the payload for login API")
	public void setup() {
		UserCredential = new UserCredential("iamfd", "password");
	}

	@Test(description = "Verifying login api is working for FD user", groups = { "api", "regression", "smoke" })
	public void loginAPITest() throws IOException {
		given().spec(requestspec(UserCredential)).when().post("login").then().spec(responsespec())
				.body("message", equalTo("Success")).and()
				.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}

}