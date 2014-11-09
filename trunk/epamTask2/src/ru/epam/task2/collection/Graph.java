package ru.epam.task2.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ru.epam.task2.gui.Task;

public class Graph extends Task {
	
	private NumGraph graph;

	public Graph(String shortName) {
		super(shortName);
	}

	@Override
	protected void doLogic() {
		int vertexCount = askVertexCount();
		graph = new NumGraph(vertexCount);
		while (true) {
			drawSreen(graph);
			String option = askOption(" Введите действие с ребром (\"+\" добавление, \"-\" удаление, 0 выход в меню): ");
			if (option.equals("+")) {
				addEdgeToGraph();
			} else if (option.equals("-")) {
				removeEdgeFromGraph();
			} else if (option.equals("0")) {
				return;
			}
		}
	}

	private void removeEdgeFromGraph() {
		while (true) {
			drawSreen(graph);
			System.out.print(" Введите вершины удаляемого ребра (формат X,Y)");
			try {
				Scanner in = new Scanner(System.in);
				String[] words = in.nextLine().split(",");
				if (words.length != 2) {
					continue;
				}
				int[] vertexes = new int[words.length];
				for (int i = 0; i < words.length; i++) {
					vertexes[i] = Integer.parseInt(words[i]);
				}
				if (!graph.removeEdge(vertexes[0], vertexes[1])){
					printError(graph, " Ошибка удаления ребра из графа!");
				} else {
					return;
				}
			} catch (Exception e) {
				continue;
			}
		}
		
	}
	
	private void addEdgeToGraph() {
		while (true) {
			drawSreen(graph);
			System.out.print(" Введите вершины добавляемого ребра (формат X,Y)");
			try {
				Scanner in = new Scanner(System.in);
				String[] words = in.nextLine().split(",");
				if (words.length != 2) {
					continue;
				}
				int[] vertexes = new int[words.length];
				for (int i = 0; i < words.length; i++) {
					vertexes[i] = Integer.parseInt(words[i]);
				}
				if (!graph.addEdge(vertexes[0], vertexes[1])) {
					printError(graph, " Ошибка добавления ребра в граф!");
				} else {
					return;
				}
			} catch (Exception e) {
				continue;
			}
		}
		
	}

	private int askVertexCount() {
		while (true) {
			drawTitle();
			printEmptyLines(15);
			System.out.print(" Введите положительное количество вершин в графе: ");
			int count;
			try {
				Scanner in = new Scanner(System.in);
				count = Integer.parseInt(in.nextLine());
				if (count > 0) {
					return count;
				}
			} catch (Exception e) {
				continue;
			}
		}
	}

//	protected int drawStructure() {
//		String[] strings = splitStringEvery(graph.toString(), 79);
//		for (String string : strings) {
//			System.out.println(string);
//		}
//		return strings.length;
//	}

	@Override
	protected void drawTitle() {
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║Задача №13. Реализовать класс - неориентированный граф. В конструкторе пере-║\r\n" + 
				 			" ║ дать количество вершин. Реализовать методы быстрого добавления и удаления. ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
		 System.out.println(" ╔════════════════════════════════════════════════════════════════════════════╗\r\n" +
				 			" ║ Добавление/удаление ребер происходит через запрос действия у пользователя: ║\r\n" +
				 			" ║ \"+\" добавление, \"-\" удаление, 0 выход в меню. Результат выводится на экран ║\r\n" +
				 			" ╚════════════════════════════════════════════════════════════════════════════╝");
	}
		
	public class NumGraph {
		
		private final HashMap<Integer, List<Integer>> graphMap = new HashMap<Integer, List<Integer>>();
		
		public NumGraph(int vertexCount) {
			for (int i = 1; i <= vertexCount; i++) {
				graphMap.put(Integer.valueOf(i), new ArrayList<Integer>());
			}
		}
		
		public boolean hasVertex (Integer vertexNumber) {
			return graphMap.containsKey(vertexNumber);
		}
		
		public boolean hasEdge (Integer vertex1, Integer vertex2) {
	        List<Integer> edges = graphMap.get(vertex1);
	        return Collections.binarySearch(edges, vertex2) >= 0;
		}
		
	    public boolean addEdge(Integer vertex1, Integer vertex2) {
	        if (vertex1.equals(vertex2)) return false;
	        if (!hasVertex(vertex1) || !hasVertex(vertex2)) return false;
	        if (hasEdge(vertex2, vertex1) || hasEdge(vertex1, vertex2)) return false;
	        List<Integer> edges1 = graphMap.get(vertex1);
	        edges1.add(vertex2);
	        Collections.sort(edges1);
	        return true;
	    }
	    
	    public boolean removeEdge (Integer vertex1, Integer vertex2) {
	        if (vertex1.equals(vertex2)) return false;
	        if (!hasVertex(vertex1) || !hasVertex(vertex2)) return false;
	        if (hasEdge(vertex2, vertex1)) {
	        	Integer temp = vertex1;
	        	vertex1 = vertex2;
	        	vertex2 = temp;
	        }
	        List<Integer> edges1 = graphMap.get(vertex1);
	        edges1.remove(vertex2);
	        Collections.sort(edges1);
	        return true;
	    }
	    
	    @Override
	    public String toString() {
	    	StringBuilder sb = new StringBuilder("");
			for (Map.Entry<Integer, List<Integer>> entry: graphMap.entrySet()) {
				for (Integer vertex : entry.getValue()) {
					sb.append(" (" + entry.getKey() + ", " + vertex + ")");
				}
			}
	    	return sb.toString();
	    }
	}
}
