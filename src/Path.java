import java.util.ArrayList;

public class Path {
	//this for comparision of paths
	private int weight = 0;
	//this should just hold nodes the search algorithms find and then the ids can be searched and changed
	ArrayList<Node> nodes = new ArrayList<>();
	
	public void addNode(Node n) { nodes.add(n); }
	public void addWeight(int toAdd) { weight += toAdd; }
	
	public int getWeight() {return weight; }
	public ArrayList<Node> getPath(){return nodes;}
}
