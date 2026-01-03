package com.api.utils;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

import com.api.constant.roles;
import com.api.request.model.UserCredential;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

public class AuthTokenProvider {
	private AuthTokenProvider()
	{}
	
	public static String getToken(roles role) throws IOException
	{
		UserCredential userCredential=null;
		if(role==roles.FD)
		{
			userCredential=new UserCredential("iamfd","password");
		}
		else if(role==roles.SUP)
		{
			userCredential=new UserCredential("iamsup","password");
		}
		else if(role==roles.ENG)
		{
			userCredential=new UserCredential("iameng","password");
		}
		else if(role==roles.QC)
		{
			userCredential=new UserCredential("iamqc","password");
		}
		String token= given().baseUri(ConfigManager.getProperty("BASE_URI"))
					.contentType(ContentType.JSON)
					.body(userCredential)
					.when()
					.post("login")
					.then()
					.log()
					.ifValidationFails()
					.statusCode(200)
					.body("message",equalTo("Success"))
					.extract().body().jsonPath().getString("data.token");
		return token;
		
	}

	

}
