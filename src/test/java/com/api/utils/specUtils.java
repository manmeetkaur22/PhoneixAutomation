package com.api.utils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.api.utils.ConfigManager.*;

import java.io.IOException;

import org.hamcrest.Matchers;

import com.api.constant.roles;
public class specUtils {

	
	public static RequestSpecification requestSpecwithAuth(roles role) throws IOException
	{
		RequestSpecification requestResponse=new RequestSpecBuilder()
		.setBaseUri(getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.addHeader("Authorization",AuthTokenProvider.getToken(roles.FD))
		.log(LogDetail.URI)
		.log(LogDetail.HEADERS)
		.log(LogDetail.METHOD)
		.log(LogDetail.BODY)
		.build();
		return requestResponse;
		
			
	}
	public static RequestSpecification requestspec(Object payload) throws IOException
	{
		RequestSpecification request=new RequestSpecBuilder()
		.setBaseUri(getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.setBody(payload)
		.log(LogDetail.URI)
		.log(LogDetail.HEADERS)
		.log(LogDetail.METHOD)
		.log(LogDetail.BODY)
		.build();
		return request;
		
			
	}

		public static ResponseSpecification responsespec()
		{
			ResponseSpecification responseSpcification=new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
			.expectStatusCode(200)
			.expectResponseTime(Matchers.lessThan(2000L))
			.log(LogDetail.ALL)
			.build();
				
			return responseSpcification;
					
		}
}
