package ru.epam.task2.gui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

/**<p>Абстрактный класс задания</p>
 * Содержит конструкторы общего вида с получением массива строк и без.
 * Содержит методы по ожиданию нажатия Enter пользователем, общие методы для 
 * работы с матрицами, а также метод печатающий пустые строки
 * @author Туркин А.К.
 *
 */
public abstract class Task {
	
	private static int nextId = 1;
	
	private final int id;
	private final String shortName;
	private String[] incomingStrings;
	
	/**<p>Конструктор задачи с получением данных из запроса пользователю</p>
	 * В конструкторе не инициализируется массив строк, так как этот тип заданий не
	 * получает данные из файла, а получает начальное условие задачи из данных введенных
	 * пользователем.
	 * @param shortName сокращенное наименование задания для отрисовки в таблице выбора задания
	 */
	protected Task(String shortName) {
		id = nextId;
		nextId++;
		this.shortName = shortName;
	}
	
	/**<p>Конструктор задачи с получением данных из файла</p>
	 * В конструкторе инициализируется массив строк содержащим начальный набор данных для выполнения задания. 
	 * Конструктор используется для заданий с получением данных из файла 
	 * @param shortName сокращенное наименование задания для отрисовки в таблице выбора задания
	 * @param incomingStrings массив строк содержащий начальное условие для решения задачи
	 */
	protected Task(String shortName, String[] incomingStrings) {
		id = nextId;
		nextId++;
		this.shortName = shortName;
		this.incomingStrings = incomingStrings;
	}
	
	/**<p>Геттер поля <code>id</code></p>
	 * Поле <code>id</code> это порядковый номер задачи в таблице, по вводу которого происходит выбор задания.
	 * @return индентификатор задания
	 */
	public int getId() {
		return id;
	}

	/**<p>Геттер поля <code>shortName</code></p>
	 * Поле <code>shortName</code> это сокращенное наименование задания для отрисовки в таблице выбора задания.
	 * @return краткое наименование задания
	 */
	public String getShortName() {
		return shortName;
	}

