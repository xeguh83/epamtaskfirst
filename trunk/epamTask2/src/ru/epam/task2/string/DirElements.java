package ru.epam.task2.string;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import ru.epam.task2.gui.Task;

public class DirElements extends Task {
	private String dirName;
	private final ArrayList<String> dirStructure;


	public DirElements(String shortName) {
		super(shortName);
		dirStructure = new ArrayList<String>();
	}
	
	@Override
	public void drawTask() {
		Scanner in = new Scanner(System.in);
		while (true) {
			drawTitle();
			Task.printEmptyLines(15);
			System.out.print(" Введите полное имя каталога (0 для выхода в меню)");
			try {
				String option = in.nextLine();
				if (option.equalsIgnoreCase("0")) {
					break;
				} else if (new File(option).isDirectory()) {
					dirName = option;
					drawTitle();
					doLogic();
					System.out.println(" Список элементов каталога " + dirName);
					System.out.println(" записан в файл task2output.txt");
					printEmptyLines(13);
					pressAnyKeyForMenu();
					break;					
				} else {
					throw new Exception("Введенная строка не является каталогом!");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	@Override
	protected void doLogic() {
		dirStructure.add(dirName.toUpperCase() + "\n");
		addElementsInsideDirToList(new File(dirName).listFiles());
		writeStringsToFile("task2output.txt", dirStructure.toArray(new String[dirStructure.size()]));
	}

	private void addElementsInsideDirToList(File[] elements) {
		for (File file : elements) {
			if (file.isDirectory()) {
				dirStructure.add(file.getPath().toUpperCase() + "\n");
				addElementsInsideDirToList(file.listFiles());
			} else {
				dirStructure.add(file.getPath() + "\n");
			}
		}
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║                Задача №2. Создать список из элементов каталога             ║\r\n" +
				 			" ║                                 и его подкаталогов                         ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║      Полное имя каталога вводится пользователем. Список его элементов      ║\r\n" +
				 			" ║      и элементов его подкаталогов записывается в файл task2output.txt      ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
