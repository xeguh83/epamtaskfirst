package ru.epam.task1.matrix;

import ru.epam.task1.gui.Task;

public class MatrixBuildRisingRows extends Task {
	private double[][] matrixData; 

	public MatrixBuildRisingRows(String shortName, String[] incomingStrings) {
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
				if (rowSum(matrixData[j]) > rowSum(matrixData[j+1])) {
					swapRows(j, j+1);
				}				
			}
		}
	}

	private void swapRows(int row, int nextRow) {
		double[] tempRow = matrixData[row];
		matrixData[row] = matrixData[nextRow];
		matrixData[nextRow] = tempRow;
	}

	private double rowSum(double[] matrixRow) {
		double sum = 0;
		for (double element : matrixRow) {
			sum += element;
		}
		return sum;
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║    Задача №16. Перестроить матрицу, переставляя в ней строки так, чтобы    ║\r\n" +
				 			" ║             сумма элементов в строках полученной матрицы возрастала        ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║   Строки матрицы будут считаны построчно из файла task16.txt, а столбцы    ║\r\n" +
				 			" ║         будут разделены пробелами. Нажмите Enter для продолжения           ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}
	
}
