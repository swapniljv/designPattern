package com.automation.runner;

import com.automation.abstractfactory.AbstractFactory;
import com.automation.abstractfactory.SpeciesFactory;
import com.automation.adapter.TemperatureClassReporter;
import com.automation.adapter.TemperatureInfo;
import com.automation.adapter.TemperatureObjectReporter;
import com.automation.bridge.BigBus;
import com.automation.bridge.BigEngine;
import com.automation.bridge.SmallCar;
import com.automation.bridge.SmallEngine;
import com.automation.bridge.Vehicle;
import com.automation.builder.ItalianMealBuilder;
import com.automation.builder.JapaneseMealBuilder;
import com.automation.builder.Meal;
import com.automation.builder.MealBuilder;
import com.automation.builder.MealDirector;
import com.automation.chain.EarthHandler;
import com.automation.chain.MercuryHandler;
import com.automation.chain.PlanetEnum;
import com.automation.chain.PlanetHandler;
import com.automation.chain.VenusHandler;
import com.automation.command.Command;
import com.automation.command.Dinner;
import com.automation.command.DinnerCommand;
import com.automation.command.Lunch;
import com.automation.command.LunchCommand;
import com.automation.command.MealInvoker;
import com.automation.composite.Composite;
import com.automation.composite.Leaf;
import com.automation.decorator.GrowlDecorator;
import com.automation.decorator.LegDecorator;
import com.automation.decorator.LivingAnimal;
import com.automation.decorator.WingDecorator;
import com.automation.facade.Facade;
import com.automation.factory.Animal;
import com.automation.factory.AnimalFactory;
import com.automation.flyweight.Flyweight;
import com.automation.flyweight.FlyweightFactory;
import com.automation.mediator.AmericanSeller;
import com.automation.mediator.Buyer;
import com.automation.mediator.DollarConverter;
import com.automation.mediator.FrenchBuyer;
import com.automation.mediator.Mediator;
import com.automation.mediator.SwedishBuyer;
import com.automation.observer.WeatherCustomer1;
import com.automation.observer.WeatherCustomer2;
import com.automation.observer.WeatherStation;
import com.automation.prototype.Dog;
import com.automation.prototype.Person;
import com.automation.proxy.FastThing;
import com.automation.proxy.Proxy;
import com.automation.singleton.SingeltonClass;
import com.automation.strategy.Context;
import com.automation.strategy.HikeStrategy;
import com.automation.strategy.SkiStrategy;
import com.automation.strategy.Strategy;
import com.automation.template.HamburgerMeal;
import com.automation.template.TacoMeal;

public class RunnerDP {

	public static void main(String[] args) {

		// runSingleton();
		// runFactory();
		// runAbstractFactory();
		// runBuilder();
		// runPrototype();
		// runAdapter();
		// runComposite();
		// runProxy();
		// runFlyweight();
		// runFacade();
		// runBridge();
		// runDecorator();
		// runTemplate();
		// runMediator();
		// runChain();
		// runObserver();
		// runStrategy();
		runCommand();
	}
	
	public static void runCommand() {
		Lunch lunch = new Lunch(); // receiver
		Command lunchCommand = new LunchCommand(lunch); // concrete command

		Dinner dinner = new Dinner(); // receiver
		Command dinnerCommand = new DinnerCommand(dinner); // concrete command

		MealInvoker mealInvoker = new MealInvoker(lunchCommand); // invoker
		mealInvoker.invoke();
		mealInvoker.setCommand(dinnerCommand);
		mealInvoker.invoke();
	}
	
	public static void runStrategy() {
		
		int temperatureInF = 60;

		Strategy skiStrategy = new SkiStrategy();
		Context context = new Context(temperatureInF, skiStrategy);

		System.out.println("Is the temperature (" + context.getTemperatureInF() + "F) good for skiing? " + context.getResult());

		Strategy hikeStrategy = new HikeStrategy();
		context.setStrategy(hikeStrategy);

		System.out.println("Is the temperature (" + context.getTemperatureInF() + "F) good for hiking? " + context.getResult());
	}
	
	public static void runObserver() {
		
		WeatherStation weatherStation = new WeatherStation(33);
		
		WeatherCustomer1 wc1 = new WeatherCustomer1();
		WeatherCustomer2 wc2 = new WeatherCustomer2();
		weatherStation.addObserver(wc1);
		weatherStation.addObserver(wc2);
		
		weatherStation.setTemperature(34);
		
		weatherStation.removeObserver(wc1);
		
		weatherStation.setTemperature(35);
	}
	
	public static void runChain() {
		PlanetHandler chain = setUpChain();

		chain.handleRequest(PlanetEnum.VENUS);
		chain.handleRequest(PlanetEnum.MERCURY);
		chain.handleRequest(PlanetEnum.EARTH);
		chain.handleRequest(PlanetEnum.JUPITER);
	}
	
