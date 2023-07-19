package Library_API;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class UsingDataProviserForMultipleTestdata
{
	@Test(dataProvider = "booksData")
	public void addBook(String isbn,String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		//ADD Book
		String responce=given().header("Content-Type","application/json").body(Payload.AddBookPayload(isbn,aisle))
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

	}
	
	@DataProvider(name="booksData")
	public Object[][] GetData()
	{
	  return new Object [][] {{"Micros","9123"},{"Appl","9184"},{"Moto","9147"}};
		
	}

}
