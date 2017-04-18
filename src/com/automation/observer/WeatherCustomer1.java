package com.automation.observer;

public class WeatherCustomer1 implements WeatherObserver {

	@Override
	public void doUpdate(int temperature) {
		System.out.println("Weather customer 1 just found out the temperature is:" + temperature);
	}

}
