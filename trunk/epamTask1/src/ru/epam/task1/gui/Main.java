package ru.epam.task1.gui;

import java.io.Console;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import ru.epam.task1.String.*;


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
		createTaskList();
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
	
	
	
	private static void createTaskList() {
		// TODO Auto-generated method stub
		Task[] taskList = new Task[20];
		taskList[1] = new MaxAndMinLengthString();
		taskList[2] = new SortString();
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
		 System.out.println("#                  Добро пожаловать в программу демонстрирующую                #");
		 System.out.println("#                     решение задач Теста №1 от <epam> Systems                 #");
		 System.out.println("#                                                                              #");
		 System.out.println("################################################################################");
	}
	
	

}
