package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language, String address) {
		AddPlace place = new AddPlace();
		place.setAccuracy(50);
		place.setAddress(address);
		place.setLanguage(language);
		Location loc = new Location();
		loc.setLat(41234123434.2143);
		loc.setLng(6661234341234.2143);
		place.setLocation(loc);
		place.setName(name);
		place.setPhone_number("24232");
		List<String> typesList = new ArrayList<String>();
		typesList.add("Auth code");
		typesList.add("Credentials");
		place.setTypes(typesList);
		place.setWebsite("sFsf");
		return place;
	}
	
	public String deletePlacePayload(String place_id) {
		return "{\r\n    \"place_id\":\"" + place_id + "\"\r\n}\r\n";
	}
	
	public String updatePlacePayload(String place_id, String newAddress) {
		return "{\r\n\"place_id\":\"" + place_id + "\",\r\n\"address\":\"" + newAddress + "\",\r\n\"key\":\"qaclick123\"\r\n}";
		
		
	}
	}
