package testcases;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;

//Add place
//Update place with new address
public class TC12_hashMapToJson {

	@Test
	public void addPlaceAndVerifyStatus() {
		
		RestAssured.baseURI="http://216.10.245.166";
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", "Learn Appium Automation with Java2.0");
		map.put("isbn", "efgh54359");
		map.put("aisle", "567854356");
		map.put("author", "John Rth");
		
		  //===========================================================================
		  //================== //Adding Place String
		  String orgResponse=given().log().all().header("Content-Type",
		  "application/json") .body(map)
		  .when().post("/Library/Addbook.php")
		  .then().assertThat().statusCode(200).extract().response().asString();
				  
		  JsonPath js = new JsonPath(orgResponse); //for parsing json 
		  String bookId=js.getString("ID");
		  String successMessage=js.getString("Msg");
		  System.out.println("Book ID :"+bookId);
		  System.out.println("Message :"+successMessage);
		 
		  
		 
		
		 
		
	}

}