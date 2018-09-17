import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		Scanner scan;
		String fileOut = "";
		String fileName = ""; //initialize here
		PrintWriter out;
		try {
			File mazeFile = new File(fileName);
			out = new PrintWriter(new File(fileOut));
			scan = new Scanner(mazeFile);
			
			
			
			
			out.close();
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		
		
	}

}
