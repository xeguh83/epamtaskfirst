package ru.etu.oop.data;


public class Room {
	
	private String number;
	private String capacity;
	private String clientFIO;
	
	/**
	 * @param words
	 * @param words2
	 * @param clientFIO
	 */
	public Room(String words, String words2, String clientFIO) {
		this.number = words;
		this.capacity = words2;
		this.clientFIO = clientFIO;
	}
	
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * @return the capacity
	 */
	public String getCapacity() {
		return capacity;
	}
	
	/**
	 * @return the clientFIO
	 */
	public String getClientFIO() {
		return clientFIO;
	}
	
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * @param clientFIO the clientFIO to set
	 */
	public void setClientFIO(String clientFIO) {
		this.clientFIO = clientFIO;
	}
	
	
}

