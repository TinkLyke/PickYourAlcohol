package com.example.will.a3a04_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserMain extends AppCompatActivity implements View.OnClickListener  {
    private Button UserInfo_Button;
    private Button ToPrice_Button;
    private Button ToGeo_Button;
    private Button ToNtn_Button;

    private Button ToAdv_Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        UserInfo_Button = (Button)findViewById(R.id.UserInfo_Button);
        ToPrice_Button = (Button)findViewById(R.id.Price_Button);
        ToGeo_Button = (Button)findViewById(R.id.Geography_Button);
        ToNtn_Button = (Button)findViewById(R.id.Ntn_Button);
        ToAdv_Button = (Button)findViewById(R.id.Advance_Button);
        UserInfo_Button.setOnClickListener(this);
        ToPrice_Button.setOnClickListener(this);
        ToGeo_Button.setOnClickListener(this);
        ToNtn_Button.setOnClickListener(this);
        ToAdv_Button.setOnClickListener(this);

    }
    @Override
    public void onClick(View view){
        if(view == UserInfo_Button){
            ToUserInfo();
        }
        else if(view == ToPrice_Button){
            ToPriceList();
        }
        else if(view == ToGeo_Button){
            ToGeoList();
        }
        else if(view == ToNtn_Button){
            ToNtnList();
        }
        else if(view == ToAdv_Button){
            ToAdvList();
        }
    }
    public void ToUserInfo(){
        Intent intent = new Intent(UserMain.this, UserInfoMenu.class);
        startActivity(intent);
    }

    public void ToPriceList(){
        Intent intent = new Intent(UserMain.this, PriceDisplay.class);
        startActivity(intent);
    }

    public void ToGeoList(){
        Intent intent = new Intent(UserMain.this, GeoDisplay.class);
        startActivity(intent);
    }

    public void ToNtnList(){
        Intent intent = new Intent(UserMain.this, NtnDisplay.class);
        startActivity(intent);
    }
    public void ToAdvList(){
        Intent intent = new Intent(UserMain.this, AdvanceSort.class);
        startActivity(intent);
    }
}
