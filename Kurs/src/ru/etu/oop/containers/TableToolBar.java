package ru.etu.oop.containers;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import ru.etu.oop.buttons.insertButton;

public class TableToolBar extends JToolBar {
	
	public TableToolBar(String title) {
		
		super(title);
		
		JButton insertClientIntoRoom = new insertButton(new ImageIcon("./img/insert.png")); 
		insertClientIntoRoom.setToolTipText("Занять комнату");
		this.add(insertClientIntoRoom);
		
		JButton freeRoom = new JButton(new ImageIcon("./img/free.png")); 
		freeRoom.setToolTipText("Освободить комнату");
		this.add(freeRoom);
		
	}

}
