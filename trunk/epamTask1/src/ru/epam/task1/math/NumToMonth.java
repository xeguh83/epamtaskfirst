package ru.epam.task1.math;

import java.text.DateFormatSymbols;
import java.util.Scanner;

import ru.epam.task1.gui.Task;

/**<p>Вывод месяца по числу</p>
 * Класс выводит наименование месяца в локали по умолчанию по 
 * числу введенному пользователем, при этом проверяя корректность ввода
 * @author Туркин А.К.
 *
 */
public class NumToMonth extends Task {
	private int monthNumber;
	

	/**Конструктор для передачи короткого наименования задачи для постройки таблицы задач 
	 * @param shortName короткое наименование задачи
	 */
	public NumToMonth(String shortName) {
		super(shortName);
	}

	/**<p>Отрисовка задания</p> 
	 * <p>Метод выводит в консоль наименование задачи, необходимые пустые строки 
	 * для корректного отображения окна и ждет ввода пользователем номера месяца, 
	 * при этом проверяя правильность ввода.</p>
	 * <p>При вводе номера месяца от 1 до 12 выводится наименование месяца в 
	 * локали по умолчанию, при вводе 0 происходит выход в меню, а при вводе 
	 * остальных значений происходит перерисовка окна</p> 
	 */
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

	/**<p>Считывание числа из консоли</p>
	 * Метод ожидает на ввод целое число и нажатие Enter. При ошибочном вводе
	 * возращяется значение -1
	 * @return целое число введенное с клавиатуры
	 */
	private int getNumFromKeyboard() {
		Scanner in = new Scanner(System.in);
		try {
			String number = in.nextLine();
			return Integer.parseInt(number); 
		} catch (Exception e) {
			return -1;
		}
	}


	/**
	 * Метод выводит на экран наименование месяца из локали по умолчанию по значению
	 * поля <code>monthNumber</code>
	 */
	@Override
	protected void doLogic() {
		DateFormatSymbols date = new DateFormatSymbols();
		String[] months = date.getMonths();
		System.out.println(" Месяц номер " + monthNumber + " это " + months[monthNumber - 1]);
	}

	/* (non-Javadoc)
	 * @see ru.epam.task1.gui.Task#drawTitle()
	 */
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
