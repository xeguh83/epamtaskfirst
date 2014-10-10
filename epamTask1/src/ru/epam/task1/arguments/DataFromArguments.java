package ru.epam.task1.arguments;

import ru.epam.task1.gui.Task;

public class DataFromArguments extends Task {

	public DataFromArguments(String shortName) {
		super(shortName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawTask() {
		// Не используется
	}

	@Override
	public void drawTask(String[] string) {
		//TODO реализовать выполнение из командной строки
		System.out.println("I get string from command line");
	}
	

}
