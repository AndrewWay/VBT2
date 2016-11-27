package gui;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadFile {
	private ArrayList<String> lines;
	private String filename;

	public ReadFile(String f) {
		filename = f;
		lines = new ArrayList<String>();

		try {

			File file = new File(filename);

			// if file doesn't exists, then create it
			if (file.exists()) {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				while ((line = reader.readLine()) != null) {
					lines.add(line);
				}
				reader.close();
			}
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();

		}

	}

	public ArrayList<String> getLines() {
		return lines;
	}

}
