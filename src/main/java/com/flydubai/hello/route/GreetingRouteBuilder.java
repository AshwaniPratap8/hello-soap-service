package com.flydubai.hello.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flydubai.hello.service.GreetingService;
import com.flydubai.hellosoap.HelloSoap;
import com.flydubai.hellosoap.HelloSoapResponse;

/**
 * The Class GreetingRouteBuilder.
 * 
 * This class configures the Camel router for handling SOAP requests related to
 * greetings by registering the greeting route in Camel context for smart
 * routing. It accepts and processes SOAP requests and produces SOAP responses
 * by utilizing the GreetingService.
 *
 * @author ashwanipratap
 */
@Component
public class GreetingRouteBuilder extends RouteBuilder {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(GreetingRouteBuilder.class);

	/** The greeting service. */
	@Autowired
	GreetingService greetingService;

	/**
	 * Returns a greeting message for the given client name.
	 *
	 * @throws Exception the exception
	 */
	@Override
	public void configure() throws Exception {
		from("spring-ws:rootqname:{http://www.flydubai.com/HelloSoap}HelloSoap?endpointMapping=#camelEndpointMapping")
				.unmarshal().jaxb(HelloSoap.class.getPackage().getName()).process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						logger.info("Request processing started.");
						HelloSoap request = exchange.getIn().getBody(HelloSoap.class);
						String clientName = request.getClientName();
						HelloSoapResponse greetingMessage = greetingService.getGreetingMessage(clientName);
						exchange.getMessage().setBody(greetingMessage);
						logger.info("Request processing completed. Response: {}", greetingMessage.getResponse());
					}
				}).marshal().jaxb(HelloSoapResponse.class.getPackage().getName());
	}
}
