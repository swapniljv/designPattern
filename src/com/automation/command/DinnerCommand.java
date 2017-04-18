package com.automation.command;

public class DinnerCommand implements Command {

	Dinner dinner;

	public DinnerCommand(Dinner dinner) {
		this.dinner = dinner;
	}

	@Override
	public void execute() {
		dinner.makeDinner();
	}

}
