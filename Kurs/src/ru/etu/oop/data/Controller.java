package ru.etu.oop.data;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import ru.etu.oop.containers.MyScrollPane;
import ru.etu.oop.frames.InsertFrame;
import ru.etu.oop.frames.MainFrame;

public class Controller {

	private final Data data;
	
	private final MainFrame mainFrame;
	
	private InsertFrame insertFrame;
	private JTextField insertFrameTextField;

	private MyScrollPane myScrollPane;
	
	
	
	public Controller() {
		this.data = new Data();
		this.mainFrame = new MainFrame(this);
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.setVisible(true);
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


//	public void updateTableFromFieldAndCloseInsertFrame() {
//		int index = insertFrame.getRow();
//		Room curRoom = data.getRooms().get(index);
//		curRoom.setClientFIO(insertFrameTextField.getText());
//		data.updateRoom(index, curRoom);
//		myScrollPane.refresh(curRoom.getClientFIO(), index, 2);
//		insertFrame.setVisible(false);
//		insertFrame = null;
//	}

//	public void freeRoomAndCloseInsertFrame() {
//		int index = insertFrame.getRow();
//		Room curRoom = data.getRooms().get(index);
//		curRoom.setClientFIO("-");
//		data.updateRoom(index, curRoom);
//		myScrollPane.refresh(curRoom.getClientFIO(), index, 2);
//		insertFrame.setVisible(false);
//		insertFrame = null;
//	}

	

	public void setRoomTable(MyScrollPane myScrollPane) {
		this.myScrollPane = myScrollPane;
	}


	public Object[][] getTableData() {
		// TODO Auto-generated method stub
		return IOClass.roomsToArrays(data);
	}


	public String getNewFIO(int selectedRow) {
		String roomNumber = data.getRooms().get(selectedRow).getNumber();
		InsertFrame frame = new InsertFrame(roomNumber, this);
		frame.setModal(true);
		frame.setVisible(true);
		
		
		return null;
	}
	

}
