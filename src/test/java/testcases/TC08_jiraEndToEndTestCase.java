package testcases;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

//Introduction to path param and query param in single test
//How to create session filter for authentication in rest assured API
//Sending file as attachment using Rest assured using multipart
//Parsing complex json and limiting json response through query parameter.


public class TC08_jiraEndToEndTestCase {
	
	@Test
	public void jiraTestcase() {
		
		
		RestAssured.baseURI="http://localhost:9090";
		
		
		//Creating Jira session
		SessionFilter session = new SessionFilter();
		
		String createSession=given().header("Content-Type","application/json").
		body("{ \"username\": \"pravin.sonawane\", \"password\": \"pravin@123\" }").
		filter(session).             //saving session id of response
		when().post("rest/auth/1/session").
		then().assertThat().statusCode(200).extract().response().asString();
		
		
		
		//add comment to jira issue
		//Used pathParam("key", "10002") for adding parameter to URL
		String addIssue=given().pathParam("key", "10002").header("Content-Type","application/json").
		body("{\r\n" + 
				"	\"body\": \"14th april Dr Baba Saheb\",\r\n" + 
				"		\"visibility\" :{\r\n" + 
				"		\"type\" : \"role\",\r\n" + 
				"		\"value\": \"Administrators\"\r\n" + 
				"		}\r\n" + 
				"	\r\n" + 
				"}").
		filter(session).   //Using above created session to create comment
		when().post("rest/api/2/issue/{key}/comment").
		then().assertThat().statusCode(201).extract().response().asString();
	
		JsonPath js = new JsonPath(addIssue);
		String expectedCommentId = js.getString("id");
		
		System.out.println("#############commentId:"+ expectedCommentId);
		
	
		/*
		 * //add attachement to Jira using multipart //The following example uploads a
		 * file called myfile.txt to the issue TEST-123: //curl -D- -u admin:admin -X
		 * POST -H "X-Atlassian-Token: no-check" //-F "file=@myfile.txt"
		 * https://your-domain.atlassian.net/rest/api/3/issue/TEST-123/attachments
		 * 
		 * String uploadfile =given().log().all().pathParam("key",
		 * "10002").header("X-Atlassian-Token","no-check") .filter(session).
		 * header("Content-Type","multipart/form-data"). multiPart("file",new
		 * File("sampleFile.txt")). when().post("rest/api/2/issue/{key}/attachments").
		 * then().log().all().assertThat().statusCode(200).extract().response().asString
		 * ();
		 * 
		 * //{//upload giving error // "errorMessages": [ //
		 * "You do not have permission to create attachments for this issue." // ], //
		 * "errors": {} // }
		 */
		
		
		
		//Get only comments field
		//then validate above comment 
		String issueComments=given().pathParam("key", "10002").filter(session).
		queryParam("fields", "comment").
		when().get("/rest/api/2/issue/{key}").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		//There are many comments we have to search one from them.
		JsonPath js1 = new JsonPath(issueComments);
		
		int commentCount=js1.getInt("fields.comment.comments.size()");
		
		for(int i=0;i<commentCount;i++) {
			String actualCommentId = js1.get("fields.comment.comments["+i+"].id").toString();
			
			if(actualCommentId.equalsIgnoreCase(expectedCommentId)) {
				String message=js1.get("fields.comment.comments["+i+"].body").toString();
				System.out.println("********message"+message);
			
			}
			
		}
		
		
	
		
	}
}
