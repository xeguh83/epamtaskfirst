package ru.etu.oop;

import java.awt.EventQueue;
import java.util.Locale;

import javax.swing.JFrame;

import ru.etu.oop.data.Controller;
import ru.etu.oop.frames.MainFrame;

public class App {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				Locale.setDefault(new Locale("ru"));
				Controller ctrl = new Controller(); 

				
				
			}
		});

	}

}
