package ru.epam.task2.collection;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

import ru.epam.task2.gui.Task;


public class BlackBoxStructure extends Task {
	
	private NumStructure struct;

	public BlackBoxStructure(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	@Override
	protected void doLogic() {
		drawTitle();
		if (!setStruct()) {
			System.out.println(" Ошибка чтения из файла или некорректный формат!\r\n" 
							 + " Числа разделенные пробелами считываются из файла task21.txt");
			printEmptyLines(13);
			return;
		}
		while (true) {
			drawSreen(struct);
			String option = askOption(" Введите \"+\" добавление, \"k\" ввод k, \"?\" поиск k-го элемента, \"0\" выход ");
			if (option.equals("+")) {
				addElement();
			} else if (option.equals("k")) {
				updateK();
			} else if (option.equals("?")) {
				findMinKElement();
			} else if (option.equals("0")) {
				return;
			}
		}
	}

	private void findMinKElement() {
		drawSreen(struct);
		System.out.print(" K-ый по минимальности элемент это:" + String.format("%#8.2f", struct.getMinKElement()));
		pressAnyKey();
	}

	private void updateK() {
		while (true) {
			drawSreen(struct);
			System.out.print(" Введите целое число K для определения минимальности: ");
			try {
				Scanner in = new Scanner(System.in);
				String word = in.nextLine();
				Integer value = Integer.parseInt(word);
				if (value < 1) {
					throw new NumberFormatException();
				} else {
					struct.setK(value);
					return;
				}
			} catch (Exception e) {
				printError(struct, " Ошибка обновления числа K!");
			}
		}
	}

	private void addElement() {
		while (true) {
			drawSreen(struct);
			System.out.print(" Введите вещественное число для добавления в структуру: ");
			try {
				Scanner in = new Scanner(System.in);
				String word = in.nextLine();
				Double value = Double.parseDouble(word);
				if (Double.isInfinite(value) || Double.isNaN(value)) {
					throw new NumberFormatException();
				} else if (!struct.add(value)) {
					printError(struct, " Ошибка! Число уже добавлено в структуру!");
				} else {
					return;
				}
			} catch (Exception e) {
				printError(struct, " Ошибка добавления числа в структуру!");
			}
		}
	}

	private boolean setStruct() {
		struct = new NumStructure();
		String[] words = getWordsFromStringArray();
		try {
			for (String string : words) {
				Double num = Double.parseDouble(string);
				if (Double.isInfinite(num) || Double.isNaN(num)) {
					return false;
				} else {
					struct.add(num);
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║ Задача №21. Реализовать структуру, хранящую множество чисел и имеющую внут-║\r\n" +
				 			" ║  реннее K. Cтруктура добавляет числа и ищет К-ое по минимальности число.   ║\r\n" +
		 					" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║  Добавление/поиск чисел происходит через запрос действия у пользователя:   ║\r\n" +
				 			" ║  \"+\" добавление, \"k\" обновление k-го, \"?\" поиск k-го, \"0\" выход в меню.    ║\r\n" +
		 					" ╚════════════════════════════════════════════════════════════════════════════╝");
	}

	private class NumStructure {
		
		private final TreeSet<Double> numSet;
		private int k;
		
		public NumStructure() {
			numSet = new TreeSet<Double>() {
				@Override
				public String toString() {
			        Iterator<Double> it = iterator();
			        if (! it.hasNext())
			            return "[]";

			        StringBuilder sb = new StringBuilder();
			        sb.append('[');
			        for (;;) {
			        	Double e = it.next();
			            sb.append(e.equals(this) ? "(this Collection)" : String.format("%#8.2f", e));
			            if (! it.hasNext())
			                return sb.append(']').toString();
			            sb.append(';').append(' ');
			        }
				}
			};
			k = 1;
		}

		public Double getMinKElement() {
			if (k >= numSet.size()) {
				return numSet.last();
			} else if (k == 1) {
				return numSet.first();
			}
			Iterator<Double> it = numSet.iterator();
			for (int i = 1; i < k; i++) {
				it.next();
			}
			return it.next();
		}

		public boolean add(Double num) {
			return numSet.add(num);
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return numSet.toString();
		}

		/**
		 * @param k the k to set
		 */
		public void setK(int k) {
			this.k = k;
		}
		
		
		
	}
}
