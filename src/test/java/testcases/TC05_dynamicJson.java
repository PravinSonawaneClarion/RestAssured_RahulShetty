package testcases;

import org.testng.annotations.Test;

import files.payload;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

//Sending parameters to payload from Test
public class TC05_dynamicJson {
	
	@Test
	public void addBook() {
		
		
		RestAssured.baseURI="http://216.10.245.166";
		given().log().all().header("Content-Type","application/json").
		body(payload.addBook("12345678","abcdefg")).
		when().post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
		
	}

}
