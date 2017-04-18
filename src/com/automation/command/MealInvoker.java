package com.automation.command;

public class MealInvoker {

	Command command;

	public MealInvoker(Command command) {
		this.command = command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public void invoke() {
		command.execute();
	}

}