package ru.epam.task1.matrix;

import java.util.ArrayList;

import ru.epam.task1.gui.Task;

public class MatrixBigestLocalMax extends Task {
	private double highestLocalMax = Double.MIN_VALUE;
	private final double[][] matrixData; 

	public MatrixBigestLocalMax(String shortName, String[] incomingStrings) {
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
		if (highestLocalMax > Double.MIN_VALUE) {
			System.out.println(" Наибольший локальный максимум: " + String.format("%#10.2f", highestLocalMax));			
		} else {
			System.out.println(" Локальные максимумы не найдены.");
		}
		Task.printEmptyLines(14);
		Task.pressAnyKeyForMenu();
		highestLocalMax = Double.MIN_VALUE;
	}

	@Override
	protected void doLogic() {
		for (int i = 0; i < matrixData.length; i++) {
			for (int j = 0; j < matrixData[i].length; j++) {
				if (isLocalMax(i, j)) {
					if (highestLocalMax < matrixData[i][j]) {
						highestLocalMax = matrixData[i][j];
					}
				}
			}
		}
	}

	private boolean isLocalMax(int rowNumber, int columnNumber) {
		ArrayList<Double> neighbors = getListOfNeighbors(rowNumber, columnNumber);
		double checkingElementValue = matrixData[rowNumber][columnNumber];
		for (Double element : neighbors) {
			if (checkingElementValue <= element) {
				return false;
			}
		}
		return true;
	}

	private ArrayList<Double> getListOfNeighbors(int rowNumber, int columnNumber) {
		ArrayList<Double> arrayOfNeighbors = new ArrayList<Double>();
		if (rowNumber > 0) {
			arrayOfNeighbors.add(matrixData[rowNumber - 1][columnNumber]);
		} 
		if (rowNumber < (matrixData.length - 1)) {
			arrayOfNeighbors.add(matrixData[rowNumber + 1][columnNumber]);
		}
		if (columnNumber > 0) {
			arrayOfNeighbors.add(matrixData[rowNumber][columnNumber - 1]);
		}
		if (columnNumber < (matrixData.length - 1)) {
			arrayOfNeighbors.add(matrixData[rowNumber][columnNumber + 1]);
		}
		return arrayOfNeighbors;
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║  Задача №18. Найти число локальных максимумов. Элемент матрицы называется  ║\r\n" +
				 			" ║      локальным максимумом, если он строго больше всех своих соседей        ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║   Строки матрицы будут считаны построчно из файла task18.txt, а столбцы    ║\r\n" +
				 			" ║         будут разделены пробелами. Нажмите Enter для продолжения           ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
