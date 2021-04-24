package com.example.lelkordy191227;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InsertSqlite extends AppCompatActivity {
    String getids;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_sqlite);
        DatabaseHelper db = new DatabaseHelper(this);
        EditText ids = (EditText)findViewById(R.id.id);
        Button insertdatabttn = (Button)findViewById(R.id.insertdatabttn);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");


        insertdatabttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getids = ids.getText().toString();
                myRef.orderByChild("userId").equalTo(getids).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot datas : snapshot.getChildren()) {
                                String key = datas.getKey();
                                String email = datas.child("emailAddress").getValue().toString();
                                String fname = datas.child("firstName").getValue().toString();
                                String lname = datas.child("lastName").getValue().toString();
                                String number = datas.child("phoneNumber").getValue().toString();
                                String id = datas.child("userId").getValue().toString();
                                String s = snapshot.toString();
                                Log.d("layali", "Specific Record: " + s);
                                db.AddUser(id,email,fname,lname,number);
                                Toast.makeText(InsertSqlite.this,"New Data Added Successfully",Toast.LENGTH_SHORT).show();
                            }
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("layali", "Fetching Specific Record Error " +error);

                    }
                });

            }
        });
    }
}