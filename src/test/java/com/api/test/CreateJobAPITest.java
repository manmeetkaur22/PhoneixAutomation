package com.api.test;

import static io.restassured.RestAssured.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import com.api.constant.roles;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtils;
import com.api.utils.specUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	@Test
	public void createJobAPITest() throws IOException
	{
		Customer customer=new Customer("Jatin","Sharma","776767777","","jatin@gmail.com","");
		CustomerAddress customer_address=new CustomerAddress("D 404","Vasant Glaxy","Bangur Nagar","Inrobit","Mumbai","332211","India","Maharashtra");
		CustomerProduct customer_product=new CustomerProduct(DateTimeUtils.getTimewithDaysAgo(10),"91676777712345","91676777712345","91676777712345",DateTimeUtils.getTimewithDaysAgo(10),1,1);
		Problems problems=new Problems(1,"Battery Issue");
		List<Problems> problemList=new ArrayList<Problems>();
		problemList.add(problems);
		CreateJobPayload createJobPayload=new CreateJobPayload(0,2,1,1,customer,customer_address,customer_product,problemList);
		given()
		.spec(specUtils.requestSpecwithAuth(roles.FD,createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(specUtils.responsespec())
		.body("message",Matchers.equalTo("Job created successfully. "))
		.body("data.job_number",Matchers.startsWith("JOB_"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobResponseSchema.json"));
	}

}
