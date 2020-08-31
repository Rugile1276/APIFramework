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
|Tomas  |Lit  		|Stoko5   |
#|Kestas |Spain      |Hido10   |

@GetPlace
Scenario Outline: Verify if correct place is returned using GetPlaceAPI
	Given Get parameters
	When user calls "getPlaceAPI" with "get" http request
	Then the API call got success with status code 200
	And "name" in response body "<name>"
	And "language" in response body "<language>"
	And "address" in response body "<address>"
Examples:
|name   |language   |address  |
|Tomas  |Lit  		|Stoko5   |

	
@UpdatePlace
Scenario Outline: Verify is place bying succesfully updated using UpdatePlaceAPI
	Given Update Place Payload with "<address>"
	When user calls "updatePlaceAPI" with "put" http request
	Then the API call got success with status code 200
	And "msg" in response body "Address successfully updated"
	
Examples:
|address      |
|updatedStoko5|

@DeletePlace
Scenario: Verify if Delete Place functionality is working
	Given Delete Payload
	When user calls "deletePlaceAPI" with "post" http request
	Then the API call got success with status code 200
	And "status" in response body "OK"