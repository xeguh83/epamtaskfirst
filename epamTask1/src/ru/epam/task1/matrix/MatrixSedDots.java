package ru.epam.task1.matrix;

import ru.epam.task1.gui.Task;

/**
 * Класс определяет количество седловых точек матрицы (минимальная в строке и 
 * максимальная в столбце точка матрицы)
 * @author Туркин А.К.
 */
public class MatrixSedDots extends Task {
	private int countSaddleDots = 0;
	private final double[][] matrixData; 

	/**
	 * Конструктор передает сокращенное наименование задания для формирования таблицы 
	 * заданий, массив строк с начальными данными. Также конструктор формирует матрицу
	 * в виде двухмерного массива вещественных чисел из массива строк с помощью метода
	 * <code>getMatrixFromStringArray()</code>
	 * @param shortName сокращенное наименование задания
	 * @param incomingStrings массив строк с начальным условием задачи
	 */
	public MatrixSedDots(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
		matrixData = getMatrixFromStringArray();
	}

	/**
	 * Метод выводит в консоль текст задания и проверяет содержимое матрицы. Если матрица
	 * пустая задание завершается и происходит выход в меню. Иначе происходит ожидание ввода
	 * любой строки для того чтобы дать возможность пользователю прочитать задание и вывод 
	 * результатов (подсчёт количества седловых точек матрицы) посчитанных методом <code>doLogic()</code>
	 */
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

	/**
	 * Метод производит перебор каждого элемента матрицы с проверкой является ли он 
	 * седловой точкой матрицы. При переборе метод определяет количество седловых 
	 * точек и записывает его значение в поле <code>countSaddleDots</code>
	 */
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

	/**
	 * Метод определяет является ли элемент матрицы седловой точкой
	 * @param i номер строки матрицы
	 * @param j номер столбца матрицы
	 * @return <code>true</code> если точка является седловой и <code>false</code> если не является
	 */
	private boolean isSaddleDot(int i, int j) {
		if (isMinInRow(i, j) && isMaxInColumn(i, j)) {
			return true;
		}
		return false;
	}

	/**
	 * Метод определяет является ли элемент матрицы максимальным в своём столбце
	 * @param rowNumber номер строки матрицы
	 * @param columnNumber номер столбца матрицы
	 * @return <code>true</code> если элемент матрицы является максимальным в своем столбце и <code>false</code> если не является
	 */
	private boolean isMaxInColumn(int rowNumber, int columnNumber) {
		for (int i = 0; i < matrixData.length; i++) {
			if (Double.compare(matrixData[rowNumber][columnNumber], matrixData[i][columnNumber]) < 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Метод определяет является ли элемент матрицы минимальным в своей строке
	 * @param rowNumber номер строки матрицы
	 * @param columnNumber номер столбца матрицы
	 * @return <code>true</code> если элемент матрицы является минимальным в своей строке и <code>false</code> если не является
	 */
	private boolean isMinInRow(int rowNumber, int columnNumber) {
		for (int i = 0; i < matrixData[rowNumber].length; i++) {
			if (Double.compare(matrixData[rowNumber][columnNumber], matrixData[rowNumber][i]) > 0) {
				return false;
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see ru.epam.task1.gui.Task#drawTitle()
	 */
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
