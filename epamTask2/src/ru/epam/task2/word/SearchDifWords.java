package ru.epam.task2.word;

import java.util.HashSet;
import java.util.Iterator;

import ru.epam.task2.gui.Task;

public class SearchDifWords extends Task {

	public SearchDifWords(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	@Override
	protected void doLogic() {
		String[] wordsArray = getWordsFromStringArray();
		HashSet<StringIgnoringCase> words = new HashSet<StringIgnoringCase>();
		for (String word : wordsArray) {
			words.add(new StringIgnoringCase(word));
		}
		writeStringsToFile("task10output.txt", getStringArray(words));
		drawTitle();
		System.out.println(" Уникальные строки с игнорированием регистра записаны в файл task10output.txt");
		printEmptyLines(14);
	}

	private String[] getStringArray(HashSet<StringIgnoringCase> words) {
		Iterator<StringIgnoringCase> wordIterator = words.iterator();
		String[] stringArray = new String[words.size()];
		for (int i = 0; i < stringArray.length; i++) {
			stringArray[i] = wordIterator.next().getValue();
		}
		return stringArray;
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║     Задача №10. Задан файл с текстом на английском языке. Выделить все     ║\r\n" + 
				 			" ║  различные слова. Игнорировать регистром букв. Использовать класс HashSet  ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║               Общая строка склеивается из строк файла task10.txt           ║\r\n" +
				 			" ║           Все различные слова будут записаны в файл task10output.txt       ║\r\n" +
		 					" ╚════════════════════════════════════════════════════════════════════════════╝");
	}
	
	private class StringIgnoringCase {
		private final String value;
		
		public StringIgnoringCase(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public int hashCode() {
			return value.toLowerCase().hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof StringIgnoringCase) {
				return value.equalsIgnoreCase(((StringIgnoringCase) obj).getValue());
			}
			return super.equals(obj);
		}

		@Override
		public String toString() {
			return value;
		}
		
		
		
	}
	
}
