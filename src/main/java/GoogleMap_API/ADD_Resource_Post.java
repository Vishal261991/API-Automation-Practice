package GoogleMap_API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ADD_Resource_Post
{
	public static void main(String[] args) 
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(payload.addPalce())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP")).extract().response().asString();
		System.out.println(response);
		
		JsonPath js=new JsonPath(response);
		String path = js.getString("place_id");
		System.out.println(path);
	}

}
