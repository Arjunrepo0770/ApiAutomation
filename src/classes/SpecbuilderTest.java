package classes;


import io.restassured.builder.RequestSpecBuilder;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Googleapi;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SpecbuilderTest {
	public static void main(String[] args) {
		
		
		Googleapi gp = new Googleapi();
		gp.setAccuracy(50);
		gp.setAddress("29, side layout, cohen 09");
		gp.setLanguage("French-IN");
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		gp.setLocation(l);
		gp.setName("Frontline house");
		gp.setPhone_number("(+91) 983 893 3937");
		gp.setWebsite("http://google.com");
		List<String> mylist = new ArrayList<String>();
		
		mylist.add("shoe park");
		mylist.add("shop");
		gp.setTypes(mylist);
		
		
		 RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
				 						addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		 
		 RequestSpecification req2 = given().spec(req).body(gp);
		 
		 
		 ResponseSpecification responspec = new ResponseSpecBuilder().expectStatusCode(200).
					expectContentType(ContentType.JSON).build();
		 
		 Response response =req2.when().post("/maps/api/place/add/json")
		 .then().spec(responspec).statusCode(200).extract().response();
		 
		 
		 System.out.println(response.asString());

		
	}
	}


