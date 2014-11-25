package ru.epam.task2.cars;

import java.util.ArrayList;
import java.util.List;

import ru.epam.task2.gui.Task;

/**
 * <p>Класс задания 23</p>
 * Класс выполняет отрисовку задания и выполняет следующую логику: выводит в файл описание первых К обгонов,
 * количество которых вводится пользователем при выполнении программы, а список машин загружается из файла. 
 * @author Туркин А.К.
 */
/**
 * @author Например Андрей
 *
 */
public class FirstKOvertakings extends Task{
	
	/**
	 * Объект набора обгонов
	 */
	private OvertakingSet overs;
	/**
	 * Список машин
	 */
	private List<Car> carList;
	/**
	 * требуемое количество обгонов
	 */
	private int k;

	/**
	 * Конструктор задания передающий наследуемому классу краткое описание задания и массив исходных данных из файла
	 * @param shortName краткое описание задания
	 * @param incomingStrings массив исходных данных
	 */
	public FirstKOvertakings(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	/**
	 * Метод содержащий тело задания. Метод отрисовывает задание и проверяет список машин, а затем запрашивает искомое число обгонов. 
	 * Если список машин содержит некорректные данные или введенное число обгонов больше реального числа обгонов, метод выводит пользователю
	 * сообщение об ошибке.
	 */
	@Override
	protected void doLogic() {
		overs = new OvertakingSet();
		if (!setCarList()) {
			drawTitle();
			System.out.println(" Ошибка чтения файла или неверный формат!\r\n" 
							 + " Машины считаются построчно из файла task23.txt\r\n" 
							 + " в формате СтартПозиция Скорость");
			printEmptyLines(12);
			return;
		}
		overs.setAllOvertakings(carList);
		setK();
		if (k > overs.getOverCount()) {
			drawTitle();
			System.out.println(" Число К больше реального числа обгонов");
			printEmptyLines(14);
			return;
		}
		drawTitle();
		overs = new OvertakingSet();
		overs.setKfirstOvertakings(carList, k);
		System.out.println(" В файл task23output.txt выписаны первые " + overs.getOverCount() + " обгонов");
		writeStringsToFile("task23output.txt", overs.getStrings());
		printEmptyLines(14);
	}

	/**
	 * Метод выводит запрос пользователю на ввод требуемого количества обногов
	 */
	private void setK() {
		while (true) {
			drawTitle();
			printEmptyLines(15);
			try {
				int value = Integer.parseInt(askOption(" Введите целое число К - количество первых обгонов: "));
				if (value < 1) continue;
				this.k = value;
				return;
			} catch (Exception e) {
				continue;
			}
		}
	}

	/* (non-Javadoc)
	 * @see ru.epam.task2.gui.Task#drawTitle()
	 */
	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║ Задача №23. На прямой гоночной трассе стоит N автомобилей, для каждого из  ║\r\n" +
				 			" ║ которых известны начальное положение и скорость. Вывести первые k обгонов. ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║                 Количество обгонов выводится численно на экран             ║\r\n" +
				 			" ║      Описание обгонов построчно выгружается в файл task23output.txt        ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
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
		
}
