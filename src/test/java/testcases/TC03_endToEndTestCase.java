package testcases;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;

//Add place
//Update place with new address
public class TC03_endToEndTestCase {

	@Test
	public void addPlaceAndVerifyStatus() {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		  //===========================================================================
		  //================== //Adding Place String
		  String orgResponse=given().queryParam("key","qaclick123").header("Content-Type",
		  "application/json") .body(payload.addPlace1())
		  .when().post("/maps/api/place/add/json")
		  .then().extract().response().asString();
		  
		  //System.out.println("Response :"+orgResponse);
		  
		  JsonPath js = new JsonPath(orgResponse); //for parsing json 
		  String orgPlaceId=js.getString("place_id");
		  
		//  System.out.println("Original placeId :"+orgPlaceId);
		 
		  
		  //===========================================================================
		  // ================ //Update place with new address String 
		   String newAddress="70 Summer walk, Africa";
		  
		  given().queryParam("key","qaclick123").header("Content-Type",
		  "application/json") .body("{\r\n" + "\"place_id\":\""+orgPlaceId+"\",\r\n" +
		  "\"address\":\""+newAddress+"\",\r\n" + "\"key\":\"qaclick123\"\r\n" +
		  "}\r\n" + "") .when().put("/maps/api/place/update/json")
		  .then().assertThat().statusCode(200).body("msg",
		  equalTo("Address successfully updated"));
		 
		  //===========================================================================
		  //================ //Get new Place and verify //For get there is no header no
		  //payload/body
		  String getPlaceResponse=given().log().all().queryParam("key","qaclick123").queryParam("place_id",orgPlaceId) 
		  .when().get("/maps/api/place/get/json")
		  .then().log().all().assertThat().statusCode(200).extract().response().asString();
		  
		  
			  JsonPath js1 = new JsonPath(getPlaceResponse); //for parsing json String
			  String actualAddress=js1.getString("address");
			  
			  System.out.println("actualAddress***"+actualAddress );
			  
			 Assert.assertEquals(actualAddress, newAddress);
			 
		
		 
		
	}

}