package ru.epam.task1.matrix;

import java.util.ArrayList;

import ru.epam.task1.gui.Task;

/**
 * Класс для нахождения количества локальных минимумов матрицы вещественных чисел
 * @author Туркин А.К.
 */
public class MatrixLocalMin extends Task {
	private int countLocalMin = 0;
	private final double[][] matrixData; 

	/**
	 * Конструктор передает сокращенное наименование задания для формирования таблицы 
	 * заданий, массив строк с начальными данными. Также конструктор формирует матрицу
	 * в виде двухмерного массива вещественных чисел из массива строк с помощью метода
	 * <code>getMatrixFromStringArray()</code>
	 * @param shortName сокращенное наименование задания
	 * @param incomingStrings массив строк с начальным условием задачи
	 */
	public MatrixLocalMin(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
		matrixData = getMatrixFromStringArray();
	}

	/**
	 * Метод выводит в консоль текст задания и проверяет содержимое матрицы. Если матрица
	 * пустая задание завершается и происходит выход в меню. Иначе происходит ожидание ввода
	 * любой строки для того чтобы дать возможность пользователю прочитать задание и вывод 
	 * результатов (нахождение количества локальных минимумов) посчитанных методом
	 * <code>doLogic()</code>
	 */
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

	/**
	 * Метод производит перебор каждого элемента матрицы с проверкой является ли он 
	 * локальным максимумом. При переборе метод определяет количество локальных
	 * минимумов и записывает его значение в поле <code>countLocalMin</code>
	 */
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

	/**
	 * <p>Проверка является ли элемент матрицы локальным минимумом</p>
	 * Метод определяет является ли элемент матрицы по указанным координатам
	 * локальным минимумом (строго ли меньше он своих соседей) 
	 * @param rowNumber номер строки матрицы
	 * @param columnNumber номер столбца матрицы
	 * @return <code>true</code> если элемент является локальным минимумом и 
	 * <code>false</code> если не является
	 */
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

	/**
	 * <p>Формирование списка соседей элемента матрицы</p>
	 * Метод формирует список элементов матрицы, которые являются соседями 
	 * заданного координатами элемента матрицы
	 * @param rowNumber номер строки элемента матрицы 
	 * @param columnNumber номер столбца элемента матрицы
	 * @return список соседей элемента матрицы 
	 */
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

	/* (non-Javadoc)
	 * @see ru.epam.task1.gui.Task#drawTitle()
	 */
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
