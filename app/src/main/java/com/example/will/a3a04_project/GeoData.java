package com.example.will.a3a04_project;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.os.Environment;
import android.util.Log;
import android.content.Context;
import java.lang.Object;
import android.content.res.AssetManager;
public class GeoData {

	ArrayList<List<String>> database = new ArrayList<List<String>>(); // arraylist of lists
	ArrayList<String> types = new ArrayList<>(); // list of types of alcohol
	
	public ArrayList<List<String>> ReadData(Context c, String filename) throws IOException{
		InputStream is = c.getAssets().open(filename);
		if (is != null){
			InputStreamReader inputStreamReader = new InputStreamReader(is);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while((line = bufferedReader.readLine()) != null) {
				List<String> item = new ArrayList<String>(Arrays.asList(line.split("\\t")));
				database.add(item);
				boolean foundType = false;
				for (int i =0; i < types.size(); i++){
					if (types.get(i).equals(item.get(1))){
						foundType = true;
						break;
					}
				}
				if (foundType = false){
					types.add(item.get(1));
				}
			}
		}
		return database;
	}
	
	public void WriteData(Context c, String newEntry, int n) throws IOException{
			OutputStream os = new FileOutputStream("resources/newDatabase.txt");
			if (os != null){
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
				
				
				if (n == database.size()){
					for (int i = 0; i< database.size(); i++){
						bufferedWriter.write(database.get(i).get(0) + "\t" + database.get(i).get(1) + "\t" + 
								database.get(i).get(2) + "\t" + database.get(i).get(3) + "\t" + database.get(i).get(4) + "\t" + 
								database.get(i).get(5) + "\t" + database.get(i).get(6) + "\t" + database.get(i).get(7) + "\t" +database.get(i).get(8) + "\t" + 
								database.get(i).get(9));
						bufferedWriter.newLine();
					
					} 
					bufferedWriter.write(newEntry);
					
				} else {
					for (int i = 0; i< database.size(); i++){
						if (i ==n){
							bufferedWriter.write(newEntry);
							bufferedWriter.newLine();

						} else {
							bufferedWriter.write(database.get(i).get(0) + "\t" + database.get(i).get(1) + "\t" + 
									database.get(i).get(2) + "\t" + database.get(i).get(3) + "\t" + database.get(i).get(4) + "\t" + 
									database.get(i).get(5) + "\t" + database.get(i).get(6) + "\t" + database.get(i).get(7) + "\t" +database.get(i).get(8) + "\t" + 
									database.get(i).get(9));
							bufferedWriter.newLine();

						}
					} 
				}
				bufferedWriter.close();
			}
		}
}
	