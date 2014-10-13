package ru.epam.task1.gui;

import java.io.*;
import java.util.Scanner;

import ru.epam.task1.arguments.DataFromArguments;
import ru.epam.task1.math.*;
import ru.epam.task1.matrix.*;
import ru.epam.task1.string.*;
import ru.epam.task1.useswitch.*;
import ru.epam.task1.word.*;


public class Main {
	

	static
	{
	  try
	  {
	    if(System.getProperty("os.name").toLowerCase().contains("windows"))
	    {
	      PrintStream out = new PrintStream(System.out, true, "Cp866");
	      PrintStream err = new PrintStream(System.err, true, "Cp866");
	      System.setOut(out);
	      System.setErr(err);
	    }
	  }
	  catch(UnsupportedEncodingException e)
	  {
	    System.err.println("Couldn't change console encoding " + e);
	  }
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		Task[] taskList = createTaskList();

		while(true) {
			drawTitle();
			printMenu(taskList);
			System.out.println("\r\n\r\n\r\n\r\n\r\n\r\n\r\n");
			System.out.print("Введите номер задачи (00 для выхода из программы):");
			try {
				String option = in.nextLine();
				if (Integer.parseInt(option) == 0) {
					break;
				} else if (Integer.parseInt(option) == 9) {
					taskList[8].drawTask(args);
				} else if ((Integer.parseInt(option) > 0) && (Integer.parseInt(option) < taskList.length)) {
					taskList[Integer.parseInt(option) - 1].drawTask();
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Неверный ввод номера задачи");
			}

		}


	}
		
	
	private static void printMenu(Task[] taskList) {
		// TODO Auto-generated method stub
		StringBuffer[] sb = new StringBuffer[12];
		int sbIndex = 0;
		sb[sbIndex] = new StringBuffer();
		sb[sbIndex].append(" ╔══════════════════════════════════════╦═════════════════════════════════════╗");
		for (int i = 0; i < 10; i++) {
			sbIndex++;
			sb[sbIndex] = new StringBuffer();	
			sb[sbIndex].append(" ║" + String.format("%02d", taskList[i].getId()) + "." + taskList[i].getShortName() + getNeededSpaces(taskList[i].getShortName()) 
							 + " ║" + String.format("%02d", taskList[i + 10].getId()) + "." + taskList[i + 10].getShortName() + getNeededSpaces(taskList[i + 10].getShortName()) + "║");
		}
		sbIndex++;
		sb[sbIndex] = new StringBuffer();
		sb[sbIndex].append(" ╚══════════════════════════════════════╩═════════════════════════════════════╝");
		
		for (StringBuffer strBuf : sb) {
			System.out.println(strBuf);
		}
	}

	private static String getNeededSpaces(String string) {
		// TODO Auto-generated method stub
		String spaces = "";
		for (int i = 0; i < (34 - string.length()); i++ ) {
			spaces = spaces + " ";
		}
		return spaces;
	}


	private static Task[] createTaskList() {
		// TODO Auto-generated method stub
		Task[] taskList = new Task[20];
		taskList[0] = new MaxAndMinLengthString("Самая короткая и длинная строки");
		taskList[1] = new SortString("Сортировка строк");
		taskList[2] = new MoreLessThanMedian("Строки меньше/больше средней длины");
		taskList[3] = new CharMinWord("Найти слово с минимумом букв");
		taskList[4] = new EngCharWord("Найти слово из латинских букв");
		taskList[5] = new CodeRisingWord("Найти слово с растущим кодом");
		taskList[6] = new DifferentCharWord("Найти слово с разными кодами", "Мама рамур. Мама. Мылаа. Рааму. Мама мала рама вчара. Вчара.мама будут мыты рама завтра");
		taskList[7] = new PalindromWord("Найти слово-палиндром");
		taskList[8] = new DataFromArguments("Задачи 1-8 из арг. ком. строки");
		taskList[9] = new SwitchFirstInterval("Найти принадлежность k интервалу A");
		taskList[10] = new SwitchSecondInterval("Найти принадлежность интервалу B");
		taskList[11] = new NumsInMatrix("Перевести числа в матрицу");
		taskList[12] = new QuadraticEq("Решение квадратных уравнений");
		taskList[13] = new NumToMonth("Вывод месяца по номеру");
		taskList[14] = new MatrixSedDots("Найти седловые точки матрицы");
		taskList[15] = new MatrixBuildRisingRows("Перестройка возрастающей матрицы");
		taskList[16] = new MatrixLocalMin("Найти локальные минимумы матрицы");
		taskList[17] = new MatrixBigestLocalMax("Локальный максимум матрицы");
		taskList[18] = new MatrixCharacterLowering("Уменьшение характеристик матрицы");
		taskList[19] = new MatrixMainDiagFill("Заполнение главной диагонали");
		return taskList;
		
	}


	public static void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║                  Добро пожаловать в программу демонстрирующую              ║\r\n" +
				 			" ║                     решение задач Теста №1 от <epam> Systems               ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
	}
	
	

}
