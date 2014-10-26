package ru.epam.task1.useswitch;

import java.util.ArrayList;
import java.util.Scanner;

import ru.epam.task1.gui.Task;

/**
 * Класс вычисляет и выводит в консоль информацию о попадании введеного 
 * значения k в заданый интервал
 * @author Туркин А.К.
 * @since 1.7
 */
public class SwitchSecondInterval extends Task {
	int k;

	/**
	 * Конструктор для передачи короткого наименования задачи для постройки таблицы задач
	 * @param shortName короткое наименование задачи
	 */
	public SwitchSecondInterval(String shortName) {
		super(shortName);
	}

	/**
	 * Метод отрисовывает наименование и текст задания, а затем ожидает на ввод
	 * целого числа. В случае ввода целого числа, это число записывается в поле
	 * <code>k</code>, а затем вызывается метод <code>doLogic</code>. В случае
	 * некорректного ввода окно перерисовывается и повторяется запрос  
	 */
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

	/**
	 * Метод вычисляет попадание значение из поля <code>k</code> в интервалы из условия 
	 * задачи, а затем формирует список строковых интерпретаций найденных интервалов и 
	 * передает их в метод <code>printResult</code>
	 */
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

	/**
	 * Метод используя оператор <code>switch</code> выбирает фразы выводящиеся в консоль в 
	 * соответствии со строковыми интерпретациями найденных интервалов
	 * @param resInterval список строковых интерпретаций найденного интервала
	 * @since 1.7
	 */
	private void printResult(ArrayList<String> resInterval) {
		for (String string : resInterval) {
			switch (string) {
			case "(-10k, 0]":
				System.out.println("Целое число k = " + k + " лежит в интервале от -10|k| исключая до 0 включая границу");
				break;
			case "[0, 10]":
				System.out.println("Целое число k = " + k + " лежит в интервале от 0 включая до 10 включая границу");
				break;
			case "[5, 15]":
				System.out.println("Целое число k = " + k + " лежит в интервале от 5 включая до 10 включая границу");
				break;
			case "[10, 10k]":
				System.out.println("Целое число k = " + k + " лежит в интервале от 10 включая до 10|k| включая границу");
			default:
				break;
			}
			
		}
		Task.printEmptyLines(14);
	}

	/* (non-Javadoc)
	 * @see ru.epam.task1.gui.Task#drawTitle()
	 */
	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║     Задача №11. Используя оператор switch вывести на экран сообщение о     ║\r\n" +
				 			" ║принадлежности числа k интервалам (-10|k|, 5], [0, 10], [5, 15], [10, 10|k|]║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║           Загрузка из файлов не используется. Введите целое число k        ║\r\n" +
				 			" ║                         и нажмите Enter для продолжения                    ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");		
	}

}
