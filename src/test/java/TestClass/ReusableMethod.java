package TestClass;

import io.restassured.path.json.JsonPath;

public class ReusableMethod 
{
	public static JsonPath rawToJason(String responce)
	{
		JsonPath js1=new JsonPath(responce);
		return js1;
	}

}
