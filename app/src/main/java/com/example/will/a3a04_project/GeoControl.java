package com.example.will.a3a04_project;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.content.Context;

/**
 * Created by jimbo on 2017-04-03.
 */

public class GeoControl {
    GeoData data;
    ArrayList<List<String>> database = new ArrayList<List<String>>();
    ArrayList<List<String>> sortedData = new ArrayList<List<String>>();
    ArrayList<List<String>> displayData = new ArrayList<List<String>>();

    public GeoControl(Context c) throws IOException
    {
        data = new GeoData();
        database = data.ReadData(c, "newDatabase.txt");
        SortData();
    }

    public void SortData()
    {
        sortedData = database;
        geoSort(sortedData);
        displayData = sortedData;
    }
    
    public void reverseSortData(){
    	Collections.reverse(displayData);
    }

    //////////Change & Add Item////////////////////////
    /*
    public void searchItem(String alcName, String location, String alcType){
    	boolean nameFound = false;
    	for (int i =0; i<database.size(); i++){
    		if (database.get(i).get(0).equals(alcName)){
    			nameFound = true;
    			changeItem(i,alcName, location, alcType);
    		}
    	} if (nameFound = false){
    		addItem(database.size(), alcName, location, alcType);
    	}
    }
    

    public void changeItem(int i, String alcName, String location, String alcType){
    	String changedEntry = alcName + "\\t" + alcType + "\\t" + database.get(i).get(2) + "\\t" + database.get(i).get(3) 
				+ "\\t" + database.get(i).get(4) + "\\t" + database.get(i).get(5) + "\\t" + database.get(i).get(6) + "\\t" + database.get(i).get(7) + "\\t" + database.get(i).get(8) 
				+ location;
    	data.WriteData(c, changedEntry, i);
    }
    
    public void addItem(int i, String alcName, String location, String alcType){
    		String newEntry = alcName + "\\t" + alcType + "\\t" + 0 + "\\t" + 0 
    				+ "\\t" + 0 + "\\t" + 0 + "\\t" + 0 + "\\t" + 0 + "\\t" + 0 
    				+ location;
    	data.WriteData(c, newEntry, i);
    }
    */
    /*public void filterGeo(String s) {
        ArrayList<List<String>> tempFilter = new ArrayList<List<String>>();
        for (int i =0; i<database.size(); i++){
        	if (database.get(i).get(9).contains(s)){
        		tempFilter.add(database.get(i));
        	}
        }
        filterData = tempFilter;
    }*/
 



    public static void geoSort(ArrayList<List<String>> a) {
        int n = a.size();

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < n/3) h = 3*h + 1;

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && compare((a.get(j).get(9)), (a.get(j-h).get(9))); j -= h) {
                    exch(a, j, j-h);
                }
            }
            assert isHsorted(a, h);
            h /= 3;
        }
        assert isSorted(a);
    }

    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
   // private static boolean less(float v, float w) {
    //    return v < w;
    //}
    private static boolean compare(String x, String y) {
        int compare = x.compareTo(y);
        if (compare < 0){
            return true; //System.out.println(a+" is before "+b);
        }
        else {
            return false; //System.out.println(b+" after,same as "+a);
        }
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
            if (compare((a.get(i).get(10)), (a.get(i-1).get(10)))) return false;
        return true;
    }

    // is the array h-sorted?
    private static boolean isHsorted(ArrayList<List<String>> a, int h) {
        for (int i = h; i < a.size(); i++)
            if (compare((a.get(i).get(10)), (a.get(i-h).get(10)))) return false;
        return true;
    }
    
    public ArrayList<List<String>> GetList (){
        return displayData;
    }




}