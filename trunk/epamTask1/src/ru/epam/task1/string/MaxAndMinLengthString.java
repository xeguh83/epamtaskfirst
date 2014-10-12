package ru.epam.task1.string;

import java.util.Scanner;

import ru.epam.task1.gui.Task;

public class MaxAndMinLengthString extends Task {
	private String incomingString = "Мама мыла раму. Мама мыла раму вчера. Мама будет мыть раму завтра";
	
	public MaxAndMinLengthString(String shortName) {
		super(shortName);
	}

	@Override
	protected void doLogic() {
		// TODO Auto-generated method stub
		String[] stringsForWork = incomingString.split("\\.");
		System.out.println("Загружены следующие строки:");
		MaxAndMinString inner = new MaxAndMinString(stringsForWork);
		System.out.println("Строка с максимальной длиной (" + stringsForWork[inner.getMaxStringIndex()].length() + "): " 
				+ stringsForWork[inner.getMaxStringIndex()]);
		System.out.println("Строка с минимальной длиной: (" + stringsForWork[inner.getMinStringIndex()].length() + "): "
				+ stringsForWork[inner.getMinStringIndex()]);
		System.out.println("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n");
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║     Задача №1. Ввести n строк с консоли, найти самую короткую и самую      ║\r\n" +
				 			" ║           длинную строки. Вывести найденные строки и их длину.             ║\r\n" +
		 					" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
		 					" ║  Строки разделённые знаком \".\" буду считаны из файла data.properties из    ║\r\n" +
		 					" ║              параметра task1. Нажмите Enter для загрузки данных.           ║\r\n" +
		 					" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}
	
	private class MaxAndMinString {
		private int maxStringIndex;
		private int minStringIndex;
		
		public MaxAndMinString(String[] string) {
			int maxStr = Integer.MIN_VALUE;
			int minStr = Integer.MAX_VALUE;
			for (int i = 0; i < string.length; i++) {
				if (string[i].length() > maxStr) {
					maxStr = string[i].length();
					maxStringIndex = i;
				}
				if (string[i].length() < minStr) {
					minStr = string[i].length();
					minStringIndex = i;
				}
			}
		}

		public int getMaxStringIndex() {
			return maxStringIndex;
		}

		public int getMinStringIndex() {
			return minStringIndex;
		}
		
		
	}
}
