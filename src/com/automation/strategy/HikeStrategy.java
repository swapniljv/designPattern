package com.automation.strategy;

public class HikeStrategy implements Strategy {

	@Override
	public boolean checkTemperature(int temperatureInF) {
		if ((temperatureInF >= 50) && (temperatureInF <= 90)) {
			return true;
		} else {
			return false;
		}
	}

}
