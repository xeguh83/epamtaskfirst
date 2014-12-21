package ru.etu.oop.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ru.etu.oop.frames.ClientsFrame;
import ru.etu.oop.frames.InsertFrame;
import ru.etu.oop.frames.MainFrame;
import ru.etu.oop.frames.WorkersFrame;
import ru.etu.oop.frames.pricesFrame;

public class Controller {

	private final Data data;
	private final MainFrame mainFrame;

	public Controller() {
		this.data = new Data();
		this.mainFrame = new MainFrame(this);
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.setVisible(true);
	}
	
	public void updateDataFromField(int selectedRow, String clientFIO) {
		Room curRoom = data.getRooms().get(selectedRow);
		curRoom.setClientFIO(clientFIO);
		data.updateRoom(selectedRow, curRoom);
	}

	public String[][] getTableData() {
		return IOClass.roomsToArrays(data);
	}

	public String getNewFIO(int selectedRow) {
		String roomNumber = data.getRooms().get(selectedRow).getNumber();
		InsertFrame frame = new InsertFrame(roomNumber, selectedRow, this);
		frame.setModal(true);
		frame.setVisible(true);
		// продолжается выполнение кода с этой точки после вызова setVisible(false) в InsertFrame
		if (!frame.isCorrectlyClosed()) {
			frame.dispose();
			return data.getRooms().get(selectedRow).getClientFIO();
		}
		String text = frame.getFieldText();
		frame.dispose();
		return text;
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

	public void showPrises() {
		pricesFrame frame = new pricesFrame();
		frame.setModal(true);
		frame.setVisible(true);
		frame.dispose();
	}

	public void showClients() {
		ClientsFrame frame = new ClientsFrame(this);
		frame.setModal(true);
		frame.setVisible(true);
		frame.dispose();
	}

	public String[][] getClientsTableData() {
		List<Room> list = new ArrayList<Room>(data.getRooms());
		
		for (Iterator<Room> it = list.iterator(); it.hasNext(); ) {
			Room room = it.next();
			if (room.getClientFIO().equals("-")) {
				it.remove();
			}
		}
		String[][] array = new String[list.size()][];
		for (int i = 0; i < array.length; i++) {
			array[i] = new String[2];
			array[i][0] = list.get(i).getClientFIO();
			array[i][1] = list.get(i).getNumber();
		}
		return array;
	}

	public void addWorker() {
		// TODO Auto-generated method stub
		
	}

	public String[][] getWorkersTableData() {
		return IOClass.workersToArrays(data);
	}

	public void showWorkers() {
		WorkersFrame frame = new WorkersFrame(this);
		frame.setModal(true);
		frame.setVisible(true);
		frame.dispose();
	}
	

}
