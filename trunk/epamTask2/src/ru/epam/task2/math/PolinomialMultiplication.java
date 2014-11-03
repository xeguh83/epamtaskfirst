package ru.epam.task2.math;

import java.util.ArrayList;
import java.util.HashMap;

import ru.epam.task2.gui.Task;

public class PolinomialMultiplication extends Task {

	private ArrayList<Double> polinomA;
	private ArrayList<Double> polinomB;
	private HashMap<Integer, Double> polinomRes;

	public PolinomialMultiplication(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	@Override
	protected void doLogic() {
		polinomA = new ArrayList<Double>();
		polinomB = new ArrayList<Double>();
		polinomRes = new HashMap<Integer, Double>();
		String[] strings = getStrings();
		if ((strings.length != 2) || (!stringsToDoublePolinoms(strings))) {
			drawTitle();
			System.out.println(" Данные в файле task8.txt не являются коэффициентами 2х многочленов");
			printEmptyLines(14);
			return;
		}
		setResMulPolinoms();
		drawTitle();
		printResults();
		printEmptyLines(13);
	}

	private void setResMulPolinoms() {
		for (int i = 0; i < polinomA.size(); i++) {
			for (int j = 0; j < polinomB.size(); j++) {
				int k = i + j;
				double element = polinomA.get(i) * polinomB.get(j);
				if (polinomRes.get(k) != null) {
					polinomRes.put(k, polinomRes.get(k) + element);
				} else {
					polinomRes.put(k, element);
				}				
			}
		}
	}

	private void printResults() {
		System.out.println(" Результатом перемножения двух многочленов из файла task8.txt будет многочлен:");
		String polinom = "";
		for (int i = polinomRes.size() - 1; i >= 0; i--) {
			if (polinomRes.get(i) == 0) {
				continue;
			}
			polinom += " + (" + polinomRes.get(i) + ")X^" + i;
		}
		System.out.println(polinom);
	}

	private boolean stringsToDoublePolinoms(String[] strings) {
		String[] wordsA = getWordsFromString(strings[0]);
		String[] wordsB = getWordsFromString(strings[1]);
		if (wordsA.length != wordsB.length) {
			return false;
		}
		try {
			for (int i = 0; i < wordsA.length; i++) {
				double elementA = Double.parseDouble(wordsA[(wordsA.length - 1) - i]);
				if (Double.isInfinite(elementA) || Double.isNaN(elementA)) {
					throw new NumberFormatException();
				}
				polinomA.add(elementA);
				double elementB = Double.parseDouble(wordsB[(wordsB.length - 1) - i]);
				if (Double.isInfinite(elementB) || Double.isNaN(elementB)) {
					throw new NumberFormatException();
				}
				polinomB.add(elementB);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
		
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║   Задача №8. Умножить два многочлена заданной степени, если коэффициенты   ║\r\n" + 
				 			" ║                 многочленов хранятся в различных списках                   ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║  Коэффициенты двух многочленов считаны из файла task7.txt (из двух строк,  ║\r\n" +
				 			" ║     элементы которых разделены пробелами). Результат выводится на экран    ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
