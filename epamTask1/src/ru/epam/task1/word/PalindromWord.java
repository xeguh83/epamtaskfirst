package ru.epam.task1.word;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.epam.task1.gui.Task;

/**
 * Класс находит и выводит в консоль единственное или второе слово палиндром, 
 * состоящее только из цифр
 * @author Туркин А.К.
 */
public class PalindromWord extends Task {

	/**
	 * Конструктор для передачи короткого наименования задачи для постройки таблицы задач и 
	 * массива строк содержащего начальные условия задачи
	 * @param shortName короткое наименование задачи
	 * @param incomingStrings массив строк с начальным условием задачи
	 */
	public PalindromWord(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	/**
	 * Метод формирует массив слов, переданных из файла с помощью метода <code>getWordsFromStringArray</code> 
	 * и выводит в консоль единственное или второе слово палиндром, состоящее только из цифр 
	 * @see #getWordsFromStringArray() 
	 */
	@Override
	protected void doLogic() {
		String[] words = getWordsFromStringArray();
		ArrayList<String> palindroms = getPalindromsOnlyNumList(words);
		printOneOrSecondPalindromWord(palindroms);
		Task.printEmptyLines(14);
	}

	/**
	 * Метод выводит в консоль единственное или второе слово из списка
	 * @param palindromWords список слов
	 */
	private void printOneOrSecondPalindromWord(ArrayList<String> palindromWords) {
		if (palindromWords.size() == 0) {
			System.out.println(" Слова-палиндромы состоящие только из цифр не найдены");
		} else if (palindromWords.size() == 1) {
			System.out.println(" Найдено единственное слово-палиндром состоящее только из цифр: " + palindromWords.get(0));
		} else {
			System.out.println(" Второе слово-палиндром состоящее только из цифр: " + palindromWords.get(1));
		}
	}

	/**
	 * Метод возращает обработанный список слов по входящему массиву слов, в 
	 * котором остаются только слова палиндромы состоящие только из цифр
	 * @param words массив слов для обработки
	 * @return список слов палиндромов состоящих только из цифр
	 */
	private ArrayList<String> getPalindromsOnlyNumList(String[] words) {
		ArrayList<String> palindromsOnlyNums = new ArrayList<String>();
		Pattern pattern = Pattern.compile("[0-9]+");
        for (String word : words) {
            Matcher numMatcher = pattern.matcher(word);
            if (numMatcher.matches() && isPalindrom(word)) {
            	palindromsOnlyNums.add(word);
            }
        }
		return palindromsOnlyNums;
	}

	/**
	 * Метод определяет является ли слово палиндромом
	 * @param word слово для определения
	 * @return <code>true</code> если слово является палиндромом и <code>false</code> если не является
	 */
	private boolean isPalindrom(String word) {
		char[] arrayOfChar = word.toCharArray();
		String oppositeWord = "";
		for (int i = arrayOfChar.length - 1; i >= 0; i--) {
			oppositeWord += String.valueOf(arrayOfChar[i]);
		}
		if (word.equals(oppositeWord)) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see ru.epam.task1.gui.Task#drawTitle()
	 */
	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║   Задача №8. Среди слов состоящих только из цифр, найти слово-палиндром.   ║\r\n" +
							" ║              Если таких слов больше одного, найти второе из них.           ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║              Слова разделённые пробелами считаны из файла task8.txt        ║\r\n" +
				 			" ║                          Нажмите Enter для продолжения                     ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
