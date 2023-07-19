package Library_API;

public class Payload
{
	public static String AddBookPayload(String isbn, String aisle)
	{
		String payload="{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"John foe\"\r\n"
				+ "}\r\n"
				+ "";
		return payload;
	}
	
	public static String DeleteBookPayload(String Id)
	{
		String DeletePayload="{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+Id+"\"\r\n"
				+ " \r\n"
				+ "} \r\n"
				+ "";
		return DeletePayload;
		
	}

}
