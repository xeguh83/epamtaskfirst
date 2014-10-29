package ru.epam.task1.word;

import java.util.HashSet;

import ru.epam.task1.gui.Task;

/**
 * Класс находит в выводит в консоль первое слово у которого все коды символов различны
 * @author Туркин А.К.
 */
public class DifferentCharWord extends Task {
	
	/**
	 * Конструктор для передачи короткого наименования задачи для постройки таблицы задач и 
	 * массива строк содержащего начальные условия задачи
	 * @param shortName короткое наименование задачи
	 * @param incomingStrings массив строк с начальным условием задачи
	 */
	public DifferentCharWord(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	/**
	 * Метод формирует массив слов, переданных из файла с помощью метода <code>getWordsFromStringArray</code> 
	 * и выводит в консоль первое слово у которого все коды символов различны
	 * @see #getWordsFromStringArray() 
	 */
	@Override
	protected void doLogic() {
		String[] word = getWordsFromStringArray();
		HashSet<Character> charSet;
		boolean result = false;
		for (int i = 0; i < word.length; i++) {
			charSet = new HashSet<Character>();
			for (int j = 0; j < word[i].toCharArray().length; j++) {
				charSet.add(word[i].toCharArray()[j]);
			}
			if (word[i].length() == charSet.size()) {
				printResultWord(word[i]);
				result = true;
				break;
			}
		}
		if (!result) {
			System.out.println("Слово состоящее только из различных символов не найдено!");
		}
		Task.printEmptyLines(14);
	}

	/**
	 * Метод выводит в консоль первое слово состоящее из различных символов
	 * @param word слово состоящее из различных символов
	 */
	private void printResultWord(String word) {
		System.out.println("Первое слово состоящее из различных символов: " + word);
	}

	/* (non-Javadoc)
	 * @see ru.epam.task1.gui.Task#drawTitle()
	 */
	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║       Задача №7. Найти слово, состоящее только из различных символов.      ║\r\n" +
							" ║               Если таких слов несколько вывести первое из них.             ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║              Слова разделённые пробелами считаны из файла task7.txt        ║\r\n" +
				 			" ║                          Нажмите Enter для продолжения                     ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
