package ru.epam.task1.matrix;

import ru.epam.task1.gui.Task;

/**
 * Класс для перестановки элементов матрицы так чтобы главная диагональ
 * матрицы состояла из максимальных элементов, при этом элемент (0, 0) был максимальным, 
 * элемент (1, 1) следующим по величине и т. д.
 * @author Туркин А.К.
 */
public class MatrixMainDiagFill extends Task {
	private final double[][] matrixData; 

	/**
	 * Конструктор передает сокращенное наименование задания для формирования таблицы 
	 * заданий, массив строк с начальными данными. Также конструктор формирует матрицу
	 * в виде двухмерного массива вещественных чисел из массива строк с помощью метода
	 * <code>getMatrixFromStringArray()</code>
	 * @param shortName сокращенное наименование задания
	 * @param incomingStrings массив строк с начальным условием задачи
	 */
	public MatrixMainDiagFill(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
		matrixData = getMatrixFromStringArray();
	}

	/**
	 * Метод выводит в консоль текст задания и проверяет содержимое матрицы. Если матрица
	 * пустая задание завершается и происходит выход в меню. Иначе происходит ожидание ввода
	 * любой строки для того чтобы дать возможность пользователю прочитать задание и вывод 
	 * результатов (перестановка элементов так, чтобы главная диагональ состояла из максимальных
	 * элементов) посчитанных методом <code>doLogic()</code>
	 */
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

	/**
	 * Метод формирует главную диагональ как массив вещественных чисел
	 * и меняет местами элементы матрицы так, чтобы главная диагональ состояла из 
	 * максимальных элементов  
	 */
	@Override
	protected void doLogic() {
		double[] mainDiag = new double[matrixData.length]; 
		for (int i = 0; i < mainDiag.length; i++) {
			swapFoundWithDiag(getMaxElemExceptMainDiag(i), i);
		}
	}


	/**
	 * Метод меняет местами элемент матрицы заданый объектом класса <code>MatrixDoubleElement</code> 
	 * и элемент главной диагонали заданый одной координатой
	 * @param element элемент матрицы из поля <code>matrixData</code> в виде объекта класса <code>MatrixDoubleElement</code>
	 * @param i координата главной диагонали матрицы из поля <code>matrixData</code>
	 */
	private void swapFoundWithDiag(MatrixDoubleElement element, int i) {
		double tempValue = matrixData[i][i];
		matrixData[i][i] = element.getValue();
		matrixData[element.getRowNumber()][element.getColumnNumber()] = tempValue;
	}

	/**
	 * Метод возвращает максимальный элемент матрицы в виде объекта класса <code>MatrixDoubleElement</code>, 
	 * исключая при этом установленные элементы главной диагонали, количество которых определяется 
	 * входящим параметром
	 * @param settedDiagElements количество установленных элементов главной диагонали матрицы из поля <code>matrixData</code>
	 * @return максимальный элемент матрицы в виде объекта класса <code>MatrixDoubleElement</code>, исключая при этом 
	 * уже установленные элементы главной диагонали
	 */
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

	/* (non-Javadoc)
	 * @see ru.epam.task1.gui.Task#drawTitle()
	 */
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
	
	/**
	 * Внутренний класс для передачи координат элемента матрицы и ее значения
	 * @author Туркин А.К.
	 */
	private class MatrixDoubleElement {
		private double value;
		private int rowNumber;
		private int columnNumber;
		
		/**
		 * Конструктор для инициализации значений координат элемента матрицы и ее значения
		 * @param value значение элемента матрицы
		 * @param rowNumber номер строки элемента матрицы
		 * @param columnNumber номер столбца элемента матрицы
		 */
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
