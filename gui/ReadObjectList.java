package gui;
import java.io.*;
import java.util.*;
import java.lang.*;


public class ReadObjectList {

	private ArrayList<Object> objects;
	private String filepath;
	private FileInputStream fis;
	private ObjectInputStream ois;
	private Object obj;
	
	public ReadObjectList () {
		
	}
	
	public ArrayList<Object> read (String f) {
		this.filepath = f;
		objects= new ArrayList<Object>();
		
		try
			{
			
			fis = new FileInputStream(filepath);
			ois = new ObjectInputStream(fis);
			
			obj = new Object();
			
			objects = (ArrayList<Object>) ois.readObject();
			
			fis.close();
			ois.close();
			
			}
		catch (IOException | ClassNotFoundException e)
			{
			//Input some error code here
			}
		
		return objects;
		
	}
	
	//Should't these be functions, not constructors?
	public ArrayList<Object> read (String f, String d) {
		this.filepath = "/"+d+"/"+f;
		objects= new ArrayList<Object>();
		
		try
			{
			
			fis = new FileInputStream(filepath);
			ois = new ObjectInputStream(fis);
			
			obj = new Object();
			
			objects = (ArrayList<Object>) ois.readObject();
			
			ois.close();
			
			}
		catch (IOException | ClassNotFoundException e)
			{
			//Input some error code here
			}
		
		return objects;
	}
	
	//Maybe take in an object as well to write to a specific object type.
	
	public ArrayList<Object> getObjects() {
		return objects;
	}
	
}
