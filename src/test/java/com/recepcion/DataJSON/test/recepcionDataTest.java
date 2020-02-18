package com.recepcion.DataJSON.test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

/**
 * 
 * @author Paula Vargas 
 *         <br/>
 *         Date: 9/04/2018 9:17:11 a.m.
 *
 */
public class recepcionDataTest extends CamelSpringTestSupport {

    private static final String PROPERTIES_FILE_DIR = "src/test/resources/";
    private static Properties testProperties = new Properties();
    
    @Test
    public void testRoute() throws Exception {
	final String timerStart = "direct:select";

	context.getRouteDefinition("ROUTE-LEERBD").adviceWith(context, new AdviceWithRouteBuilder() {
	    @Override
	    public void configure() throws Exception {
		replaceFromWith(timerStart);
		weaveAddLast().log("Finishing the unit test of the route ").to("mock://endroute");
	    }
	});
	context.start();
	// Agregamos un mock endpoint
	MockEndpoint mockEndpoint = getMockEndpoint("mock://endroute");
	mockEndpoint.expectedMinimumMessageCount(1);


	mockEndpoint.assertIsSatisfied(2000L);
    }

    /**
     * Carga del archivo de propiedades para los Test Unitarios
     * 
     * @throws Exception
     */
    @BeforeClass
    public static void init() throws Exception {
	testProperties.load(recepcionDataTest.class.getResourceAsStream("/properties.properties"));
    }
    
    @BeforeClass
    public static void sqlServerConfiguration() throws Exception {
	try {
	    // Create initial context
	    System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
	    System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
	    SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();

		// Construct DataSource
		BasicDataSource ds = new BasicDataSource();
	    ds.setUrl("jdbc:sqlserver://localhost:1433;DatabaseName=calculadoraESB;Trusted_Connection=True;");
	    ds.setUsername("prueba");
	    ds.setPassword("123");
	    ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

	    builder.bind("osgi:service/jdbc/calculadoraPaula", ds);
	    builder.activate();
	} catch (NamingException ex) {
	    ex.printStackTrace();
	}
    }

    
    @BeforeClass
    public static void setUpProperties() throws Exception {
	System.setProperty("karaf.home", PROPERTIES_FILE_DIR);
	System.setProperty("project.artifactId", "Test-Maven-Artifact");
    }

    @Override
    protected AbstractApplicationContext createApplicationContext() {
	return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml",
		"META-INF/spring/properties-beans.xml");
    }

}
