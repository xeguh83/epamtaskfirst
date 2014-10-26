package ru.epam.task1.math;

import java.util.Formatter;

import ru.epam.task1.gui.Task;

/**<p>Вывод в консоль квадратной матрицы целых чисел от 1 до 25</p>
 * Класс формирует квадратную матрицу заполненную целыми числами от 1 
 * до 25 и выводит её в консоль в стандартом виде
 * @author Туркин А.К.
 *
 */
public class NumsInMatrix extends Task {

	/**Конструктор для передачи короткого наименования задачи для постройки таблицы задач 
	 * @param shortName короткое наименование задачи
	 */
	public NumsInMatrix(String shortName) {
		super(shortName);
	}

	/**<p>Реализация абстрактного метода <code>doLogic</code></p>
	 * Метод формирует квадратную матрицу целых чисел по заданному в условии задачи размеру 
	 * и выводит её в консоль в стандартном виде, затем выводит требуемое количество отступов 
	 * для корректного отображения заглавия задачи  
	 */
	@Override
	protected void doLogic() {
		int[][] matrix = getSquareMatrixOfInt(5);
		printMatrix(matrix);
		Task.printEmptyLines(9);
	}

	/**<p>Вывод в консоль матрицы целых чисел</p>
	 * Метод выводит в консоль матрицу целых чисел в стандартном виде с требуемыми отступами
	 * @param matrix матрица целых чисел
	 */
	private void printMatrix(int[][] matrix) {
		Formatter f = new Formatter();
		for (int i = 0; i < matrix.length; i++) {
			f.format(" [ ", "");
			for (int j = 0; j < matrix[i].length; j++) {
				f.format("%3d", matrix[i][j]);
			}
			f.format(" ]%n", "");
		}
		System.out.println(f);
	}

	/**</p>Формирование квадратной матрицы<p>
	 * Метод формирует квадратную матрицу последовательно возрастающих от 1 целых чисел по 
	 * заданному размеру
	 * @param size размер квадратной матрицы
	 * @return квадратная матрица целых чисел заданного размера
	 */
	private int[][] getSquareMatrixOfInt(int size) {
		int[][] matrix = new int[size][size];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = (i * size) + (j + 1);
			}
		}
		return matrix;
	}

	/* (non-Javadoc)
	 * @see ru.epam.task1.gui.Task#drawTitle()
	 */
	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║   Задача №12. Написать программу, которая выводит числа от 1 до 25 в виде  ║\r\n" +
				 			" ║                   матрицы 5x5 слева направо и сверху вниз                  ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║                        Загрузка из файла не производится                   ║\r\n" +
				 			" ║                          Нажмите Enter для продолжения                     ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
		
	}

}
