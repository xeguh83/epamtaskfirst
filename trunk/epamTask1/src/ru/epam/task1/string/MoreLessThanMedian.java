package ru.epam.task1.string;

import ru.epam.task1.gui.Task;

public class MoreLessThanMedian extends Task {
	
	public MoreLessThanMedian(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}


	@Override
	protected void doLogic() {
		// TODO Auto-generated method stub
		String[] strings = getStrings();
		int mediana = 0;
		for (String string : strings) {
			mediana += string.length();
		}
		mediana = mediana / strings.length;
		printResult(strings, mediana);
	}

	private void printResult(String[] strings, int mediana) {
		// TODO Auto-generated method stub
		System.out.println("Строки с длинами меньше или равными средней (" + mediana + "):");
		for (String string : strings) {
			if (string.length() <= mediana) System.out.println(string + "("+ string.length() + ")");
		}
		System.out.println("Строки с длинами больше средней (" + mediana + "):");
		for (String string : strings) {
			if (string.length() > mediana) System.out.println(string + "("+ string.length() + ")");
		}
	}

	@Override
	protected void drawTitle() {
		// TODO Auto-generated method stub
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║     Задача №3. Ввести n строк с консоли. Вывести на консоль те строки,     ║\r\n" +
							" ║             длина которых меньше (больше) средней, а также длину.          ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║Строки будут считаны из файла task3.txt (учитывается только перевод строки).║\r\n" +
				 			" ║                      Для продолжения нажмите Enter.                        ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
