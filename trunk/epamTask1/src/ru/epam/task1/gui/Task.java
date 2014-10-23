package ru.epam.task1.gui;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Task {
	
	private static int nextId = 1;
	
	private final int id;
	private final String shortName;
	private String[] incomingStrings;
	
	protected Task(String shortName) {
		id = nextId;
		nextId++;
		this.shortName = shortName;
	}
	
	protected Task(String shortName, String[] incomingStrings) {
		id = nextId;
		nextId++;
		this.shortName = shortName;
		this.incomingStrings = incomingStrings;
	}
	
	public int getId() {
		return id;
	}

	public String getShortName() {
		return shortName;
	}

	public void drawTask() {
		Scanner in = new Scanner(System.in);
		while (true) {
			drawTitle();
			Task.printEmptyLines(15);
			System.out.print(" Введите любую строку для продолжения (0 для выхода в меню)");
			try {
				String option = in.nextLine();
				if (option.equalsIgnoreCase("0")) {
					break;
				} else {
					drawTitle();
					doLogic();
					pressAnyKeyForMenu();
					break;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	protected abstract void doLogic();

	protected abstract void drawTitle();

	public void drawTask(String[] strings) {
		incomingStrings = strings;
		drawTask();
	}
	
	protected static void pressAnyKeyForMenu() {
		System.out.print(" Нажмите Enter для выхода в меню");
		try {
			Scanner in = new Scanner(System.in);
			in.nextLine();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	protected void pressAnyKey() {
		System.out.print(" Нажмите Enter для продолжения");
		try {
			Scanner in = new Scanner(System.in);
			in.nextLine();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void printEmptyLines(int countEmptyLines) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < countEmptyLines; i++) {
			sb.append("\r\n");
		}
		System.out.println(sb);
	}
	
	protected String[] getStrings() {
		return incomingStrings;
	}
	
	protected double[][] getMatrixFromStringArray() {
		ArrayList<Double> doublelist = new ArrayList<Double>();
		double[][] array = new double[incomingStrings.length][];
		try {
			for (int i = 0; i < array.length; i++) {
				String[] words = incomingStrings[i].split("\\s");
				array[i] = new double[words.length]; 
				for (int j = 0; j < array[i].length; j++) {
					array[i][j] = Double.parseDouble(words[j]);				
				}
			}			
			if (array.length > 1) {
				for (int i = 0; i < array.length - 1; i++) {
					if (array[i].length != array[i + 1].length) {
						return new double[0][0];
					}
				}
			}
		} catch (Exception e) {
			return new double[0][0];
		}
		return array;
	}
	
	protected String[] getWordsFromStringArray() {
		ArrayList<String> wordsList = new ArrayList<String>();
		for (String string : incomingStrings) {
			String[] stringOfWords = string.split("\\s");
			for (String word : stringOfWords) {
				wordsList.add(word);
			}
		}
		String[] wordsArray = new String[wordsList.size()];
		return wordsList.toArray(wordsArray);
	}

}
