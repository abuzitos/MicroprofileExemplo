package com.abu.hello.app;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.arquillian.CreateSwarm;

@RunWith(Arquillian.class)
public class HelloControllerClientTest {
	
	private final Logger log = Logger.getLogger(HelloControllerClientTest.class.getName());

    @ArquillianResource
    private URL url;

    @Deployment
    public static WebArchive deploy() {

        File[] deps = Maven.resolver()
            .loadPomFromFile("pom.xml")
            .importDependencies(ScopeType.COMPILE, ScopeType.RUNTIME)
            .resolve().withTransitivity().asFile();

        WebArchive wrap = ShrinkWrap.create(WebArchive.class
                , HelloControllerClientTest.class.getName() + ".war")
        		.addPackages(true, "com.abu.hello.app")
        		.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsLibraries(deps);
        
        /*
        WebArchive wrap = ShrinkWrap.create(WebArchive.class
                , HelloControllerTest.class.getName() + ".war")
        		.addPackages(true, "io.microprofile.showcase.speaker")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource(new File("src/test/resources/ConferenceData.json"))
                .addAsManifestResource("META-INF/microprofile-config.properties","microprofile-config.properties")
                .addAsLibraries(deps);
        */
        
		return wrap;
    }
    
    @CreateSwarm
    public static Swarm newContainer() throws Exception {
             Properties properties = new Properties();
		properties.put("swarm.http.port", 8080);
		properties.put("java.util.logging.manager", "org.jboss.logmanager.LogManager");
		Swarm swarm = new Swarm(properties);
		return swarm.withProfile("defaults");
	}
	
    @Test
    @RunAsClient
    public void testGetClient() {
    	Client client = ClientBuilder.newBuilder().build();
    	WebTarget target = client.target("http://localhost:8080/data/hello");
    	Response response = target.request().get();
    	String users = response.readEntity(String.class);
    	assertEquals("Hello World do Abu", users);
    }
}
