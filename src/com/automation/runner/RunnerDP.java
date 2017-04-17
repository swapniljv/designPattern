package com.automation.runner;

import com.automation.abstractfactory.AbstractFactory;
import com.automation.abstractfactory.SpeciesFactory;
import com.automation.builder.ItalianMealBuilder;
import com.automation.builder.JapaneseMealBuilder;
import com.automation.builder.Meal;
import com.automation.builder.MealBuilder;
import com.automation.builder.MealDirector;
import com.automation.factory.Animal;
import com.automation.factory.AnimalFactory;
import com.automation.prototype.Dog;
import com.automation.prototype.Person;
import com.automation.singleton.SingeltonClass;

public class RunnerDP {
	
	public static void main(String[] args) {
		
		//runSingleton();
		//runFactory();
		//runAbstractFactory();
		//runBuilder();
		runPrototype();
	}
	
	public static void runSingleton() {
		SingeltonClass sc= SingeltonClass.getInstance();
		sc.sayHello();
	}
	
	public static void runFactory() {
		
		AnimalFactory animalFactory = AnimalFactory.getInstance();

		Animal a1 = animalFactory.getAnimal("feline");
		System.out.println("a1 sound: " + a1.makeSound());

		Animal a2 = animalFactory.getAnimal("canine");
		System.out.println("a2 sound: " + a2.makeSound());
	}
	
	public static void runAbstractFactory() {
		
		AbstractFactory abstractFactory = AbstractFactory.getInstance();

		SpeciesFactory speciesFactory1 = abstractFactory.getSpeciesFactory("reptile");
		Animal a1 = speciesFactory1.getAnimal("tyrannosaurus");
		System.out.println("tyrannosaurus sound: " + a1.makeSound());
		Animal a2 = speciesFactory1.getAnimal("snake");
		System.out.println("snake sound: " + a2.makeSound());

		SpeciesFactory speciesFactory2 = abstractFactory.getSpeciesFactory("mammal");
		Animal a3 = speciesFactory2.getAnimal("dog");
		System.out.println("dog sound: " + a3.makeSound());
		Animal a4 = speciesFactory2.getAnimal("cat");
		System.out.println("cat sound: " + a4.makeSound());
	}
	
	public static void runBuilder() {
		
		MealBuilder mealBuilder = new ItalianMealBuilder();
		MealDirector mealDirector = new MealDirector(mealBuilder);
		mealDirector.constructMeal();
		Meal meal = mealDirector.getMeal();
		System.out.println("meal is: " + meal);
		
		mealBuilder = new JapaneseMealBuilder();
		mealDirector = new MealDirector(mealBuilder);
		mealDirector.constructMeal();
		meal = mealDirector.getMeal();
		System.out.println("meal is: " + meal);
	}
	
	public static void runPrototype() {
		
		Person person1 = new Person("Fred");
		System.out.println("person 1:" + person1);
		Person person2 = (Person) person1.doClone();
		System.out.println("person 2:" + person2);

		Dog dog1 = new Dog("Wooof!");
		System.out.println("dog 1:" + dog1);
		Dog dog2 = (Dog) dog1.doClone();
		System.out.println("dog 2:" + dog2);
	}

}
