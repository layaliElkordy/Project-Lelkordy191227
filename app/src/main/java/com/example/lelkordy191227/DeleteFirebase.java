package com.example.lelkordy191227;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteFirebase extends AppCompatActivity {
    String getidf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_firebase);
        Firebase  firebase  = new Firebase();
        EditText idf = (EditText)findViewById(R.id.id);
        Button deletebttn = (Button)findViewById(R.id.insertdatabttn);
        deletebttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getidf = idf.getText().toString();
                firebase.DeleteRecord(getidf);
                Toast.makeText(DeleteFirebase.this,"Record Deleted Successfully",Toast.LENGTH_SHORT).show();
            }
        });

    }
}