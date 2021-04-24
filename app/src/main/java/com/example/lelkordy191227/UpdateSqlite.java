package com.example.lelkordy191227;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateSqlite extends AppCompatActivity {
    String getids,getemails,getfnames,getlnames,getnumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sqlite);
        EditText ids = (EditText)findViewById(R.id.id);
        EditText emails = (EditText)findViewById(R.id.email);
        EditText fnames = (EditText)findViewById(R.id.fname);
        EditText lnames = (EditText)findViewById(R.id.lname);
        EditText numbers = (EditText)findViewById(R.id.number);
        Button updatedatabttn = (Button)findViewById(R.id.updatedatabttn);
        DatabaseHelper db = new DatabaseHelper(this);

        updatedatabttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getids = ids.getText().toString();
                getemails = emails.getText().toString();
                getfnames = fnames.getText().toString();
                getlnames = lnames.getText().toString();
                getnumbers = numbers.getText().toString();
                db.UpdateUser(getids,getemails,getfnames,getlnames,getnumbers);
                Toast.makeText(UpdateSqlite.this,"Data Updated Successfully",Toast.LENGTH_SHORT).show();

            }
        });

    }
}