package ru.epam.task2.word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import ru.epam.task2.gui.Task;

public class FreqDifWords extends Task {

	public FreqDifWords(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	@Override
	protected void doLogic() {
		HashMap<String, Integer> map = arrayToMapOfKeysAndCounts(getWordsFromStringArray());
		drawTitle();
		resultsSaveToFile(map);
		System.out.println(" Коллекция уникальных слов и частоты их встречаемости записана в файл.");
		printEmptyLines(14);
	}

	private void resultsSaveToFile(HashMap<String, Integer> map) {
		ArrayList<String> wordsAndFreq = new ArrayList<String>();
		for (Entry<String, Integer> entry : map.entrySet()) {
			wordsAndFreq.add(entry.getKey() + "[" + entry.getValue() + "]");
		}
		writeStringsToFile("task11output.txt", wordsAndFreq.toArray(new String[wordsAndFreq.size()]));
	}

	private HashMap<String, Integer> arrayToMapOfKeysAndCounts(String[] wordsArray) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String word : wordsArray) {
			if (map.containsKey(word)) {
				map.put(word, map.get(word) + 1);
			} else {
				map.put(word, 1);
			}
		}
		return map;
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║Задача №11. Выделить все различные слова. Для каждого слова подсчитать час- ║\r\n" + 
				 			" ║   тоту его встречаемости. Учитывать регистр. Использовать класс HashMap    ║\r\n" +
		 					" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
		 					" ║               Общая строка склеивается из строк файла task11.txt           ║\r\n" +
		 					" ║       Различные слова с частотой будут записаны в файл task11output.txt    ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
	}

}
