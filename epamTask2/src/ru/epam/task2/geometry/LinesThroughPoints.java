package ru.epam.task2.geometry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ru.epam.task2.gui.Task;

public class LinesThroughPoints extends Task {

	public LinesThroughPoints(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	@Override
	protected void doLogic() {
		List<Dot> dotsList = getDotsFromLines(getStrings());
		if (dotsList.equals(null)) {
			drawTitle();
			System.out.println(" Ошибка чтения данных из файла. Точки должны быть записаны \n\r" 
					+ " в файл построчно в формате Целое,Целое");
			printEmptyLines(13);
			return;
		} else if (dotsList.size() < 3) {
			drawTitle();
			System.out.println(" На основании данных из файла невозможно построить ни одной прямой");
			printEmptyLines(14);
			return;
		}
		Map<Integer, Line> linesMap = getLinesMapFromDotsList(dotsList);
		drawTitle();
		System.out.println(" Перечень прямых проходящих больше чем через 2 из заданых точек выведен в файл task17output.txt");
		printEmptyLines(14);
		writeStringsToFile("task17output.txt", mapToStringArray(linesMap));
	}

	private String[] mapToStringArray(Map<Integer, Line> linesMap) {
		String[] strings = new String[linesMap.size()];
		int index = 0;
		for (Line mapValue : linesMap.values()) {
			strings[index] = mapValue.toString();
			index++;
		}
		return strings;
	}

	private Map<Integer, Line> getLinesMapFromDotsList(List<Dot> dotsList) {
		Map<Integer, Line> lines = new HashMap<Integer, Line>();
		for (Dot dot : dotsList) {
			for (int i = 0; i < dotsList.size(); i++) {
				// перебираем все точки кроме текущей 
				if (dot.equals(dotsList.get(i))) { 
					continue;
				}
				Line line = new Line(dot, dotsList.get(i));
				for (int j = 0; j < dotsList.size(); j++) {
					// пропускаем при переборе точки по которым построена прямая 
					if (dotsList.get(j).equals(dot) || dotsList.get(j).equals(dotsList.get(i))) {
						continue;
					}
					if (line.hasDot(dotsList.get(j).getX(), dotsList.get(j).getY())) {
						line.incrementDotsCount();
					}
				}
				if (line.getDotsCount() > 2) {
					lines.put(line.hashCode(), line);
				}
			}
		}
		return lines;
	}

	private List<Dot> getDotsFromLines(String[] strings) {
		if (strings.length == 0) {
			return null;
		}
		Set<Dot> dots = new HashSet<Dot>();
		try {
			for (String string : strings) {
				String[] dot = string.split(",");
				if (dot.length != 2) {
					return null;
				}
				dots.add(new Dot(Integer.parseInt(dot[0]), Integer.parseInt(dot[1])));
			}
		} catch (Exception e) {
			return null;
		}
		return new ArrayList<Dot>(dots);
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
		 			" ║   Задача №15. Реализовать структуру хранения чисел с поддержкой операций   ║\r\n" +
		 			" ║        добавления, удаления и поиска ближайшего по значению элемента       ║\r\n" +
		 			" ╚════════════════════════════════════════════════════════════════════════════╝");
System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
		 			" ║ Добавление/удаление ребер происходит через запрос действия у пользователя: ║\r\n" +
		 			" ║         \"+\" добавление, \"-\" удаление, \"?\" поиск, 0 выход в меню.           ║\r\n" +
		 			" ╚════════════════════════════════════════════════════════════════════════════╝");
	}
	
	public class Dot {
		private final int x;
		private final int y;
		
		/**
		 * Конструктор для задания точки по координатам
		 * @param x абсцисса точки
		 * @param y ордината точки
		 */
		public Dot(int x, int y) {
			this.x = x;
			this.y = y;
		}

		/**
		 * @return the x
		 */
		public int getX() {
			return x;
		}

		/**
		 * @return the y
		 */
		public int getY() {
			return y;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Dot))
				return false;
			Dot other = (Dot) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		
	}

	public class Line {
		
		/**
		 * коэффициент при <code>y</code> в уравнении <code>ay=kx+b</code>
		 */
		private final double a;
		
		/**
		 * коэффициент свободного члена в уравнении <code>ay=kx+b</code>
		 */
		private final double b;
		
		/**
		 * коэффициент при <code>x</code> в уравнении <code>ay=kx+b</code>
		 */
		private final double k;
		
		private int dotsCount;
		
		/**
		 * Конструктор для вычисления коэфициентов прямой построенной по двум точкам. Уравнение прямой по двум точкам
		 * имеет вид <code>(1/(y2-y1))y=(1/(x2-x1))x+(y1/(y2-y1)-x1/(x2-x1))</code> или <code>ay=kx+b</code>
		 * @param dotA первая точка для построения прямой
		 * @param dotB вторая точка для построения прямой
		 */
		public Line(Dot dotA, Dot dotB) {
			int x1 = dotA.getX();
			int y1 = dotA.getY();
			int x2 = dotB.getX();
			int y2 = dotB.getY();
			// ситуация когда прямая вида x=число			
			if (x1 == x2) {
				a = 0;
				k = 1;
				b = x1;
			// ситуация когда прямая вида y=число
			} else if (y1 == y2) {
				a = 1;
				k = 0;
				b = y1;
			} else {				
				a = 1 / (y2 - y1);
				k = 1 / (x2 - x1);
				b = (y1 / (y2 - y1)) - (x1 / (x2 - x1));
			}
			dotsCount = 2;
		}
		
		public boolean hasDot(int x, int y) {
			return (a * y) == ((k * x) + b);
		}
		
		public void incrementDotsCount() {
			dotsCount++;
		}

		/**
		 * @return the a
		 */
		public double getA() {
			return a;
		}

		/**
		 * @return the b
		 */
		public double getB() {
			return b;
		}

		/**
		 * @return the k
		 */
		public double getK() {
			return k;
		}

		/**
		 * @return the dotsCount
		 */
		public int getDotsCount() {
			return dotsCount;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			long temp;
			temp = Double.doubleToLongBits(a);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			temp = Double.doubleToLongBits(b);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			temp = Double.doubleToLongBits(k);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Line))
				return false;
			Line other = (Line) obj;
			if (Double.doubleToLongBits(a) != Double.doubleToLongBits(other.a))
				return false;
			if (Double.doubleToLongBits(b) != Double.doubleToLongBits(other.b))
				return false;
			if (Double.doubleToLongBits(k) != Double.doubleToLongBits(other.k))
				return false;
			return true;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "(" + String.format("%#8.2f", a) + ")*y = (" + String.format("%#8.2f", k) + ")*x + (" + String.format("%#8.2f", b) + ")";
		}

		
		
	}
	
	
}
