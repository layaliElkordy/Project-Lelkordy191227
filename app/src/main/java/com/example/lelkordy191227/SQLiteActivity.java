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

public class SQLiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_q_lite);
        Button firetosq = (Button)findViewById(R.id.firetosq);
        Button updatedata = (Button)findViewById(R.id.updaterdata);
        Button deletedate = (Button)findViewById(R.id.deletedata);
        Button viewdata = (Button)findViewById(R.id.viewdata);
        ImageView icon3 = (ImageView)findViewById(R.id.icon3);
        SharedPreferences sp1 = PreferenceManager.getDefaultSharedPreferences(this);
        String icon = sp1.getString("ww", String.valueOf(0));
        if(icon.equals(null)){
            icon3.setImageResource(R.drawable.deficon);
        }
        if(icon.equals("Clear")){
            icon3.setImageResource(R.drawable.clear);
            Log.d("layali", "onCreate: error getting weather ");
        }
        if(icon.equals("Clouds")){
            icon3.setImageResource(R.drawable.cloudy);
        }
        if(icon.equals("Rain")){
            icon3.setImageResource(R.drawable.rainy);
        }

        firetosq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SQLiteActivity.this,InsertSqlite.class));
            }
        });

        updatedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SQLiteActivity.this,UpdateSqlite.class));
            }
        });

        deletedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SQLiteActivity.this,DeleteSqlite.class));
            }
        });

        viewdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SQLiteActivity.this,FetchSqlite.class));
            }
        });
    }
}