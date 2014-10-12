package ru.epam.task1.word;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.epam.task1.gui.Task;

public class EngCharWord extends Task {
	private String incomingString = "Мама мыла раму mom washed window in her home";
		
	public EngCharWord(String shortName) {
		super(shortName);
	}


	@Override
	protected void doLogic() {
		// TODO Auto-generated method stub
		String[] word = incomingString.split("\\s");
		ArrayList<String> listOfEngWords = getAndPrintEngWords(word);
		System.out.print("Среди них слова с равным числом гласных и согласных: ");
		int count = 0;
		for (String string : listOfEngWords) {
			if (string.toLowerCase().replaceAll("[^aeiou]", "").length() == string.toLowerCase().replaceAll("[aeiou]", "").length()) {
				count++;
			}
		}
		System.out.println("(" + count + ")");
		System.out.println("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n");
		
	}

	private ArrayList<String> getAndPrintEngWords(String[] words) {
		// TODO Auto-generated method stub
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


	@Override
	protected void drawTitle() {
		// TODO Auto-generated method stub
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║Задача №5. Найти количество слов, содержащих только символы латинского алфа-║\r\n" +
							" ║вита, а среди них - количество слов с равным числом гласных и согласных букв║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║   Слова разделённые знаком \" \" буду считаны из файла data.properties из    ║\r\n" +
							" ║              параметра task5. Нажмите Enter для загрузки данных.           ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
