package com.automation.factory;

public class AnimalFactory {

	private static AnimalFactory currentClassInstance = null;

	private AnimalFactory() {
		// Prevent object creation
	}
	
	public static AnimalFactory getInstance() {
		return currentClassInstance == null ? new AnimalFactory() : currentClassInstance;
	}

	public Animal getAnimal(String type) {

		switch (type) {

		case "canine":
			return new Dog();

		case "feline":
			return new Cat();

		default:
			break;
		}

		return null;
	}
}
