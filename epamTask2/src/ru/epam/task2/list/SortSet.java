package ru.epam.task2.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import ru.epam.task2.gui.Task;

/**
 * <p>Класс реализует задание № 13</p>
 * Класс не используя дополнительных объектов переставляет элементы списка
 * целых чисел так, чтобы сначала шли числа меньше заданного числа, а затем больше
 * @author Туркин А.К.
 */
public class SortSet extends Task{

	/**
	 * Конструктор передает наследуемому классу краткое описание и исходные данные по входящим параметрам
	 * @param shortName краткое описание задания
	 * @param incomingStrings массив исходных данных
	 */
	public SortSet(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	/**
	 * Метод содержит тело задания. Сначала метод считывает слова из файла, затем преобразует 
	 * их в массив чисел. При ошибке преобразования метод информирует об этом пользователя и 
	 * завершает свою работу. Затем метод запрашивает у пользователя число, сортирует коллекцию 
	 * полученную из массива чисел и выводит сначала те элементы которые меньше заданного числа, 
	 * а затем те элементы которые больше
	 */
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

	/**
	 * Метод циклично отрисовывает экран и запрашивает у пользователя число для сравнения чисел 
	 * в списке. В случае ошибочного ввода метод перерисовывает экран и повторяет запрос
	 * @return число введенное пользователем
	 */
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

	/**
	 * Метод преобразует массив слов в массив целых чисел. В случае 
	 * ошибки преобразования метод возвращает <code>null</code>
	 * @param words список слов из файла
	 * @return массив целых чисел
	 */
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

	/* (non-Javadoc)
	 * @see ru.epam.task2.gui.Task#drawTitle()
	 */
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
