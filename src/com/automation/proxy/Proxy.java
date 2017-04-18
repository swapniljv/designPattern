package com.automation.proxy;

import java.util.Date;

public class Proxy {
	
	SlowThing slowThing;

	public Proxy() {
		System.out.println("Creating proxy at " + new Date());
	}

	public void sayHello() {
		
		slowThing = slowThing == null ? new SlowThing() : slowThing;
		
		slowThing.sayHello();
	}
}
