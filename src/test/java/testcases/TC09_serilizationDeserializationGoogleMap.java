package testcases;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pojo.addPlace;
import pojo.location;

//for serialization and desearilzation add below lib
//jackson
//json
public class TC09_serilizationDeserializationGoogleMap {

	// Using serialization to add location to google map
	// First creating json using pojo class
	// Then using json to add location to google map
	/*
	 * @Test public void serialize() {
	 * 
	 * // serialize from java object to json using pojo class addPlace p = new
	 * addPlace(); p.setAccuracy(50); p.setName("Frontline house");
	 * p.setAddress("29, side layout, cohen 09"); p.setWebsite("http://google.com");
	 * p.setLanguage("Eng");
	 * 
	 * // setting value for list List<String> typeList = new ArrayList<String>();
	 * typeList.add("shoe park"); typeList.add("shop"); p.setTypes(typeList);
	 * 
	 * // setting value for different class location l = new location();
	 * l.setLat(12.22); l.setLng(13.33); p.setLocation(l);
	 * 
	 * RestAssured.baseURI = "https://rahulshettyacademy.com";
	 * 
	 * String response = given().queryParam("key",
	 * "qaclick123").body(p).when().post("/maps/api/place/add/json").then()
	 * .log().all().assertThat().statusCode(200).extract().response().asString(); }
	 */
	// Using deserialization to get location from google map to object
	// First creating object using pojo class
	// Then using object to retrieve data
	@Test

	public void deserialization() {

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String placeId="32e393c34ecdca613ef037e06c6ffaf4"; //Hard coded run add place from postman to get place id
		
		addPlace placeDetails=given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeId)
		.expect().defaultParser(Parser.JSON)
		.when().get("/maps/api/place/get/json").as(addPlace.class);
		
		
		System.out.println(placeDetails.getAccuracy());
		
		
				
	}
}
