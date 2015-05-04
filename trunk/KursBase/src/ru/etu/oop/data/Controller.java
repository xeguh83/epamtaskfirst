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
import ru.etu.oop.frames.ReportFrame;
import ru.etu.oop.frames.WorkerFrame;
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
//			PrintWriter pw = new PrintWriter("./data/data.txt");
//			List<Room> list = data.getRooms();
//			for (Room room : list) {
//				pw.println(room.getNumber() + "|" + room.getCapacity() + "|" + room.getClientFIO());
//			}
//			pw.flush();
//			pw.close();
			
			DBAdapter.updateTable("rooms", data.getRooms());
			
			PrintWriter pw2 = new PrintWriter("./data/workers.txt");
			List<Worker> list2 = data.getWorkers();
			for (Worker worker : list2) {
				pw2.println(worker.getFIO() + "|" + worker.getWork() + "|" + worker.getPassport());
			}
			pw2.flush();
			pw2.close();
			
			JOptionPane.showMessageDialog(null, "Данные успешно сохранены в файлы /data/data.txt и /data/workers.txt");
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

	public String[][] getWorkersTableData() {
		return IOClass.workersToArrays(data);
	}

	public void showWorkers() {
		WorkersFrame frame = new WorkersFrame(this);
		frame.setModal(true);
		frame.setVisible(true);
		frame.dispose();
	}

	public String[] getNewWorker() {
		WorkerFrame frame = new WorkerFrame("", this);
		frame.setModal(true);
		frame.setVisible(true);
		
		if (frame.isOkPressed()) {
			String[] worker = new String[3];
			worker[0] = frame.getFIO();
			worker[1] = frame.getWork();
			worker[2] = frame.getPass();
			addWokerToData(worker);
			frame.dispose();
			return worker;
		}
		
		frame.dispose();
		return null;
	}

	private void addWokerToData(String[] worker) {
		data.getWorkers().add(new Worker(worker[0], worker[1], worker[2]));
	}

	public String[] updateWorker(int row) {
		WorkerFrame frame = new WorkerFrame(data.getWorkers().get(row).getFIO(), this);
		frame.setModal(true);
		frame.setVisible(true);
		
		if (frame.isOkPressed()) {
			String[] worker = new String[3];
			worker[0] = frame.getFIO();
			worker[1] = frame.getWork();
			worker[2] = frame.getPass();
			updateWorkerInData(worker, row);
			frame.dispose();
			return worker;
		}
		
		frame.dispose();
		return null;
	}

	private void updateWorkerInData(String[] worker, int row) {
		data.getWorkers().set(row, new Worker(worker[0], worker[1], worker[2]));
	}

	public void deleteWorkerFromData(int row) {
		data.getWorkers().remove(row);
	}

	public int getWorkersCount() {
		return data.getWorkers().size();
	}

	public int getClientsCount() {
		return getClientsTableData().length;
	}

	public int getPaid() {
		int money = 0;
		String[][] clients = getClientsTableData();
		for (String[] room : clients) {
			if (room[1].equals("1")) {
				money += 90;
			} else if (room[1].equals("2")) {
				money += 130;
			} else {
				money += 170;
			}
		}
		return money;
	}

	public void showReport() {
		ReportFrame frame = new ReportFrame(this);
		frame.setModal(true);
		frame.setVisible(true);
		frame.dispose();
	}
	

}
