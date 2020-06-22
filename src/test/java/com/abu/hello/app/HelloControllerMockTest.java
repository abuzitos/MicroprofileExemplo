package com.abu.hello.app;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.*;

import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class HelloControllerMockTest {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(options().port(7070));

	@Test
	public void testGet() {

		// TODO Implement a mock that responds to /sessions/speakerspeakerId/99/ using
		// the HTTP GET method
		wireMockRule.stubFor(get(urlMatching("/data/hello")).willReturn(aResponse()
				// TODO The mock must return an HTTP 200 Code
				.withStatus(200)
				// TODO the mock returns a JSON payload
				.withHeader("Content-Type", "application/json")
				// TODO The JSON payload must be returned via HTTP Body and it must be obtained
				// using the readFile method
				.withBody("Hello World do Abu")));

	}	
}
