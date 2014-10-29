package ru.epam.task1.string;

import ru.epam.task1.gui.Task;

/**
 * Класс выводит в консоль строки которые меньше/больше средней по длине
 * @author Туркин А.К.
 */
public class MoreLessThanMedian extends Task {
	
	/**
	 * Конструктор для передачи короткого наименования задачи для постройки таблицы задач и 
	 * массива строк содержащего начальные условия задачи
	 * @param shortName короткое наименование задачи
	 * @param incomingStrings массив строк с начальным условием задачи
	 */
	public MoreLessThanMedian(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}


	/**
	 * Метод вычисляет среднюю длину строки из массива строк и передает 
	 * ее в метод <code>printResult</code>
	 */
	@Override
	protected void doLogic() {
		String[] strings = getStrings();
		int mediana = 0;
		for (String string : strings) {
			mediana += string.length();
		}
		mediana = mediana / strings.length;
		printResult(strings, mediana);
	}

	/**
	 * Метод выводит в консоль строки, которые меньше или равны заданной средней длине, а
	 * затем выводит строки которые больше средней заданой длины
	 * @param strings массив строк для вывода
	 * @param mediana средняя длина строки
	 */
	private void printResult(String[] strings, int mediana) {
		System.out.println("Строки с длинами меньше или равными средней (" + mediana + "):");
		for (String string : strings) {
			if (string.length() <= mediana) System.out.println(string + "("+ string.length() + ")");
		}
		System.out.println("Строки с длинами больше средней (" + mediana + "):");
		for (String string : strings) {
			if (string.length() > mediana) System.out.println(string + "("+ string.length() + ")");
		}
	}

	/* (non-Javadoc)
	 * @see ru.epam.task1.gui.Task#drawTitle()
	 */
	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║     Задача №3. Ввести n строк с консоли. Вывести на консоль те строки,     ║\r\n" +
							" ║             длина которых меньше (больше) средней, а также длину.          ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║Строки будут считаны из файла task3.txt (учитывается только перевод строки).║\r\n" +
				 			" ║                      Для продолжения нажмите Enter.                        ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
