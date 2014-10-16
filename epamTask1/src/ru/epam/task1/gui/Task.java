package ru.epam.task1.gui;

import java.io.IOException;
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
			System.out.print("Введите любую строку для продолжения (0 для выхода в меню)");
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
				// TODO: handle exception
				System.out.println(e);
			}
		}
//		System.in.close();
	}
	
	protected abstract void doLogic();

	protected abstract void drawTitle();

	public void drawTask(String[] string) {
		//������� ������ ��� �������� ���������� ��������� ������
	}
	
	protected static void pressAnyKeyForMenu() throws IOException {
		System.out.print("Нажмите Enter для выхода в меню");
		try {
			Scanner in = new Scanner(System.in);
			in.nextLine();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
//		System.in.close();
	}
	
	public static void printEmptyLines(int countEmptyLines) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < countEmptyLines; i++) {
			sb.append("\r\n");
		}
		System.out.println(sb);
	}
	
	protected String[] getStrings() {
		// TODO Auto-generated method stub
		return incomingStrings;
	}
	
	protected String[] getWordFromStringArray(String[] stringArray) {
		String work = stringArray[0].split(regex)
	}

}
