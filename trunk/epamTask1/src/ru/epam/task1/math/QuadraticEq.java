package ru.epam.task1.math;

import java.util.Scanner;

import ru.epam.task1.gui.Task;

public class QuadraticEq extends Task{
	private double param1;
	private double param2;
	private double param3;
	private String equation = "(0.0)X^2 + (0.0)X + (0.0)";

	public QuadraticEq(String shortName) {
		super(shortName);
		param1 = 0;
		param2 = 0;
		param3 = 0;
	}

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

	private void updateEq() { 
		equation = "(" + param1 + ")X^2 + (" + param2 + ")X + (" + param3 + ")";
	}

	private boolean setParam(String string) {
		Scanner in = new Scanner(System.in);
		double param = 0;
		try {
			String num = in.nextLine();
			param = Double.parseDouble(num);
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

	@Override
	protected void doLogic() {
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

	private void printImRoots(double rootRe, double rootIm) {
		System.out.println("Уравнение имеет комплексные корни:");
		System.out.println("[ X1 = (" + String.format("%#6.2f", rootRe) + ") + j(" + String.format("%#6.2f", rootIm) + ")");
		System.out.println("[");
		System.out.println("[ X2 = (" + String.format("%#6.2f", rootRe) + ") - j(" + String.format("%#6.2f", rootIm) + ")");
	}

	private void printReRoots(double root1, double root2) {
		System.out.println("Уравнение имеет вещественные корни:");
		System.out.println("[ X1 = " + String.format("%#6.2f", root1));
		System.out.println("[");
		System.out.println("[ X2 = " + String.format("%#6.2f", root2));
	}

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
