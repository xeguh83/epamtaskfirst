package ru.epam.task1.useswitch;

import java.util.ArrayList;
import java.util.Scanner;

import ru.epam.task1.gui.Task;

public class SwitchSecondInterval extends Task {
	int k;

	public SwitchSecondInterval(String shortName) {
		super(shortName);
	}

	@Override
	public void drawTask() {
		Scanner in = new Scanner(System.in);
		while (true) {
			drawTitle();
			Task.printEmptyLines(15);
			System.out.print("Введите целое число k и нажмите Enter: ");
			try {
				String num = in.nextLine();
				k = Integer.parseInt(num);
				drawTitle();
				doLogic();
				pressAnyKeyForMenu();
				break;
			} catch (Exception e) {
				System.out.println(e);
				in.reset();
			}
			
		}
	}

	@Override
	protected void doLogic() {
		ArrayList<String> resInterval = new ArrayList<String>();
		if (k <= 5) {
			resInterval.add("(-10k, 0]");
		} 
		if (k >= 0 && k <= 10) {
			resInterval.add("[0, 10]");
		} 
		if (k >= 5 && k <= 10) {
			resInterval.add("[5, 15]");
		} 
		if (k >= 10){
			resInterval.add("[10, 10k]");
		}
		printResult(resInterval);
	}

	private void printResult(ArrayList<String> resInterval) {
		for (String string : resInterval) {
			switch (string) {
			case "(-10k, 0]":
				System.out.println("Целое число k = " + k + " лежит в интервале от -10k исключая до 0 включая границу");
				break;
			case "[0, 10]":
				System.out.println("Целое число k = " + k + " лежит в интервале от 0 включая до 10 включая границу");
				break;
			case "[5, 15]":
				System.out.println("Целое число k = " + k + " лежит в интервале от 5 включая до 10 включая границу");
				break;
			case "[10, 10k]":
				System.out.println("Целое число k = " + k + " лежит в интервале от 10 включая до 10k включая границу");
			default:
				break;
			}
			
		}
		Task.printEmptyLines(14);
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║     Задача №11. Используя оператор switch вывести на экран сообщение о     ║\r\n" +
				 			" ║   принадлежности числа k интервалам (-10k, 5], [0, 10], [5, 15], [10, 10k] ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║           Загрузка из файлов не используется. Введите целое число k        ║\r\n" +
				 			" ║                         и нажмите Enter для продолжения                    ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");		
	}

}
