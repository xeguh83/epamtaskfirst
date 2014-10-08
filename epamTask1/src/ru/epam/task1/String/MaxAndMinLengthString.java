package ru.epam.task1.String;

public class MaxAndMinLengthString extends Task {
	
	private int id;
	private String shortName;
	
	public MaxAndMinLengthString() {
		this.id = Task.getNextId();
		Task.setNextId(this.id + 1);
		this.shortName = "Самая короткая и длинная строки";
	}
	
	public String getShortName() {
		return shortName;
	}

	@Override
	protected void drawTask() {
		// TODO Auto-generated method stub
		
	}
	

}
