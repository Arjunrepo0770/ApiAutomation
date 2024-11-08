package Ecommerce;


import io.restassured.builder.RequestSpecBuilder;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.OrderDetails;
import pojo.Orders;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Login {

	public static void main(String[] args) {
		
		RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		
		LoginRequest login = new LoginRequest();
		login.setUserEmail("mtelugu2009@gmail.com");
		login.setUserPassword("Arjun@123");
		
		RequestSpecification reqlogin = given().log().all().spec(req).body(login);
		
		 LoginResponse res= reqlogin.when().post("/api/ecom/auth/login").then().statusCode(200).extract().response()
				 .as(LoginResponse.class);
		 
		 
		 String token =res.getToken();
		 String userId = res.getUserId();
		 		 
		 
		 //Create product
		 
		 RequestSpecification addProductBaseReq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				 .addHeader("Authorization", token).build();
		 
		
		 RequestSpecification addproduct =given().log().all().spec(addProductBaseReq)
		.param("productName", "addidas")
		.param("productAddedBy", userId)
		.param("productCategory", "fashion")
		.param("productSubCategory", "shirts")
		.param("productPrice", "11")
		.param("productDescription", "nike originals")
		.param("productFor", "men")
		.multiPart("productImage",new File("C:\\Users\\arjun\\Downloads\\laptops.png"));
		 
		  String addproductresp = addproduct.when().post("/api/ecom/product/add-product")
		 .then().log().all().extract().response().asString();
		  
		  
		  JsonPath js = new JsonPath(addproductresp);
		  
		 String productid = js.get("productId");
		  
		  System.out.println(productid);
		 
		 //Create order
		  
		  RequestSpecification createorderReq= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
					 .addHeader("Authorization", token).build();		
			
		OrderDetails orderdetails = new OrderDetails();
		orderdetails.setCountry("India");
		orderdetails.setProductOrderedId(productid);
		
		List<OrderDetails> orderdeailslist = new ArrayList<OrderDetails>();
		orderdeailslist.add(orderdetails);
		
		Orders orders = new Orders();
		orders.setOrders(orderdeailslist);
		
		  RequestSpecification orderReq = given().log().all().spec(createorderReq).body(orders); 
		  
		  String Orderdetailss= orderReq.when().log().all().post("/api/ecom/order/create-order")
		  .then().extract().response().asString();
		  
		  System.out.println(Orderdetailss);
	
		  
		  //Delete Product
		  
		  RequestSpecification deleteorderbase= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
					 .addHeader("Authorization", token).setContentType(ContentType.JSON).build();	
		  
		String Deleteresponse=  given().log().all().spec(deleteorderbase).pathParam("productId", productid)
		  .when().delete("/api/ecom/product/delete-product/{productId}")
		  .then().log().all().extract().response().asString();
		
		System.out.println(Deleteresponse);
		  
	}

}
