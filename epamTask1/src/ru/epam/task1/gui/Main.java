package ru.epam.task1.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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


/** 
 * Точка входа в программу.
 * Отвечает за отрисовку и перемещение по главному меню
 * @author Туркин А.К.
 *
 */
public class Main {
	
// переопределяет кодировку PrintStream на Cp866
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
	
	
	/**<p>Точка входа в программу</p>
	 * 
	 * Формирует массив задач и отрисовывает меню. 
	 * Осуществляет переход на задачу по вводу её номера
	 * @param args данные из параметров командной строки
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Task[] taskList = createTaskList();
		while(true) {
			drawTitle();
			printMenu(taskList);
			printHelpPanel();
			printTaskDate();
			Task.printEmptyLines(1);
			System.out.print(" Введите номер задачи (00 для выхода из программы):");
			try {
				String option = in.nextLine();
				if (Integer.parseInt(option) == 0) {
					break;
				} else if (Integer.parseInt(option) == 9) {
					drawPreMenuForArgs(taskList, args);
				} else if ((Integer.parseInt(option) > 0) && (Integer.parseInt(option) < (taskList.length + 1))) {
					taskList[Integer.parseInt(option) - 1].drawTask();
				}
			} catch (Exception e) {
				System.out.println("Неверный ввод номера задачи");
			}

		}
		
		in.close();
	}
		
	
	private static void printHelpPanel() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║ Загрузка начальных данных происходит при запуске программы. Для того чтобы ║\r\n" +
							" ║ изменить начальные данные, отредактируйте файлы и перезапустите программу  ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");		
	}


	/**
	 * Метод выводит в консоль дату выдачи задания и дату окончания работ по заданию
	 */
	private static void printTaskDate() {
		Calendar begin = new GregorianCalendar(2014, 9, 6);
		String startingDate = Integer.toString(begin.get(Calendar.DAY_OF_MONTH)) 
				+ "." + Integer.toString(begin.get(Calendar.MONTH) + 1) 
				+ "." + Integer.toString(begin.get(Calendar.YEAR));
		Calendar fin = new GregorianCalendar(2014, 9, 26);
		String finishingDate = Integer.toString(fin.get(Calendar.DAY_OF_MONTH)) 
				+ "." + Integer.toString(fin.get(Calendar.MONTH) + 1) 
				+ "." + Integer.toString(fin.get(Calendar.YEAR));
		 System.out.println(" Начало задания:  " + startingDate);
		 System.out.println(" Конец задания:  " + finishingDate);
	}


	/**<p>Пременю для отработки задач 1-8 с данными из командной строки</p>
	 * Отрисовывает список задач для выполнения и осуществляет переход на задачу по выбору пользователя
	 * @param taskList массив задач для перехода
	 * @param args параметры командной строки для передачи в выбранную задачу
	 */
	private static void drawPreMenuForArgs(Task[] taskList, String[] args) {
		while (true) {			
			Scanner in = new Scanner(System.in);
			drawPreMenuTitle(); 
			drawPreMenuTable(taskList); 	
			Task.printEmptyLines(13);
			System.out.print(" Введите номер задачи (00 для выхода в меню)");
			try {
				String option = in.nextLine();
				if (Integer.parseInt(option) == 0) {
					break;
				} else if ((Integer.parseInt(option) > 0) && (Integer.parseInt(option) < 9)) {
					taskList[Integer.parseInt(option) - 1].drawTask(args);
					break;
				}
			} catch (Exception e) {
				System.out.println("Неверный ввод номера задачи");
			}

		}
	}


	/**<p>Вывод списка задач для выполнения с данными из командной строки</p>
	 * Отрисовывает таблицу составленную из массива задач
	 * @param taskList массив задач для перехода
	 */
	private static void drawPreMenuTable(Task[] taskList) {
		String menu = " ╔══════════════════════════════════════╦═════════════════════════════════════╗\r\n";
		for (int i = 0; i < 4; i++) {
			menu += " ║" + String.format("%02d", taskList[i].getId()) + "." + taskList[i].getShortName() + getNeededSpaces(taskList[i].getShortName()) 
					 + " ║" + String.format("%02d", taskList[i + 4].getId()) + "." + taskList[i + 4].getShortName() + getNeededSpaces(taskList[i + 4].getShortName()) + "║\r\n";
		}
		menu += " ╚══════════════════════════════════════╩═════════════════════════════════════╝";
		System.out.println(menu);
	}


	/**
	 * Выводит на консоль заглавие таблицы выбора задачи
	 */
	private static void drawPreMenuTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
							" ║                Выберите задачу которую следует решить, используя           ║\r\n" +
							" ║                     данные из параметров командной строки                  ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");

		
	}


	/**<p>Вывод списка задач для выполнения</p>
	 * Отрисовывает таблицу составленную из массива задач
	 * @param taskList массив задач для перехода
	 */
	private static void printMenu(Task[] taskList) {
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

	/**Вспомогательный метод для подсчёта и вывода необходимого количества пробелов для выравнивания таблицы выбора
	 * @param string строка таблицы для определения необходимой длины пробелов
	 * @return строка из пробелов необходимой длины
	 */
	private static String getNeededSpaces(String string) {
		String spaces = "";
		for (int i = 0; i < (34 - string.length()); i++ ) {
			spaces = spaces + " ";
		}
		return spaces;
	}


	/**<p>Формирование списка задач</p>
	 * Формируется список задач для перехода из меню. 
	 * @return массив задач
	 */
	private static Task[] createTaskList() {
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
				taskList[14] = new MatrixSedDots("Найти седловые точки матрицы", getStringsFromFile("task15.txt"));
				taskList[15] = new MatrixBuildRisingRows("Перестройка возрастающей матрицы", getStringsFromFile("task16.txt"));
				taskList[16] = new MatrixLocalMin("Найти локальные минимумы матрицы", getStringsFromFile("task17.txt"));
				taskList[17] = new MatrixBigestLocalMax("Локальный максимум матрицы", getStringsFromFile("task18.txt"));
				taskList[18] = new MatrixCharacterLowering("Уменьшение характеристик матрицы", getStringsFromFile("task19.txt"));
				taskList[19] = new MatrixMainDiagFill("Заполнение главной диагонали", getStringsFromFile("task20.txt"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		return taskList;
		
	}


	/**<p>Загрузка строк из файла</p>
	 * Считывает построчно текстовый файл указанный в параметре метода и
	 * формирует массив из этих строк. В случае если файл пуст формирует 
	 * массив из одного пустого элемента
	 * @param file название файла для загрузки данных
	 * @return массив строк считанных из файла
	 * @throws IOException при ошибке Ввода/Вывода
	 */
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


	/**
	 * Выводит на экран заглавие программы
	 */
	public static void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║                  Добро пожаловать в программу демонстрирующую              ║\r\n" +
				 			" ║                     решение задач Теста №1 от <epam> Systems               ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
	}
	
	

}
