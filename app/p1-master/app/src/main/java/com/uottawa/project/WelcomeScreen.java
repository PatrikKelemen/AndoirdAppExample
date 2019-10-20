package com.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
    }

    public void onLogout(View view) {
        Intent returnIntent = new Intent();
        //We might use this to send stuff to the next page??
        //returnIntent.putExtra("imageID", selectedImage.getId());

        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
