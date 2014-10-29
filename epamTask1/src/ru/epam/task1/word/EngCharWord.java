package ru.epam.task1.word;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.epam.task1.gui.Task;

/**
 * Класс находит слова состоящие только из латинских символов, а среди них
 * количество слов с одинаковым количеством гласных и согласных букв
 * @author Туркин А.К.
 * @since 1.4
 */
public class EngCharWord extends Task {
	
	/**
	 * Конструктор для передачи короткого наименования задачи для постройки таблицы задач и 
	 * массива строк содержащего начальные условия задачи
	 * @param shortName короткое наименование задачи
	 * @param incomingStrings массив строк с начальным условием задачи
	 */
	public EngCharWord(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}


	/**
	 * Метод формирует массив слов, переданных из файла с помощью метода <code>getWordsFromStringArray</code> 
	 * и выводит в консоль  слова состоящие только из латинских символов, а затем среди них количество слов 
	 * с одинаковым количеством гласных и согласных букв
	 * @see #getWordsFromStringArray()
	 * @since 1.4 
	 */
	@Override
	protected void doLogic() {
		String[] word = getWordsFromStringArray();
		ArrayList<String> listOfEngWords = getAndPrintEngWords(word);
		System.out.print("Среди них слова с равным числом гласных и согласных: ");
		int count = 0;
		for (String string : listOfEngWords) {
			if (string.toLowerCase().replaceAll("[^aeiou]", "").length() == string.toLowerCase().replaceAll("[aeiou]", "").length()) {
				count++;
			}
		}
		System.out.println("(" + count + ")");
		Task.printEmptyLines(13);
		
	}

	/**
	 * Метод обрабатывает массив слов и возращает только те слова в виде списка, 
	 * которые состоят только из латинских символов 
	 * @param words массив слов для обработки
	 * @return список слов содержащих только латинские символы
	 */
	private ArrayList<String> getAndPrintEngWords(String[] words) {
        System.out.print("Слов составленных из латинского алфавита: ");
		Pattern pattern = Pattern.compile("[a-zA-Z]+");
        ArrayList<String> engWords = new ArrayList<String>();
        for (String word : words) {
            Matcher latinMatcher = pattern.matcher(word);
            if (latinMatcher.matches()) {
            	engWords.add(word);
            }
        }
        System.out.println("(" + engWords.size() + ")");
        return engWords;
	}


	/* (non-Javadoc)
	 * @see ru.epam.task1.gui.Task#drawTitle()
	 */
	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║Задача №5. Найти количество слов, содержащих только символы латинского алфа-║\r\n" +
							" ║вита, а среди них - количество слов с равным числом гласных и согласных букв║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║              Слова разделённые пробелами считаны из файла task4.txt        ║\r\n" +
							" ║                          Нажмите Enter для продолжения                     ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}
}
