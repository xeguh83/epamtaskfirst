package ru.epam.task2.cars;

/**
 * <p>Служебный класс машина</p>
 * Класс служит для формирования класса обгона. Содержит параметры машин и методы работы с ними
 * @author Туркин А.К.
 */
public class Car {
	
	/**
	 * стартовая позиция машины на треке
	 */
	private final Integer startPos;
	/**
	 * скорость машины (величина постоянная)
	 */
	private final Integer velocity;
	
	/**
	 * начало пути за отрезок времени
	 */
	private Integer startWay;
	/**
	 * конец пути за отрезок времени
	 */
	private Integer finWay;
	
	/**
	 * Конструктор задает стартовую позицию и скорость машины по входным данным, 
	 * а также инициализирует начало пути значением стартовой позиции
	 * @param startPos стартовая позиция на треке в метрах относительно стартовой линии
	 * @param velocity постоянная скорость машины в метрах за секунду
	 */
	public Car(Integer startPos, Integer velocity) {
		this.startPos = startPos;
		this.velocity = velocity;
		this.startWay = startPos;
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

	/**
	 * @return the startWay
	 */
	public Integer getStartWay() {
		return startWay;
	}

	/**
	 * @return the finWay
	 */
	public Integer getFinWay() {
		return finWay;
	}

	/**
	 * @param startWay the startWay to set
	 */
	public void setStartWay(Integer startWay) {
		this.startWay = startWay;
	}

	/**
	 * @param finWay the finWay to set
	 */
	public void setFinWay(Integer finWay) {
		this.finWay = finWay;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((startPos == null) ? 0 : startPos.hashCode());
		result = prime * result
				+ ((velocity == null) ? 0 : velocity.hashCode());
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
		if (!(obj instanceof Car))
			return false;
		Car other = (Car) obj;
		if (startPos == null) {
			if (other.startPos != null)
				return false;
		} else if (!startPos.equals(other.startPos))
			return false;
		if (velocity == null) {
			if (other.velocity != null)
				return false;
		} else if (!velocity.equals(other.velocity))
			return false;
		return true;
	}
	
	
	
}