	private static PlanetHandler setUpChain() {
		
		PlanetHandler mercuryHandler = new MercuryHandler();
		PlanetHandler venusHandler = new VenusHandler();
		PlanetHandler earthHandler = new EarthHandler();

		mercuryHandler.setSuccessor(venusHandler);
		venusHandler.setSuccessor(earthHandler);

		return mercuryHandler;
	}

	public static void runMediator() {
		
		Mediator mediator = new Mediator();

		Buyer swedishBuyer = new SwedishBuyer(mediator);
		Buyer frenchBuyer = new FrenchBuyer(mediator);
		float sellingPriceInDollars = 10.0f;
		AmericanSeller americanSeller = new AmericanSeller(mediator, sellingPriceInDollars);
		DollarConverter dollarConverter = new DollarConverter(mediator);

		float swedishBidInKronor = 55.0f;
		while (!swedishBuyer.attemptToPurchase(swedishBidInKronor)) {
			swedishBidInKronor += 15.0f;
		}

		float frenchBidInEuros = 3.0f;
		while (!frenchBuyer.attemptToPurchase(frenchBidInEuros)) {
			frenchBidInEuros += 1.5f;
		}
	}

	public static void runTemplate() {

		com.automation.template.Meal meal1 = new HamburgerMeal();
		meal1.doMeal();

		System.out.println();

		com.automation.template.Meal meal2 = new TacoMeal();
		meal2.doMeal();
	}

	public static void runDecorator() {

		com.automation.decorator.Animal animal = new LivingAnimal();
		animal.describe();

		animal = new LegDecorator(animal);
		animal.describe();

		animal = new WingDecorator(animal);
		animal.describe();

		animal = new GrowlDecorator(animal);
		animal = new GrowlDecorator(animal);
		animal.describe();
	}

	public static void runBridge() {

		Vehicle vehicle = new BigBus(new SmallEngine());
		vehicle.drive();
		vehicle.setEngine(new BigEngine());
		vehicle.drive();

		vehicle = new SmallCar(new SmallEngine());
		vehicle.drive();
		vehicle.setEngine(new BigEngine());
		vehicle.drive();

	}

	public static void runFacade() {

		Facade facade = new Facade();

		int x = 3;
		System.out.println("Cube of " + x + ":" + facade.cubeX(3));
		System.out.println("Cube of " + x + " times 2:" + facade.cubeXTimes2(3));
		System.out.println(x + " to sixth power times 2:" + facade.xToSixthPowerTimes2(3));
	}

	public static void runFlyweight() {

		FlyweightFactory flyweightFactory = FlyweightFactory.getInstance();

		for (int i = 0; i < 5; i++) {
			Flyweight flyweightAdder = flyweightFactory.getFlyweight("add");
			flyweightAdder.doMath(i, i);

			Flyweight flyweightMultiplier = flyweightFactory.getFlyweight("multiply");
			flyweightMultiplier.doMath(i, i);
		}
	}

	public static void runProxy() {

		Proxy proxy = new Proxy();

		FastThing fastThing = new FastThing();
		fastThing.sayHello();

		proxy.sayHello();
	}

	public static void runComposite() {
		Leaf leaf1 = new Leaf("Bob");
		Leaf leaf2 = new Leaf("Fred");
		Leaf leaf3 = new Leaf("Sue");
		Leaf leaf4 = new Leaf("Ellen");
		Leaf leaf5 = new Leaf("Joe");

		Composite composite1 = new Composite();
		composite1.add(leaf1);
		composite1.add(leaf2);

		Composite composite2 = new Composite();
		composite2.add(leaf3);
		composite2.add(leaf4);

		Composite composite3 = new Composite();
		composite3.add(composite1);
		composite3.add(composite2);
		composite3.add(leaf5);

		System.out.println("Calling 'sayHello' on leaf1");
		leaf1.sayHello();

		System.out.println("\nCalling 'sayHello' on composite1");
		composite1.sayHello();

		System.out.println("\nCalling 'sayHello' on composite2");
		composite2.sayHello();

		System.out.println("\nCalling 'sayGoodbye' on composite3");
		composite3.sayGoodbye();
	}

	public static void runAdapter() {
		// class adapter
		System.out.println("class adapter test");
		TemperatureInfo tempInfo = new TemperatureClassReporter();
		testTempInfo(tempInfo);

		// object adapter
		System.out.println("\nobject adapter test");
		tempInfo = new TemperatureObjectReporter();
		testTempInfo(tempInfo);
	}

	private static void testTempInfo(TemperatureInfo tempInfo) {
		tempInfo.setTemperatureInC(0);
		System.out.println("temp in C:" + tempInfo.getTemperatureInC());
		System.out.println("temp in F:" + tempInfo.getTemperatureInF());

		tempInfo.setTemperatureInF(85);
		System.out.println("temp in C:" + tempInfo.getTemperatureInC());
		System.out.println("temp in F:" + tempInfo.getTemperatureInF());
	}

	public static void runSingleton() {
		SingeltonClass sc = SingeltonClass.getInstance();
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
