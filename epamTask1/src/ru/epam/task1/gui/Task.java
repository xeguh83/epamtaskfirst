package ru.epam.task1.gui;

import java.util.Scanner;

public abstract class Task {
	
	private static int nextId = 1;
	
	private int id;
	private String shortName;
	
	protected Task(String shortName) {
		id = nextId;
		nextId++;
		this.shortName = shortName;
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
			System.out.println("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n");
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
		
	}
	
	protected abstract void doLogic();

	protected abstract void drawTitle();

	public void drawTask(String[] string) {
		//������� ������ ��� �������� ���������� ��������� ������
	}
	
	private static void pressAnyKeyForMenu() {
		System.out.print("Нажмите Enter для выхода в меню");
		try {
			Scanner in = new Scanner(System.in);
			in.nextLine();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

}
