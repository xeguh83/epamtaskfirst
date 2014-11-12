package ru.epam.task2.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import ru.epam.task2.gui.Task;

public class TwoSetsToOne extends Task {
	private List<Double> firstList;
	private List<Double> secondList;
		

	public TwoSetsToOne(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	@Override
	protected void doLogic() {
		String[] words = getWordsFromStringArray();
		if (!sureDataIsCorrectSetsAndSetLists(words)) {
			System.out.println(" Ошибка преобразования данных из файла!");
			printEmptyLines(14);
			return;
		}
		Collections.sort(firstList);
		List<Double> resList = combineAndSortLists();
		System.out.println(" 2 набора положительных чисел были считаны из файла task16.txt");
		System.out.println(" Наборы преобразованы в списки. Первый список отсортирован по ");
		System.out.println(" возрастанию, затем наборы были объединены в один общий список, который также был отсортирован");
		System.out.println(" по возрастанию. Результат будет выведен в файл task16output.txt");
		printEmptyLines(11);
		writeStringsToFile("task16output.txt", getStringArray(resList));
	}
	
	private List<Double> combineAndSortLists() {
		List<Double> res = new ArrayList<Double>(firstList);
		res.addAll(secondList);
		Collections.sort(res);
		return res;
	}

	private boolean sureDataIsCorrectSetsAndSetLists(String[] words) {
		Set<Double> firstSet = new HashSet<Double>();
		Set<Double> secondSet = new HashSet<Double>();
		boolean getFirstNegNum = false;
		for (String word : words) {
			try {
				Double element = Double.parseDouble(word);
				if (Double.isInfinite(element) || Double.isNaN(element)) {
					throw new NumberFormatException();
				}
				if (!getFirstNegNum && element >= 0) {
					firstSet.add(element);
				} else if (element < 0){
					if (getFirstNegNum) return false;
					getFirstNegNum = true;
				} else {
					secondSet.add(element);
				}
			} catch (Exception e) {
				return false;
			}
		}
		firstList = new ArrayList(firstSet);
		secondList = new ArrayList(secondSet);
		return true;
	}

	private String[] getStringArray(List<Double> list) {
		Iterator<Double> iter = list.iterator();
		String[] stringArray = new String[list.size()];
		for (int i = 0; i < stringArray.length; i++) {
			stringArray[i] = String.format("%#16.8f", iter.next());
		}
		return stringArray;
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║ Задача №16. Загрузить наборы положительных чисел раздленные отрицательным, ║\r\n" +
				 			" ║   преобразовать в списки, первый отсортировать, затем объединить списки    ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║   Данные считаны из файла task16.txt. Результирующий список упорядочен по  ║\r\n" +
				 			" ║             возрастанию и записан в файл task16output.txt построчно        ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");

	}

}
