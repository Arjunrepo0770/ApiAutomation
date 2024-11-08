package classes;

import files.payload;

import io.restassured.path.json.JsonPath;

public class ComplexJsons {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Print No of courses returned by API
		JsonPath js = new JsonPath(payload.Courseprice());
		int coursescount = js.getInt("courses.size()");
		System.out.println(coursescount);
		
		//Print Purchase Amount
		int purchaseamount =js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseamount);
		
		
		//Print Title of the first course"
		String firstcourse = js.getString("courses[0].title");
		System.out.println(firstcourse);
		
		//Print All course titles and their respective Prices
		for(int i=0;i<coursescount;i++)
		{
			System.out.println(js.getString("courses["+i+"].title").toString());
			System.out.println(js.getString("courses["+i+"].price").toString());
		}
		
		//Print no of copies sold by RPA Course
		
		for(int i=0;i<coursescount;i++)
		{
			String coursename =js.getString("courses["+i+"].title");
			if(coursename.equalsIgnoreCase("RPA")) {
				String copyname =js.getString("courses["+i+"].copies");
				System.out.println(copyname);
				break;
			}
		}
		
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
