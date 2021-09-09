package com.thien.socialmediaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    //fibase auth
    FirebaseAuth firebaseAuth;
    //views
    TextView mProfileTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //action bar and it titlte
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Profile");
        //init
        firebaseAuth = FirebaseAuth.getInstance();
        mProfileTv = mProfileTv.findViewById(R.id.profileTv);

    }
     private void checkUserStatus(){
        //get current user
         FirebaseUser user = firebaseAuth.getCurrentUser();
         if(user != null){
            //user is signed
             //set email of loggined in user
             mProfileTv.setText(user.getEmail());
         }else{
             //user not sign in , got o main activity
             startActivity(new Intent(ProfileActivity.this, MainActivity.class));
             finish();;
         }
     }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onStart() {
        //check on start of app
        checkUserStatus();
        super.onStart();
    }
//    inflate optio menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //handle menu item  click

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //get item
        int id = item.getItemId();
        if(id  ==R.id.action_logout){
            firebaseAuth.signOut();
            checkUserStatus();
        }
        return super.onOptionsItemSelected(item);
    }
}