package classes;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.Googleapi;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class serializetest {
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
		
				
		RestAssured.baseURI="https://rahulshettyacademy.com";
		Response Response = given().log().all().queryParam("key", "=qaclick123")
		.body(gp)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		
		System.out.println(Response.asString());
		
		
	}
	}


