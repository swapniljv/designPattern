package com.automation.prototype;

public class Person implements Prototype {

	private String name;

	public Person(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "This person is named " + name;
	}

	@Override
	public Prototype doClone() {
		return new Person(name);
	}
}
