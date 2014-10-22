package ru.epam.task1.math;

import java.text.DateFormatSymbols;
import java.util.Scanner;

import ru.epam.task1.gui.Task;

public class NumToMonth extends Task {
	private int monthNumber;
	

	public NumToMonth(String shortName) {
		super(shortName);
	}

	@Override
	public void drawTask() {
		int monthNumber = 0;
		while (true) {
			drawTitle();
			Task.printEmptyLines(15);
			System.out.print(" Введите номер месяца (0 для выхода в меню): ");
			monthNumber = getNumFromKeyboard();
			if (monthNumber < 0 || monthNumber > 12) {
				continue;
			} else if (monthNumber == 0) {
				break;
			} else {
				drawTitle();
				this.monthNumber = monthNumber;
				doLogic();
				Task.printEmptyLines(14);
				pressAnyKey();
			}
			
		}
	}

	private int getNumFromKeyboard() {
		Scanner in = new Scanner(System.in);
		try {
			String number = in.nextLine();
			return Integer.parseInt(number); 
		} catch (Exception e) {
			return -1;
		}
	}


	@Override
	protected void doLogic() {
		DateFormatSymbols date = new DateFormatSymbols();
		String[] months = date.getMonths();
		System.out.println(" Месяц номер " + monthNumber + " это " + months[monthNumber - 1]);
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║   Задача №14. Ввести число от 1 до 12. Вывести на консоль название месяца, ║\r\n" +
							" ║                         соответствующего данному числу.                    ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║             Загрузка из файлов не происходит. Введите номер месяца с       ║\r\n" +
							" ║               клавиатуры (1 - 12) и нажмите Enter для продолжения          ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
		
	}

}
