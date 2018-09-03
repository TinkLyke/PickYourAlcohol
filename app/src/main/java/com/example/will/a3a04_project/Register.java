package com.example.will.a3a04_project;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.EditText;
import android.view.*;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private Button Register_Button;
    private EditText Username;
    private EditText Password;
    private EditText Email;
    private ProgressDialog progressDialog;

    private ArrayList<String> ExistedUser = new ArrayList<>();

    private FirebaseDatabase database =FirebaseDatabase.getInstance();
    private DatabaseReference databaseRef = database.getReference("UserInfoDB");


    private int UserType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseRef.push().setValue(new User("wu","123","wu@yahoo.com", 1));
        UserRetrieve();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Register_Button = (Button)findViewById(R.id.Register_Button);
        Username = (EditText) findViewById(R.id.Username);
        Password = (EditText) findViewById(R.id.Password);
        Email = (EditText) findViewById(R.id.Email);
        Register_Button.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
    }

    private void RegisterUser(){
        String InputEmail = Email.getText().toString().trim();
        String InputUsername = Username.getText().toString().trim();
        String InputPassword = Password.getText().toString().trim();

        if(!isValidEmail(InputEmail)){
            Toast.makeText(this,"Please enter valid email",Toast.LENGTH_SHORT).show();
            return;
        }


        if(!isValidUsername(InputUsername)){
            Toast.makeText(this,"Please enter valid username",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(InputPassword)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }



        if(!isExisted(InputUsername)) {
            User Newuser = new User(InputUsername, InputPassword, InputEmail, UserType);
            databaseRef.push().setValue(Newuser);
            progressDialog.setMessage("User is created!");
            progressDialog.show();
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                        }
                    }, 3000);

            ToLogIn();



        }
        else{
            progressDialog.setMessage("Username has been taken!");
            progressDialog.show();
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                        }
                    }, 3000);
        }



    }

    @Override
    public void onClick(View view){
        if(view == Register_Button){
            RegisterUser();
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.UserTypeConsumer:
                if (checked)
                    UserType = 1;
                    break;
            case R.id.UserTypeRetailer:
                if (checked)
                    UserType = 2;
                    break;
        }
    }
    public final boolean isValidEmail(CharSequence Email) {
        Pattern EmailPattern = Pattern.compile( "^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+([a-zA-Z])+");
        Matcher IsMatcher = EmailPattern.matcher(Email);
        if (IsMatcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
    public final boolean isValidUsername(String Username) {

        if (TextUtils.isEmpty(Username)) {
            return false;
        }
        else if((Username.charAt(0) >= 'a'&& Username.charAt(0)<='z')
                ||(Username.charAt(0) >= 'A' && Username.charAt(0)<='Z') ){
            return true;
        }

        else {
            return false;
        }
    }
    ////To main user menu///
    public void ToLogIn(){
        Intent intent = new Intent(Register.this, LogIn.class);
        startActivity(intent);
    }


//////////////////////Check Duplicate Account - Retrieve User Data/////////////////////
    public void UserRetrieve(){
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                collectUserInfo((Map<String,User>) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // return Usergroup;
    }

    private void collectUserInfo(Map<String,User> users) {

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, User> entry : users.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            ExistedUser.add((String) singleUser.get("Name"));
        }
        //Log.d("hey-------------","---------");
        System.out.println(ExistedUser.toString());
    }

    private boolean isExisted(String name){
        for(int i = 0; i < ExistedUser.size();i++){
            if(name.equals((String)ExistedUser.get(i))){
                return true;
            }
        }

        return false;
    }



}
