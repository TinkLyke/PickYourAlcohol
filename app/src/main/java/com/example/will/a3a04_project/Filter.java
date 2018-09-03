package com.example.will.a3a04_project;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by will on 2017-04-06.
 */

public class Filter {
    public static ArrayList<List<String>> al;
    private ArrayList<List<String>> newal = new ArrayList<List<String>>();
    Filter(){
        this.al = new ArrayList<List<String>>();

    }

    public void SetArraylist(ArrayList<List<String>> pricelist){
        this.al = pricelist;
    }

    public void SortByPrice(float max, float min){
        newal = new ArrayList<List<String>>();
        if(max != -1) {//limit max
            for (int i = 0; i < al.size(); i++) {
                if (Float.parseFloat(al.get(i).get(3)) <= max){
                        newal.add(al.get(i));
                }
                if((i+1)<al.size()){
                    if(Float.parseFloat(al.get(i+1).get(3)) > max){
                        break;
                    }

                }

            }

            //Log.d("Size of newal ", Integer.toString(newal.size()));
            al = new ArrayList<List<String>>();
            al = SetTo(newal);
            newal = new ArrayList<List<String>>();
            //Log.d("Size of newal !", Integer.toString(al.size()));
        }
        if(min != -1){
            for (int i = 0; i < al.size(); i++) {
                if (Float.parseFloat(al.get(i).get(3)) >= min){
                    newal.add(al.get(i));
                }
            }
            Log.d("Size of newal !! ", Integer.toString(newal.size()));
            al = new ArrayList<List<String>>();
            al = SetTo(newal);
            newal = new ArrayList<List<String>>();
        }
    }

    public void SortByGeo(String location){
        //Log.d("Size of oldal !! ", Integer.toString(al.size()));
        newal = new ArrayList<List<String>>();
        if(!location.equals("-1")){
            //should be al.size()
            for (int i = 0; i < 100; i++) {
                if(al.get(i).get(9).equals(location)){
                    newal.add(al.get(i));
                }
            }

            al = new ArrayList<List<String>>();
            al = SetTo(newal);
            newal = new ArrayList<List<String>>();
            //Log.d("Size of al !! ", Integer.toString(al.size()));
        }

    }

    public void SortByNutrition(float maxalc, float minalc, float maxcal, float mincal, float maxcarb, float mincarb){
        newal = new ArrayList<List<String>>();
        Log.d("Size of newal ", Integer.toString(newal.size()));
        Log.d("Size of al ", Integer.toString(al.size()));
        Log.d("Size of maxcal ", Float.toString(maxcal));
        if(maxalc != -1) {//limit max
            for (int i = 0; i < al.size(); i++) {
                if (Float.parseFloat(al.get(i).get(5)) <= maxalc){
                    newal.add(al.get(i));
                }
                if((i+1)<al.size()){
                    if(Float.parseFloat(al.get(i+1).get(5)) > maxalc){
                        break;
                    }

                }

            }

            Log.d("Size of newal maxalc ", Integer.toString(newal.size()));
            al = new ArrayList<List<String>>();
            al = SetTo(newal);
            newal = new ArrayList<List<String>>();
            Log.d("Size of newal minalc!", Integer.toString(al.size()));
        }
        if(minalc != -1){
            for (int i = 0; i < al.size(); i++) {
                if (Float.parseFloat(al.get(i).get(5)) >= minalc){
                    newal.add(al.get(i));
                }
            }
            Log.d("Size of newal minalc!! ", Integer.toString(newal.size()));
            al = new ArrayList<List<String>>();
            al = SetTo(newal);
            newal = new ArrayList<List<String>>();
        }
        Log.d("Size of al maxcal!! ", Integer.toString(al.size()));
        if(maxcal != -1){
            for (int i = 0; i < al.size(); i++) {
                if (Float.parseFloat(al.get(i).get(7)) <= maxcal){
                    newal.add(al.get(i));
                }
            }
            Log.d("Size of newal maxcal!! ", Integer.toString(newal.size()));
            al = new ArrayList<List<String>>();
            al = SetTo(newal);
            newal = new ArrayList<List<String>>();
        }
        if(mincal != -1){
            for (int i = 0; i < al.size(); i++) {
                if (Float.parseFloat(al.get(i).get(7)) >= mincal){
                    newal.add(al.get(i));
                }
            }
            Log.d("Size of newal mincal!! ", Integer.toString(newal.size()));
            al = new ArrayList<List<String>>();
            al = SetTo(newal);
            newal = new ArrayList<List<String>>();
        }
        if(maxcarb != -1){
            for (int i = 0; i < al.size(); i++) {
                if (Float.parseFloat(al.get(i).get(8)) <= maxcarb){
                    newal.add(al.get(i));
                }
            }
            Log.d("Size of newa mcarb!! ", Integer.toString(newal.size()));
            al = new ArrayList<List<String>>();
            al = SetTo(newal);
            newal = new ArrayList<List<String>>();
        }

        if(mincarb != -1){
            for (int i = 0; i < al.size(); i++) {
                if (Float.parseFloat(al.get(i).get(8)) >= mincarb){
                    newal.add(al.get(i));
                }
            }
            Log.d("Size of newal mincar!! ", Integer.toString(newal.size()));
            al = new ArrayList<List<String>>();
            al = SetTo(newal);
            newal = new ArrayList<List<String>>();
        }
    }

    public ArrayList<List<String>> ReturnResultList(){
        return al;
    }

    public ArrayList<List<String>> SetTo(ArrayList<List<String>> From){
        ArrayList<List<String>> To = new ArrayList<List<String>>();
        for(int i = 0; i< From.size(); i ++){
            To.add(i, From.get(i));
        }
        return To;

    }


}
