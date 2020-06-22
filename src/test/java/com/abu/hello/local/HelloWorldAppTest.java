package com.abu.hello.local;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class HelloWorldAppTest {

	@Test
    public void should_create_greeting() {
		
		HelloWorldLocal x = new HelloWorldLocal();
		
		assertEquals(x.msg(), "Oi Mundo eu Existo");
    }
	
}
