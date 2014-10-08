package ru.epam.task1.String;

public class SortString extends Task {
	
	private int id;
	private String shortName;
	
	public SortString() {
		this.id = Task.getNextId();
		Task.setNextId(this.id + 1);
		this.shortName = "Сортировка строк";
	}

	@Override
	protected void drawTask() {
		// TODO Auto-generated method stub
		
	}

}
