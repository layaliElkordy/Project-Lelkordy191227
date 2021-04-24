package com.example.lelkordy191227;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FetchFirebase extends AppCompatActivity {
    DatabaseReference myRef;
    FirebaseDatabase database;
    ListView listview;
    ArrayList<String> user = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_firebase);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
        listview = (ListView) findViewById(R.id.list2);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.activity_fetch_firebase, R.id.textlist, user);
        listview.setAdapter(arrayAdapter);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String id = snapshot.child("userId").getValue().toString();
                String email = snapshot.child("emailAddress").getValue().toString();
                String fname = snapshot.child("firstName").getValue().toString();
                String lname = snapshot.child("lastName").getValue().toString();
                String number = snapshot.child("phoneNumber").getValue().toString();
                user.add(" User ID: " + id + "\n Email Address: " + email + "\n First Name: " + fname + "\n Last Name: " + lname + "\n Phone Number: " + number);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("layali", "Fetching Record Error " + error);
            }
        });


    }
}