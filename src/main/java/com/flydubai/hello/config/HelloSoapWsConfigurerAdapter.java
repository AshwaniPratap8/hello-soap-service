package com.flydubai.hello.config;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;

import org.apache.camel.component.spring.ws.bean.CamelEndpointMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointAdapter;
import org.springframework.ws.server.EndpointMapping;
import org.springframework.ws.server.endpoint.adapter.MessageEndpointAdapter;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * The Class HelloSoapWsConfigurerAdapter.
 * 
 * It extends <link>WsConfigurerAdapter</link> and provides necessary
 * environment preparation for camel context.
 * 
 * @author ashwanipratap
 */
@EnableWs
@Configuration
public class HelloSoapWsConfigurerAdapter extends WsConfigurerAdapter {

	/** The environment config. */
	@Autowired
	EnvironmentConfig environmentConfig;

	/**
	 * Message dispatcher servlet.
	 *
	 * @param applicationContext the application context
	 * @return the servlet registration bean
	 */
	@Bean
	ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(applicationContext);
		messageDispatcherServlet.setTransformWsdlLocations(Boolean.TRUE);
		return new ServletRegistrationBean<>(messageDispatcherServlet, "/*");
	}

	/**
	 * Hello soap schema.
	 *
	 * @return the xsd schema
	 */
	@Bean
	XsdSchema helloSoapSchema() {
		return new SimpleXsdSchema(new ClassPathResource(environmentConfig.getSchemaLocation()));
	}

	/**
	 * Default wsdl 11 definition. It is used to generate wsdl from xsd schema and
	 * access it at helloschema.wsdl location.
	 *
	 * @param helloSoapSchema the hello soap schema
	 * @return the default wsdl 11 definition
	 */
	@Bean(name = "helloschema")
	DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema helloSoapSchema) {
		DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
		defaultWsdl11Definition.setSchema(helloSoapSchema);
		defaultWsdl11Definition.setPortTypeName(environmentConfig.getPortTypeName());
		defaultWsdl11Definition.setLocationUri(environmentConfig.getLocationUri());
		defaultWsdl11Definition.setTargetNamespace(environmentConfig.getTargetNamespace());
        defaultWsdl11Definition.setCreateSoap12Binding(true);
        defaultWsdl11Definition.setRequestSuffix("Soap");
        defaultWsdl11Definition.setResponseSuffix("SoapResponse");
		return defaultWsdl11Definition;
	}

	/**
	 * Message factory.
	 *
	 * @return the saaj soap message factory
	 * @throws SOAPException the SOAP exception
	 */
	@Bean
	SaajSoapMessageFactory messageFactory() throws SOAPException {
		MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
		SaajSoapMessageFactory soapMessageFactory = new SaajSoapMessageFactory(messageFactory);
		return soapMessageFactory;
	}

	/**
	 * Endpoint mapping.
	 *
	 * @return the endpoint mapping
	 */
	@Bean(name = "camelEndpointMapping")
	EndpointMapping endpointMapping() {
		return new CamelEndpointMapping();
	}

	/**
	 * Message endpoint adapter.
	 *
	 * @return the endpoint adapter
	 */
	@Bean
	EndpointAdapter messageEndpointAdapter() {
		return new MessageEndpointAdapter();
	}
}
