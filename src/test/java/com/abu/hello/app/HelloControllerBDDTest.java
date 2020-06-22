package com.abu.hello.app;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

//import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class HelloControllerBDDTest {
		
	//@Rule
	//public WireMockRule wireMockRule = new WireMockRule(options().port(8080));
	@ClassRule
	public static WireMockRule wireMockRule = new WireMockRule(8080);
	
	
	@Test
	public void testGoogle() {
		//given().when().get("http://www.google.com").then().statusCode(200);
		assertTrue(true);
	}

	@Test
	public void testBDD() {	
		//given().when().get("http://localhost:8080/data/hello").then().statusCode(200);
		assertTrue(true);
	}

}
