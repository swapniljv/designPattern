package com.automation.chain;

public abstract class PlanetHandler {

	PlanetHandler successor;

	public void setSuccessor(PlanetHandler successor) {
		this.successor = successor;
	}

	public abstract void handleRequest(PlanetEnum request);

}
