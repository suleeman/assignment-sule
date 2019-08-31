package assignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MainDriverClass {

	public static final String OUTPUT_FILE = "MyOutput.txt";
	public static final String TEST_FILE = "MyTest.txt";

	public static void main(String[] args) throws IOException {
		String outputString = new Clinic().run();
		
		System.out.println(outputString);
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE));
	    writer.write(outputString);
	    writer.close();
	    	    
	}
}
