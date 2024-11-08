package practise;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import practisepojos.OrderDetails;
import practisepojos.Orders;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Ecommerce {
	
	public static void main(String[] args) {
		
		//login into the application
		Resources r = new Resources();
		r.setUserEmail("mtelugu2009@gmail.com");
		r.setUserPassword("Arjun@123");
		
		RequestSpecification httprequest = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
				setContentType(ContentType.JSON).build();
		
		RequestSpecification request = given().log().all().spec(httprequest).body(r);
		ResponseSpecification response = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
		
		 String finalresp = request.when().post("/api/ecom/auth/login")
				 	.then().spec(response).extract().response().asString();
		 JsonPath js = new JsonPath(finalresp);
		 String token =js.get("token");
		 String userId = js.get("userId");
		 
		 
		//create product
		 RequestSpecification Createproducthttprequest = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
				addHeader("Authorization", token).build(); 
		 RequestSpecification Createproductreq = given().log().all().spec(Createproducthttprequest)
				 
				 .param("productName", "Addidas")
				 .param("productAddedBy",userId)
				 .param("productCategory", "fashion")
				 .param("productSubCategory","shoes")
				 .param("productPrice", "2000")
				 .param("productDescription", "adidas style")
				 .param("productFor", "men")
				 .multiPart("productImage",new File("C:\\Users\\arjun\\Downloads\\laptops.png"));
		 
		 String productresponse = Createproductreq.when().post("/api/ecom/product/add-product")
				 			.then().extract().response().asString();
		 System.out.println(productresponse);
		 
		 JsonPath js1 = new JsonPath(productresponse);
		 String productid =js1.get("productId");
		 System.out.println(productid);
		 
		 
		 //add a product to cart
		 
		 RequestSpecification addproductrequest  = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				 .addHeader("Authorization", token).build();
		 
		 OrderDetails orderdetails = new OrderDetails();
		 orderdetails.setCountry("India");
		 orderdetails.setProductOrderedId(productid);
		 
		List<OrderDetails> orderlist = new ArrayList<OrderDetails>();
		orderlist.add(orderdetails);
		
		Orders orders = new Orders();
		orders.setOrders(orderlist);
		
		
		 RequestSpecification addproduct = given().spec(addproductrequest).log().all().body(orders);
		 
		 
		 String Orderdetailss= addproduct.when().log().all().post("/api/ecom/order/create-order")
				  .then().extract().response().asString();
				  
		 System.out.println(Orderdetailss);
		 
		 
		 
		 
		 
		 
		 
		 
		
		
		
		
		
			
		 
					 
	}

}
