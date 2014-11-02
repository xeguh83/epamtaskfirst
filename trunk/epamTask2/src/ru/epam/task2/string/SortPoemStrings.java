package ru.epam.task2.string;

import java.util.Arrays;
import java.util.Comparator;

import ru.epam.task2.gui.Task;

public class SortPoemStrings extends Task {

	public SortPoemStrings(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	@Override
	protected void doLogic() {
		String[] strings = getStrings();
		Arrays.sort(strings, new StringLengthComparator());
		writeStringsToFile("task3output.txt", strings);
		System.out.println(" Строки, считанные из файла task3.txt записаны в порядке возрастания длины");
		System.out.println(" в файл task3output.txt");
		printEmptyLines(13);
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║     Задача №3. Занести стихотворения одного автора в список. Провести      ║\r\n" +
				 			" ║                      сортировку по возрастанию длин строк                  ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║Строки будут считаны из файла task3.txt (учитывается только перевод строки).║\r\n" +
				 			" ║Вывод в строк в порядке возрастания длин производится в файл task3output.txt║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");	
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
