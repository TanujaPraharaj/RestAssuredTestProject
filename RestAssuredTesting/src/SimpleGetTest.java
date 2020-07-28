
import io.restassured.response.ResponseBody;

import org.json.JSONException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class SimpleGetTest {

        @Test
        public void TestGetResponseCode()
        {
            // Specify the base URL to the RESTful web service
            RestAssured.baseURI = "http://demo4032024.mockable.io/apitest";

            // Get the RequestSpecification of the request that you want to sent
            // to the server. The server is specified by the BaseURI that we have
            // specified in the above step.
            RequestSpecification httpRequest = RestAssured.given();

            // Make a request to the server by specifying the method Type and the method URL.
            // This will return the Response from the server. Store the response in a variable.
            Response response = httpRequest.request(Method.GET, "http://demo4032024.mockable.io/apitest");
            int statusCode = response.getStatusCode();

            // Assert that correct status code is returned.
            Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");

            // Now let us print the body of the message to see what response
            // we have recieved from the server
            String responseBody = response.getBody().asString();
            System.out.println("Response Body is =>  " + responseBody);

        }
    @Test
    public void TestGetResponseHeaders()
    {
        RestAssured.baseURI = "http://demo4032024.mockable.io/apitest";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("http://demo4032024.mockable.io/apitest");

        // Reader header of a give name. In this line we will get
        // Header named Content-Type
        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType /* actual value */, "application/json; charset=UTF-8" /* expected value */);

           }


   @Test
    public void testRequestBodywithJson() throws JSONException {
        RestAssured.baseURI ="http://demo4032024.mockable.io/apitest/";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams;
       requestParams = new JSONObject();
       requestParams.put("Status", "200"); // Cast
        requestParams.put("Age", "25");
        requestParams.put("Role", "QA Automation Developer");
        requestParams.put("DOB", "25-02-1994");
        requestParams.put("Message", "Data Retrived Successful");

        request.body(requestParams.toJSONString());
        Response response = request.post("/Status");
        int statusCode = response.getStatusCode();
       System.out.println("The status code recieved: " + statusCode);

       System.out.println("Response body: " + response.body().asString());
     //  String successCode = response.jsonPath().get("SuccessCode");
     // System.out.println("success code-->"+successCode);
       Assert.assertEquals("200", requestParams.get("Status"));
       Assert.assertEquals("25", requestParams.get("Age"));
       Assert.assertEquals("QA Automation Developer", requestParams.get("Role"));
       Assert.assertEquals("25-02-1994", requestParams.get("DOB"));
       Assert.assertEquals("Data Retrived Successful", requestParams.get("Message"));

   }
    @Test
    public void TestGetResponseBody()
    {
        RestAssured.baseURI = "http://demo4032024.mockable.io/apitest/";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("http://demo4032024.mockable.io/apitest/");
        // JsonPath jsonPathEvaluator = response.jsonPath();
        //Assert.assertEquals(jsonPathEvaluator.get("company"),"");
        // Retrieve the body of the Response
        ResponseBody body = response.getBody();

        // To check for sub string presence get the Response body as a String.
        // Do a String.contains
        String bodyAsString = body.asString();

        Assert.assertEquals(bodyAsString.contains("ABC Infotech") /*Expected value*/, true /*Actual Value*/, "Response body contains IG Infotech");

    }
    }

