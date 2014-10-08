package ru.epam.task1.gui;

import java.io.Console;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import ru.epam.task1.arguments.DataFromArguments;
import ru.epam.task1.math.*;
import ru.epam.task1.matrix.*;
import ru.epam.task1.string.*;
import ru.epam.task1.useswitch.*;
import ru.epam.task1.word.*;


public class Main {
	
	private static Console console;

	static
	{
	  try
	  {
	    if(System.getProperty("os.name").toLowerCase().contains("windows"))
	    {
	      PrintStream out = new PrintStream(System.out, true, "Cp866");
	      PrintStream err = new PrintStream(System.err, true, "Cp866");
	      System.setOut(out);
	      System.setErr(err);
	    }
	  }
	  catch(UnsupportedEncodingException e)
	  {
	    System.err.println("Couldn't change console encoding " + e);
	  }
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Task[] taskList = createTaskList();
		drawTitle();
//		console = System.console();
//		boolean exit = false;
//		while (!exit) {
//			switch (printMenu()) {
//				case 1: {
//					break;			
//				}
//			}
//		}

	}
	
	
	
	private static Task[] createTaskList() {
		// TODO Auto-generated method stub
		Task[] taskList = new Task[20];
		taskList[0] = new MaxAndMinLengthString("����� �������� � ������� ������");
		taskList[1] = new SortString("���������� �����");
		taskList[2] = new MoreLessThanMedian("������ ������/������ ������� �����");
		taskList[3] = new CharMinWord("����� ����� � ��������� ����");
		taskList[4] = new EngCharWord("����� ����� �� ��������� ����");
		taskList[5] = new CodeRisingWord("����� ����� � ��������������� �����");
		taskList[6] = new DifferentCharWord("����� ����� � ������� ������");
		taskList[7] = new PalindromWord("����� �����-���������");
		taskList[8] = new DataFromArguments("������ 1-8 �� ���������� ���. ������");
		taskList[9] = new SwitchFirstInterval("����� �������������� k ��������� A");
		taskList[10] = new SwitchSecondInterval("����� �������������� k ��������� B");
		taskList[11] = new NumsInMatrix("��������� ����� � �������");
		taskList[12] = new QuadraticEq("������� ���������� ���������");
		taskList[13] = new NumToMonth("����� ������ �� ������");
		taskList[14] = new MatrixSedDots("����� �������� ����� �������");
		taskList[15] = new MatrixBuildRisingRows("����������� ������� �� �����������");
		taskList[16] = new MatrixLocalMin("����� ��������� �������� �������");
		taskList[17] = new MatrixBigestLocalMax("���������� ��������� ������� �������");
		taskList[18] = new MatrixCharacterLowering("���������� ������������� �������");
		taskList[19] = new MatrixMainDiagFill("���������� ������� ���������");
		return taskList;
		
	}



	private static void clearScreen() {
		// TODO Auto-generated method stub
		char c = '\n';
		int length = 28;
		char[] chars = new char[length];
		Arrays.fill(chars, c);
		System.out.print(String.valueOf(chars));
	}



	private static int printMenu() {
		int command = 0;
		boolean success = false;
		
		while (!success) {
			try {
				System.out.println("1. Create new tree");
				System.out.println("2. Add left child");
				System.out.println("3. Add right child");
				System.out.println("4. Print tree");
				System.out.println("5. Exit");
				
				command = Integer.valueOf(console.readLine("Enter command: "));
				success = true;
			} catch (NumberFormatException e) {
				success = false;
			}
		}
		
		return command;
	}
	
	public static void drawTitle() {
		 System.out.println("################################################################################");
		 System.out.println("#                                                                              #");
		 System.out.println("#                  ����� ���������� � ��������� ���������������                #");
		 System.out.println("#                     ������� ����� ����� �1 �� <epam> Systems                 #");
		 System.out.println("#                                                                              #");
		 System.out.println("################################################################################");
	}
	
	

}
