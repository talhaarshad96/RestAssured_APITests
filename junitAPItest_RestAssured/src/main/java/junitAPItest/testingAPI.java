package junitAPItest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import groovyjarjarasm.asm.commons.Method;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class testingAPI 
{

	//@Test
	public void GetWeatherDetails()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");

		// Get the status code from the Response. In case of 
		// a successfull interaction with the web service, we
		// should get a status code of 200.
		int statusCode = response.getStatusCode();

		// Assert that correct status code is returned.
		assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
	}

	//@Test
	public void test_NumberOfCircuitsFor2017Season_ShouldBe20() 
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");

		// Get the status code from the Response. In case of 
		// a successfull interaction with the web service, we
		// should get a status code of 200.
		int statusCode = response.getStatusCode();

		// Assert that correct status code is returned.
		assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
	}


	//@Test
	public void IteratingOverHeaders()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");

		// Get all the headers. Return value is of type Headers.
		// Headers class implements Iterable interface, hence we
		// can apply an advance for loop to go through all Headers
		// as shown in the code below
		Headers allHeaders = response.headers();

		// Iterate over all the Headers
		for(Header header : allHeaders)
		{
			System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
		}
	}


	//@Test
	public void GetWeatherHeaders()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");

		// Reader header of a give name. In this line we will get
		// Header named Content-Type
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType /* actual value */, "application/json" /* expected value */);

		// Reader header of a give name. In this line we will get
		// Header named Server
		String serverType =  response.header("Server");
		Assert.assertEquals(serverType /* actual value */, "nginx" /* expected value */);

		// Reader header of a give name. In this line we will get
		// Header named Content-Encoding
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding /* actual value */, "gzip" /* expected value */);
	}


	//@Test
	public void WeatherMessageBody()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");

		// Retrieve the body of the Response
		ResponseBody body = response.getBody();

		// By using the ResponseBody.asString() method, we can convert the  body
		// into the string representation.
		System.out.println("Response Body issssss: " + body.asString());
	}


	//@Test
	public void WeatherMessageBodyTESTMATCH()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");

		// Retrieve the body of the Response
		ResponseBody body = response.getBody();

		// To check for sub string presence get the Response body as a String.
		// Do a String.contains
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("Hyderabad") /*Expected value*/, true /*Actual Value*/);
	}

	//@Test
	public void VerifyCityInJsonResponse()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");

		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Then simply query the JsonPath object to get a String value of the node
		// specified by JsonPath: City (Note: You should not put $. in the Java code)
		String city = jsonPathEvaluator.get("City");

		// Let us print the city variable to see what we got
		System.out.println("cCity received from Response " + city);

		// Validate the response
		//Assert.assertEquals(city, "Hyderabad", "Correct city name received in the Response");
		Assert.assertEquals(city, "Hyderabad");

	}

	//@Test
	public void DisplayAllNodesInWeatherAPI()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");

		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Let us print the city variable to see what we got
		System.out.println("cCity received from Response " + jsonPathEvaluator.get("City"));

		// Print the temperature node
		System.out.println("tTemperature received from Response " + jsonPathEvaluator.get("Temperature"));

		// Print the humidity node
		System.out.println("hHumidity received from Response " + jsonPathEvaluator.get("Humidity"));

		// Print weather description
		System.out.println("wWeather description received from Response " + jsonPathEvaluator.get("WeatherDescription"));

		// Print Wind Speed
		System.out.println("cCity received from Response " + jsonPathEvaluator.get("WindSpeed"));

		// Print Wind Direction Degree
		System.out.println("cCity received from Response " + jsonPathEvaluator.get("WindDirectionDegree"));
	}



	//@Test
	public void AuthenticationBasics1()
	{
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.post("http://focusync.tarramis.api.bsolit.com/api/Dashboard/Index");
		int code = RestAssured.given()
				.header("Content-Type","application/json")
				.header("Token",1)
				.header("UserHostAddress","192.168.0.114")
				.header("device-type","mobile")
				.header("LicenseKey","6ab4d7ce-7f8e-432d-98ce-ed36231213d4")
				.when()
				.post("http://focusync.tarramis.api.bsolit.com/api/Dashboard/Index")
				.getStatusCode();
		ResponseBody body = response.getBody();
		System.out.println(code);

		System.out.println("Response Body issssss: " + body.asString());
	}

	//@Test
	public void SomethingGET()
	{
		RestAssured.baseURI = "https://reqres.in";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/api/users?page=2");

		// Retrieve the body of the Response
		ResponseBody body = response.getBody();

		// By using the ResponseBody.asString() method, we can convert the  body
		// into the string representation.
		System.out.println("Response Body issssss: " + body.asString());
	}
	//@Test
	public void SomethingPOST()
	{
		RestAssured.baseURI = "https://reqres.in";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.post("/api/users");

		// Retrieve the body of the Response
		ResponseBody body = response.getBody();

		// By using the ResponseBody.asString() method, we can convert the  body
		// into the string representation.
		System.out.println("Response Body issssss: " + body.asString());
	}
	//@Test
	public void postExample() //working fine
	{
		//String myJson = "{\"name\":\"Jimi Hendrix\"}";
		RestAssured.baseURI  = "https://reqres.in";	

		Response r = given()
				//.contentType("application/json").
				.body("{\"name\":\"morpheus\"}").//.body("{\"job\":\"leader\"}").
				when().
				post("/api/users");

		String body = r.getBody().asString();
		System.out.println(body);

		int statusCode = r.getStatusCode();

		// Assert that correct status code is returned.
		assertEquals(statusCode /*actual value*/, 201 /*expected value*/, "Correct status code returned");

	}
		
	//@Test	
	public void postExample1() //working post method sending body and matching status code and response.
	{
		//String myJson = "{\"name\":\"Jimi Hendrix\"}";
		RestAssured.baseURI  = "https://reqres.in";	

		Response r = given()
				//.contentType("application/json").
				.body("{\"name\":\"morpheus\"}").//.body("{\"job\":\"leader\"}").
				when().
				post("/api/users");

		String body = r.getBody().asString();
		System.out.println(body);

		int statusCode = r.getStatusCode();

		// Assert that correct status code is returned.
		assertEquals(statusCode /*actual value*/, 201 /*expected value*/, "Correct status code returned");
		
		String bodyAsString = r.asString();
		Assert.assertEquals(bodyAsString.contains("2019-10-") /*Expected value*/, true /*Actual Value*/);
	}
	
	//@Test	
	public void postExample2() //Everything is done.
	{
		RestAssured.baseURI  = "http://focusync.tarramis.api.bsolit.com";	

		Response r = given()
				//.contentType("application/json").
				.body("{\"ReportingDate\":\"31-Mar-2018\"}").//.body("{\"job\":\"leader\"}").
				header("Content-Type","application/json").
				header("Token",1).
				header("UserHostAddress","192.168.0.114").
				header("device-type","mobile").
				header("LicenseKey","6ab4d7ce-7f8e-432d-98ce-ed36231213d4").
				when().
				post("/api/Dashboard/Index");

		String body = r.getBody().asString();
		System.out.println(body);

		int statusCode = r.getStatusCode();

		// Assert that correct status code is returned.
		assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
		
		String bodyAsString = r.asString();
		Assert.assertEquals(bodyAsString.contains("Pakistan Rupee") /*Expected value*/, true /*Actual Value*/);
	}
	
	@Test	
	public void postExample21() //Everything is done. //making body in string/array
	{
		RestAssured.baseURI  = "http://focusync.tarramis.api.bsolit.com";	
		//String APIBody = "{}";// //e.g.- "{\"key1\":\"value1\",\"key2\":\"value2\"}"
		String APIBody = "{\"ReportingDate\":\"31-Mar-2018\"}";
	//	String HEADER = " "Content-Type","application/json" ";
		
		Response r = given()
				//.contentType("application/json").
				.body(APIBody).//.body("{\"job\":\"leader\"}").
				header("Content-Type","application/json").
				header("Token",1).
				header("UserHostAddress","192.168.0.114").
				header("device-type","mobile").
				header("LicenseKey","6ab4d7ce-7f8e-432d-98ce-ed36231213d4").
				when().
				post("/api/Dashboard/Index");

		String body = r.getBody().asString();
		System.out.println(body);

		int statusCode = r.getStatusCode();

		// Assert that correct status code is returned.
		assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
		
		String bodyAsString = r.asString();
		Assert.assertEquals(bodyAsString.contains("Pakistan Rupee") /*Expected value*/, true /*Actual Value*/);
	}
	
	//@Test	
	public void postDIGITALBANK_Beneficiary() //done all headers and all.
	{
		RestAssured.baseURI  = "http://37.187.142.142/digitalbanking.api";	

		Response r = given()
				//.contentType("application/json").
				.body("{}")//.body("{\"Password\":\"Alsalam19\"}").
				.header("Content-Type","application/json").
				header("user-host-address","::::0").
				header("user-host-name","salman").
				header("device-type","mobile").
				header("user-agent","postman").
				header("device-id",1).
				header("user-language","English").
				header("license-key","EF834317-1486-48E6-91EC-04D76FF720B8").
				when().
				post("http://37.187.142.142/digitalbanking.api/customer/Beneficiary");

		String body = r.getBody().asString();
		System.out.println(body);

		int statusCode = r.getStatusCode();

		// Assert that correct status code is returned.
		assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
		
		String bodyAsString = r.asString();
		Assert.assertEquals(bodyAsString.contains("45") /*Expected value*/, true /*Actual Value*/);
		Assert.assertEquals(bodyAsString.contains("BHD") /*Expected value*/, true /*Actual Value*/);
	}
	
	//@Test	
	public void postDIGITALBANK_Beneficiary1() //done all headers and all. Make ARRAY
	{
		/* String payload = "{\n" +
		        "  \"description\": \"Some Description\",\n" +
		        "  \"id\": \"Some id\",\n" +
		        "  \"name\": \"Some name\"\n" +	
		        "}";	 */
		 String APIBody = "{}";// //e.g.- "{\"key1\":\"value1\",\"key2\":\"value2\"}"
		RestAssured.baseURI  = "http://37.187.142.142/digitalbanking.api";	

		Response r = given()
				//.contentType("application/json").
				.body(APIBody)//.body("{\"Password\":\"Alsalam19\"}").
				.header("Content-Type","application/json").
				header("user-host-address","::::0").
				header("user-host-name","salman").
				header("device-type","mobile").
				header("user-agent","postman").
				header("device-id",1).
				header("user-language","English").
				header("license-key","EF834317-1486-48E6-91EC-04D76FF720B8").
				when().
				post("http://37.187.142.142/digitalbanking.api/customer/Beneficiary");

		String body = r.getBody().asString();
		System.out.println(body);

		int statusCode = r.getStatusCode();

		// Assert that correct status code is returned.
		assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
		
		String bodyAsString = r.asString();
		Assert.assertEquals(bodyAsString.contains("45") /*Expected value*/, true /*Actual Value*/);
		Assert.assertEquals(bodyAsString.contains("BHD") /*Expected value*/, true /*Actual Value*/);
	}
	
	
	

	//2019-10-15T07:49:06.485Z
	/*@Test
	public void httpPost() throws JSONException,InterruptedException {

		//Initializing Rest API's URL
		String APIUrl = "http://{API URL}";

		//Initializing payload or API body
		String APIBody = "{API Body}"; //e.g.- "{\"key1\":\"value1\",\"key2\":\"value2\"}"

		// Building request using requestSpecBuilder
		RequestSpecBuilder builder = new RequestSpecBuilder();

		//Setting API's body
		builder.setBody(APIBody);

		//Setting content type as application/json or application/xml
		builder.setContentType("application/json; charset=UTF-8");

		RequestSpecification requestSpec = builder.build();

		//Making post request with authentication, leave blank in case there are no credentials- basic("","")
		Response response = given().spec(requestSpec).when().post(APIUrl);
		JSONObject JSONResponseBody = new JSONObject(response.body().asString());

		//Fetching the desired value of a parameter
		String result = JSONResponseBody.getString({key});

		//Asserting that result of Norway is Oslo
		Assert.assertEquals(result, "{expectedValue}");

		}*/

	//@Test
	public void checkCityForZipCode() {

		given().
		pathParam("country","us").
		pathParam("zipcode","90210").
		when().
		get("http://api.zippopotam.us/{country}/{zipcode}").
		then().
		assertThat().
		body("places.'place name'[0]",equalTo("Beverly Hills"));
	}

	/*//@Test
	public void httpPostMethod() throws JSONException,InterruptedException {

	 //Rest API's URL
	 String restAPIUrl = "http://{URL of API}";

	 //API Body
	 String apiBody = "{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\"}";

	 // Building request by using requestSpecBuilder
	 RequestSpecBuilder builder = new RequestSpecBuilder();

	 //Set API's Body
	 builder.setBody(apiBody);

	 //Setting content type as application/json
	 builder.setContentType("application/json; charset=UTF-8");

	 RequestSpecification requestSpec = builder.build();

	 //Making post request with authentication or leave blank if you don't have credentials like: basic("","")
	 Response response = given().authentication().preemptive().basic({username}, {password})
	 .spec(requestSpec).when().post(restAPIUrl);

	 JSONObject JSONResponseBody = new JSONObject(response.body().asString());

	 //Get the desired value of a parameter
	 String result = JSONResponseBody.getString({key});

	 //Check the Result
	 Assert.assertEquals(result, "{expectedValue}");

	 }*/
}
