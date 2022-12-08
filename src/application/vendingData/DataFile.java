package application.vendingData;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class DataFile {
	
	protected String directoryFile;
	protected String inputFile;
	
	public DataFile(String dirName, String inputName) {
		directoryFile = dirName;
		inputFile = inputName;
	}
	public DataFile(String dirName) {
		directoryFile = dirName;
		inputFile = null;
	}
	
	public ArrayList<String> loadDirectory() {
		Scanner scanner = null;
		ArrayList<String> lines = new ArrayList<String>();
		try {
			scanner = new Scanner(new FileReader("src/dataFiles/" + directoryFile));
			int count = 1;
			while (scanner.hasNextLine()) {
            	String line = scanner.nextLine();
                lines.add(line);
                System.out.println("directory: line " + count++ + ": " + line);
            }
		} catch (FileNotFoundException e) {
			System.out.println("File not Found:");
			e.printStackTrace();
			System.exit(0);
		}
        return lines;
	}
	
	public ArrayList<Integer> loadSampleInput() {
		Scanner scanner = null;
		ArrayList<Integer> inputs = new ArrayList<Integer>();
		try {
			scanner = new Scanner(new FileReader("src/dataFiles/" + inputFile));
			int count = 1;
			while (scanner.hasNextInt()) {
				int input = scanner.nextInt();
				inputs.add(input);
				System.out.println("input: line " + count++ + ": " + input);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not Found:");
			e.printStackTrace();
			System.exit(0);
		}
        return inputs;
	}

}
