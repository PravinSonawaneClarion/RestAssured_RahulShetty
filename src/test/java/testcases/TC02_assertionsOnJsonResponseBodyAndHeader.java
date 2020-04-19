package testcases;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import files.payload;

public class TC02_assertionsOnJsonResponseBodyAndHeader {
	
	//add place and verify status
	//Creating seperate payload file
	@Test
	public void addPlaceAndVerifyStatus() {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		//given
		//when
		//then
		
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(payload.addPlace1())
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server","Apache/2.4.18 (Ubuntu)");
		
	}

}
