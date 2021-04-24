package com.example.lelkordy191227;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FetchSqlite extends AppCompatActivity {
    ListView listview;
    ArrayList<String> user = new ArrayList<>();
    Cursor cur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_sqlite);
        DatabaseHelper db = new DatabaseHelper(this);
        listview = (ListView) findViewById(R.id.list2);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.activity_fetch_sqlite, R.id.sqlitelist, user);
        listview.setAdapter(arrayAdapter);
        cur = db.ViewUsers();
        while (cur.moveToNext()) {
            String id = cur.getString(0);
            String email = cur.getString(1);
            String fname = cur.getString(2);
            String lname = cur.getString(3);
            String number = cur.getString(4);
            user.add(" User id: " + id + "\n Email Address: " + email + "\n First Name: " + fname + "\n Last Name: " + lname + "\n Phone Number: " + number);
            arrayAdapter.notifyDataSetChanged();

        }
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //int pos = parent.getPositionForView(view);
                try {
                    int rows = 0;
                    cur = db.ViewUsers();

                    while (rows <= position) {
                        cur.moveToNext();
                        rows++;
                    }
                    Toast.makeText(FetchSqlite.this, " " + cur.getString(2) + " " + cur.getString(3), Toast.LENGTH_SHORT).show();
                    Log.d("layali",  cur.getString(2) + " " +cur.getString(3));


                } catch (Exception e) {
                    Log.d("Layali", "SQL ERROR" + e);

                }
            }
        });
    }
}