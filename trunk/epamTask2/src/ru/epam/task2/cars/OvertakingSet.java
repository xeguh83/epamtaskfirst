package ru.epam.task2.cars;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OvertakingSet {
	
	private final Set<Overtaking> overSet;
	
	public OvertakingSet() {
		overSet = new HashSet<Overtaking>();
	}
	
	public void addOvertaking(Overtaking over) {
		overSet.add(over);
	}
	
	public int getOverCount() {
		return overSet.size();
	}
	
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
	
	public String[] getStrings() {
		List<String> stringList = new ArrayList<String>();
		for (Overtaking over : overSet) {
			stringList.add(" Машина c позиции " + over.getFirstCar().getStartPos() 
					+ " обогнала машину с позиции " + over.getSecondCar().getStartPos());
		}
		return stringList.toArray(new String[stringList.size()]);
	}

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
