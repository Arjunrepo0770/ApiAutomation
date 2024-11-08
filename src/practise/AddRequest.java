package practise;

import java.util.ArrayList;

import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import practisepojos.Location;
import practisepojos.AddPayload;
import static io.restassured.RestAssured.*;

public class AddRequest {
	
	
public static void main(String[ ] args) {
	
	AddPayload ap = new AddPayload();
	ap.setAccuracy(50);
	ap.setName("Arjun");
	ap.setPhone_number("(+91) 983 893 3937");
	ap.setAddress("29, side layout, cohen 09");
	ap.setWebsite("http://google.com");
	ap.setLanguage("French-IN");
	
	List<String> types = new ArrayList<String>();
	types.add("shoe park");
	types.add("shop");
	ap.setTypes(types);
	Location l = new Location();
	l.setLat(-38.383494);
	l.setLng(33.427362);
	ap.setLocation(l);
	
	
	
	RequestSpecification httprequest = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
										.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
	
	RequestSpecification requestBody = given().spec(httprequest).body(ap);
	
	ResponseSpecification resp = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
	
	
	String response = requestBody.when().post("/maps/api/place/add/json")
	.then().spec(resp).extract().response().asString();

	JsonPath js = new JsonPath(response);
	String place_id =js.get("place_id");
	System.out.println(place_id);

	
	RequestSpecification Gethttprequest = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addQueryParam("key", "qaclick123").addQueryParam("place_id", place_id).setContentType(ContentType.JSON).build();
	
	
	RequestSpecification GetrequestBody =given().spec(Gethttprequest);
	
	ResponseSpecification getresp = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
	
	String getresponce = GetrequestBody.when().get("/maps/api/place/get/json")
	.then().spec(getresp).extract().response().asString();
	
	System.out.println(getresponce);
	
	
	RequestSpecification Deletehttprequest = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addQueryParam("key", "qaclick123").addQueryParam("place_id", place_id).setContentType(ContentType.JSON).build();
	
	RequestSpecification deleterequestBody =given().spec(Deletehttprequest);
	
ResponseSpecification deleresp = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
	
	String deleteresponce = deleterequestBody.when().delete("/maps/api/place/delete/json")
	.then().spec(deleresp).extract().response().asString();
	
	System.out.println(deleteresponce);
	
	
	
	
}



}
