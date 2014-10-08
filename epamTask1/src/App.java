package edu.aturkin.tree;

import java.io.Console;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.HashMap;
import java.util.Map;

import edu.aturkin.tree.model.Tree;

public class App {

	private static Console console;
	
	private static Tree tree;
	
	private static Map<Character, Tree.Node> nodes;
	
	public static void main(String[] args) {
		console = System.console();
		char content = '0';
		Tree.Node nodeToAdd;
		boolean exit = false;
		while (!exit) {
			switch (printMenu()) {
				case 1:
					content = console.readLine("Enter root label: ").charAt(0);
					Tree.Node root = new Tree.Node(content);
					tree = new Tree(root);
					
					nodes = new HashMap<Character, Tree.Node>();
					nodes.put(content, root);
					break;
				case 2:
					nodeToAdd = findNode();
					
					Tree.Node left = createNode();
					nodeToAdd.setLeft(left);
					
					nodes.put(left.getContent(), left);
					break;
				case 3:
					nodeToAdd = findNode();
					
					Tree.Node right = createNode();
					nodeToAdd.setRight(right);
					
					nodes.put(right.getContent(), right);
					break;
				case 4:
					System.out.println(tree.print());
					
					long start = System.currentTimeMillis();
					String walk = tree.walk();
					int nodesCountWithDepthMoreWhenTwo = tree.countNodesWithDepthMoreWhenTwo();
					long duration = start - System.currentTimeMillis();
					
					System.out.println("Walk: " + walk);
					System.out.println("Nodes with depth more than two " + nodesCountWithDepthMoreWhenTwo);
					System.out.println("Time duration: " + duration);
					break;
				case 5:
					exit = true;
					break;
			}
		}
	}
	
	private static Tree.Node findNode() {
		char content;
		boolean success = false;
		Tree.Node node = null;
		while (!success) {
			content = console.readLine("Enter label of node where add: ").charAt(0);
			
			if (!nodes.containsKey(content)) {
				success = false;
				System.out.println("Node with label '" + content + "' doesnt exists");
			} else {
				success = true;
				node = nodes.get(content);
			}
		}
		
		return node;
	}
	
	private static Tree.Node createNode() {
		boolean success = false;
		char content = '0';
		while (!success) {
			content = console.readLine("Enter child label: ").charAt(0);
			
			if (nodes.containsKey(content)) {
				success = false;
				System.out.println("Node with label '" + content + "' exists");
			} else {
				success = true;
			}
		}
		Tree.Node node = new Tree.Node(content);
		
		return node;
	}

	public static int printMenu() {
		int command = 0;
		boolean success = false;
		
		while (!success) {
			try {
				System.out.println("1. Create new tree");
				System.out.println("2. Add left child");
				System.out.println("3. Add right child");
				System.out.println("4. Print tree");
				System.out.println("5. Exit");
				
				command = Integer.valueOf(console.readLine("Enter command: "));
				success = true;
			} catch (NumberFormatException e) {
				success = false;
			}
		}
		
		return command;
	}
}
