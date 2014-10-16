package ru.epam.task1.word;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.epam.task1.gui.Task;

public class PalindromWord extends Task {

	public PalindromWord(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doLogic() {
		// TODO Auto-generated method stub
		String[] words = getWords();
		ArrayList<String> palindromWords = wordsConsistOnlyNums(words);
		for (int i = 0; i < palindromWords.size(); i++) {
				if (!isPalindrom(palindromWords.get(i))) {
					palindromWords.remove(i);
				}
		}
		printOneOrSecondPalindromWord(palindromWords);
		Task.printEmptyLines(14);
	}

	private void printOneOrSecondPalindromWord(ArrayList<String> palindromWords) {
		// TODO Auto-generated method stub
		if (palindromWords.size() == 0) {
			System.out.println("Слова-палиндромы состоящие только из цифр не найдены");
		} else if (palindromWords.size() == 1) {
			System.out.println("Найдено единственное слово-палиндром состоящее только из цифр: " + palindromWords.get(0));
		} else {
			System.out.println("Второе слово-палиндром состоящее только из цифр: " + palindromWords.get(1));
		}
	}

	private ArrayList<String> wordsConsistOnlyNums(String[] words) {
		// TODO Auto-generated method stub
		ArrayList<String> wordsOnlyNums = new ArrayList<String>();
		Pattern pattern = Pattern.compile("[0-9]+");
        for (String word : words) {
            Matcher numMatcher = pattern.matcher(word);
            if (numMatcher.matches()) {
            	wordsOnlyNums.add(word);
            }
        }
		return wordsOnlyNums;
	}

	private boolean isPalindrom(String word) {
		// TODO Auto-generated method stub
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

	@Override
	protected void drawTitle() {
		// TODO Auto-generated method stub
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║   Задача №8. Среди слов состоящих только из цифр, найти слово-палиндром.   ║\r\n" +
							" ║              Если таких слов больше одного, найти второе из них.           ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║   Слова разделённые знаком \" \" буду считаны из файла data.properties из    ║\r\n" +
							" ║              параметра task8. Нажмите Enter для загрузки данных.           ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
