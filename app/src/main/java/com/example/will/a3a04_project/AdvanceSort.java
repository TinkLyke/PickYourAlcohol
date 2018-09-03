package com.example.will.a3a04_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class AdvanceSort extends AppCompatActivity implements View.OnClickListener{
    private Button adv_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adcance_sort);
        adv_button = (Button) findViewById(R.id.AdvSearch_Button);


        adv_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if(view == adv_button){
            Log.d("go to sort", "------");
            ToResult();


        }
    }

    public void ToResult(){
        Intent intent = new Intent(AdvanceSort.this, Result.class);
        startActivity(intent);
    }
}
