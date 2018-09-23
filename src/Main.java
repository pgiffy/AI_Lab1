import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		/*Notes Section:
		 * Done by Peter Gifford and Sam Behrens
		 * 
		 * So right now there is a tree called maze that has all the nodes connected with edges to each other
		 * If something is a parent of another node there is an arraylist that holds it
		 * 
		 * I used getters and setters because my old code had it. Its a little annoying but I'm just gonna role with it cause I think it makes it easier and cleaner
		 * Also, the nodes are still linked back to the original maze so if you use setContent, the node placement in the maze will change along with it
		 * 
		 * totalMaze is the original grid of maze nodes and maze is the tree
		 * 
		 * I started the path class but I didn't write a search yet so I think it is probably missing little things
		 * I added the txt files so you can just change fileName to change the graph rather than file path
		 * 
		 * I'm pretty sure this base stuff is all good, but its kinda late and as much as I want to write a toString and read all the connections to check, I don't. And I refuse to make a smaller test graph because my keyboard clicks sound louder than normal and the idea of hitting % more than three times really bothers me. Also my hands are really cold despite all these blankets that I'm using right now. So I'm going to stop typing. IDK why I still am honestly. Maybe it was the shitty tasting mustle milk I drank. God it was so Grainy. 
		 * 
		 * Greedy should be done but should probably check the comparator
		 * 
		 * Need to fix graph generation, made mistake
		 * 
		 * */
		
		Scanner scan;
		String fileOut = "output.txt";
		String fileName = "open_maze.txt";
		PrintWriter out;
		try {
			File mazeFile = new File(fileName);
			out = new PrintWriter(new File(fileOut));
			scan = new Scanner(mazeFile);
			int xLength = 0;
			int yLength = 1;//adjusting for reading first line for x
			String temp = scan.nextLine();
			xLength = temp.length();
			while(scan.hasNextLine()) {
				scan.nextLine();
				yLength++;
			}

			int idIter = 0;
			int perLineIter = 0;
			int lineIter = 0;
			
			scan = new Scanner(mazeFile);//reseting scanner to the top
			Node[][] totalMaze = new Node[yLength][xLength];
			while(scan.hasNext()) {
				String currentLine = scan.nextLine();
				perLineIter = 0;
				for(char con : currentLine.toCharArray()) {
					totalMaze[lineIter][perLineIter] = new Node(idIter, con, perLineIter, lineIter);
					idIter++;
					perLineIter++;
				}
				lineIter++;
				//totalMaze has nodes of all 
			}
			
			Tree maze = new Tree();
			for(int y = 0; y < yLength; y++) {
				for(int x = 0; x < xLength; x++) {
					Node current = totalMaze[y][x];
					try {
						if((totalMaze[y+1][x].getContent() == ' ' || totalMaze[y+1][x].getContent() == '*') && totalMaze[y+1][x] != null) {
							Edge e = new Edge(current, totalMaze[y+1][x]);
							totalMaze[y+1][x].addParent(current);
							maze.addEdge(e);
							maze.addNode(totalMaze[y+1][x]);
						}
						if((totalMaze[y][x+1].getContent() == ' ' || totalMaze[y][x+1].getContent() == '*') && totalMaze[y][x+1] != null) {
							Edge e = new Edge(current, totalMaze[y][x+1]);
							totalMaze[y][x-1].addParent(current);
							maze.addEdge(e);
							maze.addNode(totalMaze[y][x+1]);
						}
						if((totalMaze[y-1][x].getContent() == ' ' || totalMaze[y-1][x].getContent() == '*') && totalMaze[y-1][x] != null) {
							Edge e = new Edge(current, totalMaze[y-1][x]);
							totalMaze[y-1][x].addParent(current);
							maze.addEdge(e);
							maze.addNode(totalMaze[y-1][x]);
						}
						if((totalMaze[y][x-1].getContent() == ' ' || totalMaze[y][x-1].getContent() == '*') && totalMaze[y][x-1] != null) {
							Edge e = new Edge(current, totalMaze[y][x-1]);
							totalMaze[y][x-1].addParent(current);
							maze.addEdge(e);
							maze.addNode(totalMaze[y][x-1]);
						}
					}catch(ArrayIndexOutOfBoundsException e) {

					}
				}

			}

//			for(Node n : maze.getNodes()) {
//				n.setContent('.');
//			}
			Node start = null;
			Node end = null;
			for(int i = 0; i < yLength; i++) {
				for(int j = 0; j < xLength; j++) {
					if(totalMaze[i][j].getContent() == 'P') { start = totalMaze[i][j]; }
					if(totalMaze[i][j].getContent() == '*') { end = totalMaze[i][j]; }
				}
			}
			for(int i = 0; i < yLength; i++) {
				for(int j = 0; j < xLength; j++) {
					totalMaze[i][j].setEnd(end);
				}
			}
			
			ArrayList<Node> solve = maze.greedyFirst(start);
			for(Node n : solve) {
				n.setContent('.');
			}

			//for easy viewing sake
			//later change this to out.prints to put to output file
			for(int i = 0; i < yLength; i++) {
				for(int j = 0; j < xLength; j++) {
					System.out.print(totalMaze[i][j].getContent());
				}
				System.out.println();
			}
			
			
			out.close();
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		
		
	}

}
