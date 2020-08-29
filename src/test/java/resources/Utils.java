package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	//Should be static in order to not be newly created in every test.
	//It will be shared across all tests and created only one time
	public static RequestSpecification reqspec;
	public static ResponseSpecification resspec;
	
	public RequestSpecification requestSpecification () throws IOException {
		
		//In order to run only one time through tests executions. 
		if(reqspec == null) {
			
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		
		reqspec = new RequestSpecBuilder().
				setBaseUri(getGlobalValue("baseUrl")).
				addQueryParam("key", "qaclick123").
				addFilter(RequestLoggingFilter.logRequestTo(log)).
				addFilter(ResponseLoggingFilter.logResponseTo(log)).
				setContentType(ContentType.JSON).build();
		}
		return reqspec;
	}
	
	public ResponseSpecification responseSpecification () {
		resspec = new ResponseSpecBuilder().
				expectStatusCode(200).
				expectContentType(ContentType.JSON).build();
		return resspec;
	}
	
	public String getGlobalValue(String key) throws IOException{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("/Users/rugilepetrukauskaite/eclipse-workspace/APIFramework/src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath (Response response, String key) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        
        return js.get(key).toString();
	}
}
