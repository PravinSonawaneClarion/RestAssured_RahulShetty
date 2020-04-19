package testcases;

import static io.restassured.RestAssured.given;


import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;

import org.testng.annotations.Test;


import io.restassured.RestAssured;

//Handle static json payload
//Using external json file.


public class TC07_staticJsonPayload {

	@Test
	public void addBook() throws IOException {

		RestAssured.baseURI = "http://216.10.245.166";
		given().log().all().header("Content-Type", "application/json")
		.body(generateStringFromResource("F:\\STUDY_NEW\\API TESTING\\Rahul Shetty Academy\\Automation\\AddBooks.json"))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();

	}

	
	public static String generateStringFromResource(String path) throws IOException {

	
		
		 byte[] content = Files.readAllBytes(Paths.get(path));
         System.out.println(new String(content));
         
         return new String(content);
		
	}
	
}
		