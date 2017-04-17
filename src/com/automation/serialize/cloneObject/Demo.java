package com.automation.serialize.cloneObject;

public class Demo {
	
	public static void main(String[] args) {
		
		CloneExample ce = new CloneExample();
		
		ce.setNum(3);
		ce.setThing(new Thing("Fred"));
		System.out.println("Before cloning");
		System.out.println("ce:" + ce);

		CloneExample ceShallowClone = ce.clone();
		CloneExample cdDeepClone = ce.deepClone();

		System.out.println("\nAfter cloning, setting ce num to 5");
		ce.setNum(5);
		System.out.println("After cloning, setting ce thing name to Barney");
		Thing thing = ce.getThing();
		thing.setName("Barney");

		System.out.println("ce:" + ce);
		System.out.println("ceShallowClone:" + ceShallowClone);
		System.out.println("cdDeepClone:" + cdDeepClone);

		System.out.println("\nNotice that changing ce thing name to Barney changed ceShallowClone's thing name to Barney.");
		System.out.println("This is because the copy was shallow, and ce's thing and ceShallowClone's thing point to the same Thing.");
		System.out.println("Notice that ceDeepClone's thing name is Fred. This is because the deep copy resulted in ceDeepClone having its own Thing.");

	}
}
