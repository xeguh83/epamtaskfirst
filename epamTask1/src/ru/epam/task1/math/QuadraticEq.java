package ru.epam.task1.math;

import java.util.Scanner;

import ru.epam.task1.gui.Task;

/**
 * <p>Решение квадратных уравнений</p>
 * Класс предназначен для решения квадратных уравнений с любыми числами, 
 * в том числе и комплексными
 * @author Туркин А.К.
 *
 */
public class QuadraticEq extends Task{
	private double param1;
	private double param2;
	private double param3;
	private String equation = "(0.0)X^2 + (0.0)X + (0.0)";

	/**
	 * Конструктор передает короткое наименованиеа задания для построения 
	 * таблицы заданий и обнуляет поля <code>param1, param2, param3</code> 
	 * @param shortName короткое наименование задания
	 */
	public QuadraticEq(String shortName) {
		super(shortName);
		param1 = 0;
		param2 = 0;
		param3 = 0;
	}

	/**
	 * Метод выводит в консоль текст задания и составленное уравнение 
	 * (по-умолчанию, коэффициенты уравнения равны 0). Метод последовательно 
	 * ожидает на вход ввод вещественного числа (в том числе отрицательного и 
	 * дробного). При некорректном вводе запрос повторяется. После корректного 
	 * ввода всех параметров управление передаётся методу <code>doLogic</code>
	 */
	@Override
	public void drawTask() {
		drawTaskAndGetParam("x^2");
		drawTaskAndGetParam("x");
		drawTaskAndGetParam("1");
		drawTitle();
		System.out.println("Уравнение: " + equation + "= 0");
		doLogic();
		Task.printEmptyLines(10);
		Task.pressAnyKeyForMenu();
	}
	
	/**<p>Вывод задания в консоль и ввод очередного параметра уравнения</p>
	 * Метод отрисовывает панель с текстом задания и ожидает на ввод вещественное 
	 * число (в том числе отрицательное или дробное) для коэффициента при указанном
	 * члене уравнения. При некорректном вводе запрос повторяется с перерисовкой 
	 * консольного окна
	 * @param string строкое представление члена квадратного уравнения
	 */
	private void drawTaskAndGetParam(String string) {
		while (true) {
			drawTitle();
			System.out.println("Уравнение: " + equation + "= 0");
			Task.printEmptyLines(14);
			System.out.print("Введите коэффициент при " + string + ": ");
			if (!setParam(string)){
				continue;
			}
			updateEq();
			break;
		}
		
	}

	/**
	 * Метод обновляет поле <code>equation</code> (строковое представление уравнения) в 
	 * соответствии с актуальными значениями полей <code>param1, param2, param3</code> 
	 */
	private void updateEq() { 
		equation = "(" + param1 + ")X^2 + (" + param2 + ")X + (" + param3 + ")";
	}

	/**
	 * <p>Обновление параметров уравнения</p>
	 * Метод ожидает на ввод вещественное число (в том числе отрицательное или дробное) 
	 * как коэффициент при указанном строковым представлением члене квадратного уравнения.
	 * При корректном парсинге введеного значения обновляется поле коэффициента уравнения
	 * в соответствии с указанным членом уравнения (от члена при старшей степени до 
	 * свободного члена) и возвращается значение <code>true</code>. При некорректном вводе 
	 * возвращается значение <code>false</code>
	 * @param string строковое представление члена квадратного уравнения
	 * @return <code>true</code> при корректном вводе коэффициента и <code>false</code> при некорректном
	 */
	private boolean setParam(String string) {
		Scanner in = new Scanner(System.in);
		double param = 0;
		try {
			String num = in.nextLine();
			param = Double.parseDouble(num);
			if (Double.isNaN(param)) {
				throw new NumberFormatException("С числами типа NaN вычисления не имеют смысла");
			}
			if (string == "x^2") {
				param1 = param;
				return true;
			} else if (string == "x") {
				param2 = param;
				return true;
			} else {
				param3 = param;
				return true;
			}
		} catch (Exception e) {
			System.out.println("Неверный формат! Введите вещественное число.");
			return false;
		}
	}

	/**
	 * <p>Вычисление корней квадратного уравнения</p>
	 * Метод вычисляет корни квадратного уравнения. При дискриминанте >= 0 корни 
	 * передаются в метод вывода в консоль вещественных корней 
	 * <code>printReRoots(root1, root2)</code>. В случае отрицательного дискриминанта,
	 * вещественная и мнимая части корня считаются отдельно и передаются методу вывода
	 * в консоль комплексный корней <code>printImRoots(rootRe, rootIm)</code>
	 */
	@Override
	protected void doLogic() {
		if ((param1 == 0) && (param2 == 0)) {
			if (param3 == 0) {
				System.out.println("Уравнение имеет следующее решение: X - любое число");
				Task.printEmptyLines(2);
				return;
			} else {
				System.out.println("Уравнение введено неверно и не имеет решений");
				Task.printEmptyLines(2);
				return;
			}
		}
		double discriminant = Math.pow(param2, 2) - (4 * param1 * param3);
		if (discriminant >= 0) {
			double root1 = ( ((-1)*param2) + Math.sqrt(discriminant) ) / (2 * param1);
			double root2 = ( ((-1)*param2) - Math.sqrt(discriminant) ) / (2 * param1);
			printReRoots(root1, root2);
		} else {
			double rootRe = ((-1)*param2) / (2 * param1);
			double rootIm = (Math.sqrt((-1)*discriminant)) / (2 * param1);
			printImRoots(rootRe, rootIm);
		}
	}

	/**<p>Вывод в консоль комплексный корней уравнения</p>
	 * Метод выводит в консоль систему из комплексных корней, при этом мнимой части корня
	 * дописывается символ <code>j</code> 
	 * @param rootRe вещественная часть комплексного корня
	 * @param rootIm мнимая часть комплексного корня
	 */
	private void printImRoots(double rootRe, double rootIm) {
		System.out.println("Уравнение имеет комплексные корни:");
		System.out.println("[ X1 = (" + String.format("%#6.2f", rootRe) + ") + j(" + String.format("%#6.2f", rootIm) + ")");
		System.out.println("[");
		System.out.println("[ X2 = (" + String.format("%#6.2f", rootRe) + ") - j(" + String.format("%#6.2f", rootIm) + ")");
	}

	/**<p>Вывод в консоль вещественных корней уравнения</p>
	 * Метод выводит в консоль систему из вещественных корней квадратного уравнения
	 * @param root1 первый вещественный корень
	 * @param root2 второй вещественный корень
	 */
	private void printReRoots(double root1, double root2) {
		System.out.println("Уравнение имеет вещественные корни:");
		System.out.println("[ X1 = " + String.format("%#6.2f", root1));
		System.out.println("[");
		System.out.println("[ X2 = " + String.format("%#6.2f", root2));
	}

	/* (non-Javadoc)
	 * @see ru.epam.task1.gui.Task#drawTitle()
	 */
	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║ Задача №13. Написать программу, позволяющую корректно находить корни квад- ║\r\n" +
				 			" ║ ратного уравнения. Параметры уравнения должны задаваться с командной строки║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║         Загрузка из файлов не используется. Последовательно вводите        ║\r\n" +
				 			" ║           коэффициенты уравнения и нажимайте Enter для продолжения         ║\r\n" +
		 					" ╚════════════════════════════════════════════════════════════════════════════╝");		
	}

}
