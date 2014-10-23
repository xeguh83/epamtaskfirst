package ru.epam.task1.matrix;

//import org.apache.commons.math3.linear.MatrixUtils;
//import org.apache.commons.math3.linear.RealMatrix;

import ru.epam.task1.gui.Task;

public class MatrixSedDots extends Task {
	private int countSaddleDots = 0;
	private final double[][] matrixData; 

	public MatrixSedDots(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
		matrixData = getMatrixFromStringArray();
	}

	@Override
	public void drawTask() {
//		RealMatrix m = MatrixUtils.createRealMatrix(matrixData);
		drawTitle();
		Task.printEmptyLines(15);
		if (matrixData.length == 0) {
			System.out.print(" Ошибка загрузки данных из файла...");
			Task.pressAnyKeyForMenu();
			countSaddleDots = 0;
			return;
		}
		System.out.print(" Данные матрицы загружены из файла.");
		pressAnyKey();
		drawTitle();
		doLogic();
		if (countSaddleDots == 0) {
			System.out.println(" Седловые точки не найдены");
		} else {
			System.out.println(" Количество седловых точек: " + countSaddleDots);
		}
		Task.printEmptyLines(14);
		Task.pressAnyKeyForMenu();
		countSaddleDots = 0;
	}

	@Override
	protected void doLogic() {
		for (int i = 0; i < matrixData.length; i++) {
			for (int j = 0; j < matrixData[i].length; j++) {
				if (isSaddleDot(i, j)) {
					countSaddleDots++;
				}
			}
		}
	}

	private boolean isSaddleDot(int i, int j) {
		if (isMinInRow(i, j) && isMaxInColumn(i, j)) {
			return true;
		}
		return false;
	}

	private boolean isMaxInColumn(int rowNumber, int columnNumber) {
		for (int i = 0; i < matrixData.length; i++) {
			if (new Double(matrixData[rowNumber][columnNumber]).compareTo(matrixData[i][columnNumber]) < 0) {
				return false;
			}
		}
		return true;
	}

	private boolean isMinInRow(int rowNumber, int columnNumber) {
		for (int i = 0; i < matrixData[rowNumber].length; i++) {
			if (new Double(matrixData[rowNumber][columnNumber]).compareTo(matrixData[rowNumber][i]) > 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║    Задача №15. Найти количество всех седловых точек матрицы. Точка Aij -   ║\r\n" +
							" ║  седловая, если её значение минимально в строке и максимально в столбце    ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║   Строки матрицы будут считаны построчно из файла task15.txt, а столбцы    ║\r\n" +
							" ║         будут разделены пробелами. Нажмите Enter для продолжения           ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
		
	}

}
