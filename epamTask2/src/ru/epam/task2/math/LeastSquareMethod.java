package ru.epam.task2.math;

import java.util.HashMap;

import ru.epam.task2.gui.Task;

public class LeastSquareMethod extends Task {
	private HashMap<Integer, Double> mapI, mapU, mapR;

	public LeastSquareMethod(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	@Override
	protected void doLogic() {
		mapI = new HashMap<Integer, Double>();
		mapU = new HashMap<Integer, Double>();
		mapR = new HashMap<Integer, Double>();
		HashMap<Integer, Double> map = new HashMap<Integer, Double>();
		
	}

	@Override
	protected void drawTitle() {
		// TODO Auto-generated method stub
		
	}

}
