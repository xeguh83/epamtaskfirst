package ru.epam.task1.matrix;

import ru.epam.task1.gui.Task;

/**
 * Класс для перестроения матрицы так чтобы сумма элементов в её строках возрастала
 * @author Туркин А.К.
 */
public class MatrixBuildRisingRows extends Task {
	private final double[][] matrixData; 

	/**
	 * Конструктор передает сокращенное наименование задания для формирования таблицы 
	 * заданий, массив строк с начальными данными. Также конструктор формирует матрицу
	 * в виде двухмерного массива вещественных чисел из массива строк с помощью метода
	 * <code>getMatrixFromStringArray()</code>
	 * @param shortName сокращенное наименование задания
	 * @param incomingStrings массив строк с начальным условием задачи
	 */
	public MatrixBuildRisingRows(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
		matrixData = getMatrixFromStringArray();
	}

	/**
	 * Метод выводит в консоль текст задания и проверяет содержимое матрицы. Если матрица
	 * пустая задание завершается и происходит выход в меню. Иначе происходит ожидание ввода
	 * любой строки для того чтобы дать возможность пользователю прочитать задание и вывод 
	 * результатов (нахождение наибольшего локального максимума) посчитанных методом 
	 * <code>doLogic()</code>
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
	 * Метод осуществляет пузырьковый метод сортировки строк матрицы
	 * по сумме элементов строк  
	 */
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

	/**
	 * Метод меняет указанные строки матрицы местами 
	 * @param row текущая строка матрицы
	 * @param nextRow следующая строка матрицы
	 */
	private void swapRows(int row, int nextRow) {
		double[] tempRow = matrixData[row];
		matrixData[row] = matrixData[nextRow];
		matrixData[nextRow] = tempRow;
	}

	/**
	 * Метод возвращает сумму значений элементов массива
	 * @param matrixRow массив вещественных чисел
	 * @return сумма значений элементов массива 
	 */
	private double rowSum(double[] matrixRow) {
		double sum = 0;
		for (double element : matrixRow) {
			sum += element;
		}
		return sum;
	}

	/* (non-Javadoc)
	 * @see ru.epam.task1.gui.Task#drawTitle()
	 */
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
