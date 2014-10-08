package ru.epam.task1.String;

public class SortString extends Task {
	
	private int id;
	private String shortName;
	
	public SortString() {
		this.id = Task.getNextId();
		Task.setNextId(this.id + 1);
		this.shortName = "Сортировка строк";
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
