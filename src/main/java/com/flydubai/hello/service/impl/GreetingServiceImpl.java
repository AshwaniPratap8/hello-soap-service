package com.flydubai.hello.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.flydubai.hello.exception.InvalidClientNameException;
import com.flydubai.hello.service.GreetingService;
import com.flydubai.hellosoap.HelloSoapResponse;

/**
 * The Class GreetingService.
 * 
 * The service implementation class for generating greeting messages. It
 * prepares the greeting messages based on the given client name.
 *
 * @author ashwanipratap
 */
@Service
public class GreetingServiceImpl implements GreetingService {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(GreetingServiceImpl.class);

	/** The Constant FORMAT_WELCOME_MESSAGE. */
	private static final String FORMAT_WELCOME_MESSAGE = "Welcome {{ClientName}}";

	/**
	 * Prepares greeting message for the given client name.
	 *
	 * @param clientName the client name
	 * @return the greeting message
	 */
	@Override
	public HelloSoapResponse getGreetingMessage(String clientName) {
		validateClientName(clientName);
		HelloSoapResponse helloSoapResponse = new HelloSoapResponse();
		String greetingMessage = FORMAT_WELCOME_MESSAGE.replace("{{ClientName}}", clientName);
		helloSoapResponse.setResponse(greetingMessage);
		return helloSoapResponse;
	}

	/**
	 * Validate client name.
	 *
	 * @param clientName the client name
	 */
	private void validateClientName(String clientName) {
		if (null == clientName || clientName.isBlank()) {
			logger.error("Error: Client name is null or empty. Client Name: {}", clientName);
			throw new InvalidClientNameException("Client name is null or empty.");
		}
	}
}
