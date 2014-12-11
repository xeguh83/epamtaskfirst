package ru.etu.oop.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ru.etu.oop.frames.InsertFrame;

public class insertButton extends JButton{

	/**
	 * @param icon
	 */
	public insertButton(ImageIcon icon) {
		super(icon);
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

//				InsertFrame frame = new InsertFrame();
//				frame.setVisible(true);
				
			}
		});
	}

	
	
}
