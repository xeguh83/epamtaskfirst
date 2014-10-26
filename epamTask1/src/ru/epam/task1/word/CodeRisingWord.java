package ru.epam.task1.word;

import ru.epam.task1.gui.Task;

/**
 * Класс находит в выводит в консоль первое слово у которого коды символов
 * строго возрастают по таблице символов
 * @author Туркин А.К.
 */
public class CodeRisingWord extends Task {
	
	/**
	 * Конструктор для передачи короткого наименования задачи для постройки таблицы задач и 
	 * массива строк содержащего начальные условия задачи
	 * @param shortName короткое наименование задачи
	 * @param incomingStrings массив строк с начальным условием задачи
	 */
	public CodeRisingWord(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	/**
	 * Метод формирует массив слов, переданных из файла с помощью метода <code>getWordsFromStringArray</code> 
	 * и выводит в консоль первое слово у которого коды символов строго возрастают по таблице символов 
	 * @see #getWordsFromStringArray() 
	 */
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

	/**
	 * Метод определяет возрастают ли символы слова в строгом соответствии с таблицей символов 
	 * @param string слово для анализа
	 * @return <code>true</code> если символы слова в строгом соответствии с таблицей символов, иначе возвращает <code>false</code>
	 */
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

	/**
	 * Метод определяет является ли символ <code>d</code> следущим после символа <code>с</code> в таблице символов
	 * @param c первый символ
	 * @param d второй символ
	 * @return <code>true</code> если символ <code>d</code> следующий после символа <code>с</code>, иначе возвращает <code>false</code>
	 */
	private boolean simbolNextByAsc2Code(char c, char d) {
		if (d - c == 1) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see ru.epam.task1.gui.Task#drawTitle()
	 */
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
