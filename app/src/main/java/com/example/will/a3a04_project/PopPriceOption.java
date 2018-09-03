package com.example.will.a3a04_project;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by will on 2017-04-06.
 */
public class PopPriceOption extends Activity implements View.OnClickListener {

    private EditText Maxprice;
    private EditText Minprice;
    private Button Search_Button;
    public static int AscOrDesc; // 1 - Asc, 2 - Desc
    private float maxprice;
    private float minprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_price_option);
        //setContentView(R.layout.pop_price_option);
        AscOrDesc = 1;
        maxprice = -1;
        minprice = -1;
        Maxprice = (EditText) findViewById(R.id.MaxPrice);
        Minprice = (EditText) findViewById(R.id.MinPrice);
        Search_Button = (Button) findViewById(R.id.SearchPrice_Button);


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
                    AscOrDesc = 1;
                break;
            case R.id.Descend_Button:
                if (checked)
                    AscOrDesc = 2;
                break;
        }
    }


    @Override
    public void onClick(View view){
        if(view == Search_Button){
            Log.d("go to sort", "------");
            SortPricebyOption();


        }
    }

    public void SortPricebyOption(){
        if(!Maxprice.getText().toString().equals("")) {
            maxprice = Float.parseFloat(Maxprice.getText().toString().trim());
        }
        if(!Minprice.getText().toString().equals("")) {
            minprice = Float.parseFloat(Minprice.getText().toString().trim());
        }
            Filter filter = new Filter();
            filter.SetArraylist(PriceDisplay.getPriceList());
            filter.SortByPrice(maxprice, minprice);
            //Log.d("the 1st price is", (String) filter.al.get(0).get(3));
            PriceDisplay.UpdatedDisplayData(filter.al);

    }
}
