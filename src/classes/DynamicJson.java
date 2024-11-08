package classes;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	
	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI="http://216.10.245.166";
		String response =given().header("Content-Type","application/json")
		.body(payload.addBook(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String id = js.get("ID");
	System.out.println(id);
	}
	
	
	@DataProvider(name="BooksData")
	public Object[][] bookData() {
		//array=multiple elements can be stored
		//multi dimension array = collection of arrays
		return new Object[][] {{"sdfsd","1232"},{"ggdze","6443"},{"kkdff","9943"}};
		
	}
}





