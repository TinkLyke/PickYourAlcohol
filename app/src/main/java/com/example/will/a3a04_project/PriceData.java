package com.example.will.a3a04_project;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.*;
import java.io.IOException;
import java.util.List;

import android.os.Environment;
import android.util.Log;
import android.content.Context;
import java.lang.Object;
import android.content.res.AssetManager;
/**
 * Created by jimbo on 2017-04-03.
 */

public class PriceData {
    ArrayList<List<String>> database = new ArrayList<List<String>>();
    ArrayList<String> types = new ArrayList<>();
    /*
    public PriceData(String dataName) throws IOException
    {

        GetData(dataName);
    }*/
    public ArrayList<List<String>> ReadData(Context c,String filename)throws IOException{
        InputStream is = c.getAssets().open(filename);
        if(is != null){
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                List<String> item = new ArrayList<String>(Arrays.asList(line.split("\\t")));
                database.add(item);
                boolean foundType = false;
                for (int i = 0; i < types.size(); i++)
                {
                    if (types.get(i).equals(item.get(1)))
                    {
                        foundType = true;
                        break;
                    }
                }
                if (foundType = false)
                {
                    types.add(item.get(1));
                }
            }
        }
        return database;
    }

/*
    @SuppressWarnings("resource")
    public void GetData(String dataName) throws IOException
    {   //Read File//
        String line;
        while ((line = br.readLine()) != null) {
            List<String> item = new ArrayList<String>(Arrays.asList(line.split("\\t")));
            database.add(item);
            Log.d("SSSSS", item.get(0));
            boolean foundType = false;
            for (int i = 0; i < types.size(); i++)
            {
                if (types.get(i).equals(item.get(1)))
                {
                    foundType = true;
                    break;
                }
            }
            if (foundType = false)
            {
                types.add(item.get(1));
            }
        }

    }
    */
   /* public ArrayList<List<String>> GetDatabase()
    {
        return database;
    }*/
}
