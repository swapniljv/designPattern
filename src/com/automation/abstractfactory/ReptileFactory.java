package com.automation.abstractfactory;

import com.automation.factory.Animal;
import com.automation.factory.Snake;
import com.automation.factory.Tyrannosaurus;

public class ReptileFactory extends SpeciesFactory {

	@Override
	public Animal getAnimal(String type) {
		
		switch (type) {

		case "snake":
			return new Snake();

		case "tyrannosaurus":
			return new Tyrannosaurus();

		default:
			break;
		}

		return null;
	}

}
