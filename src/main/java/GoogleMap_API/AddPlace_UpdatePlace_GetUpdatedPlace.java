package GoogleMap_API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AddPlace_UpdatePlace_GetUpdatedPlace 
{
	public static void main(String[] args) 
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		//ADD address
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(payload.addPalce())
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
		
		JsonPath js=new JsonPath(response);
		String place=js.getString("place_id");
		
		//Update Address

     	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
     	.body("{\r\n"
     			+ "\"place_id\":\""+place+"\",\r\n"
     			+ "\"address\":\"70 winter walk, Gargoti\",\r\n"
     			+ "\"key\":\"qaclick123\"\r\n"
     			+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
     	
     	
		//Get Address
     	String FetchResponce=given().log().all().queryParam("key", "qaclick123").queryParam("place_id",""+place+"")
     	.when().get("maps/api/place/get/json")
     	.then().log().all().assertThat().statusCode(200).body("address", equalTo("70 winter walk, Gargoti")).extract().response().asString();
		
     	JsonPath js1=new JsonPath(FetchResponce);
     	String address=js1.getString("address");
     	System.out.println(address);
		
		
	}

}
