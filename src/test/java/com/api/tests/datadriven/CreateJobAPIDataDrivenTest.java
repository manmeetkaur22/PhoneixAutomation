package com.api.tests.datadriven;

import static com.api.utils.specUtils.requestSpecwithAuth;
import static com.api.utils.specUtils.responsespec;

//Update JobAPI Test Script

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.roles;
import com.api.request.model.CreateJobPayload;

public class CreateJobAPIDataDrivenTest {

	@Test(description = "Verifying api is able to create new job", groups = { "regression", "smoke", "api" },
			dataProviderClass=com.dataprovider.DataProviderUtils.class,
			dataProvider="CreateJobAPIDataProvider")
	public void createJobAPITest(CreateJobPayload createJobPayload) throws IOException {

		given().spec(requestSpecwithAuth(roles.FD, createJobPayload)).when().post("/job/create").then()
				.spec(responsespec()).body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.job_number", Matchers.startsWith("JOB_"))
				.body(matchesJsonSchemaInClasspath("response-schema/CreateJobResponseSchema.json"));
	}

}
