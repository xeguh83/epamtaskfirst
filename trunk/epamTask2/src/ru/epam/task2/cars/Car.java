package ru.epam.task2.cars;

public class Car {
	
	private final Integer startPos;
	private final Integer velocity;
	
	/**
	 * @param startPos
	 * @param velocity
	 */
	
	public Car(Integer startPos, Integer velocity) {
		this.startPos = startPos;
		this.velocity = velocity;
	}

	/**
	 * @return the startPos
	 */
	public Integer getStartPos() {
		return startPos;
	}

	/**
	 * @return the velocity
	 */
	public Integer getVelocity() {
		return velocity;
	}
	
	
}
