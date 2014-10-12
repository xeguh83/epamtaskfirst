package ru.epam.task1.string;

import java.util.Scanner;

import ru.epam.task1.gui.Task;

public class MoreLessThanMedian extends Task {
	private String incomingString = "Мама мыла раму. Мама. Мыла. Раму. Мама мыла раму вчера. Вчера.Мама будет мыть раму завтра";
	
	public MoreLessThanMedian(String shortName) {
		super(shortName);
	}

	@Override
	protected void doLogic() {
		// TODO Auto-generated method stub
		String[] strings = incomingString.split("\\.");
		int mediana = 0;
		for (String string : strings) {
			mediana += string.length();
		}
		mediana = (int) (mediana / strings.length);
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
							" ║  Строки разделённые знаком \".\" буду считаны из файла data.properties из    ║\r\n" +
							" ║              параметра task3. Нажмите Enter для загрузки данных.           ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
