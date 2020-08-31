package stepDefinitions;

import static io.restassured.RestAssured.given;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.junit.Assert.*;

import java.io.IOException;

import pojo.AddPlace;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitions extends Utils {
	
	TestDataBuild data = new TestDataBuild();
	
	RequestSpecification res;
	Response response;
	static String place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		AddPlace place = data.addPlacePayload(name, language, address);
		res = given().spec(requestSpecification()).body(place);
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		
		//Constructor will be called with VALUE OF  resource which you pas
		APIResources resourceAPI = APIResources.valueOf(resource);
		if(method.equalsIgnoreCase("POST"))
			response = res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			response = res.when().log().all().get(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("PUT"))
			response = res.when().log().all().put(resourceAPI.getResource());
			
	}
    
    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
    	response = response.then().spec(responseSpecification()).extract().response();
    	
    	assertEquals(response.getStatusCode(), 200);
    }

    @Then("{string} in response body {string}")
    public void something_in_response_body_something(String key, String value) throws Throwable {

        assertEquals(getJsonPath(response, key), value);
    }
    
    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String name, String api) throws Throwable {
    	place_id = getJsonPath(response, "place_id");
    	
    	res = given().spec(requestSpecification()).queryParam("place_id", place_id);
    	user_calls_with_http_request(api, "get");
    	something_in_response_body_something("name", name);
    }
    
    @Given("Delete Payload")
    public void delete_payload() throws IOException {
    	res = given().spec(requestSpecification()).
    			body(data.deletePlacePayload(place_id));
    }
    
    @Given("Get parameters")
    public void get_payload() throws IOException {
        res = given().spec(requestSpecification()).
        		queryParam("place_id", place_id);
    }
    
    @Given("Update Place Payload with {string}")
    public void update_place_payload_with(String address) throws IOException {
        res = given().spec(requestSpecification()).body(data.updatePlacePayload(place_id, address));
    }
    
}
