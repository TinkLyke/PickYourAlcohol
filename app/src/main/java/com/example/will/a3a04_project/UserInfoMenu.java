package com.example.will.a3a04_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserInfoMenu extends AppCompatActivity {
    TextView username;
    TextView email;
    TextView password;
    TextView usertype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_menu);
        username = (TextView)findViewById(R.id.UsernameTxt);
        email = (TextView)findViewById(R.id.EmailTxt);
        password = (TextView)findViewById(R.id.PasswordTxt);
        usertype = (TextView)findViewById(R.id.UserTypeTxt);
        username.setText("Username: " + LogIn.currentUser.Name);
        email.setText("Email: " + LogIn.currentUser.Email);
        password.setText("Password: " + LogIn.currentUser.Password);
        usertype.setText("UserType: " + CovertUserType(LogIn.currentUser.UserType));
    }

    public String CovertUserType(int n){
        if(n == 1){
            return "Consumer";
        }
        else if (n == 2){
            return "Retailer";
        }
        return "";
    }
}
