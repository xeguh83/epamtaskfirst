package ru.epam.task1.string;

import ru.epam.task1.gui.Task;

/**
 * Класс для нахождения минимальной и максимальной (по длине) строк в массиве
 * @author Туркин А.К.
 */
public class MaxAndMinLengthString extends Task {
	private int maxStringIndex = Integer.MIN_VALUE;
	private int minStringIndex = Integer.MAX_VALUE;
		
	/**
	 * Конструктор для передачи короткого наименования задачи для постройки таблицы задач и 
	 * массива строк содержащего начальные условия задачи
	 * @param shortName короткое наименование задачи
	 * @param incomingStrings массив строк с начальным условием задачи
	 */
	public MaxAndMinLengthString(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	/**
	 * Метод выводит в консоль список загруженных строк, затем выводит строку с максимальной длиной 
	 * и строку с минимальной длиной 
	 */
	@Override
	protected void doLogic() {
		String[] stringsForWork = getStrings();
		System.out.println("Загружены следующие строки:");
		setMaxAndMinStringLengthIndex(stringsForWork);
		System.out.println("Строка с максимальной длиной (" + stringsForWork[maxStringIndex].length() + "): " 
				+ stringsForWork[maxStringIndex]);
		System.out.println("Строка с минимальной длиной: (" + stringsForWork[minStringIndex].length() + "): "
				+ stringsForWork[minStringIndex]);
		Task.printEmptyLines(12);
	}

	/**
	 * Метод производит перебор по строкам и указывает в поля <code>maxStringIndex</code> и 
	 * <code>minStringIndex</code> индексы максимальной и минимальной строки (по ее длине) в массиве
	 * @param strings массив строк для определения максимальной и минимальной длины
	 */
	private void setMaxAndMinStringLengthIndex(String[] strings) {
		int maxStrLength = Integer.MIN_VALUE;
		int minStrLength = Integer.MAX_VALUE;
		for (int i = 0; i < strings.length; i++) {
			if (strings[i].length() > maxStrLength) {
				maxStrLength = strings[i].length();
				maxStringIndex = i;
			}
			if (strings[i].length() < minStrLength) {
				minStrLength = strings[i].length();
				minStringIndex = i;
			}
		}
	}

	/* (non-Javadoc)
	 * @see ru.epam.task1.gui.Task#drawTitle()
	 */
	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║     Задача №1. Ввести n строк с консоли, найти самую короткую и самую      ║\r\n" +
				 			" ║           длинную строки. Вывести найденные строки и их длину.             ║\r\n" +
		 					" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
		 					" ║Строки будут считаны из файла task1.txt (учитывается только перевод строки).║\r\n" +
		 					" ║                      Для продолжения нажмите Enter.                        ║\r\n" +
		 					" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
