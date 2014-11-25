package ru.epam.task2.cars;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>Служебный класс набор разных обгонов</p>
 * Предназначен для хранения разных обгонов и обладает методами для работы с ними
 * @author Туркин А.К.
 */
public class OvertakingSet {
	
	/**
	 * набор обгонов
	 */
	private final Set<Overtaking> overSet;
	
	/**
	 * Коструктор инициализирующий набор обгонов
	 */
	public OvertakingSet() {
		overSet = new HashSet<Overtaking>();
	}
	
	/**
	 * Метод добавляет обгон в набор по правилам добавления елемента в коллекцию <code>HashSet</code>
	 * @param over объект обгона
	 */
	public void addOvertakоing(Overtaking over) {
		overSet.add(over);
	}
	
	/**
	 * Метод возвращает размер набора обгонов
	 * @return размер набора обгонов
	 */
	public int getOverCount() {
		return overSet.size();
	}
	
	/**
	 * Метод делает перебор по всему переданному списку машин сравнивая каждую машину с каждой. В случае если стартовая 
	 * позиция первой машины раньше чем второй и скорость первой машины больше чем второй, то обгон обязательно случится, 
	 * соответственно метод добавляет объект такого обгона в набор  
	 * @param carList список машин
	 */
	public void setAllOvertakings(List<Car> carList) {
		for (Car car : carList) {
			for (Car otherCar : carList) {
				if ((car.getStartPos() < otherCar.getStartPos()) 
						&& car.getVelocity() > otherCar.getVelocity()) {
					overSet.add(new Overtaking(car, otherCar));
				}
			}
		}
	}
	
	/**
	 * Метод возращает строковую интерпретацию обгонов в виде строкового массива
	 * @return строковый массив обгонов
	 */
	public String[] getStrings() {
		List<String> stringList = new ArrayList<String>();
		for (Overtaking over : overSet) {
			stringList.add(" Машина c позиции " + over.getFirstCar().getStartPos() 
					+ " обогнала машину с позиции " + over.getSecondCar().getStartPos());
		}
		return stringList.toArray(new String[stringList.size()]);
	}

	/**
	 * Метод возращает первые k обгонов в соответствии с переданными параметрами
	 * @param carList список машин
	 * @param k требуемое количество первых обгонов
	 */
	public void setKfirstOvertakings(List<Car> carList, int k) {
		while (overSet.size() < k) {
			for (Car car : carList) {
				car.setFinWay(car.getStartWay() + car.getVelocity()); // fin = start + V * t, при t = 1
			}
			for (Car car : carList) {
				for (Car otherCar : carList) {
					if (car.equals(otherCar)) continue;
					if ((car.getStartWay() < otherCar.getStartWay()) 
							&& (car.getFinWay() >= otherCar.getFinWay())) {
						overSet.add(new Overtaking(car, otherCar));
						if (overSet.size() >= k) return;
					}
				}
			}
			for (Car car : carList) {
				car.setStartWay(car.getFinWay());
			}
			
			
		}
	}
}
