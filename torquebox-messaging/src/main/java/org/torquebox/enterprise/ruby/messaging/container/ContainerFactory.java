package org.torquebox.enterprise.ruby.messaging.container;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ContainerFactory {

	public static final String DEFAULT_CONTEXT_FACTORY_CLASS_NAME = "org.jnp.interfaces.NamingContextFactory";
	public static final String DEFAULT_URL_PKG_PREFIXES = "org.jboss.naming.client";
	public static final String DEFAULT_NAMING_PROVIDER_URL = "jnp://localhost:1099";
	
	public static final String DEFAULT_CONNECTION_FACTORY_JNDI_NAME = "/ConnectionFactory";
	
	private String connectionFactoryJndiName = DEFAULT_CONNECTION_FACTORY_JNDI_NAME;
	
	private String namingProviderUrl = DEFAULT_NAMING_PROVIDER_URL;
	private String contextFactoryClassName = DEFAULT_CONTEXT_FACTORY_CLASS_NAME;
	private String urlPackagePrefixes = DEFAULT_URL_PKG_PREFIXES;

	public ContainerFactory() {

	}
	
	public String toString() {
		return "[ContainerFactory: connectionFactoryJndiName=" + this.connectionFactoryJndiName + "]";
	}
	
	public void setContextFactoryClassName(String contextFactoryClassName) {
		this.contextFactoryClassName = contextFactoryClassName;
	}
	
	public String getContextFactoryClassName() {
		return this.contextFactoryClassName;
	}
	
	public void setUrlPackagePrefixes(String urlPackagePrefixes) {
		this.urlPackagePrefixes = urlPackagePrefixes;
	}
	
	public String getUrlPackagePrefixes() {
		return this.urlPackagePrefixes;
	}

	public void setNamingProviderUrl(String namingProviderUrl) {
		this.namingProviderUrl = namingProviderUrl;
	}
	
	public String getNamingProviderUrl() {
		return this.namingProviderUrl;
	}

	public InitialContext createInitialContext() throws NamingException {
		Properties environment = new Properties();

		environment.setProperty(Context.INITIAL_CONTEXT_FACTORY, getContextFactoryClassName() );
		environment.setProperty(Context.PROVIDER_URL, getNamingProviderUrl());
		environment.setProperty(Context.URL_PKG_PREFIXES, getUrlPackagePrefixes() );

		return new InitialContext(environment);
	}
	
	public void setConnectionFactoryJndiName(String connectionFactoryJndiName) {
		this.connectionFactoryJndiName = connectionFactoryJndiName;
	}
	
	public String getConnectionFactoryJndiName() {
		return this.connectionFactoryJndiName;
	}
	
	public Container createContainer() throws NamingException {
		Container container = new Container();
		container.setContext( createInitialContext() );
		container.setConnectionFactoryJndiName( getConnectionFactoryJndiName() );
		return container;
	}
	

}