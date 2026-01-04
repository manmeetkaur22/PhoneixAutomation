package com.api.test;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import org.testng.annotations.Test;
import com.api.constant.roles;
import static com.api.utils.specUtils.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
//Add description

public class UserDetailsAPITest {

	@Test(description = "Verifying if user details api response is shown correctly", groups = { "api", "regression",
			"smoke" })
	public void userDeatislAPITest() throws IOException {
		given().spec(requestSpecwithAuth(roles.FD)).when().get("userdetails").then().spec(responsespec())
				.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}

}
