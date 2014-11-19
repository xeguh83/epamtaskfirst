package ru.epam.task2.math;

import java.util.Stack;

import ru.epam.task2.gui.Task;

public class SquareSubMatrix extends Task {
	
	private SubMatrix maxSubMatrix;
	
	private final Integer[][] matrix = {
			{ 1,  2,  3,  4,  5},
			{ 6,  7,  8,  9, 10},
			{11, 12, 13, 14, 15},
			{16, 17, 18, 19, 20},
			{21, 22, 23, 24, 25}
	};

	public SquareSubMatrix(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
		maxSubMatrix = new SubMatrix(0);
	}

	@Override
	protected void doLogic() {
		
		
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
		// TODO Auto-generated method stub
		
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
