Feature: Validating place APIs

@AddPlace
Scenario Outline: Verify is place bying succesfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "addPlaceAPI" with "post" http request
	Then the API call got success with status code 200
	And "status" in response body "OK"
	And "scope" in response body "APP"
	And verify place_Id created maps to "<name>" using "getPlaceAPI"
	
Examples:
|name   |language   |address  |
|Tomas  |LIthuania  |Stoko5   |
#|Kestas |Spain      |Hido10   |

@DeletePlace
Scenario: Verify if Delete Place functionality is working
	Given Delete Payload
	When user calls "deletePlaceAPI" with "post" http request
	Then the API call got success with status code 200
	And "status" in response body "OK"