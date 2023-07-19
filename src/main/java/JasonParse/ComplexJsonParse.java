package JasonParse;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse
{
	public static void main(String[] args) 
	{
		JsonPath js=new JsonPath(payload.cousePrice());
		
		//Print No of courses returned by API
		int TotalCourses=js.getInt("courses.size()");
		System.out.println(TotalCourses);
		
		//Print Purchase Amount
		int amount=js.getInt("dashboard.purchaseAmount");
		System.out.println(amount);
		
		//Print Title of the first course
		String firstTitle = js.getString("courses[0].title");
		System.out.println(firstTitle);
		
		
		//Print All course titles and their respective Prices
		System.out.println("----Print All course titles and their respective Prices----");
		for(int i=0;i<TotalCourses;i++)
		{
			String titles=js.getString("courses["+i+"].title");
			System.out.println(titles);
			int prices=js.getInt("courses["+i+"].price");
			System.out.println(prices);
		}
		
		//Print no of copies sold by RPA Course
		System.out.println("----Print no of copies sold by RPA Course----");
		
		for(int i=0;i<TotalCourses;i++)
		{
			String title=js.getString("courses["+i+"].title");
			if(title.equalsIgnoreCase("RPA"))
			{
				int copies=js.get("courses["+i+"].copies");
				System.out.println(copies);
				
			}
		}
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		System.out.println("----Verify if Sum of all Course prices matches with Purchase Amount----");
		int sum=0;
		for(int i=0;i<TotalCourses;i++)
		{
			int price=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			sum=sum+(price*copies);
		}
		 int purAmount=js.get("dashboard.purchaseAmount");
		 System.out.println("Purches Amount :"+purAmount);
		 System.out.println("Actual Amount :"+sum);
		 Assert.assertEquals(sum, purAmount,"Tc Failed:Amount Mismatches");
		 
	
		
		
		
	}

}
