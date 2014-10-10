package ru.epam.task1.string;

import java.util.Scanner;

import ru.epam.task1.gui.Task;

public class MaxAndMinLengthString extends Task {
	
	public MaxAndMinLengthString(String shortName) {
		super(shortName);
	}

	@Override
	public void drawTask() {
		drawTitle();
		System.out.println("\r\n\r\n\r\n\r\n\r\n\r\n\r\n");
		
		
		
	}

	private void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║     Задача №1. Ввести n строк с консоли, найти самую короткую и самую      ║\r\n" +
				 			" ║           длинную строки. Вывести найденные строки и их длину.             ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
		 					" ║ Строки разделённые знаком \".\" буду считаны из файла data.properties из   ║\r\n" +
				            " ║              параметра task1. Нажмите Enter для загрузки данных.           ║\r\n" +
		 					" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}
	

}
