package ru.epam.task1.word;

import java.util.HashSet;

import ru.epam.task1.gui.Task;

public class DifferentCharWord extends Task {
	
	public DifferentCharWord(String shortName, String incomingString) {
		super(shortName, incomingString);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doLogic() {
		// TODO Auto-generated method stub
		String[] word = getWords();
		HashSet<Character>[] hashSets = new HashSet[word.length];
		boolean result = false;
		for (int i = 0; i < hashSets.length; i++) {
			hashSets[i] = new HashSet<Character>();
			for (int j = 0; j < word[i].toCharArray().length; j++) {
				hashSets[i].add(word[i].toCharArray()[j]);
			}
			if (word[i].length() == hashSets[i].size()) {
				printResultWord(word[i]);
				result = true;
				break;
			}
		}
		if (!result) {
			System.out.println("Слово состоящее только из различных символов не найдено!");
		}
		System.out.println("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n");
	}

	private void printResultWord(String word) {
		// TODO Auto-generated method stub
		System.out.println("Первое слово состоящее из различных символов: " + word);
	}

	@Override
	protected void drawTitle() {
		// TODO Auto-generated method stub
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║       Задача №7. Найти слово, состоящее только из различных символов.      ║\r\n" +
							" ║               Если таких слов несколько вывести первое из них.             ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║   Слова разделённые знаком \" \" буду считаны из файла data.properties из    ║\r\n" +
							" ║              параметра task7. Нажмите Enter для загрузки данных.           ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
