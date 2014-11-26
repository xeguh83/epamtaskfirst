package ru.epam.task2.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ru.epam.task2.gui.Task;

/**
 * <p>Класс задания № 13</p>
 * Класс содержит поле типа числового графа и методы для работы с ним
 * @author Туркин А.К.
 */
public class Graph extends Task {
	
	/**
	 * Граф реализованный внутренним классом <code>NumGraph</code>
	 */
	private NumGraph graph;

	/**
	 * Конструктор передает в наследуемый класс краткое описание задачи, переданное в параметре
	 * @param shortName
	 */
	public Graph(String shortName) {
		super(shortName);
	}

	/**
	 * Метод запрашивает у пользователя количество вершин графа, затем инициализиует граф и начинает циклически запрашивать пользователя о действиях с полученным графом 
	 */
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

	/**
	 * Метод циклически отрисовывет экран который состоит из панели задания, текущего содержимого графа и запроса пользователю на введение вершин удаляемого ребра. 
	 * В случае ошибки формата введеного ребра, пользователю выводится сообщение об ошибке и запрос повторяется. В случае корректного ввода
	 * существующего в графе ребра, данное ребро удаляется и метод завершает работу.
	 */
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
	
	/**
	 * Метод циклически отрисовывет экран который состоит из панели задания, текущего содержимого графа и запроса пользователю на ввод вершин добавляемого ребра.
	 * В случае ошибки формата введеного ребра, пользователю выводится сообщение об ошибке и запрос повторяется. В случае корректного ввода
	 * ребра, данное ребро добавляется в граф и метод завершает работу.
	 */
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

	/**
	 * Метод циклически выполняет запрос пользователю о количестве вершин в будущем графе. В случае ошибочного ввода 
	 * пользователь информируется об это и запрос повторяется. В случае корректного ввода возвращается значение количества вершин в графе
	 * @return количество вершин в графе
	 */
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

	/* (non-Javadoc)
	 * @see ru.epam.task2.gui.Task#drawTitle()
	 */
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
		
	/**
	 * Внутренний служебный класс который реализует работу графа содержащего целые числа
	 * @author Туркин А.К.
	 */
	public class NumGraph {
		
		/**
		 * Коллекция содержащая данные графа (ключи - значения вершин, а значения коллекции - это списки других вершин, связанных ребрами с текущей)
		 */
		private final HashMap<Integer, List<Integer>> graphMap = new HashMap<Integer, List<Integer>>();
		
		/**
		 * Конструктор задающий вершины графа целыми числами последовательно от 1 до переданного числа вершин. Также конструктор инициализирует списки связанных 
		 * вершин для каждой вершины графа
		 * @param vertexCount количество вершин
		 */
		public NumGraph(int vertexCount) {
			for (int i = 1; i <= vertexCount; i++) {
				graphMap.put(Integer.valueOf(i), new ArrayList<Integer>());
			}
		}
		
		/**
		 * Метод определяет есть ли вершина в графе
		 * @param vertexNumber номер вершины
		 * @return <code>true</code> если такая вершина в графе есть, и <code>false</code> в обратном случае
		 */
		public boolean hasVertex (Integer vertexNumber) {
			return graphMap.containsKey(vertexNumber);
		}
		
		/**
		 * Метод определяет есть ли указанное в параметре ребро в текущем составе графа
		 * @param vertex1 номер первой вершины ребра
		 * @param vertex2 номер второй вершины ребра
		 * @return <code>true</code> если такое ребро в графе есть, и <code>false</code> в обратном случае
		 */
		public boolean hasEdge (Integer vertex1, Integer vertex2) {
	        List<Integer> edges = graphMap.get(vertex1);
	        return Collections.binarySearch(edges, vertex2) >= 0;
		}
		
	    /**
	     * Метод добавляет ребро в граф по указанным вершинам
	     * @param vertex1 первая вершина ребра
	     * @param vertex2 вторая вершина ребра
	     * @return <code>true</code> если ребро (не петля) успешно добавлено в граф, и <code>false</code> в обратном случае
	     */
	    public boolean addEdge(Integer vertex1, Integer vertex2) {
	        if (vertex1.equals(vertex2)) return false;
	        if (!hasVertex(vertex1) || !hasVertex(vertex2)) return false;
	        if (hasEdge(vertex2, vertex1) || hasEdge(vertex1, vertex2)) return false;
	        List<Integer> edges1 = graphMap.get(vertex1);
	        edges1.add(vertex2);
	        Collections.sort(edges1); // сортирует список связанных вершин при изменении списка для применения бинарного поиска вершины в списке
	        return true;
	    }
	    
	    /**
	     * Метод удаляет ребро из графа по указанным вершинам
	     * @param vertex1 первая вершина ребра
	     * @param vertex2 вторая вершина ребра
	     * @return <code>true</code> если ребро успешно удалено из графа, и <code>false</code> в обратном случае
	     */
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
	        Collections.sort(edges1); // сортирует список связанных вершин при изменении списка для применения бинарного поиска вершины в списке
	        return true;
	    }
	    
	    /* (non-Javadoc)
	     * @see java.lang.Object#toString()
	     */
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
