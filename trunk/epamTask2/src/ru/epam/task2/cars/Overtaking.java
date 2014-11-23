package ru.epam.task2.cars;

public class Overtaking {
	
	private final Car firstCar;
	private final Car secondCar;
	
	/**
	 * @param firstCar
	 * @param secondCar
	 */
	public Overtaking(Car firstCar, Car secondCar) {
		this.firstCar = firstCar;
		this.secondCar = secondCar;
	}

	/**
	 * @return the firstCar
	 */
	public Car getFirstCar() {
		return firstCar;
	}

	/**
	 * @return the secondCar
	 */
	public Car getSecondCar() {
		return secondCar;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result1 = prime + ((firstCar == null) ? 0 : firstCar.hashCode());
		int result2 = prime + ((secondCar == null) ? 0 : secondCar.hashCode());
		return Integer.valueOf(result1 + result2).hashCode();
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
		if (!(obj instanceof Overtaking))
			return false;
		Overtaking other = (Overtaking) obj;
		if (firstCar == null && secondCar == null
				&& other.firstCar == null && other.secondCar == null) {
			return true;
		} else if (firstCar == null && secondCar != null) {
			if ((other.firstCar == null && secondCar.equals(other.secondCar))
					|| (other.secondCar == null && secondCar.equals(other.firstCar))) {
				return true;
			} else {
				return false;
			}
		} else if (firstCar != null && secondCar == null) {
			if ((firstCar.equals(other.firstCar) && other.secondCar == null) 
					|| (firstCar.equals(other.secondCar) && other.firstCar == null)) {
				return true;
			} else {
				return false;
			}
		} else if ((firstCar.equals(other.firstCar) && secondCar.equals(other.secondCar))
					|| (firstCar.equals(other.secondCar) && secondCar.equals(other.firstCar))) {			
			return true;
		} else {
			return false;
		}
	}
	
	
	
}
