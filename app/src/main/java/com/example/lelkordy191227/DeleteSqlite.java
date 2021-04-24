package com.example.lelkordy191227;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteSqlite extends AppCompatActivity {
    String getids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_sqlite);
        EditText ids = (EditText)findViewById(R.id.id);
        Button deletedatabttn =  (Button)findViewById(R.id.insertdatabttn);
        DatabaseHelper db = new DatabaseHelper(this);
        deletedatabttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getids = ids.getText().toString();
                db.DeleteUser(getids);
                Toast.makeText(DeleteSqlite.this,"Data Deleted Successfully",Toast.LENGTH_SHORT).show();

            }
        });

    }
}