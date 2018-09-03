package com.example.will.a3a04_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.*;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LogIn extends AppCompatActivity implements View.OnClickListener  {
    private Button LogIn_Button;
    private EditText Username;
    private EditText Password;
    public ArrayList<String> ExistedUser = new ArrayList<>();
    public ArrayList<Long> ExistedUserType = new ArrayList<>();
    public ArrayList<String> ExistedPassword = new ArrayList<>();
    public ArrayList<String> ExistedEmail = new ArrayList<>();
    private FirebaseDatabase database =FirebaseDatabase.getInstance();
    private DatabaseReference databaseRef = database.getReference("UserInfoDB");
    private ProgressDialog progressDialog;

    public static User currentUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UserRetrieve();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        LogIn_Button = (Button)findViewById(R.id.LogIn_Button);
        Username = (EditText) findViewById(R.id.Username);
        Password = (EditText) findViewById(R.id.Password);

        LogIn_Button.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);

        //catch(NullPointerException e){


    }

    @Override
    public void onClick(View view){
        if(view == LogIn_Button){
            LogIn();
        }
    }

    private void LogIn(){
        String InputUsername = Username.getText().toString().trim();
        String InputPassword = Password.getText().toString().trim();
        if(FoundUser(InputUsername,InputPassword)) {
            progressDialog.setMessage("Logging in...");
            progressDialog.show();
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                        }
                    }, 3000);



            ToUserMenu();
        }
        else{
            progressDialog.setMessage("Username/Password is incorrect");
            progressDialog.show();
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                        }
                    }, 3000);
        }






    }


    ///////////////////Check Duplicate Account////
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
            ExistedUserType.add((Long) singleUser.get("UserType"));
            ExistedEmail.add((String) singleUser.get("Email"));
            ExistedPassword.add((String) singleUser.get("Password"));
        }
    }

    private boolean FoundUser(String username,String password){
        for(int i = 0; i< ExistedUser.size(); i++) {
            if(username.equals(ExistedUser.get(i))&& password.equals(ExistedPassword.get(i))) {

                currentUser.SetName(ExistedUser.get(i));
                currentUser.SetEmail(ExistedEmail.get(i));
                currentUser.SetPassword(ExistedPassword.get(i));
                currentUser.SetUserType(ExistedUserType.get(i).intValue());
                return true;
            }
        }
        return false;

    }

    ////To main user menu///
    public void ToUserMenu(){
        Intent intent = new Intent(LogIn.this, UserMain.class);
        startActivity(intent);
    }
}
