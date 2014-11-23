package ru.epam.task2.cars;

import java.util.ArrayList;
import java.util.List;

import ru.epam.task2.gui.Task;

public class FirstKOvertakings extends Task{
	
	private OvertakingSet overs;
	private List<Car> carList;
	private final int K = 5;

	public FirstKOvertakings(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	@Override
	protected void doLogic() {
		overs = new OvertakingSet();
		drawTitle();
		if (!setCarList()) {
			System.out.println(" Ошибка чтения файла или неверный формат!\r\n" 
							 + " Машины считаются построчно из файла task23.txt\r\n" 
							 + " в формате СтартПозиция Скорость");
			printEmptyLines(12);
			return;
		}
		overs.setAllOvertakings(carList);
		if (K > overs.getOverCount()) {
			System.out.println(" Число К больше реального числа обгонов");
			printEmptyLines(14);
			return;
		}
		overs = new OvertakingSet();
		overs.setKfirstOvertakings(carList, K);
		System.out.println(" Количество обгонов равно: " + overs.getOverCount());
		writeStringsToFile("task22output.txt", overs.getStrings());
		printEmptyLines(14);
	}

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
