package ru.epam.task1.math;

import java.text.DateFormatSymbols;

import ru.epam.task1.gui.Task;

public class NumToMonth extends Task {

	public NumToMonth(String shortName) {
		super(shortName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawTask() {
		DateFormatSymbols date = new DateFormatSymbols();
		String[] m = date.getMonths();
		for (String string : m) {
			System.out.println(string);
		}
	}

	@Override
	protected void doLogic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void drawTitle() {
		// TODO Auto-generated method stub
		
	}

}
