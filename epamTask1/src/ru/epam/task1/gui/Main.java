package ru.epam.task1.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import ru.epam.task1.arguments.DataFromArguments;
import ru.epam.task1.math.NumToMonth;
import ru.epam.task1.math.NumsInMatrix;
import ru.epam.task1.math.QuadraticEq;
import ru.epam.task1.matrix.MatrixBigestLocalMax;
import ru.epam.task1.matrix.MatrixBuildRisingRows;
import ru.epam.task1.matrix.MatrixCharacterLowering;
import ru.epam.task1.matrix.MatrixLocalMin;
import ru.epam.task1.matrix.MatrixMainDiagFill;
import ru.epam.task1.matrix.MatrixSedDots;
import ru.epam.task1.string.MaxAndMinLengthString;
import ru.epam.task1.string.MoreLessThanMedian;
import ru.epam.task1.string.SortString;
import ru.epam.task1.useswitch.SwitchFirstInterval;
import ru.epam.task1.useswitch.SwitchSecondInterval;
import ru.epam.task1.word.CharMinWord;
import ru.epam.task1.word.CodeRisingWord;
import ru.epam.task1.word.DifferentCharWord;
import ru.epam.task1.word.EngCharWord;
import ru.epam.task1.word.PalindromWord;


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
		Scanner in = new Scanner(System.in);
		Task[] taskList = createTaskList();
		while(true) {
			drawTitle();
			printMenu(taskList);
			Task.printEmptyLines(7);
			System.out.print("Введите номер задачи (00 для выхода из программы):");
			try {
				String option = in.nextLine();
				if (Integer.parseInt(option) == 0) {
					break;
				} else if (Integer.parseInt(option) == 9) {
					drawPreMenuForArgs(taskList, args);
				} else if ((Integer.parseInt(option) > 0) && (Integer.parseInt(option) < taskList.length)) {
					taskList[Integer.parseInt(option) - 1].drawTask();
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Неверный ввод номера задачи");
			}

		}
		
		in.close();
	}
		
	
	private static void drawPreMenuForArgs(Task[] taskList, String[] args) {
		while (true) {			
			Scanner in = new Scanner(System.in);
			drawPreMenuTitle(); 
			drawPreMenuTable(taskList); 	
			Task.printEmptyLines(13);
			System.out.print("Введите номер задачи (00 для выхода в меню)");
			try {
				String option = in.nextLine();
				if (Integer.parseInt(option) == 0) {
					break;
				} else if ((Integer.parseInt(option) > 0) && (Integer.parseInt(option) < 9)) {
					taskList[Integer.parseInt(option) - 1].drawTask(args);
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Неверный ввод номера задачи");
			}

		}
	}


	private static void drawPreMenuTable(Task[] taskList) {
		StringBuilder[] sb = new StringBuilder[6];
		int sbIndex = 0;
		sb[sbIndex] = new StringBuilder();
		sb[sbIndex].append(" ╔══════════════════════════════════════╦═════════════════════════════════════╗");
		for (int i = 0; i < 4; i++) {
			sbIndex++;
			sb[sbIndex] = new StringBuilder();	
			sb[sbIndex].append(" ║" + String.format("%02d", taskList[i].getId()) + "." + taskList[i].getShortName() + getNeededSpaces(taskList[i].getShortName()) 
							 + " ║" + String.format("%02d", taskList[i + 4].getId()) + "." + taskList[i + 4].getShortName() + getNeededSpaces(taskList[i + 4].getShortName()) + "║");
		}
		sbIndex++;
		sb[sbIndex] = new StringBuilder();
		sb[sbIndex].append(" ╚══════════════════════════════════════╩═════════════════════════════════════╝");
		
		for (StringBuilder strB : sb) {
			System.out.println(strB);
		}
	}


	private static void drawPreMenuTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║                Выберите задачу которую следует решить, используя           ║\r\n" +
							" ║                     данные из параметров командной строки                  ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");

		
	}


	private static void printMenu(Task[] taskList) {
		// TODO Auto-generated method stub
		StringBuilder[] sb = new StringBuilder[12];
		int sbIndex = 0;
		sb[sbIndex] = new StringBuilder();
		sb[sbIndex].append(" ╔══════════════════════════════════════╦═════════════════════════════════════╗");
		for (int i = 0; i < 10; i++) {
			sbIndex++;
			sb[sbIndex] = new StringBuilder();	
			sb[sbIndex].append(" ║" + String.format("%02d", taskList[i].getId()) + "." + taskList[i].getShortName() + getNeededSpaces(taskList[i].getShortName()) 
							 + " ║" + String.format("%02d", taskList[i + 10].getId()) + "." + taskList[i + 10].getShortName() + getNeededSpaces(taskList[i + 10].getShortName()) + "║");
		}
		sbIndex++;
		sb[sbIndex] = new StringBuilder();
		sb[sbIndex].append(" ╚══════════════════════════════════════╩═════════════════════════════════════╝");
		
		for (StringBuilder strB : sb) {
			System.out.println(strB);
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
			try {
				taskList[0] = new MaxAndMinLengthString("Самая короткая и длинная строки", getStringsFromFile("task1.txt"));
				taskList[1] = new SortString("Сортировка строк", getStringsFromFile("task2.txt"));
				taskList[2] = new MoreLessThanMedian("Строки меньше/больше средней длины", getStringsFromFile("task3.txt"));
				taskList[3] = new CharMinWord("Найти слово с минимумом букв", getStringsFromFile("task4.txt"));
				taskList[4] = new EngCharWord("Найти слово из латинских букв", getStringsFromFile("task5.txt"));
				taskList[5] = new CodeRisingWord("Найти слово с растущим кодом", getStringsFromFile("task6.txt"));
				taskList[6] = new DifferentCharWord("Найти слово с разными кодами", getStringsFromFile("task7.txt"));
				taskList[7] = new PalindromWord("Найти слово-палиндром", getStringsFromFile("task8.txt"));
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return taskList;
		
	}


	private static String[] getStringsFromFile(String file) throws IOException {
		   BufferedReader reader = new BufferedReader(new FileReader(new File("./" + file)));
		    ArrayList<String> store = new ArrayList<String>();
		    String line;
		    while((line = reader.readLine())!= null)
		    {
		            store.add(line);
		    } 
		    reader.close();
		    if (store.size() == 0) {
		    	String[] strings = {""};
		    	return strings;
		    } else {
		    	String[] strings = new String[store.size()];
		    	return store.toArray(strings);		    	
		    }
	}


	public static void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║                  Добро пожаловать в программу демонстрирующую              ║\r\n" +
				 			" ║                     решение задач Теста №1 от <epam> Systems               ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
	}
	
	

}
