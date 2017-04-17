package com.automation.serialize.cloneObject;

import java.io.Serializable;

public class Thing implements Serializable {

	private static final long serialVersionUID = -5560178586863869526L;
	
	String name;

	public Thing(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
