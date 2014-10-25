package ru.epam.task1.matrix;

import java.util.ArrayList;

import ru.epam.task1.gui.Task;

public class MatrixLocalMin extends Task {
	private int countLocalMin = 0;
	private final double[][] matrixData; 

	public MatrixLocalMin(String shortName, String[] incomingStrings) {
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
			countLocalMin = 0;
			return;
		}
		System.out.print(" Данные матрицы загружены из файла.");
		pressAnyKey();
		drawTitle();
		doLogic();
		System.out.println(" Количество локальных минимумов: " + countLocalMin);
		Task.printEmptyLines(14);
		Task.pressAnyKeyForMenu();
		countLocalMin = 0;
	}

	@Override
	protected void doLogic() {
		for (int i = 0; i < matrixData.length; i++) {
			for (int j = 0; j < matrixData[i].length; j++) {
				if (isLocalMin(i, j)) {
					countLocalMin++;
				}
			}
		}
	}

	private boolean isLocalMin(int rowNumber, int columnNumber) {
		ArrayList<Double> neighbors = getListOfNeighbors(rowNumber, columnNumber);
		double checkingElementValue = matrixData[rowNumber][columnNumber];
		for (Double element : neighbors) {
			if (checkingElementValue >= element) {
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
				 			" ║  Задача №17. Найти число локальных минимумов. Элемент матрицы называется   ║\r\n" +
				 			" ║       локальным минимумом, если он строго меньше всех своих соседей        ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║   Строки матрицы будут считаны построчно из файла task17.txt, а столбцы    ║\r\n" +
				 			" ║         будут разделены пробелами. Нажмите Enter для продолжения           ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
