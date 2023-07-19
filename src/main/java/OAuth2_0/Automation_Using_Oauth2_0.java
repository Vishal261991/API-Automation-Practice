package OAuth2_0;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
public class Automation_Using_Oauth2_0 
{
	@Test
	public void OAuth2_0_Verfication()
	{
	
		String url ="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AZEOvhVcfM61UE5-uUKg5-8mkUhJ8Q6zAHP10-y9N6RWkABN9Rye78hGbyOQS3gqK2Iaeg&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";

//		String []ar=url.split("code=");
//		String url1=ar[1];
//		String [] ar1=url1.split("&scope");
//		String code=ar1[0];
//		
//		System.out.println(code);
//
//		String AccessTokenresponse=given().urlEncodingEnabled(false).queryParams("code",code)
//		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
//		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
//		.queryParams("grant_type","authorization_code")
//		.queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
//		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
//		.when().log().all()
//		.post("https://www.googleapis.com/oauth2/v4/token").asString();
//		
//		JsonPath js=new JsonPath(AccessTokenresponse);
//		String token=js.getString("access_token");
//		System.out.println(token);
//		
//
//		
//		
//
//		String response=given().contentType("application/json").queryParam("Access token", token).expect().defaultParser(Parser.JSON)
//		.when().post("https://rahulshettyacademy.com/getCourse.php").asString();
//		
//		System.out.println(response);

		
		
		String partialcode=url.split("code=")[1];

		String code=partialcode.split("&scope")[0];

		System.out.println(code);

		String response =

				given() 

				.urlEncodingEnabled(false)

				.queryParams("code",code)

				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

				.queryParams("grant_type", "authorization_code")

				.queryParams("state", "verifyfjdss")

				.queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")

				// .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")

				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")

				.when().log().all()

				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		// System.out.println(response);

		JsonPath jsonPath = new JsonPath(response);

		String accessToken = jsonPath.getString("access_token");

		System.out.println(accessToken);

		String r2=    given().contentType("application/json").

				queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)

				.when()

				.get("https://rahulshettyacademy.com/getCourse.php")

				.asString();

		System.out.println(r2);

		}
		
		
		
	}

