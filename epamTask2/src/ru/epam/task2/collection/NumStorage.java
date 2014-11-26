package ru.epam.task2.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import ru.epam.task2.gui.Task;

/**
 * <p>Класс задания № 15</p>
 * Класс отрисовывает задание и содержит структуру в которую можно добавлять элементы, удалять элементы и искать ближайший к заданному элемент.
 * @author Туркин А.К.
 */
public class NumStorage extends Task {
	
	/**
	 * Структура хранения действительных чисес реализованная на базе класса <p>ArrayList</p>
	 */
	private ArrayList<Double> structure;

	/**
	 * Конструктор передает наследуемому классу краткое описание задания
	 * @param shortName краткое описание задания
	 */
	public NumStorage(String shortName) {
		super(shortName);
	}

	/**
	 * Метод инициализирует структуру на базе класса <p>ArrayList</p>, при этом переопределяя в ней метод <p>toString</p>
	 * Затем метод циклично перерисовывает экран состоящий из панели задания, текущего содержимого коллекции и текста запроса и 
	 * выполняет методы в зависимости от результата запроса
	 */
	@Override
	protected void doLogic() {
		structure = new ArrayList<Double>() {
			@Override
			public String toString() {
		        Iterator<Double> it = iterator();
		        if (! it.hasNext())
		            return "[]";

		        StringBuilder sb = new StringBuilder();
		        sb.append('[');
		        for (;;) {
		        	Double e = it.next();
		            sb.append(e.equals(this) ? "(this Collection)" : String.format("%#8.2f", e));
		            if (! it.hasNext())
		                return sb.append(']').toString();
		            sb.append(';').append(' ');
		        }
			}
		};
		while (true) {
			drawSreen(structure);
			String option = askOption(" Введите действие (\"+\" добавление, \"-\" удаление, \"?\" поиск, 0 выход): ");
			if (option.equals("+")) {
				addElement();
			} else if (option.equals("-")) {
				removeElement();
			} else if (option.equals("?")) {
				findClosestElement();
			} else if (option.equals("0")) {
				return;
			}
		}
		
		
	}


	/**
	 * Метод выводит ближайший элемент к указанному числу. В случае некорректного ввода метод 
	 * информирует об этом пользователя и повторяет запрос. В случае корректного ввода, метод 
	 * выводит ответ на экран и завершает свою работу
	 */
	private void findClosestElement() {
		while (true) {
			drawSreen(structure);
			System.out.print(" Введите число для поиска ближайшего из структуры: ");
			try {
				Scanner in = new Scanner(System.in);
				String word = in.nextLine();
				Double value = Double.parseDouble(word);
				if (Double.isInfinite(value) || Double.isNaN(value)) {
					throw new NumberFormatException();
				} else if (structure.size() == 0) {
					printError(structure, " Ошибка поиска! Структура пустая!");
				} else {
					printError(structure, " Ближайший к" + String.format("%#8.2f", value) 
							+ " элемент это" + String.format("%#16.8f", getClosestValue(value)));
					return;
				}
			} catch (Exception e) {
				printError(structure, " Ошибка поиска числа в структуре!");
			}
		}
	}

	/**
	 * Метод возвращает из структуры ближайшее значение к переданному в параметре значению 
	 * @param value значение ближайшее к которому требуется найти
	 * @return ближайшее значение к переданному в параметре значению 
	 */
	private Double getClosestValue(Double value) {
		HashMap<Double, Double> dif = new HashMap<Double, Double>();
		Iterator<Double> iter = structure.iterator();
		while (iter.hasNext()) {
			Double structElement = iter.next();
			dif.put(Math.abs(structElement - value), structElement);
		}
		return dif.get(Collections.min(dif.keySet()));
	}

	/**
	 * Метод циклично перерисовывает экран состоящий из панели задания, текущего содержимого коллекции и текста запроса и 
	 * принимает от пользователя значение элемента которого требуется удалить. В случае ошибочного ввода, либо в случае
	 * если такого элемента нет пользователь информируется об этом. В случае корректного ввода элемент удаляется из 
	 * структуры, а метод завершает свою работу 
	 */
	private void removeElement() {
		while (true) {
			drawSreen(structure);
			System.out.print(" Введите вещественное число для удаления из структуры: ");
			try {
				Scanner in = new Scanner(System.in);
				String word = in.nextLine();
				Double value = Double.parseDouble(word);
				if (Double.isInfinite(value) || Double.isNaN(value)) {
					throw new NumberFormatException();
				} else if (!structure.remove(value)) {
					printError(structure, " Ошибка! Такого числа нет в структуре!");
				} else {
					return;
				}
			} catch (Exception e) {
				printError(structure, " Ошибка удаления числа из структуры!");
			}
		}
	}

	/**
	 * Метод циклично перерисовывает экран состоящий из панели задания, текущего содержимого коллекции и текста запроса и 
	 * принимает от пользователя значение элемента которого требуется добавить в структуру. В случае ошибочного ввода, либо в случае
	 * если такой элемент уже есть в структуре, пользователь информируется об этом. В случае корректного ввода элемент добавляется в 
	 * структуру, а метод завершает свою работу 
	 */
	private void addElement() {
		while (true) {
			drawSreen(structure);
			System.out.print(" Введите вещественное число для добавления в структуру: ");
			try {
				Scanner in = new Scanner(System.in);
				String word = in.nextLine();
				Double value = Double.parseDouble(word);
				if (Double.isInfinite(value) || Double.isNaN(value)) {
					throw new NumberFormatException();
				} else if (structure.contains(value)) {
					printError(structure, " Ошибка! Число уже добавлено в структуру!");
				} else {
					structure.add(value);
					return;
				}
			} catch (Exception e) {
				printError(structure, " Ошибка добавления числа в структуру!");
			}
		}
	}

	/* (non-Javadoc)
	 * @see ru.epam.task2.gui.Task#drawTitle()
	 */
	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║   Задача №15. Реализовать структуру хранения чисел с поддержкой операций   ║\r\n" +
				 			" ║        добавления, удаления и поиска ближайшего по значению элемента       ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║ Добавление/удаление ребер происходит через запрос действия у пользователя: ║\r\n" +
				 			" ║         \"+\" добавление, \"-\" удаление, \"?\" поиск, 0 выход в меню.           ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
	}

}
