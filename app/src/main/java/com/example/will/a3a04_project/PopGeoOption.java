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
public class PopGeoOption extends Activity implements View.OnClickListener {

    private EditText Location;
    private Button Search_Button;
    public static int AscOrDescOrC; // 1 - Asc, 2 - Desc, 3 - Country
    public String loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_geo_option);

        AscOrDescOrC = 1;
        loc = "-1";
        Location = (EditText) findViewById(R.id.Location);

        Search_Button = (Button) findViewById(R.id.SearchGeo_Button);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(0.7*width),(int)(0.45*height));

        Search_Button.setOnClickListener(this);

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.Ascend_Button:
                if (checked)
                    AscOrDescOrC = 1;
                break;
            case R.id.Descend_Button:
                if (checked)
                    AscOrDescOrC = 2;
                break;
            case R.id.Location_Button:
                if (checked)
                    AscOrDescOrC = 3;
                break;
        }
    }

    @Override
    public void onClick(View view){
        if(view == Search_Button){
            Log.d("go to sort", "------");
            SortGeobyOption();


        }
    }
    public void SortGeobyOption(){
        if(AscOrDescOrC == 3) {
            if (!Location.getText().toString().equals("")) {
                loc = Location.getText().toString().trim();
            }
        }else {
            loc = "-1";
        }
        Log.d("Location is", loc);
        Filter filter = new Filter();
        filter.SetArraylist(GeoDisplay.getGeoList());
        filter.SortByGeo(loc);
        //Log.d("the 1st price is", (String) filter.al.get(0).get(3));
        GeoDisplay.UpdatedDisplayData(filter.al);

    }
}
