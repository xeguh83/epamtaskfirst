package ru.epam.task1.string;

import java.util.Arrays;
import java.util.Comparator;

import ru.epam.task1.gui.Task;

public class SortString extends Task {
	
	public SortString(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	@Override
	protected void drawTitle() {
		// TODO Auto-generated method stub
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║     Задача №2. Ввести n строк с консоли. Упорядочить и вывести строки в    ║\r\n" +
				 			" ║              порядке возрастания (убывания) значений их длины              ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║Строки будут считаны из файла task2.txt (учитывается только перевод строки).║\r\n" +
				 			" ║                      Для продолжения нажмите Enter.                        ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

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

	private void printStringsDecreaseSort(String[] strings) {
		for (int i = strings.length - 1 ; i >= 0; i--) {
			System.out.println(strings[i]);
		}
	}

	private void printStringsIncreaseSort(String[] strings) {
		for (String string : strings) {
			System.out.println(string);
		}
	}

	private class StringLengthComparator implements Comparator<String>{
		@Override
		public int compare(String obj1, String obj2) {			
			if( null != obj1 && null != obj2 ) return Integer.compare(obj1.length(), obj2.length());
			if( null == obj1 && null == obj2 ) throw new IllegalArgumentException("Both strings is null");
			if( null == obj1 ){
				return -1;
			}else{			
				return 1;
			}		
		}
	}

}
