package com.automation.serialize.cloneObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CloneExample implements Cloneable, Serializable {

	private static final long serialVersionUID = -6689829670568117739L;
	
	private int num;
	private Thing thing;

	public CloneExample clone() {
		try {
			return (CloneExample) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	public CloneExample deepClone() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (CloneExample) ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Thing getThing() {
		return thing;
	}

	public void setThing(Thing thing) {
		this.thing = thing;
	}

	public String toString() {
		return "num:" + num + ", thing:" + thing;
	}
}
