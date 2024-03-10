package com.flydubai.hello.service;

import com.flydubai.hellosoap.HelloSoapResponse;

/**
 * The Interface GreetingService.
 * 
 * @author ashwanipratap
 */
public interface GreetingService {

	/**
	 * Gets the greeting message.
	 *
	 * @param clientName the client name
	 * @return the greeting message
	 */
	HelloSoapResponse getGreetingMessage(String clientName);
}
