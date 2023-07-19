package GoogleMap_API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class Add_update_get_location
{
	public static void main(String[] args) 
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		//ADD Place
		String responce=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.addPalce())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
		
		JsonPath js=new JsonPath(responce);
		String placeId=js.getString("place_id");
		
		//Update Place
		String Address="70 winter walk, Gargoti";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
     	.body("{\r\n"
     			+ "\"place_id\":\""+placeId+"\",\r\n"
     			+ "\"address\":\""+Address+"\",\r\n"
     			+ "\"key\":\"qaclick123\"\r\n"
     			+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
		
		//Fetch Updated Place
		String respo=given().log().all().queryParam("key", "qaclick123").queryParam("place_id",""+placeId+"")
		     	.when().get("maps/api/place/get/json")
		     	.then().log().all().assertThat().statusCode(200).body("address", equalTo("70 winter walk, Gargoti")).extract().response().asString();
		
		JsonPath js1 = ReusableMethod.rawToJason(respo);
		String address=js1.getString("address");
	
		Assert.assertEquals(Address, address,"TC Failed:Address Not Updated");
	 
		
		
		
	}

}
