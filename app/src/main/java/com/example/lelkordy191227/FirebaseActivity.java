package com.example.lelkordy191227;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FirebaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        Button insertrec = (Button)findViewById(R.id.firetosq);
        Button updaterec = (Button)findViewById(R.id.updaterdata);
        Button deleterec = (Button)findViewById(R.id.deletedata);
        Button fetchrec = (Button)findViewById(R.id.viewdata);
        Button fetchspec = (Button)findViewById(R.id.fetchspec);
        ImageView icon2  = (ImageView)findViewById(R.id.icon2);
        SharedPreferences sp2 = PreferenceManager.getDefaultSharedPreferences(this);

        String icon = sp2.getString("ww", String.valueOf(0));

        if(icon.equals(null)){
            icon2.setImageResource(R.drawable.deficon);
        }
        if(icon.equals("Clear")){
            icon2.setImageResource(R.drawable.clear);
            Log.d("layali", "onCreate: error getting weather ");
        }
        if(icon.equals("Clouds")){
            icon2.setImageResource(R.drawable.cloudy);
        }
        if(icon.equals("Rain")){
            icon2.setImageResource(R.drawable.rainy);
        }


        /**********************Insert New Record************************/
        insertrec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirebaseActivity.this,InsertFirebase.class));
            }
        });
        /***********************Update Record**************************/
        updaterec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirebaseActivity.this,UpdateFirebase.class));
            }
        });
        /************************Delete Record*************************/
        deleterec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirebaseActivity.this,DeleteFirebase.class));

            }
        });
        /**********************Fetch all Records************************/
        fetchrec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirebaseActivity.this,FetchFirebase.class));

            }
        });
        /**********************Fecth Specific Record************************/
        fetchspec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirebaseActivity.this,FetchspecFirebase.class));

            }
        });

    }
}