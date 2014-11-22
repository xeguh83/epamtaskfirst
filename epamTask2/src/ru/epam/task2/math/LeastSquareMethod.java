package ru.epam.task2.math;

import java.util.ArrayList;
import java.util.List;

import ru.epam.task2.gui.Task;

public class LeastSquareMethod extends Task {
	
	private List<MeasuringVoltAndCur> measuringList;
	private Double resistance;
	private Double resError;

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
			printEmptyLines(12);
			return;
		}
		setResistanceList();
		calculateResAndEr();
		System.out.println(" Приближенное значение сопротивления на измерениях тока и напряжения составляет\r\n" 
						 + " " + String.format("%#10.2f", resistance) + " +/- " + String.format("%#12.8f", resError));
		printEmptyLines(13);
	}

	private void calculateResAndEr() {
		// находим среднее арифметическое от полученных сопротивлений
		resistance = meanOfMeasures();
		// находим значение абсолютной погрешности по СКО и коэффициенту Стьюдента
		resError = getSko() * getStyudentValue(measuringList.size());
	}

	private Double getStyudentValue(int count) {
		if (count == 2) return 12.7062047364;
		if (count == 3) return 4.30265272991;
		if (count == 4) return 3.18244630528;
		if (count == 5) return 2.7764451052;
		if (count == 6) return 2.57058183661;
		if (count == 7) return 2.44691184879;
		if (count == 8) return 2.36462425101;
		if (count == 9) return 2.30600413503;
		if (count == 10) return 2.26215716274;
		if (count == 11) return 2.22813885196;
		if (count == 12) return 2.20098516008;
		if (count == 13) return 2.17881282966;
		if (count == 14) return 2.16036865646;
		if (count == 15) return 2.14478668792;
		if (count == 16) return 2.13144954556;
		if (count == 17) return 2.11990529922;
		if (count == 18) return 2.10981557783;
		if (count == 19) return 2.10092204024;
		if (count == 20) return 2.09302405441;
		if (count == 21) return 2.08596344727;
		if (count == 22) return 2.07961384473;
		if (count == 23) return 2.0738730679;
		if (count == 24) return 2.06865761042;
		if (count == 25) return 2.06389856163;
		if (count == 26) return 2.05953855275;
		if (count == 27) return 2.05552943864;
		if (count == 28) return 2.05183051648;
		if (count == 29) return 2.0484071418;
		if (count == 30) return 2.04522964213;
		if (count == 31) return 2.0422724563;
		if (count > 31 && count <= 41) return 2.021075383;
		if (count > 41 && count <= 61) return 2.00029782106;
		if (count > 61 && count <= 121) return 1.97993040505;
		return 1.95996635682;
	}

	private Double getSko() {
		Double sigmaOfSq = 0d;
		for (MeasuringVoltAndCur measure : measuringList) {
			sigmaOfSq += Math.pow(measure.getResistance() - resistance, 2d);
		}
		return Math.sqrt(sigmaOfSq / measuringList.size());
	}

	private Double meanOfMeasures() {
		Double sigma = 0d;
		for (MeasuringVoltAndCur measure : measuringList) {
			sigma += measure.getResistance();
		}
		return sigma / measuringList.size();
	}

	private void setResistanceList() {
		for (MeasuringVoltAndCur measure : measuringList) {
			if (measure.getVoltage().equals(0d) && !measure.getCurrent().equals(0d)) {
				measure.setResistance(0d); // короткое замыкание
			} else if (!measure.getVoltage().equals(0d) && measure.getCurrent().equals(0d)) {
				measure.setResistance(Double.MAX_VALUE); // обрыв провода
			} else if (measure.getVoltage().equals(0d) && measure.getCurrent().equals(0d)) {
				measure.setResistance(0d); // цепь обесточена
			} else {
				measure.setResistance(Math.abs(measure.getVoltage() / measure.getCurrent()));
			}			
		}
	}

	private boolean setCurAndVoltLists() {
		measuringList = new ArrayList<MeasuringVoltAndCur>();
		String[] strings = getStrings();
		if (strings.length != 2) return false; 
		try {
			String[] voltageWords = strings[0].split("\\s");
			String[] currentWords = strings[1].split("\\s");
			if ((voltageWords.length != currentWords.length) || (voltageWords.length < 2)) return false;
			for (int i = 0; i < voltageWords.length; i++) {
				Double voltage = Double.parseDouble(voltageWords[i]);
				if (Double.isInfinite(voltage) || Double.isNaN(voltage)) return false;
				Double current = Double.parseDouble(currentWords[i]);
				if (Double.isInfinite(current) || Double.isNaN(current)) return false;
				measuringList.add(new MeasuringVoltAndCur(voltage, current));
			}
		} catch (Exception e) {
			return false;
		}		
		return true;
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║ Задача №5. Списки I и U содержат измерения тока и напряжения на неизвестном║\r\n" +
				 			" ║ сопротивлении R. Найти приближенное число R методом наименьших квадратов.  ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║Данные измерений считаваются из двух строк (Напряжение, ток) файла task5.txt║\r\n" +
				 			" ║    Результат вычисленного приближенного сопротивления выводится на экран   ║\r\n" +
							" ╚════════════════════════════════════════════════════════════════════════════╝");
	}
	
	private class MeasuringVoltAndCur {
		
		private final Double voltage;
		private final Double current;
		private Double resistance;

		public MeasuringVoltAndCur(Double voltage, Double current) {
			this.voltage = voltage;
			this.current = current;
		}

		/**
		 * @return the voltage
		 */
		public Double getVoltage() {
			return voltage;
		}

		/**
		 * @return the current
		 */
		public Double getCurrent() {
			return current;
		}

		/**
		 * @return the resistance
		 */
		public Double getResistance() {
			return resistance;
		}

		/**
		 * @param resistance the resistance to set
		 */
		public void setResistance(Double resistance) {
			this.resistance = resistance;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "MeasuringVoltAndCur [resistance=" + resistance + "]";
		}

		
	}

}
