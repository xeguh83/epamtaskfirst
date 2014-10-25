package ru.epam.task1.matrix;

import ru.epam.task1.gui.Task;

public class MatrixMainDiagFill extends Task {
	private final double[][] matrixData; 

	public MatrixMainDiagFill(String shortName, String[] incomingStrings) {
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
		double[] mainDiag = new double[matrixData.length]; 
		for (int i = 0; i < mainDiag.length; i++) {
			swapFoundWithDiag(getMaxElemExceptMainDiag(i), i);
		}
	}


	private void swapFoundWithDiag(MatrixDoubleElement element, int i) {
		double tempValue = matrixData[i][i];
		matrixData[i][i] = element.getValue();
		matrixData[element.getRowNumber()][element.getColumnNumber()] = tempValue;
	}

	private MatrixDoubleElement getMaxElemExceptMainDiag(int settedDiagElements) {
		MatrixDoubleElement element = new MatrixDoubleElement(Double.MIN_VALUE, 0, 0);
		for (int i = 0; i < matrixData.length; i++) {
			for (int j = 0; j < matrixData[i].length; j++) {
				if ((i != j) || (i > (settedDiagElements - 1))) {					
					if (element.getValue() < matrixData[i][j]) {
						element.setValue(matrixData[i][j]);
						element.setRowNumber(i);
						element.setColumnNumber(j);
					}
				}
			}
		}
		return element;
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║  Задача №20. Путем перестановки элементов квадратной вещественной матрицы  ║\r\n" +
				 			" ║добиться того, чтобы ее главная диагональ состояла из максимальных элементов║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║   Строки матрицы будут считаны построчно из файла task20.txt, а столбцы    ║\r\n" +
				 			" ║         будут разделены пробелами. Нажмите Enter для продолжения           ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}
	
	private class MatrixDoubleElement {
		private double value;
		private int rowNumber;
		private int columnNumber;
		
		public MatrixDoubleElement(double value, int rowNumber, int columnNumber) {
			this.value = value;
			this.rowNumber = rowNumber;
			this.columnNumber = columnNumber;
		}
		public double getValue() {
			return value;
		}
		public void setValue(double value) {
			this.value = value;
		}
		public int getRowNumber() {
			return rowNumber;
		}
		public void setRowNumber(int rowNumber) {
			this.rowNumber = rowNumber;
		}
		public int getColumnNumber() {
			return columnNumber;
		}
		public void setColumnNumber(int columnNumber) {
			this.columnNumber = columnNumber;
		}
		
	}
}
