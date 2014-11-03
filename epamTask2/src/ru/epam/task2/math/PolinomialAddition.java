package ru.epam.task2.math;

import java.util.HashMap;

import ru.epam.task2.gui.Task;

public class PolinomialAddition extends Task {
	private HashMap<Integer, Double> polinomRes;

	public PolinomialAddition(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	@Override
	protected void doLogic() {
		polinomRes = new HashMap<Integer, Double>();
		String[] strings = getStrings();
		if ((strings.length != 2) || (!stringsToResDoublePolinom(strings))) {
			drawTitle();
			System.out.println(" Данные в файле task7.txt не являются коэффициентами 2х многочленов");
			printEmptyLines(14);
			return;
		}
		drawTitle();
		printResults();
		printEmptyLines(13);
	}

	private void printResults() {
		System.out.println(" Результатом сложения двух многочленов из файла task7.txt будет многочлен:");
		String polinom = "";
		for (int i = polinomRes.size() - 1; i >= 0; i--) {
			if (polinomRes.get(i) == 0) {
				continue;
			}
			polinom += " + (" + polinomRes.get(i) + ")X^" + i;
		}
		System.out.println(polinom);
	}

	private boolean stringsToResDoublePolinom(String[] strings) {
		String[] wordsA = getWordsFromString(strings[0]);
		String[] wordsB = getWordsFromString(strings[1]);
		if (wordsA.length != wordsB.length) {
			return false;
		}
		try {
			for (int i = wordsA.length - 1; i >= 0; i--) {
				double elementA = Double.parseDouble(wordsA[(wordsA.length - 1) - i]);
				if (Double.isInfinite(elementA) || Double.isNaN(elementA)) {
					throw new NumberFormatException();
				}
				double elementB = Double.parseDouble(wordsB[(wordsB.length - 1) - i]);
				if (Double.isInfinite(elementB) || Double.isNaN(elementB)) {
					throw new NumberFormatException();
				}				
				polinomRes.put(i, elementA + elementB);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
		
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║   Задача №7. Сложить два многочлена заданной степени, если коэффициенты    ║\r\n" + 
				 			" ║                  многочленов хранятся в объекте HashMap                    ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║  Коэффициенты двух многочленов считаны из файла task7.txt (из двух строк,  ║\r\n" +
				 			" ║     элементы которых разделены пробелами). Результат выводится на экран    ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
