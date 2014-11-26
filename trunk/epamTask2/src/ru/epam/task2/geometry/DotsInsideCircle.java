package ru.epam.task2.geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

import ru.epam.task2.gui.Task;

/**
 * <p>Класс реализующий задание № 18</p>
 * Класс вычисляет и сохраняет в файл описание точек внутри заданного круга
 * @author Туркин А.К.
 */
public class DotsInsideCircle extends Task {
	
	/**
	 * Точка центра заданного круга
	 */
	private Dot circleCenter;
	/**
	 * Радиус заданного круга
	 */
	private int radius;
	
	/**
	 * Конструктор передает наследуемому классу краткое описание задачи
	 * @param shortName краткое описание задания
	 */
	public DotsInsideCircle(String shortName) {
		super(shortName);
	}

	/**
	 * Метод устанавливает задает круг по переданным параметрам, создает список точек лежащих к квадрате в который
	 * вписан заданый круг, затем исключает из списка точки которые лежат вне круга и выводит описание оставшихся точек в файл
	 */
	@Override
	protected void doLogic() {
		setCircleCenter();
		setRadius();
		List<CircleDot> squareDotsList = getDotsInSquareAroundCircle();
		PriorityQueue<CircleDot> queue = getDotsOnlyInCirlce(squareDotsList);
		writeStringsToFile("task18output.txt", queueToStringArray(queue));
		drawTitle();
		System.out.println(" Точки внутри заданного круга в порядке отдаления от центра\r\n" 
				+ " записаны в файл task18output.txt");
		printEmptyLines(13);
	}

	/**
	 * Метод преобразует очередь точек круга в массив строк для последующей записи в файл
	 * @param queue приоритетная очередь точек внутри круга
	 * @return строковый массив содержащий описания точек внутри круга
	 */
	private String[] queueToStringArray(PriorityQueue<CircleDot> queue) {
		String[] stringArray = new String[queue.size()];
		for (int i = 0; i < stringArray.length; i++) {
			stringArray[i] = queue.poll().toString();
		}
		return stringArray;
	}

	/**
	 * Метод исключающий из точек квадрата в который вписан круг, точки находящиеся вне круга
	 * @param squareDotsList список точек внутри квадрата в который вписан круг
	 * @return приоритетную очередь точек внутри круга
	 */
	private PriorityQueue<CircleDot> getDotsOnlyInCirlce(
			List<CircleDot> squareDotsList) {
		PriorityQueue<CircleDot> queue = new PriorityQueue<CircleDot>();
		for (CircleDot dot : squareDotsList) {
			if (dot.farFromCenter < radius) {
				queue.add(dot);
			}
		}
		return queue;
	}

	/**
	 * Метод формирует список точек квадрата в который вписан искомый круг. Квадрат строится исходя из центра круга и радиуса
	 * заданными полями <code>circleCenter</code> и <code>radius</code>
	 * @return список точек квадрата в который вписан искомый круг
	 */
	private List<CircleDot> getDotsInSquareAroundCircle() {
		int startX = circleCenter.getX() - radius;
		int finX = circleCenter.getX() + radius;
		int startY = circleCenter.getY() + radius;
		int finY = circleCenter.getY() - radius;
		List<CircleDot> list = new ArrayList<CircleDot>();
		for (int i = startX; i <= finX; i++) {
			for (int j = startY; j >= finY; j--) {
				list.add(new CircleDot(i, j, circleCenter.getX(), circleCenter.getY()));
			}
		}
		return list;
	}

	/**
	 * Метод циклично отрисовывает экран, в который входят панели задачи и текст запроса и ожидает от пользователя
	 * целое значение радиуса круга для записи его в поле <code>radius</code>
	 */
	private void setRadius() {
		while (true) {
			drawTitle();
			printEmptyLines(15);
			System.out.print(" Введите радиус круга в формате Целое : ");
			try {
				Scanner in = new Scanner(System.in);
				this.radius = Integer.parseInt(in.nextLine());
			} catch (Exception e) {
				continue;
			}
			return;
		}
	}

	/**
	 * Метод циклично отрисовывает экран, в который входят панели задачи и текст запроса и ожидает от пользователя
	 * значение координат центра круга в формате Целое,Целое которые в случае корректного ввода будут записаны в 
	 * поле <code>circleCenter</code>
	 */
	private void setCircleCenter() {
		while (true) {
			drawTitle();
			printEmptyLines(15);
			System.out.print(" Введите координаты центра круга в формате Целое,Целое : ");
			Dot circleCenter;
			try {
				Scanner in = new Scanner(System.in);
				String[] dot = in.nextLine().split(",");
				if (dot.length != 2) {
					continue;
				}
				circleCenter = new Dot(Integer.parseInt(dot[0]), Integer.parseInt(dot[1]));
			} catch (Exception e) {
				continue;
			}
			this.circleCenter = circleCenter;
			return;
		}
	}

	/* (non-Javadoc)
	 * @see ru.epam.task2.gui.Task#drawTitle()
	 */
	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║   Задача №18. Задан круг через координаты центра и радиус. Вывести в файл  ║\r\n" +
				 			" ║        описание точек внутри круга в порядке отдаления их от центра        ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║  Координаты центра круга и его радиус задаётся пользователем через запрос. ║\r\n" +
				 			" ║              Список точек записывается в файл task17output.txt             ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
	}
	
	/**
	 * Класс расширяющий класс <code>Dot</code> добавляющий поле <code>farFromCenter</code> для оценки лежит ли точка внутри круга
	 * @author Туркин А.К.
	 * @since 1.5
	 */
	public class CircleDot extends Dot implements Comparable<CircleDot> {

		/**
		 * расстояние от текущей точки до центра круга
		 */
		private double farFromCenter = 0;
		
		/**
		 * Конструктор задающий координаты точки по правилам класса <code>Dot</code> и задающий поле <code>farFromCenter</code>
		 * как гипотенузу по катетам абсциссы и ординаты от текущей точки до центра круга
		 * @param x абсцисса текущей точки
		 * @param y ордината текущей точки
		 * @param centerX абсцисса центра круга
		 * @param centerY ордината центра круга
		 * @since 1.5
		 */
		public CircleDot(int x, int y, int centerX, int centerY) {
			super(x, y);
			farFromCenter = Math.hypot(x - centerX, y - centerY);
			}

		/**
		 * Переопределение метода compareTo для вывода точек начиная с ближайших к центру. Метод сравнивает объекты точек только по 
		 * удаленности от центра
		 * @param other другая точка круга для сравнения
		 * @return -1 если текущая точка меньше другой точки, 1 если текущая точка больше другой точки и 0 если точки идентичны
		 */
		@Override
		public int compareTo(CircleDot other) {
	        if (this.farFromCenter < other.farFromCenter)
	            return -1;           // Если ни одно из чисел не NaN и текущее число меньше другого
	        if (this.farFromCenter > other.farFromCenter)
	            return 1;            // Если ни одно из чисел не NaN и текущее число больше другого

	        // Нельзя использовать doubleToRawLongBits из-за вероятности значения NaN
	        long thisBits    = Double.doubleToLongBits(this.farFromCenter);
	        long anotherBits = Double.doubleToLongBits(other.farFromCenter);

	        return (thisBits == anotherBits ?  0 : // Числа идентичны
	                (thisBits < anotherBits ? -1 : // (-0.0, 0.0) или (!NaN, NaN)
	                 1));                          // (0.0, -0.0) или (NaN, !NaN)
		}
	}
}
