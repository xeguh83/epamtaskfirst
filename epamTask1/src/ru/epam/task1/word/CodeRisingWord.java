package ru.epam.task1.word;

import ru.epam.task1.gui.Task;

public class CodeRisingWord extends Task {
	
	public CodeRisingWord(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	@Override
	protected void doLogic() {
		String[] word = getWordsFromStringArray();
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
		Task.printEmptyLines(14);
	}

	private boolean neededWord(String string) {
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
		if (d - c == 1) {
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
				 			" ║              Слова разделённые пробелами считаны из файла task6.txt        ║\r\n" +
							" ║                          Нажмите Enter для продолжения                     ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
