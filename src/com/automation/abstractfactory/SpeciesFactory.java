package com.automation.abstractfactory;

import com.automation.factory.Animal;

public abstract class SpeciesFactory {
	public abstract Animal getAnimal(String type);
}
