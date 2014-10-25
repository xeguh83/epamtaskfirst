package ru.epam.task1.useswitch;

import java.util.Scanner;

import ru.epam.task1.gui.Task;

public class SwitchFirstInterval extends Task {
	int k;

	public SwitchFirstInterval(String shortName) {
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
		String resInterval = "";
		if (k <= 0) {
			resInterval = "(-10k, 0]";
		} else if (k > 0 && k <= 5) {
			resInterval = "(0, 5]";
		} else if (k > 5 && k <= 10) {
			resInterval = "(5, 10]";
		} else {
			resInterval = "(10, 10k]";
		}
		printResult(resInterval);
	}

	private void printResult(String resInterval) {
		switch (resInterval) {
		case "(-10k, 0]":
			System.out.println("Целое число k = " + k + " лежит в интервале от -10|k| исключая до 0 включая границу");
			break;
		case "(0, 5]":
			System.out.println("Целое число k = " + k + " лежит в интервале от 0 исключая до 5 включая границу");
			break;
		case "(5, 10]":
			System.out.println("Целое число k = " + k + " лежит в интервале от 5 исключая до 10 включая границу");
			break;
		case "(10, 10k]":
			System.out.println("Целое число k = " + k + " лежит в интервале от 10 исключая до 10|k| включая границу");
		default:
			break;
		}
		Task.printEmptyLines(14);
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║     Задача №10. Используя оператор switch вывести на экран сообщение о     ║\r\n" +
				 			" ║ принадлежности числа k интервалам (-10|k|, 0], (0, 5], (5, 10], (10, 10|k|]║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║           Загрузка из файлов не используется. Введите целое число k        ║\r\n" +
				 			" ║                         и нажмите Enter для продолжения                    ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");		
	}

}
