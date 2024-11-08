package classes;
import io.restassured.RestAssured;


import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;



public class Basics {

	public static void main(String[] args) throws IOException {
		//validate if add place pi is working as expected
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key","qaclick123").header("Contenet-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\arjun\\OneDrive\\Documents\\Addresource.json"))))
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.extract().response().asString();
		//System.out.println(response);
		JsonPath js = new JsonPath(response);
		String place_id = js.getString("place_id");
		System.out.println(place_id);
		
		
		//PUT http method
		
		String newaddress = "70 Summer walk, USA";
		
		given().log().all().queryParam("key","qaclick123").header("content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+place_id+"\",\r\n"
				+ "\"address\":\""+newaddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "").
		when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		// get http method
		
		String getresponse =given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id",place_id)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200)
		.extract().response().asString();
		
		System.out.println(getresponse);
		
		JsonPath jsp = new JsonPath(getresponse); 
		String actualaddress = jsp.getString("address");
		System.out.println(actualaddress);
		Assert.assertEquals(actualaddress, newaddress);
	}
}
