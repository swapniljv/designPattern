package com.automation.abstractfactory;

public class AbstractFactory {
	
	private static AbstractFactory currentClassInstance = null;
	
	private AbstractFactory() {
	}

	public static AbstractFactory getInstance() {
		return currentClassInstance == null ? new AbstractFactory() : currentClassInstance;
	}
	
	public SpeciesFactory getSpeciesFactory(String type) {
		
		switch (type) {

		case "mammal":
			return new MammalFactory();

		case "reptile":
			return new ReptileFactory();

		default:
			break;
		}

		return null;
	}
}
