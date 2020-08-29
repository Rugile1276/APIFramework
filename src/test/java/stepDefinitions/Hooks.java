package stepDefinitions;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable {
		
		StepDefinitions sd = new StepDefinitions();
		if(StepDefinitions.place_id == null) {
			sd.add_place_payload_with("Tom", "Lit", "Kauno g.");
			sd.user_calls_with_http_request("addPlaceAPI", "POST");
			sd.verify_place_id_created_maps_to_using("Tom", "getPlaceAPI");
		}
			
	}
}
