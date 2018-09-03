package com.example.will.a3a04_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NtnDisplay extends AppCompatActivity implements View.OnClickListener{
    private Button SortBy_Button;
    public static TextView Displaylist;
    public static ArrayList<List<String>> NtnList;
    public NtnControl nc;

    public int sortby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ntn_display);
        SortBy_Button= (Button)findViewById(R.id.SortNtn_Button);
        Displaylist = (TextView)findViewById(R.id.NtnListTxT);
        SortBy_Button.setOnClickListener(this);
        sortby = 0;
        Displaylist.setMovementMethod(new ScrollingMovementMethod());

        try{
            nc = new NtnControl(sortby,this);
            Log.d("Null---", "----");
        }
        catch (IOException e){
            Log.d("Error1--","-------");
        }
        try {
            NtnList = nc.GetList();
            DisplayData(NtnList);
        }catch(NullPointerException n){

        }
    }

    @Override
    public void onClick(View view){
        if(view == SortBy_Button){
            ToSortOption();
        }
    }

    public void ToSortOption(){
        Intent intent = new Intent(NtnDisplay.this, PopNtnOption.class);
        startActivity(intent);
    }

    //0 - name, 1 - type, 3 - price, 5 - %alc, 7 - cal, 8 - carb, 9 - location
    public static void DisplayData(ArrayList<List<String>> list) {
        Log.d("The size of list", Integer.toString(list.size()));
        try {
            String List = "";
            //AscOrDesc = 1 - Asc, 2 - Desc
            Log.d("The size of list", Integer.toString(list.size()));

            for (int i = 0; i < 100; i++) {
                List += (String) list.get(i).get(0) +
                        "   alc%: " + (String) list.get(i).get(5) +
                        "   cal: " + (String) list.get(i).get(7) +
                        "   carb: " + (String) list.get(i).get(8) + "\n";

            }


            Displaylist.setText(List);
        }
        catch(NullPointerException e){
            // Log.d("Error2--","------");
        }
    }

    public static void UpdatedDisplayData(ArrayList<List<String>> list) {
        try {
            String List = "";
            //AscOrDesc = 1 - Asc, 2 - Desc
            //Log.d("The size of list", Integer.toString(list.size()));

            //if(PopNtnOption.sorttype == 0) {
                //Log.d("Asssss!!!","--------");
                for (int i = 0; i < list.size(); i++) {
                    List += (String) list.get(i).get(0) +
                            "   alc%: " + (String) list.get(i).get(5) +
                            "   cal: " + (String) list.get(i).get(7) +
                            "   carb: " + (String) list.get(i).get(8) + "\n";
                }
           // }
            /*
            else if (PopNtnOption.sorttype == 1){
                Log.d("Desssss","--------");
                for (int i = list.size()-1; i >= 0 ; i--) {
                    List += (String) list.get(i).get(0) +
                            "   alc%: " + (String) list.get(i).get(5) +
                            "   cal: " + (String) list.get(i).get(7) +
                            "   carb: " + (String) list.get(i).get(8) + "\n";
                }
            }*/
            Displaylist.setText(List);
        }
        catch(NullPointerException e){
            // Log.d("Error2--","------");
        }
    }

    public static ArrayList<List<String>> getNtnList(){
        return NtnList;
    }
}
