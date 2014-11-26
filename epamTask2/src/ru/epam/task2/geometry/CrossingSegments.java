package ru.epam.task2.geometry;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import ru.epam.task2.gui.Task;

import compgeom.RLineSegment2D;
import compgeom.RPoint2D;

/**
 * <p>Класс реализующий задание № 19</p>
 * Класс отрисовывает задание и вычисляет точку пересечения заданных отрезков с наименьшей абсциссой. Класс использует для расчетов библиотеку из 
 * <code>CompGeom-0.3.jar</code>
 * @author Туркин А.К.
 */
public class CrossingSegments extends Task {
	
	/**
	 * Набор отрезков реализованных классом <code>RLineSegment2D</code>
	 */
	private Set<RLineSegment2D> setOfSegments;

	/**
	 * Конструктор передает наследуемому классу краткое описание и исходные данные по входящим параметрам
	 * @param shortName краткое описание задания
	 * @param incomingStrings массив исходных данных
	 */
	public CrossingSegments(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);

	}

	/**
	 * Метод проверяет корректность инициализации списка отрезков по входящим данным. В случае ошибки метод информирует об этом пользователя.
	 * В случае корректных данных метод выводит на экран точку пересечения отрезков с наименьшей абсциссой
	 */
	@Override
	protected void doLogic() {
		if (!initSetOfSegments(getStrings())) {
			drawTitle();
			System.out.println(" Ошибка чтения отрезков из файла!\r\n" 
							 + " Отрезки в файле должны быть записаны построчно\r\n" 
							 + " в формате Целое,Целое;Целое,Целое");
			printEmptyLines(12);
			return;
		}
		Set<RPoint2D> set = BentleyOttmann.intersections(setOfSegments);
		Map<RPoint2D, RPoint2D> map = new TreeMap<RPoint2D, RPoint2D>(new RPoint2DComparator());
		for (RPoint2D rPoint2D : set) {
			map.put(rPoint2D, rPoint2D);
		}
		drawTitle();
		System.out.println(" Точка пересечения отрезков с наименьшей абсциссой это: " 
				+ Collections.min(map.keySet(), new RPoint2DComparator()).toString());
		printEmptyLines(14);
	}

	/**
	 * Метод проверяет на корректность и инициализирует набор отрезков по входящим данным
	 * @param segments входящие данные отрезков в виде строкового массива
	 * @return <code>true</code> в случае успешного преобразования данных, иначе <code>false</code>
	 */
	private boolean initSetOfSegments(String[] segments) {
		this.setOfSegments = new HashSet<RLineSegment2D>(); 
		try {
			for (String segment : segments) {
				String[] dots = segment.split(";");
				if (dots.length != 2) return false;
				String[] coordinates;
				RPoint2D[] rPoint2D = new RPoint2D[2];
				for (int i = 0; i < dots.length; i++) {
					coordinates = dots[i].split(",");
					if (coordinates.length != 2) return false;
					rPoint2D[i] = new RPoint2D(Long.parseLong(coordinates[0]), Long.parseLong(coordinates[1]));
				}
				setOfSegments.add(new RLineSegment2D(rPoint2D[0], rPoint2D[1]));
			}			
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see ru.epam.task2.gui.Task#drawTitle()
	 */
	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║ Задача №19. Из отрезков на плоскости найти точку их пересечения у которой  ║\r\n" +
				 			" ║              наименьшая абсцисса. Использовать класс TreeMap               ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║          Отрезки считаны из файла task19.txt построчно в формате           ║\r\n" +
				 			" ║             Целое,Целое;Целое,Целое  Ответ выводится на экран              ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");	
	}
	
	/**
	 * Компаратор для точек для сравнения их абсцисс
	 * @author Туркин А.К.
	 */
	private class RPoint2DComparator implements Comparator<RPoint2D> {

		@Override
		public int compare(RPoint2D o1, RPoint2D o2) {
			if( null != o1 && null != o2 ) { 
				return o1.x.abs().compareTo(o2.x.abs());
			}
			if( null == o1 && null == o2 ) {
				return 0;
			}
			if( null == o1 ){
				return -1;
			} else {			
				return 1;
			}
		}
		
	}
}
