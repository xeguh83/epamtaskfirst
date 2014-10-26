package ru.epam.task1.matrix;

import ru.epam.task1.gui.Task;

/**
 * Класс для перестроения матрицы так чтобы значения характеристик 
 * (сумма модулей элементов) в её столбцах убывала
 * @author Туркин А.К.
 */
public class MatrixCharacterLowering extends Task {
	private final double[][] matrixData; 

	/**
	 * Конструктор передает сокращенное наименование задания для формирования таблицы 
	 * заданий, массив строк с начальными данными. Также конструктор формирует матрицу
	 * в виде двухмерного массива вещественных чисел из массива строк с помощью метода
	 * <code>getMatrixFromStringArray()</code>
	 * @param shortName сокращенное наименование задания
	 * @param incomingStrings массив строк с начальным условием задачи
	 */
	public MatrixCharacterLowering(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
		matrixData = getMatrixFromStringArray();
	}

	/**
	 * Метод выводит в консоль текст задания и проверяет содержимое матрицы. Если матрица
	 * пустая задание завершается и происходит выход в меню. Иначе происходит ожидание ввода
	 * любой строки для того чтобы дать возможность пользователю прочитать задание и вывод 
	 * результатов (сортировка столбцов матрицы по убыванию их характеристик) обработанных 
	 * методом <code>doLogic()</code> 
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
	 * Метод осуществляет пузырьковый метод сортировки столбцов матрицы
	 * по уменьшению их характеристик
	 */
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

	/**
	 * Метод меняет местами указанные столбцы матрицы
	 * @param column текущий столбец матрицы
	 * @param nextColumn следующий столбец матрицы
	 */
	private void swapColumns(int column, int nextColumn) {
		double[] tempColumn = getMatrixColumn(column);
		setMatrixColumn(column, getMatrixColumn(nextColumn));
		setMatrixColumn(nextColumn, tempColumn);
	}

	/**
	 * Метод записывает в указанный столбец матрицы из поля <code>matrixData</code>
	 * значения переданного массива вещественных чисел
	 * @param column номер столбца матрицы для изменения
	 * @param matrixColumn массив вещественных чисел
	 */
	private void setMatrixColumn(int column, double[] matrixColumn) {
		for (int i = 0; i < matrixColumn.length; i++) {
			matrixData[i][column] = matrixColumn[i];
		}
	}

	/**
	 * Метод возвращает характеристику массива чисел (сумму модулей его значений)
	 * @param matrixColumn массив вещественных чисел
	 * @return сумма модулей элементов массива
	 */
	private double columnCharacteristic(double[] matrixColumn) {
		double absSum = 0;
		for (double element : matrixColumn) {
			absSum += Math.abs(element);
		}
		return absSum;
	}

	/**
	 * Метод возвращает содержимое столбца матрицы из поля <code>matrixData</code>
	 * по указанному номеру столбца
	 * @param columnNumber номер столбца матрицы
	 * @return содержимое столбца матрицы из поля <code>matrixData</code> в виде 
	 * массива вещественных чисел
	 */
	private double[] getMatrixColumn(int columnNumber) {
		double[] column = new double[matrixData.length];
		for (int i = 0; i < column.length; i++) {
			column[i] = matrixData[i][columnNumber];
		}
		return column;
	}

	/* (non-Javadoc)
	 * @see ru.epam.task1.gui.Task#drawTitle()
	 */
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
