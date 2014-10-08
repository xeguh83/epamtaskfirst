package ru.epam.task1.String;

public class MoreLessThanMedian extends Task {
	
	private int id;
	private String shortName;
	
	public MoreLessThanMedian() {
		this.id = Task.getNextId();
		Task.setNextId(this.id + 1);
		this.shortName = "Строки меньше/больше средней длины";
	}

	public int getId() {
		return id;
	}

	public String getShortName() {
		return shortName;
	}

	@Override
	public void drawTask() {
		// TODO Auto-generated method stub
		
	}

}
