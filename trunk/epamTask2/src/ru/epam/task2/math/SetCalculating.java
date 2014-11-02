package ru.epam.task2.math;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import ru.epam.task2.gui.Task;

public class SetCalculating extends Task {
	private final ArrayList<HashSet> intSets;

	public SetCalculating(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
		intSets = new ArrayList<HashSet>();
	}

	@Override
	public void drawTask() {
		Scanner in = new Scanner(System.in);
		while (true) {
			drawTitle();
			Task.printEmptyLines(15);
			System.out.print(" Введите любую строку для продолжения (0 для выхода в меню)");
			try {
				String option = in.nextLine();
				if (option.equalsIgnoreCase("0")) {
					break;
				} else {
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
		intSets.clear();
		String[] strings = getStrings();
		if (!stringArrayToListOfIntSets(strings)) {
			drawTitle();
			System.out.println(" Ошибка чтения множеств из файла");
			printEmptyLines(15);
			return;
		}
		while (true) {
			drawTitle();
			System.out.println(" Считаны следующие множества:");
			for (HashSet<Integer> set : intSets) {
				System.out.println(" " + set.toString());			
			}
			printEmptyLines(14 - intSets.size());
			System.out.print(" Введите операцию + для объединения множеств, а * для пересечения: ");
			String operation = getOperation();
			if (operation.equalsIgnoreCase("+")) {
				combineSets();
				return;
			} else if (operation.equalsIgnoreCase("*")) {
				crossSets();
				return;
			}
			
		}
		
	}

	private void crossSets() {
		HashSet<Integer> resultSet = new HashSet<Integer>();
		for (HashSet<Integer> set : intSets) {
			resultSet.addAll(set);
		}
		for (HashSet<Integer> set : intSets) {
			resultSet.retainAll(set);
		}
		outputResultOperation(resultSet, "пересечения");	
	}

	private void combineSets() {
		HashSet<Integer> resultSet = new HashSet<Integer>();
		for (HashSet<Integer> set : intSets) {
			resultSet.addAll(set);
		}
		outputResultOperation(resultSet, "объединения");
	}

	private void outputResultOperation(HashSet<Integer> resultSet, String operation) {
		drawTitle();
		System.out.println(" Результат " + operation + " множеств:");
		System.out.println(" " + resultSet.toString());
		printEmptyLines(13);
	}

	private String getOperation() {
		String option;
		try {
			Scanner in = new Scanner(System.in);
			option = in.nextLine();
		} catch (Exception e) {
			return null;
		}
		return option;
	}

	private boolean stringArrayToListOfIntSets(String[] strings) {
		for (String string : strings) {
			String[] words = getWordsFromString(string);
			HashSet<Integer> intSet = new HashSet<Integer>();
			for (String word : words) {
				try {
					intSet.add(Integer.parseInt(word));					
				} catch (NumberFormatException e) {
					return false;
				}
			}
			intSets.add(intSet);
		}
		return true;
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║  Задача №4. Определить множество на основе множества целых чисел. Создать  ║\r\n" +
				 			" ║          методы для определения пересечения и объединения множеств         ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║    Множества целых чисел считаны из файла task4.txt (строка - множество    ║\r\n" +
				 			" ║  элементов разделенных пробелами). Множество-результат выводится на экран  ║\r\n" +
		 					" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
