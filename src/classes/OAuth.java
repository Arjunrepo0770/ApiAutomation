package classes;


import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import pojo.Getcourse;
import pojo.WebAutomation;
import pojo.api;
import pojo.mobile;

public class OAuth {
	
	public static void main(String[] args) {
	
		String[]  courcetitles = {"Selenium Webdriver Java","Cypress","Protractor"};
		String response = given().log().all()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String access_Token= js.getString("access_token");
		
		System.out.println(access_Token);
		
		 Getcourse gc = given().queryParam("access_token", access_Token)
				.when().log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
				.as(Getcourse.class);
		 		System.out.println(gc.getinstructor());	
				System.out.println(gc.getUrl());	
				System.out.println(gc.getServices());	
				System.out.println(gc.getExpertise());
				System.out.println(gc.getLinkedIn());
			
				
				List<WebAutomation> webautomationcourses = gc.getCourses().getWebAutomation();
				
				for(int i=0;i<webautomationcourses.size();i++) {
					if(webautomationcourses.get(i).getCourseTitle().equalsIgnoreCase("Cypress"))
					{
						System.out.println(webautomationcourses.get(i).getPrice());
					}
					
				}				
				List<api> apicourse =gc.getCourses().getApi();
				for(int i=0;i<apicourse.size();i++) {
					if(apicourse.get(i).getCourseTitle().equalsIgnoreCase("Rest Assured Automation using Java"))
					{
						System.out.println(apicourse.get(i).getPrice());
					}
				}
				List<mobile> mobilecourse =gc.getCourses().getMobile();
				for(int i=0;i<mobilecourse.size();i++) {
					if(mobilecourse.get(i).getCourseTitle().equalsIgnoreCase("Appium-Mobile Automation using Java"))
					{
						System.out.println(mobilecourse.get(i).getPrice());
					}
				}
				
				List<WebAutomation> webautomationcourse= gc.getCourses().getWebAutomation();
				for(int i =0;i<webautomationcourse.size();i++) {
					System.out.println(webautomationcourse.get(i).getCourseTitle());
				}
				
				ArrayList<String> actual = new ArrayList<String>();
				
				List<WebAutomation> w = gc.getCourses().getWebAutomation();
				for(int j=0;j<w.size();j++) {
					actual.add(w.get(j).getCourseTitle());
				}
				List<String> expected =Arrays.asList(courcetitles);
				
				Assert.assertEquals(actual.equals(expected), true);
				}
	}


