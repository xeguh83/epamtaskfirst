package ru.epam.task1.String;

public abstract class Task {
	
	private static int nextId = 1;
	
	public static int getNextId() {
		return nextId;
	}

	protected static void setNextId(int nextId) {
		Task.nextId = nextId;
	}

	protected abstract void drawTask();

}
