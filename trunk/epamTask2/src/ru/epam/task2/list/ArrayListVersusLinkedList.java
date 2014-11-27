package ru.epam.task2.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import ru.epam.task2.gui.Task;

/**
 * <p>Класс реализует задание № 12</p>
 * Класс реализует алгоритм циклиного вычеркивания из списка каждого второго элемента пока не останется один элемент.
 * Список реализуется 2мя способами: на базе <code>ArrayList</code> и на базе <code>LinkedList</code>. Класс выводит время операции в 
 * наносекундах чтобы можно было сравнить какая из реализации эффективнее  
 * @author Туркин А.К. 
 */
public class ArrayListVersusLinkedList extends Task {

	/**
	 * Конструктор передает наследуемому классу краткое описание задания
	 * @param shortName краткое описание задания
	 */
	public ArrayListVersusLinkedList(String shortName) {
		super(shortName);

	}

	@Override
	protected void doLogic() {
		int arraySize = askArraySize(); 
		Integer[] array = fillArrayOfInt(arraySize);
		String option = askTypeOfCollection();
		Integer answer = null;
		long startTime = System.nanoTime();
		if (option.equals("ArrayList")) {
			answer = getAnswerUsingArrayList(array);
		} else {
			answer = getAnswerUsingLinkedList(array);
		}
		long endTime = System.nanoTime();
		drawTitle();
		System.out.println(" Оставшийся единственный элемент: " + answer);
		System.out.println(" Расчет с использованием " + option + " занял " + (endTime - startTime) + " нс"); 
		printEmptyLines(13);
	}

	private Integer getAnswerUsingLinkedList(Integer[] array) {
		LinkedList<Integer> list = new LinkedList<Integer>(Arrays.asList(array));
		int index = 1;
		while (list.size() > 1) {
			if (list.size() < index) {
				index = 1;
			} else if (list.size() == index) {
				index = 0;
			} else {
				list.remove(index);
				index++;
			}
		}
		return list.get(0);	}

	private Integer getAnswerUsingArrayList(Integer[] array) {
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(array)); 
		int index = 1;
		while (list.size() > 1) {
			if (list.size() < index) {
				index = 1;
			} else if (list.size() == index) {
				index = 0;
			} else {
				list.remove(index);
				index++;
			}
		}
		return list.get(0);
	}

	private String askTypeOfCollection() {
		while (true) {
			drawTitle();
			System.out.println(" Введите номер коллекции для обработки чилел: \r\n"
					+ " 1) ArrayList \r\n"
					+ " 2) LinkedList");
			printEmptyLines(12);
			int option;
			try {
				Scanner in = new Scanner(System.in);
				option = Integer.parseInt(in.nextLine());
				if (option == 1) {
					return "ArrayList";
				} else if (option == 2) {
					return "LinkedList";
				}
			} catch (Exception e) {
				continue;
			}
		}
	}

	private Integer[] fillArrayOfInt(int arraySize) {
		Integer[] array = new Integer[arraySize];
		for (int i = 0; i < array.length; i++) {
			array[i] = Integer.valueOf(i + 1);
		}
		return array;
	}

	// Разница заметна при 100000 элементах
	private int askArraySize() {
		while (true) {
			drawTitle();
			printEmptyLines(14);
			System.out.println(" Введите количество элементов массива больше нуля: ");
			int count;
			try {
				Scanner in = new Scanner(System.in);
				count = Integer.parseInt(in.nextLine());
				if (count > 0 ) {
					return count;
				}
			} catch (Exception e) {
				continue;
			}
		}
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
		 					" ║Задача №12. В кругу стоят N человек, пронумерованных от 1 до N. При ведении ║\r\n" +
		 					" ║ счета по кругу вычеркивается каждый второй человек, пока не останется один ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║   Составить две программы, моделирующие процесс. Одна из программ должна   ║\r\n" +
				 			" ║             использовать класс ArrayList, а вторая LinkedList              ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
	}

}
