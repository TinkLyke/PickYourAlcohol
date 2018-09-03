package com.example.will.a3a04_project;
import android.content.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by jimbo on 2017-04-03.
 */

public class NtnControl {
    NtnData data;
    ArrayList<List<String>> database = new ArrayList<List<String>>();
    ArrayList<List<String>> sortedData = new ArrayList<List<String>>();
    ArrayList<List<String>> displayData = new ArrayList<List<String>>();

    //sortBy determines what value to sort by.
    //Default is %alcohol (sortBy = anything), sortBy = 1 for calories, sortBy = 2 for carbs 
    public NtnControl(int sortBy, Context c) throws IOException
    {
        data = new NtnData();
        database = data.ReadData(c, "newDatabase.txt");
        SortData(sortBy);
    }

    public void SortData(int sortBy)
    {
        sortedData = database;
        sort(sortedData, sortBy);
        displayData = sortedData;
    }

    public void ReverseData()
    {
        Collections.reverse(displayData);
    }

    public void SortType(String type, int sortBy)
    {
        displayData = new ArrayList<List<String>>();
        AddType(type, sortBy);
    }

    public void AddType(String type, int sortBy)
    {
        for (int i = 0; i < sortedData.size(); i++)
        {
            if(sortedData.get(i).get(2).equals(type))
            {
                displayData.add(sortedData.get(i));
            }
        }
        sort(displayData, sortBy);
    }

    //select determins what attribute to sort by. 0 for %alc, 1 for calories, 2 for carbs. If any other number, defaults to sorting by %alc
    public static void sort(ArrayList<List<String>> a, int select) {
        int n = a.size();
        int sortBy = 5;
        switch (select){
        case 1: sortBy = 7;
        case 2: sortBy = 8;
        }

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < n/3) h = 3*h + 1;

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(Float.parseFloat(a.get(j).get(sortBy)), Float.parseFloat(a.get(j-h).get(sortBy))); j -= h) {
                    exch(a, j, j-h);
                }
            }
            assert isHsorted(a, h);
            h /= 3;
        }
        assert isSorted(a);
    }


    public void modify(String searchFor, int searchBy, String newData, String fileName) throws IOException{
    	int key = 0;
    	for (int i = 0; i < this.database.size(); i++){
    		if(this.database.get(i).get(0).equals(searchFor))
    			key = i;
    	}
    	this.database.get(key).set(searchBy, newData);
    	BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName), true));
    	for (int i = 0; i < this.database.size(); i++){
    		for (int j = 0; j < this.database.get(i).size(); j++)
    			bw.write(this.database.get(i).get(j) + "\t");
		    bw.newLine();
    	}
    	NtnControl.sort(this.database, 0);
    }
    
    public void add(String newData, String fileName) throws IOException{
    	List<String> toList = Arrays.asList(newData.split("\t"));
    	this.database.add(toList);
    	BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName), true));
    	bw.write(newData);
    	NtnControl.sort(this.database, 0);
    }
    
    /*public static int binarySearch(ArrayList<List<String>> data, int sortedBy, String searchFor){
    	int low = 0;
    	int high = data.size() - 1;
    	double key = Double.parseDouble(searchFor);
    	while(high >= low){
    		int middle = (low+high)/2;
    		if (Double.parseDouble(data.get(middle).get(sortedBy)) == key){
    			return middle;
    		}
    		if(Double.parseDouble(data.get(middle).get(sortedBy)) < key){
    			low = middle + 1;
    		}
    		if(Double.parseDouble(data.get(middle).get(sortedBy)) > key){
    			high = middle - 1;
    		}
    	}
    	return 0;
    }*/

    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(float v, float w) {
        return v < w;
    }

    // exchange a[i] and a[j]
    private static void exch(ArrayList<List<String>> a, int i, int j) {
        List<String> swap = a.get(i);
        a.set(i, a.get(j));
        a.set(j, swap);
    }


    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/
    private static boolean isSorted(ArrayList<List<String>> a) {
        for (int i = 1; i < a.size(); i++)
            if (less(Float.parseFloat(a.get(i).get(7)), Float.parseFloat(a.get(i-1).get(7)))) return false;
        return true;
    }

    // is the array h-sorted?
    private static boolean isHsorted(ArrayList<List<String>> a, int h) {
        for (int i = h; i < a.size(); i++)
            if (less(Float.parseFloat(a.get(i).get(7)), Float.parseFloat(a.get(i-h).get(7)))) return false;
        return true;
    }

    public ArrayList<List<String>> GetList (){
        return displayData;
    }
}
