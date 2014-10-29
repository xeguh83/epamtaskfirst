package ru.epam.task1.string;

import java.util.Arrays;
import java.util.Comparator;

import ru.epam.task1.gui.Task;

/**
 * Класс сортирует строки в порядке возрастания их длины и выводит их в консоль
 * @author Туркин А.К.
 */
public class SortString extends Task {
	
	/**
	 * Конструктор для передачи короткого наименования задачи для постройки таблицы задач и 
	 * массива строк содержащего начальные условия задачи
	 * @param shortName короткое наименование задачи
	 * @param incomingStrings массив строк с начальным условием задачи
	 */
	public SortString(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	/* (non-Javadoc)
	 * @see ru.epam.task1.gui.Task#drawTitle()
	 */
	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║     Задача №2. Ввести n строк с консоли. Упорядочить и вывести строки в    ║\r\n" +
				 			" ║              порядке возрастания (убывания) значений их длины              ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║Строки будут считаны из файла task2.txt (учитывается только перевод строки).║\r\n" +
				 			" ║                      Для продолжения нажмите Enter.                        ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

	/**
	 * Метод сортирует массив строк полученный из поля <code>incomingStrings</code>
	 * в соответствии с компаратором описанном в классе <code>StringLengthComparator</code>, 
	 * затем метод выводит строки сначала в порядке прямой сортировки, а затем обратной с помощью 
	 * методов <code>printStringsIncreaseSort</code> и <code>printStringsDecreaseSort</code>
	 * @see #printStringsIncreaseSort(String[])
	 * @see #printStringsDecreaseSort(String[])
	 * @see SortString.StringLengthComparator
	 */
	@Override
	protected void doLogic() {
		String[] strings = getStrings();
		Arrays.sort(strings, new StringLengthComparator());
		System.out.println("Сортировка по увеличению длины строки:");
		printStringsIncreaseSort(strings);
		pressAnyKey();
		System.out.println("Сортировка по уменьшению длины строки:");
		printStringsDecreaseSort(strings);
	}

	/**
	 * Метод выводит строки в консоль в обратной последовательности расположения их в массиве 
	 * @param strings массив строк для вывода в консоль
	 */
	private void printStringsDecreaseSort(String[] strings) {
		for (int i = strings.length - 1 ; i >= 0; i--) {
			System.out.println(strings[i]);
		}
	}

	/**
	 * Метод выводит строки в консоль в прямой последовательности расположения их в массиве 
	 * @param strings массив строк для вывода в консоль
	 */
	private void printStringsIncreaseSort(String[] strings) {
		for (String string : strings) {
			System.out.println(string);
		}
	}

	/**
	 * Класс компаратор для сортировки строк в порядке увеличения их длины
	 * @author Туркин А.К.
	 * @since 1.7
	 */
	private class StringLengthComparator implements Comparator<String>{
		
		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(String obj1, String obj2) {			
			if( null != obj1 && null != obj2 ) return Integer.compare(obj1.length(), obj2.length());
			if( null == obj1 && null == obj2 ) {
				return 0;
			}
			if( null == obj1 ){
				return -1;
			}else{			
				return 1;
			}		
		}
	}
}
