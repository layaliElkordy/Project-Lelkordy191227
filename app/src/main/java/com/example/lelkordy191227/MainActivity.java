package com.example.lelkordy191227;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button firebase = (Button)findViewById(R.id.firebase);
        Button sqlite = (Button)findViewById(R.id.sqlite);
        Button weather = (Button)findViewById(R.id.weather);
        ImageView icon1  = (ImageView)findViewById(R.id.icon1);
        SharedPreferences sp3 = PreferenceManager.getDefaultSharedPreferences(this);
        String icon = sp3.getString("ww", String.valueOf(0));
        if(icon.equals("Clear")){
            icon1.setImageResource(R.drawable.clear);
            Log.d("layali", "onCreate: error getting weather " +icon);
        }
        if(icon.equals("Clouds")){
            icon1.setImageResource(R.drawable.cloudy);
        }
        if(icon.equals("Rain")){
            icon1.setImageResource(R.drawable.rainy);
        }



        /**********************Firebase Button************************/
        firebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FirebaseActivity.class));

            }
        });

        /**********************SQLite Button************************/
        sqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SQLiteActivity.class));

            }
        });

        /**********************Weather Button************************/
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WeatherMap.class));

            }
        });

    }




} //End of code