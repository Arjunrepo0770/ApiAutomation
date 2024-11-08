package classes;

import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class Sumofcourses {
	
	@Test
	public void addingCourses() {
		
		JsonPath js  = new JsonPath(payload.Courseprice());
		int coursescount =js.getInt("courses.size()");
		int sum =0;
		//Verify if Sum of all Course prices matches with Purchase Amount
		int realpurchaseamount = js.getInt("dashboard.purchaseAmount");
		System.out.println(realpurchaseamount);
		
		for(int i=0;i<coursescount;i++)
		{
			int actualpurchaseamount=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			int muliply=actualpurchaseamount*copies;
			sum=sum+muliply;
			
			}
		System.out.println(sum);
		if(sum==realpurchaseamount) {
			System.out.println("input and output matched");
		}				
	}

}
