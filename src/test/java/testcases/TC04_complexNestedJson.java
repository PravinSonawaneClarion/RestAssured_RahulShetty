package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class TC04_complexNestedJson {

//created payload method in same file to avoid confusion
	public static String coursePrice() {
		return "{\r\n" + "  \"dashboard\": {\r\n" + "    \"purchaseAmount\": 1162,\r\n"
				+ "    \"website\": \"rahulshettyacademy.com\"\r\n" + "  },\r\n" + "  \"courses\": [\r\n" + "    {\r\n"
				+ "      \"title\": \"Selenium Python\",\r\n" + "      \"price\": 50,\r\n" + "      \"copies\": 6\r\n"
				+ "    },\r\n" + "    {\r\n" + "      \"title\": \"Cypress\",\r\n" + "      \"price\": 40,\r\n"
				+ "      \"copies\": 4\r\n" + "    },\r\n" + "    {\r\n" + "      \"title\": \"RPA\",\r\n"
				+ "      \"price\": 45,\r\n" + "      \"copies\": 10\r\n" + "    },\r\n" + "    {\r\n"
				+ "      \"title\": \"Appium\",\r\n" + "      \"price\": 36,\r\n" + "      \"copies\": 7\r\n"
				+ "    }\r\n" + "  ]\r\n" + "}";
	}

	@Test
	public void complexJson() {
		JsonPath js = new JsonPath(coursePrice());

		// print number of courses returned by API 
		int count = js.getInt("courses.size()");
		System.out.println(count);

		// Print purchase amount 
		int purchaseAmt=js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmt);

		// Print title of first course 
		String title=js.getString("courses[0].title");
		System.out.println(title);

		// Print all courses and there respective prices
		for (int i = 0; i < count; i++) {	
			System.out.println(js.getString("courses[" + i + "].title")+ ":"+js.getString("courses[" + i + "].price"));
		}

		//Verfiy if sum of course prices matches with purchase price
		int sum=0;
		for (int i = 0; i < count; i++) {	
			int price=js.getInt("courses[" + i + "].price");
			int copies=js.getInt("courses[" + i + "].copies");
			sum=sum+(price*copies);		
			
		}
		
		Assert.assertEquals(sum, purchaseAmt);
		
	}
}