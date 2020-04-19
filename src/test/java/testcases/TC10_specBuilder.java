package testcases;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import files.payload;

public class TC10_specBuilder {

	@Test
	public void verifyResponse() {

		//
		/*
		 * RestAssured.baseURI="https://rahulshettyacademy.com";
		 * 
		 * String
		 * orgResponse=given().queryParam("key","qaclick123").header("Content-Type",
		 * "application/json") .body(payload.addPlace1())
		 * .when().post("/maps/api/place/add/json")
		 * .then().extract().response().asString();
		 */


		RequestSpecification reqspec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").addHeader("Content-Type","application/json").build();

	
	
		 // Seperated request's given and when,then
		RequestSpecification res = given().log().all().spec(reqspec).body(payload.addPlace1());
				                   
		Response  reqResponse =	res.when().post("/maps/api/place/add/json").
				                    then().extract().response();

		
		System.out.println("***********"+reqResponse.asString());			

	}

}
