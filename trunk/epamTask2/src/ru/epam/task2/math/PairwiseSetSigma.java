package ru.epam.task2.math;

import java.util.Formatter;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

import ru.epam.task2.gui.Task;

public class PairwiseSetSigma extends Task {
	private LinkedHashSet<Double> numSet;

	public PairwiseSetSigma(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}
	
	@Override
	public void drawTask() {
		Scanner in = new Scanner(System.in);
		while (true) {
			drawTitle();
			Task.printEmptyLines(13);
			System.out.print(" Введите любую строку для продолжения (0 для выхода в меню)");
			try {
				String option = in.nextLine();
				if (option.equalsIgnoreCase("0")) {
					break;
				} else {
					drawTitle();
					doLogic();
					pressAnyKeyForMenu();
					break;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	@Override
	protected void doLogic() {
		numSet = new LinkedHashSet<Double>();
		String[] words = getWordsFromStringArray();
		if (!stringArrayToNumSet(words)) {
			drawTitle();
			System.out.println(" Данные в файле task6.txt не являются множеством вещественных чисел");
			printEmptyLines(12);
			return;
		}
		drawTitle();
		double result = getPairwiseSetSigma(numSet);
		System.out.println(" Многоэтапная попарная сумма элементов множества равна: " + getFormattedResult(result));
		printEmptyLines(12);
	}

	private String getFormattedResult(double result) {
		Formatter f = new Formatter();
		return f.format("%#8.2f", result).toString();
	}

	private double getPairwiseSetSigma(LinkedHashSet<Double> set) {
		LinkedHashSet<Double> newSet = new LinkedHashSet<Double>();
		Iterator<Double> setIterator = set.iterator();
		while (setIterator.hasNext()) {
			double a = setIterator.next();
			if (setIterator.hasNext()) {
				double b = setIterator.next();
				newSet.add(a + b);
			} else {
				newSet.add(a);
			}
		}
		if (newSet.size() > 1) {
			return getPairwiseSetSigma(newSet);
		} else {
			return newSet.toArray(new Double[1])[0];
		}
		
	}

	private boolean stringArrayToNumSet(String[] words) {
		if (words.length == 0) {
			return false;
		}
		for (String string : words) {
			try {
				double setElement = Double.parseDouble(string);
				if (Double.isInfinite(setElement) || Double.isNaN(setElement)) {
					throw new NumberFormatException();
				}
				numSet.add(setElement);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║Задача №6. С использованием множества выполнить попарное суммирование произ-║\r\n" + 
				 			" ║ вольного конечного ряда чисел по следующим правилам: на первом этапе сум-  ║\r\n" +
				 			" ║мируются попарно рядом стоящие числа, на втором этапе суммируются результаты║\r\n" + 
				 			" ║        первого этапа и т.д. до тех пор, пока не останется одно число       ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║ Множество вещественных чисел считано из файла task6.txt (элементы множества║\r\n" +
				 			" ║ разделены пробелами). Множество-результат попарной суммы выводится на экран║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
