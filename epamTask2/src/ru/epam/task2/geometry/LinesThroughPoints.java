package ru.epam.task2.geometry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ru.epam.task2.gui.Task;

/**
 * <p>Класс реализующий задание № 17</p>
 * Класс расчитывает все возможные прямые построенные по точкам из входных данных и записывает в файл только
 * те прямые, которые проходят больше чем через 2 точки
 * @author Туркин А.К.
 */
public class LinesThroughPoints extends Task {
	private List<Dot> dotsList;

	/**
	 * Конструктор передает наследуемому классу краткое описание и исходные данные по входящим параметрам
	 * @param shortName краткое описание задания
	 * @param incomingStrings массив исходных данных
	 */
	public LinesThroughPoints(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	/**
	 * Метод содержит тело задачи. Метод проверяет корректность входящих данных. Если данные считались с ошибками, то метод информирует
	 * об этом пользователя и завершает работу. В случае корректных данных метод проверяет количество элементов в списке точек. Если точек 
	 * не менее 3 метод продолжает вычисления. Далее метод выводит коллекцию прямых проходящих более чем через 2 точки из заданных в 
	 * файл и завершает свою работу
	 */
	@Override
	protected void doLogic() {
		if (!getDotsFromLines(getStrings())) {
			drawTitle();
			System.out.println(" Ошибка чтения данных из файла. Точки должны быть записаны \n\r в файл построчно в формате Целое,Целое");
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
		System.out.println(" Перечень прямых проходящих больше чем через 2 из заданых точек выведен\n\r в файл task17output.txt");
		printEmptyLines(13);
		writeStringsToFile("task17output.txt", mapToStringArray(linesMap));
	}

	/**
	 * Метод преобразует коллекцию прямых в строковый массив используя строковую интерпретацию из класса <code>Line</code>
	 * @param linesMap коллекция прямых
	 * @return строковый массив с описанием прямых
	 */
	private String[] mapToStringArray(Map<Integer, Line> linesMap) {
		String[] strings = new String[linesMap.size()];
		int index = 0;
		for (Line mapValue : linesMap.values()) {
			strings[index] = mapValue.toString();
			index++;
		}
		return strings;
	}

	/**
	 * Метод перебирает все возможные прямые построенные по переданным точкам и записывает в коллекцию только
	 * те прямые которые проходят более чем через 2 точки
	 * @param dotsList список точек из начальных данных
	 * @return коллекция прямых проходящих более чем через 2 точки из заданых
	 */
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
					// пропускаем при переборе точки по которым построена прямая и те которые уже проверялись на принадлежность
					if (dotsList.get(j).equals(dot) || dotsList.get(j).equals(dotsList.get(i))) {
						continue;
					}
					if (line.hasDotByCoordinates(dotsList.get(j).getX(), dotsList.get(j).getY())) {
						line.incrementDotsCount();
						line.addDot(dotsList.get(j));
					}
				}
				if ((line.getDotsCount() > 2) && hasNoProportionalLines(lines, line)) {
					lines.put(line.hashCode(), line);
				}
			}
		}
		return lines;
	}

	/**
	 * Метод проверяет наличие указанной прямой в указанной коллекции прямых
	 * @param lines коллекция прямых
	 * @param otherLine прямая проверяемая на принадлежность
	 * @return <code>true</code> если прямая уже есть в коллекции и <code>false</code> если такой прямой нет 
	 */
	private boolean hasNoProportionalLines(Map<Integer, Line> lines, Line otherLine) {
		for (Line line : lines.values()) {
			if (line.isProportionalToAnotherLine(otherLine)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Метод считавает исходные данные из параметра и в случае отсутствия ошибок записывает их в виде точек в список <code>dotsList</code>
	 * @param strings массив строк из исходных данных
	 * @return <code>true</code> если данные считались без ошибок, иначе <code>false</code>
	 */
	private boolean getDotsFromLines(String[] strings) {
		if (strings.length == 0) {
			return false;
		}
		Set<Dot> dots = new HashSet<Dot>();
		try {
			for (String string : strings) {
				String[] dot = string.split(",");
				if (dot.length != 2) {
					return false;
				}
				dots.add(new Dot(Integer.parseInt(dot[0]), Integer.parseInt(dot[1])));
			}
		} catch (Exception e) {
			return false;
		}
		this.dotsList = new ArrayList<Dot>(dots);
		return true;
	}

	/* (non-Javadoc)
	 * @see ru.epam.task2.gui.Task#drawTitle()
	 */
	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║ Задача №17. По заданым координатам точек на плоскости вывести в файл описа-║\r\n" +
				 			" ║  ние прямых проходящих более чем через 2 точки и указать количество точек  ║\r\n" +
		 					" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║    Координаты точек считываются построчно из файла task17.txt в формате    ║\r\n" +
				 			" ║      Целое,Целое Уравнение прямых записываются в файл task17output.txt     ║\r\n" +
		 					" ╚════════════════════════════════════════════════════════════════════════════╝");
	}
	

	/**
	 * Класс реализующий прямую на двумерной плоскости
	 * @author Туркин А.К.
	 */
	public class Line {
		
		/**
		 * Список точек которые уже проверялись на принадлежность текущей прямой
		 */
		private final List<Dot> dotsContains;
		
		/**
		 * коэффициент при <code>y</code> в уравнении <code>ay=kx+b</code>
		 */
		private double a;
		
		/**
		 * коэффициент свободного члена в уравнении <code>ay=kx+b</code>
		 */
		private double b;
		
		/**
		 * коэффициент при <code>x</code> в уравнении <code>ay=kx+b</code>
		 */
		private double k;
		
		/**
		 * Количество заданых точек которые содержит текущая прямая
		 */
		private int dotsCount;
		
		/**
		 * Конструктор для вычисления коэфициентов прямой построенной по двум точкам. Уравнение прямой по двум точкам
		 * имеет вид <code>(1/(y2-y1))y=(1/(x2-x1))x+(y1/(y2-y1)-x1/(x2-x1))</code> или <code>ay=kx+b</code>
		 * @param dotA первая точка для построения прямой
		 * @param dotB вторая точка для построения прямой
		 */
		public Line(Dot dotA, Dot dotB) {
			double x1 = dotA.getX();
			double y1 = dotA.getY();
			double x2 = dotB.getX();
			double y2 = dotB.getY();
			// ситуация когда прямая вида x=число			
			if (x1 == x2) {
				a = 0d;
				k = 1d;
				b = 0d - x1;
			// ситуация когда прямая вида y=число
			} else if (y1 == y2) {
				a = 1d;
				k = 0d;
				b = y1;
			} else {				
				a = 1d / (y2 - y1);
				k = 1d / (x2 - x1);
				b = (y1 / (y2 - y1)) - (x1 / (x2 - x1));
			}
			this.dotsCount = 2;
			this.dotsContains = new ArrayList<Dot>();
			this.dotsContains.add(dotA);
			this.dotsContains.add(dotB);
		}
		
		/**
		 * Метод проверяет идентичность двух прямых по уравнениям этих прямых
		 * @param otherLine другая прямая для сравнения с текущей
		 * @return <code>true</code> если прямые идентичны, иначе <code>false</code>
		 */
		public boolean isProportionalToAnotherLine(Line otherLine) {
			// прямые точно не идентичны если один из пары коэффициентов равен 0
			if ((b != otherLine.getB()) && ((b == 0) || (otherLine.getB() == 0))) {
				return false;
			} else if ((a != otherLine.getA()) && ((a == 0) || (otherLine.getA() == 0))) {
				return false;
			} else if ((k != otherLine.getK()) && ((k == 0) || (otherLine.getK() == 0))) {
				return false;
			}
			
			// случай если прямые вида ay=kx
			if (b == 0) {
				if ((k == 0) || (a == 0)) {
					return true;
				} else {
					return (a / otherLine.getA()) == (k / otherLine.getK());
				}
			}
			
			// случай когда прямые вида ay=b
			if (k == 0) {
				if ((a == 0) || (b == 0)) {
					return true;
				} else {
					return (a / otherLine.getA()) == (b / otherLine.getB());
				}
			}
			
			// случай когда прямые вида kx=b
			if (a == 0) {
				if ((k == 0) || (b == 0)) {
					return true;
				} else {
					return (k / otherLine.getK()) == (b / otherLine.getB());
				}
			}
			
			// случай когда прямые вида ay=kx+b
			return (a / otherLine.getA()) == (k / otherLine.getK()) 
				&& (a / otherLine.getA()) == (b / otherLine.getB()) 
				&& (k / otherLine.getK()) == (b / otherLine.getB());
		}

		/**
		 * Метод добавляет переданную точку в список точек из исходных данных которые лежат на текущей прямой
		 * @param dot добавляемая точка
		 */
		public void addDot(Dot dot) {
			if (!this.dotsContains.contains(dot)) {
				this.dotsContains.add(dot);
			}
		}
		
		/**
		 * Метод проверяет содержит ли список точек <code>dotsContains</code> указанную точку
		 * @param dot точка проверяемая на содержание в списке
		 * @return <code>true</code> если указанная точка присутствует в списке, иначе <code>false</code>
		 */
		public boolean hasDot(Dot dot) {
			return this.dotsContains.contains(dot);
		}
		
		/**
		 * Метод проверяет лежит ли точка по указанным координатам на текущей прямой
		 * @param x абсцисса точки
		 * @param y ордината точки
		 * @return <code>true</code> если точка лежит на прямой, иначе <code>false</code>
		 */
		public boolean hasDotByCoordinates(int x, int y) {
			return (a * y) == ((k * x) + b);
		}
		
		/**
		 * Метод увеличивает значение поля <code>dotsCount</code> на 1
		 */
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
			return "(" + String.format("%#8.2f", a) + ")*y = (" + String.format("%#8.2f", k) + ")*x + (" + String.format("%#8.2f", b) + ") [" 
					+ dotsCount + "]";
		}

		
		
	}
	
	
}
