package ru.epam.task2.list;

import java.util.ArrayList;
import java.util.Arrays;

import ru.epam.task2.gui.Task;

public class ArrayListVersusLinkedList extends Task {
	private final Integer[] array = {1,2,3,4,5,6,7,8,9,10};

	public ArrayListVersusLinkedList(String shortName) {
		super(shortName);

	}

	@Override
	protected void doLogic() {
		int arraySize = askArraySize(); 
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(array)); 
		int index = 1;
		while (list.size() > 1) {
			if (list.size() < index) {
				index = 1;
			} else if (list.size() == index) {
				index = 0;
			} else {
				list.remove(index);
				index++;
			}
		}
		System.out.println(list.get(0));
	}

	private int askArraySize() {
		while (true) {
			drawTitle();
			System.out.println(" Введите ");
		}
		return 0;
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
		 					" ║Задача №11. Выделить все различные слова. Для каждого слова подсчитать час- ║\r\n" + 
		 					" ║   тоту его встречаемости. Учитывать регистр. Использовать класс HashMap    ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║               Общая строка склеивается из строк файла task11.txt           ║\r\n" +
				 			" ║       Различные слова с частотой будут записаны в файл task11output.txt    ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
	}

}
