package Library_API;

import org.testng.Assert;
import org.testng.annotations.Test;

import GoogleMap_API.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson 
{
	@Test
    public void addBook()
	{
		RestAssured.baseURI="http://216.10.245.166";
		
		//ADD Book
		String responce=given().header("Content-Type","application/json").body(Payload.AddBookPayload("vishal1","14234"))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js = reusableMethod.rawToJson(responce);
		String actualMessage = js.getString("Msg");
		String id=js.get("ID");
		String expectedMessage="successfully added";
		Assert.assertEquals(actualMessage, expectedMessage,"TC Failed:Message Mismatch");
		
		
		//Delete Book
		String res=given().body(Payload.DeleteBookPayload(id))
		.when().delete("Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1=reusableMethod.rawToJson(res);
		String Actmes=js1.getString("msg");
        String Expmes="book is successfully deleted";
        Assert.assertEquals(Actmes, Expmes,"Tc Failed: Book Not Deleted");
		
	}

}
