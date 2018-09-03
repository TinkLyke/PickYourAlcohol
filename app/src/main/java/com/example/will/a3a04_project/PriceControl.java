package com.example.will.a3a04_project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.content.Context;

/**
 * Created by jimbo on 2017-04-03.
 */

public class PriceControl {
    PriceData data;
    ArrayList<List<String>> database = new ArrayList<List<String>>();
    ArrayList<List<String>> sortedData = new ArrayList<List<String>>();
    ArrayList<List<String>> displayData = new ArrayList<List<String>>();

    public PriceControl(Context c) throws IOException
    {
        data = new PriceData();
        database = data.ReadData(c,"newDatabase.txt");
        SortData();
    }

    public void SortData()
    {
        sortedData = database;
        sort(sortedData);
        displayData = sortedData;
    }

    public void ReverseData()
    {
        Collections.reverse(displayData);
    }

    public void SortType(String type)
    {
        displayData = new ArrayList<List<String>>();
        AddType(type);
    }

    public void AddType(String type)
    {
        for (int i = 0; i < sortedData.size(); i++)
        {
            if(sortedData.get(i).get(1).equals(type))
            {
                displayData.add(sortedData.get(i));
            }
        }
        sort(displayData);
    }

    public static void sort(ArrayList<List<String>> a) {
        int n = a.size();

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < n/3) h = 3*h + 1;

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(Float.parseFloat(a.get(j).get(3)), Float.parseFloat(a.get(j-h).get(3))); j -= h) {
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
            if (less(Float.parseFloat(a.get(i).get(3)), Float.parseFloat(a.get(i-1).get(3)))) return false;
        return true;
    }

    // is the array h-sorted?
    private static boolean isHsorted(ArrayList<List<String>> a, int h) {
        for (int i = h; i < a.size(); i++)
            if (less(Float.parseFloat(a.get(i).get(3)), Float.parseFloat(a.get(i-h).get(3)))) return false;
        return true;
    }

    public ArrayList<List<String>> GetList (){
        return displayData;
    }
}
