package ru.epam.task1.gui;

public abstract class Task {
	
	private static int nextId = 1;
	
	private int id;
	private String shortName;
	
	protected Task(String shortName) {
		id = nextId;
		nextId++;
		this.shortName = shortName;
	}
	
	public int getId() {
		return id;
	}

	public String getShortName() {
		return shortName;
	}

	public abstract void drawTask();
	
	public void drawTask(String[] string) {
		//Вариант метода для передачи аргументов командной строки
	}

}
