package com.example.lelkordy191227;

import android.app.AlertDialog;
import android.util.Log;

import androidx.annotation.NonNull;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class Firebase {
    DatabaseReference myRef;

    public Firebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
    }

    /*********************************************Add record*************************************************/
    public void AddRecord(String email, String firstname, String lastname, String number, String id) {
        User user = new User(email, firstname, lastname, number, id);
        myRef.child(id).setValue(user);

    }

    /*********************************************Delete record*************************************************/
    public void DeleteRecord(String id) {

        myRef.orderByChild("userId").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot datas : snapshot.getChildren()) {
                        String key = datas.getKey();
                        myRef.child(key).removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("layali", "Deleting Record Error " +error);
            }
        });
    } //end of delete user

    /*********************************************Update Record*************************************************/
    public void UpdateRecord(String email, String firstname, String lastname, String number, String id) {
        myRef.orderByChild("userId").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot datas : dataSnapshot.getChildren()) {
                        String key = datas.getKey();
                        String em = datas.child("emailAddress").toString();
                        String fname = datas.child("firstName").toString();
                        String lname = datas.child("lastName").toString();
                        String pnumber = datas.child("phoneNumber").toString();
                        String pid = datas.child("userId").toString();
                        myRef.child(key).child("emailAddress").setValue(email);
                        myRef.child(key).child("firstName").setValue(firstname);
                        myRef.child(key).child("lastName").setValue(lastname);
                        myRef.child(key).child("phoneNumber").setValue(number);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("layali", "Updating Record Error " +error);

            }
        });

    }//End of Update Record

    /*********************************************Fetch Record*************************************************/
    /**public void FetchRecord(DatabaseReference myRef) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object object = snapshot.getValue(Object.class);
                String json = new Gson().toJson(object);
                Log.d("layali", "onDataChange: " + json);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("layali", "Fetching Record Error " +error);
            }
        });
    }*/
    /*********************************************Fetch Specific Record*************************************************/
    /**public void FetchSpecificRecord(String id) {

        myRef.orderByChild("userId").equalTo(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Object object = snapshot.getValue(Object.class);
                //String email = snapshot.child("EmailAddress").getValue(String.class);

                String s = snapshot.toString();

                Log.d("layali", "Specific Record: " + s);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("layali", "Fetching Specific Record Error " +error);

            }
        });

    }**/



}