package com.abu.hello.app;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.inject.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 */
@Path("/hello")
@Singleton
public class HelloController {

    @GET
    public String sayHello() {
        return "Hello World do Abu";
    }
    
    @GET
	@Path("{id}")
	public Response getUserById(@PathParam("id") String id) {

	   return Response.status(200).entity("getUserById is called, id : " + id).build();
	}
        
    @POST
	@Path("/sendJson")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
	public String sendJson(String json) {
    	
    	System.out.println(json);
    	
    	InputStream stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
    	
    	JsonReaderFactory factory = Json.createReaderFactory(null);
    	
    	JsonReader reader = factory.createReader(stream);
    	
    	JsonObject object = reader.readObject();

    	return String.format("FirstName %s LastName %s PhoneNumber %s", 
    			object.getString("firstName"), 
    			object.getString("lastName"), 
    			object.getString("phoneNumber"));
	}
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/helloJson")
    public String getAllMessages() throws Exception{
        
		JsonObject value = Json.createObjectBuilder()
    		     .add("firstName", "John")
    		     .add("lastName", "Smith")
    		     .add("age", 25)
    		     .add("address", Json.createObjectBuilder()
    		         .add("streetAddress", "21 2nd Street")
    		         .add("city", "New York")
    		         .add("state", "NY")
    		         .add("postalCode", "10021"))
    		     .add("phoneNumber", Json.createArrayBuilder()
    		         .add(Json.createObjectBuilder()
    		             .add("type", "home")
    		             .add("number", "212 555-1234"))
    		         .add(Json.createObjectBuilder()
    		             .add("type", "fax")
    		             .add("number", "646 555-4567")))
    		     .build();

    	return value.toString();
    }
    
    
    
}
