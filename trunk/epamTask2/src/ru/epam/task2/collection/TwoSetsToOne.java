package ru.epam.task2.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import ru.epam.task2.gui.Task;

public class TwoSetsToOne extends Task {
		

	public TwoSetsToOne(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	@Override
	protected void doLogic() {
		String[] words = getWordsFromStringArray();
		TreeSet<Double> firstSet = new TreeSet<Double>();
		HashSet<Double> secondSet = new HashSet<Double>();
		boolean getFirstNegNum = false;
		for (String word : words) {
			try {
				Double element = Double.parseDouble(word);
				if (Double.isInfinite(element) || Double.isNaN(element)) {
					throw new NumberFormatException();
				}
				if (!getFirstNegNum && element >= 0) {
					firstSet.add(element);
				} else if (element < 0){
					if (getFirstNegNum) return;
					getFirstNegNum = true;
				} else {
					secondSet.add(element);
				}
			} catch (Exception e) {
				return;
			}
		}
		List<Double> resList = new ArrayList<Double>(firstSet);
		resList.addAll(secondSet);
		Collections.sort(resList);
		writeStringsToFile("task16output.txt", getStringArray(resList));
	}
	
	private String[] getStringArray(List<Double> list) {
		Iterator<Double> iter = list.iterator();
		String[] stringArray = new String[list.size()];
		for (int i = 0; i < stringArray.length; i++) {
			stringArray[i] = String.format("%#16.8f", iter.next());
		}
		return stringArray;
	}

	@Override
	protected void drawTitle() {
		// TODO Auto-generated method stub
		
	}

}
