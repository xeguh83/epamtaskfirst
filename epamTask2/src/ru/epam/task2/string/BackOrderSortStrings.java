package ru.epam.task2.string;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import ru.epam.task2.gui.Task;

public class BackOrderSortStrings extends Task {

	public BackOrderSortStrings(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}
	
	@Override
	protected void doLogic() {
		String[] outputStrings = getStringsInReverseOrder(getStrings());
		writeStringsToFile("task1output.txt", outputStrings);
		System.out.println(" Строки в обратном порядке записаны в файл task1output.txt");
		printEmptyLines(14);
	}

	private String[] getStringsInReverseOrder(String[] strings) {
		LinkedList<String> lnkL = new LinkedList<String>(Arrays.asList(strings));
		Iterator<String> listIterator = lnkL.descendingIterator();
		String[] outputStrings = new String[strings.length];
		for (int i = 0; i < outputStrings.length; i++) {
			outputStrings[i] = listIterator.next();
			
		}
		return outputStrings;
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║           Задача №1. Ввести строки из файла, записать в список.            ║\r\n" +
				 			" ║                  Вывести строки в файл в обратном порядке                  ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
		 					" ║    Строки считаны из файла task1.txt (учитывается только перевод строки).  ║\r\n" +
		 					" ║      Вывод в обратном порядке строк производится в файл task1output.txt    ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
