package ru.epam.task1.math;

import java.util.Formatter;

import ru.epam.task1.gui.Task;

public class NumsInMatrix extends Task {

	public NumsInMatrix(String shortName) {
		super(shortName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doLogic() {
		int[][] matrix = getSquareMatrixOfInt(5);
		printMatrix(matrix);
		Task.printEmptyLines(9);
	}

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

	private int[][] getSquareMatrixOfInt(int size) {
		int[][] matrix = new int[size][size];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = (i * 5) + (j + 1);
			}
		}
		return matrix;
	}

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
