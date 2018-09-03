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

public class PriceDisplay extends AppCompatActivity implements View.OnClickListener {
    private Button SortBy_Button;
    public static TextView Displaylist;
    public static ArrayList<List<String>> PriceList;
    public PriceControl pc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_display);
        SortBy_Button= (Button)findViewById(R.id.SortPrice_Button);
        Displaylist = (TextView)findViewById(R.id.PriceListTxT);
        SortBy_Button.setOnClickListener(this);

        Displaylist.setMovementMethod(new ScrollingMovementMethod());

        try{
           pc = new PriceControl(this);
        }
        catch (IOException e){
            Log.d("Error1--","-------");
        }
        try {
            PriceList = pc.GetList();
            DisplayData(PriceList);
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
        Intent intent = new Intent(PriceDisplay.this, PopPriceOption.class);
        startActivity(intent);
    }
    //0 - name, 1 - type, 3 - price, 5 - %alc, 7 - cal, 8 - carb, 9 - location
    public static void DisplayData(ArrayList<List<String>> list) {
        try {
            String List = "";
            //AscOrDesc = 1 - Asc, 2 - Desc

                for (int i = 0; i < 100; i++) {
                    List += (String) list.get(i).get(0) +
                            "   " + (String) list.get(i).get(1) +
                            "   $" + (String) list.get(i).get(3) + "/oz\n";
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
            if(PopPriceOption.AscOrDesc == 1) {
                Log.d("Asssss","--------");
                for (int i = 0; i < list.size(); i++) {
                    List += (String) list.get(i).get(0) +
                            "   " + (String) list.get(i).get(1) +
                            "   $" + (String) list.get(i).get(3) + "/oz\n";
                }
            }
            else if (PopPriceOption.AscOrDesc == 2){
                Log.d("Desssss","--------");
                for (int i = list.size()-1; i >= 0 ; i--) {
                    List += (String) list.get(i).get(0) +
                            "   " + (String) list.get(i).get(1) +
                            "   $" + (String) list.get(i).get(3) + "/oz\n";
                }
            }
            Displaylist.setText(List);
        }
        catch(NullPointerException e){
            // Log.d("Error2--","------");
        }
    }


    public static ArrayList<List<String>> getPriceList(){
        return PriceList;
    }

}
