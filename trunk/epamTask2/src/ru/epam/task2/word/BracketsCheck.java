package ru.epam.task2.word;

import java.util.Stack;

import ru.epam.task2.gui.Task;

public class BracketsCheck extends Task {
	private final Stack<String> brackets = new Stack<String>();

	public BracketsCheck(String shortName, String[] incomingStrings) {
		super(shortName, incomingStrings);
	}

	@Override
	protected void doLogic() {
		String[] strings = getStrings();
		String commonString = "";
		for (String line : strings) {
			commonString += line;
		}
		boolean result = bracketsValidClosingCheck(commonString.toCharArray());
		if (result) {
			drawTitle();
			System.out.println(" Результат анализа: скобки расставлены корректно!");
			printEmptyLines(14);
		} else {
			drawTitle();
			System.out.println(" Результат анализа: некорректная расстановка скобок!");
			printEmptyLines(14);
		}
	}

	private boolean bracketsValidClosingCheck(char[] charArray) {
		for (char c : charArray) {
			if (String.valueOf(c).equals("{")) {
				brackets.push("{");
			} else if (String.valueOf(c).equals("(")) {
				brackets.push("(");
			} else if (String.valueOf(c).equals("[")) {
				brackets.push("[");
			} else if (String.valueOf(c).equals("}")) {
				if (isValidClosing("}")) {
					brackets.pop();
				} else {
					return false;
				}
			} else if (String.valueOf(c).equals(")")) {
				if (isValidClosing(")")) {
					brackets.pop();
				} else {
					return false;
				}
			} else if (String.valueOf(c).equals("]")) {
				if (isValidClosing("]")) {
					brackets.pop();
				} else {
					return false;
				}
			}
		}
		if (brackets.size() != 0) {
			return false;
		}
		return true;
	}

	private boolean isValidClosing(String bracket) {
		if (bracket.equals("}") && brackets.peek().equals("{")) {
			return true;
		} else if (bracket.equals(")") && brackets.peek().equals("(")) {
			return true;
		} else if (bracket.equals("]") && brackets.peek().equals("[")) {
			return true;
		}
		return false;
	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║ Задача №9. Задана строка, содержащая символы '(', ')', '[', ']', '{', '}'. ║\r\n" + 
				 			" ║        Проверить правильность расстановки скобок. Использовать стек        ║\r\n" +
		 					" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
		 					" ║               Общая строка склеивается из строк файла task9.txt            ║\r\n" +
		 					" ║    Результат проверки на правильность закрытия скобок выводится на экран   ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
	}

}
