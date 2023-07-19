package Library_API;

import io.restassured.path.json.JsonPath;

public class reusableMethod 
{
	public static JsonPath rawToJson(String responce)
	{
		JsonPath js=new JsonPath(responce);
		return js;
	}

}
