package com.automation.abstractfactory;

import com.automation.factory.Animal;
import com.automation.factory.Cat;
import com.automation.factory.Dog;

public class MammalFactory extends SpeciesFactory {

	@Override
	public Animal getAnimal(String type) {
		
		switch (type) {

		case "dog":
			return new Dog();

		case "cat":
			return new Cat();

		default:
			break;
		}

		return null;
	}

}
