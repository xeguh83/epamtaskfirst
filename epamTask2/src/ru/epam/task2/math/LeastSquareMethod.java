package ru.epam.task2.math;

import java.util.ArrayList;
import java.util.List;

import ru.epam.task2.gui.Task;

public class LeastSquareMethod extends Task {
	
	private List<Double> voltageList;
	private List<Double> currentList;
	private List<Double> resistanceList;

	public LeastSquareMethod(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	@Override
	protected void doLogic() {
		drawTitle();
		if (!setCurAndVoltLists()) {
			System.out.println(" Ошибка чтения из файла или некорректный формат!\r\n" 
							 + " Должно быть две строки десятичных дробей разделенных пробелами:\r\n" 
							 + " первая строка - измеренные напряжения, вторая - токи на одном сопротивлении");
			printEmptyLines(13);
			return;
		}
		setResistanceList();
		
	}

	private void setResistanceList() {
		resistanceList = new ArrayList<Double>();
		for (int i = 0; i < voltageList.size(); i++) {
			if (voltageList.get(i).equals(0d) && !currentList.get(i).equals(0d)) {
				resistanceList.add(0d); // короткое замыкание
			} else if ((voltageList.get(i) > 1d) && currentList.get(i).equals(0d)) {
				resistanceList.add(Double.MAX_VALUE); // обрыв провода
			} else if (voltageList.get(i).equals(0d) && currentList.get(i).equals(0d)) {
				resistanceList.add(0d); // цепь обесточена
			} else {
				resistanceList.add(voltageList.get(i) / currentList.get(i));
			}
		}
	}

	private boolean setCurAndVoltLists() {
		voltageList = new ArrayList<Double>();
		currentList = new ArrayList<Double>();
		String[] strings = getStrings();
		if (strings.length != 2) return false; 
		try {
			String[] voltageWords = strings[0].split("\\s");
			String[] currentWords = strings[1].split("\\s");
			if ((voltageWords.length != currentWords.length) || (voltageWords.length == 0)) return false;
			for (int i = 0; i < voltageWords.length; i++) {
				Double temp = Double.parseDouble(voltageWords[i]);
				if (Double.isInfinite(temp) || Double.isNaN(temp)) return false;
				voltageList.add(temp);
				temp = Double.parseDouble(currentWords[i]);
				if (Double.isInfinite(temp) || Double.isNaN(temp)) return false;
				currentList.add(temp);
			}
		} catch (Exception e) {
			return false;
		}		
		return true;
	}

	@Override
	protected void drawTitle() {
		// TODO Auto-generated method stub
		
	}

}
