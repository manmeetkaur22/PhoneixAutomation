package com.api.tests.datadriven;

import static com.api.utils.specUtils.requestSpecwithAuth;
import static com.api.utils.specUtils.responsespec;

//Update JobAPI Test Script

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.ServiceLoaction;
import com.api.constant.Warrenty_status;
import com.api.constant.roles;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtils;

public class CreateJobAPITest {
	private CreateJobPayload createJobPayload;

	@BeforeMethod(description = "creating create job api request payload")

	public void setup() {
		Customer customer = new Customer("Jatin", "Sharma", "776767777", "", "jatin@gmail.com", "");
		CustomerAddress customer_address = new CustomerAddress("D 404", "Vasant Glaxy", "Bangur Nagar", "Inrobit",
				"Mumbai", "332211", "India", "Maharashtra");
		CustomerProduct customer_product = new CustomerProduct(DateTimeUtils.getTimewithDaysAgo(10), "91676777712345",
				"91676777712345", "91676777712345", DateTimeUtils.getTimewithDaysAgo(10), Product.NEXUS_2.getCode(),
				Model.NEXUS_2_BLUE.getCode());
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNIG_SLOW.getCode(), "Battery Issue");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		createJobPayload = new CreateJobPayload(ServiceLoaction.SERVICE_LOCATION_A.getCode(),
				Platform.FRONT_DESK.getCode(), Warrenty_status.IN_WARRENTY.getCode(), OEM.GOOGLE.getCode(), customer,
				customer_address, customer_product, problemList);

	}

	@Test(description = "Verifying api is able to create new job", groups = { "regression", "smoke", "api" })
	public void createJobAPITest() throws IOException {

		given().spec(requestSpecwithAuth(roles.FD, createJobPayload)).when().post("/job/create").then()
				.spec(responsespec()).body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.job_number", Matchers.startsWith("JOB_"))
				.body(matchesJsonSchemaInClasspath("response-schema/CreateJobResponseSchema.json"));
	}

}
