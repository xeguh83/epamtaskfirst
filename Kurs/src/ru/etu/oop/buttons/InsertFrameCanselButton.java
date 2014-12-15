package ru.etu.oop.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ru.etu.oop.data.Controller;

public class InsertFrameCanselButton extends JButton{
	
	private final Controller ctrl;
	
	public InsertFrameCanselButton(final Controller ctrl) {
		super("Освободить комнату", new ImageIcon("./img/free.png"));
		this.ctrl = ctrl;
		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				ctrl.freeRoomAndCloseInsertFrame();
			}
		});
	}

}
