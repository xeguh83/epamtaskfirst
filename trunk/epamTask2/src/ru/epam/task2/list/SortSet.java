package ru.epam.task2.list;

import java.util.Collections;
import java.util.LinkedList;

import ru.epam.task2.gui.Task;

public class SortSet extends Task{

	public SortSet(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doLogic() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		Collections.sort(list);
		for (Integer integer : list) {
			
		}
		
	}

	@Override
	protected void drawTitle() {
		// TODO Auto-generated method stub
		
	}

}
