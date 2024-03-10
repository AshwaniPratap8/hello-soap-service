package com.flydubai.hello.route;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

/**
 * The Class GreetingRouterTest.
 * 
 * @author ashwanipratap
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingRouterTest {

	/** The port. */
	@LocalServerPort
	private int port;

	/** The rest template. */
	private RestTemplate restTemplate;

	/** The request. */
	private String request;

	/** The headers. */
	private HttpHeaders headers;

	/** The entity. */
	private HttpEntity<String> entity;

	/** The hello soap service URI. */
	private String helloSoapServiceURI;

	/**
	 * Init.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@BeforeEach
	void init() throws IOException {
		helloSoapServiceURI = "http://localhost:" + port + "/HelloSoapService";
		request = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("hello-soap-request.xml"),
				StandardCharsets.UTF_8);
		headers = new HttpHeaders();
		headers.add("Content-Type", "application/soap+xml");
		entity = new HttpEntity<String>(request, headers);
		restTemplate = new RestTemplate();
	}

	/**
	 * Test greeting route.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGreetingRoute() throws Exception {
		String response = restTemplate.postForObject(helloSoapServiceURI, entity, String.class);

		assertNotNull(response);
		assertTrue(response.contains("Welcome Test Client"));
	}
}
