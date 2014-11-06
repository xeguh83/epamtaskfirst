package ru.epam.task2.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.epam.task2.gui.Task;

public class Graph extends Task {

	public Graph(String shortName) {
		super(shortName);
		
	}

	@Override
	protected void doLogic() {
		// TODO Auto-generated method stub
//		Collections.b
//		Math.ceil(double)
	}

	@Override
	protected void drawTitle() {
		
		
	}
		
	public class numGraph {
		
		private final HashMap<Integer, List<Integer>> graphMap = new HashMap<Integer, List<Integer>>();
		
		public numGraph(int vertexCount) {
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
	        if (hasEdge(vertex2, vertex1) || hasEdge(vertex1, vertex2)) return true;
	        List<Integer> edges1 = graphMap.get(vertex1);
	        List<Integer> edges2 = graphMap.get(vertex2);
	        edges1.add(vertex2);
	        edges2.add(vertex1);
	        Collections.sort(edges1);
	        Collections.sort(edges2);
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
	        List<Integer> edges2 = graphMap.get(vertex2);
	        edges1.remove(vertex2);
	        edges2.remove(vertex1);
	        Collections.sort(edges1);
	        Collections.sort(edges2);
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
