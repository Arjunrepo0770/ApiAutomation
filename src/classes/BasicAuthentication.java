package classes;

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class BasicAuthentication {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://mtelugu2009.atlassian.net/";
		String Response =given().log().all()
		.headers("Content-Type","application/json")
		.headers("Authorization","Basic bXRlbHVndTIwMDlAZ21haWwuY29tOkFUQVRUM3hGZkdGMEltdU9Fdzd5cTRVdkctVzZRRWduT0VCT20zaHhQS2JyRGtIRFB5elhGVE02Q1JwYlg2TFdpUUFkVXB6ZG9hMW9FSjJjYXJDaE0tOWNiRWczbEZRRF84ckNiU3QxWDFxb3owQlE1a25kd2Vrei10TXlBaTVhVDFNX28yY3FGSDc0QzRIYUlXYVUtVVlRNDkzWHVYVnd3RXRORDJ0ME9aVXBfaWpUaG13WW9pdz0zQzgyMzVEMw==")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"imp items is not working- rest new Automation\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}")
		.when().post("rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		
		JsonPath js = new JsonPath(Response);
		String id =js.getString("id");
		System.out.println(id);
		
		//Add Attachment
		String fileresponse= given().pathParam("key", id)
		.headers("Authorization","Basic bXRlbHVndTIwMDlAZ21haWwuY29tOkFUQVRUM3hGZkdGMEltdU9Fdzd5cTRVdkctVzZRRWduT0VCT20zaHhQS2JyRGtIRFB5elhGVE02Q1JwYlg2TFdpUUFkVXB6ZG9hMW9FSjJjYXJDaE0tOWNiRWczbEZRRF84ckNiU3QxWDFxb3owQlE1a25kd2Vrei10TXlBaTVhVDFNX28yY3FGSDc0QzRIYUlXYVUtVVlRNDkzWHVYVnd3RXRORDJ0ME9aVXBfaWpUaG13WW9pdz0zQzgyMzVEMw==")
		.headers("X-Atlassian-Token","no-check")
		.multiPart("file",new File("C:\\Users\\arjun\\Downloads\\Mohini resume.docx"))
		.when().post("rest/api/3/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();

		JsonPath js1	 = new JsonPath(fileresponse);
		String filename =js1.getString("filename");
		System.out.println(filename);
		System.out.println(filename);
		System.out.println(filename);
		
		//get issue details
		String getresponse= given().pathParam("key", id)
		.headers("Authorization","Basic bXRlbHVndTIwMDlAZ21haWwuY29tOkFUQVRUM3hGZkdGMEltdU9Fdzd5cTRVdkctVzZRRWduT0VCT20zaHhQS2JyRGtIRFB5elhGVE02Q1JwYlg2TFdpUUFkVXB6ZG9hMW9FSjJjYXJDaE0tOWNiRWczbEZRRF84ckNiU3QxWDFxb3owQlE1a25kd2Vrei10TXlBaTVhVDFNX28yY3FGSDc0QzRIYUlXYVUtVVlRNDkzWHVYVnd3RXRORDJ0ME9aVXBfaWpUaG13WW9pdz0zQzgyMzVEMw==")
		.when().get("rest/api/3/issue/{key}")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath jsw	 = new JsonPath(getresponse);
		String filenameq =jsw.getString("fields.attachment.filename");
		System.out.println(filenameq);
	}

	

		
	
	
	
}
