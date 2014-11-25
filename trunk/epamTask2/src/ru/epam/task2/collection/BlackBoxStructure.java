package ru.epam.task2.collection;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

import ru.epam.task2.gui.Task;


/**
 * <p>Класс задания № 21</p>
 * Класс отрисовывает задание и содержит структуру "черный ящик" в которую можно добавлять элементы и получать К-ый по минимальности элемент.
 * Число К вводится пользователем
 * @author Туркин А.К.
 */
public class BlackBoxStructure extends Task {
	
	/**
	 * Структура "черный ящик" реализованная внутренним классом NumStructure
	 */
	private NumStructure struct;

	/**
	 * Конструктор передает наследуемому классу краткое описание и исходные данные по входящим параметрам
	 * @param shortName краткое описание задания
	 * @param incomingStrings массив исходных данных
	 */
	public BlackBoxStructure(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	/**
	 * Метод содержит тело задания. Метод проверяет исходные данные и если они некорректны, извещает пользователя об этом и завершает
	 * работу. Если данные корректны, метод начинает циклично отрисовывать экран состоящий из панели задания и текущего содержимого 
	 * "черного ящика", а также предлагает пользователю ввести символ действия со структурой. При корректном вводе вызывается 
	 * соответствующий метод, а затем экран снова перерисовывается.
	 */
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

	/**
	 * Метод перерисовывает экран состоящий из панели задания и текущего содержимого 
	 * "черного ящика" и выводит К-ый по минимальности элемент структуры. К вводится пользователем в методе <code>updateK()</code>. 
	 * По-умолчанию, К = 1.
	 * @see #updateK()
	 */
	private void findMinKElement() {
		drawSreen(struct);
		System.out.print(" K-ый по минимальности элемент это:" + String.format("%#8.2f", struct.getMinKElement()));
		pressAnyKey();
	}

	/**
	 * Метод перерисовывает экран состоящий из панели задания и текущего содержимого 
	 * "черного ящика" и запрашивает новое значение целого К у пользователя. В случае ошибки
	 * метод перерисовывает экран и повторяет запрос
	 */
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

	/**
	 * Метод перерисовывает экран состоящий из панели задания и текущего содержимого 
	 * "черного ящика" и запрашивает у пользователя очередное значение элемента структуры. В случае ошибочного ввода 
	 * пользователь извещается об этом и экран перерисовывается с повтором ввода. Если такой элемент уже добавлен в структуру
	 * пользователь также об этом извещается. После корректного ввода метод завершает работу
	 */
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

	/**
	 * Метод инициализирует структуру объектом класса <code>NumStructure()</code>. В структуру поочередно добавляются
	 * элементы из исходных данных. В случае корректного элемента он добавляется по правилам метода <code>add()</code> 
	 * класса <code>NumStructure()</code>
	 * @return <code>true</code> если все исходные данные корректно добавлены в структуру. Иначе возращает <code>false</code>
	 */
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

	/* (non-Javadoc)
	 * @see ru.epam.task2.gui.Task#drawTitle()
	 */
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

	/**
	 * Внутренний служебный класс содержащий структуру хранения действительных чисел на базе класса <code>TreeSet</code>
	 * @author Туркин А.К.
	 */
	private class NumStructure {
		
		/**
		 * Набор чисел для хранения
		 */
		private final TreeSet<Double> numSet;
		/**
		 * Внутреннее число К
		 */
		private int k;
		
		/**
		 * Конструктор инициализирует набор действительных чисел используя коллекцию <code>TreeSet</code> и переопределяет в 
		 * нем метод <code>toString()</code> для более удобного отображения содержимого коллекции. Также конструктор задает значение
		 * числа К по умолчанию равным 1.
		 */
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

		/**
		 * Метод возвращает К-ый по минимальности элемент коллекции
		 * @return К-ый по минимальности элемент
		 */
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

		/**
		 * Метод добавляет элемент в коллекцию по правилам метода <code>add()</code> класса <code>TreeSet</code>
		 * @param num элемент для добавления в коллекцию
		 * @return возвращает значение по правилам метода <code>add()</code> класса <code>TreeSet</code>
		 * @see java.util.TreeSet#add(Object)
		 */
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
