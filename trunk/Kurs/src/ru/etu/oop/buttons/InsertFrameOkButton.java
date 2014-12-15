package ru.etu.oop.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ru.etu.oop.data.Controller;

public class InsertFrameOkButton extends JButton{
	
	private Controller ctrl;

	public InsertFrameOkButton(final Controller ctrl) {
		
		super("Занять комнату", new ImageIcon("./img/insert.png"));
		
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				ctrl.updateTableFromFieldAndCloseInsertFrame();
			}
		});

	}
}
