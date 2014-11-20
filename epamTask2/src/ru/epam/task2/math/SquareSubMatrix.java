package ru.epam.task2.math;

import java.util.Stack;

import ru.epam.task2.gui.Task;

public class SquareSubMatrix extends Task {
	
	private SubMatrix maxSubMatrix;
	
	private Integer[][] matrix;
	
	public SquareSubMatrix(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
		maxSubMatrix = new SubMatrix(0);
	}

	@Override
	protected void doLogic() {
		drawTitle();
		matrix = getMatrixFromStringArray();
		if (matrix.length == 0) {
			System.out.println(" Ошибка чтения матрицы из файла или некорректный формат! \r\n" 
							 + " Матрица должна быть записана построчно и элементы по горизонтали \r\n" 
							 + " должны быть разделены пробелами. Длина строк матрицы должна быть одинакова");
			printEmptyLines(12);
			return;
		}
		setMaxSubMatrix();
		System.out.println(" Найдена подматрица одинаковых элементов максимальной площади!");
		System.out.println(" Координаты её главной диагонали и количество элементов равны:");
		System.out.println(" " + maxSubMatrix);
		printEmptyLines(12);
	}

	private void setMaxSubMatrix() {
		// перебор матрицы построчно		
		for (int j = 0; j < matrix.length; j++) {
			for (int i = 0; i < matrix[j].length; i++) {
				
				// складываем в стек максимальную строку вправо от текущего элемента состоящую из идентичных чисел
				Stack<Integer> gorizont = new Stack<Integer>();
				for (int k = i; k < matrix[j].length; k++) {
					if (matrix[j][i].equals(matrix[j][k])) {
						gorizont.push(matrix[j][k]);
						setMaxSubMatrixOfCurrentElement(j, i, j, k);
					} else {
						break;
					}
				}
				
				// проверяем линию идентичных чисел на "расширение вниз"
				int countLines = 1;
				for (int y = j; y < matrix.length; y++) {
					if (y == j) continue;
					boolean line = true;
					for (int x = i; x < i + gorizont.size(); x++) {
						if (!matrix[j][i].equals(matrix[y][x])) {
							line = false;
							break;
						} else {
							setMaxSubMatrixOfCurrentElement(j, i, y, x);
						}
					}
					if (!line) break;
					countLines++;
				}
				setMaxSubMatrixOfCurrentElement(j, i, j + countLines - 1, i + gorizont.size() - 1);
			}
		}
	}

	private void setMaxSubMatrixOfCurrentElement(int y1, int x1, int y2, int x2) {
		if ((x2 - x1 + 1) * (y2 - y1 + 1) > maxSubMatrix.getSize()) {
			maxSubMatrix = new SubMatrix(y1, x1, y2, x2);
		}
		
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║ Задача №20. Найти в матрице целых чисел прямоугольную подматрицу, состоящую║\r\n" +
				 			" ║ из максимального количества одинаковых элементов. Использовать класс Stack ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║   Данные матрицы загружаются из файла task20.txt построчно с разделением   ║\r\n" +
				 			" ║              пробелами. Результат расчета выводится на экран               ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
	}

	public class SubMatrix {
		private int leftUpperX;
		private int leftUpperY;
		private int rightDownerX;
		private int rightDownerY;
		private final int size;
		
		public SubMatrix(int y1, int x1, int y2, int x2) {
			this.leftUpperX = x1;
			this.leftUpperY = y1;
			this.rightDownerX = x2;
			this.rightDownerY = y2;
			this.size = (x2 - x1 + 1) * (y2 - y1 + 1);
		}
		
		public SubMatrix(int zero) {
			this.size = zero;
		}
		
		@Override
		public String toString() {
			return "[(" + (leftUpperX + 1) + "," + (leftUpperY + 1) + ") .. (" + (rightDownerX + 1) 
					+ "," + (rightDownerY + 1) + ")] size = " + size; 
		}

		/**
		 * @return the leftUpperX
		 */
		public int getLeftUpperX() {
			return leftUpperX;
		}

		/**
		 * @return the leftUpperY
		 */
		public int getLeftUpperY() {
			return leftUpperY;
		}

		/**
		 * @return the rightDownerX
		 */
		public int getRightDownerX() {
			return rightDownerX;
		}

		/**
		 * @return the rightDownerY
		 */
		public int getRightDownerY() {
			return rightDownerY;
		}

		/**
		 * @return the size
		 */
		public int getSize() {
			return size;
		}
		
		
		
	}
}
