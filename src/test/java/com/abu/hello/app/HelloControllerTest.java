package com.abu.hello.app;

import java.io.File;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.arquillian.CreateSwarm;

import com.abu.hello.local.HelloWorldLocal;

@RunWith(Arquillian.class)
public class HelloControllerTest {
	
	private final Logger log = Logger.getLogger(HelloControllerTest.class.getName());

    @ArquillianResource
    private URL url;

    @Deployment
    public static WebArchive deploy() {

        File[] deps = Maven.resolver()
            .loadPomFromFile("pom.xml")
            .importDependencies(ScopeType.COMPILE, ScopeType.RUNTIME)
            .resolve().withTransitivity().asFile();

        WebArchive wrap = ShrinkWrap.create(WebArchive.class
                , HelloControllerTest.class.getName() + ".war")
        		.addPackages(true, "com.abu.hello.local")
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
    public void testGet() {
    	Assert.assertEquals("Oi Mundo eu Existo", HelloWorldLocal.msg());
    	//fail("Implementar");
    	
    }
}
