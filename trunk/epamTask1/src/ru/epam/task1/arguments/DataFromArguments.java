package ru.epam.task1.arguments;

import ru.epam.task1.gui.Task;

public class DataFromArguments extends Task {

	public DataFromArguments(String shortName) {
		super(shortName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawTask() {
		// �� ������������
	}

	@Override
	public void drawTask(String[] string) {
		//TODO ����������� ���������� �� ��������� ������
		System.out.println("I get string from command line");
	}

	@Override
	protected void doLogic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void drawTitle() {
		// TODO Auto-generated method stub
		
	}
	

}
