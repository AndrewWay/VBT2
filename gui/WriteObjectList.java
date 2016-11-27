package gui;
import java.io.*;
import java.util.*;
import java.lang.*;


public class WriteObjectList {

	private ArrayList<Object> objects;
	private String filepath;
	private FileOutputStream fos;
	private ObjectOutputStream oos;
	private Object obj;
	private ReadObjectList reader;
	private File file;
	
	public WriteObjectList () {
		reader = new ReadObjectList();
	}
	
	public void write (String f, Object o) {
		filepath = f;
		objects = new ArrayList<Object>();
		
		try
		{
		
		if ((file = new File(filepath)).exists()){
			objects = reader.read(filepath);
			
			file.delete();
		}
		
		file.createNewFile();
		
		objects.add(o);
		
		fos = new FileOutputStream(filepath);
		oos = new ObjectOutputStream(fos);
		
		oos.writeObject(objects);
		oos.close();
		fos.close();
		
		}
		
		catch(IOException e)
		{
			
		}
		
		
	}
	
	public void write (String f, String d, Object o) {
		filepath = "/"+d+"/"+f;
		
		try
		{
		
		if ((file = new File(filepath)).exists()){
			objects = reader.read(filepath);
			
			file.delete();
		}
		
		file.createNewFile();
		
		objects.add(o);
		
		fos = new FileOutputStream(filepath);
		oos = new ObjectOutputStream(fos);
		
		oos.writeObject(o);
		oos.close();
		
		}
		
		catch(IOException e)
		{
			
		}
	}
	
	public static void main (String[] args) {
		WriteObjectList write = new WriteObjectList();
		ReadObjectList read = new ReadObjectList();
		
		Tournament t1 = new Tournament("t1", "l1", null);
		Tournament t2 = new Tournament("t2", "l2", null);
		
		write.write("tester.txt",t1);
		
		ArrayList<Object> test = read.read("tester.txt");
		
		Tournament t1_2 = (Tournament) test.get(0);
		
		System.out.println(t1_2.getName() + "\n");
		
		write.write("tester.txt", t2);
		
		test = read.read("tester.txt");
		
		Tournament[] tt = new Tournament[2];
		
		for (int i=0; i<=1; i++) {
			tt[i] = (Tournament) test.get(i);
		}
		
		for (int i=0; i<=1; i++) {
			System.out.println(tt[i].getName() + "\n");
		}
	}
			
	
	
}
