package ru.epam.task2.cars;

import java.util.ArrayList;
import java.util.List;

import ru.epam.task2.gui.Task;

/**
 * <p>Класс задания 22</p>
 * Класс содержит методы по отрисовке и вычислению задачи: вывод всех обгонов на трассе по заданным параметрам машин
 * @author Туркин А.К.
 */
public class OvertakingCount extends Task {
	
	private OvertakingSet overs;
	private List<Car> carList;

	/**
	 * Конструктор задания передающий наследуемому классу краткое описание задания и массив исходных данных из файла
	 * @param shortName краткое описание задачи
	 * @param incomingStrings массив исходных данных из файла
	 */
	public OvertakingCount(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	/**
	 * Метод содержащий тело задания. Метод отрисовывает задание и проверяет список машин. 
	 * В случае ошибки считывания данных метод выводит информацию об этом пользователю и завершает работу. 
	 * Метод выводит количество обгонов на экран, а их описание записывает в файл.
	 */
	@Override
	protected void doLogic() {
		overs = new OvertakingSet();
		drawTitle();
		if (!setCarList()) {
			System.out.println(" Ошибка чтения файла или неверный формат!\r\n" 
							 + " Машины считаются построчно из файла task22.txt\r\n" 
							 + " в формате СтартПозиция Скорость");
			printEmptyLines(12);
			return;
		}
		overs.setAllOvertakings(carList);
		System.out.println(" Количество обгонов равно: " + overs.getOverCount());
		writeStringsToFile("task22output.txt", overs.getStrings());
		printEmptyLines(14);
	}

	/**
	 * Метод инициализирует список машин и заполняет его значениями из массива <code>incommingStrings</code>
	 * @return <code>true</code> в случае успешной записи списка машин и <code>false</code> при наличии ошибок
	 */
	private boolean setCarList() {
		carList = new ArrayList<Car>();
		String[] cars = getStrings();
		if (cars.length < 1) return false;
		try {
			for (String car : cars) {
				String[] carData = car.split("\\s");
				if (carData.length != 2) return false;
				carList.add(new Car(Integer.parseInt(carData[0]), Integer.parseInt(carData[1])));
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
				 			" ║ Задача №22. На прямой гоночной трассе стоит N автомобилей, для каждого из  ║\r\n" +
				 			" ║которых известны начальное положение и скорость. Cколько произойдет обгонов?║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║                 Количество обгонов выводится численно на экран             ║\r\n" +
				 			" ║      Описание обгонов построчно выгружается в файл task22output.txt        ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
	}

}
