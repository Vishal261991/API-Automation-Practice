package JiraTool_Autmation;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class Create_Issue1 
{
	
	@Test
	public void createIssue()
	{
		RestAssured.baseURI="http://localhost:8080";
		 
		SessionFilter session=new SessionFilter();
		String resp=given().log().all().header("Content-Type","application/json").body("{ \"username\": \"vishaldesai2691\", \"password\": \"Vishal@26\" }")
		.filter(session).when().post("/rest/auth/1/session")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
	
		
		//create Issue
		
       String responce=given().log().all().header("Content-Type","application/json").filter(session)
       .body("{\r\n"
       		+ "    \"fields\": {\r\n"
       		+ "       \"project\":\r\n"
       		+ "       {\r\n"
       		+ "          \"key\": \"JIR\"\r\n"
       		+ "       },\r\n"
       		+ "       \"summary\": \"crdit cardDefect-4.\",\r\n"
       		+ "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\r\n"
       		+ "       \"issuetype\": {\r\n"
       		+ "          \"name\": \"Bug\"\r\n"
       		+ "       }\r\n"
       		+ "   }\r\n"
       		+ "}")
       .when().post("rest/api/2/issue")
       .then().log().all().assertThat().statusCode(201).extract().response().asString();
       JsonPath js=new JsonPath(responce);
       String id=js.getString("id");
       
       //Add Comment
       String CommentResponce=given().log().all().pathParam("id", id).header("Content-Type","application/json").filter(session)
       .body("{\r\n"
       		+ "    \"body\": \"Creating comment using RestAssuered API by selenium.\",\r\n"
       		+ "    \"visibility\": {\r\n"
       		+ "        \"type\": \"role\",\r\n"
       		+ "        \"value\": \"Administrators\"\r\n"
       		+ "    }\r\n"
       		+ "}")
       .when().post("rest/api/2/issue/{id}/comment")
       .then().log().all().assertThat().statusCode(201).extract().response().asString();
       JsonPath js1=new JsonPath(CommentResponce);
       String CommentId=js1.getString("id");
       
       
//       //Update comment
       given().log().all().pathParam("id", id).pathParam("commentId", CommentId).header("Content-Type","application/json").filter(session)
       .body("{\r\n"
       		+ "    \"body\": \"Update the comment using rest assued API from Selenium.\",\r\n"
       		+ "    \"visibility\": {\r\n"
       		+ "        \"type\": \"role\",\r\n"
       		+ "        \"value\": \"Administrators\"\r\n"
       		+ "    }\r\n"
       		+ "}")
       .when().post("rest/api/2/issue/{id}/comment/{commentId}")
       .then().log().all().assertThat().statusCode(200);
       
       
       //Upload File
       
       given().header("X-Atlassian-Token","no-check")
       .header("content-Type","multiPart/form-data").pathParam("id", id).filter(session)
       .multiPart("file",new File("C:\\Users\\VISHAL\\eclipse-Vishals_workspace\\Api_Auomation\\Jira.txt"))
       .when().post("rest/api/2/issue/{id}/attachments")
       .then().log().all().assertThat().statusCode(200);	
       
	}

}
