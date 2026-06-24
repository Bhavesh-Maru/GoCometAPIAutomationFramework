package base;


import java.io.IOException;

import org.apache.commons.logging.impl.Log4JLogger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class BaseTest {
	
	public ApiClient apiClient;
	
	@BeforeClass
	public void setup() throws IOException {
		apiClient = new ApiClient();
	}
	
	@AfterClass
	public void tearDown() throws IOException {
		RestAssured.reset();
	}

}
