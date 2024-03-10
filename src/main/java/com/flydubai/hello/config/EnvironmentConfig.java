package com.flydubai.hello.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * The Class EnvironmentConfig.
 * 
 * @author ashwanipratap
 */
@Component
@ConfigurationProperties(prefix = "hello.soap")
public class EnvironmentConfig {

	/** The port type name. */
	private String portTypeName;

	/** The location uri. */
	private String locationUri;

	/** The target namespace. */
	private String targetNamespace;

	/** The schema location. */
	private String schemaLocation;

	/**
	 * Gets the port type name.
	 *
	 * @return the port type name
	 */
	public String getPortTypeName() {
		return portTypeName;
	}

	/**
	 * Sets the port type name.
	 *
	 * @param portTypeName the new port type name
	 */
	public void setPortTypeName(String portTypeName) {
		this.portTypeName = portTypeName;
	}

	/**
	 * Gets the location uri.
	 *
	 * @return the location uri
	 */
	public String getLocationUri() {
		return locationUri;
	}

	/**
	 * Sets the location uri.
	 *
	 * @param locationUri the new location uri
	 */
	public void setLocationUri(String locationUri) {
		this.locationUri = locationUri;
	}

	/**
	 * Gets the target namespace.
	 *
	 * @return the target namespace
	 */
	public String getTargetNamespace() {
		return targetNamespace;
	}

	/**
	 * Sets the target namespace.
	 *
	 * @param targetNamespace the new target namespace
	 */
	public void setTargetNamespace(String targetNamespace) {
		this.targetNamespace = targetNamespace;
	}

	/**
	 * Gets the schema location.
	 *
	 * @return the schema location
	 */
	public String getSchemaLocation() {
		return schemaLocation;
	}

	/**
	 * Sets the schema location.
	 *
	 * @param schemaLocation the new schema location
	 */
	public void setSchemaLocation(String schemaLocation) {
		this.schemaLocation = schemaLocation;
	}
}
