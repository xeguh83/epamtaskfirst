package ru.epam.task1.word;

import java.util.HashSet;

import ru.epam.task1.gui.Task;

public final class CharMinWord extends Task {
	private String incomingString = "Мама мыла раму. Мама. Мыла. Раму. Мама мыла раму вчера. Вчера.мама будет мыть раму завтра";
	
	public CharMinWord(String shortName) {
		super(shortName);
	}

	@Override
	protected void doLogic() {
		// TODO Auto-generated method stub
		String[] word = incomingString.split("\\s");
		HashSet[] hashSets = new HashSet[word.length];
		for (int i = 0; i < word.length; i++) {
			hashSets[i] = new HashSet();
			for (int j = 0; j < word[i].toCharArray().length; j++) {
				hashSets[i].add(word[i].toCharArray()[j]);
			}
		}
		int minLength = findMinWordLength(hashSets);
		int i = 0;
		while (hashSets[i].size() > minLength) {
			i++;
		}
		System.out.println("Слово с минимальным количеством разных символов(" + minLength + "): " + word[i]);
		System.out.println("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n");
	}

	private int findMinWordLength(HashSet[] hashSets) {
		// TODO Auto-generated method stub
		int minLegth = Integer.MAX_VALUE;
		for (int i = 0; i < hashSets.length; i++) {
			if (hashSets[i].size() < minLegth) minLegth = hashSets[i].size();
		}
		return minLegth;
	}

	@Override
	protected void drawTitle() {
		// TODO Auto-generated method stub
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║   Задача №4. Найти слово, в котором число различных символов минимально.   ║\r\n" +
							" ║               Если таких слов несколько вывести первое из них.             ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║   Слова разделённые знаком \" \" буду считаны из файла data.properties из    ║\r\n" +
							" ║              параметра task4. Нажмите Enter для загрузки данных.           ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}


}
