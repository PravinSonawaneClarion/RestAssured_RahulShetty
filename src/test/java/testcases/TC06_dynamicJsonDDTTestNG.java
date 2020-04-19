package testcases;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.RestAssured;

//Sending parameters to payload from Test using DDT testng


public class TC06_dynamicJsonDDTTestNG {

	@Test(dataProvider = "BooksData")
	public void addBook(String isbn,String aisle) {

		RestAssured.baseURI = "http://216.10.245.166";
		given().log().all().header("Content-Type", "application/json")
		.body(payload.addBook(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();

	}

	@DataProvider(name="BooksData")
	public String[][] inputData() {

		String data[][] = { {"abc","123"}, {"def","456"}, {"ghi","789"} };
		return data;
		
		

	}

}