package com.automation.flyweight;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {

	private static FlyweightFactory flyweightFactory;

	private Map<String, Flyweight> flyweightPool;

	private FlyweightFactory() {
		flyweightPool = new HashMap<String, Flyweight>();
	}

	public static FlyweightFactory getInstance() {
		
		return flyweightFactory == null ? new FlyweightFactory() : flyweightFactory;
	}

	public Flyweight getFlyweight(String key) {
		
		if (flyweightPool.containsKey(key)) {
			return flyweightPool.get(key);
		} else {
			Flyweight flyweight;
			if ("add".equals(key)) {
				flyweight = new FlyweightAdder();
			} else {
				flyweight = new FlyweightMultiplier();
			}
			flyweightPool.put(key, flyweight);
			return flyweight;
		}
	}
}
