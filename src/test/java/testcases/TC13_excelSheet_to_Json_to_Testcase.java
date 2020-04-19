package testcases;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utilities.dataDriven;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;

//Add place
//Update place with new address
public class TC13_excelSheet_to_Json_to_Testcase {

	@Test
	public void addPlaceAndVerifyStatus() throws IOException {
		
		
		dataDriven d = new dataDriven();
		ArrayList data = d.getData("Add Place");

		System.out.println(data.get(0));
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		System.out.println(data.get(3));
		
		
		
		
		RestAssured.baseURI="http://216.10.245.166";
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", data.get(1));
		map.put("isbn", data.get(2));
		map.put("aisle", data.get(3));
		map.put("author", data.get(4));
		
		
		
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