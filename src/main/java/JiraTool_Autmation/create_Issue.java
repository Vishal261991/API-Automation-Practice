package JiraTool_Autmation;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class create_Issue
{
	@Test
	public void AddComment()
	{
		RestAssured.baseURI="http://localhost:8080";
		//cookie Based Authontication
		SessionFilter session=new SessionFilter();
		String responce=given().header("Content-Type","application/json").body("{ \"username\": \"vishaldesai2691\", \"password\": \"Vishal@26\" }")
        .filter(session).when().post("/rest/auth/1/session")
        .then().log().all().assertThat().statusCode(200).extract().response().asString();
        
		//create issue
		String res=given().log().all().header("Content-Type","application/json").body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "        \"project\": \r\n"
				+ "        {\r\n"
				+ "            \"key\": \"RA\"\r\n"
				+ "        },\r\n"
				+ "        \"summary\": \"internet banking defect -01\",\r\n"
				+ "        \"description\": \"Defect at login process\",\r\n"
				+ "        \"issuetype\": \r\n"
				+ "        {\r\n"
				+ "            \"name \": \"bug\"\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("rest/api/2/issue")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js=new JsonPath(res);
		String id=js.getString("id");
		
		
		//Add comment
		given().log().all().header("key",""+id+"").header("Content-Type","application/json").filter(session).body("{\r\n"
				+ "    \"body\": \"Adding new comment for Credit Card defect -02\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").when().post("/rest/api/2/issue/key/comment")
		.then().log().all().statusCode(201).extract().response().asString();
		
	}

	
}
