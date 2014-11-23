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
}
