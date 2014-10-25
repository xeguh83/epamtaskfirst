package ru.epam.task1.matrix;

import ru.epam.task1.gui.Task;

public class MatrixCharacterLowering extends Task {
	private final double[][] matrixData; 

	public MatrixCharacterLowering(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
		matrixData = getMatrixFromStringArray();
	}

	@Override
	public void drawTask() {
		drawTitle();
		Task.printEmptyLines(15);
		if (matrixData.length == 0) {
			System.out.print(" Ошибка загрузки данных из файла...");
			Task.pressAnyKeyForMenu();
			return;
		}
		System.out.print(" Данные матрицы загружены из файла.");
		pressAnyKey();
		drawTitle();
		doLogic();
		System.out.println("Отсортированная матрица:");
		printMatrix(matrixData);
		Task.printEmptyLines(13 - matrixData.length);
		Task.pressAnyKeyForMenu();
	}

	@Override
	protected void doLogic() {
		for (int i = 0; i < matrixData.length - 1; i++) {
			for (int j = 0; j < matrixData.length - i - 1; j++) {
				if (columnCharacteristic(getMatrixColumn(j)) < columnCharacteristic(getMatrixColumn(j+1))) {
					swapColumns(j, j+1);
				}				
			}
		}
	}

	private void swapColumns(int column, int nextColumn) {
		double[] tempColumn = getMatrixColumn(column);
		setMatrixColumn(column, getMatrixColumn(nextColumn));
		setMatrixColumn(nextColumn, tempColumn);
	}

	private void setMatrixColumn(int column, double[] matrixColumn) {
		for (int i = 0; i < matrixColumn.length; i++) {
			matrixData[i][column] = matrixColumn[i];
		}
	}

	private double columnCharacteristic(double[] matrixColumn) {
		double absSum = 0;
		for (double element : matrixColumn) {
			absSum += Math.abs(element);
		}
		return absSum;
	}

	private double[] getMatrixColumn(int columnNumber) {
		double[] column = new double[matrixData.length];
		for (int i = 0; i < column.length; i++) {
			column[i] = matrixData[i][columnNumber];
		}
		return column;
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║Задача №19. Переставлять столбцы матрицы так чтобы значения их характеристик║\r\n" +
				 			" ║    убывали. (Характеристикой столбца - это сумма модулей его элементов)    ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║   Строки матрицы будут считаны построчно из файла task19.txt, а столбцы    ║\r\n" +
				 			" ║         будут разделены пробелами. Нажмите Enter для продолжения           ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
