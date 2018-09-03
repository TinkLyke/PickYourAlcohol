package com.example.will.a3a04_project;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by will on 2017-04-07.
 */
public class PopNtnOption extends Activity implements View.OnClickListener {

    private EditText maxalc;
    private EditText minalc;
    private EditText maxcal;
    private EditText mincal;
    private EditText maxcarb;
    private EditText mincarb;

    private float dmaxalc;
    private float dminalc;
    private float dmaxcal;
    private float dmincal;
    private float dmaxcarb;
    private float dmincarb;

    private Button Search_Button;
    public static int sorttype; // 1 - Asc, 2 - Desc

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_ntn_option);
        sorttype = 0;
        dmaxalc = -1;
        dminalc = -1;
        dmaxcal = -1;
        dmincal = -1;
        dmaxcarb = -1;
        dmincarb = -1;


        maxalc = (EditText) findViewById(R.id.Max_Alc);
        minalc = (EditText) findViewById(R.id.Min_Alc);
        maxcal = (EditText) findViewById(R.id.Max_Cal);
        mincal = (EditText) findViewById(R.id.Min_Cal);
        maxcarb = (EditText) findViewById(R.id.Max_Carb);
        mincarb = (EditText) findViewById(R.id.Min_Carb);


        Search_Button = (Button) findViewById(R.id.SearchNtn_Button);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(0.7*width),(int)(0.8*height));

        Search_Button.setOnClickListener(this);

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.Alc_Button:
                if (checked)
                    sorttype = 0;
                break;
            case R.id.Cal_Button:
                if (checked)
                    sorttype = 1;
                break;
            case R.id.Carb_Button:
                if (checked)
                    sorttype = 2;
                break;

        }
    }

    @Override
    public void onClick(View view){
        if(view == Search_Button){

            SortNtnbyOption();


        }
    }

    public void SortNtnbyOption(){
        if(!maxalc.getText().toString().equals("-1")) {
            dmaxalc = Float.parseFloat(maxalc.getText().toString().trim());
        }
        if(!minalc.getText().toString().equals("-1")) {
            dminalc = Float.parseFloat(minalc.getText().toString().trim());
        }
        if(!maxcal.getText().toString().equals("-1")) {
            dmaxcal = Float.parseFloat(maxcal.getText().toString().trim());
        }
        if(!mincal.getText().toString().equals("-1")) {
            dmincal = Float.parseFloat(mincal.getText().toString().trim());
        }
        if(!maxcarb.getText().toString().equals("-1")) {
            dmaxcarb = Float.parseFloat(maxcarb.getText().toString().trim());
        }
        if(!mincarb.getText().toString().equals("-1")) {
            dmincarb = Float.parseFloat(mincarb.getText().toString().trim());
        }
        //Log.d("Location is", loc);
        Filter filter = new Filter();
        filter.SetArraylist(NtnDisplay.getNtnList());

        //Log.d("size is ", Integer.toString(filter.al.size()));
        filter.SortByNutrition(dmaxalc,dminalc,dmaxcal,dmincal,dmaxcarb,dmincarb);
        //Log.d("the 1st price is", (String) filter.al.get(0).get(9));
        NtnDisplay.UpdatedDisplayData(filter.al);

    }
}
