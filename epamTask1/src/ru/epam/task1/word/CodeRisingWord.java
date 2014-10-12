package ru.epam.task1.word;

import ru.epam.task1.gui.Task;

public class CodeRisingWord extends Task {
	private String incomingString = "Мама мыла раму mom washed bcd window in her home";
	
	public CodeRisingWord(String shortName) {
		super(shortName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doLogic() {
		// TODO Auto-generated method stub
		String[] word = incomingString.split("\\s");
		String resultWord = "";
		for (String string : word) {
			if (neededWord(string)) {
				resultWord = string;
				break;
			}
		}
		if (resultWord.length() == 0) {
			System.out.println("Искомые слова не найдены");
		} else {
			System.out.println("Первое слово в котором символы идут в строгом порядке это:" + resultWord);			
		}
		System.out.println("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n");
	}

	private boolean neededWord(String string) {
		// TODO Auto-generated method stub
		if (string.length() <= 1) {
			return false;
		}
		char[] simbols = string.toCharArray();
		for (int i = 0; i < simbols.length - 1; i++) {
			if (!simbolNextByAsc2Code(simbols[i], simbols[i + 1])) {
				return false;
			}
		}
		return true;
	}

	private boolean simbolNextByAsc2Code(char c, char d) {
		// TODO Auto-generated method stub
		if ((int)d - (int)c == 1) {
			return true;
		}
		return false;
	}

	@Override
	protected void drawTitle() {
		// TODO Auto-generated method stub
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║     Задача №6. Найти слово, символы в котором идут в строгом порядке       ║\r\n" +
							" ║    возрастания кодов. Если таких слов несколько - найти первое из них.     ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║   Слова разделённые знаком \" \" буду считаны из файла data.properties из    ║\r\n" +
							" ║              параметра task5. Нажмите Enter для загрузки данных.           ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
