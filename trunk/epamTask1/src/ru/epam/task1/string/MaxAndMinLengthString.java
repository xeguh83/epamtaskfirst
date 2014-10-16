package ru.epam.task1.string;

import ru.epam.task1.gui.Task;

public class MaxAndMinLengthString extends Task {
	private int maxStringIndex = Integer.MIN_VALUE;
	private int minStringIndex = Integer.MAX_VALUE;
		
	public MaxAndMinLengthString(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	@Override
	protected void doLogic() {
		// TODO Auto-generated method stub
		String[] stringsForWork = getStrings();
		System.out.println("Загружены следующие строки:");
		setMaxAndMinStringLengthIndex(stringsForWork);
		System.out.println("Строка с максимальной длиной (" + stringsForWork[maxStringIndex].length() + "): " 
				+ stringsForWork[maxStringIndex]);
		System.out.println("Строка с минимальной длиной: (" + stringsForWork[minStringIndex].length() + "): "
				+ stringsForWork[minStringIndex]);
		Task.printEmptyLines(12);
	}

	private void setMaxAndMinStringLengthIndex(String[] strings) {
		// TODO Auto-generated method stub
		int maxStrLength = Integer.MIN_VALUE;
		int minStrLength = Integer.MAX_VALUE;
		for (int i = 0; i < strings.length; i++) {
			if (strings[i].length() > maxStrLength) {
				maxStrLength = strings[i].length();
				maxStringIndex = i;
			}
			if (strings[i].length() < minStrLength) {
				minStrLength = strings[i].length();
				minStringIndex = i;
			}
		}
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║     Задача №1. Ввести n строк с консоли, найти самую короткую и самую      ║\r\n" +
				 			" ║           длинную строки. Вывести найденные строки и их длину.             ║\r\n" +
		 					" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
		 					" ║Строки будут считаны из файла task1.txt (учитывается только перевод строки).║\r\n" +
		 					" ║                Подготовьте данные для загрузки и нажмите Enter.            ║\r\n" +
		 					" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}

}