	/**<p>Метод отрисовки жизненного цикла задания</p>
	 * Метод выполняет общие операции для отрисовки задания. Метод отрисовывает 
	 * текст задания и правила его выполнения, затем в консоль выводится требуемое значение пустых строк, 
	 * чтобы при перерисовки окна панели остались на своих местах и ожидает ввода Enter. В случае если введено 
	 * значение "0" просходит переход в меню без выполнения задания. В противном случае управление передаётся 
	 * абстрактному методу <code>doLogic()</code>   
	 */
	public void drawTask() {
		Scanner in = new Scanner(System.in);
		while (true) {
			drawTitle();
			Task.printEmptyLines(15);
			System.out.print(" Введите любую строку для продолжения (0 для выхода в меню)");
			try {
				String option = in.nextLine();
				if (option.equalsIgnoreCase("0")) {
					break;
				} else {
					drawTitle();
					doLogic();
					pressAnyKeyForMenu();
					break;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	/**
	 * Метод выполняющий основные вычисления в задании
	 */
	protected abstract void doLogic();

	/**
	 * Метод выводящий на экран панель с наименованием задания и правилами его решения
	 */
	protected abstract void drawTitle();

	/**<p>Метод отрисовки тела задания в случае получения данных из командной строки</p> 
	 * Метод инициализирует массив строк полученным массивом аргументов командной строки и 
	 * передает управление методу отрисовки задания без входных параметров
	 * @param strings массив строк полученный из аргументов командной строки
	 * @see #drawTask()
	 */
	public void drawTask(String[] strings) {
		incomingStrings = strings;
		drawTask();
	}
	
	/**<p>Ожидание ввода строки (нажатия клавиши Enter)</p>
	 * Метод ожидает на ввод любую строку. При ожидании выводится 
	 * сообщение о последующем переходе в меню. Обычно используется при окончании задания 
	 */
	protected static void pressAnyKeyForMenu() {
		System.out.print(" Нажмите Enter для выхода в меню");
		try {
			Scanner in = new Scanner(System.in);
			in.nextLine();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**<p>Ожидание ввода строки (нажатия клавиши Enter)</p>
	 * Метод ожидает на ввод любую строку. При ожидании выводится 
	 * сообщение о последующем продолжении выполнения программы. 
	 * Обычно используется чтобы дать возможность пользователю 
	 * прочитать задание
	 */
	protected void pressAnyKey() {
		System.out.print(" Нажмите Enter для продолжения");
		try {
			Scanner in = new Scanner(System.in);
			in.nextLine();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**<p>Вывод в консоль пустых строк</p>
	 * Метод выводит в консоль заданное количество пустых строк
	 * @param countEmptyLines количество пустых строк для вывода
	 * @since 1.5
	 */
	public static void printEmptyLines(int countEmptyLines) {
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < countEmptyLines; i++) {
			sb.append("\r\n");
		}
		System.out.println(sb);
	}
	
	/**<p>Геттер поля <code>incomingStrings</code></p>
	 * Поле <code>incomingStrings</code> это массив строк содержащий исходные данные для решения задачи
	 * @return массив строк
	 */
	protected String[] getStrings() {
		return incomingStrings;
	}
	
	/**<p>Получение матрицы из массива строк</p>
	 * Метод возвращает матрицу в виде двухмерного массива считанную из массива строк.
	 * Строки матрицы соответствуют строкам массива. Разделение по столбцам происходит 
	 * по пробелам между числами. Метод использует парсинг отдельных слов в числа типа 
	 * <code>double</code>. В случае если строки матрицы имеют неодинаковую длину или
	 * слова в строке содержать некорректный формат чисел, преобразование в матрицу 
	 * считается некорректным и возвращается матрица нулевой длины    
	 * @return матрица полученная из массива строк
	 */
	protected double[][] getMatrixFromStringArray() {
		ArrayList<Double> doublelist = new ArrayList<Double>();
		double[][] array = new double[incomingStrings.length][];
		try {
			for (int i = 0; i < array.length; i++) {
				String[] words = incomingStrings[i].split("\\s");
				array[i] = new double[words.length]; 
				for (int j = 0; j < array[i].length; j++) {
					array[i][j] = Double.parseDouble(words[j]);
					if (Double.isNaN(array[i][j])) {
						throw new NumberFormatException("С числами типа NaN вычисления не имеют смысла");
					}
				}
			}			
			if (array.length > 1) {
				for (int i = 0; i < array.length - 1; i++) {
					if (array[i].length != array[i + 1].length) {
						return new double[0][0];
					}
				}
			}
		} catch (Exception e) {
			return new double[0][0];
		}
		return array;
	}
	
	/**<p>Преобразование массива строк в массив слов</p>
	 * Метод используя данные в виде массива строк из поля <code>incomingStrings</code>
	 * формирует массив слов разделяя их в строке пробелами
	 * @return массив слов
	 */
	protected String[] getWordsFromStringArray() {
		ArrayList<String> wordsList = new ArrayList<String>();
		for (String string : incomingStrings) {
			String[] stringOfWords = string.split("\\s");
			for (String word : stringOfWords) {
				wordsList.add(word);
			}
		}
		String[] wordsArray = new String[wordsList.size()];
		return wordsList.toArray(wordsArray);
	}
	
	protected String[] getWordsFromString(String string) {
		return string.split("\\s");
	}
	
	/**<p>Вывод матрицы в консоль в стандартном виде</p>
	 * Выводит содержимое матрицы десятично дробных чисел в консоль в стандартном виде. На число отводится 8 цифр, 
	 * в том числе округленная до сотых дробная часть
	 * @param matrix матрица для вывода на консоль
	 */
	protected void printMatrix(double[][] matrix) {
		Formatter f = new Formatter();
		for (int i = 0; i < matrix.length; i++) {
			f.format(" [ ", "");
			for (int j = 0; j < matrix[i].length; j++) {
				f.format("%#8.2f", matrix[i][j], " ");
			}
			f.format(" ]%n", "");
		}
		System.out.println(f);
	}
	
	protected static boolean writeStringsToFile(String file, String[] strings) {
		if (strings.length == 0) {
			strings = new String[1];
			strings[0] = "";
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("./" + file));
			for (int i = 0; i < strings.length - 1; i++) {
				writer.write(strings[i]);
				writer.newLine();
			}
			writer.write(strings[strings.length - 1]);
			writer.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	protected String askOption(String message) {
		System.out.print(message);
		try {
			Scanner in = new Scanner(System.in);
			return in.nextLine();
		} catch (Exception e) {
			return null;
		}
	}
	
	protected void drawSreen(Object obj) {
		drawTitle();
		int stringsCount = drawStructure(obj);
		printEmptyLines(15 - stringsCount);
	}

	protected int drawStructure(Object obj) {
		String[] strings = splitStringEvery(obj.toString(), 79);
		for (String string : strings) {
			System.out.println(string);
		}
		return strings.length;
	}
	
	protected String[] splitStringEvery(String s, int interval) {
	    int arrayLength = (int) Math.ceil(((s.length() / (double)interval)));
	    String[] result = new String[arrayLength];
	    if (result.length == 0) {
	    	return result;
	    }
	    int j = 0;
	    int lastIndex = result.length - 1;
	    for (int i = 0; i < lastIndex; i++) {
	        result[i] = s.substring(j, j + interval);
	        j += interval;
	    }
	    result[lastIndex] = s.substring(j);
	    return result;
	}
	
	protected void printError(Object obj, String string) {
		drawSreen(obj);
		System.out.print(string + " ");
		pressAnyKey();
	}
}
