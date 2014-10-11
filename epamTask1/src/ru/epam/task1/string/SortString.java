package ru.epam.task1.string;

import java.util.Arrays;
import java.util.Scanner;

import ru.epam.task1.gui.Task;

public class SortString extends Task {
	private String incomingString = "Мама мыла раму. Мама. Мыла. Раму. Мама мыла раму вчера. Вчера.Мама будет мыть раму завтра";
	
	public SortString(String shortName) {
		super(shortName);
	}

	@Override
	protected void drawTitle() {
		// TODO Auto-generated method stub
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║     Задача №2. Ввести n строк с консоли. Упорядочить и вывести строки в    ║\r\n" +
				 			" ║              порядке возрастания (убывания) значений их длины              ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║  Строки разделённые знаком \".\" буду считаны из файла data.properties из    ║\r\n" +
							" ║              параметра task2. Нажмите Enter для загрузки данных.           ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

	@Override
	protected void doLogic() {
		// TODO Auto-generated method stub
		String[] strings = incomingString.split("\\.");
		ComparableString[] stringsForWork = new ComparableString[strings.length];
		for (int i = 0; i < strings.length; i++) {
			stringsForWork[i] = new ComparableString(strings[i]);
		}
		
		Arrays.sort(stringsForWork);
		printResults(stringsForWork);
	}
	
	private void printResults(ComparableString[] stringsForWork) {
		// TODO Auto-generated method stub
		drawTitle();
		System.out.println("Сортировка по увеличению длины строки:");
		for (ComparableString string : stringsForWork) {
			System.out.println(string);
		}
		System.out.println("Сортировка по уменьшению длины строки:");
		for (int i = stringsForWork.length - 1; i >= 0 ; i=i-1) {
			System.out.println(stringsForWork[i]);
		}
		System.out.print("Нажмите Enter для выхода в меню");
		try {
			Scanner in = new Scanner(System.in);
			in.nextLine();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}

	private class ComparableString implements Comparable<ComparableString> {
		private String string; 
		
		public ComparableString(String string) {
			this.string = string;
		}
		
		@Override
		public int compareTo(ComparableString other) {
			// TODO Auto-generated method stub
			return Integer.compare(this.string.length(), other.string.length());
		}

		@Override
		public String toString() {
			return string;
			
		}
		
	}

}
