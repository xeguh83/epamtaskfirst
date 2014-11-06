package ru.epam.task2.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import ru.epam.task2.gui.Task;

public class SortSet extends Task{

	public SortSet(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	@Override
	protected void doLogic() {
		Integer[] nums = wordsToIntegers(getWordsFromStringArray());
		if (nums == null) {
			drawTitle();
			System.out.println(" Ошибка чтения списка чисел из файла");
			printEmptyLines(14);
			return;
		}
		Double x = askNumToCompare();
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(nums));
		Collections.sort(list);
		drawTitle();
		System.out.println(" Числа не превышающие указанного числа: ");
		for (Integer integer : list) {
			if (integer <= x) {
				System.out.print(" " + integer);
			}
		}
		System.out.println("\r\n Числа больше указанного числа: ");
		for (Integer integer : list) {
			if (integer > x) {
				System.out.print(" " + integer);
			}
		}
		printEmptyLines(12);
	}

	private Double askNumToCompare() {
		while (true) {
			drawTitle();
			printEmptyLines(15);
			System.out.print(" Введите число для сравнения чисел в списке: ");
			Double answer;
			try {
				Scanner in = new Scanner(System.in);
				answer = Double.parseDouble(in.nextLine());
				if (Double.isInfinite(answer) || Double.isNaN(answer)) {
					throw new NumberFormatException();
				} else {
					return answer;
				}
			} catch (Exception e) {
				continue;
			}
		}
	}

	private Integer[] wordsToIntegers(String[] words) {
		Integer[] nums = new Integer[words.length];
		try {
			for (int i = 0; i < nums.length; i++) {
				nums[i] = Integer.parseInt(words[i]);
			}			
		} catch (Exception e) {
			return null;
		}
		return nums;
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
		 					" ║Задача №13. Задан список целых чисел и число Х. Не используя вспомогательных║\r\n" + 
		 					" ║объектов, переставить элементы чтобы сначала они были меньше Х потом больше ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║              Список целых чисел получается из строк файла task13.txt       ║\r\n" +
				 			" ║                   Результат перестановки будет выведен на экран            ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
	}

}
