package ru.etu.oop.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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


	public void updateTableFromField(int selectedRow, String clientFIO) {
		Room curRoom = data.getRooms().get(selectedRow);
		curRoom.setClientFIO(clientFIO);
		data.updateRoom(selectedRow, curRoom);
		mainFrame.updateTable(selectedRow, clientFIO);
	}

	public void setRoomTable(MyScrollPane myScrollPane) {
		this.myScrollPane = myScrollPane;
	}


	public Object[][] getTableData() {

		return IOClass.roomsToArrays(data);
	}


	public String getNewFIO(int selectedRow) {
		String roomNumber = data.getRooms().get(selectedRow).getNumber();
		InsertFrame frame = new InsertFrame(roomNumber, selectedRow, this);
		frame.setModal(true);
		frame.setVisible(true);
		
		
		return null;
	}


	public void saveDataToFile() {
		try {
			PrintWriter pw = new PrintWriter("./data/data.txt");
			List<Room> list = data.getRooms();
			for (Room room : list) {
				pw.println(room.getNumber() + "|" + room.getCapacity() + "|" + room.getClientFIO());
			}
			pw.flush();
			pw.close();
			JOptionPane.showMessageDialog(null, "Данные успешно сохранены в файл /data/data.txt");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Ошибка записи данных в файл");
		}
	}
	

}
