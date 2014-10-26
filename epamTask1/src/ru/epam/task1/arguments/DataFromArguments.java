package ru.epam.task1.arguments;

import ru.epam.task1.gui.Task;

/**Класс для формирования списка задач. Передает в массив строк короткое наименование задачи номер 9
 * @author Туркин А.К.
 */
public class DataFromArguments extends Task {

	/**Конструктор для передачи в массив строк короткого наименования задачи номер 9
	 * @param shortName сокращенное наименование задачи
	 */
	public DataFromArguments(String shortName) {
		super(shortName);
	}


	/**
	 * Пустой метод как реализация абстрактного метода <code>doLogic()</code>
	 */
	@Override
	protected void doLogic() {
	}

	/**
	 * Пустой метод как реализация абстрактного метода <code>drawTitle()</code>
	 */
	@Override
	protected void drawTitle() {
	}
	

}
