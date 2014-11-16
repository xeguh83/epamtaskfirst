package ru.epam.task2.geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

import ru.epam.task2.gui.Task;

public class DotsInsideCircle extends Task {
	
	private Dot circleCenter;
	private int radius;
	
	public DotsInsideCircle(String shortName) {
		super(shortName);
	}

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

	private String[] queueToStringArray(PriorityQueue<CircleDot> queue) {
		String[] stringArray = new String[queue.size()];
		for (int i = 0; i < stringArray.length; i++) {
			stringArray[i] = queue.poll().toString();
		}
		return stringArray;
	}

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
	
	public class CircleDot extends Dot implements Comparable<CircleDot> {

		private double farFromCenter = 0;
		
		public CircleDot(int x, int y, int centerX, int centerY) {
			super(x, y);
			farFromCenter = Math.hypot(x - centerX, y - centerY);
			}

		@Override
		public int compareTo(CircleDot other) {
	        if (this.farFromCenter < other.farFromCenter)
	            return -1;           // Neither val is NaN, thisVal is smaller
	        if (this.farFromCenter > other.farFromCenter)
	            return 1;            // Neither val is NaN, thisVal is larger

	        // Cannot use doubleToRawLongBits because of possibility of NaNs.
	        long thisBits    = Double.doubleToLongBits(this.farFromCenter);
	        long anotherBits = Double.doubleToLongBits(other.farFromCenter);

	        return (thisBits == anotherBits ?  0 : // Values are equal
	                (thisBits < anotherBits ? -1 : // (-0.0, 0.0) or (!NaN, NaN)
	                 1));                          // (0.0, -0.0) or (NaN, !NaN)
		}
	}
}
