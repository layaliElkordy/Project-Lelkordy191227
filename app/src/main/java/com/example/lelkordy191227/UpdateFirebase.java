package com.example.lelkordy191227;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateFirebase extends AppCompatActivity {
    String getidf,getemailf,getfnamef,getlnamef,getnumberf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_firebase);
        Firebase firebase = new Firebase();
        EditText idf = (EditText)findViewById(R.id.id);
        EditText emailf = (EditText)findViewById(R.id.email);
        EditText fnamef = (EditText)findViewById(R.id.fname);
        EditText lnamef = (EditText)findViewById(R.id.lname);
        EditText numberf = (EditText)findViewById(R.id.number);
        Button updatebttn = (Button)findViewById(R.id.updatedatabttn);

        updatebttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getidf = idf.getText().toString();
                getemailf = emailf.getText().toString();
                getfnamef = fnamef.getText().toString();
                getlnamef = lnamef.getText().toString();
                getnumberf = numberf.getText().toString();
                firebase.UpdateRecord(getemailf,getfnamef,getlnamef,getnumberf,getidf);
                Toast.makeText(UpdateFirebase.this,"Record Updated Successfully",Toast.LENGTH_SHORT).show();

            }
        });
    }
}