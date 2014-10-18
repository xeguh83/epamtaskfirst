package ru.epam.task1.word;

import java.util.HashSet;

import ru.epam.task1.gui.Task;

public final class CharMinWord extends Task {
	
	public CharMinWord(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	@Override
	protected void doLogic() {
		String[] words = getWordsFromStringArray();
		int i = getMinDifCharWordIndex(words);
		System.out.println("Слово с минимальным количеством разных символов(" + words[i].length() + "): " + words[i]);
		Task.printEmptyLines(14);
	}

	private int getMinDifCharWordIndex(String[] words) {
		int minDifCharWordLength = Integer.MAX_VALUE;
		int minDifCharWordIndex = 0;
		for (int i = 0; i < words.length; i++) {
			HashSet<Character> difCharword = new HashSet<Character>();
			for (int j = 0; j < words[i].toCharArray().length; j++) {
				difCharword.add(words[i].toCharArray()[j]);
			}
			if (difCharword.size() < minDifCharWordLength) {
				minDifCharWordLength = difCharword.size(); 
				minDifCharWordIndex = i;
			}
		}
		return minDifCharWordIndex;
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║   Задача №4. Найти слово, в котором число различных символов минимально.   ║\r\n" +
							" ║               Если таких слов несколько вывести первое из них.             ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║              Слова разделённые пробелами считаны из файла task4.txt        ║\r\n" +
							" ║                          Нажмите Enter для продолжения                     ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}


}
