package ru.etu.oop.data;

public class Worker {
	
	private String FIO;
	private String work;
	private String passport;
	
	/**
	 * @param fIO
	 * @param work
	 * @param passport
	 */
	public Worker(String fIO, String work, String passport) {
		FIO = fIO;
		this.work = work;
		this.passport = passport;
	}

	/**
	 * @return the fIO
	 */
	public String getFIO() {
		return FIO;
	}

	/**
	 * @return the work
	 */
	public String getWork() {
		return work;
	}

	/**
	 * @return the passport
	 */
	public String getPassport() {
		return passport;
	}

	/**
	 * @param fIO the fIO to set
	 */
	public void setFIO(String fIO) {
		FIO = fIO;
	}

	/**
	 * @param work the work to set
	 */
	public void setWork(String work) {
		this.work = work;
	}

	/**
	 * @param passport the passport to set
	 */
	public void setPassport(String passport) {
		this.passport = passport;
	}
	
	
	
	

}
