package ru.etu.oop.data;

import javax.swing.JTextField;

import ru.etu.oop.containers.MyScrollPane;
import ru.etu.oop.frames.InsertFrame;

public class Controller {

	private final Data data;
	
	private InsertFrame insertFrame;
	private JTextField insertFrameTextField;

	private MyScrollPane myScrollPane;
	
	
	
	public Controller() {
		this.data = new Data();
	}
	
	
	/**
	 * @param insertFrame the insertFrame to set
	 */
	public void setInsertFrame(InsertFrame insertFrame) {
		this.insertFrame = insertFrame;
	}

	public void closeInsertFrame() {
		insertFrame.setVisible(false);
		insertFrame = null;
	}


	public void setInsertFrameTextField(JTextField textFieldFIO) {
		this.insertFrameTextField = textFieldFIO;
	}


	public void updateTableFromFieldAndCloseInsertFrame() {
		int index = insertFrame.getRow();
		Room curRoom = data.getRooms().get(index);
		curRoom.setClientFIO(insertFrameTextField.getText());
		data.updateRoom(index, curRoom);
		myScrollPane.refresh(curRoom.getClientFIO(), index, 2);
		insertFrame.setVisible(false);
		insertFrame = null;
	}


	public void setRoomTable(MyScrollPane myScrollPane) {
		this.myScrollPane = myScrollPane;
	}
	

}
