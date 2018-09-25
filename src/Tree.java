import java.util.*;

import org.omg.CORBA.Current;

public class Tree {
	ArrayList<Node> nodes = new ArrayList<>();
	ArrayList<Edge> edges = new ArrayList<>();
	
	public Tree() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }
	
	public void addNode(Node n) {
        nodes.add(n);
    }

    public void addEdge(Edge e) {
        Node fromNode = e.getFromNode();
        Node toNode = e.getToNode();
        edges.add(e);
        fromNode.addOutgoingEdge(e);
        toNode.addIncomingEdge(e);
    }

    public void addEdge(Node fromNode, Node toNode) {
        Edge newEdge = new Edge(fromNode, toNode);
        edges.add(newEdge);
        fromNode.addOutgoingEdge(newEdge);
        toNode.addIncomingEdge(newEdge);
    }
    
    public Node getNode(int id) { return nodes.get(id); }
    public ArrayList<Node> getNodes(){ return nodes; }
    public int numNodes() { return nodes.size(); }
    public ArrayList<Edge> getEdges(){return edges;}
    
    public ArrayList<Node> removeDuplicates(ArrayList<Node> remove){
    	Set<Node> temp = new HashSet<>();
    	temp.addAll(remove);
    	remove.clear();
    	remove.addAll(temp);
    	return remove;
    	
    }
    
    MyComparator compare = new MyComparator();
    MyComparator2 compare2 = new MyComparator2();
    
    public ArrayList<Node> depthFirst(Node start) {
    	Stack<Node> stack = new Stack<>();
    	stack.add(start);
    	Node current;
    	while(!stack.isEmpty()) {
    		current = stack.pop();
    		if(current.visited == true) {
    			continue;
    		}
    		if(current.getContent() == '*') {
    			return current.tail;
    		}
    		current.visited = true;
    		current.tail.add(current);
    		for(Edge e : current.getOutgoingEdges()) {
    			
    			e.getToNode().tail.addAll(removeDuplicates(current.tail));
    			stack.add(e.getToNode());
    		}
    		
    	}
    	return null;
    }
    
    public ArrayList<Node> breadthFirst(Node start) {
    	LinkedList<Node> queue = new LinkedList<>();
    	queue.add(start);
    	Node current;
    	while(!queue.isEmpty()) {
    		current = queue.poll();
    		if(current.visited == true) {
    			continue;
    		}
    		if(current.getContent() == '*') {
    			return current.tail;
    		}
    		current.visited = true;
    		current.tail.add(current);
    		for(Edge e : current.getOutgoingEdges()) {
    			if(e.getToNode().tail.size() <= current.tail.size() && e.getToNode().tail.size() != 0) { continue; } // has to here to remove possiblity of two equal length lines
    			e.getToNode().tail.addAll(removeDuplicates(current.tail));
    			queue.add(e.getToNode());
    		}
    		
    	}
    	return null;
    }

    public ArrayList<Node> greedyFirst(Node start) {
    	PriorityQueue<Node> pq = new PriorityQueue<>(compare);
    	pq.add(start);
    	Node current;
    	while(!pq.isEmpty()) {
    		current = pq.poll();
    		if(current.visited == true) {
    			continue;
    		}
    		if(current.getContent() == '*') {
    			return current.tail;
    		}
    		current.visited = true;
    		current.tail.add(current);
    		for(Edge e : current.getOutgoingEdges()) {
    			e.getToNode().tail.addAll(removeDuplicates(current.tail));
    			pq.add(e.getToNode());
    		}
    		
    	}
    	return null;
    }
    
    public ArrayList<Node> AStar(Node start){
    	PriorityQueue<Node> pq = new PriorityQueue<>(compare2);
    	pq.add(start);
    	Node current;
    	while(!pq.isEmpty()) {
    		current = pq.poll();
    		if(current.visited == true) {
    			continue;
    		}
    		if(current.getContent() == '*') {
    			return current.tail;
    		}
    		current.visited = true;
    		current.tail.add(current);
    		for(Edge e : current.getOutgoingEdges()) {
    			if(e.getToNode().tail.size() <= current.tail.size() && e.getToNode().tail.size() != 0) { continue; } // has to here to remove possiblity of two equal length lines
    			e.getToNode().tail.addAll(removeDuplicates(current.tail));
    			pq.add(e.getToNode());
    		}
    	}
    	return null;
    }
    
}

class MyComparator implements Comparator<Node>{
	public int compare(Node a, Node b) {
		Integer atail = a.getManhattan();
		Integer btail = b.getManhattan();
		return atail.compareTo(btail);
	}
}

class MyComparator2 implements Comparator<Node>{
	public int compare(Node a, Node b) {
		Integer aR = a.getManhattan() + a.tail.size();
		Integer bR = b.getManhattan() + b.tail.size();
		return aR.compareTo(bR);
	}
}
