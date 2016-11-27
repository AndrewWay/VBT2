package gui;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class WriteObject {
	private ArrayList<Object> object;
	private String filename;

	public WriteObject() {
		ReadObject r = new ReadObject(filename);
		object = r.getObjects();
	}

	public ArrayList<Object> getObject() {
		return object;
	}
	public void setObject(ArrayList<Object> o){
		object =o;
	}
	public void addObject(Object object) {
		
		this.object.add(object);
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void writeObject() {
		try {

			File file = new File(filename);
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			oos.writeObject(object);

			oos.close();
			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
