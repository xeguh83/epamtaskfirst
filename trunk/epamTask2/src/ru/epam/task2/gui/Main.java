package ru.epam.task2.gui;

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

import ru.epam.task2.cars.FirstKOvertakings;
import ru.epam.task2.cars.OvertakingCount;
import ru.epam.task2.collection.BlackBoxStructure;
import ru.epam.task2.collection.Graph;
import ru.epam.task2.collection.NumStorage;
import ru.epam.task2.collection.TwoSetsToOne;
import ru.epam.task2.geometry.CrossingSegments;
import ru.epam.task2.geometry.DotsInsideCircle;
import ru.epam.task2.geometry.LinesThroughPoints;
import ru.epam.task2.list.ArrayListVersusLinkedList;
import ru.epam.task2.list.SortSet;
import ru.epam.task2.math.LeastSquareMethod;
import ru.epam.task2.math.PairwiseSetSigma;
import ru.epam.task2.math.PolinomialAddition;
import ru.epam.task2.math.PolinomialMultiplication;
import ru.epam.task2.math.SetCalculating;
import ru.epam.task2.math.SquareSubMatrix;
import ru.epam.task2.string.BackOrderSortStrings;
import ru.epam.task2.string.DirElements;
import ru.epam.task2.string.SortPoemStrings;
import ru.epam.task2.word.BracketsCheck;
import ru.epam.task2.word.FreqDifWords;
import ru.epam.task2.word.SearchDifWords;


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
		Calendar begin = new GregorianCalendar(2014, 9, 24);
		String startingDate = Integer.toString(begin.get(Calendar.DAY_OF_MONTH)) 
				+ "." + Integer.toString(begin.get(Calendar.MONTH) + 1) 
				+ "." + Integer.toString(begin.get(Calendar.YEAR));
		Calendar fin = new GregorianCalendar(2014, 10, 10);
		String finishingDate = Integer.toString(fin.get(Calendar.DAY_OF_MONTH)) 
				+ "." + Integer.toString(fin.get(Calendar.MONTH) + 1) 
				+ "." + Integer.toString(fin.get(Calendar.YEAR));
		 System.out.print(" Начало задания:  " + startingDate + ",    Конец задания:  " + finishingDate);
	}

	/**<p>Вывод списка задач для выполнения</p>
	 * Отрисовывает таблицу составленную из массива задач
	 * @param taskList массив задач для перехода
	 */
	private static void printMenu(Task[] taskList) {
		String menu = " ╔══════════════════════════════════════╦═════════════════════════════════════╗\r\n";
		for (int i = 0; i < 11; i++) {
			menu += " ║" + String.format("%02d", taskList[i].getId()) + "." + taskList[i].getShortName() + getNeededSpaces(taskList[i].getShortName()) 
					 + " ║" + String.format("%02d", taskList[i + 11].getId()) + "." + taskList[i + 11].getShortName() + getNeededSpaces(taskList[i + 11].getShortName()) + "║\r\n";
		}
		menu += " ║" + String.format("%02d", taskList[22].getId()) + "." + taskList[22].getShortName() + getNeededSpaces(taskList[22].getShortName()) 
				+ " ║   " + getNeededSpaces("") + "║\r\n";
		menu += " ╚══════════════════════════════════════╩═════════════════════════════════════╝";
		System.out.println(menu);
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
		Task[] taskList = new Task[23];
			try {
				taskList[0] = new BackOrderSortStrings("Обратный порядок строк", getStringsFromFile("task1.txt"));
				taskList[1] = new DirElements("Список из элементов каталога");
				taskList[2] = new SortPoemStrings("Сортировка строк стихотворения", getStringsFromFile("task3.txt"));
				taskList[3] = new SetCalculating("Вычисление подмножеств", getStringsFromFile("task4.txt"));
				taskList[4] = new LeastSquareMethod("Поиск приближенного R", getStringsFromFile("task5.txt"));
				taskList[5] = new PairwiseSetSigma("Попарная сумма элементов множества", getStringsFromFile("task6.txt"));
				taskList[6] = new PolinomialAddition("Сложение многочленов", getStringsFromFile("task7.txt"));
				taskList[7] = new PolinomialMultiplication("Умножение многочленов", getStringsFromFile("task8.txt"));
				taskList[8] = new BracketsCheck("Проверка расстановки скобок", getStringsFromFile("task9.txt"));
				taskList[9] = new SearchDifWords("Поиск отличающихся слов (HashSet)", getStringsFromFile("task10.txt"));
				taskList[10] = new FreqDifWords("Поиск отличающихся слов (HashMap)", getStringsFromFile("task11.txt"));
				taskList[11] = new ArrayListVersusLinkedList("Выбывание каждого второго из круга");
				taskList[12] = new SortSet("Сортировка списка по возрастанию", getStringsFromFile("task13.txt"));
				taskList[13] = new Graph("Создание неориентированного графа");
				taskList[14] = new NumStorage("Структура для хранения чисел");
				taskList[15] = new TwoSetsToOne("Объединение двух списков в один", getStringsFromFile("task16.txt"));
				taskList[16] = new LinesThroughPoints("Построение линий по точкам", getStringsFromFile("task17.txt"));
				taskList[17] = new DotsInsideCircle("Поиск всех точек внутри круга");
				taskList[18] = new CrossingSegments("Нахождение пересечений отрезков", getStringsFromFile("task19.txt"));
				taskList[19] = new SquareSubMatrix("Нахождение подматрицы целых чисел", getStringsFromFile("task20.txt"));
				taskList[20] = new BlackBoxStructure("Структура черный ящик", getStringsFromFile("task21.txt"));
				taskList[21] = new OvertakingCount("Определение кол-ва обгонов", getStringsFromFile("task22.txt"));
				taskList[22] = new FirstKOvertakings("Кол-во первых К обгонов", getStringsFromFile("task23.txt"));
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
