package com.abu.hello.config;

import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/config")
@RequestScoped
public class HelloConfig {

	
	//Exemplo 1
	@Inject
    @ConfigProperty(name = "optional")
    private String optional;
	
	@Path("/injected")
    @GET
	public String getOptional() {
		return optional;
	}
	
	//Exemplo 2
	@Inject
	private Config config;
	
	@Path("/injected2")
    @GET
	public String displayProperties(){
	   Optional<String> optionalValue = config.getOptionalValue("optional",String.class);
	   optionalValue.ifPresent(v -> System.out.println(v));

	   String requiredValue = config.getValue("required",String.class);
	   return optionalValue + " --- " + requiredValue;
	}
	
	
	public static void main(String[] args) {
		HelloConfig hello = new HelloConfig();
		
		System.out.println(hello.getOptional());

	}
}


