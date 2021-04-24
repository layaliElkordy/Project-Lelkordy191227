package com.example.lelkordy191227;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FetchspecFirebase extends AppCompatActivity {
    DatabaseReference myRef;
    TextView emailres, fnameres,lnamres,numberes;
    String getidf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetchspec_firebase);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
        emailres = (TextView)findViewById(R.id.emailres);
        fnameres = (TextView)findViewById(R.id.fnameres);
        lnamres = (TextView)findViewById(R.id.lnameres);
        numberes = (TextView)findViewById(R.id.numberes);
        EditText idf = (EditText)findViewById(R.id.id);
        Button fetchspecbttn = (Button)findViewById(R.id.updatedatabttn);
        fetchspecbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getidf  = idf.getText().toString();
                FetchSpecificRecord(getidf);
            }
        });

    }
    /*********************************************Fetch Specific Record*************************************************/
    public void FetchSpecificRecord(String id) {
        myRef.orderByChild("userId").equalTo(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot datas : snapshot.getChildren()) {
                        String key = datas.getKey();
                        String email = datas.child("emailAddress").getValue().toString();
                        String fname = datas.child("firstName").getValue().toString();
                        String lname = datas.child("lastName").getValue().toString();
                        String number = datas.child("phoneNumber").getValue().toString();
                        emailres.setText(email);
                        fnameres.setText(fname);
                        lnamres.setText(lname);
                        numberes.setText(number);
                        String s = snapshot.toString();
                        Log.d("layali", "Specific Record: " + s);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("layali", "Fetching Specific Record Error " +error);

            }
        });
    }
}